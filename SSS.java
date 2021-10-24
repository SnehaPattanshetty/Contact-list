import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import net.proteanit.sql.DbUtils;  
public class SSS{
    public JFrame frame;
	public JTextField t1;
	public JTextField t2;
	public JTable table;
	DefaultTableModel model;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SSS window = new SSS();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
        public SSS() {
		initialize();
	}
	private <Workbook> void initialize() {
		frame = new JFrame();
	    frame.getContentPane().setBackground(Color.PINK);
		frame.setBackground(Color.PINK);
		frame.setBounds(100, 100, 520, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 51, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(10, 76, 46, 14);
		frame.getContentPane().add(lblNumber);
		t1 = new JTextField();
		t1.setBounds(66, 48, 86, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(66, 73, 86, 20);
		frame.getContentPane().add(t2);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(175, 11, 319, 248);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			int i=table.getSelectedRow();
			t1.setText(table.getValueAt(i, 0).toString());
			t2.setText(table.getValueAt(i, 1).toString());
			}
		});
		table.setBackground(new Color(147, 112, 219));
		model=new DefaultTableModel();
		Object[] column= {"Name", "Number"};
		
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(t1.getText().equals("")||t2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill Complete Information");
			}
					
			else if (!(t1.getText().matches("[a-zA-Z]+"))) {
							JOptionPane.showMessageDialog(null, "Invalid name");
						}
					
