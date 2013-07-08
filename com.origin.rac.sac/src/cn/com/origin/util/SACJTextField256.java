package cn.com.origin.util;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import com.teamcenter.rac.util.MessageBox;

public class SACJTextField256 extends JTextField {

	public SACJTextField256() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SACJTextField256(Document arg0, String arg1, int arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	public SACJTextField256(int arg0) {
		super(arg0);
	
		// TODO Auto-generated constructor stub
	}

	public SACJTextField256(String arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public SACJTextField256(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public Document createDefaultModel(){
		return  (new NumberDocument(getText()));
	}
	
	static class NumberDocument extends PlainDocument
	{
		String str1 = "" ;
		public NumberDocument(String sr)
		{
			str1 = sr ;
		}
		public void insertString(int offs,String str,AttributeSet a)
		{
			if(str == null)
			{
				return ;
			}
			try {
				if(getText(0, getLength()).getBytes().length+str.getBytes().length <=256)
				{
					super.insertString(offs, new String(str).trim(), a);
				}else
				{
					MessageBox.post("���ı�������������ݳ��ȳ�����256", "��ʾ", MessageBox.INFORMATION);
					System.out.println("wei kkkkkkkk===>:"+getText(0, getLength()).getBytes().length);
				}
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
