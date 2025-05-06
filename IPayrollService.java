package dao;

import java.util.Date;
import java.util.ArrayList;

import Model.Payroll;

public interface IPayrollService {
	void GeneratePayroll(Payroll payroll);
	Payroll GetPayrollById(int payrollId);
	ArrayList<Payroll> GetPayrollsForEmployee(int employeeId);
	ArrayList<Payroll> GetPayrollsForPeriod(Date startDate, Date endDate);
	
}