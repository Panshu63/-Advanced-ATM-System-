package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Signup3 extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
    JRadioButton r1,r2,r3,r4;
    JButton b1,b2;
    JCheckBox c1,c2,c3,c4,c5,c6,c7;
    String formno;

    Signup3(String formno) {
        this.formno = formno;
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 3");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel l14 = new JLabel(new ImageIcon(i2));
        l14.setBounds(150, 0, 100, 100);
        add(l14);

        l1 = new JLabel("Page 3: Account Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));

        l2 = new JLabel("Account Type:");
        l2.setFont(new Font("Raleway", Font.BOLD, 18));

        l3 = new JLabel("Card Number:");
        l3.setFont(new Font("Raleway", Font.BOLD, 18));

        l4 = new JLabel("XXXX-XXXX-XXXX-4184");
        l4.setFont(new Font("Raleway", Font.BOLD, 18));

        l5 = new JLabel("(Your 16-digit Card number)");
        l5.setFont(new Font("Raleway", Font.BOLD, 12));

        l6 = new JLabel("It would appear on ATM Card/Cheque Book and Statements");
        l6.setFont(new Font("Raleway", Font.BOLD, 12));

        l7 = new JLabel("PIN:");
        l7.setFont(new Font("Raleway", Font.BOLD, 18));

        l8 = new JLabel("XXXX");
        l8.setFont(new Font("Raleway", Font.BOLD, 18));

        l9 = new JLabel("(4-digit password)");
        l9.setFont(new Font("Raleway", Font.BOLD, 12));

        l10 = new JLabel("Services Required:");
        l10.setFont(new Font("Raleway", Font.BOLD, 18));

        l11 = new JLabel("Form No:");
        l11.setFont(new Font("Raleway", Font.BOLD, 14));

        l12 = new JLabel(formno);
        l12.setFont(new Font("Raleway", Font.BOLD, 14));

        b1 = new JButton("Submit");
        b1.setFont(new Font("Raleway", Font.BOLD, 14));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2 = new JButton("Cancel");
        b2.setFont(new Font("Raleway", Font.BOLD, 14));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        // Services checkboxes
        c1 = new JCheckBox("ATM CARD"); c2 = new JCheckBox("Internet Banking");
        c3 = new JCheckBox("Mobile Banking"); c4 = new JCheckBox("EMAIL Alerts");
        c5 = new JCheckBox("Cheque Book"); c6 = new JCheckBox("E-Statement");
        c7 = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge.", true);

        for(JCheckBox cb : new JCheckBox[]{c1,c2,c3,c4,c5,c6,c7}) {
            cb.setBackground(Color.WHITE);
            cb.setFont(new Font("Raleway", Font.BOLD, cb==c7 ? 12 : 16));
        }

        // Account type radio buttons
        r1 = new JRadioButton("Saving Account");
        r2 = new JRadioButton("Fixed Deposit Account");
        r3 = new JRadioButton("Current Account");
        r4 = new JRadioButton("Recurring Deposit Account");

        for(JRadioButton rb : new JRadioButton[]{r1,r2,r3,r4}) {
            rb.setFont(new Font("Raleway", Font.BOLD, 16));
            rb.setBackground(Color.WHITE);
        }

        ButtonGroup groupAccType = new ButtonGroup();
        groupAccType.add(r1); groupAccType.add(r2); groupAccType.add(r3); groupAccType.add(r4);

        setLayout(null);

        // Positioning
        l11.setBounds(700,10,70,30); add(l11);
        l12.setBounds(770,10,40,30); add(l12);

        l1.setBounds(280,40,400,40); add(l1);

        l2.setBounds(100,140,200,30); add(l2);
        r1.setBounds(100,180,150,30); add(r1);
        r2.setBounds(350,180,300,30); add(r2);
        r3.setBounds(100,220,250,30); add(r3);
        r4.setBounds(350,220,250,30); add(r4);

        l3.setBounds(100,300,200,30); add(l3);
        l4.setBounds(330,300,250,30); add(l4);
        l5.setBounds(100,330,200,20); add(l5);
        l6.setBounds(330,330,500,20); add(l6);

        l7.setBounds(100,370,200,30); add(l7);
        l8.setBounds(330,370,200,30); add(l8);
        l9.setBounds(100,400,200,20); add(l9);

        l10.setBounds(100,450,200,30); add(l10);
        c1.setBounds(100,500,200,30); add(c1);
        c2.setBounds(350,500,200,30); add(c2);
        c3.setBounds(100,550,200,30); add(c3);
        c4.setBounds(350,550,200,30); add(c4);
        c5.setBounds(100,600,200,30); add(c5);
        c6.setBounds(350,600,200,30); add(c6);
        c7.setBounds(100,680,600,20); add(c7);

        b1.setBounds(250,720,100,30); add(b1);
        b2.setBounds(420,720,100,30); add(b2);

        getContentPane().setBackground(Color.WHITE);
        setSize(850,850);
        setLocation(500,120);
        setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String atype = null;
        if(r1.isSelected()) atype = "Saving Account";
        else if(r2.isSelected()) atype = "Fixed Deposit Account";
        else if(r3.isSelected()) atype = "Current Account";
        else if(r4.isSelected()) atype = "Recurring Deposit Account";

        Random ran = new Random();
        String cardno = String.format("%016d", Math.abs(ran.nextLong() % 10000000000000000L));
        String pin = String.format("%04d", ran.nextInt(10000));

        String facility = "";
        if(c1.isSelected()) facility += " ATM Card";
        if(c2.isSelected()) facility += " Internet Banking";
        if(c3.isSelected()) facility += " Mobile Banking";
        if(c4.isSelected()) facility += " EMAIL Alerts";
        if(c5.isSelected()) facility += " Cheque Book";
        if(c6.isSelected()) facility += " E-Statement";

        try {
            if(ae.getSource()==b1) {
                if(atype == null) {
                    JOptionPane.showMessageDialog(null, "Please select an account type");
                } else {
                    Conn c1 = new Conn();
                    String q1 = "INSERT INTO signup3 VALUES('"+formno+"','"+atype+"','"+cardno+"','"+pin+"','"+facility+"')";
                    String q2 = "INSERT INTO login VALUES('"+formno+"','"+cardno+"','"+pin+"')";
                    c1.s.executeUpdate(q1);
                    c1.s.executeUpdate(q2);

                    JOptionPane.showMessageDialog(null, "Card Number: " + cardno + "\nPin: " + pin);
                    new Deposit(pin).setVisible(true);
                    setVisible(false);
                }
            } else if(ae.getSource()==b2) {
                System.exit(0);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup3("").setVisible(true);
    }
}