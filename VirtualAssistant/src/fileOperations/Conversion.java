package fileOperations;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;


public class Conversion {
Process process;
String ffmpeg;
File source;
File dest;
public Conversion() {
process=null;
ffmpeg="C:\\Program Files\\ffmpeg\\bin\\ffmpeg\\";}





public void audioConversion(File source, File dest) {
try {
process = new ProcessBuilder(
ffmpeg,"-i",
"C:\\Users\\bilal.iqbal\\Pictures\\FILES\\voices\\sample.wav" , "-y", 
"C:\\Users\\bilal.iqbal\\Pictures\\FILES\\output\\sampleAudioConverted.wav").start();
}catch (IOException e) { e.printStackTrace();}}

public void videoConversion(File source, File dest) {
try {
process = new ProcessBuilder(
ffmpeg,"-i",
source.toString(), "-y", 
dest.toString()).start();
}catch (IOException e) { e.printStackTrace();}}


public void compressVideo(File source, File dest) {
try {
process = new ProcessBuilder(
ffmpeg,"-i",
source.toString() , "-s","720x500",//"\"-c:a\"","copy", 
dest.toString()).start();

}catch (IOException e) { e.printStackTrace();}}

public void resizeVideo(File source, File dest) {
try {
process = new ProcessBuilder(
ffmpeg,"-i",
source.toString() , "-s","720x500",//"\"-c:a\"","copy", 
dest.toString()).start();

}catch (IOException e) { e.printStackTrace();}}


public void videoReletiveResizing(File source, File dest) {
try {
process= new ProcessBuilder(
ffmpeg,"-i",
source.toString(), "-vf","scale=\"720:-1\"",
dest.toString()).start();
//ffmpeg.waitFor();
//if (0 != ffmpeg.exitValue()) {System.out.println("ffmpeg failed! Exit value: {}"+ ffmpeg.exitValue());}} 
//catch (IOException e) { e.printStackTrace();throw new RuntimeException("Unable to run ffmpeg", e);} 
//catch (InterruptedException e) {e.printStackTrace();}
}catch (IOException e) { e.printStackTrace();}}

public void resizeImage(File file, File dest) throws IOException {
//File input = file;String name=file.getName();
File output =dest;
BufferedImage originalImage = ImageIO.read(file);
int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
BufferedImage resizeImageJpg = resizeImage(originalImage, type);
ImageIO.write(resizeImageJpg, "jpg",output);} 
private static BufferedImage resizeImage(BufferedImage originalImage, int type){
int Width=originalImage.getWidth();
int Height=originalImage.getHeight();
//int Diagnal=(int)Math.sqrt((Width*Width)+(Height*Height));
double Slope=(double)Height/Width;
int width=0;int height=0;
if(Width>1280) {width=1280;}else {width=Width;}
height=(int) (Slope*width);
//width=(int)(Width*0.5);height=(int)(Height*0.5);
BufferedImage resizedImage = new BufferedImage(width, height, type);
Graphics2D g = resizedImage.createGraphics();
g.drawImage(originalImage, 0, 0, width, height, null);
g.dispose();return resizedImage;}
		
		
public  void compressImage(File file, File dest) throws IOException {
File input =file;//String name=file.getName();
File compressedImageFile = dest;
BufferedImage image = ImageIO.read(input);
OutputStream os =new FileOutputStream(compressedImageFile);
Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
ImageWriter writer = (ImageWriter) writers.next();
ImageOutputStream ios = ImageIO.createImageOutputStream(os);
writer.setOutput(ios);
float CR=0.5f;long size=file.length();
CR=(float)102400L/size;
if(CR>1.0) {CR=1.0f;}else{CR=0.5f;}
ImageWriteParam param = writer.getDefaultWriteParam();
param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
param.setCompressionQuality(CR);
writer.write(null, new IIOImage(image, null, null), param);
os.close();
ios.close();
writer.dispose();}

}
