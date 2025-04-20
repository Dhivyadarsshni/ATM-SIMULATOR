package com.ATMSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SignUpThree extends JFrame implements ActionListener {

    JRadioButton saving_acc, fd_acc,current_acc,rd_acc;

    JCheckBox atm_card, internet_banking, mobile_banking, notifications,chequebook, statements,declaration;

    JButton submit , cancel;

    String formno;

    SignUpThree(String formno){

        this.formno= formno;

        setTitle("APPLICATION PAGE:03" );

        //Frame's default Measurements
        setLayout(null);

        //Sub - Heading : Account details - page 3
        JLabel accDetails = new JLabel("Page 3 : Account Details ");
        accDetails.setBounds(280,40,400,30);
        accDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        add(accDetails);

        //Account type
        JLabel acc_type = new JLabel("Account Type:");
        acc_type.setBounds(100,140,200,30);
        acc_type.setFont(new Font("Raleway", Font.BOLD,22));
        add(acc_type);

        //Radio Button - Saving account
        saving_acc = new JRadioButton("Saving Account");
        saving_acc.setFont(new Font("Raleway", Font.BOLD,18));
        saving_acc.setBackground(Color.WHITE);
        saving_acc.setBounds(100,180,220,35);
        add(saving_acc);

        //Radio Button - FD account
        fd_acc = new JRadioButton("Fixed Deposit");
        fd_acc.setFont(new Font("Raleway", Font.BOLD,18));
        fd_acc.setBackground(Color.WHITE);
        fd_acc.setBounds(350,180,220,35);
        add(fd_acc);

        //Radio Button - Current account
        current_acc = new JRadioButton("Current Account");
        current_acc.setFont(new Font("Raleway", Font.BOLD,18));
        current_acc.setBackground(Color.WHITE);
        current_acc.setBounds(100,220,220,35);
        add(current_acc);

        //Radio Button - RD account
        rd_acc = new JRadioButton("Recurring Deposit");
        rd_acc.setFont(new Font("Raleway", Font.BOLD,18));
        rd_acc.setBackground(Color.WHITE);
        rd_acc.setBounds(350,220,220,35);
        add(rd_acc);

        //ButtonGroup - to add all buttons
        ButtonGroup groupaccount =new ButtonGroup();
        groupaccount.add(saving_acc);
        groupaccount.add(rd_acc);
        groupaccount.add(fd_acc);
        groupaccount.add(current_acc);

        //Card Number
        JLabel card = new JLabel("Card Number:");
        card.setBounds(100,300,200,30);
        card.setFont(new Font("Raleway", Font.BOLD,22));
        add(card);

        //Card Number - XXXX XXXX XXXX ____
        JLabel cardnumber = new JLabel("XXXX XXXX XXXX 7112");
        cardnumber.setBounds(330,300,300,30);
        cardnumber.setFont(new Font("Raleway", Font.BOLD,22));
        add(cardnumber);

        //16 Digit Number
        JLabel card_details = new JLabel("Your 16 Digit Number ");
        card_details.setBounds(100,330,300,30);
        card_details.setFont(new Font("Raleway", Font.BOLD,12));
        add(card_details);

        //Pin :
        JLabel pin = new JLabel("PIN: ");
        pin.setBounds(100,370,200,30);
        pin.setFont(new Font("Raleway", Font.BOLD,22));
        add(pin);

        //Pin Number :
        JLabel pin_number = new JLabel("XXXX");
        pin_number.setBounds(330,370,200,30);
        pin_number.setFont(new Font("Raleway", Font.BOLD,22));
        add(pin_number);

        //4 Digit Number
        JLabel pin_details = new JLabel("Your 4 Digit Password ");
        pin_details.setBounds(100,400,300,30);
        pin_details.setFont(new Font("Raleway", Font.BOLD,12));
        add(pin_details);

        //Services Required
        JLabel services_req = new JLabel("Services Required: ");
        services_req.setBounds(100,450,300,30);
        services_req.setFont(new Font("Raleway", Font.BOLD,22));
        add(services_req);

        //Check Box - ATM Card
        atm_card = new JCheckBox("ATM Card");
        atm_card.setBounds(100,500,300,30);
        atm_card.setFont(new Font("Raleway", Font.BOLD,18));
        atm_card.setBackground(Color.WHITE);
        add(atm_card);

        //Check Box - Internet Banking
        internet_banking = new JCheckBox("Internet Banking");
        internet_banking.setBounds(400,500,300,30);
        internet_banking.setFont(new Font("Raleway", Font.BOLD,18));
        internet_banking.setBackground(Color.WHITE);
        add(internet_banking);

        //Check Box - Mobile Banking
        mobile_banking = new JCheckBox("Mobile Banking");
        mobile_banking.setBounds(100,550,300,30);
        mobile_banking.setFont(new Font("Raleway", Font.BOLD,18));
        mobile_banking.setBackground(Color.WHITE);
        add(mobile_banking);

        //Check Box - Alerts and Notifications
        notifications = new JCheckBox("Email & SMS Alerts");
        notifications.setBounds(400,550,300,30);
        notifications.setFont(new Font("Raleway", Font.BOLD,18));
        notifications.setBackground(Color.WHITE);
        add(notifications);

        //Check Box - Cheque Book
        chequebook = new JCheckBox("Cheque Book");
        chequebook.setBounds(100,600,300,30);
        chequebook.setFont(new Font("Raleway", Font.BOLD,18));
        chequebook.setBackground(Color.WHITE);
        add(chequebook);

        //Check Box - E Statement
        statements = new JCheckBox("E Statement");
        statements.setBounds(400,600,300,30);
        statements.setFont(new Font("Raleway", Font.BOLD,18));
        statements.setBackground(Color.WHITE);
        add(statements);

        //CheckBox - Declaration
        declaration = new JCheckBox("I hereby declare that the above details are correct to the best of my knowledge");
        declaration.setBounds(100,665,700,30);
        declaration.setFont(new Font("Raleway", Font.BOLD,14));
        declaration.setBackground(Color.WHITE);
        add(declaration);

        //Submit Button
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.white);
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBounds(250,700,120,35);
        submit.addActionListener(this);
        add(submit);

        //Cancel Button
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBounds(420,700,120,35);
        cancel.addActionListener(this);
        add(cancel);


        getContentPane().setBackground(Color.WHITE);
        setSize(850,820);
        setLocation(350,0);
        setVisible(true);
        setBackground(Color.WHITE);

    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==submit){

        String accountType = null;
        if(saving_acc.isSelected()){
            accountType= "Saving Account";
        } else if (current_acc.isSelected()) {
            accountType = "Current Account";
        } else if (rd_acc.isSelected()) {
            accountType = "Recurring Deposit Account";
        } else if (fd_acc.isSelected()) {
            accountType = "Forward Deposit Account";
        }

        Random random = new Random();
        String cardNumber = ""+ Math.abs((random.nextLong() % 90000000L ) + 5040936000000000L);

        String pinNumber = ""+ Math.abs((random.nextLong() % 9000L)+1000L);

        String facility = null;
        if(atm_card.isSelected()){
            facility = facility + "ATM Card";
        } else if (internet_banking.isSelected()) {
            facility = facility + "Internet Banking";
        } else if (mobile_banking.isSelected()) {
            facility =facility + "Mobile Banking";
        } else if (notifications.isSelected()) {
            facility = facility +"Email & SMS Alerts";
        } else if (chequebook.isSelected()) {
            facility =facility + "ChequeBook";
        } else if (statements.isSelected()) {
            facility = facility + "E - Statements";
        }

        try{
            if(accountType.equals("")){
                JOptionPane.showMessageDialog(null,"Account Type is Required");
            }
            else{
                Conn conn = new Conn();
                String query1 = "insert into signupThree values('"+formno+"','"+accountType+"','"+cardNumber+"','"+pinNumber+"','"+facility+"')";
                String query2 = "insert into login values('"+formno+"','"+cardNumber+"','"+pinNumber+"')";
                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null,"Card Number "+cardNumber+"\n Pin: "+pinNumber);
            }
            setVisible(false);
            new Deposit(pinNumber).setVisible(false);

        }
        catch (Exception e){
            System.out.println(e);
        }

        }
        else if (ae.getSource()== cancel){
            setVisible(false);
            new Login().setVisible(true);
        }
    }


    public static void main(String[] args) {
        new SignUpThree("");
    }
}
