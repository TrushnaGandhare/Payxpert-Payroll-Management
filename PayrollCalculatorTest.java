package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import payroll.PayrollCalculator;

public class PayrollCalculatorTest {

    @Test
    public void testCalculateNetSalaryAfterDeductions() {
        PayrollCalculator calculator = new PayrollCalculator();

        double basicSalary = 50000;
        double tax = 5000;
        double insurance = 2000;

        double expectedNetSalary = 43000;
        double actualNetSalary = calculator.calculateNetSalary(basicSalary, tax, insurance);

        assertEquals(expectedNetSalary, actualNetSalary, 0.001);
    }

    @Test
    public void testVerifyTaxCalculationForHighIncomeEmployee() {
        PayrollCalculator calculator = new PayrollCalculator();

        double income = 200000;
        double expectedTax = 60000;
        double actualTax = calculator.calculateTax(income);

        assertEquals(expectedTax, actualTax, 0.001);
    }
    @Test
    public void testCalculateGrossSalaryForEmployee() {
        PayrollCalculator calculator = new PayrollCalculator();

        double basic = 40000;
        double hra = 10000;
        double allowances = 5000;

        double expectedGross = 55000;
        double actualGross = calculator.calculateGrossSalary(basic, hra, allowances);

        assertEquals(expectedGross, actualGross, 0.001);
    }
}

