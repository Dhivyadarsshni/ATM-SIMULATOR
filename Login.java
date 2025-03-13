package com.ATMSimulator;
//Java's GUI Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    //public declarations of variables to use external
    JButton login, clear, signin;
    JTextField cardNoField;
    JPasswordField pinField;


    Login() {
        //frame's title
        setTitle("AUTOMATED TELLER MACHINE");

        //Frame's default Measurements
        setLayout(null);


        //image 1: bank symbol
        //get the image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));

        //set the size with Image class by awt
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        //cant have awt lib's object into swing, so create a new object and pass i2
        ImageIcon i3 = new ImageIcon(i2);

        //convert to label to add it in the frame
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        //change bg color
        getContentPane().setBackground(Color.WHITE);

        //add Welcome text
        JLabel welcome_text = new JLabel("Welcome to ATM");
        welcome_text.setFont(new Font("Times New Roman", Font.BOLD, 38));
        welcome_text.setBounds(200, 40, 400, 40);
        add(welcome_text);

        // Add Card No
        JLabel cardNo = new JLabel("Card No: ");
        cardNo.setFont(new Font("Raleway", Font.BOLD, 28));
        cardNo.setBounds(120, 150, 150, 30);
        add(cardNo);

        // Add input field for Card No
        cardNoField = new JTextField();
        cardNoField.setFont(new Font("Arial", Font.PLAIN, 18));
        cardNoField.setBounds(300, 150, 230, 30);
        add(cardNoField);

        // Add PIN
        JLabel pin = new JLabel("PIN: ");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 150, 30);
        add(pin);

        // Add input field for PIN
        pinField = new JPasswordField();
        pinField.setFont(new Font("Arial", Font.PLAIN, 18));
        pinField.setBounds(300, 220, 230, 30);
        add(pinField);

        // Login button
        login = new JButton("LOGIN");
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.setFont(new Font("Arial", Font.BOLD, 18)); // Reduced font size
        login.setBounds(300, 300, 100, 30);
        login.addActionListener(this);
        add(login);

        // Sign in button
        signin = new JButton("SIGN IN");
        signin.setBackground(Color.black);
        signin.setForeground(Color.white);
        signin.setFont(new Font("Arial", Font.BOLD, 18));
        signin.setBounds(430, 300, 100, 30);
        signin.addActionListener(this);
        add(signin);

        //Clear button
        clear = new JButton("CLEAR");
        clear.setBounds(360, 350, 100, 30);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.setFont(new Font("Arial", Font.BOLD, 18));
        clear.addActionListener(this);
        add(clear);


        setSize(800, 500);
        setVisible(true);
        setLocation(350, 200);
    }
    //add actions to all text fields and buttons

    //override method from ActionListener Interfaced
    // actionPerformed(ActionEvent ae) {
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == clear){
            cardNoField.setText(" ");
            pinField.setText("");
        } else if (ae.getSource()== login) {
            Conn conn = new Conn();
            String cardnumber = cardNoField.getText();
            String pinnumber = pinField.getText();
            String query = "select * from login where Card_Number = '"+cardnumber+"' and Pin_Number = '"+pinnumber+"' ";
            //System.out.println("Executing Query: " + query);
            try {
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()){
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or Pin");
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        else if (ae.getSource()== signin) {
            setVisible(false);
            new SignUpOne().setVisible(true);

        }

    }


    public static void main(String[] args) {

        new Login();

    }
}
