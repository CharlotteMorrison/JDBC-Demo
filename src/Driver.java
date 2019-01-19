
import java.sql.*;
public class Driver {


	public static void main(String[] args) throws SQLException {
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "Isabella1";
		Connection myConn = null;
		//Statement myStmt = null;
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		
		try {
			/*
			//Part 1-4 no longer work- new database set, use the format below for 1-4 only			
			Connection myConn = DriverManager.getConnection(url, user, password);
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from employees");
	
			//1. print a list of values
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + " , " + myRs.getString("first_name"));
			}
			
			//2. Insert a record
			String sql = "insert into employees "
					+ " (last_name, first_name, email)"
					+ " values ('Brown', 'David', 'david.brown@foo.com')";
			myStmt.executeUpdate(sql);			
			System.out.println("Insert Complete.");

			//3. Update a record
			String sql = "update employees "
					+ " set email='demo@luvecode.com'"
					+ " where id=4";
			myStmt.executeUpdate(sql);			
			System.out.println("Update Complete.");			

			//4. Delete a record
			String sql = "delete from employees where last_name='brown'";
			
			int rowsAffected = myStmt.executeUpdate(sql);
			
			System.out.println("Rows Affected: " + rowsAffected);
			System.out.println("Delete Complete.");
			*/
			
			
		//5. Prepared statments: precompiled sql statement
			//Set parameter placeholders using a ?
		
		    myConn = DriverManager.getConnection(url, user, password);	
		    myStmt = myConn.prepareStatement("select * from employees where salary > ? and department=?");
			//delete statement- use the rows affected code above
		    //myStmt = myConn.prepareStatement("delete from employees where salary > ? and department=?");	    
		    
			//set parameters
			myStmt.setDouble(1, 80000);
			myStmt.setString(2, "Legal");

			myRs = myStmt.executeQuery();
			display(myRs);
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

	}
	private static void display(ResultSet myRs) throws SQLException {
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");
			
			System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
		}
	}	

}
