package database;
import java.sql.*;
import java.util.*;
public class insertdb {
	  public String data="INSERT INTO employee"+
			   "(id, name, designation, age, salary)VALUES"+ 
		       "(?, ?, ?, ?, ?);";
   public static void main(String []args) {
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enetr how many rows insert");
	   int n=sc.nextInt();
	   for(int i=1;i<=n;i++) {			   
	    insertdb s=new insertdb();
	    s.insert();
   }
   }
   public void insert(){
	 
 try (Connection connect=DriverManager
		 .getConnection("jdbc:postgresql://localhost:2002/table",
				 "postgres","indhu@vtg");
		 Scanner sc=new Scanner(System.in);
		  PreparedStatement S=connect.prepareStatement(data)) {
	     System.out.println("Enter employee Id:");
	     S.setInt(1, sc.nextInt());
	     System.out.println("Enter employee Name:");
	     S.setString(2, sc.next());
	     System.out.println("Enter employee Designation:");
	     S.setString(3, sc.next());
	     System.out.println("Enetr employee Age:");
	     S.setInt(4,sc.nextInt());
	     System.out.println("Enter employee Salary:");
	     S.setInt(5, sc.nextInt());
	     S.addBatch();
	     S.executeUpdate();
	     System.out.println("Data Inserted Successfully !!!");
	}
   
 catch(Exception e) {
	 System.out.println();
 }
   }
   
}

