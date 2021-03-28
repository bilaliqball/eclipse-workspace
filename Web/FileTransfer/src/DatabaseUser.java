import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class DatabaseUser {

	public static String server="http://localhost:52854/";
	
public static void main(String args[]) throws IOException, InterruptedException {
checkExpiration("bee");}
	
	


	
public static void RegisterUser() throws IOException, InterruptedException {
String username="bee";
String password = "pass";
String email = "bilal@gmail.com";
String fName = "fName";
String lName = "lName";
okhttp3.OkHttpClient client=null;
okhttp3.RequestBody requestBody;
okhttp3.Request request ;
okhttp3.Response response;;


client = new OkHttpClient();

requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
.addFormDataPart("userName", username)
.addFormDataPart("email", email)
.addFormDataPart("password", password)
.addFormDataPart("fName", fName)
.addFormDataPart("lName", lName)
.build();

request = new Request.Builder().url(server+"/User/register").post(requestBody).build();
response = client.newCall(request).execute();

if (!response.isSuccessful()) {
TimeUnit.SECONDS.sleep(1);
System.out.println(" "+ response);}


String res=response.body().string();
System.out.println(res);
JSONObject obj = new JSONObject(res);

System.out.println(obj.getString("status"));
response.close();}
	

public static void updateUser() throws IOException, InterruptedException {
String username="bee";
String password = "pass";
String email = "bilal@gmail.com";
String fName = "fName";
String lName = "lName";
okhttp3.OkHttpClient client=null;
okhttp3.RequestBody requestBody;
okhttp3.Request request ;
okhttp3.Response response;;


client = new OkHttpClient();

requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
.addFormDataPart("userName", username)
.addFormDataPart("email", email)
.addFormDataPart("password", password)
.addFormDataPart("fName", fName)
.addFormDataPart("lName", lName)
.build();

request = new Request.Builder().url(server+"/User/update").post(requestBody).build();
response = client.newCall(request).execute();

if (!response.isSuccessful()) {
TimeUnit.SECONDS.sleep(1);
System.out.println(" "+ response);}

System.out.println(response.body().string());;
response.close();}

public static void deleteUser(String username) throws IOException, InterruptedException {
okhttp3.OkHttpClient client=null;
okhttp3.RequestBody requestBody;
okhttp3.Request request ;
okhttp3.Response response;;
client = new OkHttpClient();
requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
.addFormDataPart("userName", username)
.build();
request = new Request.Builder().url(server+"/User/delete").post(requestBody).build();
response = client.newCall(request).execute();

if (!response.isSuccessful()) {
TimeUnit.SECONDS.sleep(1);
System.out.println(" "+ response);}

System.out.println(response.body().string());;
response.close();}



public static void checkUser(String username) throws IOException {
OkHttpClient client = new OkHttpClient();
okhttp3.Response response =null;
try {
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("userName", username)
.build();
String uri=server+"/User/checkUsername";
Request request = new Request.Builder()
.url(uri)
.post(requestBody)
.build();
response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException(" " + response);}

System.out.println(response.body().string());
response.close();} 
catch (Exception ex) {}}


public static void checkExpiration(String username) throws IOException {
OkHttpClient client = new OkHttpClient();
okhttp3.Response response =null;
try {
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("userName", username)
.build();
String uri=server+"/User/registrationExpired";
Request request = new Request.Builder()
.url(uri)
.post(requestBody)
.build();
response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException(" " + response);}

String res=response.body().string();
JSONObject obj = new JSONObject(res);
String status=obj.getString("status");
System.out.println(status);response.close();} 
catch (Exception ex) {}}


public static void checkRegistration(String username) throws IOException {
OkHttpClient client = new OkHttpClient();
okhttp3.Response response =null;
try {
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("userName", username)
.build();
String uri=server+"/User/checkRegistration";
Request request = new Request.Builder()
.url(uri)
.post(requestBody)
.build();
response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException(" " + response);}

String res=response.body().string();//System.out.println(res);



JSONObject obj = new JSONObject(res);
String joindate=obj.getString("registrationDate");
System.out.println(joindate);


String datepattern="M/d/YYYY hh:mm:ss aa";
joindate="5/22/2019 12:52:38 PM";




SimpleDateFormat dateFormat = new SimpleDateFormat(datepattern,Locale.US);



Date newDate = dateFormat.parse(joindate);


System.out.println(newDate);
System.out.println(dateFormat.parse(joindate));



//String formattedDate = dateFormat.format(new Date()).toString();
//System.out.println(formattedDate);


response.close();} 
catch (Exception ex) {}
}


public static void checkEmail(String email) throws IOException {
OkHttpClient client = new OkHttpClient();
okhttp3.Response response =null;
try {
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("email", email)
.build();
String uri=server+"/User/checkEmail";
Request request = new Request.Builder()
.url(uri)
.post(requestBody)
.build();
response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException(" " + response);}
System.out.println(response.body().string());


response.close();} 
catch (Exception ex) {}
}


public static void ShowAll() throws IOException {
String dir="";
OkHttpClient client = new OkHttpClient();
okhttp3.Response response =null;
try {
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("directoryName", dir)
.build();
String uri=server+"/User/showAll";
Request request = new Request.Builder()
.url(uri)
.post(requestBody)
.build();
response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException(" " + response);}

System.out.println(response.body().string());
response.close();} 
catch (Exception ex) {}
}
	

public static void parseJsonString(String str) {
	str="{\"isTaken\":\"True, email is taken \"}";

	JSONObject obj = new JSONObject(str);

	System.out.println(obj.getString("isTaken"));
}

}
