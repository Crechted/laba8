package Server;

import Code8.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
import java.util.concurrent.Callable;

public class WorkHandler implements Callable<DatagramPacket> {
    private byte[] buffer;
    private DatagramPacket packet;
    private InetAddress inetAddress;
    private int port;
    private boolean isUser;
    private Workable work;
    private int access;

    public WorkHandler(Workable work, byte[] buffer, DatagramPacket packet, InetAddress inetAddress, int port, boolean isUser, int access) {
        this.work = work;
        this.buffer = buffer;
        this.packet = packet;
        this.inetAddress = inetAddress;
        this.port = port;
        this.isUser = isUser;
        this.access = access;
    }

    @Override
    public DatagramPacket call() throws SQLException, IOException {
        Message answer = new Message(work);
        TreeSet<Organization> tsa = new TreeSet<>();
        TreeSet<UserInformation> tsu = new TreeSet<>();
        if(work instanceof Command.Start){
            tsa.addAll(CollectionOrganization.getCollectionOrganization().getCollection());
            answer.setAllCollection(tsa);
            tsu.addAll(CollectionOrganization.getCollectionOrganization().getUserCollection());
            answer.setUserCollection(tsu);
            answer.setAnswerServer("");
            //System.out.println("was completed command " + work.getClass().getSimpleName());
            buffer = Converter.convertToBytes(answer);
            packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            return packet;
        }

        // PASSWORD - LIE
        if (!isUser) {
            tsa.addAll(CollectionOrganization.getCollectionOrganization().getCollection());
            answer.setAllCollection(tsa);
            tsu.addAll(CollectionOrganization.getCollectionOrganization().getUserCollection());
            answer.setUserCollection(tsu);
            answer.setAnswerServer("WRONG PASSWORD!!!");
            //System.out.println("was completed command " + work.getClass().getSimpleName());
            buffer = Converter.convertToBytes(answer);
            packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            return packet;
        }
        //EXIT

        if (work instanceof Command.Exit) {
            tsa.addAll(CollectionOrganization.getCollectionOrganization().getCollection());
            answer.setAllCollection(tsa);
            tsu.addAll(CollectionOrganization.getCollectionOrganization().getUserCollection());
            answer.setUserCollection(tsu);
            answer.setAnswerServer("End");
            buffer = Converter.convertToBytes(answer);
            packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            System.out.println("Client EXIT");
            return packet;
        }

        //ADD
        if (work instanceof Command.Add) {
            Organization org = ((Command.Add) work).getOrg();

            String sql =
                    "INSERT INTO ORGANIZATION (ID, NAME, X, Y, CREATION_DATE, ANNUAL_TURNOVER, EMPLOYEES_COUNT, TYPE, ADDRESS, ACCESS) " +
                            "VALUES (nextval('SID'), ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = Server.connectionDB.prepareStatement(sql);

            stmt.setString(1 , org.getName());
            stmt.setLong(2, org.getCoordinates().getX());
            stmt.setInt(3, org.getCoordinates().getY());
            stmt.setDate(4, Date.valueOf(org.getCreationDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));
            stmt.setFloat(5, org.getAnnualTurnover());
            stmt.setInt(6, org.getEmployeesCount());
            stmt.setString(7, org.getType().NAME);
            stmt.setString(8, org.getOfficialAddress().getZipCode());
            stmt.setInt(9, access);


            //System.out.println(sql);

            int i = stmt.executeUpdate();
            if (i == 1)
                answer.setAnswerServer(work.work());
            else
                answer.setAnswerServer("Organization wasn't added");

            stmt.close();
            Server.connectionDB.commit();

            Statement st = Server.connectionDB.createStatement();
            sql = "SELECT * FROM CLIENTS";
            ResultSet res = st.executeQuery(sql);

            Server.synchronizeCollection();

            tsa.addAll(CollectionOrganization.getCollectionOrganization().getCollection());
            answer.setAllCollection(tsa);
            tsu.addAll(CollectionOrganization.getCollectionOrganization().getUserCollection());
            answer.setUserCollection(tsu);
            buffer = Converter.convertToBytes(answer);
            packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            //Command.Save save = Command.Save.getSave();
            System.out.println("Client exit");
            return packet;
        }

        //UPDATE BY ID
        if (work instanceof Command.UpdateElement) {
            Organization org = ((Command.UpdateElement) work).getOrg();

            String sql =
                    "UPDATE ORGANIZATION SET NAME = ?, X = ?, Y = ?, CREATION_DATE = ?, ANNUAL_TURNOVER = ?, " +
                            "EMPLOYEES_COUNT = ?, TYPE = ?, ADDRESS = ? " +
                            " WHERE ID = " + ((Command.UpdateElement) work).getId() + " AND ACCESS = " + access + ";";

            System.out.println("ID:" + ((Command.UpdateElement)work).getId());
            PreparedStatement stmt = Server.connectionDB.prepareStatement(sql);
            stmt.setString(1 , org.getName());
            stmt.setLong(2, org.getCoordinates().getX());
            stmt.setInt(3, org.getCoordinates().getY());
            stmt.setDate(4, Date.valueOf(org.getCreationDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));
            stmt.setFloat(5, org.getAnnualTurnover());
            stmt.setInt(6, org.getEmployeesCount());
            stmt.setString(7, org.getType().NAME);
            stmt.setString(8, org.getOfficialAddress().getZipCode());
            //System.out.println(sql);

            int i = stmt.executeUpdate();

            if (i == 1)
                answer.setAnswerServer(work.work());
            else
                answer.setAnswerServer("Organization wasn't updated, because not found Organizations by id: " + ((Command.UpdateElement) work).getId() + "\n" +
                        "or your wasn't create this Organization");

            stmt.close();
            Server.connectionDB.commit();

            Server.synchronizeCollection();

            tsa.addAll(CollectionOrganization.getCollectionOrganization().getCollection());
            answer.setAllCollection(tsa);
            tsu.addAll(CollectionOrganization.getCollectionOrganization().getUserCollection());
            answer.setUserCollection(tsu);
            buffer = Converter.convertToBytes(answer);
            packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            //Command.Save save = Command.Save.getSave();
            System.out.println("Client exit");
            return packet;
        }

        // ADD IF MIN

        if (work instanceof Command.AddIfMin) {
            Organization org = ((Command.AddIfMin) work).getOrg();

            answer.setAnswerServer(work.work());
            String sql =
                    "INSERT INTO ORGANIZATION (ID, NAME, X, Y, CREATION_DATE, ANNUAL_TURNOVER, EMPLOYEES_COUNT, TYPE, ADDRESS, ACCESS) " +
                            "VALUES (nextval('SID'), ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = Server.connectionDB.prepareStatement(sql);

            stmt.setString(1 , org.getName());
            stmt.setLong(2, org.getCoordinates().getX());
            stmt.setInt(3, org.getCoordinates().getY());
            stmt.setDate(4, Date.valueOf(org.getCreationDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));
            stmt.setFloat(5, org.getAnnualTurnover());
            stmt.setInt(6, org.getEmployeesCount());
            stmt.setString(7, org.getType().NAME);
            stmt.setString(8, org.getOfficialAddress().getZipCode());
            stmt.setInt(9, access);

            //System.out.println(sql);

            if (answer.equals("Organization was added")) {
                stmt.executeUpdate();
            }
            stmt.close();
            Server.connectionDB.commit();

            Server.synchronizeCollection();

            tsa.addAll(CollectionOrganization.getCollectionOrganization().getCollection());
            answer.setAllCollection(tsa);
            tsu.addAll(CollectionOrganization.getCollectionOrganization().getUserCollection());
            answer.setUserCollection(tsu);
            buffer = Converter.convertToBytes(answer);
            packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            System.out.println("Client exit");
            return packet;
        }

        //REMOVE BY ID
        if (work instanceof Command.Remove) {
            Statement stmt = Server.connectionDB.createStatement();
            String sql =
                    "DELETE FROM ORGANIZATION WHERE ID = " + ((Command.Remove) work).getId() + " AND ACCESS = " + access + ";";

            //System.out.println(sql);
            System.out.println("RID: " + ((Command.Remove) work).getId());
            System.out.println(((Command.Remove) work).getRemoveServer() == null);
            System.out.println("2ID: " + ((Command.Remove) work).getIdRemoverServer());
            System.out.println(((Command.Remove) work).getRemoveServer() == ((Command.Remove) work));
            int i = stmt.executeUpdate(sql);

            if (i == 1)
                answer.setAnswerServer(work.work());
            else
                answer.setAnswerServer("Organization wasn't deleted");

            stmt.close();
            Server.connectionDB.commit();

            Server.synchronizeCollection();

            tsa.addAll(CollectionOrganization.getCollectionOrganization().getCollection());
            answer.setAllCollection(tsa);
            tsu.addAll(CollectionOrganization.getCollectionOrganization().getUserCollection());
            answer.setUserCollection(tsu);
            buffer = Converter.convertToBytes(answer);
            packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            //Command.Save save = Command.Save.getSave();
            System.out.println("Client exit");
            return packet;
        }

        // REMOVE GREATER

        if (work instanceof Command.RemoveGreater) {
            answer.setAnswerServer(work.work());
            Statement stmt = Server.connectionDB.createStatement();
            String sql =
                    "DELETE FROM ORGANIZATION WHERE ANNUAL_TURNOVER > " + ((Command.RemoveGreater) work).getAnnualTurnover() + " AND ACCESS = " + access + ";";

            System.out.println(sql);

            if (answer.getAnswerServer().equals("Organizations \"greater\" specified were deleted")) {
                int i = stmt.executeUpdate(sql);
                System.out.println("Send!!");
                if (i == 0 ) {
                    answer.setAnswerServer("Organizations \"greater\" specified weren't deleted, because you'r dont created this Organization");
                    //CollectionOrganization.getCollectionOrganization().getCollection().add(((Command.RemoveGreater) work).getoRem());
                }
            }
            stmt.close();
            Server.connectionDB.commit();

            Server.synchronizeCollection();

            tsa.addAll(CollectionOrganization.getCollectionOrganization().getCollection());
            answer.setAllCollection(tsa);
            tsu.addAll(CollectionOrganization.getCollectionOrganization().getUserCollection());
            answer.setUserCollection(tsu);
            buffer = Converter.convertToBytes(answer);
            packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            //Command.Save save = Command.Save.getSave();
            System.out.println("Client exit");
            return packet;
        }

        // REMOVE LOWER

        if (work instanceof Command.RemoveLower) {
            answer.setAnswerServer(work.work());
            Statement stmt = Server.connectionDB.createStatement();
            String sql =
                    "DELETE FROM ORGANIZATION WHERE ANNUAL_TURNOVER < " + ((Command.RemoveLower) work).getAnnualTurnover() + " AND ACCESS = " + access + ";";

            //System.out.println(sql);

            if (answer.getAnswerServer().equals("Organizations \"lower\" specified were removed")) {
                int i = stmt.executeUpdate(sql);
                if (i == 0 ) {
                    answer.setAnswerServer("Organizations \"lower\" specified weren't deleted, because you'r dont created this Organization");
                    //CollectionOrganization.getCollectionOrganization().getCollection().add(((Command.RemoveLower) work).getoRem());
                }
            }
            stmt.close();
            Server.connectionDB.commit();

            Server.synchronizeCollection();

            tsa.addAll(CollectionOrganization.getCollectionOrganization().getCollection());
            answer.setAllCollection(tsa);
            tsu.addAll(CollectionOrganization.getCollectionOrganization().getUserCollection());
            answer.setUserCollection(tsu);
            buffer = Converter.convertToBytes(answer);
            packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            //Command.Save save = Command.Save.getSave();
            System.out.println("Client exit");
            return packet;
        }

        //ClEAR

        if (work instanceof Command.Clear) {
            answer.setAnswerServer(work.work());
            Statement stmt = Server.connectionDB.createStatement();
            String sql =
                    "DELETE FROM ORGANIZATION WHERE ACCESS = " + access + ";";

            //System.out.println(sql);


            stmt.executeUpdate(sql);
            stmt.close();
            Server.connectionDB.commit();

            Server.synchronizeCollection();

            tsa.addAll(CollectionOrganization.getCollectionOrganization().getCollection());
            answer.setAllCollection(tsa);
            tsu.addAll(CollectionOrganization.getCollectionOrganization().getUserCollection());
            answer.setUserCollection(tsu);
            buffer = Converter.convertToBytes(answer);
            packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            //Command.Save save = Command.Save.getSave();
            System.out.println("Client exit");
            return packet;
        }



        //DEFAULT
        Server.synchronizeCollection();

        answer.setAnswerServer(work.work());
        System.out.println("was completed command " + work.getClass().getSimpleName());

        tsa.addAll(CollectionOrganization.getCollectionOrganization().getCollection());
        answer.setAllCollection(tsa);
        tsu.addAll(CollectionOrganization.getCollectionOrganization().getUserCollection());
        answer.setUserCollection(tsu);
        buffer = Converter.convertToBytes(answer);
        packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
        return packet;

    }
}

