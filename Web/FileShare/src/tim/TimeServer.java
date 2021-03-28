package tim;
import java.io.*;
import java.net.*;
import java.util.Date;
 
/**
 * This program demonstrates a simple TCP/IP socket server.
 *
 * @author www.codejava.net
 */
public class TimeServer {
 
    public static void main(String[] args) throws IOException {
      
 String add="192.168.22.106";
 
        int port = Integer.parseInt("8383");
        InetAddress addr = InetAddress.getByName(add);
        SocketAddress sockaddr = new InetSocketAddress(addr, port);
        ServerSocket serverSocket=null;
        try {
        	serverSocket = new ServerSocket(port);
        	//serverSocket.bind(sockaddr);
            System.out.println("Server is listening on  " +add+":"+ port);
 
            while (true) {
                Socket socket = serverSocket.accept();
 
                System.out.println("New client connected");
 
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
 
                writer.println(new Date().toString());
            }
 

        } catch (IOException ex) {
         
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally {
        	   serverSocket.close();
          }
    }
}