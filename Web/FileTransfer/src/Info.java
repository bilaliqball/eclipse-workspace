
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JWindow;
import javax.swing.text.DefaultCaret;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JScrollBar;

public class Info {
	public JWindow infoWindow;
	//public JTextArea textArea;	
	public JTextArea textArea;	
	public JScrollPane scrollPane;
	
	public static String whatIsTrango="What is it?\r\n" + 
			"trango is an encrypted, real-time and direct file transfer service. From your browser to another, trango moves files with speed, privacy and precision.";
	public static String howItsWork="How it works?\r\n" + 
			"The sender and receiver need to be online when the file transfer is taking place. Either select OR drag and drop a file to create a link. Then share that link with anyone.\n"
			+ " The transfer begins as soon as the receiver opens the link. Once the file has been received by the browser, the sender may close their browser.\r\n" + 
			"\r\n" + 
			"Please keep browser open until transfer is complete!\r\n" + 
			"Select any file\r\n" + 
			"A link will be generated\r\n" + 
			"Copy the link\r\n" + 
			"Send link to Recipient\r\n" + 
			"Transfer will begin once recipient has opened the link";
	
	public static String privacyPolicy="What information do we collect?\r\n" + 
			"We collect information from you when you register for our service. When registering for our service, you may be asked to enter your e-mail address\n. "
			+ "You may, however, visit our website anonymously.\r\n" + 
			"\r\n" + 
			"What do we use your information for?\r\n" + 
			"Any of the information we collect from you may be used in one of the following ways:\r\n" + 
			"\r\n" + 
			"To authenticate your use. So we know it is you who is using the service.\r\n" + 
			"To improve our service by routing your transfer through the fastest possible channel.\r\n" + 
			"To send periodic emails\r\n" + 
			"If you subscribe to our [push notification/newsletter], your email address may be used to send you occasional company news and updates related to product or service information, etc.\n "
			+ "Note: If at any time you would like to unsubscribe from receiving future emails, we include detailed unsubscribe instructions at the bottom of each email.\r\n" + 
			"How do we protect your information?\r\n" + 
			"We implement a variety of security measures to maintain the safety of your personal information when you enter, submit, or access your personal information.\n"
			+ " Your data stays with us in encrypted form.\r\n" + 
			"\r\n" + 
			"Where do we transfer your data files?\r\n" + 
			"We do not store your data files on any server once the file has left our servers to the recipient. Once the file is transferred from one device to another,\n"
			+ " no other copy of data files exists except in the devices involved for transfer.\r\n" + 
			"\r\n" + 
			"Where do we transfer your data?\r\n" + 
			"During the process of transfer of file from one device to another, relay server temporarily store data packets till the transfer of data files is complete\r\n" + 
			"\r\n" + 
			"How do we protect your data?\r\n" + 
			"We do not store your data on any server. We give end to end encryption for data file transfer when used with our Desktop or Mobile App.\r\n" + 
			"\r\n" + 
			"Do we retain any other information?\r\n" + 
			"Our product (only desktop and mobile app) stores your transfer history and contact list in your own devices. Whenever a user comes online, transfer history and contact list is\n"
			+ " relayed through our server so this information gets synchronised and updated in other devices on which you are using our Desktop or Mobile application.\r\n" + 
			"\r\n" + 
			"Do we use cookies?\r\n" + 
			"We don't save cookies for preferences. We only use cookies to identify the region from which you are sending your file. This is only to offer you the fastest file transfer.\n"
			+ " It uses the identification of your region to establish the best route for sending the data files\r\n" + 
			"\r\n" + 
			"Do we disclose any information to outside parties?\r\n" + 
			"We do not sell, trade, or otherwise transfer to outside parties your personally identifiable information. We may also release your information when we believe release is appropriate\n"
			+ " to comply with the law, enforce our site policies, or protect ours or others rights, property, or safety.\r\n" + 
			"\r\n" + 
			"Third party links\r\n" + 
			"We wont be serving any third party links.\r\n" + 
			"\r\n" + 
			"Terms and Conditions\r\n" + 
			"Please also visit our Terms of Service section establishing the use, disclaimers, and limitations of liability governing the use of our services.\r\n" + 
			"\r\n" + 
			"Your Consent\r\n" + 
			"By using our service, you consent to our privacy policy.\r\n" + 
			"\r\n" + 
			"Changes to our Privacy Policy\r\n" + 
			"If we decide to change our privacy policy, we will update the Privacy Policy modification date below. We encourage you to review this Privacy Policy from time to time to stay informed\n"
			+ " about how we are protecting the personally identifiable information we collect. If you continue to use our Service after we post an update to this Privacy Policy, this will indicate\n"
			+ "your acceptance of the update.\r\n" + 
			"\r\n" + 
			"trango.io\r\n" + 
			"file transfer made easy\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"Max Limit on Browser is 5 GB\r\n" + 
			"      \r\n" + 
			"\r\n" + 
			"© Copyright 2019. Bellstone Pvt Ltd.\r\n" + 
			"\r\n" + 
			"";
	
