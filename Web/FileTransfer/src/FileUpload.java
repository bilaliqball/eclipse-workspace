import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class FileUpload {

	
public static void main(String args[]) throws IOException {
File file=new File("C:\\Users\\bilal.iqbal\\Pictures\\inputs\\iceberg.bmp");
	uploadSplit(file);
//downlaodMerge("file_zip_89");
}
	

public static void uploadSplit(File file) throws IOException {
long start=System.currentTimeMillis();
creteDirectory(file);System.out.println("Directory Created");
splitFile(file);System.out.println("File Splitted"); 
uploadChunks(file);System.out.println("Chunk Uploaded"); 
long exec=(System.currentTimeMillis()-start)/1000;
System.out.println("time taken to execute: "+exec);}

public static void downlaodMerge(String dirname) throws IOException {
long start=System.currentTimeMillis();
String chunk=dirname.substring(dirname.lastIndexOf("_")+1);int chunks=Integer.parseInt(chunk);
String filename="";String index="";String part="";
String username = System.getProperty("user.name");
File outdir=new File("C:\\Users\\"+username+"\\Downloads\\"+dirname+"\\");
outdir.mkdir();
for (int i=1;i<chunks+1;i++) {
index="000"+i;
part=index.substring(index.length() - 4);
filename=dirname+"_"+part+".txt";
try {download(dirname, filename,outdir.toString());}catch (IOException e1) {e1.printStackTrace();}}
mergeFiles(outdir);System.out.println("Chunks Merged");
deleteDirectory(dirname);System.out.println("File Deleted");
long exec=(System.currentTimeMillis()-start)/1000;
System.out.println("time taken to execute: "+exec);}

public static void splitFile(File file) throws IOException {
String filename=file.getName();
String name=filename.substring(0,filename.lastIndexOf('.'));
String ext=filename.substring(filename.lastIndexOf('.')+1); 
long chunksize=4*1024*1024;
long size= file.length();
double chunks=0;
int parts=0;
if(size>chunksize) {chunks=(double)size/chunksize;parts=(int)Math.ceil(chunks);}
String dir=name+"_"+ext+"_"+parts;new File(file.getParent()+"//"+dir).mkdir();	
int partCounter = 1;//I like to name parts from 001, 002, 003, ...
int sizeOfFiles = 4*1024 * 1024;// 1MB
byte[] buffer = new byte[sizeOfFiles];
String fileName = name+"_"+ext+"_"+parts;
try (FileInputStream fis = new FileInputStream(file);
BufferedInputStream bis = new BufferedInputStream(fis)) {
int bytesAmount = 0;
while ((bytesAmount = bis.read(buffer)) > 0) {
String filePartName = String.format("%s_%04d", fileName, partCounter++);
File newFile = new File(file.getParent()+"//"+dir+"//"+ filePartName+".txt");
try (FileOutputStream out = new FileOutputStream(newFile)) {out.write(buffer, 0, bytesAmount);} }}}


public  static void mergeFiles(File dir) {
FileOutputStream fos;
FileInputStream fis;
byte[] fileBytes;
int bytesRead = 0;
ArrayList<File> filelist = new ArrayList<File>();
File[] files=dir.listFiles();
String dirname=dir.getName();
String filename=dirname.substring(0,dirname.lastIndexOf("_"));//System.out.println("filename: "+filename);
String name=filename.substring(0,filename.lastIndexOf("_"));
String ext=filename.substring(filename.lastIndexOf("_")+1);
File output=new File(dir.toString()+"//"+name+"."+ext);

for (int i = 0; i < files.length; i++) {
if (files[i].isFile() && files[i].getName().contains(dirname)) {
filelist.add(files[i]);}}
try {
fos = new FileOutputStream(output,true);
for (File file : filelist) {
fis = new FileInputStream(file);
fileBytes = new byte[(int) file.length()];
bytesRead = fis.read(fileBytes, 0,(int)  file.length());
assert(bytesRead == fileBytes.length);
assert(bytesRead == (int) file.length());
fos.write(fileBytes);
fos.flush();
fileBytes = null;
fis.close();
fis = null;}
fos.close();
fos = null;}
catch (Exception exception){exception.printStackTrace();}}


public static Boolean creteDirectory(File file) throws IOException {
OkHttpClient client = new OkHttpClient();
String filename=file.getName();
String name=filename.substring(0,filename.lastIndexOf('.'));
String ext=filename.substring(filename.lastIndexOf('.')+1);
long chunksize=4*1024*1024;
long size= file.length();
double chunks=0;
int parts=0;
if(size>chunksize) {chunks=(double)size/chunksize;parts=(int)Math.ceil(chunks);}
String dir=name+"_"+ext+"_"+parts;
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



public static void uploadChunks(File file) throws IOException {
String filename=file.getName();
String name=filename.substring(0,filename.lastIndexOf('.'));//System.out.println(name); 
String ext=filename.substring(filename.lastIndexOf('.')+1);//System.out.println(ext); 
long chunksize=4*1024*1024;
long size= file.length();
double chunks=0;
int parts=0;
if(size>chunksize) {chunks=(double)size/chunksize;parts=(int)Math.ceil(chunks);}
String dir=name+"_"+ext+"_"+parts;new File(file.getParent()+"//"+dir).mkdir();	
File[] files=new File(file.getParent()+"//"+dir).listFiles();System.out.println("Files found"+files.length);
for(int i = 0; i < files.length; i++) {uploadChunkSync(dir,files[i]);}}


public static Boolean uploadChunkAsync(String dir,File file) throws IOException {
OkHttpClient client = new OkHttpClient();
Path path = file.toPath();
String mimeType = Files.probeContentType(path);System.out.println("mimetype: "+mimeType );
try {
RequestBody requestBody = new MultipartBody.Builder()
.setType(MultipartBody.FORM)
.addFormDataPart("file", file.getName(),RequestBody.create(MediaType.parse(mimeType), file))
.addFormDataPart("directory", dir).build();
Request request = new Request.Builder().url("http://202.142.168.20:8383/Home/uploadFileSimple").post(requestBody).build();
client.newCall(request).enqueue(new Callback() {
@Override
public void onFailure(Call call, IOException ioe) {throw new UnsupportedOperationException("Not supported yet."); }
@Override
public void onResponse(Call call, Response rspns) throws IOException {System.out.println(file.toString()+" "+rspns);}});
return true;} 
catch (Exception ex) {}
return false;}


public static void uploadChunkSync(String dir,File file) throws IOException {
String Filename=file.getName();
OkHttpClient client = new OkHttpClient();
Path path = file.toPath();
String mimeType = Files.probeContentType(path);System.out.println("mimetype: "+mimeType ); 
okhttp3.MediaType MEDIA_TYPE = okhttp3.MediaType.parse(mimeType);
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
.addFormDataPart("file", Filename, RequestBody.create(MEDIA_TYPE, file))
.addFormDataPart("directory", dir)
.build();
 Request request = new Request.Builder().url("http://202.142.168.20:8383/Home/uploadFileSimple")
.post(requestBody).build();
okhttp3.Response response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException("Unexpected code " + response);}
System.out.println(response.body().string());}
 

public static void download(String dir,String name,String outdir) throws IOException {
String uri="http://202.142.168.20:8383/UploadedFiles/"+dir+"/"+name;
//uri="http://localhost:62111/UploadedFiles/"+dir+"/"+name;
FileOutputStream fos = new FileOutputStream(outdir+"\\"+name);
URL website = new URL(uri);
ReadableByteChannel rbc = Channels.newChannel(website.openStream());
fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
System.out.println("file download at path: "+outdir+"\\"+name);
fos.close();}


public static Boolean deleteFile(String dir) throws IOException {
OkHttpClient client = new OkHttpClient();
try {
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("directoryName", dir)
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


public static Boolean deleteDirectory(String dir) throws IOException {
OkHttpClient client = new OkHttpClient();
try {
RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)	       
.addFormDataPart("directoryName", dir)
.build();
String uri="http://202.142.168.20:8383/Home/deleteDirectory";
Request request = new Request.Builder()
.url(uri)
.post(requestBody)
.build();
okhttp3.Response response = client.newCall(request).execute();
if (!response.isSuccessful()) {throw new IOException("Unexpected code " + response);}
System.out.println(response.body().string());
return true;} 
catch (Exception ex) {}
return false;}}
