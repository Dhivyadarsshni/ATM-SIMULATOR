package com.ATMSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fastcash extends JFrame implements ActionListener {

    JButton amt_hundred, amt_fivehundred, amt_thousand , amt_twothousand, amt_fivethousand, amt_tenthousand, back;
    String pinnumber;

    Fastcash(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("PLEASE SELECT WITHDRAWAL AMOUNT");
        text.setBounds(185,300,700,35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD,16));
        image.add(text);

        amt_hundred = new JButton("RS 100");
        amt_hundred.setBounds(170,415,150,30);
        amt_hundred.addActionListener(this);
        image.add(amt_hundred);

        amt_fivehundred = new JButton("RS 500");
        amt_fivehundred.setBounds(355,415,150,30);
        amt_fivehundred.addActionListener(this);
        image.add(amt_fivehundred);

        amt_thousand = new JButton("RS 1000");
        amt_thousand.setBounds(170,450,150,30);
        amt_thousand.addActionListener(this);
        image.add(amt_thousand);

        amt_twothousand = new JButton("RS 2000");
        amt_twothousand.setBounds(355,450,150,30);
        amt_twothousand.addActionListener(this);
        image.add(amt_twothousand);

        amt_fivethousand = new JButton("RS 5000");
        amt_fivethousand.setBounds(170,485,150,30);
        amt_fivethousand.addActionListener(this);
        image.add(amt_fivethousand);

        amt_tenthousand = new JButton("RS 10000");
        amt_tenthousand.setBounds(355,485,150,30);
        amt_tenthousand.addActionListener(this);
        image.add(amt_tenthousand);

        back = new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else {
            String amount = ((JButton)ae.getSource()).getText().split(" ")[1];

            Conn c = new Conn();
            try {
                ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE Pin_Number = '"+pinnumber+"' ");
                int balance = 0;
                while (rs.next()) {
                    if(rs.getString("Type").equals("Deposit")){
                        balance += Integer.parseInt(rs.getString("Amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("Amount"));
                    }
                }
                if (balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                Date date = new Date();

                String query = "INSERT INTO bank (Pin_Number, Date, Type, Amount,category) VALUES ('"+pinnumber+"', '"+date+"', 'Withdraw', '"+amount+"','Other')";

                try {
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Fastcash("");
    }
}
