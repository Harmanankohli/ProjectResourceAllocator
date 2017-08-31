import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class QClient extends JFrame {
	private JTextField cname;
	private JTextField cid;
	private JTextField cstatus;
	String cname1,statc,cid1;
	int cid2;
public void insert(int cid3,String statc1,String cname2) throws SQLException, ClassNotFoundException{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost/queue","root","root");
	Statement stat= con.createStatement();
	String query4="Select cid from clie where cid='"+cid3+"'";
	java.sql.ResultSet rs4=stat.executeQuery(query4);
	
	
	
	while(rs4.next()){
	if(Integer.parseInt(rs4.getString(1))==cid3){
		JOptionPane.showMessageDialog(null, "The entered Client ID exists. Please either change the Client id or check the data");
		cid.setText("");
	return;
	}	
	}
	stat.executeUpdate("Insert into clie(cid,cname,statc) values("+cid3+",'"+cname2+"','"+statc1+"'"+")");
	JOptionPane.showMessageDialog(null, "Data entered successfully");
		
    con.close();
    cname.setText("");
    cid.setText("");
    cstatus.setText("");
}
	
	
	public QClient() {
		setTitle("Client Form");
		setForeground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblClientId = new JLabel("Client ID:");
		lblClientId.setBounds(21, 77, 60, 23);
		lblClientId.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel = new JLabel("Client Name:");
		lblNewLabel.setBounds(21, 111, 79, 23);
		
		JLabel lblClientStatus = new JLabel("Client Status:");
		lblClientStatus.setBounds(21, 145, 79, 23);
		
		cname = new JTextField();
		cname.setBounds(110, 112, 229, 20);
		cname.setToolTipText("Enter Client's Name\r\n");
		cname.setHorizontalAlignment(SwingConstants.CENTER);
		cname.setColumns(10);
		
		cid = new JTextField();
		cid.setBounds(110, 78, 229, 20);
		cid.setToolTipText("Enter Client ID");
		cid.setHorizontalAlignment(SwingConstants.CENTER);
		cid.setColumns(10);
		
		cstatus = new JTextField();
		cstatus.setBounds(110, 146, 229, 20);
		cstatus.setToolTipText("Enter the status of Client(Active or Inactive)");
		cstatus.setHorizontalAlignment(SwingConstants.CENTER);
		cstatus.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setToolTipText("Insert the data into database");
		btnInsert.setBounds(45, 214, 89, 23);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cid1=cid.getText();
				cname1=cname.getText();
				statc=cstatus.getText();
				cid2=Integer.parseInt(cid1);
			try {
				insert(cid2,statc,cname1);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Reset the fields");
		btnCancel.setBounds(179, 214, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cname.setText("");
				cid.setText("");
				cstatus.setText("");
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(lblClientId);
		getContentPane().add(cid);
		getContentPane().add(lblNewLabel);
		getContentPane().add(cname);
		getContentPane().add(lblClientStatus);
		getContentPane().add(cstatus);
		getContentPane().add(btnInsert);
		getContentPane().add(btnCancel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 39, 21);
		getContentPane().add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmDashboard = new JMenuItem("Dashboard");
		mntmDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					dispose();
					Dashboard obj=new Dashboard();
				 obj.setVisible(true);
					obj.setSize(new Dimension(1000,700));
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					obj.setLocation(dim.width/2-obj.getSize().width/2, dim.height/2-obj.getSize().height/2);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		mnMenu.add(mntmDashboard);
		
		JMenuItem mntmAllocationForm = new JMenuItem("Allocation Form");
		mntmAllocationForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{	
					dispose();
					QAllocation obj1=new QAllocation();
				    obj1.setVisible(true);
					obj1.setSize(new Dimension(500,330));
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					obj1.setLocation(dim.width/2-obj1.getSize().width/2, dim.height/2-obj1.getSize().height/2);
				}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}
			}
		});
		mnMenu.add(mntmAllocationForm);
		
		JMenuItem mntmProjectForm = new JMenuItem("Project Form");
		mntmProjectForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					dispose();
					QProject obj5=new QProject();
					obj5.setVisible(true);
					obj5.setSize(new Dimension(500,330));
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					obj5.setLocation(dim.width/2-obj5.getSize().width/2, dim.height/2-obj5.getSize().height/2);
				}catch(Exception e5){
					JOptionPane.showMessageDialog(null, e5);
				}
			}
		});
		mnMenu.add(mntmProjectForm);
		
		JMenuItem mntmRsourcesForm = new JMenuItem("Resources Form");
		mntmRsourcesForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					dispose();
					QResources obj3=new QResources();
					obj3.setVisible(true);
					obj3.setSize(new Dimension(460,460));
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					obj3.setLocation(dim.width/2-obj3.getSize().width/2, dim.height/2-obj3.getSize().height/2);
				}catch(Exception e3){
					JOptionPane.showMessageDialog(null, e3);
				}
			}
		});
		mnMenu.add(mntmRsourcesForm);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setToolTipText("Exit the application");
		btnExit.setBounds(313, 214, 89, 23);
		getContentPane().add(btnExit);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
QClient o=new QClient();
o.setVisible(true);
o.setSize(new Dimension(430,330));
Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
o.setLocation(dim.width/2-o.getSize().width/2, dim.height/2-o.getSize().height/2);
	}

}
