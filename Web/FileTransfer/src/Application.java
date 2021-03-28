import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.wb.swt.SWTResourceManager;

public class Application {

public Shell shell;
public Display display;
public JWindow processWindow;
public Text downloadtext;
	
File uploadFile=null;
File downlaodFile=null;
Label infolabel=null;
JLabel infolable=null;


//public Text textField_=null;

public static void main(String[] args) {
try {Application window = new Application();window.open();
//window.initWindow();window.showWindow();
} 
catch (Exception e) {e.printStackTrace();}}

//public JButton closeButton=null;
//public JButton uploadSplitButton=null;
//public JButton downloadMergeButton=null; 
//public JTextField textField=null;
//public JWindow window=null;
//
//public void initWindow() {
//window=new JWindow();
//window.setSize(800, 500);
//window.getContentPane().setBackground(java.awt.Color.WHITE);
//window.getContentPane().setLayout(null);
//
//textField = new JTextField();
//textField.setBounds(38, 226, 341, 23);
//textField.setColumns(10);
//textField.setText("...");
//textField.setEditable(true);
//window.getContentPane().add(textField);
//
//infolable = new JLabel("...");
//infolable.setBounds(38, 271, 276, 23);
//window.getContentPane().add(infolable);
//
//closeButton = new JButton("");
//closeButton.addActionListener(new ActionListener() {
//public void actionPerformed(ActionEvent e) {
//closeshareasWindow();}});
//
//closeButton.setIcon(new ImageIcon(Resources.getIcon("close.png")));
//closeButton.setBounds(770, 11, 20, 23);
//closeButton.setOpaque(false);
//closeButton.setContentAreaFilled(false);
//closeButton.setBorderPainted(false);
//window.getContentPane().add(closeButton);
//
//uploadSplitButton = new JButton("");
//uploadSplitButton.setIcon(new ImageIcon(Resources.getIcon("upload4.png")));
//uploadSplitButton.setBounds(389, 143, 158, 128);
//uploadSplitButton.setOpaque(false);
//uploadSplitButton.setContentAreaFilled(false);
//uploadSplitButton.setBorderPainted(false);
//window.getContentPane().add(uploadSplitButton);
//uploadSplitButton.addActionListener(new ActionListener() {
//public void actionPerformed(ActionEvent arg0) {
//infolable.setText("Uploaded and Sliptted");}});
//
//downloadMergeButton = new JButton("");
//downloadMergeButton.setIcon(new ImageIcon(Resources.getIcon("download2.png")));
//downloadMergeButton.setBounds(574, 143, 158, 128);
//downloadMergeButton.setOpaque(false);
//downloadMergeButton.setContentAreaFilled(false);
//downloadMergeButton.setBorderPainted(false);
//window.getContentPane().add(downloadMergeButton);
//downloadMergeButton.addActionListener(new ActionListener() {
//public void actionPerformed(ActionEvent e) {
//String text=textField.getText();
//infolable.setText("Downloaded and Merged"+text);
//textField.setText("Hiiii");}});}
//
//public void showWindow() throws HeadlessException {
//window.setVisible(true);
//window.setAlwaysOnTop(true);
//window.setLocationRelativeTo(null);}
//public void closeshareasWindow() {window.setVisible(false);window.dispose();}




public void initProcessWindow() {processWindow=new JWindow();
String ic=Resources.getIcon("loading1.gif");
ImageIcon icon=	new ImageIcon(ic);
processWindow.getContentPane().add(new JLabel("Processing", icon, SwingConstants.CENTER));
processWindow.setBounds(500, 200, 200, 200);}
public void showProcessWindow() {
processWindow.setVisible(true);
processWindow.setAlwaysOnTop(true);
processWindow.setLocationRelativeTo(null);}
public void closeProcessWindow() {processWindow.setVisible(false);processWindow.dispose();}

public static void openuri(String url){
try{
url=	"http://202.142.168.20:8383/";
URI uri = new URI(url);
Desktop dt = Desktop.getDesktop();
dt.browse(uri);}
catch(Exception ex){}}

public static void openurl(String url){
String os = System.getProperty("os.name").toLowerCase();
Runtime rt = Runtime.getRuntime();
try{
if (os.indexOf( "win" ) >= 0) {rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);} 
else if (os.indexOf( "mac" ) >= 0) {rt.exec( "open " + url);} 
else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {
String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror","netscape","opera","links","lynx"};
StringBuffer cmd = new StringBuffer();
for (int i=0; i<browsers.length; i++)
cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");
rt.exec(new String[] { "sh", "-c", cmd.toString() });} 
else {return;}}
catch (Exception e){return;}
return;}

public static void loadWebForm(){
final JFrame frame=new JFrame();
frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
frame.setSize(620,440);
final JFXPanel fxpanel=new JFXPanel();
frame.getContentPane().add(fxpanel);

Platform.runLater(new Runnable() {
@Override
public void run(){
WebEngine engine;
WebView wv=new WebView();
engine=wv.getEngine();
fxpanel.setScene(new Scene(wv));
engine.load("http://202.142.168.20:8383/");}});
frame.setVisible(true);}
	
