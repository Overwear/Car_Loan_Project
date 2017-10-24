package carloan.views;

import static org.junit.Assert.*;

import org.junit.Test;

public class Months_Test {

	@Test
	public void Months_Test_1()
	{
		int months = Formulas.CalculateNumberOfMonths(0, 10000, 333.33);
		assertEquals(30, months);
	}
	
	@Test
	public void Months_Test_2()
	{
		int months = Formulas.CalculateNumberOfMonths(2, 25000, 250);
		assertEquals(109, months);
	}
	

}
