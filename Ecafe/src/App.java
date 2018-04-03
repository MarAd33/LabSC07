import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class App {

	private JFrame frame;
	private JTable table_1;
	/**
	 * @wbp.nonvisual location=21,-31
	 */
	private final JTable table_2 = new JTable();
	JTable table=new JTable();
	Connection con;  
	ResultSet rs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		textField.setColumns(10);
		initialize();
	}
 
  
	/**
	 * Initialize the contents of the frame.
	 */
	Statement stmt;
	DefaultTableModel model;
	/**
	 * @wbp.nonvisual location=2,149
	 */
	private final JTextField textField = new JTextField();
	private void initialize() {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			 
					con= DriverManager.getConnection(  
							"jdbc:mysql://localhost:3306/ecafedb","root","");  
				   String[] columns=new String[]{"ItemId","ItemName", "Price", "Quantity"};
					Object[][] data = new Object[][] {};
					final Class[] columnClass = new Class[] {
						    String.class, String.class, Double.class, Integer.class
						};
					 stmt=con.createStatement(); 
					Scanner s =new Scanner(System.in);
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
					ResultSet rs=stmt.executeQuery("select ItemID,ItemName,Price,Quantity from item");  
					while(rs.next())  
					{//System.out.println(rs.getInt(1)+"  "+rs.getInt(2)+"  "+rs.getString(3)+"\t"+rs.getString(4)+""+rs.getString(5)+ "    "+rs.getString(6)+"    "+rs.getString(7));  
					int id=rs.getInt(1);
					String name=rs.getString(2);
					Double price=rs.getDouble(3);
					int quantity=rs.getInt(4);
					model.addRow(new Object[] {id,name, price, quantity});
					}
					table.setModel(model); 
			   }
			//here sonoo is database name, root is u and password  
			
			
	         
	        //add the table to the frame
			
			
			catch(Exception e){ System.out.println(e);}  
		frame = new JFrame();
		frame.setBounds(100, 100, 439, 296);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JList list = new JList();
		frame.getContentPane().add(list, BorderLayout.NORTH);
		
		JList list_1 = new JList();
		frame.getContentPane().add(list_1, BorderLayout.WEST);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel model = new DefaultListModel();
				model.addElement("Online People");
				JList list = new JList( model );
			}
		});
		
		
		//JScrollPane scrollPane= new  JScrollPane(table);
		//frame.getContentPane().add(scrollPane);
		
		//JPanel bottombtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
       // JPanel bottombtnPn2 = new JPanel(new BorderLayout());
        JPanel panOuter = new JPanel(new BorderLayout());
        JPanel Outer = new JPanel(new BorderLayout());
        JPanel belowpanOuter = new JPanel(new BorderLayout());
        JPanel panLeft = new JPanel(new BorderLayout());
        JPanel panCen = new JPanel(new BorderLayout());
       // bottombtnPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
       // bottombtnPn2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panLeft.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panOuter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        belowpanOuter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel panRight = new JPanel(new BorderLayout());
        panRight.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panCen.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panOuter.add(panLeft, BorderLayout.WEST);
        panOuter.add(panRight, BorderLayout.EAST);
        panOuter.add(panCen, BorderLayout.CENTER);
       Outer.add(panOuter, BorderLayout.WEST);
      Outer.add(belowpanOuter, BorderLayout.EAST);
     // bottombtn.add(bottombtnPnl, BorderLayout.EAST);
     // bottombtn.add(bottombtnPn2, BorderLayout.WEST);
        JTextField Itemname=new JTextField(10);
        JTextField Price=new JTextField(10);
        JTextField Quantity=new JTextField(10);
        JLabel Item=new JLabel("Item Name");
        JLabel pric=new JLabel("Price");
        JLabel quan=new JLabel("Quantity");
        JButton enter=new JButton("ENTER");
        enter.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String pid=Itemname.getText();
        		Double tid=Double.parseDouble(Price.getText());
        		int rid=Integer.parseInt(Quantity.getText());
        	     
				
					try {
					
						 String query = " insert into item (Itemname, Price, Quantity)"
		        	    	        + " values (?, ?, ?)";
		        	      PreparedStatement preparedStmt = con.prepareStatement(query);
		        	   
							preparedStmt.setString (1, pid);
							preparedStmt.setDouble (2, tid);
							preparedStmt.setInt(3, rid);
						if(preparedStmt.executeUpdate()!=0)
						{
							model.setRowCount(0);
							JOptionPane.showMessageDialog(null, "Record has been inserted");
							ResultSet rs=stmt.executeQuery("select ItemID,ItemName,Price,Quantity from item");  
							while(rs.next())  
							{//System.out.println(rs.getInt(1)+"  "+rs.getInt(2)+"  "+rs.getString(3)+"\t"+rs.getString(4)+""+rs.getString(5)+ "    "+rs.getString(6)+"    "+rs.getString(7));  
							int id=rs.getInt(1);
							String name=rs.getString(2);
							Double price=rs.getDouble(3);
							int quantity=rs.getInt(4);
							model.addRow(new Object[] {id,name, price, quantity});
							}
							table.setModel(model); 
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
        	}
        });
        
        
        belowpanOuter.add(enter, BorderLayout.CENTER);
        panLeft.add(Item, BorderLayout.NORTH);
        panLeft.add(Itemname, BorderLayout.CENTER);

        panRight.add(pric, BorderLayout.NORTH);
        panRight.add(Price, BorderLayout.CENTER);
        
        panCen.add(quan, BorderLayout.NORTH);
        panCen.add(Quantity, BorderLayout.CENTER);

        
        JButton button = new JButton("DELETE");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int row =table.getSelectedRow();
        		int col=0;
        		int id=(int) table.getModel().getValueAt(row, col);
        		PreparedStatement ps;
				try {
					ps = con.prepareStatement("DELETE FROM Item WHERE ItemId =?");
					ps.setInt(1,id);
					
					if(ps.executeUpdate()!=0)
					{
						model.setRowCount(0);
						JOptionPane.showMessageDialog(null, "Record has been deleted");
						ResultSet rs=stmt.executeQuery("select ItemID,ItemName,Price,Quantity from item");  
						while(rs.next())  
						{//System.out.println(rs.getInt(1)+"  "+rs.getInt(2)+"  "+rs.getString(3)+"\t"+rs.getString(4)+""+rs.getString(5)+ "    "+rs.getString(6)+"    "+rs.getString(7));  
						id=rs.getInt(1);
						String name=rs.getString(2);
						Double price=rs.getDouble(3);
						int quantity=rs.getInt(4);
						model.addRow(new Object[] {id,name, price, quantity});
						}
						table.setModel(model); 
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
        	        
        		
        	}
        });
        bottombtnPnl.add(button);
        JButton button_1 = new JButton("ADD");
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panOuter.setVisible(true);
        		
	        	        
        	      
        	     
        		
				
        		
        	}
        });
        bottombtnPnl.add(button_1);
        bottombtnPnl.add(new JButton("EDIT"));
        JButton orders=new JButton("CHECK ORDERS");
        orders.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame2orders order = new frame2orders();
        		order.setVisible(true);
        	}
        });
        bottombtnPnl.add(orders);
        table.getTableHeader().setReorderingAllowed(false);



        frame.getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
        frame.getContentPane().add(table, BorderLayout.CENTER);
        frame.getContentPane().add( Outer, BorderLayout.SOUTH);
        frame.getContentPane().add(bottombtnPnl, BorderLayout.EAST);
        //frame.getContentPane().add(bottombtnPn2, BorderLayout.EAST);
        table.setVisible(true);
        panOuter.setVisible(false);
	}

}