	public static String termAndConditions="The following terms and conditions govern all use of the Trango in website and the related mobile/desktop app (collectively, the “Service”).\n"
			+ " Trango is owned and operated by Bell Stones Pvt. Ltd and the our Services are offered subject to your acceptance without modification of all of the terms and conditions contained\n"
			+ " herein and all other operating rules, policies (including, without limitation, our Privacy Policy) and procedures that may be published from time to time on this site by Trango \n"
			+ "(collectively, the “Agreement”). Please read this Agreement carefully before accessing or using the Service. By accessing or using any part of the web site, you agree to become bound\n"
			+ " by the terms and conditions of this agreement. If you do not agree to all the terms and conditions of this agreement, then you may not access the Service or use any Services.\n"
			+ " If these terms and conditions are considered an offer by Trango such, acceptance is expressly limited to these terms. The Service is available only to individuals who are at least 13 years old.\r\n" + 
			"\r\n" + 
			"Your Permissions and Your Stuff:\r\n" + 
			"If you create an Account on the Service, you are responsible for maintaining the security of your account, and you are fully responsible for all activities that occur under the account\n"
			+ " and any other actions taken in connection with the account. You must immediately notify Trango of any unauthorized uses of your account or any other breaches of security.\n"
			+ " Trango will not be liable for any acts or omissions by You, including any damages of any kind incurred as a result of such acts or omissions. When you use our Services, \n"
			+ "you transfer your files (\"Your Files\") with complete privacy and security. Your files are yours and we protect your files by use of our Services. These Terms don't give us any\n"
			+ " rights to Your Files.\r\n" + 
			"\r\n" + 
			"Responsibility of Website Visitors:\r\n" + 
			"Trango has not reviewed, and cannot review, all of the material, including computer software, posted to the Service, and cannot therefore be responsible for that material’s content,\n"
			+ " use or effects. By operating with our Service, Trango does not represent or imply that it endorses the material there shared, or that it believes such material to be accurate,\n"
			+ " useful or non-harmful. You are responsible for taking precautions as necessary to protect yourself and your computer systems from viruses, worms, Trojan horses, and other harmful or\n"
			+ "destructive content. The content being shared may contain content that is offensive, indecent, or otherwise objectionable, as well as content containing technical inaccuracies,\n"
			+ " typographical mistakes, and other errors. The Content being shared may also contain material that violates the privacy or publicity rights, or infringes the intellectual property\n"
			+ " and other proprietary rights, of third parties, or the downloading, copying or use of which is subject to additional terms and conditions, stated or unstated.\n"
			+ " Trango disclaims any responsibility for any harm resulting from the use by visitors of the Service, or from any downloading by those visitors of content there posted.\r\n" + 
			"\r\n" + 
			"Privacy Protection:\r\n" + 
			"Trango’s Privacy Policy explains how we treat your personal data and protect your privacy when you use our services.\r\n" + 
			"\r\n" + 
			"Sharing Your Files:\r\n" + 
			"Our Services let you share Your Files with others, you don’t have to care what you share, your transfer of files will be fully secure and encrypted*.\r\n" + 
			"*Encryptions are limited to controls of website browser, otherwise our applications offer full end to end encryption.\r\n" + 
			"\r\n" + 
			"Use of technology for Transfer of Data:\r\n" + 
			"Trango use four different ways for transfer of a data file\r\n" + 
			"\r\n" + 
			"LAN\r\n" + 
			"WLAN\r\n" + 
			"Hotspot\r\n" + 
			"Online\r\n" + 
			"The website only provides the online Service whereas the desktop and mobile apps provide all 4 methods. The first three methods are completely offline and do not require the transfer or storage of any data outside the internal network of the user.\r\n" + 
			"Automatic Updates:\r\n" + 
			"Some of our Products/Services allow you to download our software (\"Software\") which may update automatically.\r\n" + 
			"\r\n" + 
			"Modifications to Trango:\r\n" + 
			"We are constantly changing and improving Trango. We may make performance or security improvements, change functionalities or features, or make changes to comply with law or to prevent illegal activities on, or abuse of, our system.\r\n" + 
			"\r\n" + 
			"Suspension and Termination:\r\n" + 
			"Trango may terminate your access to all or any part of the Website at any time, with or without cause, with or without notice, effective immediately. If you wish to terminate this Agreement or your Trango account (if you have one), you may simply discontinue using the Service. All provisions of this Agreement which by their nature should survive termination shall survive termination, including, without limitation, ownership provisions, warranty disclaimers, indemnity and limitations of liability.\r\n" + 
			"\r\n" + 
			"Disclaimer of Warranties:\r\n" + 
			"The Service is provided “as is”. Trango and its suppliers and licensors hereby disclaim all warranties of any kind, express or implied, including, without limitation, the warranties of merchantability, fitness for a particular purpose and non-infringement. Neither Trango nor its suppliers and licensors, makes any warranty that the Service will be error free or that access thereto will be continuous or uninterrupted. You understand that you download from, or otherwise obtain content or Services through, the Service at your own discretion and risk.\r\n" + 
			"\r\n" + 
			"Limitation of Liability:\r\n" + 
			"In no event will Trango, or its suppliers or licensors, be liable with respect to any subject matter of this agreement under any contract, negligence, strict liability or other legal or equitable theory for: (i) any special, incidental or consequential damages; (ii) the cost of procurement for substitute products or services; (iii) for interruption of use or loss or corruption of data; or (iv) for any amounts that exceed the fees paid by you to Trango under this agreement during the twelve (12) month period prior to the cause of action. Trango shall have no liability for any failure or delay due to matters beyond their reasonable control. The foregoing shall not apply to the extent prohibited by applicable law\r\n" + 
			"\r\n" + 
			"Indemnification:\r\n" + 
			"You agree to indemnify and hold harmless Trango, its contractors, and its licensors, and their respective directors, officers, employees and agents from and against any and all claims and expenses, including attorneys’ fees, arising out of your use of the Service, including but not limited to your violation of this Agreement.\r\n" + 
			"\r\n" + 
			"Intellectual Property and Copyrights:\r\n" + 
			"This Agreement does not transfer from Trango to you any Trango or third-party intellectual property, and all right, title and interest in and to such property will remain (as between the parties) solely with Trango logo, and all other trademarks, service marks, graphics and logos used in connection with Trango in, or the Service are trademarks or registered trademarks of BellStone Pvt. Ltd. Other trademarks, service marks, graphics and logos used in connection with the Service may be the trademarks of other third parties. Your use of the Service grants you no right or license to reproduce or otherwise use any BellStone Pvt. Ltd or third-party trademarks.\r\n" + 
			"\r\n" + 
			"Discontinuation of Services:\r\n" + 
			"We may decide to discontinue the Services in response to unforeseen circumstances beyond our control or to comply with a legal requirement. If we do so, we'll give you reasonable prior notice.\r\n" + 
			"\r\n" + 
			"Entire Agreement:\r\n" + 
			"These Terms constitute the entire agreement between you and Trango with respect to the subject matter of these Terms.";
	
public void getInfo() {

initshareasWindow();
showshareasWindow();}
	
	

public void initshareasWindow() {
infoWindow=new JWindow();
infoWindow.setSize(1000, 500);
infoWindow.getContentPane().setBackground(java.awt.Color.WHITE);
infoWindow.getContentPane().setLayout(null);



JButton whatIsTrangoButton = new JButton("What is Trango?");
whatIsTrangoButton.setToolTipText("What is Trango?");
//whatIsTrangoButton.setIcon(new ImageIcon("C:\\Users\\bilal.iqbal\\ico\\onedrive.png"));
whatIsTrangoButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
textArea.setText(whatIsTrango);
}});
whatIsTrangoButton.setBounds(3, 43, 200, 50);
whatIsTrangoButton.setOpaque(false);
whatIsTrangoButton.setContentAreaFilled(false);
whatIsTrangoButton.setBorderPainted(false);
infoWindow.getContentPane().add(whatIsTrangoButton);



