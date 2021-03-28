package fileOperations;
import javax.swing.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.io.IOException;
import java.io.File;

public class Assistant extends JFrame {

private static final long serialVersionUID = 1L;
private JTextField textEnter = new JTextField();
private JTextArea textChat = new JTextArea();

public FileOperation FO;
public Conversion CO;
public Info IN;

public String operation=""; 	public void setOperation(String op) {this.operation=op;}
public String filename=""; 		public void setFilename(String fn) {this.filename=fn;}
public String destination="";	public void setDestination(String d) {this.destination=d; }
public int parameters=0; 		public void setParameters(int p) {this.parameters=p;} 

public File sourceFile; 		public void setSourceFile(String sf) {this.sourceFile=new File(sf);}
public File sourceDir; 			public void setSourceDir(String sd) {this.sourceDir=new File(sd);}
public File destinationFile; 	public void setDestFile(String df) {this.destinationFile=new File(df);}
public File destinationDir;  	public void setDestinationDir(String dd) {destinationDir=new File(dd);}
								public void setDefaultDestinationDir(String dd) {destinationDir=new File(dd);}

public Boolean srcFound;
public Boolean desFound;

public void parseQuery(String query) {
query+=" ";
String command[]=query.split(" ");
parameters=command.length;
if(parameters==1) {
parameters=2;setParameters(2);
operation=command[0];setOperation(operation);}

else if(parameters==2) {
parameters=2;setParameters(2);
operation=command[0];setOperation(operation);
filename=command[1];setFilename(filename);
destination=command[1];setDestination(destination);}

else if(parameters==3) {
parameters=3;setParameters(3);
operation=command[0];setOperation(operation);
filename=command[1];setFilename(filename);
destination=command[2];setDestination(destination);}}

public void parseNaturalQuery(String query) {
query+=" ";filename="";destination="";
String command[]=query.split(" ");
int params=command.length;int i; operation="";
Boolean src=false; Boolean des=false;
if(query.toLowerCase().contains("file ")) {src=true;}
if(query.toLowerCase().contains("to ") ||query.toLowerCase().contains("as ")) {des=true;}

for(i=0;i<command.length;i++) {
if(command[i].toLowerCase().equals("file")||command[i].toLowerCase().equals("directory")||command[i].toLowerCase().equals("folder")||command[i].toLowerCase().equals("by")) {filename=command[i+1];break;}
else{operation+=command[i];}}
if(query.toLowerCase().contains("directory")||query.toLowerCase().contains("folder")) {operation+="directory";}

for(i=0;i<command.length;i++) {
if(command[i].toLowerCase().equals("to")||command[i].toLowerCase().equals("for")||command[i].toLowerCase().equals("as")) {destination=command[i+1]; break;}}
if(query.toLowerCase().contains("as ")) {operation+="as";}

if(des) {
setParameters(3);
setOperation(operation);
setFilename(filename);
setDestination(destination);}

else if(src) {
setParameters(2);
setOperation(operation);
setFilename(filename);
setDestination(filename);}

else if(params>0) {
setParameters(2);
setOperation(operation);}

//System.out.println("Parameters: "+ parameters);
//System.out.println("Operation: "+operation);
//System.out.println("Source :"+ filename);
//System.out.println("Destination: "+destination);

}

public String toPath(String s) {
String pa[]=s.split("\\\\");
String absPath="";
int si=pa.length;
int i=0;
for(i=0;i<si;i++) {absPath=absPath+pa[i]+"\\\\";}
return absPath;} 

public Boolean contains(String str, String con) {
Boolean isContain=false;
isContain=str.toLowerCase().contains(con.toLowerCase());
return isContain;}
public void setFile(String fn) throws IOException {
if(FO.searchFile(fn)) {setSourceFile(FO.file);}}

public void searchDirectory(String fn) throws IOException {
FO.searchDirectory(fn);}

public void searchFile(String fn) throws IOException {
if(FO.searchFile(fn)) {setSourceFile(FO.file);}
else {assistantSay("File not found!");}}

public void getSearchFiles() throws ParseException, IOException {
FO.searchFiles(filename,destination);}

//_____________________________________________________________________________________________________________________________
public void getBackupAllFiles() throws IOException {
FO.backupAllFiles();}

public void getBackupFiles() throws IOException {
FO.backupFiles();}

public void getBackupFile() {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir));
FO.backupFile(src);} catch (IOException e1) {e1.printStackTrace();}}


