import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.PluginTransfer;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class Application {
	
	
	

protected Shell shell;
static String inputFile;
public Text textArea;
private Text IpAddressText;
public Label infoLabel;
public static Application window;
public static IPAddressValidator iPAddressValidator=new IPAddressValidator();	
	
public static void main(String[] args) {
try {
window = new Application();
window.open();} 
catch (Exception e) {e.printStackTrace();}}









public Task createWorker() {
return new Task() {
@Override
protected Object call() throws Exception {
findIPLocations(inputFile);
return true;}};}


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



public void findIPLocations(String input) throws IOException, InterruptedException {
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
String info="";
for(int i=0;i<rows;i++) {
row=sheet.getRow(i);
senderIP = row.getCell(1).toString();
receiverIP = row.getCell(2).toString();
if(iPAddressValidator.validate(senderIP)) {senderInfo=getIPInfo(senderIP);}else {senderInfo = "Invalid IPAddress";}
if(iPAddressValidator.validate(receiverIP)) {receiverInfo=getIPInfo(senderIP);}else {receiverInfo= "Invalid IPAddress";}
info=i+"/"+rows +"	"+senderInfo + "		"+receiverInfo;
System.out.println(info);infoLabel.setText(info);
appendRow(senderInfo,receiverInfo,input);
TimeUnit.MILLISECONDS.sleep(1000);}
workbook.close();fis.close();}


public static void appendRow(String senderInfo,String receiverInfo, String inputfile){
try{
File file=new File(inputfile);
FileInputStream myxls = new FileInputStream(file);
XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
XSSFSheet worksheet = studentsSheet.getSheetAt(2);
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



public void open() {
Display display = Display.getDefault();
createContents();
shell.open();
shell.layout();
while (!shell.isDisposed()) {
if (!display.readAndDispatch()) {display.sleep();}}}

	
protected void createContents() {
shell = new Shell();
shell.setSize(550, 376);
shell.setText("SWT Application");

textArea = new Text(shell, SWT.BORDER);
textArea.setBounds(31, 108, 329, 27);
textArea.setText("Add URL or Drop File Here");
textArea.setToolTipText("Add URL or Drop File Here");
org.eclipse.swt.dnd.DropTarget dt  = new org.eclipse.swt.dnd.DropTarget(textArea, DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK);
dt.setTransfer(new Transfer[] { FileTransfer.getInstance(), PluginTransfer.getInstance() });


Button getIPInfo = new Button(shell, SWT.NONE);
getIPInfo.addSelectionListener(new SelectionAdapter() {
@Override
public void widgetSelected(SelectionEvent e) {
String IP=IpAddressText.getText();String res;
if(iPAddressValidator.validate(IP)) {
infoLabel.setText("Waiting to find Info");res=getIPInfo(IP);}
else {res ="Invalid IP";}
infoLabel.setText(res);
System.out.println(res);
}});
getIPInfo.setBounds(279, 49, 81, 25);
getIPInfo.setText("FIND IP INFO");



infoLabel = new Label(shell, SWT.NONE);
infoLabel.setBounds(31, 248, 455, 27);
infoLabel.setText("...");


IpAddressText = new Text(shell, SWT.BORDER);
IpAddressText.setText("IP ADDRESS HERE");
IpAddressText.setToolTipText("Enter IP Address Here");
IpAddressText.setBounds(31, 49, 247, 25);


Button writeIPInfo = new Button(shell, SWT.NONE);
writeIPInfo.addSelectionListener(new SelectionAdapter() {
	@Override
	public void widgetSelected(SelectionEvent e) {
	System.out.println("file choosed: "+ inputFile);
	
	
//	Display.getDefault().asyncExec(new Runnable() {
//		public void run(){
//			try {
//			
//				findIPLocations(inputFile);
//			} catch (IOException | InterruptedException e) {
//				e.printStackTrace();
//			}
//        }
//		});
	

new Thread(() -> {
Task copyTask = createWorker();
new Thread(copyTask).start();}
).start();


	
	}
});
writeIPInfo.setBounds(31, 156, 329, 25);
writeIPInfo.setText("WRITE IPS INFO INTO FILE");
dt.addDropListener(new DropTargetAdapter() {
public void drop(DropTargetEvent event) {
String fileList[] = null;String file="";
FileTransfer ft = FileTransfer.getInstance();
if (ft.isSupportedType(event.currentDataType)) {fileList = (String[]) event.data;}
file=fileList[0];inputFile=file;
System.out.println(file);
textArea.setText(file);
}});

	}
}
