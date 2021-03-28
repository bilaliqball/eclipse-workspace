package ReadGmail;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;


public class EmailReader {

private static final String HOST = "pop.gmail.com";
private static final String TYPE = "pop3";
private static final String USER = "bilaliqbal038@gmail.com";
private static final String PASSWORD = "bitf11m038";
private static final String PORT = "995";

public static void getMails() {
long startTime=System.currentTimeMillis();
try {
Properties properties = new Properties();
properties.put("mail.pop3.host", HOST);
properties.put("mail.pop3.port", PORT);
properties.put("mail.pop3.starttls.enable", "true");
properties.put("mail.pop3.socketFactory.class" , "javax.net.ssl.SSLSocketFactory" );

//Session emailSession = Session.getDefaultInstance(properties);
Session emailSession = Session.getDefaultInstance(properties , new Authenticator() {
@Override
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(USER , PASSWORD);}});

Store store = emailSession.getStore(TYPE);
store.connect(HOST,USER, PASSWORD);

Folder emailFolder = store.getFolder("INBOX");
emailFolder.open(Folder.READ_ONLY);


//Message[] messages = emailFolder.getMessages();
//Message messages[] = emailFolder.search(new FlagTerm(new Flags(Flag.SEEN), false));

Flags seen = new Flags(Flags.Flag.SEEN);
FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
Message messages[] = emailFolder.search(unseenFlagTerm);

System.out.println("messages.length---" + messages.length);

for (int i = 0, n = messages.length; i < n; i++) {
Message message = messages[i];
System.out.println("---------------------------------");
System.out.println("Email Number " + (i + 1));
System.out.println("Subject: " + message.getSubject());
System.out.println("From: " + message.getFrom()[0]);
//System.out.println("Body: " + message.getContent().toString());
}


emailFolder.close(true);
store.close();} 
catch (NoSuchProviderException e) {e.printStackTrace();} 
catch (MessagingException e) {e.printStackTrace();} 
catch (Exception e) {e.printStackTrace();}

long endTime=System.currentTimeMillis();
System.out.println("\ntotal time taken="+(endTime-startTime)+" millisecs");}



public static void searchMails() {
long startTime=System.currentTimeMillis();
try {
Properties properties = new Properties();
properties.put("mail.pop3.host", HOST);
properties.put("mail.pop3.port", PORT);
properties.put("mail.pop3.starttls.enable", "true");
properties.put("mail.pop3.socketFactory.class" , "javax.net.ssl.SSLSocketFactory" );
Session emailSession = Session.getDefaultInstance(properties);
//Session emailSession = Session.getDefaultInstance(properties , new Authenticator() {
//@Override
//protected PasswordAuthentication getPasswordAuthentication() {
//return new PasswordAuthentication( USER , PASSWORD);}});


Store store = emailSession.getStore(TYPE);
store.connect(HOST, USER, PASSWORD);
Folder emailFolder = store.getFolder("INBOX");
emailFolder.open(Folder.READ_ONLY);

SearchTerm searchCondition = new SearchTerm() {
@Override
public boolean match(Message message) {
try {
if (message.getSubject().contains("account")) {return true;}} catch (MessagingException ex) {ex.printStackTrace();}
return false;}};
   
System.out.println("Searching");
Message[] foundMessages = emailFolder.search(searchCondition);
System.out.println("\n");

for (int i = 0, n = foundMessages.length; i < n; i++) {
Message message = foundMessages[i];
System.out.println("---------------------------------");
System.out.println("Email Number " + (i + 1));
System.out.println("Subject: " + message.getSubject());
System.out.println("From: " + message.getFrom()[0]);
System.out.println("Body: " + message.getContent().toString());
}
emailFolder.close(false);
store.close();} 
catch (NoSuchProviderException e) {e.printStackTrace();} 
catch (MessagingException e) {e.printStackTrace();} 
catch (Exception e) {e.printStackTrace();}
long endTime=System.currentTimeMillis();
System.out.println("\ntotal time taken="+(endTime-startTime)+" millisecs");}



