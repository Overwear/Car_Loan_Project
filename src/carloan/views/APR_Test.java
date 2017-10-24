package carloan.views;

import static org.junit.Assert.*;

import org.junit.Test;

public class APR_Test {

	@Test
	public void APR_Test_1()
	{
		double APR = Formulas.CalculateAPR(1000, 3, 333.33);
		assertEquals(0.0, APR, .01);
	}
	
	@Test(expected = Exception.class)
	public void APR_Test_2()
	{
		double APR = Formulas.CalculateAPR(1000, 3, 333.32);
	}
	
	@Test
	public void APR_Test_3()
	{
		double APR = Formulas.CalculateAPR(10000, 60, 175.28);
		assertEquals(2.0, APR, .01);
	}

}
