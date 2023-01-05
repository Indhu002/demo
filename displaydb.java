package database;

import java.sql.*;
import java.util.*;

public class displaydb {
	 public static void main(String[] args) {
	    	Scanner sc=new Scanner(System.in);
	    	System.out.println("Enter number to hoe many rows display");
	    	int n=sc.nextInt();
	    	for(int i=1;i<=n;i++) {
	    	System.out.println("Enter your ID for display");
	        displaydb s = new displaydb();
	        s.display(sc.nextInt());
	    	}
	    }
	  public void display(int id) {
	        String sql = "SELECT id,name,designation,age,salary "
	                + "FROM employee "
	                + "WHERE id = ?";

	        try (Connection connect=DriverManager
	       		 .getConnection("jdbc:postgresql://localhost:2002/table",
	    				 "postgres","indhu@vtg");
	                PreparedStatement s = connect.prepareStatement(sql)) {

	            s.setInt(1, id);
	            ResultSet rs = s.executeQuery();
	            while (rs.next()) {
	                System.out.println(rs.getInt("id") + "\t"
	                        + rs.getString("name") + "\t"
	                        + rs.getString("designation") + "\t"
	                        + rs.getInt("age") + "\t"
	                        + rs.getInt("salary"));
	            }
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
	    }
}


