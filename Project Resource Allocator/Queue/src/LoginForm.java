import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.sun.prism.Image;

import javax.swing.JPasswordField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {
	private JTextField Usernametxt;
	private JPasswordField PasswordField;
	public LoginForm() {
		setTitle("Login Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(175, 74, 85, 25);
		getContentPane().add(lblUsername);
		
		Usernametxt = new JTextField();
		Usernametxt.setToolTipText("Enter the Username");
		Usernametxt.setHorizontalAlignment(SwingConstants.CENTER);
		Usernametxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		Usernametxt.setBounds(279, 76, 236, 20);
		getContentPane().add(Usernametxt);
		Usernametxt.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(175, 130, 85, 25);
		getContentPane().add(lblPassword);
		
		PasswordField = new JPasswordField();
		PasswordField.setToolTipText("Enter the password");
		PasswordField.setFont(new Font("Tahoma", Font.BOLD, 14));
		PasswordField.setHorizontalAlignment(SwingConstants.CENTER);
		PasswordField.setBounds(279, 132, 236, 20);
		getContentPane().add(PasswordField);
		
		JButton Loginbtn = new JButton("Login");
		Loginbtn.setToolTipText("Login inside the application");
		Loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=null;
				con=DriverManager.getConnection("jdbc:mysql://localhost/queue","root","root");
				Statement stat= con.createStatement();
				String Query="Select * from login where username=? and password=? ";
				PreparedStatement pst=(PreparedStatement) con.prepareStatement(Query);
				pst.setString(1, Usernametxt.getText());
				pst.setString(2,PasswordField.getText());
				ResultSet rs=(ResultSet) pst.executeQuery();
				int count=0;
				while(rs.next()){
					count=count+1;
				}
				if(count==1){
					JOptionPane.showMessageDialog(null, "Username and password is correct");
					dispose();
					Menu obj=new Menu();
					obj.setVisible(true);
					obj.setSize(new Dimension(450,330));
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					obj.setLocation(dim.width/2-obj.getSize().width/2, dim.height/2-obj.getSize().height/2);
				}	
				else if(count>1){
					JOptionPane.showMessageDialog(null, " Duplicate Username and password");
				}
				else{
					JOptionPane.showMessageDialog(null, "Username and password is not correct. Try Again..");
				}
				con.close();
				}
			catch(Exception ee){
				JOptionPane.showMessageDialog(null, ee);
			}
			}
		});
		Loginbtn.setBounds(203, 208, 89, 25);
		getContentPane().add(Loginbtn);
		
		JButton Cancelbtn = new JButton("Cancel");
		Cancelbtn.setToolTipText("Reset the fields");
		Cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usernametxt.setText("");
				PasswordField.setText("");
			}
		});
		Cancelbtn.setBounds(313, 208, 89, 23);
		getContentPane().add(Cancelbtn);
		
		JButton Exitbtn = new JButton("Exit");
		Exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Exitbtn.setToolTipText("Exit the application");
		Exitbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		Exitbtn.setBounds(426, 208, 89, 23);
		getContentPane().add(Exitbtn);
		
		JLabel lbl1 = new JLabel("");
		java.awt.Image img = new ImageIcon(this.getClass().getResource("/login-icon.png")).getImage();
		lbl1.setIcon(new ImageIcon(img));
		lbl1.setBounds(26, 30, 139, 203);
		getContentPane().add(lbl1);
	}

	public static void main(String[] args) {
		LoginForm obj1=new LoginForm();
		 obj1.setVisible(true);
			obj1.setSize(new Dimension(700,300));
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			obj1.setLocation(dim.width/2-obj1.getSize().width/2, dim.height/2-obj1.getSize().height/2);

	}
}
