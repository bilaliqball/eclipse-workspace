
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JWindow;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import javafx.scene.text.Text;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JTextField;
import java.awt.Color;

public class Dialog {

	public static JWindow messageDialogWindow;
	public static JWindow confirmDialogWindow;
	public static JWindow loadingDialogWindow;
	public static JWindow scanningDialogWindow;
	public static JWindow passwordDialogWindow;
	public static JWindow shareablelinkWindow;
	public static JWindow linkshareWindow;
	public static JLabel uploadInfoLabel;
	public static JLabel hotspotNamelabel;
	public static JLabel hotspotPasswordLabel;
	public static JLabel messageLabel;
	public static JButton messageIcon;
	public static JButton closeButton;
	public static JLabel scanningInfoLabel;
	public static String status="waiting"; 
	public static boolean receive=true;


	public static JLabel infolabel;


	public static void showloadingWindow(String message) {
		loadingDialogWindow=new JWindow();
		loadingDialogWindow.setSize(400, 120);
		loadingDialogWindow.getContentPane().setBackground(java.awt.Color.WHITE);
		loadingDialogWindow.getContentPane().setLayout(null);
		loadingDialogWindow.setOpacity(0.2f);
		loadingDialogWindow.setShape(new RoundRectangle2D.Double(0, 0, 400, 120, 20, 20));
		//loadingDialogWindow.setBackground(new Color(0, 0, 0, 180));
		//loadingDialogWindow.setBackground(new Color(19,69,122,1));

		messageIcon = new JButton("");
		messageIcon.setIcon(new ImageIcon("src/trango/img/loading1.gif"));
		messageIcon.setBounds(10, 11, 63, 63);
		messageIcon.setOpaque(false);
		messageIcon.setContentAreaFilled(false);
		messageIcon.setBorderPainted(false);
		loadingDialogWindow.getContentPane().add(messageIcon);
		infolabel = new JLabel(message,SwingConstants.CENTER);
		infolabel.setForeground(Color.WHITE);
		infolabel.setBounds(20, 86, 370, 23);
		infolabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		loadingDialogWindow.getContentPane().add(infolabel);
		JLabel lblNewLabel = new JLabel("Please Wait",SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(178, 106, 71, 14);
		loadingDialogWindow.getContentPane().add(lblNewLabel);
		JButton closebutton = new JButton("");
		closebutton.setBounds(377, 0, 23, 23);
		closebutton.setIcon(new ImageIcon("src/trango/img/Cross.png"));
		closebutton.setOpaque(false);
		closebutton.setContentAreaFilled(false);
		closebutton.setBorderPainted(false);
		closebutton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {closeloadingWindow();}});
		loadingDialogWindow.getContentPane().add(closebutton);
		loadingDialogWindow.setVisible(true);
		loadingDialogWindow.setAlwaysOnTop(true);
		loadingDialogWindow.setLocationRelativeTo(null);}
		public static void closeloadingWindow(){loadingDialogWindow.setVisible(false);loadingDialogWindow.dispose();}
		public  static void updateInfo(String info) {infolabel.setText(info);}



	
	
public static void main(String args[]) throws InterruptedException {
	showloadingWindow("hi");



}
}
