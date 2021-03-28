import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class FileDownload {

public static void main(String args[]) throws IOException, InterruptedException {

	downloadViaStream();
}
	
public static void setInfo(String info) {System.out.println(info);}
public static void showInfo(String info) {
JOptionPane.showMessageDialog(null,
info,"File Link",JOptionPane.PLAIN_MESSAGE);
StringSelection stringSelection = new StringSelection(info);
Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
clipboard.setContents(stringSelection, null);}

public static void showMessage(String info) {new Thread(new Runnable(){
@Override
public void run() {JOptionPane.showMessageDialog(null, "message", info,JOptionPane.PLAIN_MESSAGE);}}).start();
StringSelection stringSelection = new StringSelection(info);
Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
clipboard.setContents(stringSelection, null);}

public static Boolean uploadFileInChunks(String dir) throws IOException {
OkHttpClient client = new OkHttpClient();
try {
	System.out.println("..");
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("directoryName", dir)
.build();
String uri="http://202.142.168.20:8383/Home/uploadFileInChunks";
Request request = new Request.Builder()
.url(uri)
.post(requestBody)
.build();
okhttp3.Response response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException("Unexpected code " + response);}
System.out.println(response.body().string());} 
catch (Exception ex) {}
return false;}
	
public static Boolean creteDirectory(String dir) throws IOException {
OkHttpClient client = new OkHttpClient();
try {
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("directoryName", dir)
.build();
String uri="http://202.142.168.20:8383/Home/test";
Request request = new Request.Builder()
.url(uri)
.post(requestBody)
.build();
okhttp3.Response response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException("Unexpected code " + response);}
System.out.println(response.body().string());} 
catch (Exception ex) {}
return false;}


public static void uploadFile(File file) throws IOException {
String filename=file.getName();
String name=filename.substring(0,filename.lastIndexOf('.'));
String ext=filename.substring(filename.lastIndexOf('.')+1); 
long chunksize=4*1024*1024;
long size= file.length();
double chunks=0;
int parts=0;
if(size>chunksize) {chunks=(double)size/chunksize;parts=(int)Math.ceil(chunks);}
String dir=name+"_"+ext+"_"+parts;
creteDirectory(dir);

String Filename=file.getName();
OkHttpClient client = new OkHttpClient();
Path path = file.toPath();
String mimeType = Files.probeContentType(path);//System.out.println("mimetype: "+mimeType ); 
okhttp3.MediaType MEDIA_TYPE = okhttp3.MediaType.parse(mimeType);
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
.addFormDataPart("file", Filename, RequestBody.create(MEDIA_TYPE, file))
.addFormDataPart("directoryName", dir)
.build();
 Request request = new Request.Builder().url("http://202.142.168.20:8383/Home/SplitFile")
.post(requestBody).build();
okhttp3.Response response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException("Unexpected code " + response);}
System.out.println(response.body().string());}



public static void downloadFileList(String dir) throws IOException {
OkHttpClient client = new OkHttpClient();
try {
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("directoryName", dir)
.build();
String uri="http://localhost:49755/Desktop/getFilelist";
Request request = new Request.Builder()
.url(uri)
.post(requestBody)
.build();
okhttp3.Response response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException("Unexpected code " + response);}
String res=response.body().string();
System.out.println(res);
JSONObject obj = new JSONObject(res);
String files=obj.getString("status");
String filelist[]=files.split(",");
if(filelist.length>0) {
for(int i=0; i<filelist.length;i++) {System.out.println(filelist[i]);}
}

}catch (Exception ex) {}}


public static boolean exists(String URLName){
try {
HttpURLConnection.setFollowRedirects(false);
HttpURLConnection con =(HttpURLConnection) new URL(URLName).openConnection();
con.setRequestMethod("HEAD");
return (con.getResponseCode() == HttpURLConnection.HTTP_OK);}
catch (Exception e) {e.printStackTrace();return false;}}



public static void downloadFile(String dir,String name,String outdir) throws IOException, InterruptedException {
String uri="http://202.142.168.20:8383/UploadedFiles/"+dir+"/"+name;

FileOutputStream fos = new FileOutputStream(outdir+"\\"+name);
URL website = new URL(uri);
if(exists(uri)) {System.out.println("File Exists");
ReadableByteChannel rbc = Channels.newChannel(website.openStream());
fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
setInfo("File download at path: "+outdir+"\\"+name);fos.close();}
else {TimeUnit.SECONDS.sleep(3);setInfo("File Doesnt Exists, Waiting for upload");downloadFile(dir,name,outdir);}}

