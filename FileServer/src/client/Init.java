/**
 * Created by mario on 06/10/14.
 */
package client;
import client.Cli;;
public class Init {
    public static void main(String[] args)
    {
//        String ip = args[0];
//        String port = args[1];
//        String destinationFolder = args[2];
//        String bufferSize=8192+"";
    	
    	   String ip = "192.168.22.106";
           String port = "8383";
           String destinationFolder = "C:\\Users\\bilal.iqbal\\Pictures\\output\\";
           String bufferSize=8192+"";

        Cli cli = new Cli(ip,port,bufferSize,destinationFolder);
        cli.Execute();
    }
}
