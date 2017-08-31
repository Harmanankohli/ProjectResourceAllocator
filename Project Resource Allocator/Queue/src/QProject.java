import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class QProject extends JFrame {
	private JTextField tpid;
	private JTextField tpname;
	private JTextField tcid;
	private JTextField tsdate;
	private JTextField tedate;
	private JTextField tpstatus;
	String pid2,cid2,psd2,ped2;
	String statp,pname,psd,ped;
	int pid,cid;
	public void ProjectInsert(int pid1,String pname1,int cid1,String psd1,String ped1, String statp1) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/queue","root","root");
		Statement stat= con.createStatement();
		
		
		String query4="Select pid from project where pid='"+pid1+"'";
		java.sql.ResultSet rs4=stat.executeQuery(query4);
		
		while(rs4.next()){
		if(Integer.parseInt(rs4.getString(1))==pid1){
			JOptionPane.showMessageDialog(null, "The entered Project ID exists. Please either change the project id or check the data");
			tpid.setText("");
		return;
		}
		}	
		
		stat.executeUpdate("Insert into PROJECT(pid,pname,cid,psd,ped,statp) values("+pid+",'"+pname+"',"+cid+",'"+psd+"','"+ped+"',"+"'"+statp+"')");
		JOptionPane.showMessageDialog(null, "Data entered successfully");
	con.close();
	tpid.setText("");
	tpname.setText("");
	tcid.setText("");
	tsdate.setText("");
	tedate.setText("");
	tpstatus.setText("");
	}
	
public void desc() throws ClassNotFoundException, SQLException{
	pid2=tpid.getText();
	 pname=tpname.getText();
	 cid2=tcid.getText();
	 psd=tsdate.getText();
	 ped=tedate.getText();
	 statp=tpstatus.getText();
	 pid=Integer.parseInt(pid2);
	 cid=Integer.parseInt(cid2);
	
	 
	 ProjectInsert(pid,pname,cid,psd,ped,statp);
}

	
		
		
			
		
	public QProject() {
		setTitle("Project Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblProjectId = new JLabel("Project ID:");
		lblProjectId.setBounds(23, 22, 71, 22);
		getContentPane().add(lblProjectId);
		
		JLabel lblProjectName = new JLabel("Project Name:");
		lblProjectName.setBounds(23, 55, 81, 22);
		getContentPane().add(lblProjectName);
		
		JLabel lblClientId = new JLabel("Client ID:");
		lblClientId.setBounds(23, 88, 71, 22);
		getContentPane().add(lblClientId);
		
		JLabel lblStartingDate = new JLabel("Starting Date:");
		lblStartingDate.setBounds(23, 121, 81, 22);
		getContentPane().add(lblStartingDate);
		
		JLabel lblEndingDate = new JLabel("Ending Date:");
		lblEndingDate.setBounds(23, 154, 71, 22);
		getContentPane().add(lblEndingDate);
		
		JLabel lblProjectStatus = new JLabel("Project Status:");
		lblProjectStatus.setBounds(23, 187, 94, 22);
		getContentPane().add(lblProjectStatus);
		
		tpid = new JTextField();
		tpid.setToolTipText("Enter Project ID");
		tpid.setHorizontalAlignment(SwingConstants.CENTER);
		tpid.setBounds(142, 23, 219, 20);
		getContentPane().add(tpid);
		tpid.setColumns(10);
		
		tpname = new JTextField();
		tpname.setToolTipText("Enter Project's Name");
		tpname.setHorizontalAlignment(SwingConstants.CENTER);
		tpname.setBounds(142, 56, 219, 20);
		getContentPane().add(tpname);
		tpname.setColumns(10);
		
		tcid = new JTextField();
		tcid.setToolTipText("Enter the Client ID which has given this project");
		tcid.setHorizontalAlignment(SwingConstants.CENTER);
		tcid.setBounds(142, 89, 219, 20);
		getContentPane().add(tcid);
		tcid.setColumns(10);
		
		tsdate = new JTextField();
		tsdate.setToolTipText("Enter the starting date of project");
		tsdate.setHorizontalAlignment(SwingConstants.CENTER);
		tsdate.setBounds(142, 122, 219, 20);
		getContentPane().add(tsdate);
		tsdate.setColumns(10);
		
		tedate = new JTextField();
		tedate.setToolTipText("Enter the Ending/Projected Ending Date of project");
		tedate.setHorizontalAlignment(SwingConstants.CENTER);
		tedate.setBounds(142, 155, 219, 20);
		getContentPane().add(tedate);
		tedate.setColumns(10);
		
		tpstatus = new JTextField();
		tpstatus.setToolTipText("Enter the Project's status(Active or Inactive)");
		tpstatus.setHorizontalAlignment(SwingConstants.CENTER);
		tpstatus.setBounds(142, 188, 219, 20);
		getContentPane().add(tpstatus);
		tpstatus.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setToolTipText("Insert the data into database");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desc();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
		});
		btnInsert.setBounds(27, 238, 89, 23);
		getContentPane().add(btnInsert);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Reset the fields");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tpid.setText("");
				tpname.setText("");
				tcid.setText("");
				tsdate.setText("");
				tedate.setText("");
				tpstatus.setText("");
			}
		});
		btnCancel.setBounds(143, 238, 89, 23);
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
		
		JMenuItem mntmClientF = new JMenuItem("Client Form");
		mntmClientF.addActionListener(new ActionListener() {
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
		mnMenu.add(mntmClientF);
		
		JMenuItem mntmResourcesForm = new JMenuItem("Resources Form");
		mntmResourcesForm.addActionListener(new ActionListener() {
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
		mnMenu.add(mntmResourcesForm);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setToolTipText("Exit the application");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		btnExit.setBounds(259, 238, 89, 23);
		getContentPane().add(btnExit);
	
		
		}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QProject o=new QProject();
		o.setVisible(true);
		o.setSize(new Dimension(500,330));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		o.setLocation(dim.width/2-o.getSize().width/2, dim.height/2-o.getSize().height/2);
	}
	}

