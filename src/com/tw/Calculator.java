package com.tw;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

/**
 * Main
 *
 * @author T.W 2021/6/23
 */
public class Calculator implements ActionListener {
    JLabel amountLabel;
    JTextField amountText;
    JLabel discountRateLabel;
    JTextField discountRateText;
    JLabel resultLabel;
    JTextField resultText;
    JButton singleBtn;
    JButton groupBtn;

    int frameWidth = 400;
    int frameHeight = 400;

    public static void main(String[] args) {
        // write your code here
        new Calculator();
    }


    Calculator() {
        JFrame frame = new JFrame("提成计算");
        amountLabel = new JLabel("金额:");
        amountLabel.setBounds(frameWidth / 5, frameHeight / 6, frameWidth / 6, frameHeight / 10);
        amountText = new JTextField();
        amountText.setBounds(frameWidth * 2 / 5, frameHeight / 6, frameWidth / 3, frameHeight / 10);

        discountRateLabel = new JLabel("折扣率");
        discountRateLabel.setBounds(frameWidth / 5, frameHeight * 2 / 6, frameWidth / 6, frameHeight / 10);
        discountRateText = new JTextField();
        discountRateText.setBounds(frameWidth * 2 / 5, frameHeight * 2 / 6, frameWidth / 3, frameHeight / 10);
        discountRateText.addFocusListener(new TextFieldHintListener(discountRateText, "请输入小数"));

        resultLabel = new JLabel("提成:");
        resultLabel.setBounds(frameWidth / 5, frameHeight * 3 / 6, frameWidth / 6, frameHeight / 10);
        resultText = new JTextField();
        resultText.setBounds(frameWidth * 2 / 5, frameHeight * 3 / 6, frameWidth / 3, frameHeight / 10);
        resultText.setEditable(false);

        singleBtn = new JButton("计算提成");
        singleBtn.setBounds(frameWidth / 3, frameHeight * 4 / 6, frameWidth / 3, frameHeight / 10);
        singleBtn.addActionListener(this);
//        groupBtn = new JButton("团队提成");
//        groupBtn.setBounds(frameWidth * 4 / 7, frameHeight * 4 / 6, frameWidth / 4, frameHeight / 10);
//        groupBtn.addActionListener(this);

        frame.add(amountLabel);
        frame.add(amountText);
        frame.add(discountRateLabel);
        frame.add(discountRateText);
        frame.add(resultLabel);
        frame.add(resultText);
        frame.add(singleBtn);
//        frame.add(groupBtn);

        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String result = null;
        String amountStr = amountText.getText();
        String discountRateStr = discountRateText.getText();
        double amount = Double.parseDouble(amountStr);
        double discountRate = 1;
        if (null != discountRateStr && !"".equals(discountRateStr)) {
            discountRate = Double.parseDouble(discountRateStr);
        }
        if (e.getSource() == singleBtn) {
            result = this.calculateSingle(amount, discountRate);
        }
        resultText.setText(result);
    }

    String calculateSingle(Double amount, Double discountRate) {
        BigDecimal personalRate = new BigDecimal("0.01");
        BigDecimal groupRate = new BigDecimal("0");
        BigDecimal amountDec = new BigDecimal(amount.toString());
        BigDecimal discountRateDc = new BigDecimal(discountRate.toString());
        if (amount >= 60001 && amount <= 90000) {
            personalRate = new BigDecimal("0.02");
        } else if (amount >= 90001 && amount <= 160000) {
            personalRate = new BigDecimal("0.03");
        } else if (amount >= 160001 && amount <= 300000) {
            personalRate = new BigDecimal("0.04");
        } else if (amount >= 300001 && amount <= 500000) {
            personalRate = new BigDecimal("0.05");
        } else if (amount >= 500001) {
            personalRate = new BigDecimal("0.06");
        }

        if (amount >= 600000 && amount <= 900000) {
            groupRate = new BigDecimal("0.2");
        } else if (amount >= 900001 && amount <= 1300000) {
            groupRate = new BigDecimal("0.3");
        } else if (amount >= 1300001 && amount <= 1800000) {
            groupRate = new BigDecimal("0.36");
        } else if (amount >= 1800001 && amount <= 2400000) {
            groupRate = new BigDecimal("0.45");
        } else if (amount >= 2400001) {
            groupRate = new BigDecimal("0.50");
        }
        BigDecimal result = amountDec.multiply(personalRate).multiply(groupRate.add(BigDecimal.valueOf(1))).multiply(discountRateDc);
        return result.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }

}
