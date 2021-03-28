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

public class SendMailSSL {
	public static void main(String[] args) {
		Properties props = new Properties();
		//props.put("mail.smtp.host", "127.0.0.1");
		props.put("mail.smtp.host", "smtp.gmail.com");
		
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication("bilaliqbal038@gmail.com","bitf11m038");}});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("bilaliqbal038@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("beelaliqball@gmail.com"));
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("bilal.iqbal@curexa.com.pk"));
			message.setSubject("Testing Subject SLS");
			//message.setText("Dear Mail Crawler SSL," +"\n\n No spam to my email, please!");
			
			
			
	         // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText("This is message body");
	         
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "hello.txt";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart );
			
			

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}