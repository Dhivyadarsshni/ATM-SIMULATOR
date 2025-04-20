package com.ATMSimulator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MiniStatement extends JFrame {
    private String pinnumber;
    private DefaultCategoryDataset dataset;
    private JComboBox<String> filterDropdown;
    private ChartPanel chartPanel;

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

        // Filter dropdown
        filterDropdown = new JComboBox<>(new String[]{"Daily", "Weekly", "Monthly"});
        filterDropdown.setBounds(350, 60, 120, 25);
        add(filterDropdown);

        filterDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateChart();
            }
        });

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
        dataset = new DefaultCategoryDataset();
        updateChart();

        JFreeChart barChart = ChartFactory.createBarChart(
                "Spending Overview", "Category", "Amount (Rs)", dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL, true, true, false);

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator());
        renderer.setSeriesItemLabelsVisible(0, true);

        chartPanel = new ChartPanel(barChart);
        chartPanel.setBounds(20, 300, 450, 350);
        add(chartPanel);

        // Frame settings
        setSize(500, 700);
        setLocation(100, 50);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    private void updateChart() {
        dataset.clear();
        String filter = (String) filterDropdown.getSelectedItem();
        try {
            Conn conn = new Conn();
            String[] categories = {"Food", "Bills", "Shopping", "Travel", "Other"};
            String dateCondition = getDateCondition(filter);

            for (String category : categories) {
                ResultSet rs = conn.s.executeQuery(
                        "SELECT SUM(Amount) FROM bank WHERE Pin_Number='" + pinnumber + "' AND Category='" + category + "' " + dateCondition);
                if (rs.next()) {
                    dataset.addValue(rs.getInt(1), "Spending", category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDateCondition(String filter) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String condition = "";

        if ("Daily".equals(filter)) {
            condition = "AND Date = '" + sdf.format(cal.getTime()) + "'";
        } else if ("Weekly".equals(filter)) {
            cal.add(Calendar.DAY_OF_YEAR, -7);
            condition = "AND Date >= '" + sdf.format(cal.getTime()) + "'";
        } else if ("Monthly".equals(filter)) {
            cal.add(Calendar.MONTH, -1);
            condition = "AND Date >= '" + sdf.format(cal.getTime()) + "'";
        }
        return condition;
    }

    public static void main(String[] args) {
        new MiniStatement("1234"); // Pass test PIN for checking UI
    }
}
