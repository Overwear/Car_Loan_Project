package carloan.views;

import java.lang.Math;

public class Formulas 
{
	private static double Last_Payment;
	
	public static double getLastPayment()
	{
		return Last_Payment;
	}
	public static double CalculateMonthlyPayments(double capital, int months, double apr)
	{
		double result = 0;
		if(apr == 0.0)
		{
			result = capital/months;
			double fraction = result - (int) result;
			fraction = fraction * 100;
			fraction = fraction - (int) fraction;
			fraction = (fraction/100) * months;
			Last_Payment = result + fraction;
			Last_Payment = Math.round(Last_Payment*100.0)/100.0;
		}
		else
		{
			double x = Math.pow(((apr/1200.0)+1), ((double)months));
			result = ((capital * (apr/1200.0)) *  x) / (x - 1);
			double fraction = result - (int) result;
			fraction = fraction * 100;
			fraction = fraction - (int) fraction;
			fraction = (fraction/100) * months;
			Last_Payment = result + fraction;
			Last_Payment = Math.round(Last_Payment*100.0)/100.0;
		}
		return result;
	}
	
	public static double CalculateAPR(double capital, int months, double monthly_payment)
	{
		double previous_value = 0;
		double inter_value = 0;
		double r_prime = 5.0;
		double R_result = CalculateMonthlyPayments(capital, months, r_prime);
		double delta = R_result - monthly_payment;
		if(((monthly_payment*months) - capital) < -.02)
		{
			throw new ArithmeticException("Monthly Payment * Months Is Less Than Capital!");
		}
		while ( (delta < -.00) || (delta > .005) )
		{
			if (delta > 0)
			{
				if (inter_value == 0)
				{
					previous_value = r_prime;
					inter_value = r_prime/2;
					r_prime = r_prime - inter_value;
				}
				else
				{
					inter_value = Math.abs(r_prime - (previous_value/2));
					previous_value = r_prime;
					r_prime = r_prime - inter_value;
			 	}
			}
			else if (delta < 0)
			{
				if (inter_value == 0)
				{
					previous_value = r_prime;
					inter_value = r_prime/2;
					r_prime = r_prime + inter_value;
				}
				else
				{
					inter_value = Math.abs((previous_value - r_prime)/2);
					previous_value = r_prime;
					r_prime = r_prime + inter_value;
				}
			}
			R_result = CalculateMonthlyPayments(capital, months, r_prime);
			delta = R_result - monthly_payment;
		}
		
		if(r_prime < .001 && r_prime > 0)
		{
			r_prime = 0;
		}
		Last_Payment = Math.round(R_result*100.00)/100.00;
		return r_prime;
	}
	
	public static double CalculateCapital(double apr, int months, double monthly_payment)
	{
		double result = 0;
		if(apr == 0.0)
		{
			result = monthly_payment * months;
		}
		else if (apr < 0.0)
		{
			throw new ArithmeticException("APR Is Negative");
		}
		else if (monthly_payment <= 0.0)
		{
			throw new ArithmeticException("Monthly Payment Is Too Small");
		}
		else
		{
			double x = Math.pow(((apr/1200.0)+1), ((double)months));
			result = (monthly_payment * (x-1))/((apr/1200)*x);
		}
		Last_Payment = monthly_payment;
		return result;
	}
	
	public static int CalculateNumberOfMonths(double apr, double capital, double monthly_payment)
	{
		int result = 0;
		if(apr == 0.0)
		{
			result = (int)(capital/monthly_payment);
		}
		else if (monthly_payment == 0.0)
		{
			throw new ArithmeticException("Divide By Zero Error");
		}
		else if (capital <= 0.0)
		{
			throw new ArithmeticException("Capital Is Too Small");
		}
		else
		{
			double x = Math.log(1/(1-(capital*(apr/1200)/monthly_payment)));
			double y = Math.log(1 + (apr/1200));
			result = (int)Math.round(x/y);
		}
		Last_Payment = monthly_payment;
		return result;
	}
}