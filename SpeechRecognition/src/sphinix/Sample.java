package sphinix;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.result.WordResult;

public class Sample {
public static void main(String args[]) throws Exception {
//liveSpeech();
streamSpeech();
}

public static void streamSpeech() throws Exception {
Configuration configuration = new Configuration();
configuration.setAcousticModelPath("resources/model");
configuration.setDictionaryPath("resources/dictionary/dictionary.dict");
configuration.setLanguageModelPath("resources/language/en-us.lm.bin");
configuration.setGrammarPath("grammar");
configuration.setGrammarName("digits");
configuration.setUseGrammar(true);
StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
String speechFilePath;ArrayList<String> WORDS=new ArrayList<String>();
//speechFilePath= "voices/story/aud (2).wav";
speechFilePath="voices/sample/sample1.wav";
recognizer.startRecognition(new FileInputStream(speechFilePath));
SpeechResult result;
while ((result = recognizer.getResult()) != null) {
//System.out.format("Hypothesis: %s\n", result.getHypothesis());
WORDS.add(result.getHypothesis());}
recognizer.stopRecognition();

System.out.println("Speech Recognize is: ");
for(int i=0;i<WORDS.size();i++) {System.out.print(WORDS.get(i)+ " ");}
}

public static void liveSpeech(){
	ArrayList<String> WORDS=new ArrayList<String>();
try{
Logger logger = LogManager.getLogManager().getLogger("");
Handler[] handlers = logger.getHandlers();
logger.setLevel(Level.OFF);
Configuration config = new Configuration();
config.setAcousticModelPath("resources/model");
config.setDictionaryPath("resources/dictionary/dictionary.dict");
config.setLanguageModelPath("resources/language/en-us.lm.bin");
//config.setGrammarPath("grammar");
//config.setGrammarName("dialoge");
//config.setUseGrammar(true);
LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(config);
recognizer.startRecognition(true);
while (true) {
SpeechResult result = recognizer.getResult();
for (WordResult r : result.getWords()) {
System.out.format("Hypothesis: %s\n", result.getHypothesis());
System.out.println(r);WORDS.add(result.getHypothesis());}
}}
catch(Exception e){System.out.println("Unable to provide speech recognition: " + e);
System.out.println("Speech Recognize is: ");
for(int i=0;i<WORDS.size();i++) {System.out.print(WORDS.get(i)+ " ");}
System.exit(1);}}



 
}
