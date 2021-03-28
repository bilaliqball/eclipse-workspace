//package formFilling;
//import HTTPClient.HTTPConnection;
//import HTTPClient.HTTPResponse;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//import HTTPClient.CookieModule;
//public class SearchResult {
//	
//	public static void main(String args[]) {
//	 
//	String urlWithValues = "search?q="+ URLEncoder.encode("easy makro recorder");
//	String host = "64.233.167.99"; // = google.com. You can also use InetAddress.getByName("google.com") instead.
//	String port = "80";
//	HTTPConnection connection = new HTTPConnection(host, port);
//	HTTPResponse response = connection.Post(urlWithValues);
//	 
//	InputStream input = response.getInputStream();
//	BufferedReader dataInput = new BufferedReader(new InputStreamReader(input));
//	String searchResults = "";
//	while (true)
//	{
//	    String line = dataInput.readLine();
//	    if (line == null)  break;// end of response
//	    searchResults += line;
//	}
//	 
//	System.out.println("google found following results:" + searchResults); 
//}
