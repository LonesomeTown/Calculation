package com.tw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
/**
 *JTextFieldHintListener
 *
 *@author T.W 2021/6/23
 */
public class TextFieldHintListener implements FocusListener {
	private final String hintText;
	private final JTextField textField;
	public TextFieldHintListener(JTextField jTextField, String hintText) {
		this.textField = jTextField;
		this.hintText = hintText;
		//默认直接显示
		jTextField.setText(hintText);
		jTextField.setForeground(Color.GRAY);
	}
 
	@Override
	public void focusGained(FocusEvent e) {
		//获取焦点时，清空提示内容
		String temp = textField.getText();
		if(temp.equals(hintText)) {
			textField.setText("");
			textField.setForeground(Color.BLACK);
		}
		
	}
 
	@Override
	public void focusLost(FocusEvent e) {	
		//失去焦点时，没有输入内容，显示提示内容
		String temp = textField.getText();
		if("".equals(temp)) {
			textField.setForeground(Color.GRAY);
			textField.setText(hintText);
		}
		
	}
 
}