public void getBackupDirectory() {
srcFound=false;
String srcdir;String desdir;
File src;File dest;
try {
srcFound=FO.SEARCHDirectory(filename);srcdir=FO.directory;src=new File(toPath(srcdir));
desdir=FO.backupDir;
dest=new File(desdir+src.getName());
if(dest.exists()==true) {
String message="File with same name already exists, do you want to override?";
int n = JOptionPane.showConfirmDialog(null,message,"Override File?",JOptionPane.YES_NO_OPTION);
if(n==0) {FO.backupDirectory(src);}else {}}
else{FO.backupDirectory(src);}} catch (IOException e1) {e1.printStackTrace();}}



public void getBackupFileAs() throws IOException {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir));
FO.backupFileAs(src,destination);} catch (IOException e1) {e1.printStackTrace();}}

public void getBackupDirectoryAs() throws IOException {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHDirectory(filename);srcdir=FO.directory;src=new File(toPath(srcdir));
FO.backupDirectoryAs(src,destination);} catch (IOException e1) {e1.printStackTrace();}}

public void getCopyFile() {
srcFound=false;
desFound=false;
String srcfile, desdir;
File src,des;
try {
srcFound=FO.SEARCHFile(filename);srcfile=FO.file;src=new File(toPath(srcfile));
desFound=FO.SEARCHDirectory(destination);desdir=FO.directory+"\\"+src.getName();des=new File(toPath(desdir));
if(des.exists()==true) {
String message="File with same name already exists, do you want to override?";
int n = JOptionPane.showConfirmDialog(null,message,"Override File?",JOptionPane.YES_NO_OPTION);
if(n==0) {FO.copyFile(src, des);}}
else{FO.copyFile(src, des);}} catch (IOException e1) {e1.printStackTrace();}}


public void getCopyDirectory() {
srcFound=false;
desFound=false;
String srcdir, desdir;
File src,des;File dest;
try {
srcFound=FO.SEARCHDirectory(filename);srcdir=FO.directory;src=new File(toPath(srcdir));
desFound=FO.SEARCHDirectory(destination);desdir=FO.directory;des=new File(toPath(desdir));
dest=new File(toPath(desdir+"\\"+filename));
if(dest.exists()==true) {
String message="Directory with same name already exists, do you want to override?";
int n = JOptionPane.showConfirmDialog(null,message,"Override File?",JOptionPane.YES_NO_OPTION);
if(n==0) {FO.copyDirectory(src, des);}}
else {FO.copyDirectory(src, des);}} catch (IOException e1) {e1.printStackTrace();}}

//________________________________________________________________________________________________________________________________________________
public void getFavourtizeFile() {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir));
FO.favourtizeFile(src);} catch (IOException e1) {e1.printStackTrace();}}

public void getFavourtizeDirectory() {
srcFound=false;
desFound=false;
String srcdir, desdir;
File src,des;File dest;
try {
srcFound=FO.SEARCHDirectory(filename);srcdir=FO.directory;src=new File(toPath(srcdir));
desdir=FO.favouriteDir;des=new File(toPath(desdir));
dest=new File(toPath(desdir+"\\"+src.getName()));
if(dest.exists()==true) {
String message="Directory with same name already exists, do you want to Override?";
int n = JOptionPane.showConfirmDialog(null,message,"Override File?",JOptionPane.YES_NO_OPTION);
if(n==0) {FO.overrideDirectory(src, des);}else{assistantSay("File already exists");}}
else{FO.favourtizeDirectory(src);}} catch (IOException e1) {e1.printStackTrace();}}

public void getFavourtizeFileAs() throws IOException {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir));
FO.favourtizeFileAs(src,destination);} catch (IOException e1) {e1.printStackTrace();}}


public void getFavourtizeDirectoryAs() throws IOException {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHDirectory(filename);srcdir=FO.directory;src=new File(toPath(srcdir));
FO.favourtizeDirectoryAs(src,destination);} catch (IOException e1) {e1.printStackTrace();}}