			else if(!(t2.getText().matches("[0-9]+") && t2.getText().length()==10)) {
								JOptionPane.showMessageDialog(null, "Invalid number");
							}	 
					else
				{
					String Name=t1.getText();
					String Number=t2.getText();
							
							
							        
							        try
							        {
							            Class.forName("com.mysql.cj.jdbc.Driver");
							            String databaseURL = "jdbc:mysql://localhost:3306/crud1";
							            Connection con = DriverManager.getConnection(databaseURL, "root", "Hasne99@");
							            Statement stat = con.createStatement();    
							            String selectQuery = "select * from user where Name	='"+Name+"' and Number='"+Number+"'";
							            System.out.println(selectQuery);
							            ResultSet rs=stat.executeQuery(selectQuery);
							         //   System.out.println(rs.next());
							           if(rs.next())
							           {
							        	   JOptionPane.showMessageDialog(null, "Data already exists");
							            
							           }
							           else
							           {
							          String insertQuery = "insert into user values('" + Name + "','" + Number + "')";
							           
							          stat.executeUpdate(insertQuery);
							          JOptionPane.showMessageDialog(null, "Added succesfully");
							        				        }
							        }
							        catch(Exception e1)
							                {
							                    System.out.println(e1);
							                }
								}
			
			}
		});
		btnNewButton.setBounds(31, 110, 89, 23);
		frame.getContentPane().add(btnNewButton);
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().equals("")||t2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill Complete Information");
			}
					
			else if (!(t1.getText().matches("[a-zA-Z]+"))) {
							JOptionPane.showMessageDialog(null, "Invalid name");
						}
					
			else if(!(t2.getText().matches("[0-9]+") && t2.getText().length()==10)) {
								JOptionPane.showMessageDialog(null, "Invalid number");
							}
			else {
				int i=table.getSelectedRow();
				table.setValueAt(t1.getText(), i, 0);
				table.setValueAt(t2.getText(), i, 1);
					
				 try
			        {
					 String Name=t1.getText();
						String Number=t2.getText();
								
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            String databaseURL = "jdbc:mysql://localhost:3306/crud1";
			            Connection con = DriverManager.getConnection(databaseURL, "root", "Hasne99@");
			            Statement stat = con.createStatement();    
			            String selectQuery = "select * from user where Name	='"+Name+"' and Number='"+Number+"'";
			            System.out.println(selectQuery);
			            ResultSet rs=stat.executeQuery(selectQuery);
			         //   System.out.println(rs.next());
			           if(rs.next())
			           {
			        	   JOptionPane.showMessageDialog(null, "Data already exists");
			            
			           }
			           else
			           {

							
			          String insertQuery = "update user set number=('\"+number1+\"') where name='\"+name1+\"'\"";
			           
			          stat.executeUpdate(insertQuery);
			          JOptionPane.showMessageDialog(null, "Updated succesfully");
			        				        }
			        }
			        catch(Exception e1)
			                {
			                    System.out.println(e1);
			                }

			      }
				try {
					  Class.forName("com.mysql.cj.jdbc.Driver");              
					  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud1","root", "Hasne99@");
					  String sql="select * from user";
					  PreparedStatement pst=connection.prepareStatement(sql);
					  ResultSet rs=pst.executeQuery();
					  table.setModel(DbUtils.resultSetToTableModel(rs));
					  } catch (Exception e1) {
						System.out.println(e1);
					}
				
}
		});
		btnUpdate.setBounds(31, 134, 89, 23);
		frame.getContentPane().add(btnUpdate);
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				DefaultTableModel model= (DefaultTableModel)table.getModel();
                String selected = model.getValueAt(row, 0).toString();
                if (row >= 0) {
                   model.removeRow(row);}
				            try{
								 Class.forName("com.mysql.cj.jdbc.Driver");  
							       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud1","root","Hasne99@");  
                    String query="Delete from user where Name='"+t1.getText()+"'";
									PreparedStatement pst = connection.prepareStatement(query);
								    pst.executeUpdate();  
								    JOptionPane.showMessageDialog(null, "Deleted Successfully");
								  }
								catch(Exception e1){
								  System.out.println(e1);
								}       
				               }
			});
		btnDelete.setBounds(31, 156, 89, 23);
		frame.getContentPane().add(btnDelete);
	    JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent e) {
				t1.setText("");
				t2.setText("");
			}
		});
		btnClear.setBounds(31, 179, 89, 23);
		frame.getContentPane().add(btnClear);
		JButton btnNewButton_1 = new JButton("Fetch data");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					  Class.forName("com.mysql.cj.jdbc.Driver");              
					  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud1","root", "Hasne99@");
					  String sql="select * from user";
					  PreparedStatement pst=connection.prepareStatement(sql);
					  ResultSet rs=pst.executeQuery();
					  table.setModel(DbUtils.resultSetToTableModel(rs));
					  } catch (Exception e1) {
						System.out.println(e1);
					}
			}
		});
		btnNewButton_1.setBounds(367, 270, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		JButton btnNewButton_2 = new JButton("export to excel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}              
					  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud1","root", "Hasne99@");
					  Statement statement = connection.createStatement();
                      FileOutputStream fileOut;
                     fileOut = new FileOutputStream("C:\\Users\\Admin\\eclipse-workspace\\MYSQL_CRUD\\file.xls");
                       HSSFWorkbook workbook = new HSSFWorkbook();
                    HSSFSheet worksheet = workbook.createSheet("Sheet 0");
                    Row row1 = worksheet.createRow((short)0);
                    row1.createCell(0).setCellValue("Name");
                    row1.createCell(1).setCellValue("Number");
                    Row row2 ;
                    ResultSet rs = statement.executeQuery("SELECT Name,Number FROM user");
                    while(rs.next()){
                    	int a = rs.getRow();
                    	row2 = worksheet.createRow((short)a);
                    	row2.createCell(0).setCellValue(rs.getString(1));
                    	row2.createCell(1).setCellValue(rs.getString(2));
                    }
                    workbook.write(fileOut);
                    fileOut.flush();
                    fileOut.close();
                    rs.close();
                    statement.close();
                    System.out.println("Export Success");
				}
				catch(SQLException ex){
					System.out.println(ex);
				}catch(IOException ioe){
					System.out.println(ioe);
				}
			}
		});
		btnNewButton_2.setBounds(31, 258, 121, 23);
		frame.getContentPane().add(btnNewButton_2);
		}
}
