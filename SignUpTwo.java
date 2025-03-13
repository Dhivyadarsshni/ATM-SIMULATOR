package com.ATMSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpTwo extends JFrame implements ActionListener {

    String formno;
    //global declaration
    JTextField panTextfield, aadhaarTextfield;

    JButton next;

    JRadioButton seniorcitizen_yes, seniorcitizen_no,existingacc_yes, existingacc_no;

    JComboBox religionDropdown , categoryDropdown, incomeDropdown, qualificationDropDown, occupationDropdown;

    SignUpTwo(String formno){

        this.formno= formno;

        //frame's title
        setTitle("APPLICATION PAGE:02" +formno);

        //Frame's default Measurements
        setLayout(null);

        //Sub - Heading : Additional details - page 2
        JLabel addlDetails = new JLabel("Page 2 : Additional Details ");
        addlDetails.setBounds(290,80,400,30);
        addlDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        add(addlDetails);

        //Religion
        JLabel religion_category = new JLabel("Religion: ");
        religion_category.setBounds(100,140,100,30);
        religion_category.setFont(new Font("Raleway", Font.BOLD, 20));
        add(religion_category);

        //Religion dropdown
        String valReligion[] = {"Select religion ", "Hindu" , "Christian ", "Muslim", "Sikh", "Others"};
        religionDropdown = new JComboBox(valReligion);
        religionDropdown.setFont(new Font("Raleway", Font.BOLD, 14));
        religionDropdown.setBackground(Color.WHITE);
        religionDropdown.setBounds(300,140,400,30);
        add(religionDropdown);

        // Category
        JLabel category_name = new JLabel("Category: ");
        category_name.setBounds(100,190,200,30);
        category_name.setFont(new Font("Raleway", Font.BOLD, 20));
        add(category_name);

        //Category Dropdown
        String valCategory[] = {"Select category ","General" , "OBC ", "FC", "SC","ST", "Others"};
        categoryDropdown = new JComboBox(valCategory);
        categoryDropdown.setBackground(Color.WHITE);
        categoryDropdown.setFont(new Font("Raleway", Font.BOLD, 14));
        categoryDropdown.setBounds(300,190,400,30);
        add(categoryDropdown);

        //Income
        JLabel income_amt = new JLabel("Annual Income: ");
        income_amt.setBounds(100,240,200,30);
        income_amt.setFont(new Font("Raleway", Font.BOLD, 20));
        add(income_amt);

        //Income Dropdown
        String valIncome[] = {"Null" , "< 1,50,000 ", "< 2,50,000", "< 5,00,000","< 10,00,000", "10,00,000+"};
        incomeDropdown = new JComboBox(valIncome);
        incomeDropdown.setBackground(Color.WHITE);
        incomeDropdown.setFont(new Font("Raleway", Font.BOLD, 14));
        incomeDropdown.setBounds(300,240,400,30);
        add(incomeDropdown);

        //Educational
        JLabel educational = new JLabel("Educational ");
        educational.setBounds(100,315,200,30);
        educational.setFont(new Font("Raleway", Font.BOLD, 20));
        add(educational);

        //Qualification
        JLabel qualification_details = new JLabel("Qualification: ");
        qualification_details.setBounds(100,340,200,30);
        qualification_details.setFont(new Font("Raleway", Font.BOLD, 20));
        add(qualification_details);

        //Educational Qualification dropdown--
        String valQualification[] = {"Select Qualification ","Graduate" , "Post - Graduate ", "12th/Diploma", "10th", "Others"};
        qualificationDropDown = new JComboBox(valQualification);
        qualificationDropDown.setBackground(Color.WHITE);
        qualificationDropDown.setFont(new Font("Raleway", Font.BOLD, 14));
        qualificationDropDown.setBounds(300,340,400,30);
        add(qualificationDropDown);

        //Occupation
        JLabel occupational_details = new JLabel("Occupation: ");
        occupational_details.setBounds(100,390,200,30);
        occupational_details.setFont(new Font("Raleway", Font.BOLD, 20));
        add(occupational_details);

        String valOccupation[] = {"Select valid Occupation ","Salaried" , "Self - Employed ", "Business", "Retired", "Others"};
        occupationDropdown = new JComboBox(valOccupation);
        occupationDropdown.setBackground(Color.WHITE);
        occupationDropdown.setFont(new Font("Raleway", Font.BOLD, 14));
        occupationDropdown.setBounds(300,390,400,30);
        add(occupationDropdown);

        //PAN number
        JLabel pan_id = new JLabel("PAN number: ");
        pan_id.setBounds(100,440,200,30);
        pan_id.setFont(new Font("Raleway", Font.BOLD, 20));
        add(pan_id);

        //PAN number Textfield --
        panTextfield = new JTextField();
        panTextfield.setFont(new Font("Raleway", Font.BOLD, 14));
        panTextfield.setBounds(300,440,400,30);
        add(panTextfield);

        //Aadhaar Number
        JLabel aadhaar_id = new JLabel("Aadhaar Number: ");
        aadhaar_id.setBounds(100,490,200,30);
        aadhaar_id.setFont(new Font("Raleway", Font.BOLD, 20));
        add(aadhaar_id);

        //Aadhaar Number Textfield--
        aadhaarTextfield  = new JTextField();
        aadhaarTextfield.setFont(new Font("Raleway", Font.BOLD, 14));
        aadhaarTextfield.setBounds(300,490,400,30);
        add(aadhaarTextfield);

        //Senior Citizen
        JLabel seniorcitizen_details = new JLabel("Senior Citizen: ");
        seniorcitizen_details.setBounds(100,540,200,30);
        seniorcitizen_details.setFont(new Font("Raleway", Font.BOLD, 20));
        add(seniorcitizen_details);

        //seniorcitizen Radio-button
        //yes
        seniorcitizen_yes = new JRadioButton("Yes");
        seniorcitizen_yes.setBounds(300,540,90,30);
        seniorcitizen_yes.setBackground(Color.WHITE);
        add(seniorcitizen_yes);

        //no
        seniorcitizen_no = new JRadioButton("No");
        seniorcitizen_no.setBounds(400,540,90,30);
        seniorcitizen_no.setBackground(Color.WHITE);
        add(seniorcitizen_no);

        //Button grouper to select either yes or no
        ButtonGroup seniorcitizenGroup = new ButtonGroup();
        seniorcitizenGroup.add(seniorcitizen_yes);
        seniorcitizenGroup.add(seniorcitizen_no);

        //Existing Account
        JLabel existingacc_details = new JLabel("Existing Account: ");
        existingacc_details.setBounds(100,590,200,30);
        existingacc_details.setFont(new Font("Raleway", Font.BOLD, 20));
        add(existingacc_details);

        //existing account Radio-button
        //yes
        existingacc_yes = new JRadioButton("Yes");
        existingacc_yes.setBounds(300,590,90,30);
        existingacc_yes.setBackground(Color.WHITE);
        add(existingacc_yes);

        //no
        existingacc_no = new JRadioButton("No");
        existingacc_no.setBounds(400,590,90,30);
        existingacc_no.setBackground(Color.WHITE);
        add(existingacc_no);

        //Button grouper to select either yes or no
        ButtonGroup existingaccGroup = new ButtonGroup();
        existingaccGroup.add(existingacc_yes);
        existingaccGroup.add(existingacc_no);


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

        if (ae.getSource()== next) {
            setVisible(false);
        }
        String religion = (String)religionDropdown.getSelectedItem();
        String category = (String)categoryDropdown.getSelectedItem();
        String income = (String)incomeDropdown.getSelectedItem();
        String qualification = (String)qualificationDropDown.getSelectedItem();
        String occupation = (String)occupationDropdown.getSelectedItem();
        String pan = panTextfield.getText();
        String aadhaar = aadhaarTextfield.getText();
        String seniorcitizen = null;
        if(seniorcitizen_yes.isSelected()){
            seniorcitizen= "Yes";
        }
        else if(seniorcitizen_no.isSelected()){
            seniorcitizen = "No";
        }
        String existingaccount = "null";
        if(existingacc_yes.isSelected()){
            existingaccount="Yes";
        }
        else if(existingacc_no.isSelected()){
            existingaccount="No";
        }
        try{
            if(aadhaar.isEmpty()){
                JOptionPane.showMessageDialog(null, "Aadhaar ID is required");
            }
            else{
                Conn c = new Conn();
                String query = "insert into signupTwo values('"+formno+"','"+religion+"', '"+category+"', '"+income+"','"+qualification+"', '"+occupation+"','"+pan+"','"+aadhaar+"','"+seniorcitizen+"','"+existingaccount+"')";
                c.s.executeUpdate(query);
                setVisible(false);
                new SignUpThree(formno).setVisible(true);
            }

        }
        catch (Exception e){
            System.out.println(e);
        }



    }
    public static void main(String[] args) {

        new SignUpTwo(" ");
    }
}
