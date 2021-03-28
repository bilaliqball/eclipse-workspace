
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.time.Duration;
import static java.time.temporal.ChronoUnit.SECONDS;

public class IPLocationFinder {


	
	

	
public static void writeXlsx() throws IOException {
String excelFileName = "C:\\Users\\bilal.iqbal\\Pictures\\output\\output.xlsx";
String sheetName = "Sheet1";
XSSFWorkbook wb = new XSSFWorkbook();
XSSFSheet sheet = wb.createSheet(sheetName) ;
for (int r=0;r < 3; r++ ){
XSSFRow row = sheet.createRow(r);
for (int c=0;c < 3; c++ ){
XSSFCell cell = row.createCell(c);
cell.setCellValue("Cellss "+r+" "+c);}}
FileOutputStream fileOut = new FileOutputStream(excelFileName);
wb.write(fileOut);
fileOut.flush();
fileOut.close();}
	
	
public static void readXlsx() throws IOException {
File excelFile = new File("C:\\Users\\bilal.iqbal\\Pictures\\output\\input.xlsx");
FileInputStream fis = new FileInputStream(excelFile);
XSSFWorkbook workbook = new XSSFWorkbook(fis);
XSSFSheet sheet = workbook.getSheetAt(0);
Iterator<Row> rowIt = sheet.iterator();
int rows=0;
while(rowIt.hasNext()) {
Row row = rowIt.next();
Iterator<Cell> cellIterator = row.cellIterator();
while (cellIterator.hasNext()) {Cell cell = cellIterator.next();System.out.print(cell.toString() + ";");}System.out.println();
rows++;}
workbook.close();
fis.close();
System.out.println(rows);}






public static void getRowCount(){
try{String file="C:\\Users\\bilal.iqbal\\Pictures\\output\\input.xlsx";
FileInputStream myxls = new FileInputStream(file);
XSSFWorkbook workbook = new XSSFWorkbook(myxls);
XSSFSheet worksheet = workbook.getSheetAt(0);
int lastRow=worksheet.getLastRowNum();System.out.println(lastRow);}
catch(Exception e){}}
	



public static void writeRow(int r){
try{
String file="output.xlsx";
FileInputStream myxls = new FileInputStream(file);
XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
XSSFSheet worksheet = studentsSheet.getSheetAt(0);
Row row = worksheet.createRow(r);
row.createCell(0).setCellValue("Update Value");
myxls.close();
FileOutputStream output_file =new FileOutputStream(new File(file));  
studentsSheet.write(output_file);
output_file.close();
studentsSheet.close();
System.out.println("successfully written");}
catch(Exception e){}}
	
	
public static void readRow(int r) throws IOException {
String file="output.xlsx";
File excelFile = new File(file);
FileInputStream fis = new FileInputStream(excelFile);
XSSFWorkbook workbook = new XSSFWorkbook(fis);
XSSFSheet sheet = workbook.getSheetAt(0);
Row row=sheet.getRow(2);
Cell cell = row.getCell(2);
System.out.println(cell);
workbook.close();fis.close();}









public static void appendRow(String senderInfo,String receiverInfo,String outputFile){
try{
File file=new File(outputFile);
FileInputStream myxls = new FileInputStream(file);
XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
XSSFSheet worksheet = studentsSheet.getSheetAt(1);
int lastRow=worksheet.getLastRowNum();
Row row = worksheet.createRow(++lastRow);
row.createCell(0).setCellValue(senderInfo);
row.createCell(1).setCellValue(receiverInfo);
myxls.close();
FileOutputStream output_file =new FileOutputStream(file);  
studentsSheet.write(output_file);
output_file.close();
studentsSheet.close();}
catch(Exception e){}}

public static IPAddressValidator iPAddressValidator=new IPAddressValidator();	
	

public static String getIPInfo(String ipAddress ) {
String response="";
try {
URL url = new URL("http://ip-api.com/csv/"+ipAddress+"?fields=country,regionName,city,query");
HttpURLConnection conn = (HttpURLConnection)url.openConnection();
conn.setRequestMethod("GET");
conn.connect();
int rc = conn.getResponseCode();
Scanner sc = new Scanner(url.openStream());
while(sc.hasNext()){response+=sc.nextLine();}} 
catch (FileNotFoundException ex) {} 
catch (IOException ex) {}
return response;}



public static void findIPLocations(String input) throws IOException, InterruptedException {
File file=new File(input);
FileInputStream fis = new FileInputStream(file);
XSSFWorkbook workbook = new XSSFWorkbook(fis);
XSSFSheet sheet = workbook.getSheetAt(0);
int rows=sheet.getLastRowNum();
Row row=null;
String senderIP = null;
String receiverIP=null;
String senderInfo = "Invalid IPAddress";
String receiverInfo= "Invalid IPAddress";
for(int i=1;i<rows;i++) {
row=sheet.getRow(i);
senderIP = row.getCell(1).toString();
receiverIP = row.getCell(2).toString();
if(iPAddressValidator.validate(senderIP)) {senderInfo=getIPInfo(senderIP);}else {senderInfo = "Invalid IPAddress";}
if(iPAddressValidator.validate(receiverIP)) {receiverInfo=getIPInfo(senderIP);}else {receiverInfo= "Invalid IPAddress";}
System.out.println(i+"/"+rows +"	"+senderInfo + "		"+receiverInfo);
appendRow(senderInfo,receiverInfo,input);
TimeUnit.MILLISECONDS.sleep(50);}
workbook.close();fis.close();}








public static void main(String args[]) throws IOException {
System.out.println("Waiting");
System.out.println(getIPInfo("202.142.168.20"));

}
}