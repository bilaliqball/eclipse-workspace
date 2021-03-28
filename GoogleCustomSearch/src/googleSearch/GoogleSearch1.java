package googleSearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleSearch1 {

public static void main(String[] args) throws IOException {
String cx = "014723624719242706501:ky6zn2teax4";
String cx1="013036536707430787589:_pqjad5hr1a";
String apiKey = "AIzaSyBFnKBQPESdi2sP1twKp59-3mBscTVw99k";
URL url = new URL("https://www.googleapis.com/customsearch/v1?key="+apiKey+"&cx="+cx1+"&q=flowers&alt=json");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");
conn.setRequestProperty("Accept", "application/json");
BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
String output;
System.out.println("Output from Server .... \n");
while ((output = br.readLine()) != null) {System.out.println(output);}
conn.disconnect();}
}
