package com.gdnz.sac1.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import cn.com.origin.util.ProgressBarThread;

import com.origin.rac.sac.htjs.S4HTJSOperation;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.kernel.TCTextService;
import com.teamcenter.rac.util.MessageBox;

public class CGSQDBianHaoDialog extends AbstractAIFDialog {
	/**
	 * 
	 */
	private AbstractAIFApplication application;
	private static final long serialVersionUID = 1L;
	private TCSession session = null;
	public JTextField textbianhao = null;//������
	public JButton okButton=null;
	public JButton cancelButton=null;
	public  CGSQDBianHaoDialog(AbstractAIFApplication app) {
		super(true);
		this.application=app;
		session=(TCSession) app.getSession();
		// TODO Auto-generated constructor stub
		initUI();
	}
	public void initUI(){
		this.setTitle("�ɹ����뵥���");
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
			panel00.setPreferredSize(new Dimension(200,20));
			
			JLabel lbbianhao=new JLabel();
			lbbianhao.setText("������");lbbianhao.setPreferredSize(new Dimension(60,20));
			textbianhao =new JTextField();

			panel00.add(lbbianhao);	
			panel00.add(textbianhao);

			
			panel0.add(panel00);
			
			JPanel button_Panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
			
			okButton = new JButton("ȷ��");
		//	okButton.setEnabled(false);
			cancelButton = new JButton("ȡ��");
			button_Panel.add(okButton);
			button_Panel.add(cancelButton);
			// ����ť��Ӽ����¼�
			okButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(textbianhao.getText()==null)
						textbianhao.setText("");
					InterfaceAIFComponent[] items = query(textbianhao.getText());
					if(items == null || items.length==0){
						
						String s=textbianhao.getText();
						closeDialog();
						try {
							S4CGSQDSCOperation option = new S4CGSQDSCOperation(application,s);	
							option.executeOperation();
						} catch (TCException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					else{
						MessageBox.post("���������������Ѵ���", "��ʾ", MessageBox.INFORMATION);
						return;
					}
					
				}	
			});
			cancelButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					closeDialog();
				}
			});
			 
         
           JPanel p=new JPanel(new BorderLayout());
           p.add(panel0,BorderLayout.NORTH);
           p.add(button_Panel,BorderLayout.CENTER);
		   panel.add(p);
		   return panel;
	 
	 }
	 
	 public void closeDialog() {
		setVisible(false);
		disposeDialog();
	}
	
	 
	//����ϵͳ����� ID��ѯ
	 public InterfaceAIFComponent[] query(String id) {
		InterfaceAIFComponent[] items = null;
		try {
			TCTextService tcService = session.getTextService();
			String askKey[] = { tcService.getTextValue("ItemID") };
			String askValue[] = { id };
			items = session.search("����� ID", askKey, askValue);
		} catch (TCException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}	
		
}
