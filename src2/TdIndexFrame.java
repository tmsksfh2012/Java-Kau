package src2;

// choose Term Deposit service

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TdIndexFrame extends  JFrame{

    public TdIndexFrame() {
        setLayout(null);
        setTitle("Service");
        setSize(300, 180);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        Label txt = new Label("Choose Service");
        txt.setBounds(100, 10, 150, 20);
        add(txt);

        Button deposit = new Button("Deposit");
        Button chkBalance = new Button("Check Balance");
        //Button chkRecord = new Button("Check Record");
        Button back = new Button("Back");

        Panel P1 = new Panel();
        P1.add(deposit);
        Panel P2 = new Panel();
        P2.add(chkBalance);
        /*Panel P3 = new Panel();
        P3.add(chkRecord);*/
        Panel P4 = new Panel();
        P4.add(back);

        P1.setBounds(25, 40, 100, 30);
        P2.setBounds(155, 40, 100, 30);
        //P3.setBounds(25, 70, 100, 30);
        P4.setBounds(155, 70, 100, 30);


        add(P1);
        add(P2);
        //add(P3);

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DepositFrame();
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
