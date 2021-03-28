package googleSearch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleSearch {
public static void main(String[] args) throws Exception {
String key="AIzaSyCDE-vFJpbn5iIR0d5KNsiP2joT1-m7soE";
String qry="lahore+weather";
URL url = new URL("https://www.googleapis.com/customsearch/v1?key="+key+ "&cx=013036536707430787589:_pqjad5hr1a&q="+ qry + "&alt=json");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");
conn.setRequestProperty("Accept", "application/json");
BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

String output;
System.out.println("Output from Server .... \n");
while ((output = br.readLine()) != null) {

if(output.contains("\"link\": \"")){ 
//System.out.println(output);
String link=output.substring(output.indexOf("\"link\": \"")+("\"link\": \"").length(), output.indexOf("\","));
System.out.println(link);}}
conn.disconnect();                              
}
}
