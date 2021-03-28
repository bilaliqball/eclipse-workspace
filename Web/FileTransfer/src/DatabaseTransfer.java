import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class DatabaseTransfer {

	public static String server="http://localhost:49755/";
	
	public static void main(String args[]) throws IOException, InterruptedException {
		
		AddTransferInfo("linkId");
	}
	
public static void AddTransferInfo(String linkid) throws IOException, InterruptedException {
String senderId ="sender";
String receiverId = "reciever";
String linkId= linkid;

String filelink = "filelink";
String originalName="originalName";
String isDownloaded = "isDownloaded";
String status = "status";

okhttp3.OkHttpClient client=null;
okhttp3.RequestBody requestBody;
okhttp3.Request request ;
okhttp3.Response response;;
client = new OkHttpClient();

requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
.addFormDataPart("senderId", senderId)
.addFormDataPart("receiverId", receiverId)
.addFormDataPart("linkId", linkId)
.addFormDataPart("filelink", filelink)
.addFormDataPart("originalName", originalName)
.addFormDataPart("isDownloaded", isDownloaded)
.addFormDataPart("status", status)
.build();
request = new Request.Builder().url(server+"/Login/addTransferInfo").post(requestBody).build();
response = client.newCall(request).execute();
if (!response.isSuccessful()) {
TimeUnit.SECONDS.sleep(1);
System.out.println(" "+ response);}
String res=response.body().string();
System.out.println(res);
JSONObject obj = new JSONObject(res);
System.out.println(obj.getString("status"));
response.close();}


public static void checkStatus(String linkId) throws IOException {
OkHttpClient client = new OkHttpClient();
okhttp3.Response response =null;
try {
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("linkId", linkId)
.build();
String uri=server+"/Login/checkStatus";
Request request = new Request.Builder()
.url(uri)
.post(requestBody)
.build();
response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException(" " + response);}

System.out.println(response.body().string());
response.close();} 
catch (Exception ex) {}}


public static void updateStatus(String linkId) throws IOException, InterruptedException {
String filelink = "filelink";
String status = "ready";
okhttp3.OkHttpClient client=null;
okhttp3.RequestBody requestBody;
okhttp3.Request request ;
okhttp3.Response response;;
client = new OkHttpClient();

requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
.addFormDataPart("linkId", linkId)
.addFormDataPart("filelink", filelink)
.addFormDataPart("status", status)
.build();

request = new Request.Builder().url(server+"/Login/updateStatus").post(requestBody).build();
response = client.newCall(request).execute();

if (!response.isSuccessful()) {
TimeUnit.SECONDS.sleep(1);
System.out.println(" "+ response);}
String res=response.body().string();
System.out.println(res);
JSONObject obj = new JSONObject(res);
System.out.println(obj.getString("status"));
response.close();}


}