public void getMoveFile() {
srcFound=false;
desFound=false;
String srcfile, desdir;
File src,des;
try {
srcFound=FO.SEARCHFile(filename);srcfile=FO.file;src=new File(toPath(srcfile));
desFound=FO.SEARCHDirectory(destination);desdir=FO.directory+"\\"+src.getName();des=new File(toPath(desdir));
if(des.exists()==true) {
String message="File with same name already exists, do you want to override?";
int n = JOptionPane.showConfirmDialog(null,message,"Override File?",JOptionPane.YES_NO_OPTION);
if(n==0) {FO.copyFile(src, des);src.delete();}else{assistantSay("File already exists");}}
else{FO.moveFile(src, des);}} catch (IOException e1) {e1.printStackTrace();}}

public void getMoveDirectory() {
srcFound=false;
desFound=false;
String srcdir, desdir;
File src,des;File dest;
try {
srcFound=FO.SEARCHDirectory(filename);srcdir=FO.directory;src=new File(toPath(srcdir));
desFound=FO.SEARCHDirectory(destination);desdir=FO.directory;des=new File(toPath(desdir));
dest=new File(toPath(desdir+"\\"+src.getName()));
if(dest.exists()==true) {
String message="Directory with same name already exists, do you want to Override?";
int n = JOptionPane.showConfirmDialog(null,message,"Override File?",JOptionPane.YES_NO_OPTION);
if(n==0) {FO.overrideDirectory(src, des);}else{assistantSay("File already exists");}}
else{FO.moveDirectory(src, des);}} catch (IOException e1) {e1.printStackTrace();}}
//________________________________________________________________________________________________________________________________________________
public void getRenameFile() throws IOException {
srcFound=false;
String srcfile;
File src,des;
String renameAs=destination;
srcFound=FO.SEARCHFile(filename);srcfile=FO.file;src=new File(toPath(srcfile));
String dir=src.getParent().toString();
String ext=FilenameUtils.getExtension(src.getName());
if(destination.contains(".")) {des=new File(dir+"\\"+renameAs);}
else {des=new File(dir+"\\"+renameAs+"."+ext);}
FO.renameFile(src,des);}

public void getRenameDir() throws IOException {
srcFound=false;
desFound=false;
String srcdir, desdir;
File src,des;
srcFound=FO.SEARCHDirectory(filename);srcdir=FO.directory;src=new File(toPath(srcdir));
desdir=src.getParent()+"/"+destination;des=new File(toPath(desdir));
FO.renameDir(src, des);}

//________________________________________________________________________________________________________________________________________________
public void getDeleteFile() {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHFile(filename);
srcdir=FO.file;
src=new File(toPath(srcdir));
FO.deleteFile(src);} catch (IOException e1) {e1.printStackTrace();}}

public void getDeleteDirectory() {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHDirectory(filename);
srcdir=FO.directory;
src=new File(toPath(srcdir));
FO.deleteDirectory(src);} catch (IOException e1) {e1.printStackTrace();}}

public void getRemoveFile() {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHFile(filename);
srcdir=FO.file;
src=new File(toPath(srcdir));
FO.removeFile(src);} catch (IOException e1) {e1.printStackTrace();}}

public void getRemoveDirectory() {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHDirectory(filename);
srcdir=FO.directory;
src=new File(toPath(srcdir));
FO.removeDirectory(src);} catch (IOException e1) {e1.printStackTrace();}}

public void getShiftDeleteFile() {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHFile(filename);
srcdir=FO.file;
src=new File(toPath(srcdir));
FO.deleteFile(src);} catch (IOException e1) {e1.printStackTrace();}}

public void getDeleteTrash() throws IOException {
FO.deleteTrash();}

public void getUpdateTrash() throws IOException {
FO.updateTrash();}
//________________________________________________________________________________________________________________________________________________
public void getCompressAllFiles() throws IOException {
FO.compressAllFiles();}
public void getCompressFiles() throws IOException {
FO.compressFiles();}

public void getCompressFile() {
srcFound=false;
String srcdir;
File src;String ext;
String Doc="pdf docx xlsx pptx";
String Img="jpg png";
String Vid="mp4 avi flv wmv";
try {
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir)); ext=FilenameUtils.getExtension(src.getName());
     if(Doc.contains(ext)) {FileUtils.copyFile(src, new File("D:\\VirtualAssistant\\Compressed\\Documents\\"+src.getName()));}
else if(Img.contains(ext)) {CO.compressImage(src, new File("D:\\VirtualAssistant\\Compressed\\Pictures\\"+src.getName()));}
else if(Vid.contains(ext)) {CO.compressVideo(src, new File("D:\\VirtualAssistant\\Compressed\\Videos\\"+src.getName()));}
} catch (IOException e1) {e1.printStackTrace();}}

