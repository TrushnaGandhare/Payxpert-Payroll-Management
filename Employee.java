package Model;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
public class Employee {
	
	private int EmployeeID;
	Date DateOfBirth,JoiningDate,TerminationDate;
	private String FirstName, LastName, Gender, Email,PhoneNumber, Address, Position;
	private double bonus;
	private double basicSalary;
	private double overtimePay;





	public Employee() {		
	};
	
	
	
	
	public Employee(int employeeID, String firstName, String lastName, Date dateOfBirth, String gender, String email,
			String phoneNumber, String address, String position, Date joiningDate, Date terminationDate) {
		
		EmployeeID = employeeID;
		FirstName = firstName;
		LastName = lastName;
		DateOfBirth = dateOfBirth;
		Gender = gender;
		Email = email;
		PhoneNumber = phoneNumber;
		Address = address;
		Position = position;
		JoiningDate = joiningDate;
		TerminationDate = terminationDate;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	// Setter for overtimePay
	public void setOvertimePay(double overtimePay) {
		this.overtimePay = overtimePay;
	}
	public double getBasicSalary() {
		return basicSalary;
	}

	public double getOvertimePay() {
		return overtimePay;
	}
	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public int getEmployeeID() {
		return EmployeeID;
	}



	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}



	public String getFirstName() {
		return FirstName;
	}



	public void setFirstName(String firstName) {
		FirstName = firstName;
	}



	public String getLastName() {
		return LastName;
	}



	public void setLastName(String lastName) {
		LastName = lastName;
	}



	public Date getDateOfBirth() {
		return DateOfBirth;
	}



	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}



	public String getGender() {
		return Gender;
	}



	public void setGender(String gender) {
		Gender = gender;
	}



	public String getEmail() {
		return Email;
	}



	public void setEmail(String email) {
		Email = email;
	}



	public String getPhoneNumber() {
		return PhoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}



	public String getAddress() {
		return Address;
	}



	public void setAddress(String address) {
		Address = address;
	}



	public String getPosition() {
		return Position;
	}



	public void setPosition(String position) {
		Position = position;
	}



	public Date getJoiningDate() {
		return JoiningDate;
	}



	public void setJoiningDate(Date joiningDate) {
		JoiningDate = joiningDate;
	}



	public Date getTerminationDate() {
		return TerminationDate;
	}



	public void setTerminationDate(Date terminationDate) {
		TerminationDate = terminationDate;
	}



	public int CalculateAge(String dobirth) {
		LocalDate dob = LocalDate.parse(dobirth);
        LocalDate currentDate = LocalDate.now();
        int age =(Period.between(dob, currentDate)).getYears();
        return age;
	}

	@Override
    public String toString() {
    	return "Employee{" +
                "employeeID=" + EmployeeID +
                ", firstName='" + FirstName + '\'' +
                ", lastName='" + LastName + '\'' +
                ", dateOfBirth='" + DateOfBirth + '\'' +
                ", gender='" + Gender + '\'' +
                ", email='" + Email + '\'' +
                ", phoneNumber='" + PhoneNumber + '\'' +
                ", address='" + Address + '\'' +
                ", position='" + Position + '\'' +
                ", joiningDate='" + JoiningDate + '\'' +
                ", terminationDate='" + TerminationDate + '\'' 
                + '}';}


}