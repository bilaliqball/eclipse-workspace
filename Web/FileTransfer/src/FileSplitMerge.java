import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
public class FileSplitMerge {

public static void splitFileChunks(File f) throws IOException {
int partCounter = 1;
int sizeOfFiles = 4*1024 * 1024;
byte[] buffer = new byte[sizeOfFiles];
String fileName = f.getName();
FileInputStream fis;
BufferedInputStream bis;
FileOutputStream fos; 
try {
fis = new FileInputStream(f);
bis = new BufferedInputStream(fis); 
int bytesAmount = 0;
while ((bytesAmount = bis.read(buffer)) > 0) {
String filePartName = String.format("%s.%03d", fileName, partCounter++);
File newFile = new File(f.getParent(), filePartName);
fos = new FileOutputStream(newFile);
fos.write(buffer, 0, bytesAmount);fos.close();} bis.close();}
catch (Exception exception){exception.printStackTrace();}}


public static void mergeFileChunks(File inp) throws IOException {
File output=new File(inp.getParentFile()+"\\file.mp4");
ArrayList<File> filelist=new ArrayList<File>();
File[] files=inp.listFiles();
for (int i = 0; i < files.length; i++) {
if (files[i].isFile() && files[i].getName().contains(".")) {
filelist.add(files[i]);}}
try {
FileOutputStream fos = new FileOutputStream(output);
BufferedOutputStream bos = new BufferedOutputStream(fos);
for (File f : filelist) {Files.copy(f.toPath(),bos);}
bos.close();fos.close();}
catch (Exception exception){exception.printStackTrace();}}



public static void splitFile2(File f) {
String FILE_NAME = f.toString();
int PART_SIZE = 1024*1024;

File inputFile = new File(FILE_NAME);
FileInputStream inputStream;
String newFileName;
FileOutputStream filePart;
int fileSize = (int) inputFile.length();
int nChunks = 0, read = 0, readLength = PART_SIZE;
byte[] byteChunkPart;
try {
inputStream = new FileInputStream(inputFile);
while (fileSize > 0) {
	if (fileSize <= PART_SIZE) {
		readLength = fileSize;
	}
byteChunkPart = new byte[readLength];
read = inputStream.read(byteChunkPart, 0, readLength);
fileSize -= read;
assert (read == byteChunkPart.length);
nChunks++;
newFileName = FILE_NAME + ".part"
		+ Integer.toString(nChunks - 1);
filePart = new FileOutputStream(new File(newFileName));
filePart.write(byteChunkPart);
filePart.flush();
filePart.close();
byteChunkPart = null;
filePart = null;
	}
	inputStream.close();
} catch (IOException exception) {
	exception.printStackTrace();
}
}



public static void mergeFiles2(File inp) {
File output=new File(inp.toString()+"\\file.wmv");

FileOutputStream fos;
FileInputStream fis;
byte[] fileBytes;
int bytesRead = 0;
ArrayList<File> filelist = new ArrayList<File>();
File[] files=inp.listFiles();

for (int i = 0; i < files.length; i++) {
if (files[i].isFile() && files[i].getName().contains(".part")) {
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
fis = null;
}
fos.close();
fos = null;}
catch (Exception exception){exception.printStackTrace();}
}

public static void main(String[] args) throws IOException {
//splitFile(new File("C:\\Users\\bilal.iqbal\\Pictures\\inputs\\input\\raining.mp4"));
mergeFileChunks(new File("C:\\Users\\bilal.iqbal\\Pictures\\inputs\\input"));}
	
}