public void getCompressDirectory() {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHDirectory(filename);
srcdir=FO.directory;
src=new File(toPath(srcdir));
FO.compressDirectory(src);} catch (IOException e1) {e1.printStackTrace();}}

public void getCompressImage() throws IOException {
srcFound=false;
String srcdir;String desdir;
File src;File des;
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir));
desdir="D:\\VirtualAssistant\\Compressed\\Pictures\\compress"; des=new File(desdir+src.getName());
CO.compressImage(src,des);}

public void getResizeImage() throws IOException {
srcFound=false;
String srcdir;String desdir;
File src;File des;
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir));
desdir="D:\\VirtualAssistant\\Resized\\Pictures\\resize"; des=new File(desdir+src.getName());
CO.resizeImage(src,des);}

public void getConvertVideo() {
srcFound=false;
String srcdir;String desdir;
File src;File des;
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir));
desdir="C:\\Users\\bilal.iqbal\\Pictures\\FILES\\output\\converted"; des=new File(desdir+src.getName());
CO.videoConversion(src,des);}

public void getCompressVideo() {
srcFound=false;
String srcdir;String desdir;
File src;File des;
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir));
desdir="D:\\VirtualAssistant\\Compressed\\Videos\\compress"; des=new File(desdir+src.getName());
CO.compressVideo(src,des);}

public void getResizeVideo() {
srcFound=false;
String srcdir;String desdir;
File src;File des;
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir));
desdir="D:\\VirtualAssistant\\Resized\\Videos\\resize"; des=new File(desdir+src.getName());
CO.resizeVideo(src,des);}

public void getRelativeVideo() {
srcFound=false;
String srcdir;String desdir;
File src;File des;
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir));
desdir="D:\\VirtualAssistant\\Resized\\Videos\\relative"; des=new File(desdir+src.getName());
CO.videoReletiveResizing(src,des);}
//________________________________________________________________________________________________________________________________________________
public void getResizeAllFiles() throws IOException {
FO.resizeAllFiles();}

public void getResizeFiles() throws IOException {
FO.resizeFiles();}

public void getResizeFile() {
srcFound=false;
String srcdir;
File src;String ext;
String Doc="pdf docx xlsx pptx";
String Img="jpg png";
String Vid="mp4 avi flv wmv";
try {
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir)); ext=FilenameUtils.getExtension(src.getName());
     if(Doc.contains(ext)) {FileUtils.copyFile(src, new File("D:\\VirtualAssistant\\Resized\\Documents\\"+src.getName()));}
else if(Img.contains(ext)) {CO.resizeImage(src, new File("D:\\VirtualAssistant\\Resized\\Pictures\\"+src.getName()));}
else if(Vid.contains(ext)) {CO.resizeVideo(src, new File("D:\\VirtualAssistant\\Resized\\Videos\\"+src.getName()));}
} catch (IOException e1) {e1.printStackTrace();}}

public void getResizeDirectory() {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHDirectory(filename);
srcdir=FO.directory;
src=new File(toPath(srcdir));
FO.resizeDirectory(src);} catch (IOException e1) {e1.printStackTrace();}}

//________________________________________________________________________________________________________________________________________________
public void getZipAllFiles() throws IOException {
FO.zipAllFiles();}

public void getResizeZipAllFiles() throws IOException {
FO.resizeZipAllFiles();}

public void getCompressZipAllFiles() throws IOException {
FO.compressZipAllFiles();}

public void getZipFiles() throws IOException {
FO.zipFiles();}

public void getZipFile() {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHFile(filename);
srcdir=FO.file;
src=new File(toPath(srcdir));
FO.zipFile(src);} catch (IOException e1) {e1.printStackTrace();}}

public void getUnZipFile() throws IOException{
srcFound=false;
String srcdir;
File src;
srcFound=FO.SEARCHFile(filename);srcdir=FO.file;src=new File(toPath(srcdir));
FO.unZipFile(src);}

public void getZipDirectory() {
srcFound=false;
String srcdir;
File src;
try {
srcFound=FO.SEARCHDirectory(filename);
srcdir=FO.directory;
src=new File(toPath(srcdir));
FO.zipDirectory(src);} catch (IOException e1) {e1.printStackTrace();}}
//________________________________________________________________________________________________________________________________________________

