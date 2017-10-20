package carloan.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.lang.Math;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import java.awt.TextField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarLoanGui extends JFrame {

	private JPanel contentPane;
	private JTextField txtCapital;
	private JTextField txtNumberOfMonths;
	private JTextField txtAPR;
	private JTextField txtMonthlyPayment;
	private JButton btnCalculate;
	private JButton btnAddToGraph;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarLoanGui frame = new CarLoanGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CarLoanGui() 
	{
		initComponents();
		createEvents();

	}

	/*********************************************************
	 * This method contains all of the code for creating
	 * and initializing Components
	 ********************************************************/
	private void initComponents() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 444);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtCapital = new JTextField();
		txtCapital.setColumns(10);
		
		JLabel lblCapital = new JLabel("Capital");
		lblCapital.setBackground(SystemColor.info);
		lblCapital.setForeground(Color.BLACK);
		
		JLabel lblNumberOfMonths = new JLabel("Number of Months");
		
		txtNumberOfMonths = new JTextField();
		txtNumberOfMonths.setColumns(10);
		
		JLabel lblAPR = new JLabel("APR");
		
		txtAPR = new JTextField();
		txtAPR.setColumns(10);
		
		JLabel lblMonthlyPayment = new JLabel("Monthly Payments");
		
		txtMonthlyPayment = new JTextField();
		txtMonthlyPayment.setColumns(10);
		
		btnAddToGraph = new JButton("Add To Graph");
		btnAddToGraph.setBackground(SystemColor.menu);
		btnCalculate = new JButton("Calculate");
		btnCalculate.setBackground(SystemColor.menu);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNumberOfMonths)
						.addComponent(lblCapital)
						.addComponent(lblAPR)
						.addComponent(lblMonthlyPayment))
					.addGap(72)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtMonthlyPayment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNumberOfMonths, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCapital, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAPR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(105))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(258, Short.MAX_VALUE)
					.addComponent(btnAddToGraph)
					.addGap(18)
					.addComponent(btnCalculate)
					.addGap(40))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCapital, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCapital))
					.addGap(51)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNumberOfMonths, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumberOfMonths))
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtAPR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAPR))
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtMonthlyPayment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMonthlyPayment))
					.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddToGraph)
						.addComponent(btnCalculate))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	/**********************************************************
	 * This method contains all of the code for creating events
	 *********************************************************/
	private void createEvents() 
	{
		btnCalculate.addActionListener(new ActionListener() 
		{
			
			public boolean isCapitalEntered()
			{
				boolean isEntered = false;
				isEntered = txtCapital.getText().isEmpty();
				return !isEntered;
			}
			
			public boolean isNumOfMonthEntered()
			{
				boolean isEntered = false;
				isEntered = txtNumberOfMonths.getText().isEmpty();
				return !isEntered;
			}
			public boolean isAPREntered()
			{
				boolean isEntered = false;
				isEntered = txtAPR.getText().isEmpty();
				return !isEntered;
			}
			public boolean isMonthlyPaymentEntered()
			{
				boolean isEntered = false;
				isEntered = txtMonthlyPayment.getText().isEmpty();
				return !isEntered;
			}
			
			public double CalculateMonthlyPayments(double capital, int months, double apr)
			{
				double result = 0;
				double x = Math.pow(((apr/1200.0)+1), ((double)months));
				result = ((capital * (apr/1200.0)) *  x) / (x - 1);
				return result;
			}
			
			public double CalculateAPR(double capital, int months, double monthly_payment)
			{
				double previous_value = 0;
				double inter_value = 0;
				double r_prime = 5.0;
				double R_result = CalculateMonthlyPayments(capital, months, r_prime);
				double delta = R_result - monthly_payment;;
				while ( (delta < -.001) || (delta > .001) )
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
				
				return r_prime;
			}
			
			
			public double CalculateCapital(double apr, int months, double monthly_payment)
			{
				double result = 0;
				double x = Math.pow(((apr/1200.0)+1), ((double)months));
				result = (monthly_payment * (x-1))/((apr/1200)*x);
				return result;
			}
			
			public int CalculateNumberOfMonths(double apr, double capital, double monthly_payment)
			{
				int result = 0;
				double x = Math.log(1/(1-(capital*(apr/1200)/monthly_payment)));
				double y = Math.log(1 + (apr/1200));
				result = (int)Math.round(x/y);
				return result;
			}
			
			public void actionPerformed(ActionEvent e) 
			{
				//Parse variables into number respective format
				double capital = 0;
				int months = 0;
				double apr = 0;
				double monthly_payment = 0;
				
				
				if (txtCapital.getText().isEmpty() == false)
				{
					capital = Double.parseDouble(txtCapital.getText());
				}
				if (txtNumberOfMonths.getText().isEmpty() == false)
				{
					months = Integer.parseInt(txtNumberOfMonths.getText());
				}
				if (txtAPR.getText().isEmpty() == false)
				{
					apr = Double.parseDouble(txtAPR.getText());
				}
				if(txtMonthlyPayment.getText().isEmpty() == false)
				{
					monthly_payment = Double.parseDouble(txtMonthlyPayment.getText());
				}
				
				
				//Calculate missing variable

				//Calculate Monthly Payment
				if (isCapitalEntered() && isNumOfMonthEntered() && isAPREntered())
				{
					double calc_payment = CalculateMonthlyPayments(capital, months, apr);
					txtMonthlyPayment.setText(Double.toString(calc_payment));
				}
				//Calculate APR
				else if (isCapitalEntered() && isNumOfMonthEntered() && isMonthlyPaymentEntered())
				{
					double calc_apr = CalculateAPR(capital, months, monthly_payment);
					txtAPR.setText(Double.toString(calc_apr));
				}

				//Calculate Capital
				else if (isAPREntered() && isNumOfMonthEntered() && isMonthlyPaymentEntered())
				{
					double calc_capital = CalculateCapital(apr, months, monthly_payment);
					txtCapital.setText(Double.toString(calc_capital));
				}
				//Calculate Number of Months
				else if (isAPREntered() && isCapitalEntered() && isMonthlyPaymentEntered())
				{
					int calc_months = CalculateNumberOfMonths(apr, capital, monthly_payment);
					txtNumberOfMonths.setText(Integer.toString(calc_months));
				}

				/*
				//else invalid combination
				else
				{
					//prompt user that we need more inputs
				}
				*/
			}

		});
		
		btnAddToGraph.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//add variables to graph
			}
		});
		
	}
}
