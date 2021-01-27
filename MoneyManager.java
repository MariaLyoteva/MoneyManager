package money;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MoneyManager {
	static String selectedCurrency = "$";
	public static void main(String[] args) {

		JFrame frame = new JFrame("Money Manager");
		frame.setSize(50000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// main containers and Layouts
		JPanel mainPanel = new JPanel();
		frame.add(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		JPanel leftPanel = new JPanel();
		mainPanel.add(leftPanel);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		JPanel rightPanel = new JPanel();
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		// left side
		JLabel moneyManagerLabel = new JLabel("Money Report");
		leftPanel.add(moneyManagerLabel);

		JTextArea reportArea = new JTextArea();
		reportArea.setBounds(100, 20, 165, 25);
		
		reportArea.setEditable(false);
		reportArea.setText("Here are the reports from previous days");
		leftPanel.add(reportArea);
		File myObj = new File("filename.txt");
		String text = "";
		try {
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				text += myReader.nextLine() + "\n";
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			try {
				myObj.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		reportArea.setText(text);
		


		// right side
		SimpleDateFormat ft = new SimpleDateFormat("dd. M yyyy");
		JLabel currentDateLabel = new JLabel(ft.format(new Date()));
		rightPanel.add(currentDateLabel);
		
		JLabel incomeLabel = new JLabel("Daily Income:");
		 incomeLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		 incomeLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		rightPanel.add(incomeLabel);
		//JTextField incomeTxt = new JTextField();
		//rightPanel.add(incomeTxt);
		
		JTextField incomeTxt = new JFormattedTextField();
		incomeTxt.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        Runnable format = new Runnable() {
		            @Override
		            public void run() {
		                String text = incomeTxt.getText();
		                if(!text.matches("\\d*(\\.\\d{0,2})?")){
		                	incomeTxt.setText(text.substring(0,text.length()-1));
		                }
		            }
		        };
		        SwingUtilities.invokeLater(format);
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {

		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {

		    }
		});
		rightPanel.add(incomeTxt);
		
		JLabel expensesLabel = new JLabel("Daily Expenses:");
		expensesLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		expensesLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		rightPanel.add( expensesLabel);
		//JTextField expensesTxt = new JTextField();
		JTextField expensesTxt = new JFormattedTextField();
		expensesTxt.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        Runnable format = new Runnable() {
		            @Override
		            public void run() {
		                String text = expensesTxt.getText();
		                if(!text.matches("\\d*(\\.\\d{0,2})?")){
		                	expensesTxt.setText(text.substring(0,text.length()-1));
		                }
		            }
		        };
		        SwingUtilities.invokeLater(format);
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {

		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {

		    }
		});
		
		
		rightPanel.add(expensesTxt);
		
		JLabel totalLabel = new JLabel("Total Income:");
		totalLabel.setFont(new Font("Serif", Font.BOLD, 14));
		totalLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		rightPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		rightPanel.add(totalLabel);
		JTextField totalDayTxt = new JTextField();
		rightPanel.add(totalDayTxt);

		
		  
		String[] Currencies = {
		         "$",
		         "Лв.",
		         "Еu"
		};
		
	
		
		JComboBox currList = new JComboBox(Currencies);
		currList.setEditable(true);
		currList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 ItemSelectable is = (ItemSelectable)arg0.getSource();
				 selectedCurrency = selectedString(is);

			}
		});
		rightPanel.add(currList);
		
	    
	
		
		rightPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		JButton subButton = new JButton("Submit");
		subButton.setBackground(new Color(204,229,255));
		subButton.setFont(new Font("Serif", Font.BOLD, 20));
		subButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	//	subButton.setMargin(new Insets(20, 40, 20, 40));
		subButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String income =incomeTxt.getText();
				double dIncome = Double.parseDouble(income);
				String expenses = expensesTxt.getText();
				double dExp = Double.parseDouble(expenses);
				DecimalFormat df = new DecimalFormat("#.##");
				df.setRoundingMode(RoundingMode.CEILING);
				double tot = dIncome - dExp;
				Double totF = Double.parseDouble(df.format(tot));
				String total = String.valueOf(totF);
				totalDayTxt.setText(total);
				if (income.isEmpty() && expenses.isEmpty()) {
					return;
				}
				String text = 
						currentDateLabel.getText() + "\n"
				+ "Income: "+ income + selectedCurrency +  "\n"
				+ "Expenses: "+expenses + "\n" 
				+ "Total Income: " + total+ "\n";
				try {
					FileWriter myWriter = new FileWriter("filename.txt",true);
					myWriter.write(text);
					myWriter.close();

				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}

				File myObj = new File("filename.txt");
				text = "";
				try {
					Scanner myReader = new Scanner(myObj);
					while (myReader.hasNextLine()) {
						text += myReader.nextLine() + "\n";
					}
					myReader.close();
				} catch (FileNotFoundException e) {
					try {
						myObj.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				reportArea.setText(text);

			}
		});
		rightPanel.add(subButton);
		
		rightPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		
		JButton clearHistory = new JButton("Clear history");
		clearHistory.setAlignmentX(JButton.CENTER_ALIGNMENT);
		clearHistory.setBackground(new Color(238,94,94));
		clearHistory.setForeground(Color.white);
		clearHistory.setFont(new Font("Cambria", Font.ITALIC, 20));
		clearHistory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = new File("filename.txt");
				if(file.exists()){
					
				    file.delete();
				}
				reportArea.setText("");
				
			}
			
		});
		rightPanel.add(clearHistory);

		rightPanel.add(Box.createRigidArea(new Dimension(10, 20)));
		JLabel inspirationLabel = new JLabel("Money, like emotions, is something you must control"
			+ " to keep your life on the right track");
		inspirationLabel.setForeground(new Color(233,177,80));
	    rightPanel.add(inspirationLabel);
	    rightPanel.add(Box.createRigidArea(new Dimension(10, 20)));
		frame.pack();
		// Setting the frame visibility to true
		frame.setVisible(true);

	}
	
	static String selectedString(ItemSelectable is) {
		    Object selected[] = is.getSelectedObjects();
		    return ((selected.length == 0) ? "null" : (String)selected[0]);
	 } 
	
}