public void getPrintFile() {
srcFound=false;
String srcdir;
File src;
srcFound=FO.SEARCHFile(filename);
srcdir=FO.file;
src=new File(toPath(srcdir));
FO.printFile(src);}

public void getPrintDirectory() {
srcFound=false;
String srcdir;
File src;
srcFound=FO.SEARCHDirectory(filename);
srcdir=FO.directory;
src=new File(toPath(srcdir));
FO.printDirectory(src);}

public void getCreateDirectory() {try {FO.createDirectory(filename);} catch (IOException e1) {e1.printStackTrace();}}

public void getCreateFile() throws IOException {FO.createFile(filename);}

public void print(String s) {System.out.println(s);}

public void getDisplayAllFiles() {FO.displayAllFiles();}

public void getDisplaySystemFiles() {FO.displaySystemFiles();}

public void getDisplaySystemFolders() {FO.displaySystemFolders();}

public void getDisplayDirectoryFiles() {srcFound=false;String srcdir;File src;
srcFound=FO.SEARCHDirectory(filename);srcdir=FO.directory;src=new File(toPath(srcdir));
FO.displayDirectoryFiles(src);}

public void getDisplayDirectoryFolders() {srcFound=false;String srcdir;File src;
srcFound=FO.SEARCHDirectory(filename);srcdir=FO.directory;src=new File(toPath(srcdir));
FO.displayDirectoryFolders(src);}

public void getDisplayDirectorySubFiles() throws IOException {srcFound=false;String srcdir;File src;
srcFound=FO.SEARCHDirectory(filename);srcdir=FO.directory;src=new File(toPath(srcdir));
FO.displayDirectorySubFiles(src);}

public void getDisplayDirectorySubFolders() throws IOException {srcFound=false;String srcdir;File src;
srcFound=FO.SEARCHDirectory(filename);srcdir=FO.directory;src=new File(toPath(srcdir));
FO.displayDirectorySubFolders(src);}



public void getSortFiles() {FO.sortFiles(filename);}

public void getArchieveFiles() throws IOException {FO.zipFiles(); }
public void getArrangeFiles() throws IOException {FO.arrangeAllFiles(); }

public void getDestinationPath() {String pa=toPath(filename);setDestinationDir(pa);}

public void addtoHistory(String cmd) {FO.history.add(cmd);FO.setComamnd(cmd);}

public void getDisplayHistory() {FO.displayHistory();}

public void getDisplayQueries() {FO.displayQueries();}

public void getDisplayLastCommands() throws IOException, InterruptedException, ParseException {
FO.displayLastExecutedCommands();}




