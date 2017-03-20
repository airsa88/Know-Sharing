
//Java GUI code to connect to Cassandra Database and fetch the query results.

package cassandra.cluster;
//import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Collection;
import javax.swing.*;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
@SuppressWarnings("serial")
public class Connection2 extends JFrame implements ActionListener {
	//GUI Setup
	private JFrame frame1;
	private JPanel panel1;
	private JButton button1,button2,button3,button4;
	String element1 = "";
	String element3 = "";
	String element5 = "";
	String element7 = "";
	String element8 = "";
	String element10 = "";
	String element12 = "";
	String element14 = "";
	String element16 = "";
	//int count = 0;
	
	
public Connection2() {
	frame1 = new JFrame("Cassandra Java UI");
	frame1.setVisible(true);
	frame1.setSize(300,300);
	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	panel1 = new JPanel();
	button1 = new JButton("QUERY1");
	button1.addActionListener(this);
	button2 = new JButton("QUERY2");
	button2.addActionListener(this);
	button3 = new JButton("QUERY3");
	button3.addActionListener(this);
	button4 = new JButton("QUERY4");
	button4.addActionListener(this);
	panel1.add(button1);
	panel1.add(button2);
	panel1.add(button3);
	panel1.add(button4);
	frame1.add(panel1);
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		//Query1 - Button call
		if (ae.getSource() == button1) {
			
			//Cassandra cluster connection driver logic(generated by instacluster while creating cassandra cluster line 63-77).
			final Cluster.Builder clusterBuilder = Cluster.builder()
				    .addContactPoints(
				        "ip_address1", "ip_address2", "ip_address3" //  (Amazon Web Services (VPC))
				    )
				    .withLoadBalancingPolicy(DCAwareRoundRobinPolicy.builder().withLocalDc("-----------------").build()) // your local data centre
				    .withPort(----) // relevant port number
				    .withAuthProvider(new PlainTextAuthProvider("username", "password"));

				try (final Cluster cluster = clusterBuilder.build()) {
				    final Metadata metadata = cluster.getMetadata();
				    //Connection establishment to Cassandra cluster
				    System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());
				    for (final Host host: metadata.getAllHosts()) {
				        System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(), host.getRack());
				    }
				    JOptionPane.showMessageDialog(null, "Connectivity Successful", "Query1", JOptionPane.INFORMATION_MESSAGE);
				    Session session = cluster.connect("crimedata");
					//Crimedata is the database table which I created in Cassandra
				    String query = "select \"Location_Block\" from crimedata where \"Hour\" >= 0 and \"Hour\" <=3 and \"Text_General_Code\" = 'Thefts' LIMIT 10 ALLOW FILTERING";
				    ResultSet result1 = session.execute(query);
				    //System.out.println(result1.all());
				    Collection<Row> rows1 = result1.all();
				    for(Row element : rows1) {
				       element1 = element1 + '\n' + element;
				       element1 = element1.replaceAll("Row|\\[|\\]", ""); 
				    }
				  //Query Execution time
				    String query6 = "select duration,parameters from system_traces.sessions";		
				    ResultSet rs6 = session.execute(query6);
				    Collection<Row> rows6 = rs6.all();
				    for(Row element11 : rows6) {
				    	//System.out.println(element11);
				    	element12 = element12 + '\n' + '\n' + element11;
				    	element12 = element12.replaceAll("Row|\\[|\\]", ""); 
				    }	
				    System.out.println(element1);
				    System.out.println(element12);
				    String element21 = "Location_Block" + element1 + '\n' + '\n'+ "Execution Time"+ element12;
				    JTextArea textarea1 = new JTextArea(30, 30);
				    textarea1.setText(element21);
				    textarea1.setLineWrap(true);
				    textarea1.setEditable(false);
	                JScrollPane scrollPane = new JScrollPane(textarea1);
	                JOptionPane.showMessageDialog(null, scrollPane, "Query1-RESULT", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("Connectivity Error - Please check");
					JOptionPane.showMessageDialog(null,"Connectivity Error");
		}
		
		}
		//Query2 - Button call
		else if(ae.getSource() == button2) {
			final Cluster.Builder clusterBuilder = Cluster.builder()
				    .addContactPoints(
				         "ip_address1", "ip_address2", "ip_address3" //  (Amazon Web Services (VPC))
				    )
				    .withLoadBalancingPolicy(DCAwareRoundRobinPolicy.builder().withLocalDc("--------------").build()) // your local data centre
				    .withPort(----) // relevant port number (given by Cassandra)
				    .withAuthProvider(new PlainTextAuthProvider("username", "password"));

				try (final Cluster cluster = clusterBuilder.build()) {
				    final Metadata metadata = cluster.getMetadata();
				    //Connection establishment to Cassandra cluster
				    System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());

				    for (final Host host: metadata.getAllHosts()) {
				        System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(), host.getRack());
				    }
				    Session session = cluster.connect("crimedata");
				    JOptionPane.showMessageDialog(null, "Connectivity Successful", "Query2", JOptionPane.INFORMATION_MESSAGE);
				    String query2 = "select \"Dc_Key\",\"Text_General_Code\" from crimedata where \"UCR_General\" = 1500 LIMIT 10 ALLOW FILTERING";
				    ResultSet result2 = session.execute(query2);
				    Collection<Row> rows2 = result2.all();
				    for(Row element2 : rows2) {
				       element3 = element3 + '\n' + element2;
				       element3 = element3.replaceAll("Row|\\[|\\]", ""); 
				       }
				  //Query Execution time
				    String query5 = "select duration,parameters from system_traces.sessions";		
				    ResultSet rs5 = session.execute(query5);
				    Collection<Row> rows5 = rs5.all();
				    for(Row element9 : rows5) {
				    	//System.out.println(element9);
				    	element10 = element10 + '\n' + '\n' + element9;
				    	element10 = element10.replaceAll("Row|\\[|\\]", ""); 
				    }
				    System.out.println(element3);
				    System.out.println(element10);
				    String element22 = "DC_Key, Text_General_Code" + element3 + '\n' + '\n'+ "Execution Time"+ element10;
				    JTextArea textarea1 = new JTextArea(30, 30);
				    textarea1.setText(element22);
				    textarea1.setLineWrap(true);
				    textarea1.setEditable(false);
	                JScrollPane scrollPane = new JScrollPane(textarea1);
	                JOptionPane.showMessageDialog(null, scrollPane, "Query2-RESULT", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("Connectivity Error - Please check");
					JOptionPane.showMessageDialog(null,"Connectivity Error");
		}
				//System.exit(0);
		
		}
		//Query3 - Button call
		else if (ae.getSource() == button3) {
			
			final Cluster.Builder clusterBuilder = Cluster.builder()
				    .addContactPoints(
				        "ip_address1", "ip_address2", "ip_address3" //  (Amazon Web Services (VPC))
				    )
				    .withLoadBalancingPolicy(DCAwareRoundRobinPolicy.builder().withLocalDc("---------------").build()) // your local data centre
				    .withPort(----) // port number generated by Cassandra
				    .withAuthProvider(new PlainTextAuthProvider("username", "password"));

				try (final Cluster cluster = clusterBuilder.build()) {
				    final Metadata metadata = cluster.getMetadata();
				    //Connection establishment to Cassandra cluster
				    System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());

				    for (final Host host: metadata.getAllHosts()) {
				        System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(), host.getRack());
				    }
				    Session session = cluster.connect("crimedata");
				    JOptionPane.showMessageDialog(null, "Connectivity Successful", "Query3", JOptionPane.INFORMATION_MESSAGE);
				    String query3 = "select \"Dispatch_Date\" from crimedata where \"Location_Block\" = '5500 BLOCK N 5TH ST' LIMIT 10 ALLOW FILTERING";
				    ResultSet result3 = session.execute(query3);
				    Collection<Row> rows3 = result3.all();
				    for(Row element4 : rows3) {
				       element5 = element5 + '\n' + element4;
				       element5 = element5.replaceAll("Row|\\[|\\]", ""); 
				       }
				  //Query Execution time
				    String query7 = "select duration,parameters from system_traces.sessions";		
				    ResultSet rs7 = session.execute(query7);
				    Collection<Row> rows7 = rs7.all();
				    for(Row element13 : rows7) {
				    	//System.out.println(element13);
				    	element14 = element14 + '\n' + '\n' + element13;
				    	element14 = element14.replaceAll("Row|\\[|\\]", ""); 
				    }	   
				    
				    System.out.println(element5);
				    System.out.println(element14);
				    String element23 = "Dispatch_Date" + element5 + '\n' + '\n'+ "Execution Time"+ element14;
				    JTextArea textarea1 = new JTextArea(30, 30);
				    textarea1.setText(element23);
				    textarea1.setLineWrap(true);
				    textarea1.setEditable(false);
	                JScrollPane scrollPane = new JScrollPane(textarea1);
	                JOptionPane.showMessageDialog(null, scrollPane, "Query3-RESULT", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("Connectivity Error - Please check");
					JOptionPane.showMessageDialog(null,"Connectivity Error");
		}
		}
		
		//Query4 - Button call
		else {
			
			final Cluster.Builder clusterBuilder = Cluster.builder()
				    .addContactPoints(
				        "ip_address1", "ip_address2", "ip_address3" //  (Amazon Web Services (VPC))
				    )
				    .withLoadBalancingPolicy(DCAwareRoundRobinPolicy.builder().withLocalDc("----------------------").build()) // your local data centre
				    .withPort(----) // port number generated by Cassandra
				    .withAuthProvider(new PlainTextAuthProvider("username", "password"));

				try (final Cluster cluster = clusterBuilder.build()) {
				    final Metadata metadata = cluster.getMetadata();
				    //Connection establishment to Cassandra cluster
				    System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());

				    for (final Host host: metadata.getAllHosts()) {
				        System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(), host.getRack());
				    }
				    Session session = cluster.connect("crimedata");
				    JOptionPane.showMessageDialog(null, "Connectivity Successful", "Query4", JOptionPane.INFORMATION_MESSAGE);
				    String query4 = "select \"Dispatch_Time\" from crimedata where \"Text_General_Code\" = 'Weapon Violations' LIMIT 10 ALLOW FILTERING";
			    	   ResultSet result4 = session.execute(query4);
					    Collection<Row> rows4 = result4.all();
					    for(Row element6 : rows4) {
					    	element7 = element6.toString();
					    	element7 = element7.replaceAll("Row|\\[|\\]", ""); 
					    	long val = Long.valueOf(element7).longValue();
					    	val = val / 1000000000;
					    	int seconds = (int) (val) % 60 ;
					    	String sec = new DecimalFormat("00").format(seconds);  
					    	int minutes = (int) ((val % (60*60)) / 60);
					    	String mins = new DecimalFormat("00").format(minutes);  
					    	int hours   = (int) ((val / (60*60)));
					    	String hrs = new DecimalFormat("00").format(hours);  
					    	element7 = hrs + ":" + mins + ":" + sec;
					    	element8 = element8 + '\n' + element7;
					    	//System.out.println(element7);
					    	//element7.replaceAll("Row", "");
					    }	
					    //Query Execution time
					    String query8 = "select duration,parameters from system_traces.sessions";		
					    ResultSet rs8 = session.execute(query8);
					    Collection<Row> rows8 = rs8.all();
					    for(Row element15 : rows8) {
					    	//System.out.println(element15);
					    	element16 = element16 + '\n' + '\n' + element15;
					    	element16 = element16.replaceAll("Row|\\[|\\]", ""); 
					    }		    
				    
				    System.out.println(element8);
				    System.out.println(element16);
				    String element24 = "Dispatch_Time" + element8 + '\n' + '\n'+ "Execution Time"+ element16;
				    JTextArea textarea1 = new JTextArea(30, 30);
				    textarea1.setText(element24);
				    textarea1.setLineWrap(true);
				    textarea1.setEditable(false);
	                JScrollPane scrollPane = new JScrollPane(textarea1);
	                JOptionPane.showMessageDialog(null, scrollPane, "Query4-RESULT", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("Connectivity Error - Please check");
					JOptionPane.showMessageDialog(null,"Connectivity Error");
		}
			
		}
		
	}
	
	 public static void main(String[] args) {
		 new Connection2();
	}
	}

