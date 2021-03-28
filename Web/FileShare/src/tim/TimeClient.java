package tim;
import java.net.*;
import java.io.*;
 
/**
 * This program demonstrates a simple TCP/IP socket client.
 *
 * @author www.codejava.net
 */
public class TimeClient {
 
    public static void main(String[] args) {
       
 
        String hostname = "192.168.22.106";
  
        int port = Integer.parseInt("8383");
 
        try (Socket socket = new Socket(hostname, port)) {
 
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            String time = reader.readLine();
 
            System.out.println(time);
 
 
        } 
        catch (UnknownHostException ex) {System.out.println("Server not found: " + ex.getMessage());} 
        catch (IOException ex) {System.out.println("I/O error: " + ex.getMessage());}
    }
}