JButton howItsWorkButton = new JButton("How its work?");
howItsWorkButton.setToolTipText("How Trango works?");
//howItsWorkButton.setIcon(new ImageIcon("C:\\Users\\bilal.iqbal\\ico\\gdrive.png"));
howItsWorkButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent arg0) {
	textArea.setText(howItsWork);
}});
howItsWorkButton.setBounds(331, 43, 200, 50);
howItsWorkButton.setOpaque(false);
howItsWorkButton.setContentAreaFilled(false);
howItsWorkButton.setBorderPainted(false);
infoWindow.getContentPane().add(howItsWorkButton);



JButton privacyPolicyButton = new JButton("Privacy Policy");
privacyPolicyButton.setToolTipText("Privacy Policy");
//privacyPolicyButton.setIcon(new ImageIcon("ico\\outlook.png"));
privacyPolicyButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
	textArea.setText(privacyPolicy);
}});
privacyPolicyButton.setBounds(582, 43, 200, 50);
privacyPolicyButton.setOpaque(false);
privacyPolicyButton.setContentAreaFilled(false);
privacyPolicyButton.setBorderPainted(false);
infoWindow.getContentPane().add(privacyPolicyButton);



JButton termAndConditionButton = new JButton("Term and conditions");
termAndConditionButton.setToolTipText("Term and Conditions");
//termAndConditionButton.setIcon(new ImageIcon("ico\\gmail.png"));
termAndConditionButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
textArea.setText(termAndConditions);
}});
termAndConditionButton.setBounds(761, 43, 200, 50);
termAndConditionButton.setOpaque(false);
termAndConditionButton.setContentAreaFilled(false);
termAndConditionButton.setBorderPainted(false);
infoWindow.getContentPane().add(termAndConditionButton);








JButton closeButton = new JButton("");
closeButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {

closeshareasWindow();}});
closeButton.setIcon(new ImageIcon("ico\\close.png"));
closeButton.setBounds(970, 0, 20, 23);
closeButton.setOpaque(false);
closeButton.setContentAreaFilled(false);
closeButton.setBorderPainted(false);
infoWindow.getContentPane().add(closeButton);

textArea = new JTextArea();
textArea.setBounds(55, 129, 922, 297);
textArea.setEditable(false);
//scrollPane = new JScrollPane(textArea);
infoWindow.getContentPane().add(textArea);
}



public void showshareasWindow() throws HeadlessException {
infoWindow.setVisible(true);
infoWindow.setAlwaysOnTop(true);
infoWindow.setLocationRelativeTo(null);}
public void closeshareasWindow() {infoWindow.setVisible(false);infoWindow.dispose();}

	
public static void main(String args[]) {
new Info().getInfo();}
}
