package com.ATMSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    JButton deposit,back;

    JTextField amount;

    String pinnumber;
    Deposit(String pinnumber){
        this.pinnumber= pinnumber;
        setLayout(null);


        //ATM Machine Picture
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        //Deposit Amount Text
        JLabel text = new JLabel("ENTER THE AMOUNT YOU WANT TO DEPOSIT");
        text.setBounds(155,300,700,35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD,16));
        image.add(text);

        //Amount TextField

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD,22));
        amount.setBounds(170,350,320,25);
        image.add(amount);

        //BUTTONS

        //deposit
        //Balance Enquiry
        deposit = new JButton("Deposit");
        deposit.setBounds(355,485,150,30);
        deposit.addActionListener(this);
        image.add(deposit);

        //back
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

    public void actionPerformed(ActionEvent ae)
    {
    if (ae.getSource()==deposit){
        String number = amount.getText();
        Date date = new Date();
        if(number.equals("")){
            JOptionPane.showMessageDialog(null,"Please enter the amount you want to deposit");
        }else{
            try{
            Conn conn = new Conn();
            String query = "insert into bank values ('"+pinnumber+"','"+date+"','Deposit','"+number+"')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Rs "+number+ "Deposited Successfully");
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }

    } else if (ae.getSource()==back) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }



    }


    public static void main(String[] args) {
        new Deposit("");
    }



}