public static void deleteMails() {
long startTime=System.currentTimeMillis();
try {
Properties properties = new Properties();
properties.put("mail.pop3.host", HOST);
properties.put("mail.pop3.port", PORT);
properties.put("mail.pop3.starttls.enable", "true");
properties.put("mail.pop3.socketFactory.class" , "javax.net.ssl.SSLSocketFactory" );
Session emailSession = Session.getDefaultInstance(properties);
//Session emailSession = Session.getDefaultInstance(properties , new Authenticator() {
//@Override
//protected PasswordAuthentication getPasswordAuthentication() {
//return new PasswordAuthentication( USER , PASSWORD);}});


Store store = emailSession.getStore(TYPE);
store.connect(HOST, USER, PASSWORD);
Folder emailFolder = store.getFolder("inbox");
emailFolder.open(Folder.READ_WRITE);

SearchTerm searchCondition = new SearchTerm() {
@Override
public boolean match(Message message) {
try {
if (message.getSubject().contains("sampleSearchExample")) {return true;}} catch (MessagingException ex) {ex.printStackTrace();}
return false;}};
   
System.out.println("Searching");
Message[] foundMessages = emailFolder.search(searchCondition);
System.out.println("\n");


foundMessages[0].setFlag(Flags.Flag.DELETED, true);
System.out.println("Deleted");
emailFolder.close(true);
store.close();}

catch (NoSuchProviderException e) {e.printStackTrace();} 
catch (MessagingException e) {e.printStackTrace();} 
catch (Exception e) {e.printStackTrace();}
long endTime=System.currentTimeMillis();
System.out.println("\ntotal time taken="+(endTime-startTime)+" millisecs");}




public static void delete() 
	   {
	 String pop3Host = "pop.gmail.com";// change accordingly
     String storeType = "pop3";
     String user = "bilaliqbal038@gmail.com";// change accordingly
     String password = "bitf11m038";// change accordingly
	      try 
	      {
 // get the session object
 Properties properties = new Properties();
 properties.put("mail.store.protocol", "pop3");
 properties.put("mail.pop3s.host", pop3Host);
 properties.put("mail.pop3s.port", "995");
 properties.put("mail.pop3.starttls.enable", "true");
 Session emailSession = Session.getDefaultInstance(properties);
 // emailSession.setDebug(true);

 // create the POP3 store object and connect with the pop server
 Store store = emailSession.getStore("pop3s");

 store.connect(pop3Host, user, password);

 // create the folder object and open it
 Folder emailFolder = store.getFolder("INBOX");
 emailFolder.open(Folder.READ_WRITE);

 BufferedReader reader = new BufferedReader(new InputStreamReader(
    System.in));
 // retrieve the messages from the folder in an array and print it
 Message[] messages = emailFolder.getMessages();
 System.out.println("messages.length---" + messages.length);
 
//	         for (int i = 214; i >= messages.length; i--) {
//	            Message message = messages[i];
//	            System.out.println("---------------------------------");
//	            System.out.println("Email Number " + (i + 1));
//	            System.out.println("Subject: " + message.getSubject());
//	            System.out.println("From: " + message.getFrom()[0]);
//
//	            String subject = message.getSubject();
//	            System.out.print("Do you want to delete this message [y/n] ? ");
//	            String ans = reader.readLine();
//	            if ("Y".equals(ans) || "y".equals(ans)) {
//		       // set the DELETE flag to true
//		       message.setFlag(Flags.Flag.DELETED, true);
//		       System.out.println("Marked DELETE for message: " + subject);
//	            } else if ("n".equals(ans)) {
//		       break;
//	            }
//	         }
     
     Message mess=messages[212];
     System.out.println(mess.getSubject());
   mess.setFlag(Flags.Flag.DELETED, true);
     emailFolder.close(true);
     store.close();

  } catch (NoSuchProviderException e) {
     e.printStackTrace();
  } catch (MessagingException e) {
     e.printStackTrace();
  }
	   }

public static void main(String[] args) {
	getMails();


;

}

}
