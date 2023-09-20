// This code is for establishing connection with MySQL
// database and retrieving data
import java.io.*;
import java.sql.*;
import java.util.*;

class main {
	public static void main(String[] args) throws Exception
	{
		String url
			= "jdbc:mysql://10.10.10.118/31375_db";
		String username = "te31375";
		String password = "te31375";
		String query; 
		PreparedStatement ps = null;
		Class.forName(
			"com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(
			url, username, password);
		System.out.println(
			"Connection Established successfully");
		Statement st = con.createStatement();
				
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter operation to be performed: ");
		int num = sc.nextInt();
		int id;
		String sql;
		switch(num) {
		case 1: 
			System.out.println("Insertion operation");
			System.out.println("Enter ID");
			id = sc.nextInt();
			System.out.println("Enter name");
			String name;
			name = sc.next();
			sql = "insert into names values("+ id + ",'" + name + "');";
			ps = con.prepareStatement(sql);
			ps.execute();
			System.out.println("Insertion successful");
			break;
		case 2:
			System.out.println("Delete operation");
			System.out.println("Enter ID to be deleted: ");
			id = sc.nextInt();
			sql = "delete from names where id="+ id;
			ps = con.prepareStatement(sql);
			ps.execute();
			System.out.println("Deletion successful");
			break;
		case 3:
			System.out.println("Update operation");
			System.out.println("Enter the id of the entry to be updated");
			id = sc.nextInt();
			System.out.println("Enter new value for Name: ");
			name = sc.next();
			sql = "update names set name = '"+ name + "'  where id = "+ id;
			ps = con.prepareStatement(sql);
			ps.execute();
			System.out.println("Update successful");
			break;
		case 4:
			ResultSet rs = null;
			try {
	            sql = "select * from names";
	            ps = con.prepareStatement(sql);
	            rs = ps.executeQuery();
	            System.out.println("id\t\tname");
	 
	            // Condition check
	            while (rs.next()) {
	 
	                id = rs.getInt("id");
	                name = rs.getString("name");
	                System.out.println(id + "\t\t" + name);
	            }
	        }
			catch (SQLException e) {
	            System.out.println(e);
	        }
	 
			break;
		}
		st.close(); 
		con.close(); 
		System.out.println("Connection Closed....");
		
	}
}
