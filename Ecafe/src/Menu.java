/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecafe;


import static ecafe.Login.uid;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maryam
 */
public class Menu extends javax.swing.JFrame {
JTable table=new JTable();


    /**
     * Creates new form Menu
     */  
    //Integer a[][]=new Integer[2][2];
int ss=0;
static int count=1;
String time=null;
Integer tim=0;
String address=null;
String addr=null;
     Integer bill=0;
     int price=0;
     int[][] arr = new int[2][2];
          String a=null;
           String s=null;
           int qty=0;
           String disp=null;
           int p=0;
           String status="In Progress";
          public Menu() throws SQLException, ClassNotFoundException, InstantiationException {
       initComponents();
       jTable1.setFont(new Font("Serif", Font.PLAIN, 20));
      jLabel1.setFont(new Font("Arial", Font.PLAIN, 40));

       java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        String currentTime = sdf.format(dt);
       int uid1=Integer.parseInt(uid);
       System.out.println(uid);
            resultSetToTableModel();
             jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            int selrow=0;
            // do some actions here, for example
            // print first column value from selected row 
           
           String message=null;
           a=jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            System.out.println(a);
            
            s = JOptionPane.showInputDialog("Enter quantity",message);
             ss=Integer.parseInt(s);
             p+=ss;
            if(ss<1){
             JOptionPane.showMessageDialog(null,"Invalid Quantity");
            }
             qty=Integer.parseInt(s);
        a=jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        price=Integer.parseInt(a);
        bill+=price*qty;
         System.out.println(s);
         //inserting into database
         
         
        Integer cc=count;
        int k=0;
        try {
            String b=jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            Integer b1=Integer.parseInt(b);
           // String count1=cc.toString();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
           Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Ecafedb","root","");

       Statement stmt1=(Statement) myConn.createStatement();
        Statement stmt2=(Statement) myConn.createStatement();
       
           System.out.println("qty:"+ss);
           while(k<ss){
        int rs=stmt1.executeUpdate("INSERT INTO ORDEREDITEMS values('"+count+"','"+count+"','"+status+"')");
         k++;  
             // int rs2=stmt2.executeUpdate("INSERT INTO order values('"+count+"','"+uid1+"','"+status+"','"+count+"')");  
           } 
           
           int rs=stmt1.executeUpdate("INSERT INTO ORDERS values('"+uid+"','"+count+"','"+count+"','"+currentTime+"','"+status+"')");
         k++; 
         //  String u="1998-01-02";
        //int rs1=stmt1.executeUpdate("INSERT INTO ORDER values('"+ count + "', '"+ count+ "', '" + status+"')");    
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
           

       
         
        }
    });
      jButton1.addActionListener(new ActionListener() 
{
    public void actionPerformed(ActionEvent e) {   
        
        disp=bill.toString();
        jTextField1.setText(disp);
 //   System.out.println("yay "+bill);
    }
    
});
          //Order delivery
          
          jButton2.addActionListener(new ActionListener() 
{
    public void actionPerformed(ActionEvent e) {   
      address = JOptionPane.showInputDialog("Enter your address",addr);
 //   System.out.println("yay "+bill);
 if(address==null){
  address = JOptionPane.showInputDialog("Enter a valid address",addr);
 }
    }
    
});
   //Pickup
       jButton3.addActionListener(new ActionListener() 
{
    public void actionPerformed(ActionEvent e) {   
      time = JOptionPane.showInputDialog("Enter a pickup time between 12:00 and 18:00 hours",time);
 //   System.out.println("yay "+bill);
  int t= Integer.parseInt(time);
 if(t<12 || t>18){
     
  JOptionPane.showMessageDialog(null,"Sorry we are not open at these hours.");
 }
    }
    
});
   //Inserting into database
   
         jButton4.addActionListener(new ActionListener() 
{
    public void actionPerformed(ActionEvent e) {   
     count++;
       JOptionPane.showMessageDialog(null,"Your order has been confirmed.");
    }
    
});
   
   

       
          }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void resultSetToTableModel() throws SQLException, ClassNotFoundException, InstantiationException{
        //Create new table model
        try
       {
           //loading the jdbc driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
           //get a connection to database
           Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Ecafedb","root","");
    Statement stmt=(Statement)myConn.createStatement();
           //execute sql query
          ResultSet rs=stmt.executeQuery("select ItemID, ItemName, Price from Item");
             System.out.println("Connected to databse");
       DefaultTableModel tableModel = new DefaultTableModel(){
        @Override
        public Class<?> getColumnClass(int col) {
            if(col == 7){
                return Boolean.class;
            }
            return super.getColumnClass(col);
        }
    };

        //Retrieve meta data from ResultSet
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        //Get all column names from meta data and add columns to table model
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }
         Object[] row = new Object[columnCount];

        //Scroll through result set
        while (rs.next()){
            //Get object from column with specific index of result set to array of objects
            for (int i = 0; i < columnCount; i++){
                row[i] = rs.getObject(i+1);
            }
            //Now add row to table model with that array of objects as an argument
            tableModel.addRow(row);
        }

        //Now add that table model to your table and you are done :D
        jTable1.setModel(tableModel);
        jButton1.setFont(new Font("Arial", Font.PLAIN, 20));
        jButton2.setFont(new Font("Arial", Font.PLAIN, 20));
        jButton3.setFont(new Font("Arial", Font.PLAIN, 20));
        jButton4.setFont(new Font("Arial", Font.PLAIN, 20));
        jTextField1.setFont(new Font("Arial", Font.PLAIN, 20));
        // table.setSize( 1000, 1000 );
}       catch (IllegalAccessException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        

        //Get number of columns from meta data
       
        //Create array of Objects with size of column count from meta data
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Calculate Bill");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton2.setText("Home Delivery");

        jButton3.setText("Pick-Up");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Confirm your Order");

        jLabel1.setText("Menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(308, 308, 308))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(408, 408, 408)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
            new Menu().setVisible(true);
            //selectcode
      
        } catch (Exception e) {
            // TODO Auwto-generated catch block
            e.printStackTrace();
        }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
