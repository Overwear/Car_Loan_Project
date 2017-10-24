package carloan.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.lang.Math;
import java.text.*;
import java.awt.print.*;


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
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class CarLoanGui extends JFrame {

	private JPanel contentPane;
	private JTextField txtCapital;
	private JTextField txtNumberOfMonths;
	private JTextField txtAPR;
	private JTextField txtMonthlyPayment;
	private JButton btnCalculate;
	private JButton btnAddToGraph;
	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	private JButton btnPrint;
	private double Last_Payment;
	private boolean hasCapital = false;
	private boolean hasAPR = false;
	private boolean hasNumOfMonths = false;
	private boolean hasMonthlyPayment = false;
	private int counter = 0;
	
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
	
    public static void popup(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, "ERROR: " + title, JOptionPane.INFORMATION_MESSAGE);
    }
    
	private boolean isCapitalEntered()
	{
		return !(txtCapital.getText().isEmpty());
	}
	
	private boolean isNumOfMonthEntered()
	{
		return !(txtNumberOfMonths.getText().isEmpty());
	}
	 
	private boolean isAPREntered()
	{
		return !(txtAPR.getText().isEmpty());
	}
	
	private boolean isMonthlyPaymentEntered()
	{
		return !(txtMonthlyPayment.getText().isEmpty());
	}
	
	private boolean isThreeInputsEntered()
	{
		return((hasCapital && hasAPR && hasNumOfMonths)
		|| (hasCapital && hasAPR && hasMonthlyPayment)
		|| (hasNumOfMonths && hasAPR && hasMonthlyPayment)
		|| (hasNumOfMonths && hasCapital && hasMonthlyPayment));
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
		setBounds(100, 100, 638, 456);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		btnPrint = new JButton("Print");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(399, Short.MAX_VALUE)
					.addComponent(btnPrint)
					.addGap(29))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnPrint)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		Object[] columns = {"Loan Value", "# Of Months", "APR", "Monthly Payment", "Final Payment"};
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		JLabel lblCapital = new JLabel("Capital");
		lblCapital.setBackground(SystemColor.info);
		lblCapital.setForeground(Color.BLACK);
		
		txtCapital = new JTextField();
		txtCapital.setColumns(10);
		
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
		btnCalculate.setEnabled(false);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnAddToGraph)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblMonthlyPayment)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtMonthlyPayment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(6)
							.addComponent(btnCalculate))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNumberOfMonths)
								.addComponent(lblAPR)
								.addComponent(lblCapital))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCapital, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAPR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNumberOfMonths, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCapital)
						.addComponent(txtCapital, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfMonths)
						.addComponent(txtNumberOfMonths, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAPR)
						.addComponent(txtAPR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMonthlyPayment)
						.addComponent(txtMonthlyPayment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddToGraph)
						.addComponent(btnCalculate))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	/**********************************************************
	 * This method contains all of the code for creating events
	 *********************************************************/
	private void createEvents() 
	{
		btnCalculate.addActionListener(new ActionListener() 
		{
			public double CalculateMonthlyPayments(double capital, int months, double apr)
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
			
			public double CalculateAPR(double capital, int months, double monthly_payment)
			{
				double previous_value = 0;
				double inter_value = 0;
				double r_prime = 5.0;
				double R_result = CalculateMonthlyPayments(capital, months, r_prime);
				double delta = R_result - monthly_payment;
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
				
				if(r_prime < .001 && r_prime > 0)
				{
					r_prime = 0;
				}
				Last_Payment = monthly_payment;
				return r_prime;
			}
			
			public double CalculateCapital(double apr, int months, double monthly_payment)
			{
				double result = 0;
				if(apr == 0.0)
				{
					result = monthly_payment * months;
				}
				else
				{
					double x = Math.pow(((apr/1200.0)+1), ((double)months));
					result = (monthly_payment * (x-1))/((apr/1200)*x);
				}
				Last_Payment = monthly_payment;
				return result;
			}
			
			public int CalculateNumberOfMonths(double apr, double capital, double monthly_payment)
			{
				int result = 0;
				if(apr == 0.0)
				{
					result = (int)(capital/monthly_payment);
				}
				else if (monthly_payment == 0.0)
				{
					//throws divide by 0 exception
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
			
			public void actionPerformed(ActionEvent e) 
			{
				//Parse variables into number respective format
				double capital = 0;
				int months = 0;
				double apr = 0;
				double monthly_payment = 0;
				
				
				if (txtCapital.getText().isEmpty() == false)
				{
					try
					{
						capital = Double.parseDouble(txtCapital.getText());
					}
					
					catch (Exception e1)
					{
						popup("Capital Input Invalid", "INPUT ERROR");
					}
				}
				if (txtNumberOfMonths.getText().isEmpty() == false)
				{
					try
					{
						months = Integer.parseInt(txtNumberOfMonths.getText());
					}
					
					catch (Exception e2)
					{
						popup("Number Of Months Input Invalid", "INPUT ERROR");
					}
				}
				if (txtAPR.getText().isEmpty() == false)
				{
					try
					{
						apr = Double.parseDouble(txtAPR.getText());
					}
					
					catch (Exception e3)
					{
						popup("APR Input Invalid", "INPUT ERROR");
					}
				}
				if(txtMonthlyPayment.getText().isEmpty() == false)
				{
					try
					{
						monthly_payment = Double.parseDouble(txtMonthlyPayment.getText());
					}
					
					catch (Exception e4)
					{
						popup("Monthly Payment Input Invalid", "INPUT ERROR");
					}
				}
				
				
				//Calculate missing variable

				//Calculate Monthly Payment
				if (isCapitalEntered() && isNumOfMonthEntered() && isAPREntered())
				{
					try
					{
						double calc_payment = CalculateMonthlyPayments(capital, months, apr);
						calc_payment = Math.round(calc_payment*100.0)/100.0;
						txtMonthlyPayment.setText(Double.toString(calc_payment));
					}
					
					catch (Exception e1)
					{
						popup("Inputs For Monthly Payment Calculation Is Not Valid.", "INPUT ERROR");
					}
				}
				//Calculate APR
				else if (isCapitalEntered() && isNumOfMonthEntered() && isMonthlyPaymentEntered())
				{
					try
					{
						double calc_apr = CalculateAPR(capital, months, monthly_payment);
						calc_apr = Math.round(calc_apr*100.0)/100.0;
						txtAPR.setText(Double.toString(calc_apr));
					}
					
					catch(Exception e2)
					{
						popup("Inputs For APR Calculation Is Not Valid.", "INPUT ERROR");
					}
				}

				//Calculate Capital
				else if (isAPREntered() && isNumOfMonthEntered() && isMonthlyPaymentEntered())
				{
					try
					{
						double calc_capital = CalculateCapital(apr, months, monthly_payment);
						calc_capital = Math.round(calc_capital*100.0)/100.0;
						txtCapital.setText(Double.toString(calc_capital));
					}
					
					catch(Exception e3)
					{
						popup("Inputs For Capital Calculation Is Not Valid.", "INPUT ERROR");
					}
				}
				//Calculate Number of Months
				else if (isAPREntered() && isCapitalEntered() && isMonthlyPaymentEntered())
				{
					try
					{
						int calc_months = CalculateNumberOfMonths(apr, capital, monthly_payment);
						txtNumberOfMonths.setText(Integer.toString(calc_months));
					}
					
					catch(Exception e4)
					{
						popup("Inputs For Number Of Months Calculation Is Not Valid.", "INPUT ERROR");
					}
				}

				
				//else invalid combination
				else
				{
					popup("Need More Inputs", "INPUT ERROR");
				}
				
			}

		});
		Object[] row = new Object[5];
		btnAddToGraph.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				row[0] = txtCapital.getText();
				row[1] = txtNumberOfMonths.getText();
				row[2] = txtAPR.getText();
				row[3] = txtMonthlyPayment.getText();
				row[4] = Double.toString(Last_Payment);
				
				model.addRow(row);
			}
		});
		btnPrint.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				MessageFormat header = new MessageFormat("Loan Analysis");
				MessageFormat footer = new MessageFormat("Page{0,number,integer}");
				try
				{
					table.print(JTable.PrintMode.NORMAL, header, footer);
				}
				catch(java.awt.print.PrinterException e)
				{
					System.err.format("Cannot Print %s%n", e.getMessage());
				}
			}
		});
		txtCapital.addKeyListener(new KeyAdapter() 
		{
			public void keyReleased(KeyEvent arg0) 
			{
				if(txtCapital.getText().length() > 0)
				{
					hasCapital = true;
				}
				else
				{
					hasCapital= false;
				}
				if(isThreeInputsEntered())
				{
					btnCalculate.setEnabled(true);
				}
				else
				{
					btnCalculate.setEnabled(false);
				}
			}
		});
		txtNumberOfMonths.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent arg0)
			{
				if(txtNumberOfMonths.getText().length() > 0)
				{
					hasNumOfMonths = true;
				}
				else
				{
					hasNumOfMonths = false;
				}
				if(isThreeInputsEntered())
				{
					btnCalculate.setEnabled(true);
				}
				else
				{
					btnCalculate.setEnabled(false);
				}
			}
		});
		txtAPR.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				if(txtAPR.getText().length() > 0)
				{
					hasAPR = true;
				}
				else
				{
					hasAPR = false;
				}
				if(isThreeInputsEntered())
				{
					btnCalculate.setEnabled(true);
				}
				else
				{
					btnCalculate.setEnabled(false);
				}
			}
		});
		txtMonthlyPayment.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				if(txtMonthlyPayment.getText().length() > 0)
				{
					hasMonthlyPayment = true;
				}
				else
				{
					hasMonthlyPayment = false;
				}
				if(isThreeInputsEntered())
				{
					btnCalculate.setEnabled(true);
				}
				else
				{
					btnCalculate.setEnabled(false);
				}
				
			}
		});
	}
}
