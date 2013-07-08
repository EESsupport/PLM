package com.origin.rac.sac.sendbominfo;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import cn.com.origin.util.SACJTextField64;
import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.util.PropertyLayout;

public class SendYXBomInfoDialog extends AbstractAIFDialog {
	
	private static final long serialVersionUID = 1L;
	private TCComponentBOMLine select_bom;
	public JButton okButton;
	public JButton cancelButton;
//	public JComboBox zzdm_box;//��֯����
	public JComboBox sfgd_checkbox;//�Ƿ�鵵
	public JComboBox whbmiTextFiled;//ά������
	public SACJTextField64 sjryiTextFiled;//�����Ա
	public SACJTextField64 wpxhiTextFiled;//��Ʒ�ͺ�
	public JComboBox gsiTextFiled;//��˾
	private String[] sfgd_str = {"","G","N","P","Y"};
	private String useName = "";
	private HashMap<String,String[]> map = new HashMap<String,String[]>();
	private String[] gs_str = null;
	private String[] chus_whbm_str = {""};
	private String[] whbm_str = null;
	private String[] all_str = null;
	private String zzdm = "";
	
	public SendYXBomInfoDialog(AbstractAIFUIApplication app,TCComponentBOMLine bom,String str1,String[] s,String str){
		super(true);
		this.select_bom = bom;
		this.useName = str1;
		this.all_str = s;
		this.zzdm = str;
		gs_str = new String[all_str.length+1];
		for (int i = 0; i < all_str.length; i++) {
			String[] value = all_str[i].split("=");
			gs_str[0] = "";
			gs_str[i+1] = value[0];
			String[] bm = value[1].split(",");
			map.put(value[0],bm);
		}
		initUI();
	}
	
	
	public void initUI(){
		this.setTitle("ԭ��BOM��������ά��");
		this.setAlwaysOnTop(false);
		this.setResizable(false);
		JPanel parentpanel = new JPanel(new PropertyLayout());
		JPanel first_Panel = new JPanel(new PropertyLayout());
		JPanel second_Panel = new JPanel(new PropertyLayout());
		JPanel button_Panel = new JPanel(new PropertyLayout());
		/*JLabel zzdm = new JLabel("��֯����*");
		zzdm.setForeground(Color.red);
		zzdm_box = new JComboBox(zzdm_str);
		zzdm_box.setPreferredSize(new Dimension(187,23));
		//ѡ����֯�����ļ����¼�
		zzdm_box.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String zzdm_select = zzdm_box.getSelectedItem().toString();
				if(zzdm_select==null || "".equals(zzdm_select)){
					okButton.setEnabled(false);
				}else{
					okButton.setEnabled(true);
				}
			}
			
		});*/
		JLabel sfgd = new JLabel("�Ƿ�鵵");
		sfgd_checkbox = new JComboBox(sfgd_str);
		sfgd_checkbox.setPreferredSize(new Dimension(187,23));
		JLabel whbm = new JLabel("ά������");
		whbmiTextFiled = new JComboBox(chus_whbm_str);
		whbmiTextFiled.setPreferredSize(new Dimension(187,23));
		whbmiTextFiled.setEnabled(false);
		JLabel sjry = new JLabel("�����Ա");
		sjryiTextFiled = new SACJTextField64(30);
		JLabel wpxh = new JLabel("��Ʒ�ͺ�");
		wpxhiTextFiled = new SACJTextField64(30);
		JLabel gs = new JLabel("��˾");
		gsiTextFiled = new JComboBox(gs_str);
		gsiTextFiled.setPreferredSize(new Dimension(187,23));
		//ѡ��˾��ļ����¼�
		gsiTextFiled.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String gs_select = gsiTextFiled.getSelectedItem().toString();
				System.out.println("gs_select----->:"+gs_select);
				if(gs_select!=null && !"".equals(gs_select)){
					whbmiTextFiled.setEnabled(true);
					whbm_str = map.get(gs_select);
					whbmiTextFiled.removeAllItems();
					for (int i = 0; i < whbm_str.length; i++) {
						whbmiTextFiled.addItem(whbm_str[i]);
					}
				}else{
					whbmiTextFiled.removeAllItems();
					whbmiTextFiled.addItem(" ");
					whbmiTextFiled.setEnabled(false);
				}
			}
			
		});
		okButton = new JButton("ȷ��");
		cancelButton = new JButton("ȡ��");
		
		first_Panel.add("1.1.right",new JLabel(" "));
		first_Panel.add("2.1.right",gs);
		first_Panel.add("2.2.right",gsiTextFiled);
		first_Panel.add("3.1.right",whbm);
		first_Panel.add("3.2.right",whbmiTextFiled);
		first_Panel.add("4.1.right",wpxh);
		first_Panel.add("4.2.right",wpxhiTextFiled);
		
		second_Panel.add("1.1.right",new JLabel(" "));
		second_Panel.add("2.1.right",sfgd);
		second_Panel.add("2.2.right",sfgd_checkbox);
		second_Panel.add("3.1.right",sjry);
		second_Panel.add("3.2.right",sjryiTextFiled);
		
		
		button_Panel.add("1.1.center",okButton);
		button_Panel.add("1.2.center",cancelButton);
		// ����ť��Ӽ����¼�
		okButton.addActionListener(enterListener());
		cancelButton.addActionListener(cancelListener());
		parentpanel.add("1.1.left",first_Panel);
		parentpanel.add("1.2.left",second_Panel);
		parentpanel.add("2.1.right",button_Panel);
		
		getContentPane().add(parentpanel);
		centerToScreen();
		pack();
		setVisible(true);
		
	}
	
	
	/**
	 * ȷ����ť�ļ����¼�
	 * */
	private ActionListener enterListener() {
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e1) {
				try {
					closeDialog();
					System.out.println("zzdm----->:"+zzdm);
					String str_sfgd = sfgd_checkbox.getSelectedItem().toString();
					String whbm = whbmiTextFiled.getSelectedItem().toString();
					String str_sjry = sjryiTextFiled.getText().toString();
					String str_wpxh = wpxhiTextFiled.getText().toString();
					String str_gs = gsiTextFiled.getSelectedItem().toString();
					String str_whbm = "";
					if("0".equals(whbm)){
						str_whbm = whbm;
					}else{
						str_whbm = whbm.split("\\.")[0];
					}
					System.out.println("str_whbm---->:"+str_whbm);
					//����ִ����
					SendYXBomInfoOperation operation=new SendYXBomInfoOperation(select_bom,zzdm,str_sfgd,str_whbm,str_sjry,str_wpxh,str_gs,useName);
					operation.executeOperation();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		return listener;
	}
	
	
	/**
	 * ȡ����ť�ļ����¼�
	 * */
	private ActionListener cancelListener() {
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e1) {
				//�رնԻ���
				closeDialog();
			}
		};
		return listener;
	}
	
	
	/**
	 * �رնԻ���
	 */
	public void closeDialog() {
		setVisible(false);
		disposeDialog();
	}

}
