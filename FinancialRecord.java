package Model;

import java.util.Date;

public class FinancialRecord {
	private int RecordID, EmployeeID;
	private Date RecordDate;
	private String Description, RecordType;
	private double Amount;
	
	public FinancialRecord(){};
	
	public FinancialRecord(int recordID, int employeeID, Date recordDate, String description, String recordType,
			double amount) {
		RecordID = recordID;
		EmployeeID = employeeID;
		RecordDate = recordDate;
		Description = description;
		RecordType = recordType;
		Amount = amount;
	}
	
	public int getRecordID() {
		return RecordID;
	}
	public void setRecordID(int recordID) {
		RecordID = recordID;
	}
	
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}

	public Date getRecordDate() {
		return RecordDate;
	}
	public void setRecordDate(Date recordDate) {
		RecordDate = recordDate;
	}

	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}

	public String getRecordType() {
		return RecordType;
	}
	public void setRecordType(String recordType) {
		RecordType = recordType;
	}

	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount) {
		Amount = amount;
	}
		
	@Override
	public String toString() {
		return "FinancialRecord [RecordID=" + RecordID + ", EmployeeID=" + EmployeeID + ", RecordDate=" + RecordDate + ", Description=" + Description + ", RecordType=" + RecordType + ", Amount=" + Amount + "]";
	}
	
}
