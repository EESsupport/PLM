package cn.com.origin.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.teamcenter.rac.util.MessageBox;

import java.awt.Toolkit;
import java.util.regex.Pattern;

/**
 * Java ���ı����MQDocument�ĵ�������������ʽ���ж������Ƿ�Ϸ�
 *
 * @author 
 * @blog 
 */

public class SACDocument32 extends PlainDocument {

	private String limit = null; // �����ַ����Ƶ�������ʽ

	private int maxLength = -1; // �����ַ���󳤶ȵ����ƣ�-1Ϊ������

	private double maxValue = 0; // �������������֣������ֵ����

	private boolean isMaxValue = false; // �Ƿ���������ֵ����

	private Toolkit toolkit = null; // �����ڴ����ʱ�򷢳�ϵͳ����

	private boolean beep = false; // �Ƿ�����trueΪ��������

	// ���췽��

	public SACDocument32() {
		super();
		this.init();
	}

	public SACDocument32(Content c) {
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
	 * �����ַ���������
	 * 
	 * @param limit
	 *  �������� ������ʽ
	 */
	public void setCharLimit(String limit) {
		this.limit = limit;
	}

	/**
	 * �����ַ����Ƶ�����
	 * 
	 * @return ����
	 */
	public String getCharLimit() {
		return this.limit;
	}

	/**
	 * ������������ַ�
	 */
	public void clearLimit() {
		this.limit = null;
	}

	/**
	 * �ַ����������Ƿ�������ַ�
	 * 
	 * @param input
	 *            �ַ�
	 * @return trueΪ������falseΪ������
	 */
	public boolean isOfLimit(CharSequence input) {
		if (limit == null) {
			return true;
		} else {
			return Pattern.compile(limit).matcher(input).find();
		}
	}

	/**
	 * �ַ�������������Ƿ�Ϊ��
	 * 
	 * @return trueΪ�գ�falseΪ��
	 */
	public boolean isEmptyLimit() {
		if (limit == null) {
			return true;
		} else {
			return false;
		}
	}

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
	 * ��������Ϊ�����֣�����ô˷������������ֵ����ֵ
	 * 
	 * @param maxValue
	 *            ���ֵ
	 */
	public void setMaxValue(double maxValue) {
		this.isMaxValue = true;
		this.maxValue = maxValue;
	}

	/**
	 * �ı����Ƿ��������������ݵ������ֵ
	 * 
	 * @return trueΪ������
	 */
	public boolean isMaxValue() {
		return this.isMaxValue;
	}

	/**
	 * �������������������ֵ
	 * 
	 * @return double�����ֵ�����û�����ƻ᷵��0
	 */
	public double getMaxValue() {
		return this.maxValue;
	}

	/**
	 * ȡ���������ݵ����ֵ����
	 */
	public void cancelMaxValue() {
		this.isMaxValue = false;
		this.maxValue = 0;
	}

	/**
	 * ʹ�����������ûָ�Ĭ��
	 */
	public void reset() {
		clearLimit();
		cancelMaxLength();
		cancelMaxValue();
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
		if(str == null)
		{
			return ;
		}
		try {
			if(getText(0, getLength()).getBytes().length+str.getBytes().length <=32)
			{
				super.insertString(offs, new String(str).trim(), a);
			}else
			{
				MessageBox.post("���ı�������������ݳ��ȳ�����32", "��ʾ", MessageBox.INFORMATION);
				System.out.println("wei kkkkkkkk===>:"+str.getBytes().length);
			}
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

