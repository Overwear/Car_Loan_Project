package carloan.views;

import static org.junit.Assert.*;

import org.junit.Test;

public class Capital_Test {

	@Test
	public void Capital_Test_1()
	{
		double capital = Formulas.CalculateCapital(0, 3, 333.33);
		assertEquals(999.99, capital, .01);
	}
	
	public void Capital_Test_2()
	{
		double capital = Formulas.CalculateCapital(0, 3, 333.33);
		double last_payment = Formulas.getLastPayment();
		assertEquals(333.33, last_payment, .01);
	}

}
