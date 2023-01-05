package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.springframework.http.ReactiveHttpOutputMessage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/1")
public class Ex1 {
	static final String URL = "jdbc:postgresql://localhost:2002/table";
	static final String USER = "postgres";
	static final String PASS = "indhu@vtg";

	@GetMapping("/2")
	public String data() {
		try {
			Connection connect = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Data Base Connected Successfilly");
		} catch (Exception e) {
			System.out.println();
		}
		return ("Data Base connected");
	}

	@GetMapping("/displayall")
	public void displayall() {
		try (Connection connect = DriverManager.getConnection(URL, USER, PASS);) {
			String data = "SELECT * FROM employee";
			Statement s = connect.createStatement();
			ResultSet rs = s.executeQuery(data);
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getString("designation")
						+ "\t" + rs.getInt("age") + "\t" + rs.getInt("salary"));
			}

		} catch (Exception e) {
			System.out.println();
		}
	}

	@GetMapping("display/{id}")
	public String display(@PathVariable("id") int id) {

		try (Connection connect = DriverManager.getConnection(URL, USER, PASS);) {

			String data = "SELECT id,name,designation,age,salary " + "FROM employee " + "WHERE id =" + id;
			Statement s = connect.createStatement();
			ResultSet rs = s.executeQuery(data);
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getString("designation")
						+ "\t" + rs.getInt("age") + "\t" + rs.getInt("salary"));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return ("display Successfully");

	}

    @PostMapping("/insert")
	public String insert( int id,String name, String designation, int age,int salary) {
		try (Connection connect = DriverManager.getConnection(URL, USER, PASS);) {
			// Scanner sc=new Scanner(System.in); String
			String data = "INSERT INTO employee" + "(Id, Name, Designation, Age, Salary)VALUES"
					+ "(?,?,?,?,?);";
			PreparedStatement ps = connect.prepareStatement(data);
			// System.out.println("Enter how many row insert"); //int N=sc.nextInt(); //(int
			// i=1;i<=N;i++) { System.out.println("Enter employee Id:"); 
			ps.setInt(1,id);
			ps.setString(2, name);
			ps.setString(3, designation);
			ps.setInt(4, age);
			ps.setInt(5, salary);
			ps.executeUpdate();
			System.out.println("Data Inserted Successfully !!!");
		} catch (Exception e) {
			System.out.println();
		}
		return ("Inserted Successfully");
	}

	@DeleteMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		try (Connection connect = DriverManager.getConnection(URL, USER, PASS);) {
			String data = "DELETE FROM employee " + "WHERE id =" + id;
			Statement stmt = connect.createStatement();
			stmt.executeUpdate(data);
			System.out.println("Data deleted Successfully");
		} catch (SQLException e) {
			System.out.println();
		}
		return ("Deleted Sucessfully");
	}
}
