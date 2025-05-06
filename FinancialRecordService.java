package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Model.FinancialRecord;
import Exception.EmployeeNotFoundException;
import Exception.FinancialRecordException;
import util.DBUtil;

public class FinancialRecordService implements IFinancialRecordService{

	@Override
	public void AddFinancialRecord(int employeeId, String description, double amount, String recordType) {
		try {
			String q1 = "SELECT 1 FROM Employee WHERE EmployeeID = ?";
			PreparedStatement pstmt1 = DBUtil.connection.prepareStatement(q1);
			pstmt1.setInt(1, employeeId);
			ResultSet rs1 = pstmt1.executeQuery(); 
		    if(rs1.next()) { // check for employee
			    //if employee present then 
				String q2 = "INSERT INTO FinancialRecord (EmployeeID, RecordDate, Description, Amount, RecordType) " +
						"VALUES (?, CURRENT_DATE, ?, ?, ?)";
				PreparedStatement pstmt2 = DBUtil.connection.prepareStatement(q2);
				pstmt2.setInt(1, employeeId);
				pstmt2.setString(2, description);
				pstmt2.setDouble(3, amount);
				pstmt2.setString(4, recordType);
				pstmt2.executeUpdate();
		    }
		    else {//if employee not present
		    	throw(new EmployeeNotFoundException("No employee with id:"+employeeId+" exists"));
		    	}
		}catch (EmployeeNotFoundException enfe) {
			enfe.printStackTrace();
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public FinancialRecord GetFinancialRecordById(int recordId) {
		try {
			String q1 = "SELECT * FROM FinancialRecord WHERE RecordID = ?";
			PreparedStatement pstmt1 = DBUtil.connection.prepareStatement(q1) ;
			pstmt1.setInt(1, recordId);
	        ResultSet rs1 = pstmt1.executeQuery();
	        if (rs1.next()) {//check if recordId present or not
	        	//if present
	        	int recordid= rs1.getInt("RecordID");
	        	int employeeid = rs1.getInt("EmployeeID");
	        	Date recordDate = rs1.getDate("RecordDate");
	        	String description = rs1.getString("Description");
	        	Double amount = rs1.getDouble("Amount");
	        	String recordtype = rs1.getString("RecordType");    	
	        	FinancialRecord fr = new FinancialRecord(recordid, employeeid, recordDate, description,recordtype, amount);
	        	return fr;
	        }
	        else {//if not present
	        	throw(new FinancialRecordException("No such record with record id "+recordId+" found"));
	        }
      	}catch (FinancialRecordException fre) {
      		fre.printStackTrace();
      	}catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public ArrayList<FinancialRecord> GetFinancialRecordsForEmployee(int employeeId) {
		try {
			//check if employee present in employee table 
			String q1 = "SELECT 1 FROM Employee WHERE EmployeeID = ?";
			PreparedStatement pstmt1 = DBUtil.connection.prepareStatement(q1);
			pstmt1.setInt(1, employeeId);
			ResultSet rs1 = pstmt1.executeQuery(); 
		    if(!rs1.next()) { // check for employee
		    	throw(new EmployeeNotFoundException("employee with empid: "+employeeId+" doesnot exist"));
		    }
		    
		    //if present
			ArrayList<FinancialRecord> financialRecords = new ArrayList<FinancialRecord>();
	        String q2 = "SELECT * FROM financialrecord WHERE EmployeeID = ?";
	        PreparedStatement pstmt2 = DBUtil.connection.prepareStatement(q2);
	        pstmt2.setInt(1, employeeId);
	        ResultSet rs2 = pstmt2.executeQuery();
            while (rs2.next()) {
            	int recordid= rs2.getInt("RecordID");
	        	int employeeid = rs2.getInt("EmployeeID");
	        	Date recordDate = rs2.getDate("RecordDate");
	        	String description = rs2.getString("Description");
	        	Double amount = rs2.getDouble("Amount");
	        	String recordtype = rs2.getString("RecordType");    	
	        	FinancialRecord fr = new FinancialRecord(recordid, employeeid, recordDate, description,recordtype, amount);
	        	financialRecords.add(fr);
            }
	        if(financialRecords.isEmpty()) {
	        	throw (new FinancialRecordException("No record found for employeeId: "+employeeId));
	        }   
	        return financialRecords;
		}catch(EmployeeNotFoundException enfe){
			enfe.printStackTrace();
		}catch(FinancialRecordException fre){
			fre.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<FinancialRecord> GetFinancialRecordsForDate(Date recordDate) {
		 ArrayList<FinancialRecord> financialRecords = new ArrayList<>();
		 
		 try {
			 String q1 = "SELECT * FROM FinancialRecord WHERE RecordDate = ?";
			 PreparedStatement pstmt1 = DBUtil.connection.prepareStatement(q1) ;
			 java.sql.Date sqlRecordDate = new java.sql.Date(recordDate.getTime());
			 pstmt1.setDate(1, sqlRecordDate);
			 ResultSet rs1 = pstmt1.executeQuery();
			 while (rs1.next()) {
				int recordid= rs1.getInt("RecordID");
				int employeeid = rs1.getInt("EmployeeID");
				Date recorddate = rs1.getDate("RecordDate");
				String description = rs1.getString("Description");
				Double amount = rs1.getDouble("Amount");
				String recordtype = rs1.getString("RecordType");    	
					FinancialRecord fr = new FinancialRecord(recordid, employeeid, recorddate, description,recordtype, amount);
					financialRecords.add(fr);
				}
			 if(financialRecords.isEmpty()) {
				 throw (new FinancialRecordException("Financial Records on RecordDate: "+recordDate+" doesnot exist"));
		        }   
		        return financialRecords;
		 }catch(FinancialRecordException enfe){
			 enfe.printStackTrace();
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		return null;
	}

}
