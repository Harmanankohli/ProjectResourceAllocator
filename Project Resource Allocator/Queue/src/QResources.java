import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class QResources extends JFrame {
	private JTextField eid;
	private JTextField ename;
	private JTextField dob;
	private JTextField doj;
	private JTextField designation;
	private JTextField dept;
	private JTextField country;
	private JTextField email;
	private JTextField cnumber;
	private JTextField rstatus;
	private JTextField Skillsettxt;
	
	public void insert(int eid2, String ename2, String dob2, String doj2, String designation2, String dept2, String country2,String email2,String cnumber2, String rstatus2,String skill2) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/queue","root","root");
		Statement stat= con.createStatement();
		String query4="Select eid from resources where eid='"+eid2+"'";
		java.sql.ResultSet rs4=stat.executeQuery(query4);
		
		
		
		int f = 1;
		while(rs4.next()){
		if(Integer.parseInt(rs4.getString(1))==eid2){
			JOptionPane.showMessageDialog(null, "The entered Employee ID exists. Please either change the Employee id or check the data");
			eid.setText("");
		return;
		}	
		}
		stat.executeUpdate("Insert into resources values("+eid2+",'"+ename2+"','"+dob2+"','"+doj2+"','"+designation2+"','"+dept2+"','"+country2+"','"+email2+"','"+cnumber2+"','"+rstatus2+"','"+skill2+"')");
		JOptionPane.showMessageDialog(null, "Data entered successfully");
			
		
	con.close();
	eid.setText("");
	ename.setText("");
	dob.setText("");
	doj.setText("");
	designation.setText("");
	dept.setText("");
	country.setText("");
	email.setText("");
	cnumber.setText("");
	rstatus.setText("");
	Skillsettxt.setText("");
	}
	public QResources() {
		setTitle("Resources Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("Employee ID:");
		lblEmployeeId.setBounds(10, 33, 72, 14);
		getContentPane().add(lblEmployeeId);
		
		JLabel lblEmployeeName = new JLabel("Employee Name:");
		lblEmployeeName.setBounds(10, 58, 100, 14);
		getContentPane().add(lblEmployeeName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(10, 83, 100, 14);
		getContentPane().add(lblDateOfBirth);
		
		JLabel lblDateOfJoining = new JLabel("Date of Joining:");
		lblDateOfJoining.setBounds(10, 108, 100, 14);
		getContentPane().add(lblDateOfJoining);
		
		JLabel lblDesignation = new JLabel("Designation:");
		lblDesignation.setBounds(10, 133, 72, 14);
		getContentPane().add(lblDesignation);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setBounds(10, 191, 72, 14);
		getContentPane().add(lblDepartment);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setBounds(10, 216, 72, 14);
		getContentPane().add(lblCountry);
		
		JLabel lblEmailId = new JLabel("Email ID:");
		lblEmailId.setBounds(10, 241, 58, 14);
		getContentPane().add(lblEmailId);
		
		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setBounds(10, 266, 100, 14);
		getContentPane().add(lblContactNumber);
		
		JLabel lblResourceStatus = new JLabel("Resource Status:");
		lblResourceStatus.setBounds(10, 291, 100, 14);
		getContentPane().add(lblResourceStatus);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setToolTipText("Insert the data in database");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int eid1=Integer.parseInt(eid.getText());
				String ename1=ename.getText();
				String dob1=dob.getText();
				String doj1=doj.getText();
				String designation1=designation.getText();
				String dept1=dept.getText();
				String country1=country.getText();
				String email1=email.getText();
				String cnumber1=cnumber.getText();
				String rstatus1=rstatus.getText();
				String skill1=Skillsettxt.getText();
				
				try {
					insert(eid1,ename1,dob1,doj1,designation1,dept1,country1,email1,cnumber1,rstatus1,skill1);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnInsert.setBounds(32, 319, 89, 23);
		getContentPane().add(btnInsert);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Reset each field");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eid.setText("");
				ename.setText("");
				dob.setText("");
				doj.getText();
				designation.setText("");
				dept.setText("");
				country.setText("");
				email.setText("");
				cnumber.setText("");
				rstatus.setText("");
				Skillsettxt.setText("");
			}
		});
		btnCancel.setBounds(153, 319, 89, 23);
		getContentPane().add(btnCancel);
		
		eid = new JTextField();
		eid.setToolTipText("Enter Employee ID");
		eid.setHorizontalAlignment(SwingConstants.CENTER);
		eid.setBounds(105, 30, 263, 20);
		getContentPane().add(eid);
		eid.setColumns(10);
		
		ename = new JTextField();
		ename.setToolTipText("Enter Employee's Name\r\n");
		ename.setHorizontalAlignment(SwingConstants.CENTER);
		ename.setBounds(105, 55, 263, 20);
		getContentPane().add(ename);
		ename.setColumns(10);
		
		dob = new JTextField();
		dob.setToolTipText("Enter Employee's Date of Birth");
		dob.setHorizontalAlignment(SwingConstants.CENTER);
		dob.setBounds(105, 80, 263, 20);
		getContentPane().add(dob);
		dob.setColumns(10);
		
		doj = new JTextField();
		doj.setToolTipText("Enter Employee's Date of Joining");
		doj.setHorizontalAlignment(SwingConstants.CENTER);
		doj.setBounds(105, 105, 263, 20);
		getContentPane().add(doj);
		doj.setColumns(10);
		
		designation = new JTextField();
		designation.setToolTipText("Enter the role of Employee in the company");
		designation.setHorizontalAlignment(SwingConstants.CENTER);
		designation.setBounds(105, 130, 263, 20);
		getContentPane().add(designation);
		designation.setColumns(10);
		
		dept = new JTextField();
		dept.setToolTipText("Enter the department of Employee");
		dept.setHorizontalAlignment(SwingConstants.CENTER);
		dept.setBounds(105, 188, 263, 20);
		getContentPane().add(dept);
		dept.setColumns(10);
		
		country = new JTextField();
		country.setToolTipText("Enter the Employee's birth country");
		country.setHorizontalAlignment(SwingConstants.CENTER);
		country.setBounds(105, 213, 263, 20);
		getContentPane().add(country);
		country.setColumns(10);
		
		email = new JTextField();
		email.setToolTipText("Enter the Employee's E-mail ID");
		email.setHorizontalAlignment(SwingConstants.CENTER);
		email.setBounds(105, 238, 263, 20);
		getContentPane().add(email);
		email.setColumns(10);
		
		cnumber = new JTextField();
		cnumber.setToolTipText("Enter the Employee's mobile number");
		cnumber.setHorizontalAlignment(SwingConstants.CENTER);
		cnumber.setBounds(105, 263, 263, 20);
		getContentPane().add(cnumber);
		cnumber.setColumns(10);
		
		rstatus = new JTextField();
		rstatus.setToolTipText("Enter the Employee's status(Active or Inactive)");
		rstatus.setHorizontalAlignment(SwingConstants.CENTER);
		rstatus.setBounds(105, 288, 263, 20);
		getContentPane().add(rstatus);
		rstatus.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 39, 25);
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
		
		JMenuItem mntmClientForm = new JMenuItem("Client Form");
		mntmClientForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
          try{
        	  dispose();
	            QClient obj4=new QClient();
					obj4.setVisible(true);
					obj4.setSize(new Dimension(430,330));
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					obj4.setLocation(dim.width/2-obj4.getSize().width/2, dim.height/2-obj4.getSize().height/2);
					}catch(Exception e4){
						JOptionPane.showMessageDialog(null, e4);
					}
			}
		});
		mnMenu.add(mntmClientForm);
		
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
		
		JButton btnExit = new JButton("Exit");
		btnExit.setToolTipText("Exit the application");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		btnExit.setBounds(279, 319, 89, 23);
		getContentPane().add(btnExit);
		
		JLabel lblSkillset = new JLabel("Skillset:");
		lblSkillset.setBounds(10, 158, 58, 23);
		getContentPane().add(lblSkillset);
		
		Skillsettxt = new JTextField();
		Skillsettxt.setToolTipText("Enter the skillset of the employee");
		Skillsettxt.setHorizontalAlignment(SwingConstants.CENTER);
		Skillsettxt.setBounds(105, 157, 263, 20);
		getContentPane().add(Skillsettxt);
		Skillsettxt.setColumns(10);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QResources oo=new QResources();
		oo.setVisible(true);
		oo.setSize(new Dimension(460,460));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		oo.setLocation(dim.width/2-oo.getSize().width/2, dim.height/2-oo.getSize().height/2);
	}
}
