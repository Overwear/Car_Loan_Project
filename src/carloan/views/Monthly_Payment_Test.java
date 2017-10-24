package carloan.views;

import static org.junit.Assert.*;

import org.junit.Test;

public class Monthly_Payment_Test {

	@Test
	public void Monthly_Payment_Test_1()
	{
		double monthly_payment = Formulas.CalculateMonthlyPayments(1000, 3, 0);
		assertEquals(333.33, monthly_payment, .01);
	}
	
	@Test
	public void Monthly_Payment_Test_2()
	{
		double monthly_payment = Formulas.CalculateMonthlyPayments(1000, 3, 0);
		double last_payment = Formulas.getLastPayment();
		assertEquals(333.34, monthly_payment, .009);
	}
	
	@Test
	public void Monthly_Payment_Test_3()
	{
		double monthly_payment = Formulas.CalculateMonthlyPayments(10000, 60, 1.99);
		assertEquals(175.23, monthly_payment, .05);
	}
	
	@Test
	public void Monthly_Payment_Test_4()
	{
		double monthly_payment = Formulas.CalculateMonthlyPayments(10000, 60, 1.99);
		double last_payment = Formulas.getLastPayment();
		assertEquals(175.45, last_payment, .05);
	}

}
