package dao;

import java.util.ArrayList;
import java.util.Date;

import Model.FinancialRecord;

public interface IFinancialRecordService {

	void AddFinancialRecord(int employeeId,String description,double amount,String recordType);
	
	FinancialRecord GetFinancialRecordById(int recordId);
	
	ArrayList<FinancialRecord> GetFinancialRecordsForEmployee(int employeeId);
	
	ArrayList<FinancialRecord> GetFinancialRecordsForDate(Date recordDate);
	
}