public void getSystemInfo() {
System.out.println("User :"+System.getProperty("user.name"));
System.out.println("OS: "+System.getProperty("os.name").toLowerCase());
System.out.println("Version: " + System.getProperty("os.version"));
System.out.println("Arch: " + System.getProperty("os.arch"));
System.out.println();System.getProperties().list(System.out);}
	
public int confirmClose(){
String mes="Are you sure you Want to close window?";
JFrame jf=new JFrame();
jf.setAlwaysOnTop(true);
int response = JOptionPane.showConfirmDialog(jf,mes, "Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
return response;}

	
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
//String chunk=dirname.substring(dirname.lastIndexOf("_")+1);
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
String mimeType = Files.probeContentType(path);//System.out.println("mimetype: "+mimeType ); 
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
return false;}
	
public void FileChooser() throws IOException {
JFileChooser chooser;
String username = System.getProperty("user.name");
String path = "C:\\Users\\"+username+"\\Pictures\\";
chooser = new JFileChooser(); 
chooser.setCurrentDirectory(new File(path));
chooser.setDialogTitle("Choose File");
chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
String choosed=chooser.getSelectedFile().toString();
File file=new File(choosed);
uploadSplit(file);}}	
	


public void open() {
display = Display.getDefault();
createContents();
shell.open();
shell.layout();
while (!shell.isDisposed()) {
if (!display.readAndDispatch()) {display.sleep();}}}

protected void createContents() {
shell = new Shell();
shell.setSize(650, 566);
shell.setText("TRANGO-IO");


Button uploadButton = new Button(shell, SWT.NONE);
//uploadButton.setImage(SWTResourceManager.getImage("C:\\Users\\bilal.iqbal\\Web\\FileTransfer\\ico\\upload2.png"));
uploadButton.addSelectionListener(new SelectionAdapter() {
@Override
public void widgetSelected(SelectionEvent e) {openuri("http://202.142.168.20:8383/");}});
uploadButton.setBounds(36, 116, 302, 36);
uploadButton.setText("UPLOAD- SPLIT VIA BROWSER");



downloadtext = new Text(shell, SWT.BORDER);
downloadtext.setFont(Resources.getFont("Segoe UI", 14, SWT.BOLD));
downloadtext.setForeground(Resources.getColor(SWT.COLOR_BLACK));
downloadtext.setBounds(36, 198, 302, 36);
downloadtext.setToolTipText("Enter ID to download and merge File");
Button downloadButton = new Button(shell, SWT.NONE);
//downloadButton.setImage(SWTResourceManager.getImage("C:\\Users\\bilal.iqbal\\Web\\FileTransfer\\ico\\download2.png"));
downloadButton.addSelectionListener(new SelectionAdapter() {

@Override
public void widgetSelected(SelectionEvent e) {
String dirname=downloadtext.getText();
String chunk=dirname.substring(dirname.lastIndexOf("_")+1);int chunks=Integer.parseInt(chunk);
String filename="";String index="";String part="";
String username = System.getProperty("user.name");
File outdir=new File("C:\\Users\\"+username+"\\Downloads\\"+dirname+"\\");
outdir.mkdir();

for (int i=1;i<chunks+1;i++) {
index="000"+i;
part=index.substring(index.length() - 4);
filename=dirname+"_"+part+".txt";
try {download(dirname, filename,outdir.toString());infolabel.setText("Chunk: "+filename+" Downloaded!");}catch (IOException e1) {e1.printStackTrace();}}
mergeFiles(outdir);infolabel.setText("Chunks Merged");
try {deleteDirectory(dirname);} catch (IOException e1) {e1.printStackTrace();}infolabel.setText("File Deleted");}});
downloadButton.setBounds(358, 198, 196, 36);
downloadButton.setText("DOWNLOAD-MERGE");

infolabel = new Label(shell, SWT.NONE);
infolabel.setText("...");
infolabel.setBounds(36, 267, 458, 36);

//org.eclipse.swt.graphics.Color color=display.getSystemColor(SWT.COLOR_BLUE);
//org.eclipse.swt.graphics.Image image = new org.eclipse.swt.graphics.Image(display, Resources.getImage("back (5).jpg"));
//shell.setBackground(color);
//shell.setBackgroundImage(image);

Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
int x = (int) ((dimension.getWidth() - 700) / 2);
int y = (int) ((dimension.getHeight() - 480) / 2);
shell.setLocation(x, y);
Button uploadSplit = new Button(shell, SWT.NONE);
uploadSplit.addSelectionListener(new SelectionAdapter() {
@Override
public void widgetSelected(SelectionEvent e) {
try {FileChooser();} 
catch (IOException e1) {e1.printStackTrace();}}});
uploadSplit.setBounds(358, 116, 196, 36);
uploadSplit.setText("UPLOAD-SPLIT VIA APP");}
}