public void findOperation() throws IOException, InterruptedException, ParseException {
	 if(operation.contains("Hi") && operation.contains("hi") && parameters==2) {assistantSay("Hi");}

else if(operation.contains("rea")&& operation.contains("ir") && parameters==2) {getCreateDirectory();assistantSay("Folder "+filename+" created!");} 
else if(operation.contains("rea")&& operation.contains("cre")&& parameters==2) {getCreateFile();assistantSay("File "+filename+" created!");}
	 
else if(operation.contains("pen") &&filename.contains("ote") && parameters==2) {FO.openNotepad();assistantSay(filename+" opened!");}
else if(operation.contains("pen") &&filename.contains("ord") && parameters==2) {FO.openWord();assistantSay(filename+" opened!");}	
else if(operation.contains("pen") &&filename.contains("xce") && parameters==2) {FO.openExcel();assistantSay(filename+" opened!");}	
else if(operation.contains("pen") &&filename.contains("owe") && parameters==2) {FO.openPowerPoint();assistantSay(filename+" opened!");}
else if(operation.contains("pen") &&filename.contains("dob") && parameters==2) {FO.openAcrobat();assistantSay(filename+" opened!");}
else if(operation.contains("pen") &&filename.contains("edi") && parameters==2) {FO.openMediaPlayer();assistantSay(filename+" opened!");}
else if(operation.contains("pen") &&filename.contains("vlc") && parameters==2) {FO.openVLC();assistantSay(filename+" opened!");}
else if(operation.contains("pen") &&operation.contains("pen")&& parameters==2) {FO.OPENFile(filename);assistantSay("File "+filename+" opened! ");}

else if(operation.contains("los") &&filename.contains("ote") && parameters==2) {FO.closeNotepad();assistantSay(filename+" closed!");}
else if(operation.contains("los") &&filename.contains("ord") && parameters==2) {FO.closeWord();assistantSay(filename+" closed!");}
else if(operation.contains("los") &&filename.contains("xce") && parameters==2) {FO.closeExcel();assistantSay(filename+" closed!");}
else if(operation.contains("los") &&filename.contains("owe") && parameters==2) {FO.closePowerPoint();assistantSay(filename+" closed!");}
else if(operation.contains("los") &&filename.contains("dob") && parameters==2) {FO.closeAcrobat();assistantSay(filename+" closed!");}
else if(operation.contains("los") &&filename.contains("edi") && parameters==2) {FO.closeMediaPlayer();assistantSay(filename+" closed!");}
else if(operation.contains("los") &&filename.contains("vlc") && parameters==2) {FO.closeVLC();assistantSay(filename+" closed!");}
else if(operation.contains("los") &&operation.contains("ll") && parameters==2) {FO.closeAll();assistantSay("All opened Files are closed!");}
else if(operation.contains("los") &&operation.contains("lo") && parameters==2) {FO.closeFile(filename);assistantSay("File "+filename+" closed!");}

else if(operation.contains("ip")  && operation.contains("omp")&& parameters==2) {getCompressZipAllFiles();assistantSay("All files Compressed and zipped !");} 
else if(operation.contains("ip")  && operation.contains("esi")&& parameters==2) {getResizeZipAllFiles();assistantSay("All files resized and zipped!");}
else if(operation.contains("ip")  && operation.contains("all")&& parameters==2) {getZipAllFiles();assistantSay("All File zipped!");}	 
else if(operation.contains("ip")  && operation.contains("les")&& parameters==2) {getZipFiles();assistantSay("Files are zipped!");}	 
else if(operation.contains("ip")  && operation.contains("dir")&& parameters==2) {getZipDirectory();assistantSay("Directory "+filename+" zipped!");} 
else if(operation.contains("ip")  && operation.contains("unz")&& parameters==2) {getUnZipFile();assistantSay("Files unziped!");} 
else if(operation.contains("ip")  && operation.contains("zip")&& parameters==2) {getZipFile();assistantSay("File "+filename+" zipped!");} 
	 
else if(operation.contains("iv")  && operation.contains("all")&& parameters==2) {getZipAllFiles();assistantSay("All File archived!");}	 
else if(operation.contains("iv")  && operation.contains("les")&& parameters==2) {getZipFiles();assistantSay("Files are archived!");}	 
else if(operation.contains("iv")  && operation.contains("dir")&& parameters==2) {getZipDirectory();assistantSay("Directory "+filename+" archived!");} 
else if(operation.contains("iv")  && operation.contains("ive")&& parameters==2) {getZipFile();assistantSay("File "+filename+" archived!");}
else if(operation.contains("ct")  && operation.contains("xtr")&& parameters==2) {getUnZipFile();assistantSay("Files extracted!");} 

	 
else if(operation.contains("siz") && operation.contains("all")&& parameters==2) {getResizeAllFiles();assistantSay("All File Resized!");}
else if(operation.contains("siz") && operation.contains("les")&& parameters==2) {getResizeFiles();assistantSay("Large Files are resized!");}
else if(operation.contains("siz") && operation.contains("dir")&& parameters==2) {getResizeDirectory();  assistantSay("Directory "+filename+" resized!");}
else if(operation.contains("siz") && operation.contains("siz")&& parameters==2) {getResizeFile();  assistantSay("File "+filename+" resized!");}
	 
else if(operation.contains("omp") && operation.contains("all")&& parameters==2) {getCompressAllFiles();assistantSay("All File Compressed!");}
else if(operation.contains("omp") && operation.contains("les")&& parameters==2) {getCompressFiles();assistantSay("Large Files are compressed!");}
else if(operation.contains("omp") && operation.contains("dir")&& parameters==2) {getCompressDirectory();  assistantSay("Directory "+filename+" compressed!");}
else if(operation.contains("omp") && operation.contains("omp")&& parameters==2) {getCompressFile();  assistantSay("File "+filename+" compressed!");}
	 

else if(operation.contains("isp") && operation.contains("yst")&& parameters==2) {getDisplayAllFiles();assistantSay("All "+FO.FileList.size()+" Files found!");}
else if(operation.contains("isp") && operation.contains("sto")&& parameters==2) {getDisplayQueries(); assistantSay("History displayed!");}
else if(operation.contains("isp") && operation.contains("lfi")&& parameters==2) {getDisplaySystemFiles();assistantSay("getDisplaySystemFiles!");}
else if(operation.contains("isp") && operation.contains("lfo")&& parameters==2) {getDisplaySystemFolders();assistantSay("getDisplaySystemFolders!");}

else if(operation.contains("isp") && operation.contains("bfi")&& parameters==2) {getDisplayDirectorySubFiles();assistantSay("getDisplayDirectorySubFiles!");}
else if(operation.contains("isp") && operation.contains("bfo")&& parameters==2) {getDisplayDirectorySubFolders();assistantSay("getDisplayDirectorySubFolders!");}
	 
else if(operation.contains("isp") && operation.contains("fil")&& parameters==2) {getDisplayDirectoryFiles();assistantSay("getDisplayDirectoryFiles!");}
else if(operation.contains("isp") && operation.contains("fol")&& parameters==2) {getDisplayDirectoryFolders();assistantSay("getDisplayDirectoryFolders!");}




else if(operation.contains("em")  && operation.contains("dir") &&parameters==2) {getRemoveDirectory();assistantSay("File "+filename+" removed!");}
else if(operation.contains("em")  && operation.contains("rem") &&parameters==2) {getRemoveFile();assistantSay("File "+filename+" removed!");}
else if(operation.contains("en")  && operation.contains("dir") &&parameters==3) {getRenameDir();assistantSay("Directory "+filename+" renamed!");}
else if(operation.contains("en")  && operation.contains("nam") &&parameters==3) {getRenameFile();assistantSay("File "+filename+" renamed!");}
	 
else if(operation.contains("ack") && operation.contains("all")&& parameters==2) {getBackupAllFiles();assistantSay("All File backup!");}
else if(operation.contains("ack") && operation.contains("les")&& parameters==2) {getBackupFiles();assistantSay("Files backup!");} 


else if(operation.contains("ack")  && operation.contains("ir")&& parameters==3) {getBackupDirectoryAs();assistantSay("Directory "+filename+" renameCoppied!");}
else if(operation.contains("avo")  && operation.contains("ir")&& parameters==3) {getFavourtizeDirectoryAs();assistantSay("Directory "+filename+" renameMoved!");}
else if(operation.contains("ack")  && operation.contains("ck")&& parameters==3) {getBackupFileAs();assistantSay("File "+filename+" renameCoppied!");}
else if(operation.contains("avo")  && operation.contains("vo")&& parameters==3) {getFavourtizeFileAs();assistantSay("File "+filename+" renameMoved!");}

	 
else if(operation.contains("ack") && operation.contains("dir")&& parameters==2) {getBackupDirectory();assistantSay("Directory backup!");} 
else if(operation.contains("ack") && operation.contains("ack")&& parameters==2) {getBackupFile();assistantSay("File backup!");} 	 
else if(operation.contains("avo") && operation.contains("dir")&& parameters==2) {getFavourtizeDirectory();assistantSay("Dirctory added to favourite!");} 
else if(operation.contains("avo") && operation.contains("our")&& parameters==2) {getFavourtizeFile();assistantSay("File added to favourite!");} 
	 
else if(operation.contains("opy") && operation.contains("dir")&& parameters==2) {getBackupDirectory();assistantSay("Directory copy!");} 
else if(operation.contains("opy") && operation.contains("opy")&& parameters==2) {getBackupFile();assistantSay("File copy!");} 	 
else if(operation.contains("ove") && operation.contains("dir")&& parameters==2) {getFavourtizeDirectory();assistantSay("Dirctory move!");} 
else if(operation.contains("ove") && operation.contains("ove")&& parameters==2) {getFavourtizeFile();assistantSay("File moved!");} 

else if(operation.contains("op")  && operation.contains("dir")&& parameters==3) {getCopyDirectory();  assistantSay("Directory "+filename+" coppied!");}
else if(operation.contains("op")  && operation.contains("opy")&& parameters==3) {getCopyFile();assistantSay("File "+filename+" Coppied to entered path!");}
else if(operation.contains("ov")  && operation.contains("dir")&& parameters==3) {getMoveDirectory();  assistantSay("Directory "+filename+" Moved to Entered path!");}
else if(operation.contains("ov")  && operation.contains("ove")&& parameters==3) {getMoveFile();assistantSay("File "+filename+" Moved to entered path!");}	 

else if(operation.contains("el")  && operation.contains("ire")&&parameters==2) {getDeleteDirectory();assistantSay("Directory "+filename+" Deleted with all content!");}  
else if(operation.contains("el")  && operation.contains("hif")&&parameters==2) {getShiftDeleteFile();assistantSay("File "+filename+" ShifteDeleted!");}
else if(operation.contains("el")  && operation.contains("ras")&&parameters==2) {getDeleteTrash();assistantSay("Empty Trash, all files Deleted!");} 
else if(operation.contains("pd")  && operation.contains("ras")&&parameters==2) {getUpdateTrash();assistantSay("Trash Updates, files older than 1 days are deleted!");} 
else if(operation.contains("el")  && operation.contains("ele")&&parameters==2) {getDeleteFile();assistantSay("File "+filename+" deleted!");}
	 
else if(operation.contains("ri")  && operation.contains("dir") &&parameters==2) {getPrintDirectory();assistantSay("File "+filename+" Printed!");}	 
else if(operation.contains("ri")  && operation.contains("int") &&parameters==2) {getPrintFile();assistantSay("File "+filename+" Printed!");}
	 
	 

else if(operation.contains("ort") && operation.contains("les")&&parameters==2) {getSortFiles();assistantSay("File sorted!");}
else if(operation.contains("rch") && operation.contains("les")&&parameters==2) {getSearchFiles();assistantSay("Files Seacrhed");} 
else if(operation.contains("rch") && operation.contains("les")&&parameters==3) {getSearchFiles();assistantSay("Files Seacrhed");} 
else if(operation.contains("ran") && operation.contains("les")&&parameters==2) {getArrangeFiles(); assistantSay("File Arranged!");}

else if(operation.contains("et")  && operation.contains("ath")&&parameters==2) {getDestinationPath(); assistantSay("Default Path "+filename+" updated!");}
else if(operation.contains("fr")  && operation.contains("esh")&&parameters==2) {getRefresh(); assistantSay("Refreshed!");}	
else if(operation.contains("nd")  && operation.contains("ist")&&parameters==2) {getDisplayLastCommands();assistantSay("Undoable commands!");}
else if(operation.contains("nd")  && operation.contains("ndo")&&parameters==2) {FO.deQuery(); assistantSay("UNDO");}
else if(operation.contains("fo")  && operation.contains("inf")&&parameters==2) {IN.checkInfoMessage(filename); assistantSay("info displayed!");}//
else if(operation.contains("le")  && operation.contains("lea")&&parameters==2) {getClear();}
else {assistantSay("Cant recognize your command! Enter again.");}}






