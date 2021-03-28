

import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.search.SearchTerm;

public class MailReader {

	public MailReader() {}
public static void main(String args[]) {
	
MailReader mr=new MailReader();
//mr.readMails();
//mr.searchEmail();
	

//mr.getSearchMails();

//mr.searchEmail();
mr.deleteMail();
    }







public void searchEmail() {
String host = "imap.gmail.com";
String port = "993";
String userName = "bilaliqbal038";
String password = "bitf11m038";
String keyword = "account";
Properties properties = new Properties();
properties.put("mail.imap.host", host);
properties.put("mail.imap.port", port);
properties.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
properties.setProperty("mail.imap.socketFactory.fallback", "false");
properties.setProperty("mail.imap.socketFactory.port",String.valueOf(port));
Session session = Session.getDefaultInstance(properties);
 try {
Store store = session.getStore("imap");
store.connect(userName, password);
Folder folderInbox = store.getFolder("inbox");
folderInbox.open(Folder.READ_WRITE);
 SearchTerm searchCondition = new SearchTerm() {
@Override
public boolean match(Message message) {
try {
if (message.getSubject().contains("sampleSerchExample")) {return true;}} catch (MessagingException ex) {ex.printStackTrace();}
return false;}};
 
System.out.println("Searching");
Message[] foundMessages = folderInbox.search(searchCondition);
System.out.println("\n");
for (int i = 0; i < foundMessages.length; i++) {
Message message = foundMessages[i];
String subject = message.getSubject();
System.out.println("Found message #" + i + ": " + subject);}
 folderInbox.close(false);
store.close();} 
catch (NoSuchProviderException ex) {System.out.println("No provider.");ex.printStackTrace();} 
catch (MessagingException ex) {System.out.println("Could not connect to the message store."); ex.printStackTrace();}
}

	


public void deleteEmail() {
String host = "imap.gmail.com";
String port = "993";
String userName = "bilaliqbal038";
String password = "bitf11m038";
String keyword = "account";
Properties properties = new Properties();

properties.put("mail.imap.host", host);
properties.put("mail.imap.port", port);
properties.setProperty("mail.imap.socketFactory.class","javax.net.ssl.SSLSocketFactory");
properties.setProperty("mail.imap.socketFactory.fallback", "false");
properties.setProperty("mail.imap.socketFactory.port",String.valueOf(port));
 Session session = Session.getDefaultInstance(properties);
 try {
Store store = session.getStore("imap");
store.connect(userName, password);
 Folder folderInbox = store.getFolder("inbox");
folderInbox.open(Folder.READ_WRITE);
 SearchTerm searchCondition = new SearchTerm() {
@Override
public boolean match(Message message) {
try {
if (message.getSubject().contains("sampleSearchExample")) {return true;}} catch (MessagingException ex) {ex.printStackTrace();}
return false;}};
 System.out.println("Searching");
Message[] foundMessages = folderInbox.search(searchCondition);
System.out.println("\n");
for (int i = 0; i < foundMessages.length; i++) {
Message message = foundMessages[i];
String subject = message.getSubject();
System.out.println("Found message #" + i + ": " + subject);
}
Message mes=foundMessages[0];
mes.setFlag(Flags.Flag.DELETED, true);
System.out.println("Deleted");
 folderInbox.close(true);
store.close();} 
catch (NoSuchProviderException ex) {System.out.println("No provider.");ex.printStackTrace();} 
catch (MessagingException ex) {System.out.println("Could not connect to the message store."); ex.printStackTrace();}
}





public void isMatch(Message message, String keyword) throws MessagingException, IOException {
String subject="";
subject=message.getSubject().toString();
String date=message.getReceivedDate().toString();
String content=message.getContent().toString();
if (subject.contains(keyword)) {System.out.println(date+" "+ subject);}
else {}}

public void getSearchMails() {	
String host = "imap.gmail.com";
String username = "bilaliqbal038";
String password = "bitf11m038";
Properties props = new Properties();
Store store;
Folder inbox ;
try {
props.setProperty("mail.imap.ssl.enable", "true");
Session session = Session.getInstance(props);
store = session.getStore("imap");
store.connect(host, username, password);

inbox = store.getFolder("inbox");
inbox.open(Folder.READ_ONLY);

int messageCount = inbox.getMessageCount();
System.out.println("Total Messages Found:- " + messageCount);
Message[] messages = inbox.getMessages();

System.out.println("------------------------------");
for (int i = 0; i < messages.length/2; i++) {
Message mes=messages[i];
isMatch(mes,"account");}

inbox.close(true);
store.close();} 
catch (Exception e) {e.printStackTrace();}
}


public void getAllMails() {	
String host = "imap.gmail.com";
String username = "bilaliqbal038";
String password = "bitf11m038";
try {
Properties props = new Properties();
props.setProperty("mail.imap.ssl.enable", "true");
Session session = Session.getInstance(props);
Store store = session.getStore("imap");
store.connect(host, username, password);

Folder inbox = store.getFolder("inbox");
inbox.open(Folder.READ_ONLY);
int messageCount = inbox.getMessageCount();
System.out.println("Total Messages:- " + messageCount);
Message[] messages = inbox.getMessages();

System.out.println("------------------------------");
for (int i = 0; i < 10; i++) {
String subject=messages[i].getSubject();
String date=messages[i].getReceivedDate().toString();
String content=messages[i].getContent().toString();
//String sender=messages[i].getFrom().toString();
String des=messages[i].getDescription();
System.out.println(date+" "+ subject+" "+content);}
inbox.close(true);
store.close();} 
catch (Exception e) {e.printStackTrace();}
}



public void deleteMail() {	
String host = "imap.gmail.com";
String username = "bilaliqbal038";
String password = "bitf11m038";
try {
Properties props = new Properties();
props.setProperty("mail.imap.ssl.enable", "true");
Session session = Session.getInstance(props);
Store store = session.getStore("imap");
store.connect(host, username, password);

Folder inbox = store.getFolder("inbox");
inbox.open(Folder.READ_WRITE);
int messageCount = inbox.getMessageCount();
System.out.println("Total Messages:- " + messageCount);
Message[] messages = inbox.getMessages();

System.out.println(messages[186].getSubject());
messages[186].setFlag(Flags.Flag.DELETED, true);
System.out.println("Deleted");


inbox.close(true);
store.close();} 
catch (Exception e) {e.printStackTrace();}
}

}


