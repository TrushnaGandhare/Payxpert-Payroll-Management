package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;


import Model.Employee;
import Exception.EmployeeNotFoundException;
import util.DBUtil;

public class EmployeeService implements IEmployeeService{

	@Override
	
	public Employee GetEmployeeById(int employeeId) {
		
		try {
			String q1 = "SELECT * FROM employee WHERE EmployeeID = ?";
			PreparedStatement pstmt1 = DBUtil.connection.prepareStatement(q1) ;
			pstmt1.setInt(1, employeeId);
	        ResultSet rs1 = pstmt1.executeQuery();
	        // check Employee is present or not of given id
	        if(rs1.next()) {
	        	//employee is present in database 
	        	int eID=rs1.getInt("EmployeeID");
	        	String firstName=rs1.getString("FirstName");
	        	String lastName=rs1.getString("LastName");
	        	Date dateOfBirth=(Date)rs1.getDate("DateOfBirth");
	        	String gender=rs1.getString("Gender");
	        	String email=rs1.getString("Email");
	        	String phoneNumber=rs1.getString("PhoneNumber");
	        	String address=rs1.getString("Address");
	        	String position=rs1.getString("Position");
	        	Date joiningDate=(Date)rs1.getDate("JoiningDate");
	        	Date terminationDate=(Date)rs1.getDate("TerminationDate");
	        	Employee employee = new Employee(eID,firstName,lastName,dateOfBirth,gender,email
	        			,phoneNumber,address,position,joiningDate,terminationDate);
	        	
		        // Close the PreparedStatement
		        pstmt1.close();
		        // return employee
	        	return employee;
	        }else {
	        	// employee is not present in database so we are throwing Exception
	        	throw new EmployeeNotFoundException("Employee not Find of id : "+employeeId);
	        }
			
		}catch(EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Employee> GetAllEmployees() {
		
		try {
			String q1 = "SELECT * FROM employee";
			PreparedStatement pstmt1 = DBUtil.connection.prepareStatement(q1) ;
	        ResultSet rs1 = pstmt1.executeQuery();
	        //check employess data present in database or not
	        
	        //Employee type arrayList to store employee data
	        ArrayList<Employee> employeeList=new ArrayList<>();
	        while(rs1.next()) {
	        	int eID=rs1.getInt("EmployeeID");
	        	String firstName=rs1.getString("FirstName");
	        	String lastName=rs1.getString("LastName");
	        	Date dateOfBirth=(Date)rs1.getDate("DateOfBirth");
	        	String gender=rs1.getString("Gender");
	        	String email=rs1.getString("Gender");
	        	String phoneNumber=rs1.getString("PhoneNumber");
	        	String address=rs1.getString("Address");
	        	String position=rs1.getString("Position");
	        	Date joiningDate=(Date)rs1.getDate("JoiningDate");
	        	Date terminationDate=(Date)rs1.getDate("TerminationDate");
	        	Employee employee = new Employee(eID,firstName,lastName,dateOfBirth,gender,email
	        			,phoneNumber,address,position,joiningDate,terminationDate);
	        	
	        	employeeList.add(employee);
	        }
	        if(employeeList.isEmpty()) {
	        	throw new EmployeeNotFoundException("There is no Employee data in record");
	        }
	        return employeeList;
		}catch (EmployeeNotFoundException enf) {
			enf.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void AddEmployee(Employee employeeData) {
		
		try {
			String q1 = "INSERT INTO employee (EmployeeID, FirstName, LastName, DateOfBirth, Gender, " +
                    "Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = DBUtil.connection.prepareStatement(q1) ;
	        // Set the parameter values using the employeeData 
	        pstmt.setInt(1, employeeData.getEmployeeID());
	        pstmt.setString(2, employeeData.getFirstName());
	        pstmt.setString(3, employeeData.getLastName());
	        pstmt.setDate(4,new java.sql.Date(employeeData.getDateOfBirth().getTime()));
	        pstmt.setString(5, employeeData.getGender());
	        pstmt.setString(6, employeeData.getEmail());
	        pstmt.setString(7, employeeData.getPhoneNumber());
	        pstmt.setString(8, employeeData.getAddress());
	        pstmt.setString(9, employeeData.getPosition());
	        pstmt.setDate(10,new java.sql.Date (employeeData.getJoiningDate().getTime()));
	        pstmt.setDate(11, null);
	        
	        int affectedRows = pstmt.executeUpdate();
	        //check weather employee added or not
	        if (affectedRows > 0) {
	            System.out.println("Employee added successfully.");
	        } else {
	            System.out.println("Failed to add employee.");
	        }
	        
	        // Close the PreparedStatement
	        pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Employee UpdateEmployee(Employee employeeData) {
		
		try {
			
			String q1 = "SELECT * FROM employee WHERE EmployeeID = ?";
			PreparedStatement pstmt1 = DBUtil.connection.prepareStatement(q1) ;
			pstmt1.setInt(1, employeeData.getEmployeeID());
	        ResultSet rs1 = pstmt1.executeQuery();
	        //check employee is present in the database or not
	        if(!rs1.next()) {
	        	throw new EmployeeNotFoundException("Employee not Find of id : "+employeeData.getEmployeeID()+" to update");
	        }else {
				
				String updateQuery = "UPDATE employee SET FirstName=?, LastName=?, DateOfBirth=?, " +
	                    "Gender=?, Email=?, PhoneNumber=?, Address=?, Position=?, " +
	                    "JoiningDate=?, TerminationDate=? WHERE EmployeeID=?";

	            // Prepare the statement with the query
	            PreparedStatement pstmt = DBUtil.connection.prepareStatement(updateQuery);

	            // Set the parameter values using the employeeData object
	            pstmt.setString(1, employeeData.getFirstName());
	            pstmt.setString(2, employeeData.getLastName());
	            pstmt.setDate(3, (Date) employeeData.getDateOfBirth());
	            pstmt.setString(4, employeeData.getGender());
	            pstmt.setString(5, employeeData.getEmail());
	            pstmt.setString(6, employeeData.getPhoneNumber());
	            pstmt.setString(7, employeeData.getAddress());
	            pstmt.setString(8, employeeData.getPosition());
	            pstmt.setDate(9, (Date) employeeData.getJoiningDate());

	            pstmt.setDate(10, (Date) employeeData.getTerminationDate());
	            pstmt.setInt(11, employeeData.getEmployeeID());

	            // Execute the query
	            int rowsAffected = pstmt.executeUpdate();
            	pstmt.close();
            	pstmt1.close();
	            if (rowsAffected > 0) {
	            	System.out.println("Employee updated successfully.");
	            	return employeeData; // Return the updated employee 
				} else {
					System.out.println("Failed to update employee.");
					return null;
				}
	        }

		}catch (EmployeeNotFoundException enf) {
				enf.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return null;
	}

	@Override
	public void RemoveEmployee(int employeeId) {
		
		try {
			
			String q1 = "SELECT * FROM employee WHERE EmployeeID = ?";
			PreparedStatement pstmt1 = DBUtil.connection.prepareStatement(q1) ;
			pstmt1.setInt(1, employeeId);
	        ResultSet rs1 = pstmt1.executeQuery();
	        //check employee is present in the database or not
	        if(!rs1.next()) {
	        	throw new EmployeeNotFoundException("Employee not Find of id : "+employeeId+" to remove");
	        }
	        	//Query to delete employee from employee table
	            String deleteQuery = "DELETE FROM employee WHERE EmployeeID = ?";
	            PreparedStatement pstmt = DBUtil.connection.prepareStatement(deleteQuery);
	            pstmt.setInt(1, employeeId);
	            int rowsAffected = pstmt.executeUpdate();
	            
	            // check operation performed successfully or not
	            if (rowsAffected > 0) {
	                System.out.println("Employee removed successfully.");
	            } else {
	                System.out.println("Failed to remove employee. Employee ID not found.");
	            }
	        
			
		}catch (EmployeeNotFoundException enf) {
			enf.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


}
