package com.ATMSimulator;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class MiniStatement extends JFrame {

    String pinnumber;

    MiniStatement(String pinnumber) {

        this.pinnumber=pinnumber;
        setTitle("Mini Statement");

        setLayout(null);

        //text
        JLabel mini = new JLabel();
        add(mini);

        //bank name
        JLabel bank = new JLabel("India's Bank");
        bank.setBounds(150,20,100,20);
        add(bank);

        //card number - title
        JLabel card = new JLabel();
        card.setBounds(20,80,300,20);
        add(card);

        //balance
        JLabel balance = new JLabel();
        balance.setBounds(20,80,1000,20);
        add(balance);



        //connecting to sql to fetch card's pin
        try{
            Conn conn = new Conn();

            ResultSet rs = conn.s.executeQuery("select * from login where Pin_Number ='"+pinnumber+"' ");

            while (rs.next()){
                card.setText("Card Number: "+rs.getString("Card_Number").substring(0,4)+"XXXX XXXX"+rs.getString("Card_Number").substring(12));
            }

        }catch (Exception e){
            System.out.println(e);
        }

        //
        try {
            Conn conn = new Conn();
            int bal = 0;
            ResultSet rs = conn.s.executeQuery("select * from bank where Pin_Number ='"+pinnumber+"' ");

            while (rs.next()){
                mini.setText(mini.getText() + "<html>"+ rs.getString("Date") +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ rs.getString("Type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ rs.getString("Amount") +"<br><br></html>");
                if(rs.getString("Type").equals("Deposit")){
                    bal += Integer.parseInt(rs.getString("amount"));
                }else{
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your Current account balance is Rs "+bal);
        }catch (Exception e){
            System.out.println(e);
        }

        mini.setBounds(20,140,400,200);

        //mini statement - title
        /*JLabel mini = new JLabel();
        mini.setBounds(20,80,300,20);
        add(mini);*/


        //layouts
        setSize(400,600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);


    }

    public static void main(String[] args) {
        new MiniStatement("");
    }
}
