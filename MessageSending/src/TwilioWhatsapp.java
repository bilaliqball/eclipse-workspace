
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class TwilioWhatsapp {
public static final String ACCOUNT_SID = "AC7c51e5abd44eeee8d841d2b86799ad4a";
public static final String AUTH_TOKEN = "6d1433061278ac5822f8d2bf83b306e3";

public static void main(String[] args) {
Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
String mes="Hello there bee!";
Message message = Message.creator(
new com.twilio.type.PhoneNumber("whatsapp:+923344442033"),
new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
mes).create();

System.out.println(message.getSid());
}

}
