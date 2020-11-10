package src2;

// choose Deposit and WithDraw service

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DawIndexFrame extends JFrame {
    public DawIndexFrame() {
        setLayout(null);
        setTitle("Service");

        setSize(300, 180);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);

        Label txt = new Label("Choose Service");
        txt.setBounds(100, 10, 150, 20);

        add(txt);

        Button deposit = new Button("Deposit");
        Button withDraw = new Button("WithDraw");
        Button bankTransfer = new Button("Bank Transfer");
        Button chkBalance = new Button("Check Balance");
        //Button chkRecord = new Button("Check Record");
        Button back = new Button("Back");

        Panel P1 = new Panel();
        P1.add(deposit);
        Panel P2 = new Panel();
        P2.add(withDraw);
        Panel P3 = new Panel();
        P3.add(bankTransfer);
        Panel P4 = new Panel();
        P4.add(chkBalance);
        /*Panel P5 = new Panel();
        P5.add(chkRecord);*/
        Panel P6 = new Panel();
        P6.add(back);

        add(P1);
        P1.setBounds(25, 40, 100, 30);
        add(P2);
        P2.setBounds(155, 40, 100, 30);
        add(P3);
        P3.setBounds(25, 70, 100, 30);
        add(P4);
        P4.setBounds(155, 70, 100, 30);
        /*add(P5);
        P5.setBounds(25, 100, 100, 30);*/
        add(P6);
        P6.setBounds(155, 100, 100, 30);

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DepositFrame();
                dispose();
            }
        });
        withDraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WithdrawFrame();
                dispose();
            }
        });
        bankTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BankTransferFrame();
                dispose();
            }
        });
        chkBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new CheckBalanceFrame();
                 dispose();
            }
        });
        /*chkRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckRecordFrame();
                dispose();
            }
        });*/
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new MainFrame();
                dispose();
            }
        });
    }
}
