package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Pin extends JFrame implements ActionListener {

    JPasswordField t1,t2;
    JButton b1,b2;
    JLabel l1,l2,l3;
    String pin;

    Pin(String pin) {
        this.pin = pin;

        // ATM background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 960, 1080);
        add(background);

        l1 = new JLabel("CHANGE YOUR PIN");
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);

        l2 = new JLabel("New PIN:");
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setForeground(Color.WHITE);

        l3 = new JLabel("Re-Enter New PIN:");
        l3.setFont(new Font("System", Font.BOLD, 16));
        l3.setForeground(Color.WHITE);

        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));

        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 25));

        b1 = new JButton("CHANGE");
        b2 = new JButton("BACK");

        b1.addActionListener(this);
        b2.addActionListener(this);

        setLayout(null);

        l1.setBounds(280,330,800,35); background.add(l1);
        l2.setBounds(180,390,150,35); background.add(l2);
        l3.setBounds(180,440,200,35); background.add(l3);

        t1.setBounds(350,390,180,25); background.add(t1);
        t2.setBounds(350,440,180,25); background.add(t2);

        b1.setBounds(390,588,150,35); background.add(b1);
        b2.setBounds(390,633,150,35); background.add(b2);

        setSize(960,1080);
        setLocation(500,0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String npin = new String(t1.getPassword());
            String rpin = new String(t2.getPassword());

            if(!npin.equals(rpin)) {
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }

            if(npin.equals("") || rpin.equals("")) {
                JOptionPane.showMessageDialog(null, "PIN fields cannot be empty");
                return;
            }

            if(!npin.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(null, "PIN must be exactly 4 digits");
                return;
            }

            if(ae.getSource()==b1) {
                Conn c1 = new Conn();
                String q1 = "UPDATE bank SET pin = '"+rpin+"' WHERE pin = '"+pin+"'";
                String q2 = "UPDATE login SET pin = '"+rpin+"' WHERE pin = '"+pin+"'";
                String q3 = "UPDATE signup3 SET pin = '"+rpin+"' WHERE pin = '"+pin+"'";

                c1.s.executeUpdate(q1);
                c1.s.executeUpdate(q2);
                c1.s.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(rpin).setVisible(true);

            } else if(ae.getSource()==b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Pin("").setVisible(true);
    }
}