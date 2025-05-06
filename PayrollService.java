package dao;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Employee;
import Model.Payroll;
import Exception.PayrollGenerationException;
import util.DBUtil;

public class PayrollService implements IPayrollService{

	@Override
	public void GeneratePayroll(Payroll payroll) {
		
		try {
			
			String q1 = "INSERT INTO Payroll (PayrollID,EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = DBUtil.connection.prepareStatement(q1) ;
            pstmt.setInt(1, payroll.getPayrollID());
            pstmt.setInt(2, payroll.getEmployeeID());
            pstmt.setDate(3, new java.sql.Date(payroll.getPayPeriodStartDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(payroll.getPayPeriodEndDate().getTime()));
            pstmt.setDouble(5, payroll.getBasicSalary());
            pstmt.setDouble(6, payroll.getOvertimePay());
            pstmt.setDouble(7, payroll.getDeductions());
            pstmt.setDouble(8, payroll.getNetSalary());
            
	        int affectedRows = pstmt.executeUpdate();
	        //check query successfully get executed or not
            if (affectedRows > 0) {
                System.out.println("Payroll added successfully.");
            } else {
            	throw new PayrollGenerationException("Failed to Generate payroll.");
            }
			
		}catch (PayrollGenerationException pge) {
			pge.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Payroll GetPayrollById(int payrollId) {
		
		try {
			
			String q1 = "SELECT * FROM Payroll WHERE PayrollID = ?";
			PreparedStatement pstmt = DBUtil.connection.prepareStatement(q1) ;
			pstmt.setInt(1, payrollId);
			ResultSet resultSet = pstmt.executeQuery();
			if(!resultSet.next()) {
				System.out.println("Payroll Data is Empty");
				return null;
			}else {
				//extract data in local variables
		        int payrollID1 = resultSet.getInt("PayrollID");
		        int employeeID = resultSet.getInt("EmployeeID");
		        Date payPeriodStartDate = resultSet.getDate("PayPeriodStartDate");
		        Date payPeriodEndDate = resultSet.getDate("PayPeriodEndDate");
		        double basicSalary = resultSet.getDouble("BasicSalary");
		        double overtimePay = resultSet.getDouble("OvertimePay");
		        double deductions = resultSet.getDouble("Deductions");
		        double netSalary = resultSet.getDouble("NetSalary");
		        Payroll payroll=new Payroll(payrollID1,employeeID,payPeriodStartDate,payPeriodEndDate,
		        		basicSalary,overtimePay,deductions,netSalary);
		        return payroll;
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Payroll> GetPayrollsForEmployee(int employeeId) {
		
		try {
			
			String q1 = "SELECT * FROM Payroll WHERE EmployeeID = ?";
			PreparedStatement pstmt = DBUtil.connection.prepareStatement(q1) ;
			pstmt.setInt(1, employeeId);
			ResultSet resultSet = pstmt.executeQuery();
		 
				ArrayList<Payroll> empPayrolllist=new ArrayList<>();
				while(resultSet.next()) {
			        int payrollID1 = resultSet.getInt("PayrollID");
			        int employeeID = resultSet.getInt("EmployeeID");
			        Date payPeriodStartDate = resultSet.getDate("PayPeriodStartDate");
			        Date payPeriodEndDate = resultSet.getDate("PayPeriodEndDate");
			        double basicSalary = resultSet.getDouble("BasicSalary");
			        double overtimePay = resultSet.getDouble("OvertimePay");
			        double deductions = resultSet.getDouble("Deductions");
			        double netSalary = resultSet.getDouble("NetSalary");
			        Payroll payroll=new Payroll(payrollID1,employeeID,payPeriodStartDate,payPeriodEndDate,
			        		basicSalary,overtimePay,deductions,netSalary);
			        empPayrolllist.add(payroll);
				}
				if(empPayrolllist.isEmpty()) {
					System.out.println("Payroll Data is Empty for employee id : "+employeeId);
					return null;
				}
				return empPayrolllist;
			
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Payroll> GetPayrollsForPeriod(Date startDate, Date endDate) {
		
		try {
			
			String q1 = "SELECT * FROM Payroll WHERE PayPeriodStartDate BETWEEN ? AND ?";
			PreparedStatement pstmt = DBUtil.connection.prepareStatement(q1) ;
            pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
            pstmt.setDate(2, new java.sql.Date(endDate.getTime()));
			ResultSet resultSet = pstmt.executeQuery();
			
				ArrayList<Payroll> empPayrolllist=new ArrayList<>();
				while(resultSet.next()) {
			        int payrollID1 = resultSet.getInt("PayrollID");
			        int employeeID = resultSet.getInt("EmployeeID");
			        Date payPeriodStartDate = resultSet.getDate("PayPeriodStartDate");
			        Date payPeriodEndDate = resultSet.getDate("PayPeriodEndDate");
			        double basicSalary = resultSet.getDouble("BasicSalary");
			        double overtimePay = resultSet.getDouble("OvertimePay");
			        double deductions = resultSet.getDouble("Deductions");
			        double netSalary = resultSet.getDouble("NetSalary");
			        Payroll payroll=new Payroll(payrollID1,employeeID,payPeriodStartDate,payPeriodEndDate,
			        		basicSalary,overtimePay,deductions,netSalary);
			        empPayrolllist.add(payroll);
				}
				if(empPayrolllist.isEmpty()) {
					System.out.println("Payroll Data not found ");
					return null;
				}
				return empPayrolllist;
			
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
		
	}
	public double calculateGrossSalary(Employee emp) {
		double basic = emp.getBasicSalary();
		double overtime = emp.getOvertimePay();
		double bonus = emp.getBonus(); // Make sure you have this field
		return basic + overtime + bonus;
	}



}