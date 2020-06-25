package Server;

import Code8.Command;
import Code8.Converter;
import Code8.Message;
import Code8.Workable;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.*;

public class RequestsHandler implements Runnable {

    private DatagramSocket socket;
    private DatagramPacket packet;
    private byte[] buffer = new byte[10000];
    private int port, portStart = 5680;
    private InetAddress inetAddress;

    public RequestsHandler (DatagramSocket socket, int portStart, InetAddress inetAddress) {
        this.socket = socket;
        this.portStart = portStart;
        this.inetAddress = inetAddress;
    }

    @Override
    public void run() {

        while (true) {
            try {
                //socket = new DatagramSocket(portStart);
                buffer = new byte[65 * 1024];
                packet = new DatagramPacket(buffer, buffer.length);
                boolean isUser = true;

                String login, password, passwordHash;
                int access = 0;

                socket.receive(packet);

                buffer = packet.getData();

                inetAddress = packet.getAddress();
                port = packet.getPort();

                Message message = (Message) Converter.convertFromBytes(buffer);

                login = message.getLogin();
                password = message.getPassword();

                passwordHash = PasswordHandler.getHash(password);
                Workable work = message.getWork();

                Statement stmt = Server.connectionDB.createStatement();
                String sql = "SELECT * FROM CLIENTS";
                ResultSet res = stmt.executeQuery(sql);

                System.out.println("Connected client:" + login);
                //System.out.println("****** in DB *********");
                while (res.next()){
                    String log = res.getString("LOGIN");
                    //System.out.println(log);
                    String pas = res.getString("PASSWORD");
                    //System.out.println(pas);
                    if (login.equals(log) && pas.equals(passwordHash))
                        access = res.getInt("ACCESS");
                    //System.out.println(access);

                    if (login.equals(log) && (pas.equals(passwordHash) == false)){
                        isUser = false;
                    }
                }

                stmt.close();

                if (access == 0 && isUser) {
                    stmt = Server.connectionDB.createStatement();
                    sql = "INSERT INTO CLIENTS VALUES (nextval('ACCESS_ITERATOR'), '" + login + "', '"+ passwordHash + "' );";
                    int i = stmt.executeUpdate(sql);
                    System.out.println("added user " + i);
                    stmt.close();
                    Server.connectionDB.commit();

                    // GET ACCESS NUMBER CLIENT
                    stmt = Server.connectionDB.createStatement();
                    sql = "SELECT * FROM CLIENTS";
                    res = stmt.executeQuery(sql);
                    while (res.next()){
                        String log = res.getString("LOGIN");
                        String pas = res.getString("PASSWORD");
                        if (log.equals(login) && pas.equals(passwordHash))
                            access = res.getInt("ACCESS");
                    }
                }

                //********* HANDLER START *********
                ExecutorService esHandler = Executors.newFixedThreadPool(1);
                boolean finalIsUser = isUser;

                Callable<DatagramPacket> handler = new WorkHandler(work, buffer, packet, inetAddress, port, isUser, access);
                Future result = esHandler.submit(handler);

                //********* HANDLER FINISH *********

                // SEND ANSWER
                Server.synchronizeCollection();
                socket.send((DatagramPacket) result.get());

                esHandler.shutdown();
            } catch (IOException | ClassNotFoundException | InterruptedException | ExecutionException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
