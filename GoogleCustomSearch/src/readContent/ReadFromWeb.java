package readContent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadFromWeb {
public static void readFromWeb(String webURL) throws IOException {

URL url = new URL(webURL);
InputStream is =  url.openStream();
try( BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
String line;
while ((line = br.readLine()) != null) {System.out.println(line);}}
catch (MalformedURLException e) {e.printStackTrace();throw new MalformedURLException("URL is malformed!!");}
catch (IOException e) {e.printStackTrace();throw new IOException();}}

public static void main(String[] args) throws IOException {
String url = "https://en.wikipedia.org/wiki/Hello_(Adele_song)";
readFromWeb(url);}

}