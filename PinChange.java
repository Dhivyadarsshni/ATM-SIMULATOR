package com.ATMSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin,repin;

    JButton change,back;

    String pinnumber;
    PinChange(String pinnumber){

        this.pinnumber = pinnumber;

        setLayout(null);

        //ATM Machine image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        //change your pin - Amount Text
        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setBounds(250,280,500,35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD,16));
        image.add(text);

        //NEW PIN - Amount Text
        JLabel pintext = new JLabel("NEW PIN");
        pintext.setBounds(165,320,180,25);
        pintext.setForeground(Color.white);
        pintext.setFont(new Font("System", Font.BOLD,16));
        image.add(pintext);

        //NEW PIN - TEXTFIELD
        pin = new JPasswordField();
        pin.setFont(new Font("Raleway",Font.BOLD,25));
        pin.setBounds(330,320,180,25);
        image.add(pin);

        //RE ENTER YOUR PIN- Amount Text
        JLabel repintext = new JLabel("RE-ENTER YOUR PIN");
        repintext.setBounds(165,360,180,25);
        repintext.setForeground(Color.white);
        repintext.setFont(new Font("System", Font.BOLD,16));
        image.add(repintext);

        //RE ENTER YOUR PIN - TEXTFIELD
        repin = new JPasswordField();
        repin.setFont(new Font("Raleway",Font.BOLD,25));
        repin.setBounds(330,360,180,25);
        image.add(repin);

        //Buttons
        //change
        change = new JButton("CHANGE");
        change.setBounds(355,485,150,30);
        change.addActionListener(this);
        image.add(change);

        //back
        back = new JButton("BACK");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);



        //layouts
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);

    }
    public  void  actionPerformed(ActionEvent ae){
        if (ae.getSource()==change) {
            try {
                String npin = pin.getText();
                String rpin = repin.getText();
                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }
                if(npin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter PIN");
                    return;
                }
                if(rpin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please re-enter PIN");
                    return;
                }

                Conn conn = new Conn();
                String query1 = "update bank set Pin_Number = '"+rpin+"' where Pin_Number ='"+pinnumber+"' ";
                String query2 = "update login set Pin_Number = '"+rpin+"' where Pin_Number ='"+pinnumber+"' ";
                String query3 = "update signupThree set Pin_Number = '"+rpin+"' where Pin_Number ='"+pinnumber+"' ";

                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null,"PIN changed successfully");

                setVisible(false);
                new Transactions(rpin).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }

        }else {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }

    }
    public static void main(String[] args) {
        new PinChange("");
    }
}
