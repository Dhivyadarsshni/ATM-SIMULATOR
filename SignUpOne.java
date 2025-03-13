package com.ATMSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import com.toedter.calendar.JDateChooser;

public class SignUpOne extends JFrame implements ActionListener {

    //global declaration
    long random;
    JTextField nameTextfield, fnameTextfield, emailTextfield, addrTextfield, cityTextfield, stateTextfield, pcodeTextfield;

    JButton next;

    JRadioButton male,female,married,unmarried, other;

    JDateChooser dobChooser;

    SignUpOne(){

        //frame's title
        setTitle("APPLICATION PAGE:01" );

        //Frame's default Measurements
        setLayout(null);

        //creating the form number with
        //Random class
        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L )+1000L);

        //Heading
        JLabel formno = new JLabel("APPLICATION FORM NO. "+ random);
        formno.setBounds(140,20,600,40);
        formno.setFont(new Font("Raleway", Font.BOLD, 38));
        add(formno);

        //Sub - Heading : Personal details - page 1
        JLabel personalDetails = new JLabel("Page 1 : Personal Details ");
        personalDetails.setBounds(290,80,400,30);
        personalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        add(personalDetails);

        //name
        JLabel name = new JLabel("Name: ");
        name.setBounds(100,140,100,30);
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        add(name);

        //name Textfield
        nameTextfield = new JTextField();
        nameTextfield.setFont(new Font("Raleway", Font.BOLD, 14));
        nameTextfield.setBounds(300,140,400,30);
        add(nameTextfield);

        // Father name
        JLabel fname = new JLabel("Father's Name: ");
        fname.setBounds(100,190,200,30);
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        add(fname);

        //Father's name Textfield
        fnameTextfield = new JTextField();
        fnameTextfield.setFont(new Font("Raleway", Font.BOLD, 14));
        fnameTextfield.setBounds(300,190,400,30);
        add(fnameTextfield);

        //Date of Birth
        JLabel dob = new JLabel("Date of Birth: ");
        dob.setBounds(100,240,200,30);
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        add(dob);

        //Date of Birth - calendar
        dobChooser = new JDateChooser();
        dobChooser.setFont(new Font("Raleway", Font.BOLD, 14));
        dobChooser.setBounds(300,240,400,30);
        add(dobChooser);

        //Gender
        JLabel gender = new JLabel("Gender: ");
        gender.setBounds(100,290,200,30);
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        add(gender);

        //Gender - male
        male = new JRadioButton("Male");
        male.setBounds(300,290,60,30);
        male.setBackground(Color.WHITE);
        add(male);

        //Gender - female
        female = new JRadioButton("Female");
        female.setBounds(400,290,70,30);
        female.setBackground(Color.WHITE);
        add(female);

        //Button grouper to select either male or female
        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);

        //Email address
        JLabel email = new JLabel("Email Address: ");
        email.setBounds(100,340,200,30);
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        add(email);

        //Email Textfield
        emailTextfield = new JTextField();
        emailTextfield.setFont(new Font("Raleway", Font.BOLD, 14));
        emailTextfield.setBounds(300,340,400,30);
        add(emailTextfield);

        //Marital Status
        JLabel mstat = new JLabel("Marital Status: ");
        mstat.setBounds(100,390,200,30);
        mstat.setFont(new Font("Raleway", Font.BOLD, 20));
        add(mstat);

        //Marital Status- married
        married = new JRadioButton("Married");
        married.setBounds(300,390,90,30);
        married.setBackground(Color.WHITE);
        add(married);

        //Marital Status- Unmarried
        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(400,390,90,30);
        unmarried.setBackground(Color.WHITE);
        add(unmarried);

        //Marital Status- Other
        other = new JRadioButton("Other");
        other.setBounds(510,390,70,30);
        other.setBackground(Color.WHITE);
        add(other);

        //Button grouper to select either male or female
        ButtonGroup marritalstatusgroup = new ButtonGroup();
        marritalstatusgroup.add(married);
        marritalstatusgroup.add(unmarried);
        marritalstatusgroup.add(other);


        //Address
        JLabel addr = new JLabel("Address: ");
        addr.setBounds(100,440,200,30);
        addr.setFont(new Font("Raleway", Font.BOLD, 20));
        add(addr);

        //Address Textfield
        addrTextfield = new JTextField();
        addrTextfield.setFont(new Font("Raleway", Font.BOLD, 14));
        addrTextfield.setBounds(300,440,400,30);
        add(addrTextfield);

        //City
        JLabel city = new JLabel("City: ");
        city.setBounds(100,490,200,30);
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        add(city);

        //City Textfield
        cityTextfield = new JTextField();
        cityTextfield.setFont(new Font("Raleway", Font.BOLD, 14));
        cityTextfield.setBounds(300,490,400,30);
        add(cityTextfield);

        //Pincode
        JLabel pcode = new JLabel("Pincode: ");
        pcode.setBounds(100,540,200,30);
        pcode.setFont(new Font("Raleway", Font.BOLD, 20));
        add(pcode);

        //Pincode Textfield
        pcodeTextfield = new JTextField();
        pcodeTextfield.setFont(new Font("Raleway", Font.BOLD, 14));
        pcodeTextfield.setBounds(300,540,400,30);
        add(pcodeTextfield);

        //State
        JLabel state = new JLabel("State: ");
        state.setBounds(100,590,200,30);
        state.setFont(new Font("Raleway", Font.BOLD, 20));
        add(state);

        //State Textfield
        stateTextfield = new JTextField();
        stateTextfield.setFont(new Font("Raleway", Font.BOLD, 14));
        stateTextfield.setBounds(300,590,400,30);
        add(stateTextfield);

        //Next Button
        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620,650,80,30);
        next.addActionListener(this);
        add(next);

        //setting frame
        getContentPane().setBackground(Color.WHITE);

        setSize(850,800);
        setLocation(350,10);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){

        String formno ="" + random;
        String name = nameTextfield.getText();
        String fname = fnameTextfield.getText();
        String dob = ((JTextField) dobChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if(male.isSelected()){
            gender= "Male";
        }
        else if(female.isSelected()){
            gender = "Female";
        }
        String email = emailTextfield.getText();
        String marital = "null";
        if(married.isSelected()){
            marital="Married";
        }
        else if(unmarried.isSelected()){
            marital="Unmarried";
        }
        else if(other.isSelected()){
            marital="Other";
        }
        String address = addrTextfield.getText();
        String city = cityTextfield.getText();
        String state = stateTextfield.getText();
        String pin = pcodeTextfield.getText();

        try{
            if(name.isEmpty()){
                JOptionPane.showMessageDialog(null, "Name is required");
            }
            else{
                Conn c = new Conn();
                String query = "insert into signup values('"+formno+"', '"+name+"', '"+fname+"', '"+dob+"', '"+gender+"','"+email+"', '"+marital+"','"+address+"','"+city+"','"+pin+"','"+state+"')";
                c.s.executeUpdate(query);

                setVisible(false);
                new SignUpTwo(formno).setVisible(true);  // Pass formno to SignUpTwo
            }

        }
        catch (Exception e){
            System.out.println(e);
        }



    }
    public static void main(String[] args) {

        new SignUpOne();
    }
}
