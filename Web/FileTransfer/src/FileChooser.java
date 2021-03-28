import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class FileChooser extends Application{
	
public static void main(String args[]) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
new FileChooser().chooseInput();}
	
	

	

public  void zipDirectory(File dir) throws IOException {
String pardir=dir.getParent();
String filename=dir.getName();
String outputZipFile =pardir+"\\_"+filename+".zip";
byte[] buffer = new byte[4096];
try{
File files[]=dir.listFiles();
FileOutputStream fos = new FileOutputStream(outputZipFile);
ZipOutputStream zos = new ZipOutputStream(fos);
for(File file:files) {
System.out.println(file);
ZipEntry ze= new ZipEntry(file.getName().toString());
zos.putNextEntry(ze);
FileInputStream in = new FileInputStream(file);
int len;while ((len = in.read(buffer)) > 0) {zos.write(buffer, 0, len);}
in.close();}
zos.closeEntry();zos.close();}
catch(IOException ex){ex.printStackTrace();}
System.out.println("Compressed");}


public  void zipMultiFiles(File files[] ) throws IOException {
String pardir=files[0].getParent();
String outputZipFile =pardir+"\\_multiplefiles.zip";
byte[] buffer = new byte[4096];
try{
FileOutputStream fos = new FileOutputStream(outputZipFile);
ZipOutputStream zos = new ZipOutputStream(fos);
for(File file:files) {
ZipEntry ze= new ZipEntry(file.getName().toString());
zos.putNextEntry(ze);
FileInputStream in = new FileInputStream(file);
int len;while ((len = in.read(buffer)) > 0) {zos.write(buffer, 0, len);}
in.close();}
zos.closeEntry();zos.close();}
catch(IOException ex){ex.printStackTrace();}}


public  void unzip(File zipFile) throws IOException{
String par=zipFile.getParent();
String filename=zipFile.getName(); 
String name=filename.substring(0,filename.lastIndexOf('.'));
String comdir=par+"\\"+name+"\\_comp";
new File(comdir).mkdirs();
byte[] buffer = new byte[4096];
ZipInputStream zis=null;
ZipEntry ze=null;
FileOutputStream fos = null;
try{
zis= new ZipInputStream(new FileInputStream(zipFile));
ze= zis.getNextEntry();
while(ze!=null){
String fileName = ze.getName();
File newFile = new File(comdir+"\\"+fileName);
fos = new FileOutputStream(newFile);             
int len;
while ((len = zis.read(buffer)) > 0) {fos.write(buffer, 0, len);}
fos.close();ze = zis.getNextEntry();}}
catch(IOException ex){ex.printStackTrace(); }
finally{fos.close();zis.closeEntry();zis.close();}}








public File[] chooseFiles() throws IOException {
String username = System.getProperty("user.name");
final FileDialog fileDialog = new FileDialog((Frame)null, "Choose File");
fileDialog.setMultipleMode(true);
fileDialog.setDirectory(username);
fileDialog.setVisible(true);
File files[] = fileDialog.getFiles();
//String res="";String dir=fileDialog.getDirectory();
//String fil = fileDialog.getFile();
//for (File file : files) {res+=file.getName()+" ";System.out.println(file.getName());}
for (File file : files) {System.out.println(file);}
return files;}



public  void chooseDirectory(){
Stage stage=new Stage();
DirectoryChooser directoryChooser = new DirectoryChooser();
File selectedDirectory=directoryChooser.showDialog(stage);
File f = directoryChooser.getInitialDirectory();
directoryChooser.setTitle("Select Some Directories");
directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
System.out.println(selectedDirectory);}


public void chooseInput() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException{
JFileChooser chooser;
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
String username = System.getProperty("user.name");
String path = "C:\\Users\\"+username+"\\Pictures\\";
chooser = new JFileChooser(); 
chooser.setCurrentDirectory(new File(path));
chooser.setDialogTitle("Choose File");
chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
chooser.setMultiSelectionEnabled(true);
chooser.setAcceptAllFileFilterUsed(false);
String filterfiles="png";
FileNameExtensionFilter filter = new FileNameExtensionFilter("FILES",filterfiles );
chooser.addChoosableFileFilter(filter);
if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
String choosed=chooser.getSelectedFile().toString();
File file=new File(choosed);
File files[]= chooser.getSelectedFiles();
for (File fi : files) {System.out.println(fi);}
if(file.isFile()) {System.out.println("File choosed: "+file.getName());}
if(file.isDirectory()) {System.out.println("Directory choosed: "+file.getName());
zipDirectory(file);}}}	






@Override
public void start(Stage arg0) throws Exception {
	// TODO Auto-generated method stub
	
}
  

	


}
