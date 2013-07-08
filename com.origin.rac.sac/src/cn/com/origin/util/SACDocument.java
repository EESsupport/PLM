package cn.com.origin.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.teamcenter.rac.util.MessageBox;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Java ���ı����MQDocument�ĵ�������������ʽ���ж������Ƿ�Ϸ�
 *
 * @author 
 * @blog 
 */

public class SACDocument extends PlainDocument {
	
	private int maxLength = -1; // �����ַ���󳤶ȵ����ƣ�-1Ϊ������
	
	private Toolkit toolkit = null; // �����ڴ����ʱ�򷢳�ϵͳ����

	private boolean beep = false; // �Ƿ�����trueΪ��������

	
	// ���췽��

	public SACDocument() {
		super();
		this.init();
	}

	public SACDocument(Content c) {
		super(c);
		this.init();
	}

	/**
	 * ���й��춼��Ҫ�Ĺ�������
	 */
	private void init() {
		toolkit = Toolkit.getDefaultToolkit();
	}

	// ���췽������

	/**
	 * �����ı������������������ַ�����
	 * 
	 * @param maxLength
	 *            ����ַ�����
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * ȡ���ı�������ַ����ȵ�����
	 */
	public void cancelMaxLength() {
		this.maxLength = -1;
	}

	/**
	 * ʹ�����������ûָ�Ĭ��
	 */
	public void reset() {
		cancelMaxLength();
	}

	/**
	 * ����ʱ��������
	 * 
	 * @param beep
	 *            trueΪ������
	 */
	public void errorBeep(boolean beep) {
		this.beep = beep;
	}

	/**
	 * �������ʱ�Ƿ���
	 * 
	 * @return boolean trueΪ����
	 */
	public boolean isErrorBeep() {
		return beep;
	}

	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException, NumberFormatException {
		// ���ַ���Ϊ�գ�ֱ�ӷ��ء�
		if (str == null) {
			return;
		}
		boolean b = true;
		//System.out.println(str);
		if (maxLength > -1 && (this.getText(0,this.getLength()).getBytes().length+str.getBytes().length) >maxLength) {
			b = false;
			MessageBox.post("����������ݳ��ȳ�����"+this.maxLength, "��ʾ", MessageBox.INFORMATION);
		}
		char[] ch = str.toCharArray();
		/*
		for (int i = 0; i < ch.length; i++) {
			String temp = String.valueOf(ch[i]);
			// ���Ҫ������ַ���������Χ��
			if (!isOfLimit(temp)) {
				b = false;
			}
			// ������ַ��������ƣ��������ڵ��ַ������Ѿ����ڻ��������
			if (maxLength > -1 && (getText(0,getLength()).getBytes().length+str.getBytes().length) >maxLength){//str.getBytes().length
				b = false;
				MessageBox.post("����������ݳ��ȳ�����"+this.maxLength, "��ʾ", MessageBox.INFORMATION);
			}
			
		}
		*/
		
		
		/*
		 * 
		// ��������������������
		if (isMaxValue) {
			String s = this.getText(0, this.getLength()); // �ĵ������е��ַ�
			s = s.substring(0, offs) + str + s.substring(offs, s.length());
			if (Double.parseDouble(s) > maxValue) {
				if (beep) {
					toolkit.beep(); // ��������
				}
				return;
			}
		}
		
       */
		
		// ������벻�Ϸ�
		if (!b) {
			if (beep) {
				toolkit.beep(); // ��������
			}
			return;
		}

		super.insertString(offs, new String(ch), a);
	}

}

