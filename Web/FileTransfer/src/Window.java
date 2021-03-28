import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;


public class Window {


//public JWindow window;
//public JLabel label;
//public void initWindow() {window=new JWindow();
//String gif=Resources.getIcon("loading5.gif");
//ImageIcon icon=	new ImageIcon(gif);
//window.getContentPane().add(new JLabel("Processing", icon, SwingConstants.CENTER));
//window.setBounds(500, 200, 200, 200);}
//public void showWindow() {
//window.setVisible(true);
//window.setAlwaysOnTop(true);
//window.toFront();
//window.requestFocus();
//window.setAlwaysOnTop(false);
//window.setLocationRelativeTo(null);}
//public void closeWindow() {window.setVisible(false);window.dispose();}


public JWindow window;
public JLabel label;

//public void initWindow() {
//window=new JWindow();
//window.setSize(400, 200);
//String gif=Resources.getIcon("compress.gif");
//Icon icon = new ImageIcon(gif);
//label = new JLabel("", icon, JLabel.LEFT);window.getContentPane().add(label);
//window.getContentPane().setBackground(java.awt.Color.WHITE);
//}
//public void showWindow(int progress) throws HeadlessException {
//label.setText("Processed:"+progress);
//window.setVisible(true);
//window.setAlwaysOnTop(true);
////window.toFront();
////window.requestFocus();
////window.setAlwaysOnTop(false);
//window.setLocationRelativeTo(null);}
//public void closeWindow() {window.setVisible(false);window.dispose();}






public JProgressBar bar; 
public void initWindow() {
window=new JWindow();
window.getContentPane().setForeground(Color.WHITE);
JLabel label=new JLabel("PROCESSING");
label.setForeground(Color.WHITE);
label.setText("Processing:");
label.setBounds(207, 63, 100, 20);
bar=new JProgressBar(0,100);    
bar.setForeground(SystemColor.activeCaption);
bar.setBounds(10,22,480,30);         
bar.setValue(0);    
bar.setStringPainted(true);    
window.getContentPane().add(bar);   window.getContentPane().add(label); 
window.setSize(500,100);    
window.getContentPane().setLayout(null); 
window.getContentPane().setBackground(java.awt.Color.DARK_GRAY);}
public void showWindow(int i){
window.setVisible(true);
window.setAlwaysOnTop(true);
//window.toFront();
//window.requestFocus();
//window.setAlwaysOnTop(false);
window.setLocationRelativeTo(null);  bar.setValue(i); }    
public void closeWindow() {window.setVisible(false);window.dispose();}
	

//public ProgressBar bar; 
//public void initWindow() {
//window=new JWindow();
//window.getContentPane().setForeground(Color.WHITE);
//JLabel label=new JLabel("PROCESSING");
//label.setForeground(Color.WHITE);
//label.setText("Processing:");
//label.setBounds(207, 63, 100, 20);
//bar=new ProgressBar(shell, SWT.NULL); 
//bar.setBounds(10,22,480,30);         
//bar.setMinimum(0);
//bar.setMaximum(100);
//bar.setSelection(10);
//
//   
//window.getContentPane().add(bar);   
//window.getContentPane().add(label); 
//window.setSize(500,100);    
//window.getContentPane().setLayout(null); 
//window.getContentPane().setBackground(java.awt.Color.DARK_GRAY);}
//public void showWindow(int i){
//window.setVisible(true);
//window.setAlwaysOnTop(true);
////window.toFront();
////window.requestFocus();
////window.setAlwaysOnTop(false);
//window.setLocationRelativeTo(null);  bar.setValue(i); }    
//public void closeWindow() {window.setVisible(false);window.dispose();}

	
	
	
protected Shell shell;
public Display display;

public static void main(String[] args) throws IOException {
Window window = new Window();//window.init();

window.initWindow();
for (int i=0; i<=100; i++) {try {Thread.sleep (100);} catch (Throwable th) {}
window.showWindow(i);}window.closeWindow();

//try {window.open();}catch (Exception e) {e.printStackTrace();}

}

public int confirmClose(){
String mes="Are you sure you Want to close window?";
JFrame jf=new JFrame();
jf.setAlwaysOnTop(true);
int response = JOptionPane.showConfirmDialog(jf,mes, "Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
return response;}




public void open() {
display = Display.getDefault();
shell = new Shell();
shell.addShellListener(new ShellAdapter() {
@Override
public void shellClosed(ShellEvent e) {
e.doit=false;
int n=confirmClose();
if(n==0) {e.doit=true;System.exit(0);}}});

shell.setSize(457, 350);
shell.setText("SWT Application");

//org.eclipse.swt.graphics.Color color=display.getSystemColor(SWT.COLOR_BLUE);
//org.eclipse.swt.graphics.Image image = new Image(display, "C:\\Users\\bilal.iqbal\\Pictures\\FILES\\Pictures\\code.jpg");
//org.eclipse.swt.graphics.Color color_=Resources.getColor(9);
//org.eclipse.swt.graphics.Image image_=Resources.getImage("C:\\Users\\bilal.iqbal\\Pictures\\FILES\\Pictures\\code.jpg");

//shell.setBackground(color);
//shell.setBackgroundImage(image);
//shell.setBackground(color_);
//shell.setBackgroundImage(image_);
Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
int x = (int) ((dimension.getWidth() - 700) / 2);
int y = (int) ((dimension.getHeight() - 480) / 2);
shell.setLocation(x, y);

createContents();
shell.open();shell.layout();
while (!shell.isDisposed()) {
//shell.open();shell.layout();
if (!display.readAndDispatch()) {display.sleep();}
}}


protected void createContents() {
Button btnNewButton = new Button(shell, SWT.NONE);
//btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
//btnNewButton.setBackgroundImage(SWTResourceManager.getImage("C:\\Users\\bilal.iqbal\\Pictures\\FILES\\Pictures\\test.jpg"));
//btnNewButton.setImage(SWTResourceManager.getImage("C:\\Users\\bilal.iqbal\\Pictures\\FILES\\Pictures\\flowers.jpg"));

btnNewButton.addSelectionListener(new SelectionAdapter() {
@Override
public void widgetSelected(SelectionEvent e) {
//btnNewButton.setImage(SWTResourceManager.getImage("C:\\Users\\bilal.iqbal\\fileCompression\\res\\loading4.gif"));
 org.eclipse.swt.graphics.Image image = new Image(display, "C:\\Users\\bilal.iqbal\\Pictures\\FILES\\Pictures\\code.jpg");
 btnNewButton.setImage(image);
 
initWindow();
for (int i=0; i<=100; i++) {try {Thread.sleep (100);} catch (Throwable th) {}
showWindow(i);}closeWindow();
 
}});
//btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
btnNewButton.setBounds(10, 77, 167, 58);
btnNewButton.setText("New Button");
		
	}
}
