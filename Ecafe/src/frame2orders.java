import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frame2orders extends JFrame {

	static Statement stmt;
	static DefaultTableModel model;
	static JTable table=new JTable();
	static Connection con2;
	static ResultSet rs;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame2orders frame = new frame2orders();
					frame.setVisible(true);
					
					Class.forName("com.mysql.jdbc.Driver");  
					 
					con2 = DriverManager.getConnection(  
							"jdbc:mysql://localhost:3306/ecafedb","root","");  
				   String[] columns=new String[]{"OrderNo","User Name", "Date", "Status"};
					Object[][] data = new Object[][] {};
					final Class[] columnClass = new Class[] {
						    String.class, String.class, String.class, String.class
						};
					 stmt=con2.createStatement(); 
				     
					 model = new DefaultTableModel(data, columns) { @Override
					    public boolean isCellEditable(int row, int column)
				    {
				        return false;
				    }
				    @Override
				    public Class<?> getColumnClass(int columnIndex)
				    {
				        
						return columnClass[columnIndex];
				    } };
					ResultSet rs=stmt.executeQuery("select UserName,OrderNo,OrderDate,Status from users join orders where users.UserID=orders.UserID");  
					while(rs.next())  
					{//System.out.println(rs.getInt(1)+"  "+rs.getInt(2)+"  "+rs.getString(3)+"\t"+rs.getString(4)+""+rs.getString(5)+ "    "+rs.getString(6)+"    "+rs.getString(7));  
					int id=rs.getInt(2);
					String name=rs.getString(1);
					
					String status=rs.getString(4);
				    Date d1 = new Date(rs.getDate(3).getTime());
					String date=d1.toString();
					model.addRow(new Object[] {id,name, date, status});
					}
					table.setModel(model); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frame2orders() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JLabel dat=new JLabel("Enter Date to view orders");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		JTextField dateTextField = new JTextField();
		JPanel panOuter = new JPanel(new BorderLayout());
	    JPanel Outer = new JPanel(new BorderLayout());
	    JPanel panOuterL = new JPanel(new BorderLayout());
	    JButton Click=new JButton("CLICK");
		Click.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date date1;
				String text=dateTextField.getText();
				
					
					
				
				 
				ResultSet rs;
				try {
					model.setRowCount(0);
					 String query = "select UserName,OrderNo,OrderDate,Status from orders join users where OrderDate=? AND users.UserID=orders.UserID";
	        	      PreparedStatement preparedStmt = con2.prepareStatement(query);
	        	      try {
							date1= df.parse(text);
							java.sql.Date date = new java.sql.Date(date1.getTime());
							preparedStmt.setDate (1, date);
							rs=preparedStmt.executeQuery();
							if(!rs.next())
							{
								JOptionPane.showMessageDialog(null, "Records not found");
							}
							
							while(rs.next())  
							{//System.out.println(rs.getInt(1)+"  "+rs.getInt(2)+"  "+rs.getString(3)+"\t"+rs.getString(4)+""+rs.getString(5)+ "    "+rs.getString(6)+"    "+rs.getString(7));  
							int id=rs.getInt(2);
							String name=rs.getString(1);
							System.out.println(date);
							String status=rs.getString(4);
						    Date d1 = new Date(rs.getDate(3).getTime());
							String da=d1.toString();
							model.addRow(new Object[] {id,name, date, status});
						}
							
							table.setModel(model); 
	        	      }catch (ParseException e2) {
							// TODO Auto-generated catch block
	        	    	    JOptionPane.showMessageDialog(null, "Invalid date enterd! Enter correct dateformat(yyyy-MM-dd)");
							e2.printStackTrace();
						}
	        	      
						
					
				
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
				
				
			}
		});
	    panOuter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panOuterL.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panOuterL.add(dat, BorderLayout.NORTH);
       panOuterL.add(dateTextField,BorderLayout.CENTER );
       panOuterL.add(Click, BorderLayout.EAST);
        panOuter.add(table.getTableHeader(), BorderLayout.NORTH);
        panOuter.add(table, BorderLayout.CENTER);
      //contentPane.add(Outer, BorderLayout.CENTER);
      contentPane.add(panOuter, BorderLayout.CENTER);
      contentPane.add(panOuterL, BorderLayout.SOUTH);
      
	}

}
