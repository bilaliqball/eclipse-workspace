package voce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class synthesisTest{
public static void main(String[] argv){
voce.SpeechInterface.init("C:\\Users\\bilal.iqbal\\eclipse-workspace2\\VoiceRecognition\\", true, false, "", "");
voce.SpeechInterface.synthesize("This is a speech synthesis test Type a message to hear it spoken aloud.");
voce.SpeechInterface.synthesize("'Hello World' 'good morning' 'hello mighty computer'");
voce.SpeechInterface.synthesize("'please' 'What is your Name' 'Open Fire fox' 'Open notepad' 'Open grammar' 'Nice to meet you'");
voce.SpeechInterface.synthesize("'thanks' 'thank you' 'thank you very much'");
voce.SpeechInterface.synthesize("'oh' 'zero' 'one' 'two' 'three' 'four' 'five' 'six' 'seven' 'eight' 'nine' 'ten'");

//System.out.println("This is a speech synthesis test Type a message to hear it spoken aloud.");
//System.out.println("Type 's' + 'enter' to make the synthesizer stop speaking.  Type 'q' + 'enter' to quit.");

BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

try{
String s = "";
while (!s.equals("q")){
s = console.readLine();
if (s.equals("s")){voce.SpeechInterface.stopSynthesizing();}
else{voce.SpeechInterface.synthesize(s);}}}
catch (java.io.IOException ioe){System.out.println( "IO error:" + ioe );}
voce.SpeechInterface.destroy();
System.exit(0);}
}

