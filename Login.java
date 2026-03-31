package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3;

    // Animation variables
    private Color[] colors = { new Color(255, 255, 255), new Color(200, 230, 255), new Color(255, 240, 200) };
    private int colorIndex = 0;
    private JPanel backgroundPanel;

    Login() {
        setTitle("AUTOMATED TELLER MACHINE");

        // Animated background panel
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(colors[colorIndex]);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        // Timer to animate background color
        Timer timer = new Timer(1500, e -> {
            colorIndex = (colorIndex + 1) % colors.length;
            backgroundPanel.repaint();
        });
        timer.start();

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(70, 10, 100, 100);
        backgroundPanel.add(l11);

        l1 = new JLabel("WELCOME TO ATM");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setBounds(200,40,450,40);
        backgroundPanel.add(l1);

        l2 = new JLabel("Card No:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(125,150,375,30);
        backgroundPanel.add(l2);

        tf1 = new JTextField(15);
        tf1.setBounds(300,150,230,30);
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        backgroundPanel.add(tf1);

        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(125,220,375,30);
        backgroundPanel.add(l3);

        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(300,220,230,30);
        backgroundPanel.add(pf2);

        b1 = new JButton("SIGN IN");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2 = new JButton("CLEAR");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        b3 = new JButton("SIGN UP");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);

        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(300,300,100,30);
        backgroundPanel.add(b1);

        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(430,300,100,30);
        backgroundPanel.add(b2);

        b3.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setBounds(300,350,230,30);
        backgroundPanel.add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        setSize(800,480);
        setLocation(550,200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if(ae.getSource()==b1) {
                Conn c1 = new Conn();
                String cardno  = tf1.getText();
                String pin  = new String(pf2.getPassword());
                String q  = "SELECT * FROM login WHERE cardnumber = '"+cardno+"' AND pin = '"+pin+"'";

                ResultSet rs = c1.s.executeQuery(q);
                if(rs.next()) {
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } else if(ae.getSource()==b2) {
                tf1.setText("");
                pf2.setText("");
            } else if(ae.getSource()==b3) {
                setVisible(false);
                new Signup().setVisible(true);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}