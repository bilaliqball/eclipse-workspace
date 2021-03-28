package googleSearch;

import java.util.ArrayList;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;

public class CustomSearch {
private static final int HTTP_REQUEST_TIMEOUT = 3 * 600000;
private static final String GOOGLE_API_KEY="AIzaSyCDE-vFJpbn5iIR0d5KNsiP2joT1-m7soE";
private static final String SEARCH_ENGINE_ID="013036536707430787589:_pqjad5hr1a";
private static final String QUERY="hello";
public static void main(String args[]) {
//ArrayList<Result> r=new ArrayList<Result>();
getList();}

public static ArrayList<Result> search(String keyword){
Customsearch customsearch= null;
try {
customsearch = new Customsearch(new NetHttpTransport(),new JacksonFactory(), new HttpRequestInitializer() {
public void initialize(HttpRequest httpRequest) {
try {
httpRequest.setConnectTimeout(HTTP_REQUEST_TIMEOUT);
httpRequest.setReadTimeout(HTTP_REQUEST_TIMEOUT);} 
catch (Exception ex) {ex.printStackTrace();}}});
} catch (Exception e) {e.printStackTrace();}


ArrayList<Result> resultList=null;
try {
Customsearch.Cse.List list=customsearch.cse().list(keyword);
list.setKey(GOOGLE_API_KEY);
list.setCx(SEARCH_ENGINE_ID);
Search results=list.execute();
resultList=(ArrayList<Result>) results.getItems();}
catch (  Exception e) {e.printStackTrace();}
return resultList;}



public static  void getList() {
ArrayList<Result> results = new ArrayList<>();
try {
results = search(QUERY);} 
catch (Exception e) {e.printStackTrace();}
for(Result result : results){
System.out.println(result.getDisplayLink());
//System.out.println(result.getTitle());
// System.out.println(result.getHtmlSnippet());
//System.out.println(result.toString());//All attributes:
}
}
}
