package ReadGmail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;

public class EmailOperations {

	public EmailOperations() {}
public static void main(String args[]) {
	
EmailOperations mr=new EmailOperations();
//mr.displayEmail();
//mr.searchEmail();
//mr.deleteEmail();
//
//mr.getMail();
//mr.searchMail();
//mr.deleteMail();

mr.getUnReadMail();
//mr.getReadMail();

    }




public void displayEmail() {	
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
for (int i =0; i < messages.length; i++) {
String subject=messages[i].getSubject();
String date=messages[i].getReceivedDate().toString();
String content=messages[i].getContent().toString();
String des=messages[i].getDescription();
System.out.println(date+" "+ subject);}
inbox.close(true);
store.close();} 
catch (Exception e) {e.printStackTrace();}}


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
if (message.getSubject().contains("account")) {return true;}} catch (MessagingException ex) {ex.printStackTrace();}
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
System.out.println("Found message #" + i + ": " + subject);}
Message mes=foundMessages[0];
mes.setFlag(Flags.Flag.DELETED, true);
System.out.println("Deleted");
 folderInbox.close(true);
store.close();} 
catch (NoSuchProviderException ex) {System.out.println("No provider.");ex.printStackTrace();} 
catch (MessagingException ex) {System.out.println("Could not connect to the message store."); ex.printStackTrace();}
}


public void getMail() {	
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
for (int i = messageCount-1; i < messages.length; i--) {
String subject=messages[i].getSubject();
String date=messages[i].getReceivedDate().toString();
String content=messages[i].getContent().toString();
String des=messages[i].getDescription();
System.out.println(date+" "+ subject);}
inbox.close(true);
store.close();} 
catch (Exception e) {e.printStackTrace();}}


public void isMatch(Message message, String keyword) throws MessagingException, IOException {
String subject="";
subject=message.getSubject().toString();
String date=message.getReceivedDate().toString();
String content=message.getContent().toString();
if (subject.contains(keyword)) {System.out.println(date+" "+ subject);}
else {}}


public void searchCondition(Message message, String keyword) throws MessagingException, IOException {
String subject="";
subject=message.getSubject().toString();
if (subject.contains(keyword)) {
System.out.println("---------------------------------");
System.out.println("Subject: " + message.getSubject());
System.out.println("From: " + message.getFrom()[0]);}}


public void searchMail() {	
long startTime=System.currentTimeMillis();
String host = "imap.gmail.com";
String username = "bilaliqbal038";
String password = "bitf11m038";
String keyword="account";
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
for (int i = 0; i < messages.length; i++) {
Message mes=messages[i];
searchCondition(mes,keyword);}

inbox.close(true);
store.close();} 
catch (Exception e) {e.printStackTrace();}
long endTime=System.currentTimeMillis();
System.out.println("\ntotal time taken="+(endTime-startTime)+" millisecs");}



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
catch (Exception e) {e.printStackTrace();}}


public void getUnReadMail() {
long startTime=System.currentTimeMillis();
String host = "imap.gmail.com";
String username = "bilaliqbal038";
String password = "bitf11m038";
try {
Properties props = new Properties();
props.setProperty("mail.imap.ssl.enable", "true");
Session session = Session.getInstance(props);
Store store = session.getStore("imap");
store.connect(host, username, password);

//Folder inbox = store.getFolder("inbox");
Folder inbox = store.getFolder("[Gmail]/Trash");

inbox.open(Folder.READ_WRITE);
int messageCount = inbox.getMessageCount();
System.out.println("Total Messages:- " + messageCount);
System.out.println("Total messaage: " + inbox.getMessageCount());
System.out.println("Unread messaage: " + inbox.getUnreadMessageCount());
System.out.println("Deleted messaage: " + inbox.getDeletedMessageCount());

System.out.println("---------------------------------");
Folder[] folderList = store.getFolder("[Gmail]").list();
for (int i = 0; i < folderList.length; i++) {System.out.println(folderList[i].getFullName());}


//Message message[]=inbox.getMessages();
//Message m=message[messageCount-1];
//System.out.println("---------------------------------");
//System.out.println("Subject: " + m.getSubject());
//System.out.println("From: " + m.getFrom()[0]);
//System.out.println("Description: " + m.getDescription());
//System.out.println("message no.: " + m.getMessageNumber());
//System.out.println("Size: " + m.getSize());
//System.out.println("Content: " + m.getContent().toString());
//System.out.println("Folder: " + m.getFolder());
//System.out.println("Recieved: " + m.getReceivedDate());
//Flags seen = new Flags(Flags.Flag.SEEN);
//Flags recent=new Flags(Flags.Flag.RECENT);
//Flags draft=new Flags(Flags.Flag.DRAFT);
//Flags user=new Flags(Flags.Flag.USER);
//FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
//FlagTerm recentFlagTerm=new FlagTerm(recent,false);
//FlagTerm draftFlagTerm=new FlagTerm(draft,true);
//FlagTerm userFlagTerm=new FlagTerm(user,false);




inbox.close(true);
store.close();} 
catch (Exception e) {e.printStackTrace();}
long endTime=System.currentTimeMillis();
System.out.println("\ntotal time taken="+(endTime-startTime)+" millisecs");
}




public void getReadMail() {
long startTime=System.currentTimeMillis();
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
System.out.println("  New Messages:- "+inbox.getNewMessageCount());
//Message[] messages = inbox.getMessages();//get all messsages
//Message messages[] = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));

Flags seen = new Flags(Flags.Flag.SEEN);
Flags recent=new Flags(Flags.Flag.RECENT);
Flags draft=new Flags(Flags.Flag.DRAFT);
Flags user=new Flags(Flags.Flag.USER);

FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
FlagTerm recentFlagTerm=new FlagTerm(recent,false);
FlagTerm draftFlagTerm=new FlagTerm(draft,true);
FlagTerm userFlagTerm=new FlagTerm(user,false);


SearchTerm searchCondition = new SearchTerm() {
@Override
public boolean match(Message message) {
try {
if (message.getSubject().contains("account")) {return true;}} catch (MessagingException ex) {ex.printStackTrace();}
return false;}};




Message messages[] = inbox.search(searchCondition);


//for (int i = 0; i < messages.length; i++) {
//String subject=messages[i].getSubject();
//String date=messages[i].getReceivedDate().toString();
//String content=messages[i].getContent().toString();
//String des=messages[i].getDescription();
//System.out.println(date+" "+ subject);}

for (int i = 0, n = messages.length; i < n; i++) {
Message message = messages[i];
System.out.println("---------------------------------");
System.out.println("Email Number " + (i + 1));
System.out.println("Subject: " + message.getSubject());
System.out.println("From: " + message.getFrom()[0]);
}

inbox.close(true);
store.close();} 
catch (Exception e) {e.printStackTrace();}
long endTime=System.currentTimeMillis();
System.out.println("\ntotal time taken="+(endTime-startTime)+" millisecs");
}


}


