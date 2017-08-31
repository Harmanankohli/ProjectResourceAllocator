import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu");
		getContentPane().setLayout(null);
		
		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.setToolTipText("Will be directed to Dashboard Form");
		btnDashboard.addActionListener(new ActionListener() {
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
		btnDashboard.setBounds(162, 17, 100, 23);
		getContentPane().add(btnDashboard);
		
		JButton btnAllocationForm = new JButton("Allocation Form");
		btnAllocationForm.setToolTipText("Will be directed to Allocation Form");
		btnAllocationForm.addActionListener(new ActionListener() {
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
		btnAllocationForm.setBounds(150, 57, 125, 23);
		getContentPane().add(btnAllocationForm);
		
		JButton btnClientForm = new JButton("Client Form");
		btnClientForm.setToolTipText("Will be directed to Client Form");
		btnClientForm.addActionListener(new ActionListener() {
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
		btnClientForm.setBounds(162, 97, 100, 23);
		getContentPane().add(btnClientForm);
		
		JButton btnProjectForm = new JButton("Project Form");
		btnProjectForm.setToolTipText("Will be directed to Project Form");
		btnProjectForm.addActionListener(new ActionListener() {
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
		btnProjectForm.setBounds(158, 137, 108, 23);
		getContentPane().add(btnProjectForm);
		
		JButton btnResourcesForm = new JButton("Resources Form");
		btnResourcesForm.setToolTipText("Will be directed to Resources Form");
		btnResourcesForm.addActionListener(new ActionListener() {
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
		btnResourcesForm.setBounds(145, 177, 135, 23);
		getContentPane().add(btnResourcesForm);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setToolTipText("Exit the application");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(168, 217, 89, 23);
		getContentPane().add(btnExit);
	}

	public static void main(String[] args) {
		Menu m=new Menu();
		 m.setVisible(true);
			m.setSize(new Dimension(450,330));
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			m.setLocation(dim.width/2-m.getSize().width/2, dim.height/2-m.getSize().height/2);
	}
}
