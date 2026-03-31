package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField t1;
    JButton b1,b2;
    JLabel l1,l2;
    String pin;

    Withdrawl(String pin) {
        this.pin = pin;

        // ATM background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 960, 1080);
        add(background);

        l1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        l2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 16));

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));

        b1 = new JButton("WITHDRAW");
        b2 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(190,350,400,20); background.add(l1);
        l2.setBounds(190,400,400,20); background.add(l2);
        t1.setBounds(190,450,330,30); background.add(t1);

        b1.setBounds(390,588,150,35); background.add(b1);
        b2.setBounds(390,633,150,35); background.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setSize(960,1080);
        setLocation(500,0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amountText = t1.getText().trim();
            Date date = new Date();

            if(ae.getSource()==b1) {
                if(amountText.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
                    return;
                }

                int amount;
                try {
                    amount = Integer.parseInt(amountText);
                } catch(NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");
                    return;
                }

                if(amount > 10000) {
                    JOptionPane.showMessageDialog(null, "Maximum withdrawal limit is Rs.10,000");
                    return;
                }

                Conn c1 = new Conn();
                ResultSet rs = c1.s.executeQuery("SELECT * FROM bank WHERE pin = '"+pin+"'");
                int balance = 0;
                while(rs.next()) {
                    if(rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                if(balance < amount) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                c1.s.executeUpdate("INSERT INTO bank VALUES('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");

                setVisible(false);
                new Transactions(pin).setVisible(true);

            } else if(ae.getSource()==b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("error: "+e);
        }
    }

    public static void main(String[] args) {
        new Withdrawl("").setVisible(true);
    }
}