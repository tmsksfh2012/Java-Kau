package src2;

// 명세표 ui

import java.awt.*;
import javax.swing.*;

public class SpecificationMessage extends JFrame {
    // main ui 생성되면 amount 수정

    public SpecificationMessage() {
        String[] btn = {"Yes", "No"};
        String chkSpecification = "거래 금액은 " + Integer.toString(DepositFrame.amount) + "원 입니다.\n명세표를 확인하시겠습니까?";

        int specification = JOptionPane.showOptionDialog(null, chkSpecification, null,
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, btn, "Yes");

        // 명세표 확인
        if (specification == 0) {
            // 현재 잔액 받아오는 거 수정
            String checkSpecification = "거래 금액: " + Integer.toString(DepositFrame.amount) + "\n현재 잔액: " + Integer.toString(MainFrame.user.getUserBalance());
            JOptionPane.showMessageDialog(null, checkSpecification, "명세표", 1);
            new MainFrame();
        }
        else {
        	new MainFrame();
        }
    }

}
