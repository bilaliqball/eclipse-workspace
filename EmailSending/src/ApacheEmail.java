import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class ApacheEmail {
public static void main(String args[]) throws Exception {
sendSimpleMail();
//sendAttachmentMail();
}

public static void sendSimpleMail() throws Exception {
Email email = new SimpleEmail();
email.setSmtpPort(587);
email.setAuthenticator(new DefaultAuthenticator("bilaliqbal038@gmail.com","bitf11m038"));
email.setDebug(false);
email.setHostName("smtp.gmail.com");
email.setFrom("bilaliqbal038@gmail.com");
email.setSubject("Hi");
email.setMsg("This is a test mail ... :-)");
email.addTo("beelaliqball@gmail.com");
email.setStartTLSEnabled(true);
email.send();
System.out.println("Mail sent!");
}

public static void sendAttachmentMail() {
try {
EmailAttachment attachment = new EmailAttachment();
attachment.setPath("hello.txt");
attachment.setDisposition(EmailAttachment.ATTACHMENT);
attachment.setDescription("file");
attachment.setName("Map");

MultiPartEmail email = new MultiPartEmail();
email.setHostName("smtp.googlemail.com");
email.setSmtpPort(465);
email.setSSLOnConnect(true);
email.setAuthenticator(new DefaultAuthenticator("bilaliqbal038@gmail.com","bitf11m038"));
  
email.setHostName("smtp.gmail.com");
email.setFrom("bilaliqbal038@gmail.com");
email.addTo("beelaliqball@gmail.com");
email.setSubject("The Map");
email.setMsg("Here is the map you wanted");

email.attach(attachment);
email.send();
System.out.println("Sent!");} 
catch (Exception e) {e.printStackTrace();}}
}
