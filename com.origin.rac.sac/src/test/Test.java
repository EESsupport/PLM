package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * ����MQDocument�������ı������������
 *
 * @author 
 * @blog 
 */

public class Test extends JFrame {

	private JPanel pane = null;

	private JTextArea text = null;
	private JTextArea text1 = null;

	private JComboBox combox = null;
	private MQDocument doc = null;
	private MQDocument doc1 = null;
	private String[] str = {"","kk*���Ľ���","pp*������"};

	public Test() {
		super("Test");
		pane = new JPanel();
		combox = new JComboBox(str);
		combox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(combox.getSelectedItem().toString().contains("*")){
					System.out.println("KKKKKKKKKK");
					/*combox.removeAllItems();
					for (int i = 0; i < str.length; i++) {
						combox.addItem(str[i]);
					}*/
					String s1 = combox.getSelectedItem().toString();
					System.out.println("s1====>:"+s1);
					String s2 = s1.split("\\*")[0];
					System.out.println("s2---->:"+s2);
//					combox.removeItem(s1);
					combox.addItem(s2);
					combox.setSelectedItem(s2);
					combox.updateUI();
					pane.updateUI();
				}
			}
			
		});
		
		pane.add(combox);
		/*text = new JTextArea(3,20);
		text1 = new JTextArea(4,10);
		text.setLineWrap(true);
		text1.setLineWrap(true);
		doc = new MQDocument();
		doc1 = new MQDocument();
		text.setDocument(doc);
		text1.setDocument(doc1);
		pane.add(text);
		pane.add(text1);
		doc.errorBeep(true); // ���벻�Ϸ�ʱ������ʾ��
		doc.setMaxLength(100); // ���볤�Ȳ��ܳ���9���ַ�
		doc1.setMaxLength(8); // ���볤�Ȳ��ܳ���9���ַ�
//		doc.setCharLimit("[a-zA-Z]"); // ֻ���������СдӢ����ĸ
		doc.setCharLimit("[0-9]"); // ֻ������������
		// doc.setCharLimit("[\u4e00-\u9fa5]"); // ֻ������������
*/		this.getContentPane().add(pane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 180);
		this.setVisible(true);
	}

	public static void main(String args[]) {
		new Test();
	}

}

