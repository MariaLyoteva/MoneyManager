package money;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MoneyManager {
	public static void main(String[] args) {

		JFrame frame = new JFrame("Money Manager");
		frame.setSize(1000, 800);
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
		


		// right side
		SimpleDateFormat ft = new SimpleDateFormat("dd. M yyyy");
		JLabel currentDateLabel = new JLabel(ft.format(new Date()));
		rightPanel.add(currentDateLabel);
		
		JLabel incomeLabel = new JLabel("Daily Income:");
		rightPanel.add(incomeLabel);
		JTextField incomeTxt = new JTextField();
		rightPanel.add(incomeTxt);
		
		JLabel expensesLabel = new JLabel("Daily Expenses:");
		rightPanel.add( expensesLabel);
		JTextField expensesTxt = new JTextField();
		rightPanel.add(expensesTxt);
		JLabel totalLabel = new JLabel("Total Income:");
		rightPanel.add(totalLabel);
		JTextField totalDayTxt = new JTextField();
		rightPanel.add(totalDayTxt);

		JButton thankButton = new JButton("Submit");
		rightPanel.add(thankButton);

		JLabel inspirationLabel = new JLabel("Money, like emotions, is something you must control"
				+ " to keep your life on the right track");
		rightPanel.add(inspirationLabel);

		frame.pack();
		// Setting the frame visibility to true
		frame.setVisible(true);

	}
}
