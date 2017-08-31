import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class Dashboard extends JFrame {
	private JTextField from_date;
	private JTextField to_date;
	private JTable table_1;
	private JTable table;
	private JTable table_2;
	@SuppressWarnings("unchecked")
	public Dashboard() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Dashboard");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee ID:");
		lblNewLabel.setBounds(78, 61, 79, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Project ID:");
		lblNewLabel_1.setBounds(78, 106, 70, 14);
		getContentPane().add(lblNewLabel_1);
		
		
		JLabel fd1 = new JLabel("From Date:");
		fd1.setBounds(78, 150, 70, 14);
		getContentPane().add(fd1);
		
		JLabel lblNewLabel_3 = new JLabel("To Date:");
		lblNewLabel_3.setBounds(78, 199, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Select Employee ID");
		comboBox.setBounds(158, 58, 258, 20);
		getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("Select Project ID");
		comboBox_1.setBounds(158, 103, 258, 20);
		getContentPane().add(comboBox_1);
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/queue","root","root");
		Statement stat= con.createStatement();
		comboBox.addItem("Choose one...");
		String query1="Select EID,ename FROM Resources";
		ResultSet rs1=stat.executeQuery(query1);
		while(rs1.next()){
			comboBox.addItem(rs1.getString(1)+"-"+rs1.getString(2));
			}
		comboBox.addItem("All");
		String query="SELECT pid,PNAME FROM Project";
		comboBox_1.addItem("Choose one...");
		ResultSet rs=stat.executeQuery(query);
		while(rs.next()){
			comboBox_1.addItem(rs.getString(1)+"-"+rs.getString(2));
		}
		comboBox_1.addItem("All");
		from_date = new JTextField();
		from_date.setToolTipText("Enter Start date of Employee on Project");
		from_date.setHorizontalAlignment(SwingConstants.CENTER);
		from_date.setBounds(158, 147, 258, 20);
		getContentPane().add(from_date);
		from_date.setColumns(10);
		
		to_date = new JTextField();
		to_date.setToolTipText("Enter End date of Employee on Project");
		to_date.setHorizontalAlignment(SwingConstants.CENTER);
		to_date.setBounds(158, 196, 258, 20);
		getContentPane().add(to_date);
		to_date.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setToolTipText("Search on the basis of from date and to date");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost/queue","root","root");
					Statement stat= con.createStatement();
					String eid=comboBox.getSelectedItem().toString();
					String pid=comboBox_1.getSelectedItem().toString();
					String fd=from_date.getText();
					String td=to_date.getText();
					
					if((eid.equals("Choose one...") && pid.equals("Choose one...")) && (fd.length()==0 && td.length()==0)){
						JOptionPane.showMessageDialog(null, "Invalid Input");
						}
					else if(eid.equals("All") && pid.equals("All")){
						String query1="Select allocation.EID,Resources.ENAME,Project.Pname,Allocation.from_date,Allocation.to_date,Allocation.hours from ALLOCATION JOIN RESOURCES on allocation.EID=resources.Eid join project on allocation.pid=project.pid;";
						PreparedStatement pst1=(PreparedStatement) con.prepareStatement(query1);
						ResultSet rs3=pst1.executeQuery(query1);
						table_2.setModel(DbUtils.resultSetToTableModel(rs3));
						pst1.close();
						rs3.close();
						}
					else if(eid=="All"){
						String query="Select EID,Ename,Designation,Department,Skillset,Contact,Email,Statr as Status from resources";
						PreparedStatement pst3=(PreparedStatement) con.prepareStatement(query);
						ResultSet rs5=pst3.executeQuery(query);
						table_2.setModel(DbUtils.resultSetToTableModel(rs5));
						pst3.close();
						rs5.close();
					}
					else if(pid=="All"){
						String query="Select * from project;";
						PreparedStatement pst3=(PreparedStatement) con.prepareStatement(query);
						ResultSet rs5=pst3.executeQuery(query);
						table_2.setModel(DbUtils.resultSetToTableModel(rs5));
						pst3.close();
						rs5.close();
					}
					else if((eid!="Choose one...") && (pid!="Choose one...")&& (fd.length()>0 && td.length()>0)){
						String ss[]=pid.split("-");
						String query6="Select allocation.EID,Resources.ENAME,Project.Pname,Allocation.from_date,Allocation.to_date,Allocation.hours from ALLOCATION JOIN RESOURCES on allocation.EID=resources.Eid join project on allocation.pid=project.pid where (from_date>='"+fd+"' and to_date='"+td+"') and (resources.eid='"+eid+"' and pname='"+ss[1]+"');";
						PreparedStatement pst6=(PreparedStatement) con.prepareStatement(query6);
						ResultSet rs8=pst6.executeQuery(query6);
						table_2.setModel(DbUtils.resultSetToTableModel(rs8));
						pst6.close();
						rs8.close();
					}
					else if((eid!="Choose one...") && (pid!="Choose one...")&& (fd.length()>0)){
						String ss[]=pid.split("-");
						String query6="Select allocation.EID,Resources.ENAME,Project.Pname,Allocation.from_date,Allocation.to_date,Allocation.hours from ALLOCATION JOIN RESOURCES on allocation.EID=resources.Eid join project on allocation.pid=project.pid where from_date>='"+fd+"' and (resources.eid='"+eid+"' and pname='"+ss[1]+"');";
						PreparedStatement pst6=(PreparedStatement) con.prepareStatement(query6);
						ResultSet rs8=pst6.executeQuery(query6);
						table_2.setModel(DbUtils.resultSetToTableModel(rs8));
						pst6.close();
						rs8.close();
					}
					
					else if((eid!="Choose one...") && (pid!="Choose one...")&& (td.length()>0)){
						String ss[]=pid.split("-");
						String query7="Select allocation.EID,Resources.ENAME,Project.Pname,Allocation.from_date,Allocation.to_date,Allocation.hours from ALLOCATION JOIN RESOURCES on allocation.EID=resources.Eid join project on allocation.pid=project.pid where to_date<='"+td+"' and (resources.eid='"+eid+"' and pname='"+ss[1]+"');";
						PreparedStatement pst7=(PreparedStatement) con.prepareStatement(query7);
						ResultSet rs9=pst7.executeQuery(query7);
						table_2.setModel(DbUtils.resultSetToTableModel(rs9));
						pst7.close();
						rs9.close();
					}
					
					
					else if(eid.equals("Choose one...") && pid.equals("All")){
						String query2="Select PID,Pname,psd as Start_date, ped as End_date,statp as Status from project;";
						PreparedStatement pst2=(PreparedStatement) con.prepareStatement(query2);
						ResultSet rs4=pst2.executeQuery(query2);
						table_2.setModel(DbUtils.resultSetToTableModel(rs4));
						pst2.close();
						rs4.close();
						}
					else if((eid!="Choose one...") && (fd.length()>0)){
						String query4="Select allocation.EID,Resources.ENAME,Project.Pname,Allocation.from_date,Allocation.to_date,Allocation.hours from ALLOCATION JOIN RESOURCES on allocation.EID=resources.Eid join project on allocation.pid=project.pid where resources.eid='"+eid+"' and from_date>='"+fd+"';";
						PreparedStatement pst4=(PreparedStatement) con.prepareStatement(query4);
						ResultSet rs6=pst4.executeQuery(query4);
						table_2.setModel(DbUtils.resultSetToTableModel(rs6));
						pst4.close();
						rs6.close();
					}
					
					else if((eid!="Choose one...") && (td.length()>0)){
						String query4="Select allocation.EID,Resources.ENAME,Project.Pname,Allocation.from_date,Allocation.to_date,Allocation.hours from ALLOCATION JOIN RESOURCES on allocation.EID=resources.Eid join project on allocation.pid=project.pid where resources.eid='"+eid+"' and to_date<='"+td+"';";
						PreparedStatement pst4=(PreparedStatement) con.prepareStatement(query4);
						ResultSet rs6=pst4.executeQuery(query4);
						table_2.setModel(DbUtils.resultSetToTableModel(rs6));
						pst4.close();
						rs6.close();
					}
					else if((pid!="Choose one...") && (fd.length()>0)){
						String query5="Select PID,Pname,psd as Start_date, ped as End_date,statp as Status from project where pid='"+pid+"' and psd>='"+fd+"';";
						PreparedStatement pst5=(PreparedStatement) con.prepareStatement(query5);
						ResultSet rs7=pst5.executeQuery(query5);
						table_2.setModel(DbUtils.resultSetToTableModel(rs7));
						pst5.close();
						rs7.close();
					}
					else if(fd.length()>0 && td.length()>0){
						String query="Select allocation.EID,Resources.ENAME,Project.Pname,Allocation.from_date,Allocation.to_date,Allocation.hours from ALLOCATION JOIN RESOURCES on allocation.EID=resources.Eid join project on allocation.pid=project.pid where from_date>='"+fd+"' and to_date<='"+td+"';";
						PreparedStatement pst=(PreparedStatement) con.prepareStatement(query);
						ResultSet rs=pst.executeQuery(query);
						table_2.setModel(DbUtils.resultSetToTableModel(rs));
						pst.close();
						rs.close();
					}
					else if(eid=="Choose one..." && pid!="Choose one..."){
						String query="Select * FROM PROJECT WHERE PID='"+pid+"'";
						PreparedStatement pst=(PreparedStatement) con.prepareStatement(query);
						ResultSet rs=pst.executeQuery(query);
						table_2.setModel(DbUtils.resultSetToTableModel(rs));
						pst.close();
						rs.close();
					}
					else if(eid!="Choose one..." && pid=="Choose one..."){
						String query3="Select EID,Ename,Designation,Department,Skillset,Contact,Email,Statr as Status from resources where eid='"+eid+"';";
						PreparedStatement pst3=(PreparedStatement) con.prepareStatement(query3);
						ResultSet rs5=pst3.executeQuery(query3);
						table_2.setModel(DbUtils.resultSetToTableModel(rs5));
						pst3.close();
						rs5.close();
						}
					
					else{
						JOptionPane.showMessageDialog(null, "There is no entry. Please choose another search input..");
					}
					
					con.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNewButton.setBounds(102, 306, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Available");
		btnNewButton_1.setToolTipText("Seach the Employee's which have free time");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost/queue","root","root");
					Statement stat= con.createStatement();
					String query1="Select Resources.EID, resources.ENAME,sum(allocation.hours) as Hours from allocation join resources on resources.eid=allocation.eid where allocation.hours<=7 group by allocation.eid;";
					PreparedStatement pst1=(PreparedStatement) con.prepareStatement(query1);
					ResultSet rs1=pst1.executeQuery(query1);
					table_2.setModel(DbUtils.resultSetToTableModel(rs1));
					con.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
			
			}
		});
		btnNewButton_1.setBounds(293, 306, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 39, 21);
		getContentPane().add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
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
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}

			}
		});
		mntmAllocationForm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				}
		});
		mnMenu.add(mntmAllocationForm);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 379, 968, 183);
		getContentPane().add(scrollPane);
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setToolTipText("Exit the application");
		btnExit.setBounds(484, 306, 89, 23);
		getContentPane().add(btnExit);
		
		
		
		
		
		
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Dashboard obj=new Dashboard();
		 obj.setVisible(true);
			obj.setSize(new Dimension(1000,700));
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			obj.setLocation(dim.width/2-obj.getSize().width/2, dim.height/2-obj.getSize().height/2);
	}
}
