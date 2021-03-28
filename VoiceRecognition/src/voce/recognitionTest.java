package voce;

public class recognitionTest{
public static void main(String[] argv){
voce.SpeechInterface.init("C:\\Users\\bilal.iqbal\\eclipse-workspace2\\VoiceRecognition\\", false, true, "grammar", "grammars");
System.out.println("This is a speech recognition test. Speak digits from 0-9 into the microphone.");

boolean quit = false;
while (!quit){
try{Thread.sleep(200);}catch (InterruptedException e){}
while (voce.SpeechInterface.getRecognizerQueueSize() > 0){
System.out.println("Que Size "+ voce.SpeechInterface.getRecognizerQueueSize());
String s = voce.SpeechInterface.popRecognizedString();
if (-1 != s.indexOf("quit")){quit = true;}
System.out.println("You say: " + s);}}
voce.SpeechInterface.destroy();
System.exit(0);}


}

