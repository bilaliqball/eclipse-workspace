
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

public class FileAES {
public static void processFile(boolean encrypt, File inputFile, String inputKey, File outputFile) throws Exception {
Key key = new SecretKeySpec(inputKey.getBytes(),"AES");
Cipher cipher = Cipher.getInstance("AES");
if(encrypt) {cipher.init(Cipher.ENCRYPT_MODE,key);}
else {cipher.init(Cipher.DECRYPT_MODE,key);}
FileInputStream fileInputStream = new FileInputStream(inputFile);
byte[] inputBytes = new byte[(int)inputFile.length()];
fileInputStream.read(inputBytes);
byte[] outputBytes = cipher.doFinal(inputBytes);
FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
fileOutputStream.write(outputBytes);
fileInputStream.close();
fileOutputStream.close();}


public static void encryptFileChunk(File inputFile,File outputFile) throws Exception {
String inputKey = "aesEncryptionKey";
Key key = new SecretKeySpec(inputKey.getBytes(),"AES");
Cipher cipher = Cipher.getInstance("AES");
cipher.init(Cipher.ENCRYPT_MODE,key);
FileInputStream fileInputStream = new FileInputStream(inputFile);
byte[] inputBytes = new byte[(int)inputFile.length()];
fileInputStream.read(inputBytes);
byte[] outputBytes = cipher.doFinal(inputBytes);
FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
fileOutputStream.write(outputBytes);
fileInputStream.close();
fileOutputStream.close();}


public static void decryptFileChunk(File inputFile,File outputFile) throws Exception {
String inputKey = "aesEncryptionKey";
Key key = new SecretKeySpec(inputKey.getBytes(),"AES");
Cipher cipher = Cipher.getInstance("AES");
cipher.init(Cipher.DECRYPT_MODE,key);
FileInputStream fileInputStream = new FileInputStream(inputFile);
byte[] inputBytes = new byte[(int)inputFile.length()];
fileInputStream.read(inputBytes);
byte[] outputBytes = cipher.doFinal(inputBytes);
FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
fileOutputStream.write(outputBytes);
fileInputStream.close();
fileOutputStream.close();}


public static void encryptFile(File inputFile,File outputFile) throws Exception {
String inputKey = "aesEncryptionKey";
processFile(true,inputFile,inputKey,outputFile);}


public static void decryptFile(File inputFile,File outputFile) throws Exception {
String inputKey = "aesEncryptionKey";	
processFile(false,inputFile,inputKey,outputFile);}


public static String encodeString(String inpstr) {
Base32 base32 = new Base32();
String encstr=base32.encodeAsString(inpstr.getBytes());
encstr= encstr.replaceAll("=","");
return encstr;}

public static String decodeString(String encstr) {
Base32 base32 = new Base32();
String decstr=new String(base32.decode(encstr.getBytes()));
return decstr;}



public static void main(String args[]) throws Exception {

	
	
File inp=new File("C:\\Users\\bilal.iqbal\\Pictures\\inputs\\enc.bin");
File enc=new File("C:\\Users\\bilal.iqbal\\Pictures\\inputs\\enc.bin");
//File dec=new File("C:\\Users\\bilal.iqbal\\Pictures\\inputs\\iphone.png");
encryptFileChunk(inp,enc);
//decryptFileChunk(enc,dec);
	
//String inpstr="mosque.png";
//String encstr=encodeString(inpstr);
//System.out.println("  input:"+inpstr);
//System.out.println("encoded:"+encstr);
//System.out.println("decoded:"+decodeString(encstr));


}
    
}