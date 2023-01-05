package database;
import java.util.*;
import java.sql.*;
public class employeedb {
	static final String URL = "jdbc:postgresql://localhost:2002/table";
	static final String USER = "postgres";
	static final String PASS = "indhu@vtg";
	   public static void main(String []args)  {
	       Scanner sc=new Scanner(System.in);
	       System.out.println("CREATE operation enter == create");
	       System.out.println("INSERT operation enter == insert");
	       System.out.println("UPDATE operation enter == update");
	       System.out.println("DELETE operation enter == delete");
	       System.out.println("DISPLAY operation enter == display");
	       System.out.println("DISPLAY ALL DATA operation enter == display all");
	       System.out.println("Enter your Option");
	       		String str=sc.next();
	       		employeedb s=new employeedb();
   switch(str){
       case "create":
    	    s.create();
    	    break;
       case "insert":
    	    s.insert();
    	    break;
       case "update":
    	    s.update();
    	    break;
       case "delete":
    	    s.delete();
    	    break;
       case "display":
    	    s.display();
    	    break;
       case "displayall":
    	    s.displayall();
    	    break;
       default:
    	    System.out.println("Enter the valid data");
      }
 }
	    // connection = connect
	   // Statement = s
	  // preparedStatement = ps
	 // Query = data
	   public void create() {
		    try {
		    	  Connection connect=DriverManager.getConnection("jdbc:postgresql://localhost:2002/table","postgres","indhu@vtg");
		    	  Statement  s=connect.createStatement();
		          String data="create table employee"+"(ID int primary key not null,"+"NAME varchar(50),"+"DESIGNATION varchar(50),"+"AGE int,"+"SALARY int);";
		          System.out.println(data);
		          s.executeUpdate(data);
		       }
		    catch(Exception e) {
		    	  System.out.println();
		        }
		          System.out.println("Table Created Successfully");
	 }
	   public void insert() {
		    try (Connection connect=DriverManager
				  .getConnection(URL,USER,PASS);) {
		    			Scanner sc=new Scanner(System.in);
		    			String data="INSERT INTO employee"+
							   "(id, name, designation, age, salary)VALUES"+ 
						       "(?, ?, ?, ?, ?);";
		    			PreparedStatement ps=connect.prepareStatement(data);
		    			System.out.println(" Enter how many row insert");
		    			int N=sc.nextInt();
		    			for(int i=1;i<=N;i++) {
		    				System.out.println("Enter employee Id:");
		    				ps.setInt(1, sc.nextInt());
		    				System.out.println("Enter employee Name:");
		    				ps.setString(2, sc.next());
		    				System.out.println("Enter employee Designation:");
		    				ps.setString(3, sc.next());
		    				System.out.println("Enetr employee Age:");
		    				ps.setInt(4,sc.nextInt());
		    				System.out.println("Enter employee Salary:");
		    				ps.setInt(5, sc.nextInt());
		    				ps.executeUpdate();
		    				System.out.println("Data Inserted Successfully !!!");
		    			}
		      }
			   catch(Exception e) {
				    System.out.println();
			   }
	 }
	    public void display() {
		   
		    try (Connection connect=DriverManager
				   .getConnection(URL,USER,PASS);){
					    Scanner sc=new Scanner(System.in);
					    System.out.println("Enter your ID for display");
					    int id=sc.nextInt();
					    String data = "SELECT id,name,designation,age,salary "
				               + "FROM employee "
				               + "WHERE id ="+ id;
					    Statement s=connect.createStatement(); 
					    ResultSet rs = s.executeQuery(data);
					    	while (rs.next()) {
					    		System.out.println(rs.getInt("id") + "\t"
					    				+ rs.getString("name") + "\t"
					    				+ rs.getString("designation") + "\t"
					    				+ rs.getInt("age") + "\t"
					    				+ rs.getInt("salary"));
					    	}
	        	}
		    	catch (SQLException ex) {
		    		System.out.println(ex.getMessage());
		    	}
	   }
	        public void delete() {
			       try (Connection connect=DriverManager
					      .getConnection(URL,USER,PASS);) {
						 	  Scanner sc=new Scanner(System.in);
						 	  System.out.println("Enter employee ID for delete");
						 	  int id=sc.nextInt();
						 	  String data = "DELETE FROM employee " +
						                    "WHERE id ="+id;
						 	  Statement stmt = connect.createStatement();
						 	  stmt.executeUpdate(data);
						 	  System.out.println("Data deleted Successfully");
		          } 
			      catch (SQLException e) {
			    	        System.out.println(); 
		         } 
		}
		     public void displayall() {
			      try (Connection connect=DriverManager
						 .getConnection(URL,USER,PASS); ) {
				              String data = "SELECT * FROM employee";
				              Statement s=connect.createStatement();
				              ResultSet rs = s.executeQuery(data);
		               while (rs.next()) {
		            	   	  System.out.println(rs.getInt("id") + "\t"
		            	   			  + rs.getString("name") + "\t"
		            	   			  + rs.getString("designation") + "\t"
		            	   			  + rs.getInt("age") + "\t"
		            	   			  + rs.getInt("salary"));
		               	}
		          } 
			      catch (SQLException e) {
			    	  System.out.println(e.getMessage());
		          }
		 }
		     public void update() {
		    	 try  (Connection connect=DriverManager
		    			 .getConnection(URL,USER,PASS);
		    			 Statement s = connect.createStatement();) {
		    		 		Scanner sc=new Scanner(System.in);	
		    		 		System.out.println("enter which colunm you need to change");
		    		 		String colunmname=sc.next();
		    		 		System.out.println("enter id to chage"+ colunmname );
		    		 		int id=sc.nextInt();
		    		 		System.out.println("Enetr value to change");
		    		 		String value=sc.next();
		    		 		String data5 ="update employee set " +" "+ colunmname + "='"+ value +"' "+ "where id= "+id;
		    		 		String name,age,designation;
		
		    		 		if("name".equals(colunmname)) {
		    		 			s.executeUpdate(data5);
		    		 		 }
			    	        else if("designation".equals(colunmname)) {
			    	        	s.executeUpdate(data5);
			    	        }
			    	        else if("age".equals(colunmname)) {
			    	        	s.executeUpdate(data5);
			    	        }
			    	        else {
			    	        	s.executeUpdate(data5);
			    	        }
		    		 	System.out.println("Data Updated Successfully!!!");
		    		 	String data = "SELECT * FROM employee";
		    		 	ResultSet rs = s.executeQuery(data);
		    		 	while (rs.next()) {
		    		 		System.out.println(rs.getInt("id") + "\t"
		    		 				+ rs.getString("name") + "\t"
		    		 				+ rs.getString("designation") + "\t"
		    		 				+ rs.getInt("age") + "\t"
		    		 				+ rs.getInt("salary"));
		    		 			} 
					 }
		    	 catch(Exception e) {
		    		 System.out.println(e);
		    	 }
		  }
  }



