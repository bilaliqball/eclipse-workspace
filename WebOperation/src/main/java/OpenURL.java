import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;



public class OpenURL {
	public static void main(String args[]) throws URISyntaxException {openURL();}

	public String APIKEY="AIzaSyBS922h0_4pZZsg3wBfdvKUx9cOt4ArAEI";
	public static void openURL() throws URISyntaxException {
		try {
		//String url="https://www.google.com/";
		//String url="https://accounts.google.com/";//
		String url="https://mail.google.com/mail/u/0/#inbox";
		if (Desktop.isDesktopSupported()) {
		URI uri=new URI(url);Desktop.getDesktop().browse(uri);}} 
		catch (IOException ioe) {ioe.printStackTrace();}}
}
