package ReadGmail;

import java.io.*;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class ReplyMail {

  public static void main(String args[]) throws Exception {

  Date date = null;
  Properties properties = System.getProperties();
  properties.setProperty("mail.smtp.host", "pop.gmail.com");

  Session session = Session.getDefaultInstance(properties);

  Store store = session.getStore("pop3");
  store.connect("pop.gmail.com", "bilaliqbal038@gmail.com", "bitf11m038");

  Folder folder = store.getFolder("inbox");
  if (!folder.exists()) {
  System.out.println("inbox not found");
  System.exit(0);
  }
  folder.open(Folder.READ_ONLY);

  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  Message[] message = folder.getMessages();
  if (message.length != 0) {
  System.out.println("no. From \t\tSubject \t\tDate");
  //for (int i = 0, n = message.length; i < n; i++) {
  date = message[folder.getMessageCount()-1].getSentDate();

  System.out.println(" " + (1) + ": " + message[folder.getMessageCount()-1].getFrom()[0] + "\t" +message[folder.getMessageCount()-1].getSubject() );
  System.out.print("Do you want to reply [y/n] : ");
  String ans = reader.readLine();
  if ("Y".equals(ans) || "y".equals(ans)) {

  // Create a reply message
  MimeMessage reply = (MimeMessage) message[folder.getMessageCount()-1].reply(false);

  // Set the from field
  reply.setFrom(message[folder.getMessageCount()-1].getFrom()[0]);

  // Create the reply content
  // Create the reply content, copying over the original if text
  MimeMessage orig = (MimeMessage) message[folder.getMessageCount()-1];
  StringBuffer buffer = new StringBuffer("Thanks\n\n");
  if (orig.isMimeType("text/plain")) {
  String content = (String) orig.getContent();
  StringReader contentReader = new StringReader(content);
  BufferedReader br = new BufferedReader(contentReader);
  String contentLine;
  while ((contentLine = br.readLine()) != null) {
  buffer.append("> ");
  buffer.append(contentLine);
  buffer.append("\r\n");
  }
  }
  // Set the content
  reply.setText(buffer.toString());

  // Send the message
  Transport.send(reply);

  } 
  //else if ("n".equals(ans)) {break;}
  //}

  } else {
  System.out.println("There is no msg....");
  }

  }
} 
