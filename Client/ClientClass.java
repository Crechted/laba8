package Client;


import Client.Application.Controllers.RootController;
import Code8.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Client.Application.Main;


public class ClientClass {

    private static ByteBuffer buffer;
    private static SocketAddress address;
    private static DatagramChannel channel;
    private static int port = 5680;
    private static String host = "localhost";
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void setPort(int port) {
        ClientClass.port = port;
    }

    public static void setHost(String host) {
        ClientClass.host = host;
    }

    private static byte[] bufferOut;
    private static byte[] bufferIn;
    private static Workable work;
    private static String login;
    private static String password;
    private static RootController rootController = Main.getRootController();

    public static void setLogin(String login) {
        ClientClass.login = login;
    }

    public static String getLogin() {
        return login;
    }

    public static void setPassword(String password) {
        ClientClass.password = password;
    }

    /**
     * @see CollectionOrganization
     * @see Work
     */
    public static Message SendAndReceiveAnswerByServer(String commandName) {

        work = null;
        Message answer = null;

        //new Thread(() -> Main.main(null)).start();

        try {
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
        } catch (IOException e) {
            //System.out.println("Channel is not open");
        }

        address = new InetSocketAddress(host, port);

        work = Work.goInteractiveWork(commandName);
        if (work == null)
            return null;

        try {

            bufferOut = Converter.convertToBytes(new Message(work, login, password));
            buffer = ByteBuffer.wrap(bufferOut);


            //EXECUTE_SCRIPT

            boolean exit = false;
            if (work instanceof Command.ExecuteScript) {
                work.work();
                ArrayList<Workable> list = Command.ExecuteScript.getExecuteScript().getListWork();

                for (int i = 0; i < list.size(); ++i) {

                    bufferOut = Converter.convertToBytes(new Message(list.get(i), login, password));
                    buffer = ByteBuffer.wrap(bufferOut);
                    channel.send(buffer, address);

                    answer = serverReceive(); //receive answer by the server
                    if (answer.getAnswerServer().equals("End")) {
                        //System.out.println(answer);
                        exit = true;
                        break;
                    }

                    //System.out.println(answer);
                    if(i != list.size()-1)
                        rootController.addLabelTextSpace(answer.getAnswerServer());
                }
                Work.removeScriptNameList();
                Command.ExecuteScript.getExecuteScript().getListWork().removeAll(list);
                return answer;
            }
            if (exit) {
                reader.close();

            }

            //DEFAULT

            channel.send(buffer, address);

            answer = serverReceive(); //receive answer by the server
            return answer;

            //EXCEPTION


        } catch (SocketException  e1) {
            System.out.println("*happens* SocketException");
        } catch (UnknownHostException e2) {
            System.out.println("*happens* UnknownHostException");
        } catch (IOException e3) {
            System.out.println("*happens* IOException");
            e3.printStackTrace();
        } catch (StackOverflowError e) {
            System.out.println(e.getStackTrace());
        } catch (UnresolvedAddressException e) {
            host = "localhost";
            System.out.println("*happens* UnresolvedAddressException");
            System.out.println("Default host: " + host);
        } catch (InterruptedException e) {
        } catch (ClassNotFoundException e) {
            System.out.println("*happens* ClassNotFoundException");;
        }
        if (answer == null) {
            answer = new Message(work);
            answer.setAnswerServer("Server is not responding");
        }
        return  answer;
    }// C:\Programming\Plugin\gson-2.8.6.jar Lab5 C:/Programming/resultNJ.json



    public static Message serverReceive() throws IOException, InterruptedException, ClassNotFoundException {
        ResourceBundle rb = ResourceBundle.getBundle("GuiRoot", Main.getLoc());
        rootController = Main.getRootController();
        String answer = "";
        Message messageByServer = new Message(null);
        ArrayList<byte[]> packetList = new ArrayList<>(1);
        boolean isReceived = false;
        int count = 0;
        for (int i = 0; true; ++i){
            byte[] packet = new byte[65*1024];
            ByteBuffer buffer = ByteBuffer.wrap(packet);

            if (i == 6) {
                rootController.addLabelTextSpace(rb.getString("sClient1"));
                break;
            }

            SocketAddress add = channel.receive(buffer);
            if (add != null) {
                rootController.addLabelTextSpace(rb.getString("sClient2") + " " + ++count + " " + rb.getString("sClient3"));
                isReceived = true;
                packetList.add(packet);
                continue;
            }
            Thread.currentThread().sleep(500);
            if(i % 2 == 0) {
                rootController.addLabelTextSpace(""+(i/2+1));
            }
        }



        for(int i = 0; i < packetList.size(); ++i) {
            messageByServer = (Message)Converter.convertFromBytes(packetList.get(i));
        }

        if (!isReceived)
            messageByServer.setAnswerServer("Server is not responding");

        return messageByServer;
    }

    public static byte[] getAnswerByte(byte[] buffer) {
        byte[] b;
        if (buffer == null) return null;
        int count = 0;
        for (int i = 0; i < buffer.length; ++i) {
            if (buffer[i] == 0) {
                count = i;
                break;
            }
        }
        b = new byte[count];
        for (int i = 0; i < count; ++i)
            b[i] = buffer[i];
        return b;
    }
}
