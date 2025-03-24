/*package com.ATMSimulator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MiniStatement extends JFrame {
    String pinnumber;

    public MiniStatement(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("Mini Statement - Spending Chart");
        setLayout(null);

        // Bank name
        JLabel bank = new JLabel("SRM Bank");
        bank.setBounds(200, 20, 200, 25);
        add(bank);

        // Card number
        JLabel card = new JLabel();
        card.setBounds(20, 70, 300, 20);
        add(card);

        // Transaction details
        JLabel mini = new JLabel();
        mini.setVerticalAlignment(JLabel.TOP); // Align text to the top

        // Wrap in JScrollPane
        JScrollPane scrollPane = new JScrollPane(mini);
        scrollPane.setBounds(20, 100, 450, 150); // Fixed height to show ~5 transactions
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);


        // Balance label
        JLabel balance = new JLabel();
        balance.setBounds(20, 300, 400, 25);
        add(balance);

        // Fetch card number
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM login WHERE Pin_Number ='" + pinnumber + "'");
            while (rs.next()) {
                card.setText("Card Number: " + rs.getString("Card_Number").substring(0, 4) + " XXXX XXXX " + rs.getString("Card_Number").substring(12));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // Fetch transaction history and balance

        try {
            Conn conn = new Conn();
            int bal = 0;
            ResultSet rs = conn.s.executeQuery("select * from bank where Pin_Number ='" + pinnumber + "' ");

            StringBuilder transactionHistory = new StringBuilder("<html>");
            while (rs.next()) {
                transactionHistory.append(rs.getString("Date"))
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(rs.getString("Type"))
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(rs.getString("Amount"))
                        .append("<br><br>");

                if (rs.getString("Type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            transactionHistory.append("</html>");
            mini.setText(transactionHistory.toString());

            balance.setText("Your Current account balance is Rs " + bal);
        } catch (Exception e) {
            System.out.println(e);
        }

        // Create spending bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Spending Overview", "Category", "Amount (Rs)", createDataset(),
                org.jfree.chart.plot.PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);

        chartPanel.setBounds(20, 350, 450, 350);
        add(chartPanel);

        // Frame settings
        setSize(500, 750);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            Conn conn = new Conn();
            String[] categories = {"Food", "Bills", "Shopping", "Travel", "Other"};
            for (String category : categories) {
                String query = "SELECT SUM(Amount) FROM bank WHERE Pin_Number='" + pinnumber + "' AND category='" + category + "'";
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    dataset.addValue(rs.getInt(1), "Spending", category);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return dataset;
    }

    public static void main(String[] args) {
        new MiniStatement("");
    }
}*/
package com.ATMSimulator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MiniStatement extends JFrame {
    private String pinnumber;

    public MiniStatement(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("Mini Statement - Spending Chart");
        setLayout(null);

        // Bank name
        JLabel bank = new JLabel("SRM Bank", SwingConstants.CENTER);
        bank.setFont(new Font("Arial", Font.BOLD, 18));
        bank.setBounds(150, 20, 200, 25);
        add(bank);

        // Card number
        JLabel card = new JLabel();
        card.setBounds(20, 60, 300, 20);
        add(card);

        // Table with headers for transactions
        String[] columns = {"Date", "Type", "Amount"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setEnabled(false); // Non-editable
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 90, 450, 150);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        // Balance label
        JLabel balance = new JLabel();
        balance.setBounds(20, 260, 400, 25);
        add(balance);

        // Fetch card number
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM login WHERE Pin_Number ='" + pinnumber + "'");
            if (rs.next()) {
                String cardNum = rs.getString("Card_Number");
                card.setText("Card Number: " + cardNum.substring(0, 4) + " XXXX XXXX " + cardNum.substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Fetch transaction history and balance
        int bal = 0;
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE Pin_Number ='" + pinnumber + "' ORDER BY Date DESC");

            while (rs.next()) {
                String date = rs.getString("Date");
                String type = rs.getString("Type");
                String amount = rs.getString("Amount");

                model.addRow(new String[]{date, type, amount});

                if (type.equalsIgnoreCase("Deposit")) {
                    bal += Integer.parseInt(amount);
                } else {
                    bal -= Integer.parseInt(amount);
                }
            }
            balance.setText("Your Current Account Balance: Rs " + bal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create spending bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Spending Overview", "Category", "Amount (Rs)", createDataset(),
                org.jfree.chart.plot.PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);

        chartPanel.setBounds(20, 300, 450, 350);
        add(chartPanel);

        // Frame settings
        setSize(500, 700);
        setLocation(100, 50);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            Conn conn = new Conn();
            String[] categories = {"Food", "Bills", "Shopping", "Travel", "Other"};

            for (String category : categories) {
                ResultSet rs = conn.s.executeQuery(
                        "SELECT SUM(Amount) FROM bank WHERE Pin_Number='" + pinnumber + "' AND Category='" + category + "'");
                if (rs.next()) {
                    dataset.addValue(rs.getInt(1), "Spending", category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public static void main(String[] args) {
        new MiniStatement("1234"); // Pass test PIN for checking UI
    }
}