public static Boolean deleteFile(String dir,String file) throws IOException {
OkHttpClient client = new OkHttpClient();
try {
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("directoryName", dir)
.addFormDataPart("fileName", file)
.build();
String uri="http://202.142.168.20:8383/Home/deleteFile";
Request request = new Request.Builder()
.url(uri)
.post(requestBody)
.build();
okhttp3.Response response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException("Unexpected code " + response);}
System.out.println(response.body().string());
return true;} 
catch (Exception ex) {}
return false;}



public static void downloadDelete() {

String dirname="iceberg_bmp_26";
String chunk=dirname.substring(dirname.lastIndexOf("_")+1);int chunks=Integer.parseInt(chunk);
String filename="";String index="";String part="";
String username = System.getProperty("user.name");
File outdir=new File("C:\\Users\\"+username+"\\Downloads\\"+dirname+"\\");
outdir.mkdir();

for (int i=1;i<chunks+1;i++) {
index="000"+i;
part=index.substring(index.length() - 4);
filename=dirname+"_"+part+".txt";
try {downloadFile(dirname, filename,outdir.toString());}catch (IOException e1) {e1.printStackTrace();} catch (InterruptedException e1) {e1.printStackTrace();}
try {deleteFile(dirname,filename);setInfo("File Deleted"+ dirname+"//"+filename);} catch (IOException e2) {e2.printStackTrace();}}}


public static void downloadViaStream() throws IOException, InterruptedException {
String filename="raining.wmv";
String uri="http://202.142.168.20:8383/UploadedFiles/"+filename;
uri="https://www.googleapis.com/download/storage/v1/b/trango/o/image.png?generation=1568443567132879&alt=media";
URL website = new URL(uri);
if(exists(uri)) {System.out.println("file exists");
ReadableByteChannel rbc = Channels.newChannel(website.openStream());
FileOutputStream fos = new FileOutputStream("C:\\Users\\bilal.iqbal\\Pictures\\output\\"+"image.png");
fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);fos.close();System.out.println("File downloaded");}
else {TimeUnit.SECONDS.sleep(5);System.out.println("Wait for 5 sec");
downloadViaStream();}}



public static void downlaodViaBrowser(){
try{
String filename="iceberg.bmp";
String uri="http://202.142.168.20:8383/Home/SaveFile?filename="+filename;
uri="https://www.googleapis.com/download/storage/v1/b/trango/o/image.png?generation=1568443567132879&alt=media";
URI url = new URI(uri);
Desktop dt = Desktop.getDesktop();
dt.browse(url);}
catch(Exception ex){}}


public static Boolean downloadViaHttp() throws IOException {
OkHttpClient client = new OkHttpClient();
String filename="raining.mp4";
File file = new File("C:\\Users\\bilal.iqbal\\Pictures\\InputFiles\\download.png");
try {
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("filename", filename)
.build();
String uri="http://202.142.168.20:8383/Home/SaveFile";

uri="https://www.googleapis.com/download/storage/v1/b/trango/o/image.png?generation=1568443567132879&alt=media";
Request request = new Request.Builder()
.url(uri)
.post(requestBody)
.build();
okhttp3.Response response = client.newCall(request).execute();

//InputStream is = response.body().byteStream();
//BufferedInputStream input = new BufferedInputStream(is);
//OutputStream output = new FileOutputStream(file);
//byte[] data = new byte[1024];
//long total = 0;int count=0;
//while ((count = input.read(data)) != -1) {total += count;output.write(data, 0, count);}
//output.flush();
//output.close();
//input.close();


//BufferedSink sink = Okio.buffer(Okio.sink(file));
//sink.writeAll(response.body().source());
//sink.close();

//FileOutputStream fos = new FileOutputStream(file);
//fos.write(response.body().bytes());
//fos.close();



//try (BufferedSource bufferedSource = response.body().source()) {
//  BufferedSink bufferedSink = Okio.buffer(Okio.sink(file));
//  bufferedSink.writeAll(bufferedSource);
//  bufferedSink.close();
//}


if (!response.isSuccessful()) {throw new IOException("Unexpected code ");}

return true;} 
catch (Exception ex) {}
return false;}
	


	
}
