package sphinix;
import javax.speech.*;
import javax.speech.recognition.*;
import java.io.*;
import java.net.URL;
import java.util.Locale;
@SuppressWarnings("unused")

public class HelloWorld extends ResultAdapter {
static Recognizer rec;

public void resultAccepted(ResultEvent e){
Result r = (Result)(e.getSource());
ResultToken tokens[] = r.getBestTokens();
for (int i = 0; i < tokens.length; i++)
System.out.print(tokens[i].getSpokenText() + " ");
System.out.println();


try {rec.deallocate();} 
catch (EngineException e1) {e1.printStackTrace();} 
catch (EngineStateError e1) {e1.printStackTrace();}
System.exit(0);}
	

public static void main(String args[]) {
try{rec = Central.createRecognizer(new EngineModeDesc(Locale.ENGLISH));
try{rec.allocate();}
catch(Exception e){e.printStackTrace();}


//URL add = new URL("/grammars.gram");
File file = new File("grammars.gram");
FileReader reader = new FileReader(file.toString());
RuleGrammar gram = rec.loadJSGF(reader);
gram.setEnabled(true);


rec.addResultListener(new HelloWorld());
rec.commitChanges();
rec.requestFocus();
rec.resume();} 

catch (Exception e) {e.printStackTrace();}}
}