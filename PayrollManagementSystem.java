package Main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.EmployeeService;
import dao.FinancialRecordService;
import dao.PayrollService;
import dao.TaxService;
import Model.Employee;
import Model.FinancialRecord;
import Model.Payroll;
import Model.Tax;
import util.DBUtil;

public class PayrollManagementSystem {
	
	static Employee employee=null;
	static ArrayList<Employee> empList=new ArrayList<>();
	static ArrayList<FinancialRecord> financialRecordsList=new ArrayList<>();
	static ArrayList<Payroll> payrollList=new ArrayList<>();
	static ArrayList<Tax> taxList=new ArrayList<>();
	
    private static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Error parsing date. Using current date instead.");
            return new Date();
        }
    }

	public static void main(String[] args) throws Exception {
	
		DBUtil.getConnection();
		Scanner scanner =new Scanner(System.in);
		System.out.println();
		System.out.println(" -------- PayXpert, The Payroll Management System --------");
		System.out.println(" Employee : 1");
		System.out.println(" Payroll : 2");
		System.out.println(" Tax : 3");
		System.out.println(" Employee FinancialRecord : 4");
		System.out.println(" Exit : 5");
		System.out.print("Select option : ");
		int option=scanner.nextInt();
		int option2=0;
		int empId=0;
		switch(option) {
		
		
		
		
		case 1:
			System.out.println();
			System.out.println("--- Employee ---");
			System.out.println("Get Employee By Id : 1");
			System.out.println("Get All Employees : 2");
			System.out.println("Add Employee : 3");
			System.out.println("Update Employee : 4");
			System.out.println("Remove Employee : 5");
			System.out.println(" Back : 6");
			System.out.print("Select option : ");
			option2=scanner.nextInt();
			switch(option2) {
			case 1:
				System.out.print("Enter Employee ID : ");
				empId=scanner.nextInt();
				employee= new EmployeeService().GetEmployeeById(empId);
				System.out.println(employee);
				break;
			case 2:
				System.out.println();
				empList=new EmployeeService().GetAllEmployees();
				for(Employee employee:empList) {
					System.out.println(employee);
				}
				break;
			case 3:
			    // Taking user input for employee details
		        System.out.print("Enter Employee ID: ");
		        int employeeID = scanner.nextInt();

		        System.out.print("Enter First Name: ");
		        String firstName = scanner.next();

		        System.out.print("Enter Last Name: ");
		        String lastName = scanner.next();

		        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
		        Date dateOfBirth = parseDate(scanner.next());

		        System.out.print("Enter Gender: ");
		        String gender = scanner.next();

		        System.out.print("Enter Email: ");
		        String email = scanner.next();

		        System.out.print("Enter Phone Number: ");
		        String phoneNumber = scanner.next();

		        System.out.print("Enter Address: ");
		        String address = scanner.next();

		        System.out.print("Enter Position: ");
		        String position = scanner.next();

		        System.out.print("Enter Joining Date (YYYY-MM-DD): ");
		        Date joiningDate = parseDate(scanner.next());

		        System.out.print("Enter Termination Date (or type null) (YYYY-MM-DD): ");
		        String terminationDateString = scanner.next();
		        Date terminationDate = terminationDateString.equals("null") ? null : parseDate(terminationDateString);
		        Employee employee = new Employee();
		        employee.setEmployeeID(employeeID);
		        employee.setFirstName(firstName);
		        employee.setLastName(lastName);
		        employee.setDateOfBirth(dateOfBirth);
		        employee.setGender(gender);
		        employee.setEmail(email);
		        employee.setPhoneNumber(phoneNumber);
		        employee.setAddress(address);
		        employee.setPosition(position);
		        employee.setJoiningDate(joiningDate);
		        employee.setTerminationDate(terminationDate);
		        new EmployeeService().AddEmployee(employee);
		        break;
			case 4:
				String ch1="";
				System.out.print("Enter Employee ID : ");
				empId=scanner.nextInt();
				do {
					System.out.println("Enter which data to update");
				System.out.println("First Name : 1");
				System.out.println("Last Name : 2");
				System.out.println("Date of Birth : 3");
				System.out.println("Gender : 4");
				System.out.println("Email : 5");
				System.out.println("Phone Number : 6");
				System.out.println("Address : 7");
				System.out.println("Position : 8");
				System.out.println("Joining Date : 9");
				System.out.println("Termination Date : 10");
				System.out.println("exit : 11");
				System.out.print("Enter choice : ");
				int ch=scanner.nextInt();
				scanner.nextLine();
					Employee obj=new EmployeeService().GetEmployeeById(empId);
					switch(ch) {
					case 1:System.out.print("Enter First Name : ");
						obj.setFirstName(scanner.nextLine());
						new EmployeeService().UpdateEmployee(obj);
						break;
					case 2:System.out.print("Enter Last Name : ");
						obj.setLastName(scanner.nextLine());
						new EmployeeService().UpdateEmployee(obj);
						break;
					case 3:System.out.print("Enter Date Of Birth : ");
						obj.setDateOfBirth(parseDate(scanner.nextLine()));
						new EmployeeService().UpdateEmployee(obj);
						break;
					case 4:System.out.print("Enter Gender : ");
						obj.setGender(scanner.next());
					    new EmployeeService().UpdateEmployee(obj);
					    break;
					case 5:System.out.print("Enter Email : ");
						obj.setEmail(scanner.next());
					   	new EmployeeService().UpdateEmployee(obj);
					   	break;
					case 6:System.out.print("Enter Phone Number : ");
						obj.setPhoneNumber(scanner.nextLine());
						new EmployeeService().UpdateEmployee(obj);
						break;
					case 7:System.out.print("Enter Address : ");
						obj.setAddress(scanner.nextLine());
						new EmployeeService().UpdateEmployee(obj);
						break;
					case 8:System.out.println("Enter Position : ");
						obj.setPosition(scanner.nextLine());
						new EmployeeService().UpdateEmployee(obj);
						break;
					case 9:System.out.println("Enter Joining Date : ");
						obj.setJoiningDate(parseDate(scanner.nextLine()));
						new EmployeeService().UpdateEmployee(obj);
						break;
					case 10://System.out.println("Enter termination Date : ");

//						obj.setTerminationDate(parseDate(scanner.nextLine()));
//						new EmployeeService().UpdateEmployee(obj);
						break;
					default :System.out.println("Exiting");
						break;
					}
					System.out.println(" Want to update same employee again (y/n)");
					ch1=scanner.next().toLowerCase();
				}while(ch1.equals("y"));
				break;
				
			case 5:
				System.out.print("Enter Employee ID : ");
				empId=scanner.nextInt();
				new EmployeeService().RemoveEmployee(empId);
				System.out.println(empId + "removed successfully");
				break;
			case 6:break;
			default :
			}
			break;
			
			
			
			
			
			
			
			
		case 2:
			System.out.println();
			System.out.println("--- Payroll ---");
			System.out.println(" Generate Payroll : 1");
			System.out.println(" Get Payroll By Id : 2");
			System.out.println(" Get Payrolls For Employee : 3");
			System.out.println(" Get Payrolls For Period : 4");
			System.out.println(" Back : 5");
			System.out.print("Select option : ");
			option2=scanner.nextInt();
			switch(option2) {
				case 1:
//					  Taking user input for payroll details
			        System.out.print("Enter Payroll ID: ");
			        int payrollID = scanner.nextInt();
	
			        System.out.print("Enter Employee ID: ");
			        int employeeID = scanner.nextInt();
	
			        System.out.print("Enter Pay Period Start Date (YYYY-MM-DD): ");
			        Date payPeriodStartDate = parseDate(scanner.next());
	
			        System.out.print("Enter Pay Period End Date (YYYY-MM-DD): ");
			        Date payPeriodEndDate = parseDate(scanner.next());
	
			        System.out.print("Enter Basic Salary: ");
			        double basicSalary = scanner.nextDouble();
	
			        System.out.print("Enter Overtime Pay: ");
			        double overtimePay = scanner.nextDouble();
	
			        System.out.print("Enter Deductions: ");
			        double deductions = scanner.nextDouble();
	
			        System.out.print("Enter Net Salary: ");
			        double netSalary = scanner.nextDouble();
	
			        // Creating a Payroll object with user input
			        Payroll payroll = new Payroll(payrollID,employeeID, payPeriodStartDate, payPeriodEndDate,
			                basicSalary, overtimePay, deductions, netSalary);
			        new PayrollService().GeneratePayroll(payroll);
					break;
				case 2:
					System.out.println();
					System.out.print("Enter Payroll Id : ");
					int payrollId=scanner.nextInt();
					Payroll payroll2=new PayrollService().GetPayrollById(payrollId);
					System.out.println(payroll2);
					break;
				case 3:
					System.out.println();
					System.out.print("Enter Employee Id : ");
					empId =scanner.nextInt();
					payrollList=new PayrollService().GetPayrollsForEmployee(empId);
					for(Payroll payroll3: payrollList) {
						System.out.println(payroll3);
					}
					break;
				case 4:
					System.out.println();
					scanner.nextLine()
;					System.out.print("Enter the Start Date : ");
					Date startDate = parseDate(scanner.nextLine());
					System.out.print("Enter the End Date : ");
					Date endDate = parseDate(scanner.nextLine());
					payrollList=new PayrollService().GetPayrollsForPeriod(new java.sql.Date(startDate.getTime()),new java.sql.Date(endDate.getTime()));
					for(Payroll payroll3: payrollList) {
						System.out.println(payroll3);
					}
					break;
					
				case 5:break;
				default :
				}
				break;
				
				
				
			
			
			
			
			
		case 3:
			System.out.println();
			System.out.println("--- Tax ---");
			System.out.println(" Calculate Tax : 1");
			System.out.println(" Get Tax By Id : 2");
			System.out.println(" Get Taxes For Employee : 3");
			System.out.println(" Get Taxes For Year : 4");
			System.out.println(" Back : 5");
			System.out.print("Select option : ");
			option2=scanner.nextInt();
			switch(option2) {
			case 1:
				System.out.print("Enter employee ID : ");
				empId = scanner.nextInt();
				System.out.println("Enter Year : ");
				int Year1 = scanner.nextInt();
		        Year year = Year.of(Year1);
		        Double calculatedTax=new TaxService().CalculateTax(empId, year);
		        System.out.println("Tax : "+calculatedTax);
		        break;
			case 2:
				System.out.println();
				System.out.print("Enter Tax ID : ");
				int taxId=scanner.nextInt();
				Tax tax = new TaxService().GetTaxById(taxId);
				System.out.println(tax);
				break;
			case 3:
				System.out.println();
				System.out.print("Enter employee id : ");
				empId=scanner.nextInt();
				taxList=new TaxService().GetTaxesForEmployee(empId);
				for(Tax tax2:taxList) {
					System.out.println(tax2);
				}
				break;
			case 4:
				System.out.println();
				System.out.print("Enter year : ");
				int Year2 = scanner.nextInt();
		        Year year1 = Year.of(Year2);
		        taxList=new TaxService().GetTaxesForYear(year1);
		        for(Tax tax3:taxList) {
		        	System.out.println(tax3);
		        }
		        break;
			case 5:
				break;
			default : System.out.println("Invalid Choice try again");
				break;
			}
			break;
			
			
			
			
			
			
			
			
			
			
		case 4:
			System.out.println();
			System.out.println("--- FinancialRecordService ---");
			System.out.println(" AddFinancialRecord : 1");
			System.out.println(" GetFinancialRecordById : 2");
			System.out.println(" GetFinancialRecordsForEmployee : 3");
			System.out.println(" GetFinancialRecordsForDate : 4");
			System.out.println(" Back : 5");
			System.out.print("Select option : ");
			option2=scanner.nextInt();
			switch(option2) {
			case 1:
				System.out.println();
				// Taking user input for FinancialRecord attributes
		        System.out.print("Enter Record ID: ");
		        int recordID = scanner.nextInt();
		        
		        System.out.print("Enter Employee ID: ");
		        int employeeID = scanner.nextInt();
		        
		        System.out.print("Enter Record Date (YYYY-MM-DD): ");
		        Date recordDate = parseDate(scanner.next());
		        
		        System.out.print("Enter Description: ");
		        scanner.nextLine();
		        String description = scanner.nextLine();
		        
		        System.out.print("Enter Amount: ");
		        double amount = scanner.nextDouble();
		        
		        System.out.print("Enter Record Type: ");
		        scanner.nextLine();
		        String recordType = scanner.nextLine();
		        
		        new FinancialRecordService().AddFinancialRecord(employeeID, description, amount, recordType);
		        System.out.println("successfull added");
		        break;
			case 2:
				System.out.println();
				System.out.println("Enter Record Id : ");
				int financialRecoedId=scanner.nextInt();
				FinancialRecord financialRecord=new FinancialRecordService().GetFinancialRecordById(financialRecoedId);
				System.out.println(financialRecord);
				break;
			case 3:
				System.out.println();
				System.out.println("Enter Employee ID : ");
				empId=scanner.nextInt();
				financialRecordsList = new FinancialRecordService().GetFinancialRecordsForEmployee(empId);
				for(FinancialRecord financialRecord2:financialRecordsList) {
					System.out.println(financialRecord2);
				}
				break;
			case 4:
				System.out.println();
				scanner.nextLine();
				System.out.println("Enter Date (YYYY-MM-DD): ");
				Date date=parseDate(scanner.nextLine());
				financialRecordsList = new FinancialRecordService().GetFinancialRecordsForDate(new java.sql.Date(date.getTime()));
				for(FinancialRecord financialRecord2:financialRecordsList) {
					System.out.println(financialRecord2);
				}
				break;
				
				
				
				
			case 5: break;
			
			default : System.out.println("wrong choice try again");
				break;
			}
			break;
			
		case 5:
			System.out.println(" System closed!");
			System.exit(0);
		
		}
	}

}


