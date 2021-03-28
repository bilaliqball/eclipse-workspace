/**
 * Created by mario on 06/10/14.
 */
package server;
public class Init {
    public static void main(String[] args)
    {
//        System.out.println("Server's ON");
//        String port = args[0];
//        String dir = args[1];
//        String bufferSize = 8192+"";

    	   System.out.println("Server's ON");
         String port = "8383";
         String dir = "C:\\Users\\bilal.iqbal\\Pictures\\input\\";
         String bufferSize = 8192+"";
        Srv srv = new Srv(port,dir, bufferSize);
        srv.Execute();
    }
}
