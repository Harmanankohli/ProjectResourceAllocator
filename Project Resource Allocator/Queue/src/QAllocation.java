import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.ResultSet;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class QAllocation extends JFrame {
	private JTextField aid;
	private JTextField fd;
	private JTextField td;
	private JTextField noh;
	int aid1,noh1;
	String fd1,td1,pid1,eid1;
	
	
	public void submit(int aid2,String pid2,String eid2,String fd2,String td2,int noh2) 
	{
		
		
		try
		{
		
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/queue","root","root");
			Statement stat1= con.createStatement();
			
			
			String query3="Select eid,SUM(Hours) from allocation where eid="+eid2+" group by eid;";
			
			
			String query4="Select aid from allocation where aid='"+aid2+"'";
			java.sql.ResultSet rs4=stat1.executeQuery(query4);
			int no_of_hours=Integer.parseInt(noh.getText());
			if(no_of_hours>8){
				JOptionPane.showMessageDialog(null,"Number of hours should be less or equal to 8");
				noh.setText("");
				return;
			}
			int f = 1;
			while(rs4.next()){
			if(Integer.parseInt(rs4.getString(1))==aid2){
				JOptionPane.showMessageDialog(null, "The entered Alloacation ID exists. Please either change the allocation id or check the data");
				aid.setText("");
			return;
			}	
			
			}
			
			java.sql.ResultSet rs3= stat1.executeQuery(query3);
			while(rs3.next()){
				if(Integer.parseInt(rs3.getString(2))+noh2>8){
					
					JOptionPane.showMessageDialog(null,"number of hours exceeded");
					noh.setText("");
					f =0;
					}	
				
	
			}
			
			if(f==1){
				stat1.executeUpdate("Insert into Allocation values("+aid2+","+pid2+","+eid2+",'"+fd2+"','"+td2+"',"+noh2+")");	
		        
				JOptionPane.showMessageDialog(null, "Data submitted successfully");
				}
			
	        con.close();
	        aid.setText("");
			fd.setText("");
			td.setText("");
			noh.setText("");
		}
		catch(Exception ee)
		{
			//JOptionPane.showMessageDialog(null, 5);
			
			JOptionPane.showMessageDialog(null, ee);
		}
	}
	
	public QAllocation() throws ClassNotFoundException, SQLException {
		setTitle("Allocation Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		getContentPane().setLayout(null);
		
		JLabel lblAllocationId = new JLabel("Allocation Id:");
		lblAllocationId.setBounds(10, 29, 83, 14);
		getContentPane().add(lblAllocationId);
		
		JLabel lblProjectId = new JLabel("Project Id:");
		lblProjectId.setBounds(10, 70, 62, 14);
		getContentPane().add(lblProjectId);
		
		JLabel lblEmployeeId = new JLabel("Employee Id:");
		lblEmployeeId.setBounds(10, 109, 83, 14);
		getContentPane().add(lblEmployeeId);
		
		JLabel lblFromDate = new JLabel("From Date:");
		lblFromDate.setBounds(10, 141, 62, 14);
		getContentPane().add(lblFromDate);
		
		JLabel lblToDate = new JLabel("To Date:");
		lblToDate.setBounds(10, 179, 46, 14);
		getContentPane().add(lblToDate);
		
		JLabel lblNumberOfHours = new JLabel("Number of Hours:");
		lblNumberOfHours.setBounds(10, 204, 101, 14);
		getContentPane().add(lblNumberOfHours);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("Select the Project ID associated with Allocation ID");
		comboBox_1.setBounds(121, 67, 225, 20);
		getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setToolTipText("Select the Employee ID associated with Allocation ID");
		comboBox_2.setBounds(121, 106, 225, 20);
		getContentPane().add(comboBox_2);
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/queue","root","root");
		Statement stat= con.createStatement();
		
		String QUERY1="SELECT pid,PNAME FROM Project";
		java.sql.ResultSet rs=stat.executeQuery(QUERY1);
		while(rs.next()){
		    //comboBox.addItem(rs1.getString(1));
			comboBox_1.addItem(rs.getString(1)+"-"+rs.getString(2));
			//comboBox_2.addItem(rs1.getString(3));
			//comboBox_3.addItem(rs1.getString(4));
			//comboBox_4.addItem(rs1.getString(5));
			//comboBox_5.addItem(rs1.getString(6));	
		}
		
		String QUERY2="Select EID,ename,skillset FROM Resources"; 
		java.sql.ResultSet rs1=stat.executeQuery(QUERY2);
		while(rs1.next()){
			comboBox_2.addItem(rs1.getString(1)+"-"+rs1.getString(2)+"-"+rs1.getString(3));
			
		}
		
		JButton btnSubmit = new JButton("Insert");
		btnSubmit.setToolTipText("Insert the data into database");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aid1=Integer.parseInt(aid.getText());
				fd1=fd.getText();
				td1=td.getText();
				noh1=Integer.parseInt(noh.getText());
			    pid1=comboBox_1.getSelectedItem().toString();
			    String ss[]=pid1.split("-");
				eid1=comboBox_2.getSelectedItem().toString();
				String ss2[]=eid1.split("-");
			
			try {
					submit(aid1,ss[0],ss2[0],fd1,td1,noh1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}		
		}
				);
		btnSubmit.setBounds(37, 260, 89, 23);
		getContentPane().add(btnSubmit);
		
		aid = new JTextField();
		aid.setHorizontalAlignment(SwingConstants.CENTER);
		aid.setToolTipText("Enter the Allocation ID");
		aid.setBounds(121, 26, 225, 20);
		getContentPane().add(aid);
		aid.setColumns(10);
		
		fd = new JTextField();
		fd.setHorizontalAlignment(SwingConstants.CENTER);
		fd.setToolTipText("Enter the Starting Date of project");
		fd.setBounds(121, 138, 225, 20);
		getContentPane().add(fd);
		fd.setColumns(10);
		
		td = new JTextField();
		td.setToolTipText("Enter till date employee's have worked on Project");
		td.setHorizontalAlignment(SwingConstants.CENTER);
		td.setBounds(121, 170, 225, 20);
		getContentPane().add(td);
		td.setColumns(10);
		
		noh = new JTextField();
		noh.setHorizontalAlignment(SwingConstants.CENTER);
		noh.setToolTipText("Enter the number of hours given to the project by a Employee");
		noh.setBounds(121, 201, 225, 20);
		getContentPane().add(noh);
		noh.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Reset the fields");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aid.setText("");
				fd.setText("");
				td.setText("");
				noh.setText("");
			}
		});
		btnCancel.setBounds(163, 260, 89, 23);
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
					Dashboard obj1=new Dashboard();
					 obj1.setVisible(true);
						obj1.setSize(new Dimension(1000,700));
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						obj1.setLocation(dim.width/2-obj1.getSize().width/2, dim.height/2-obj1.getSize().height/2);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}

			}
		});
		mnMenu.add(mntmDashboard);
		
		JMenuItem mntmClientForm = new JMenuItem("Client Form");
		mntmClientForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					dispose();
					QClient obj2=new QClient();
					 obj2.setVisible(true);
						obj2.setSize(new Dimension(500,330));
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						obj2.setLocation(dim.width/2-obj2.getSize().width/2, dim.height/2-obj2.getSize().height/2);
				}catch(Exception e2){
					JOptionPane.showMessageDialog(null, e2);
				}

			}
		});
		mnMenu.add(mntmClientForm);
		
		JMenuItem mntmProjectForm = new JMenuItem("Project Form");
		mntmProjectForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					dispose();
					QProject obj3=new QProject();
					 obj3.setVisible(true);
						obj3.setSize(new Dimension(500,330));
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						obj3.setLocation(dim.width/2-obj3.getSize().width/2, dim.height/2-obj3.getSize().height/2);
				}catch(Exception e3){
					JOptionPane.showMessageDialog(null, e3);
				}

			}
		});
		mnMenu.add(mntmProjectForm);
		
		JMenuItem mntmResourcesForm = new JMenuItem("Resources Form");
		mntmResourcesForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					dispose();
					QResources obj4=new QResources();
					 obj4.setVisible(true);
						obj4.setSize(new Dimension(500,430));
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						obj4.setLocation(dim.width/2-obj4.getSize().width/2, dim.height/2-obj4.getSize().height/2);
				}catch(Exception e4){
					JOptionPane.showMessageDialog(null, e4);
				}

			}
		});
		mnMenu.add(mntmResourcesForm);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setToolTipText("Exit the application");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(289, 260, 89, 23);
		getContentPane().add(btnExit);
		
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
 QAllocation obj=new QAllocation();
 obj.setVisible(true);
	obj.setSize(new Dimension(500,330));
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	obj.setLocation(dim.width/2-obj.getSize().width/2, dim.height/2-obj.getSize().height/2);
	}
}
