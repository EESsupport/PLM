package com.origin.rac.sac.cgsqdcd;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.kernel.TCPreferenceService;
import com.teamcenter.rac.kernel.TCSession;

public class S4CGSQDCDDialog extends AbstractAIFDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AbstractAIFUIApplication app = null;
	public JComboBox textserentity = null;//ҵ��ʵ��
	public JComboBox textinvenORG=null;//�����֯
//	public JComboBox textproFile = null;//�ջ���ַ
	public JComboBox textDestination = null;//Ŀ�ĵ�����
	public JComboBox textsource=null;//��Դ
	public JComboBox textpurapptype= null;//�ɹ���������
	public JButton okButton=null;
	public JButton cancelButton=null;
	
	private String preferenceName = "SAC_QGD";
	private String[] optionKeys = null;
	private TCSession session ;
	private ArrayList<S4WLInfo> wlinfolist = null;
	//������ر���
	private String applyNumber;

	
	public S4CGSQDCDDialog(AbstractAIFUIApplication application,ArrayList<S4WLInfo> list,String str) {
		super(true);
		this.app = application;
		session = (TCSession)app.getSession();
		this.wlinfolist = list;
		this.applyNumber = str;
		optionKeys = getTCPreferenceArray(session, preferenceName);
		initUI();
	}
	
	public S4CGSQDCDDialog(AbstractAIFUIApplication application) {
		super(true);
		this.app = application;
		session = (TCSession)app.getSession();
		optionKeys = getTCPreferenceArray(session, preferenceName);
		initUI();
	}
	
	
	public void initUI(){
		this.setTitle("�빺��");
		this.setAlwaysOnTop(false);
		this.setResizable(false);
		getContentPane().add(Panel());
		centerToScreen();
		pack();
		setVisible(true);
	}
	
	
	 public JPanel Panel(){
			JPanel panel = new JPanel();
		//	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			JPanel panel0=new JPanel(new GridLayout(1,2));
			JPanel panel00=new JPanel();
			panel00.setLayout(new BoxLayout(panel00, BoxLayout.X_AXIS));
			JPanel panel01=new JPanel();
			panel01.setLayout(new BoxLayout(panel01, BoxLayout.X_AXIS));
			JPanel panel02=new JPanel();
			panel02.setLayout(new BoxLayout(panel02, BoxLayout.X_AXIS));
			
			JLabel lbserentity=new JLabel();
			lbserentity.setText("ҵ��ʵ��*");lbserentity.setPreferredSize(new Dimension(60,20));
			JLabel lbinvenORG=new JLabel();
			lbinvenORG.setText("�����֯*");lbinvenORG.setPreferredSize(new Dimension(60,20));
		//	JLabel lbproFile=new JLabel();
		//	lbproFile.setText("�ջ���ַ");lbproFile.setPreferredSize(new Dimension(60,20));
			
			textserentity =new JComboBox();
			textinvenORG=new JComboBox();
			textinvenORG.setEnabled(false);
		//	textproFile =new JComboBox();
			textserentity.addItem("");//
			for(int i=0;i<optionKeys.length;i++)
			{
				textserentity.addItem(optionKeys[i].split("=")[0].trim());
			}
			for(int i=0;i<optionKeys.length;i++)
			{
				
				if(textserentity.getSelectedItem().equals(optionKeys[i].split("=")[0].trim()))
				{
				    textinvenORG.removeAllItems();
					String[] kczz = optionKeys[i].split("=")[1].split(",");
					for (int j=0; j<kczz.length; j++) {
						System.out.println(kczz[j]);
					textinvenORG.addItem( kczz[j].trim());
					}
					textinvenORG.setSelectedIndex(0);
					}
				}
			textserentity.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < optionKeys.length; i++) {
						if (textserentity.getSelectedItem().equals("")
								|| textserentity.getSelectedItem() == null) {
							okButton.setEnabled(false);
							textinvenORG.setEnabled(false);
							textinvenORG.removeAllItems();
						} else if (textserentity.getSelectedItem().equals(
								optionKeys[i].split("=")[0].trim())) {
							okButton.setEnabled(true);
							textinvenORG.setEnabled(true);
							textinvenORG.removeAllItems();
							System.out.println("**********1******");
							String[] kczz = optionKeys[i].split("=")[1].split(",");
							for (int j = 0; j < kczz.length; j++) {
								System.out.println(kczz[j]);
								textinvenORG.addItem(kczz[j].trim());
							}
							textinvenORG.setSelectedIndex(0);
						}
						else {
							textinvenORG.setEnabled(true);
							okButton.setEnabled(true);
						}
					}
				}
			});
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
			lbDestination.setText("Ŀ�ĵ�����*");lbDestination.setPreferredSize(new Dimension(70,20));
			JLabel lbsource=new JLabel();
			lbsource.setText("  ��Դ*");lbsource.setPreferredSize(new Dimension(40,20));
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
			
			JPanel button_Panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
			
			okButton = new JButton("ȷ��");
			okButton.setEnabled(false);
			cancelButton = new JButton("ȡ��");
			button_Panel.add(okButton);
			button_Panel.add(cancelButton);
			// ����ť��Ӽ����¼�
			okButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e1) {
					try {
						closeDialog();
						String str_ywst = textserentity.getSelectedItem().toString();
						String str_kczz = textinvenORG.getSelectedItem().toString();
						String str_cgsqlx = textpurapptype.getSelectedItem().toString();
						String str_mddlx = textDestination.getSelectedItem().toString();
						String str_ly = textsource.getSelectedItem().toString();
						//����ִ����
						S4CGSQDCDOperation operation=new S4CGSQDCDOperation(app,wlinfolist,str_ywst,str_kczz,applyNumber,str_cgsqlx,str_mddlx,str_ly);
						operation.executeOperation();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}	
			});
			cancelButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					closeDialog();
				}
			});
			 
		   JPanel p0=new JPanel(new BorderLayout(10,10));
           p0.add(panel0,BorderLayout.NORTH);
           p0.add(panel1,BorderLayout.CENTER);
         
           JPanel p=new JPanel(new BorderLayout());
           p.add(p0,BorderLayout.NORTH);
           p.add(button_Panel,BorderLayout.CENTER);
		   panel.add(p);
		
		   return panel;
	 
	 }
	 
	 //��ȡ��ѡ��
	  private String[] getTCPreferenceArray(TCSession tcSession, String preferenceName) {
			
			String[] preString = null;
		
			TCPreferenceService tcPreservice = tcSession.getPreferenceService();
			
			preString = tcPreservice.getStringArray(TCPreferenceService.TC_preference_site, preferenceName);

			return preString;
		}
	  
	  
	  /**
		 * �رնԻ���
		 */
		public void closeDialog() {
			setVisible(false);
			disposeDialog();
		}

}
