import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class OutlookEmail {
public static void main(String[] args) {
SendEmail();
}


public static void SendEmail() {
long start=System.currentTimeMillis();
final String username = "bilal.iqbal@curexa.com.pk";
final String password = "bil@1234";
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.host", "outlook.office365.com");
props.put("mail.smtp.port", "587");
Session session = Session.getInstance(props,new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(username, password);}});

try {
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress(username));
//message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("beelaliqball@gmail.com"));
//message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("bilaliqbal038@gmail.com"));
message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("bilal.iqbal@curexa.com.pk"));
//message.addRecipients(Message.RecipientType.TO,InternetAddress.parse("bilal.iqbal@pucit.edu.pk"));
//message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("bilal.iqbal@curexa.com.pk"));
message.setSubject("Testing Subject");


BodyPart messageBodyPart = new MimeBodyPart();
messageBodyPart.setText("Please find the attachment");
Multipart multipart = new MimeMultipart();
multipart.addBodyPart(messageBodyPart);
messageBodyPart = new MimeBodyPart();
String filename = "hello.txt";


DataSource source = new FileDataSource(filename);
messageBodyPart.setDataHandler(new DataHandler(source));
messageBodyPart.setFileName(filename);
multipart.addBodyPart(messageBodyPart);
message.setContent(multipart );
Transport.send(message);
System.out.println("Email Sent");
} catch (MessagingException e) {throw new RuntimeException(e);}
long end=System.currentTimeMillis();
long diff=(end-start)/1000; System.out.println("execution time: "+diff +" sec");}

}