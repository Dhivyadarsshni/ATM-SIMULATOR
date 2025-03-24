package com.ATMSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

    JButton withdrawl, back;
    JButton food, bills, shopping, travel, other;
    JTextField amount;
    String pinnumber, category = "Other";

    Withdrawl(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        // ATM Machine Picture
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Withdraw Amount Text
        JLabel text = new JLabel("ENTER THE AMOUNT YOU WANT TO WITHDRAW");
        text.setBounds(155, 290, 700, 35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 15));
        image.add(text);

        // Amount TextField
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 330, 320, 25);
        image.add(amount);

        // Category Buttons
        food = new JButton("Food");
        food.setBounds(170, 370, 100, 30);
        food.addActionListener(this);
        image.add(food);

        bills = new JButton("Bills");
        bills.setBounds(280, 370, 100, 30);
        bills.addActionListener(this);
        image.add(bills);

        shopping = new JButton("Shopping");
        shopping.setBounds(390, 370, 100, 30);
        shopping.addActionListener(this);
        image.add(shopping);

        travel = new JButton("Travel");
        travel.setBounds(170, 410, 100, 30);
        travel.addActionListener(this);
        image.add(travel);

        other = new JButton("Other");
        other.setBounds(280, 410, 100, 30);
        other.addActionListener(this);
        image.add(other);

        // Withdraw and Back Buttons
        withdrawl = new JButton("Withdraw");
        withdrawl.setBounds(355, 485, 150, 30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        // Layout settings
        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdrawl) {
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
            }else{
                try{
                    Conn c = new Conn();
                        ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE Pin_Number = '"+pinnumber+"' ");
                        int balance = 0;
                        while (rs.next()) {
                            if(rs.getString("Type").equals("Deposit")){
                                balance += Integer.parseInt(rs.getString("Amount"));
                            } else {
                                balance -= Integer.parseInt(rs.getString("Amount"));
                            }
                        }
                        if (balance < Integer.parseInt(amount.getText())) {
                            JOptionPane.showMessageDialog(null, "Insufficient Balance");
                            return;
                        }

                        String query = "INSERT INTO bank (Pin_Number, Date, Type, Amount, category) VALUES ('" + pinnumber + "', '" + date + "', 'Withdraw', '" + number + "', '" + category + "')";
                        c.s.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Rs " + number + " withdrawn successfully for " + category);
                        setVisible(false);
                        new Transactions(pinnumber).setVisible(true);
                    } catch (Exception e) {
                        System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else if (ae.getSource() == food) {
            category = "Food";
        } else if (ae.getSource() == bills) {
            category = "Bills";
        } else if (ae.getSource() == shopping) {
            category = "Shopping";
        } else if (ae.getSource() == travel) {
            category = "Travel";
        } else if (ae.getSource() == other) {
            category = "Other";
        }
    }

    public static void main(String[] args) {
        new Withdrawl("");
    }
}

