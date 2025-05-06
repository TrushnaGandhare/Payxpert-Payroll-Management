package payroll;

public class PayrollCalculator {

    public double calculateNetSalary(double salary, double tax, double insurance) {
        return salary - tax - insurance;
    }


        public double calculateTax(double income) {
            if (income > 100000) {
                return income * 0.30; // Example: flat 30% for high-income
            } else if (income > 50000) {
                return income * 0.20;
            } else if (income > 25000) {
                return income * 0.10;
            } else {
                return 0;
            }
        }

    public double calculateGrossSalary(double basic, double hra, double allowances) {
        return basic + hra + allowances;
    }
}

