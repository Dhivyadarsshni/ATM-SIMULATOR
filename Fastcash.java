package com.ATMSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Fastcash extends JFrame implements ActionListener {

    JButton amt_hundred, amt_fivehundred, amt_thousand , amt_twothousand, amt_fivethousand, amt_tenthousand, back;

    String pinnumber;
    Fastcash(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);

        //ATM Machine image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        //Transaction Type Text
        JLabel text = new JLabel("PLEASE SELECT WITHDRAWL AMOUNT");
        text.setBounds(185,300,700,35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD,16));
        image.add(text);

        // buttons --
        //RS 100
        amt_hundred = new JButton("RS 100");
        amt_hundred.setBounds(170,415,150,30);
        amt_hundred.addActionListener(this);
        image.add(amt_hundred);

        //RS 500
        amt_fivehundred = new JButton("RS 500");
        amt_fivehundred.setBounds(355,415,150,30);
        amt_fivehundred.addActionListener(this);
        image.add(amt_fivehundred);

        //RS 1000
        amt_thousand = new JButton("RS 1000");
        amt_thousand.setBounds(170,450,150,30);
        amt_thousand.addActionListener(this);
        image.add(amt_thousand);

        //RS 2000
        amt_twothousand = new JButton("RS 2000");
        amt_twothousand.setBounds(355,450,150,30);
        amt_twothousand.addActionListener(this);
        image.add(amt_twothousand);

        //RS 5000
        amt_fivethousand = new JButton("RS 5000");
        amt_fivethousand.setBounds(170,485,150,30);
        amt_fivethousand.addActionListener(this);
        image.add(amt_fivethousand);

        //RS 10000
        amt_tenthousand = new JButton("RS 10000");
        amt_tenthousand.setBounds(355,485,150,30);
        amt_tenthousand.addActionListener(this);
        image.add(amt_tenthousand);

        //exit
        back = new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);

        //layouts
        setSize(900,900);
        setLocation(300,0);

        setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else  {

            String amount = ((JButton)ae.getSource()).getText().substring(3);
            Conn c = new Conn();
            try {
                ResultSet rs = c.s.executeQuery("select * from bank where Pin_Number = '"+pinnumber+"' ");
                int balance = 0;
                while (rs.next()){
                    if(rs.getString("Type").equals("Deposit")){
                        balance += Integer.parseInt(rs.getString("amount"));
                    }else{
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if (ae.getSource()!=back && balance <Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }

                Date date = new Date();
                String query = "insert into bank values ('"+pinnumber+"','"+date+"','Withdrawal','"+amount+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs "+ amount+ "Debited Successfully");

                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }
    public static void main(String[] args) {
        new Fastcash("");
    }
}