ActionListener act=new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
String command = textEnter.getText();youSay(command);
//parseQuery(command);addtoHistory(command);
parseNaturalQuery(command);addtoHistory(command);

try {setFile(filename);} catch (IOException e2) {e2.printStackTrace();}
try {findOperation(); } 
catch (IOException | InterruptedException e1) {e1.printStackTrace();} 
catch (ParseException e1) {e1.printStackTrace();}
textEnter.setText("");}};



public Assistant() throws IOException {
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setSize(420, 600);
this.setVisible(true);
this.setResizable(false);
this.setLayout(null);
this.setTitle("VIRTUAL ASSISATANT");

textEnter.setLocation(2,540);
textEnter.setSize(590, 30);
JScrollPane scrollPane = new JScrollPane(textChat);

scrollPane.setLocation(7,10);
scrollPane.setSize(400,520);
textChat.setEditable(false);

this.add(textEnter);
this.add(scrollPane);
FO=new FileOperation();CO=new Conversion();IN=new Info();
setDefaultDestinationDir("D:\\VirtualAssistant\\Assistant\\");


textEnter.addActionListener(act);}

public void youSay(String you) {
textChat.append("You: " + you + "\n");}

public void assistantSay(String assistant) {
textChat.append("Assistant: " + assistant + "\n");}

public void getClear() {
textChat.setText("");}

public void getRefresh() throws IOException {
FO.refresh();}

public static void main(String[] args) throws IOException {
new Assistant();}
}
