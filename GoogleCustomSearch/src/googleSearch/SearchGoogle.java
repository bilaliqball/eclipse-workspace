package googleSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchGoogle{

// pattern for extracting the link such as www.codeforeach.com/java/ ( domainname + path )
private static final Pattern p = Pattern.compile("([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}(/[^&]*)*");

public static void main(String[] args) throws IOException {
String searchQuery = "java tutorials";
List<String> links = searchGoogle(searchQuery);
for (String link : links) {System.out.println(link);}}

public static List<String> searchGoogle(String searchQuery) throws IOException {
List<String> result = new ArrayList<String>();
String request = "https://www.google.com/search?q=" + searchQuery + "&num=20";

Document doc = Jsoup.connect(request)
.userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").get();
// get the required content from response . Here ( h3 a ) is the selector pattern for selecting all heading links
Elements links = doc.select("h3 a[href]");

for (Element link : links) {
String hrefValue = link.attr("href");
if (hrefValue.startsWith("/url?q="))
result.add(extractLink(hrefValue));}
return result;}

// extract required link from href value
private static String extractLink(String href) {
String result = null;
Matcher m = p.matcher(href);
if (m.find()) {result = m.group();}
return result;}

}
