package com.gdnz.sac1.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class S4QGDRevisionMasterUI {

	
	 private static final long serialVersionUID = 1L;	
     public int maxlen=31;

		public JComboBox textserentity = null;//ҵ��ʵ��
		public JComboBox textinvenORG=null;//�����֯
	//	public JComboBox textproFile = null;//�ջ���ַ
		public JComboBox textDestination = null;//Ŀ�ĵ�����
		public JComboBox textsource=null;//��Դ
		public JComboBox textpurapptype= null;//�ɹ���������
		
		public JTabbedPane jTabbedPane = null;
		public JTabbedPane getJTabbedPane(){
			return jTabbedPane;
		}
		
		public S4QGDRevisionMasterUI(){
			//super(new GridLayout(1, 1));
			jTabbedPane = new JTabbedPane(JTabbedPane.NORTH);
			jTabbedPane.setBounds(0, 0, 500, 500);
			jTabbedPane.addTab("�빺��", Panel());
		//	jpanel=new JPanel();
		//	jpanel.setBounds(0, 0, 500, 500);
			
			
			}
		 public JPanel Panel(){
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
				JPanel panel0=new JPanel(new GridLayout(1,2));
				JPanel panel00=new JPanel();
				panel00.setLayout(new BoxLayout(panel00, BoxLayout.X_AXIS));
				JPanel panel01=new JPanel();
				panel01.setLayout(new BoxLayout(panel01, BoxLayout.X_AXIS));
				JPanel panel02=new JPanel();
				panel02.setLayout(new BoxLayout(panel02, BoxLayout.X_AXIS));
				
				JLabel lbserentity=new JLabel();
				lbserentity.setText("ҵ��ʵ��*");lbserentity.setPreferredSize(new Dimension(80,20));
				JLabel lbinvenORG=new JLabel();
				lbinvenORG.setText("�����֯*");lbinvenORG.setPreferredSize(new Dimension(80,20));
			//	JLabel lbproFile=new JLabel();
			//	lbproFile.setText("�ջ���ַ");lbproFile.setPreferredSize(new Dimension(60,20));
				
				textserentity =new JComboBox();
				textinvenORG=new JComboBox();
			//	textproFile =new JComboBox();
				
				

				panel00.add(lbserentity);	
				panel00.add(textserentity);
				
				panel01.add(lbinvenORG);
				panel01.add(textinvenORG);
				
			//	panel02.add(lbproFile);
			//	panel02.add(textproFile);
				
				panel0.add(panel00);
				panel0.add(panel01);
			//	panel0.add(panel02);
				
				JPanel panel1=new JPanel(new GridLayout(1,3));
				JPanel panel10=new JPanel();
				panel10.setLayout(new BoxLayout(panel10, BoxLayout.X_AXIS));
				JPanel panel11=new JPanel();
				panel11.setLayout(new BoxLayout(panel11, BoxLayout.X_AXIS));
				JPanel panel12=new JPanel();
				panel12.setLayout(new BoxLayout(panel12, BoxLayout.X_AXIS));
				
				
				JLabel lbDestination=new JLabel();
				lbDestination.setText("Ŀ�ĵ�����*");lbDestination.setPreferredSize(new Dimension(80,20));
				JLabel lbsource=new JLabel();
				lbsource.setText("  ��Դ*");lbsource.setPreferredSize(new Dimension(80,20));
				JLabel lbpurapptype=new JLabel();
				lbpurapptype.setText("�ɹ���������*");lbpurapptype.setPreferredSize(new Dimension(80,20));
		 
				textDestination =new JComboBox(new String[]{"���","����"});
				textDestination.setSelectedIndex(0);
				textsource=new JComboBox(new String[]{"��Ӧ��","���"});
				textsource.setSelectedIndex(0);
				textpurapptype=new JComboBox(new String[]{"�ɹ�����","�ڲ�����"});
				textpurapptype.setSelectedIndex(0);
				
				panel10.add(lbDestination);	
				panel10.add(textDestination);
				
				panel11.add(lbsource);
				panel11.add(textsource);
				
				panel12.add(lbpurapptype);
				panel12.add(textpurapptype);
				
				panel1.add(panel10);
				panel1.add(panel11);
				panel1.add(panel12);
				
				JPanel p0=new JPanel(new BorderLayout(5,5));
	             
	            p0.add(panel0,BorderLayout.NORTH);
	            p0.add(panel1,BorderLayout.CENTER);
	            
	            JPanel p=new JPanel(new BorderLayout());
	            p.add(p0,BorderLayout.NORTH);
				
				panel.add(p);
				return panel;
		 
		 }
}
