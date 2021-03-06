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
	private boolean hasCapital = false;
	private boolean hasAPR = false;
	private boolean hasNumOfMonths = false;
	private boolean hasMonthlyPayment = false;
	
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
		Object[] columns = {"Loan Value", "# Of Months", "APR %", "Monthly Payment", "Final Payment"};
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		JLabel lblCapital = new JLabel("Capital (USD)");
		lblCapital.setBackground(SystemColor.info);
		lblCapital.setForeground(Color.BLACK);
		
		txtCapital = new JTextField();
		txtCapital.setColumns(10);
		
		JLabel lblNumberOfMonths = new JLabel("Number of Months");
		txtNumberOfMonths = new JTextField();
		txtNumberOfMonths.setColumns(10);
		
		JLabel lblAPR = new JLabel("APR (%)");
		txtAPR = new JTextField();
		txtAPR.setColumns(10);
		
		JLabel lblMonthlyPayment = new JLabel("Monthly Payments (USD)");
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
			public void actionPerformed(ActionEvent e) 
			{
				//Parse variables into number respective format
				double capital = 0;
				int months = 0;
				double apr = 0;
				double monthly_payment = 0;
				boolean isPassing = true;
				
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
						if(((months % 12) != 0) || (months < 12) || (months > 72))
						{
							isPassing = false;
							popup("Number of Months Is Out Of Range.", "INPUT ERROR");
						}
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
						if((apr < -0.99) || (apr > 75.0))
						{
							isPassing = false;
							popup("APR Is Out Of Range", "INPUT ERROR");	
						}
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
						if(isPassing)
						{
							double calc_payment = Formulas.CalculateMonthlyPayments(capital, months, apr);
							calc_payment = Math.round(calc_payment*100.0)/100.0;
							txtMonthlyPayment.setText(Double.toString(calc_payment));
						}
						else
						{
							popup("APR Range: 0-75\n Number Of Months Range: 12-72", "ERROR");
						}
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
						
						if(isPassing)
						{
							double calc_apr = Formulas.CalculateAPR(capital, months, monthly_payment);
							calc_apr = Math.round(calc_apr*100.0)/100.0;
							if((calc_apr <= 75) && (calc_apr >= 0))
							{
								txtAPR.setText(Double.toString(calc_apr));
							}
							else
							{
								popup("Calculated APR Is Out Of Range", "CALCULATION ERROR");
							}
						}
						else
						{
							popup("APR Range: 0-75\n Number Of Months Range: 12-72", "ERROR");
						}
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
						if(isPassing)
						{
							double calc_capital = Formulas.CalculateCapital(apr, months, monthly_payment);
							calc_capital = Math.round(calc_capital*100.0)/100.0;
							txtCapital.setText(Double.toString(calc_capital));
						}
						else
						{
							popup("APR Range: 0-75\n Number Of Months Range: 12-72", "ERROR");
						}
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
						if(isPassing)
						{
							int calc_months = Formulas.CalculateNumberOfMonths(apr, capital, monthly_payment);
							txtNumberOfMonths.setText(Integer.toString(calc_months));
						}
						else
						{
							popup("APR Range: 0-75\n Number Of Months Range: 12-72", "ERROR");
						}
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
				row[4] = Double.toString(Formulas.getLastPayment());
				
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
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
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
