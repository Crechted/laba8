package Server;

import Code8.*;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {


    private static DatagramSocket socket;
    private static DatagramPacket packet;
    private static byte[] buffer = new byte[10000];
    private static int port, portStart = 5680;
    private static InetAddress inetAddress;


    static {
        System.out.println("Enter port:");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            portStart = Integer.parseInt(reader.readLine());
            System.out.println("port: " + portStart);
        } catch (Exception e) {
            System.out.println("Default port: 5680");
        }
    }


    public static Connection connectionDB;
    /**
     *Creates a new object of the CollectionOrganization class.
     *Created collection with objects from the file whose address is passed in the args variable[0]
     * @param args address of thew file
     * @see CollectionOrganization
     */
    public static void main(String[] args) {

        //****** CONNECT DATABASE *******


        //     "jdbc:postgresql://localhost:5432/tasteBase","????", "????"
        //      "jdbc:postgresql://pg:5432/studs","??????", "?????"
        try {
            Class.forName("org.postgresql.Driver");
            connectionDB = DriverManager.
                    getConnection("jdbc:postgresql://localhost:5432/tasteBase","??????", "????");
            connectionDB.setAutoCommit(false);

            synchronizeCollection();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("*happens* Exception with DateBase");
            e.printStackTrace();
        }


        //****** COMPLETED DATABASE ******

        printStartText();

        //start work with client

        ExecutorService esRequest = Executors.newCachedThreadPool();

        try {
            socket = new DatagramSocket(portStart);
        } catch (SocketException e) {
            System.out.println("*happens* socketException");
        }

            for (int i = 0; i < 8; i++) {

                RequestsHandler requestsHandler = new RequestsHandler(socket, portStart, inetAddress);
                Future result = esRequest.submit(requestsHandler);

                //EXCEPTION
            }

        esRequest.shutdown();



    }

    public static void printStartText(){
        System.out.println("Была создана коллекция с элементами типа Organization." +
                "\n" + "Элементы были созданы на основе БД ");
        int num = CollectionOrganization.getCollectionOrganization().getCollection().size();
        String s = "эл.";
        switch (num % 10){
            case 1:{ s = "элемент";
            break;}
            case 2:
            case 3:
                case 4:{ s = "элемента";
                    break;}
            case 0:
            case 5:
            case 6:
            case 7:
            case 8:
                case 9:{ s = "элементов";
            break;}
        }
        System.out.println("В данный момент в коллекции " + num + " " + s +
                "\n" + "Ожидаю клиентов...");
    }

    public static void synchronizeCollection() throws SQLException {
        Statement stmt = connectionDB.createStatement();
        String sql = "SELECT * FROM ORGANIZATION;";
        ResultSet res = stmt.executeQuery(sql);
        Organization organization;
        CollectionOrganization.getCollectionOrganization().getCollection().clear();

        while(res.next()) {
            long id = res.getLong("ID");
            String  name = res.getString("NAME");
            long x = res.getLong("X");
            int y = res.getInt("Y");
            Coordinates cor = new Coordinates(x, y);
            float annualTurnover = res.getFloat("ANNUAL_TURNOVER");
            Integer employeesCount = res.getInt("EMPLOYEES_COUNT");
            String typeS = res.getString("TYPE");
            LocalDateTime localDateTime = Converter.convertDateToLocalDateTime(res.getDate("CREATION_DATE"));
            OrganizationType type = null;
            if(typeS.equals(OrganizationType.COMMERCIAL.NAME))
                type = OrganizationType.COMMERCIAL;
            else if(typeS.equals(OrganizationType.PUBLIC.NAME))
                type = OrganizationType.PUBLIC;
            else if(typeS.equals(OrganizationType.TRUST.NAME))
                type = OrganizationType.TRUST;
            Address officialAddress = new Address(res.getString("ADDRESS"));
            int access = res.getInt("ACCESS");

            organization = new Organization(id, name, cor, localDateTime, annualTurnover, employeesCount, type, officialAddress);
            organization.setAccess(access);
            CollectionOrganization.getCollectionOrganization().getCollection().add(organization);
        }
        stmt.close();
        connectionDB.commit();

        stmt = connectionDB.createStatement();
        sql = "SELECT LOGIN, ACCESS FROM CLIENTS;";
        res = stmt.executeQuery(sql);
        UserInformation userInformation;
        CollectionOrganization.getCollectionOrganization().getUserCollection().clear();

        while(res.next()) {
            String  login = res.getString("LOGIN");
            int access = res.getInt("ACCESS");

            userInformation = new UserInformation(access, login);
            CollectionOrganization.getCollectionOrganization().getUserCollection().add(userInformation);
        }
        stmt.close();
        connectionDB.commit();
    }
}
