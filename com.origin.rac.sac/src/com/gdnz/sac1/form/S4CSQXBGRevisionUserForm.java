package com.gdnz.sac1.form;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.eclipse.jface.dialogs.MessageDialog;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.commands.namedreferences.ImportFilesOperation;
import com.teamcenter.rac.commands.newdataset.NewDatasetCommand;
import com.teamcenter.rac.commands.newdataset.NewDatasetOperation;
import com.teamcenter.rac.form.AbstractTCForm;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentDataset;
import com.teamcenter.rac.kernel.TCComponentDatasetType;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentItemType;
import com.teamcenter.rac.kernel.TCComponentProcess;
import com.teamcenter.rac.kernel.TCComponentSignoff;
import com.teamcenter.rac.kernel.TCComponentTask;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCPreferenceService;
import com.teamcenter.rac.kernel.TCProperty;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.kernel.TCComponentDatasetType;


public class S4CSQXBGRevisionUserForm extends AbstractTCForm {
	private String DEFAULT="���ֻ����д256����,";
	private int maxlength=512;
	private static final long serialVersionUID = 1L;
	private TCComponentForm form = null;
	private S4CSQXBGRevisionUI s4CSQXBGRevisionUI = null;
	
	private TCProperty assets= null;//������Ʒ
	private TCProperty repPeo = null;//������
	private TCProperty repDate= null;//��������
	private TCProperty testedObj = null;//�������
	private TCProperty currversion = null;//��ǰ�汾��
	private TCProperty defectDescribtion = null;//ȱ������
	private TCProperty defectClass = null;//ȱ�ݷ���
	private TCProperty servletlevel = null;//���صȼ�
	private TCProperty proprior = null;//�������ȼ�
	private TCProperty suggestRepa = null;//�޸�����
	private TCProperty status = null;//״̬
	private TCProperty resoversion = null;//�����汾��
	private TCProperty resolvingPro = null;//�����Ա
	private TCProperty changeRe = null;//���ļ�¼
	private TCProperty exegesisl = null;//ȱ���޸�ע��
	private TCProperty exegesisr = null;//�ع����ע��
	
//	private TCProperty[] fujian ;//����
	private TCProperty fujian =null;//����

	
	private String strAssets= null;//������Ʒ
	private String strRepPeo = null;//������
	private Date strRepDate= null;//��������
	private String strTestedObj = null;//�������
	private String strCurrversion = null;//��ǰ�汾��
	private String strDefectDescribtion = null;//ȱ������
	private String strDefectClass = null;//ȱ�ݷ���
	private String strServletlevel = null;//���صȼ�
	private String strProprior = null;//�������ȼ�
	private String strSuggestRepa = null;//�޸�����
	private String strStatus = null;//״̬
	private String strResoversion = null;//�����汾��
	private String strResolvingPro = null;//�����Ա
	private String strChangeRe = null;//���ļ�¼
	private String strExegesisl = null;//ȱ���޸�ע��
	private String strExegesisr = null;//�ع����ע��
	
	private String strfujian[] = null;//����
	
	private String node_name=null;//�����н����Ա��ֵ
	
   
	
	private String preferenceName = "DefectRepForm";
	private String current_node_name=null;
	private String[] optionKeys = null;
	private String start = "��ʼ";
	
	

   
	public S4CSQXBGRevisionUserForm(TCComponentForm arg0) throws Exception {
		super(arg0);
		// TODO Auto-generated constructor stub
		form=arg0;
		initUI();
		loadForm();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		setLayout(new GridLayout(1, 1));
		s4CSQXBGRevisionUI = new S4CSQXBGRevisionUI();
		JTabbedPane jTabbedPane = s4CSQXBGRevisionUI.getJTabbedPane();
		add(jTabbedPane);
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
	}

	/**
	 * 
	 */
	@Override
	public void loadForm() throws TCException {
		System.out.println("******loading begin******");
		assets=form.getTCProperty("s4Assets");
		repPeo=form.getTCProperty("s4reporter");
		repDate=form.getTCProperty("s4repdate1");
		testedObj=form.getTCProperty("s4measurand");
		currversion=form.getTCProperty("s4nowversion");
		defectDescribtion=form.getTCProperty("s4Description");
		defectClass=form.getTCProperty("s4DefectClass");
		servletlevel=form.getTCProperty("s4Severlevel");
		proprior=form.getTCProperty("s4proprior");
		suggestRepa=form.getTCProperty("s4suggestrepa");
		status=form.getTCProperty("s4statuss");
		resoversion=form.getTCProperty("s4solvversion");
		resolvingPro=form.getTCProperty("s4Resolvingpro");
		changeRe=form.getTCProperty("s4ChangeRe");
		exegesisl=form.getTCProperty("s4exegesis");
		exegesisr=form.getTCProperty("s4exegesis1");
        fujian=form.getTCProperty("s4acc");
        s4CSQXBGRevisionUI.fujiancount=fujian.getStringArrayValue().length;//��������

		// TODO Auto-generated method stub
		strAssets=assets.getStringValue();
		s4CSQXBGRevisionUI.textassets.setText(strAssets);
		strRepPeo = repPeo.getStringValue();
		s4CSQXBGRevisionUI.textrepPeo.setText(strRepPeo);
		if(repDate.getDateValue()==null)
			//DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
			//strRepDate=df.format(new Date());
			strRepDate=new Date();
		else
		strRepDate=repDate.getDateValue();
		s4CSQXBGRevisionUI.buttonrepDate.setText((new SimpleDateFormat("yyyy/MM/dd")).format(strRepDate));
		strTestedObj=testedObj.getStringValue();
		s4CSQXBGRevisionUI.texttestedObj.setText(strTestedObj);
		strCurrversion=currversion.getStringValue();
		s4CSQXBGRevisionUI.textcurrversion.setText(strCurrversion);
		//strKaiFaRenYuan=kaiFaRenYuan.getStringValue();
		//s4CSQXBGRevisionUI.textkaiFaRenYuan.setText(strKaiFaRenYuan);
		strDefectDescribtion=defectDescribtion.getStringValue();
		if(strDefectDescribtion==null||strDefectDescribtion.equals(""))
		{
			s4CSQXBGRevisionUI.textdefectDescribtion.setText(DEFAULT+"�ò����ɲ�����Ա��д");
			s4CSQXBGRevisionUI.textdefectDescribtion.setForeground(Color.GRAY);
			
		}
		else
		   s4CSQXBGRevisionUI.textdefectDescribtion.setText(strDefectDescribtion);
		
		strDefectClass=defectClass.getStringValue();
		if(strDefectClass.equals("software"))
			strDefectClass="���";
		else	if(strDefectClass.equals("hardware"))
			strDefectClass="Ӳ��";
	//	System.out.println("*******111*******"+strDefectClass);
		s4CSQXBGRevisionUI.textdefectClass.setSelectedItem(strDefectClass);
		strServletlevel=servletlevel.getStringValue();
		if(strServletlevel.equals("Critical"))
			strServletlevel="����";
		else	if(strServletlevel.equals("Major"))
			strServletlevel="����";
		else	if(strServletlevel.equals("Minor"))
			strServletlevel="һ��";
		else	if(strServletlevel.equals("Cosmetic"))
			strServletlevel="����";
	//	System.out.println("*******222*******"+strServletlevel);
		s4CSQXBGRevisionUI.textservletlevel.setSelectedItem(strServletlevel);
		strProprior=proprior.getStringValue();
		if(strProprior.equals("highs"))
			strProprior="������ȼ�";
		else	if(strProprior.equals("high"))
			strProprior="�θ����ȼ�";
		else	if(strProprior.equals("medium"))
			strProprior="�е����ȼ�";
		else	if(strProprior.equals("low"))
			strProprior="������ȼ�";
	//	System.out.println("*******333*******"+strProprior);
		s4CSQXBGRevisionUI.textproprior.setSelectedItem(strProprior);
		
		strSuggestRepa=suggestRepa.getStringValue();
		
		if(strSuggestRepa==null||strSuggestRepa.equals(""))
		{
			s4CSQXBGRevisionUI.textsuggestRepa.setText(DEFAULT+"�ò�������Ŀ������д");
			s4CSQXBGRevisionUI.textsuggestRepa.setForeground(Color.GRAY);
			
		}
		else
		   s4CSQXBGRevisionUI.textsuggestRepa.setText(strSuggestRepa);
		strStatus=status.getStringValue();
		if(strStatus.equals("new"))
			strStatus="�½�";
		else	if(strStatus.equals("open"))
			strStatus="��";
		else	if(strStatus.equals("reject"))
			strStatus="�ܾ�";
		else	if(strStatus.equals("process"))
			strStatus="������";
		else	if(strStatus.equals("openagain"))
			strStatus="�ٴδ�";
		else	if(strStatus.equals("closed"))
			strStatus="�ر�";
		s4CSQXBGRevisionUI.textstatus.setSelectedItem(strStatus);
		
		
		
		
		strResoversion=resoversion.getStringValue();
		if(strResoversion==null||strResoversion.equals(""))
		{
			s4CSQXBGRevisionUI.textresoversion.setText("�ò�������Ŀ������д");
			s4CSQXBGRevisionUI.textresoversion.setForeground(Color.GRAY);
			
		}
		else
		s4CSQXBGRevisionUI.textresoversion.setText(strResoversion);
		//�Զ���ȡ��˽ڵ��ִ����
		strResolvingPro=resolvingPro.getStringValue();
		node_name = getCurrentStates();  
		if(node_name==null)
		{}
		else
		strResolvingPro=node_name;
		
		System.out.println("node_name=123=="+node_name);
		//�޸ĵĵط�
		if(strResolvingPro!=null){
			s4CSQXBGRevisionUI.textresolvingPro.setText(strResolvingPro);//������Ա�ĵط�
		}
		
	//	s4CSQXBGRevisionUI.textresolvingPro.setText(strResolvingPro);
		
		strChangeRe=changeRe.getStringValue();
		if(strChangeRe==null||strChangeRe.equals(""))
		{
			s4CSQXBGRevisionUI.textchangeRe.setText(DEFAULT+"�ò������з���Ա��д");
			s4CSQXBGRevisionUI.textchangeRe.setForeground(Color.GRAY);
			
		}
		else
		    s4CSQXBGRevisionUI.textchangeRe.setText(strChangeRe);
		strExegesisl=exegesisl.getStringValue();
		if(strExegesisl==null||strExegesisl.equals(""))
		{
			s4CSQXBGRevisionUI.textexegesisl.setText(DEFAULT+"�ò������з���Ա��д");
			s4CSQXBGRevisionUI.textexegesisl.setForeground(Color.GRAY);
			
		}
		else
		    s4CSQXBGRevisionUI.textexegesisl.setText(strExegesisl);
		
		strExegesisr=exegesisr.getStringValue();
		if(strExegesisr==null||strExegesisr.equals(""))
		{
			s4CSQXBGRevisionUI.textexegesisr.setText(DEFAULT+"�ò����ɲ�����Ա��д");
			s4CSQXBGRevisionUI.textexegesisr.setForeground(Color.GRAY);
			
		}
		else
		    s4CSQXBGRevisionUI.textexegesisr.setText(strExegesisr);

		
		strfujian=fujian.getStringArrayValue();
		
		if(s4CSQXBGRevisionUI.fujiancount>0){
		s4CSQXBGRevisionUI.accessoryPanel=new JPanel(new GridLayout(1,1));
		for(int i=0;i<s4CSQXBGRevisionUI.fujiancount;i++)
	 	{
    	s4CSQXBGRevisionUI.p[i]=new JPanel(new FlowLayout(FlowLayout.LEFT));
    	//System.out.println(form.getProperty("s4accessorylink").toString());
    	//s4CSQXBGRevisionUI.filelabel[i]=new LinkLabel(strfujian[i],new File(form.getProperty("s4accessorylink").toString()));
    	s4CSQXBGRevisionUI.filelabel[i]=new LinkLabel(strfujian[i],new File(""));
    	s4CSQXBGRevisionUI.filelabel[i].setForeground(Color.BLUE);
    	//System.out.println(s4CSQXBGRevisionUI.filelabel[i].getText());
    	s4CSQXBGRevisionUI.p[i].add(s4CSQXBGRevisionUI.filelabel[i]);
       	final JLabel label_delete=new JLabel("ɾ��");
    	s4CSQXBGRevisionUI.p[i].add(label_delete);
    	s4CSQXBGRevisionUI.accessoryPanel.setLayout(new GridLayout(	s4CSQXBGRevisionUI.accessoryPanel.getComponentCount()+1,1));//��������Panel�Ĳ���
    	s4CSQXBGRevisionUI.accessoryPanel.add(	s4CSQXBGRevisionUI.p[i]);
    	//System.out.println("*******fujian mianban count**********"+s4CSQXBGRevisionUI.accessoryPanel.getComponentCount());
    	label_delete.addMouseListener(new MouseAdapter(){
			public void mouseExited(MouseEvent e) {  
				label_delete.setForeground(Color.BLACK);
						    	  }                     
			public void mouseEntered(MouseEvent e) { 
				label_delete.setForeground(Color.RED);
				label_delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
				
			public void mouseClicked(MouseEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ���ø�����","ɾ������",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION){
					s4CSQXBGRevisionUI.accessoryPanel.setLayout(new GridLayout(s4CSQXBGRevisionUI.accessoryPanel.getComponentCount()-1,1));//��������Panel�Ĳ���
					int j=0;
					for(int i=0;i<s4CSQXBGRevisionUI.fujiancount;i++)
						if(s4CSQXBGRevisionUI.filelabel[i].getParent()==e.getComponent().getParent())//ɾ��--����
							{
							   j=i;//ѡ��ɾ�����Ǹ�����
							   try {  
									
									AbstractAIFApplication app= AIFUtility.getCurrentApplication();
									TCSession session=(TCSession)app.getSession();
									TCComponentDatasetType tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("Dataset");
									TCComponentDataset dataset=tccomponentDatasetType.find(s4CSQXBGRevisionUI.filelabel[i].getText());
									//System.out.println(dataset.getUid());
							//		TCComponentItemRevision mItemRevision=(TCComponentItemRevision)app.getTargetContext().getParentComponent();
							//		if (mItemRevision==null){
							//			System.out.println("��ѡ�б�����");
							//			return;
							//			}
								//	mItemRevision.remove("IMAN_specification",dataset );
									if(dataset==null)//���ݼ��Ѿ���ɾ������ʱ�����κζ���
									    {}
									else
									{ 
									   dataset.delete();
									   dataset.clearCache();
									}
									}catch (TCException err) {
										// TODO Auto-generated catch block
										err.printStackTrace();
									}  
							   break;
							}
					for(;j<s4CSQXBGRevisionUI.fujiancount-1;j++){
						s4CSQXBGRevisionUI.filelabel[j].setText(s4CSQXBGRevisionUI.filelabel[j+1].getText());
						s4CSQXBGRevisionUI.filelabel[j].setPath(s4CSQXBGRevisionUI.filelabel[j+1].getPath());
					}
					s4CSQXBGRevisionUI.fujiancount--;
					s4CSQXBGRevisionUI.accessoryPanel.remove(s4CSQXBGRevisionUI.p[s4CSQXBGRevisionUI.fujiancount]);
					s4CSQXBGRevisionUI.accessoryPanel.setLayout(new GridLayout(s4CSQXBGRevisionUI.fujiancount,1));
					s4CSQXBGRevisionUI.jTabbedPane.repaint();//ʵʱˢ�±�ǩ�ϵ�����
					s4CSQXBGRevisionUI.jTabbedPane.setBounds(0, 0, 500, 500);
				//	s4CSQXBGRevisionUI.jTabbedPane.setBounds(0, 0, s4CSQXBGRevisionUI.jTabbedPane.getWidth(), s4CSQXBGRevisionUI.jTabbedPane.getHeight());
				} 
				}
    	}
			);
	 	}
    	s4CSQXBGRevisionUI.panel3.add(s4CSQXBGRevisionUI.accessoryPanel,BorderLayout.CENTER);
	//	s4CSQXBGRevisionUI.textstatus.setEditable(false);
		}
	    System.out.println("********loading end*********");
	    TCSession session = form.getSession();
		
		optionKeys = getTCPreferenceArray(session, preferenceName);
		
		getCurrentName();
		if (current_node_name == null) {
			if (optionKeys.length != 0) {
				System.out.println("**1");

				for (int i= 0; i<optionKeys.length; i++) {
					System.out.println("**nj1");

					if (start.equals(optionKeys[i].split(",")[1].split("=")[0].trim())) {
						System.out.println("hhhhh");
						String[] editText = optionKeys[i].split("=")[1].split(",");
						for (int j=0; j<editText.length; j++) {
							if ((editText[j].trim()).equals("s4reporter")) {
								s4CSQXBGRevisionUI.textrepPeo.setEnabled(false);
							} 
				
							if ((editText[j].trim()).equals("s4Assets")) {
								s4CSQXBGRevisionUI.textassets.setEnabled(false);
							} 
							if ((editText[j].trim()).equals("s4repdate1")) {
								s4CSQXBGRevisionUI.buttonrepDate.setEnabled(false);
								s4CSQXBGRevisionUI.buttonrepDate.removeActionListener(s4CSQXBGRevisionUI.buttonrepDate.getDateActionListener());

								    } 
							if ((editText[j].trim()).equals("s4measurand")) {
								s4CSQXBGRevisionUI.texttestedObj.setEnabled(false);
							} 
							if ((editText[j].trim()).equals("s4nowversion")) {
								s4CSQXBGRevisionUI.textcurrversion.setEnabled(false);
							} 
				
							if ((editText[j].trim()).equals("s4Description")) {
								s4CSQXBGRevisionUI.textdefectDescribtion.setEnabled(false);
							} 
				
							if ((editText[j].trim()).equals("s4DefectClass")) {
								s4CSQXBGRevisionUI.textdefectClass.getComponent(0).removeMouseListener(s4CSQXBGRevisionUI.textdefectClass.getMouseListeners()[0]);
								s4CSQXBGRevisionUI.textdefectClass.removeMouseListener(s4CSQXBGRevisionUI.textdefectClass.getMouseListeners()[0]);
							} 
							if ((editText[j].trim()).equals("s4Severlevel")) {
							//	s4CSQXBGRevisionUI.textservletlevel.setEnabled(false);
								s4CSQXBGRevisionUI.textservletlevel.getComponent(0).removeMouseListener(s4CSQXBGRevisionUI.textservletlevel.getMouseListeners()[0]);
								s4CSQXBGRevisionUI.textservletlevel.removeMouseListener(s4CSQXBGRevisionUI.textservletlevel.getMouseListeners()[0]);
							} 
				
							if ((editText[j].trim()).equals("s4proprior")) {
							//	s4CSQXBGRevisionUI.textproprior.setEnabled(false);
								s4CSQXBGRevisionUI.textproprior.getComponent(0).removeMouseListener(s4CSQXBGRevisionUI.textproprior.getMouseListeners()[0]);
								s4CSQXBGRevisionUI.textproprior.removeMouseListener(s4CSQXBGRevisionUI.textproprior.getMouseListeners()[0]);

							} 
				
							if ((editText[j].trim()).equals("s4suggestrepa")) {
								s4CSQXBGRevisionUI.textsuggestRepa.setEnabled(false);
							}
							if ((editText[j].trim()).equals("s4statuss")) {
							//	s4CSQXBGRevisionUI.textstatus.setEnabled(false);
								s4CSQXBGRevisionUI.textstatus.getComponent(0).removeMouseListener(s4CSQXBGRevisionUI.textstatus.getMouseListeners()[0]);
								s4CSQXBGRevisionUI.textstatus.removeMouseListener(s4CSQXBGRevisionUI.textstatus.getMouseListeners()[0]);
								
							} 
							if ((editText[j].trim()).equals("s4solvversion")) {
								s4CSQXBGRevisionUI.textresoversion.setEnabled(false);
							} 
				
							if ((editText[j].trim()).equals("s4Resolvingpro")) {
								s4CSQXBGRevisionUI.textresolvingPro.setEnabled(false);
							}
							if ((editText[j].trim()).equals("s4ChangeRe")) {
								s4CSQXBGRevisionUI.textchangeRe.setEnabled(false);
							}
							if ((editText[j].trim()).equals("s4exegesis")) {
								s4CSQXBGRevisionUI.textexegesisl.setEnabled(false);
							}
							if ((editText[j].trim()).equals("s4exegesis1")) {
								s4CSQXBGRevisionUI.textexegesisr.setEnabled(false);
							}
						}
					}
				}
			}
	}

		else {
				if (optionKeys.length != 0) {
					System.out.println("**2");

					for (int i= 0; i<optionKeys.length; i++) {
						System.out.println("**nj2");

						if (current_node_name.equals(optionKeys[i].split(",")[1].split("=")[0].trim())) {
							System.out.println("hhhhh");
							String[] editText = optionKeys[i].split("=")[1].split(",");
							for (int j=0; j<editText.length; j++) {
								if ((editText[j].trim()).equals("s4reporter")) {
									s4CSQXBGRevisionUI.textrepPeo.setEnabled(false);
								} 
					
								if ((editText[j].trim()).equals("s4Assets")) {
									s4CSQXBGRevisionUI.textassets.setEnabled(false);
								} 
								
								if ((editText[j].trim()).equals("s4repdate1")) {
									s4CSQXBGRevisionUI.buttonrepDate.setEnabled(false);
									s4CSQXBGRevisionUI.buttonrepDate.removeActionListener(s4CSQXBGRevisionUI.buttonrepDate.getDateActionListener());
								} 
								
								if ((editText[j].trim()).equals("s4measurand")) {
									s4CSQXBGRevisionUI.texttestedObj.setEnabled(false);
								} 
								if ((editText[j].trim()).equals("s4nowversion")) {
									s4CSQXBGRevisionUI.textcurrversion.setEnabled(false);
								} 
					
								if ((editText[j].trim()).equals("s4Description")) {
									s4CSQXBGRevisionUI.textdefectDescribtion.setEnabled(false);
								} 
					
								if ((editText[j].trim()).equals("s4DefectClass")) {
									s4CSQXBGRevisionUI.textdefectClass.getComponent(0).removeMouseListener(s4CSQXBGRevisionUI.textdefectClass.getMouseListeners()[0]);
									s4CSQXBGRevisionUI.textdefectClass.removeMouseListener(s4CSQXBGRevisionUI.textdefectClass.getMouseListeners()[0]);
								} 
								if ((editText[j].trim()).equals("s4Severlevel")) {
								//	s4CSQXBGRevisionUI.textservletlevel.setEnabled(false);
									s4CSQXBGRevisionUI.textservletlevel.getComponent(0).removeMouseListener(s4CSQXBGRevisionUI.textservletlevel.getMouseListeners()[0]);
									s4CSQXBGRevisionUI.textservletlevel.removeMouseListener(s4CSQXBGRevisionUI.textservletlevel.getMouseListeners()[0]);
								} 
					
								if ((editText[j].trim()).equals("s4proprior")) {
								//	s4CSQXBGRevisionUI.textproprior.setEnabled(false);
									s4CSQXBGRevisionUI.textproprior.getComponent(0).removeMouseListener(s4CSQXBGRevisionUI.textproprior.getMouseListeners()[0]);
									s4CSQXBGRevisionUI.textproprior.removeMouseListener(s4CSQXBGRevisionUI.textproprior.getMouseListeners()[0]);

								} 
					
								if ((editText[j].trim()).equals("s4suggestrepa")) {
									s4CSQXBGRevisionUI.textsuggestRepa.setEnabled(false);
								}
								if ((editText[j].trim()).equals("s4statuss")) {
								//	s4CSQXBGRevisionUI.textstatus.setEnabled(false);
									s4CSQXBGRevisionUI.textstatus.getComponent(0).removeMouseListener(s4CSQXBGRevisionUI.textstatus.getMouseListeners()[0]);
									s4CSQXBGRevisionUI.textstatus.removeMouseListener(s4CSQXBGRevisionUI.textstatus.getMouseListeners()[0]);
									
								} 								
								if ((editText[j].trim()).equals("s4solvversion")) {
									s4CSQXBGRevisionUI.textresoversion.setEnabled(false);
								} 
					
								if ((editText[j].trim()).equals("s4Resolvingpro")) {
									s4CSQXBGRevisionUI.textresolvingPro.setEnabled(false);
								}
								if ((editText[j].trim()).equals("s4ChangeRe")) {
									s4CSQXBGRevisionUI.textchangeRe.setEnabled(false);
								}
								if ((editText[j].trim()).equals("s4exegesis")) {
									s4CSQXBGRevisionUI.textexegesisl.setEnabled(false);
									
								}
								if ((editText[j].trim()).equals("s4exegesis1")) {
									s4CSQXBGRevisionUI.textexegesisr.setEnabled(false);
									
								}
							}
						}
					}
				}
		}
		
	  
	}

	

	@Override
	public void saveForm() {
		// TODO Auto-generated method stub
		try{
		strAssets=(String)s4CSQXBGRevisionUI.textassets.getText();
		strRepPeo = (String) s4CSQXBGRevisionUI.textrepPeo.getText();
		try{
		strRepDate=	s4CSQXBGRevisionUI.buttonrepDate.getDate();
		System.out.println(strRepDate);
		}catch(Exception e){
			System.out.println("���������ʽ����");
		}
		strTestedObj=(String)s4CSQXBGRevisionUI.texttestedObj.getText();
		strCurrversion=(String)s4CSQXBGRevisionUI.textcurrversion.getText();
		strDefectDescribtion = (String) s4CSQXBGRevisionUI.textdefectDescribtion.getText();
		strDefectClass=(String)s4CSQXBGRevisionUI.textdefectClass.getSelectedItem().toString();
		if(strDefectClass.equals("���"))
			strDefectClass="software";
		else	if(strDefectClass.equals("Ӳ��"))
			strDefectClass="hardware";
		strServletlevel=(String)s4CSQXBGRevisionUI.textservletlevel.getSelectedItem().toString();
		if(strServletlevel.equals("����"))
			strServletlevel="Critical";
		else	if(strServletlevel.equals("����"))
			strServletlevel="Major";
		else	if(strServletlevel.equals("һ��"))
			strServletlevel="Minor";
		else	if(strServletlevel.equals("����"))
			strServletlevel="Cosmetic";
		strProprior=(String)s4CSQXBGRevisionUI.textproprior.getSelectedItem().toString();
		if(strProprior.equals("������ȼ�"))
			strProprior="highs";
		else	if(strProprior.equals("�θ����ȼ�"))
			strProprior="high";
		else	if(strProprior.equals("�е����ȼ�"))
			strProprior="medium";
		else	if(strProprior.equals("������ȼ�"))
			strProprior="low";
		strSuggestRepa = (String) s4CSQXBGRevisionUI.textsuggestRepa.getText();
		strStatus=(String)s4CSQXBGRevisionUI.textstatus.getSelectedItem().toString();
		if(strStatus.equals("�½�"))
			strStatus="new";
		else	if(strStatus.equals("��"))
			strStatus="open";
		else	if(strStatus.equals("�ܾ�"))
			strStatus="reject";
		else	if(strStatus.equals("������"))
			strStatus="process";
		else	if(strStatus.equals("�ٴδ�"))
			strStatus="openagain";
		else	if(strStatus.equals("�ر�"))
			strStatus="closed";
	//	strStatus=String.valueOf(s4CSQXBGRevisionUI.textstatus.getSelectedIndex());
		strResoversion=(String)s4CSQXBGRevisionUI.textresoversion.getText();
    	strResolvingPro=(String)s4CSQXBGRevisionUI.textresolvingPro.getText();
		strChangeRe=(String)s4CSQXBGRevisionUI.textchangeRe.getText();	
		strExegesisl=(String)s4CSQXBGRevisionUI.textexegesisl.getText();
		strExegesisr=(String)s4CSQXBGRevisionUI.textexegesisr.getText();
		strfujian=new String[s4CSQXBGRevisionUI.fujiancount];
		for(int i=0;i<s4CSQXBGRevisionUI.fujiancount;i++)
		{  
			strfujian[i]=(String)s4CSQXBGRevisionUI.filelabel[i].getText();
		}
	
		

		assets.setStringValue(strAssets);
		repPeo.setStringValue(strRepPeo);
		repDate.setDateValue(strRepDate);
		testedObj.setStringValue(strTestedObj);
		currversion.setStringValue(strCurrversion);
		
		if(strDefectDescribtion.equals(DEFAULT+"�ò����ɲ�����Ա��д"))
			strDefectDescribtion="";
		defectDescribtion.setStringValue(strDefectDescribtion);
		
	//	defectClass.setStringValueData(strDefectClass);
		defectClass.setStringValue(strDefectClass);
		defectClass.setStringValue(strDefectClass);
		servletlevel.setStringValue(strServletlevel);
		proprior.setStringValue(strProprior);
		
		if(strSuggestRepa.equals(DEFAULT+"�ò�������Ŀ������д"))
			strSuggestRepa="";
		suggestRepa.setStringValue(strSuggestRepa);
		
		status.setStringValue(strStatus);
		if(strResoversion.equals("�ò�������Ŀ������д"))
			strResoversion="";
		resoversion.setStringValue(strResoversion);
		resolvingPro.setStringValue(strResolvingPro);
		
		if(strChangeRe.equals(DEFAULT+"�ò������з���Ա��д"))
			strChangeRe="";
		changeRe.setStringValue(strChangeRe);
		
		if(strExegesisl.equals(DEFAULT+"�ò������з���Ա��д"))
			strExegesisl="";
		exegesisl.setStringValue(strExegesisl);
		System.out.println(strExegesisl);
		
		if(strExegesisr.equals(DEFAULT+"�ò����ɲ�����Ա��д"))
			strExegesisr="";
		exegesisr.setStringValue(strExegesisr);
		
		fujian.setStringValueArray(strfujian);
		System.out.println("**** save ǰ����������*****"+s4CSQXBGRevisionUI.fujiancount);
		
		TCProperty[] tcProperty = new TCProperty[17];
		tcProperty[0] = assets;
		tcProperty[1] = repPeo;
		tcProperty[2] = repDate;
		tcProperty[3] = testedObj;
		tcProperty[4] = currversion;
		tcProperty[5] = defectDescribtion;
		tcProperty[6] = defectClass;
		tcProperty[7] = servletlevel;
		tcProperty[8] = proprior;
		tcProperty[9] = suggestRepa;
		tcProperty[10] = status; 
		tcProperty[11] = resoversion;
		tcProperty[12] = resolvingPro;
		tcProperty[13] = changeRe;
		tcProperty[14] = exegesisl;
		tcProperty[15] = exegesisr;
		tcProperty[16] = fujian;
				
		form.setTCProperties(tcProperty);
		System.out.println("********saving end*********");
		
	//	form.setIntProperty("s4account", s4CSQXBGRevisionUI.fujiancount);
		//huoquyaoguanjiede  ItemRevision

		//TCComponentItemRevision mItemRevision = ((TCComponentItem)com).getLatestItemRevision();
		for(int i=0;i<s4CSQXBGRevisionUI.fujiancount;i++){
			
			AbstractAIFApplication app= AIFUtility.getCurrentApplication();
			TCSession session=(TCSession)app.getSession();
			TCComponentDatasetType tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("Dataset");
			TCComponentDataset predataset=tccomponentDatasetType.find(s4CSQXBGRevisionUI.filelabel[i].getText());
			if(predataset==null)
			try{

			//	AbstractAIFApplication app= AIFUtility.getCurrentApplication();
			//  TCComponentItemRevision mItemRevision=(TCComponentItemRevision)app.getTargetContext().getParentComponent();
			//  TCSession session=(TCSession)app.getSession();
			//	TCComponentDatasetType tccomponentDatasetType=null;
				if(s4CSQXBGRevisionUI.filelabel[i].getText().endsWith(".doc"))
		        tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("MSWord");
				else	if(s4CSQXBGRevisionUI.filelabel[i].getText().endsWith(".docx"))
				tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("MSWordX");	
				
				else	if(s4CSQXBGRevisionUI.filelabel[i].getText().endsWith(".xls"))
			        tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("MSExcel");
				else	if(s4CSQXBGRevisionUI.filelabel[i].getText().endsWith(".xlsx"))
			        tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("MSExcelX");
				
				else	if(s4CSQXBGRevisionUI.filelabel[i].getPath().toString().endsWith(".txt"))
			        tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("Text");
				else	
			        tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("Text");
			//	NewDatasetCommand datasetCmd=new NewDatasetCommand(session,s4CSQXBGRevisionUI.filelabel[i].getText(),"",s4CSQXBGRevisionUI.filelabel[i].getText(),"",null,tccomponentDatasetType.toString(),null);
			//	NewDatasetOperation datasetOp=new NewDatasetOperation(datasetCmd);
			//	datasetOp.executeOperation();
			//	TCComponentDataset dataset =(TCComponentDataset) datasetOp.getNewDataset();	
				
		   TCComponentDataset dataset =tccomponentDatasetType.create(s4CSQXBGRevisionUI.filelabel[i].getText(), tccomponentDatasetType.toString(),  tccomponentDatasetType.toString());
           ImportFilesOperation fileOp=null;
            if(s4CSQXBGRevisionUI.filelabel[i].getText().endsWith(".doc"))
        	    fileOp=new ImportFilesOperation(dataset,s4CSQXBGRevisionUI.filelabel[i].getPath(),"MSWord","BINARY","word",null); 
            else   if(s4CSQXBGRevisionUI.filelabel[i].getText().endsWith(".docx"))
        	    fileOp=new ImportFilesOperation(dataset,s4CSQXBGRevisionUI.filelabel[i].getPath(),"MSWordX","BINARY","word",null);  
            
            else	if(s4CSQXBGRevisionUI.filelabel[i].getText().endsWith(".xls"))
				fileOp=new ImportFilesOperation(dataset,s4CSQXBGRevisionUI.filelabel[i].getPath(),"MSExcel","BINARY","excel",null); 
            else	if(s4CSQXBGRevisionUI.filelabel[i].getText().endsWith(".xlsx"))
				fileOp=new ImportFilesOperation(dataset,s4CSQXBGRevisionUI.filelabel[i].getPath(),"MSExcelX","BINARY","excel",null);  
            else	if(s4CSQXBGRevisionUI.filelabel[i].getText().endsWith(".txt"))
				fileOp=new ImportFilesOperation(dataset,s4CSQXBGRevisionUI.filelabel[i].getPath(),"Text","BINARY","Text",null);  
            else	
	        	    fileOp=new ImportFilesOperation(dataset,s4CSQXBGRevisionUI.filelabel[i].getPath(),"Text","BINARY","Text",null); 

		fileOp.executeOperation();
     
    //    mItemRevision.add("IMAN_specification", dataset);
        
			}catch(Exception e){
				e.printStackTrace();
			}

		} 
			
	}catch(Exception e){
	e.printStackTrace();	
	}	
	}
	TCComponentItemRevision   ir;
	/*
	 * ��ȡ��ǰ�����״̬
	 * */
	TCComponent   currentTask;
	String  node_type;
	String node_user;
	Vector<String>  nodeUser=new Vector();
	Vector<String>  doTaskNameVector=new Vector();
	
	boolean inProcess=false;
	
	/*
	@SuppressWarnings({ "unchecked", "unchecked" })
	public  String  getCurrentStates() {
		try {
		    //node.removeAllElements();			
			nodeUser.removeAllElements();											                      //��仰��ʲô��˼   ,nodeUser��ʲô�������Ͱ�
			AIFComponentContext[] aif = form.getPrimary();                                                   //�õ�form���ϲ����ö���
			if (aif != null && aif.length > 0) {
				for (int i = 0; i < aif.length; i++) {
					String type = aif[0].getComponent().getClass().toString();                                 //��䣿��
					System.out.println(type);
					System.out.println("���Ե���һ��");
					if (type.indexOf("TCComponentItemRevision") > 0) {                                      //���Ͳ�֪��ʲô��˼��
						ir = (TCComponentItemRevision) aif[0].getComponent();                                 // �����ir��TCComponent��
						String processRW = ir.getProperty("current_job");
						  System.out.println("---------------------------1---------------");
						  System.out.println("processRW="+processRW);                                           //�����processRWΪ��
						if (processRW != null && !"".equals(processRW)) 
						{                                     	//�߲�������                 
							inProcess = true;                          				                      		//�����inProcess������
                           System.out.println("---------------------------11---------------");
							TCComponentProcess tcp = ir.getCurrentJob();                               			//�ⲽ����
							String auditInfo = tcp.getAuditInfo(false);
							int l1 = auditInfo.split("��ʼ").length;                                               //���
							String newstr = auditInfo.split("��ʼ")[l1 - 1].split("-")[0];
							int l = newstr.length();
							System.out.println("---------------------------2---------------");
							String node_name = newstr.substring(0, l - 5).trim();                                    //node_name��Srting������
							TCComponentTask[] subTask = tcp.getRootTask().getSubtasks();
							for (int j = 0; j < subTask.length; j++) 
							{
								String subTaskType = subTask[j].getTaskType();
								String subTaskName = subTask[j].getName();
								System.out.println("---------------------------3--------------");
								//if (node_name.equals("���")) {
								System.out.println(subTaskName);
								if (node_name.equals("ȱ���޸�")) 
								{
									System.out.println("---------------------------4---------------");
									
									//node_user="admin";
									currentTask = subTask[j];
									node_type = subTaskType;
									if ("EPMDoTask".equals(subTaskType)) 
									{
										node_user = subTask[j].getResponsibleParty().toString().split("\\(")[0].trim();
										nodeUser.add(node_user);
										doTaskNameVector.add(node_name);                         			//�����doTaskNameVector��ʲô
									} 
									else 
									{
										TCComponentSignoff[] childs=subTask[j].getUserSignoffs();
										TCComponentSignoff tcsignoff=(TCComponentSignoff) childs[0];
										node_user=tcsignoff.getProperty("signoff_member");   				//signoff_member��ʲô��
										TCComponent[] valid_signoffs=subTask[j].getRelatedComponents("valid_signoffs");
										for(int v=0;v<valid_signoffs.length;v++)
										{
											nodeUser.add(valid_signoffs[v].toString().split("\\(")[0].trim());
										}
										System.out.print(nodeUser.toString());
									}
									System.out.println("node_user==>"+node_user);
								}
							}
						}
					}
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return node_user;		
	}
	
	public void getCurrentName() {
		try {
			nodeUser.removeAllElements();
			AIFComponentContext[] aif = form.getPrimary();
			if (aif != null && aif.length > 0) {
				for (int i = 0; i < aif.length; i++) {
					String type = aif[0].getComponent().getClass().toString();
					
					System.out.println("type = "+type);

					if (type.indexOf("TCComponentItemRevision") > 0) {
						ir = (TCComponentItemRevision) aif[0].getComponent();
						String processRW = ir.getProperty("current_job");
						
						System.out.println("processRW = "+processRW);

						
						if (processRW != null && !"".equals(processRW)) {
							inProcess = true;

							TCComponentProcess tcp = ir.getCurrentJob();
							String auditInfo = tcp.getAuditInfo(false);
							
							System.out.println("auditInfo = "+auditInfo);

							
							int l1 = auditInfo.split("��ʼ").length;
							String newstr = auditInfo.split("��ʼ")[l1 - 1].split("-")[0];
							
							System.out.println("newstr = "+newstr);

							
							int l = newstr.length();

							node_name = newstr.substring(0, l - 5).trim();
							
							System.out.println("node_name = "+node_name);

							TCComponentTask[] subTask = tcp.getRootTask().getSubtasks();
							for (int j = 0; j < subTask.length; j++) {
								String subTaskType = subTask[j].getTaskType();
								
								System.out.println("subTaskType = "+subTaskType);

								String subTaskName = subTask[j].getName();
								
								System.out.println("subTaskName = "+subTaskName);

								
								if (node_name.equals(subTaskName)) {
									currentTask = subTask[j];
									
									System.out.println("currentTask = "+currentTask);

									node_type = subTaskType;
									
									System.out.println("node_type = "+node_type);
	
									System.out.println("node_name = "+node_name);
									
									current_node_name = node_name;
									
									if ("EPMDoTask".equals(subTaskType)) {
										node_user = subTask[j].getResponsibleParty().toString().split("\\(")[0].trim();
										nodeUser.add(node_user);
										
										System.out.println("node_user = "+node_user);
										
										doTaskNameVector.add(node_name);
									} else {
										TCComponentSignoff[] childs=subTask[j].getUserSignoffs();
										TCComponentSignoff tcsignoff=(TCComponentSignoff) childs[0];
										node_user=tcsignoff.getProperty("signoff_member");
										
										System.out.println("node_user = "+node_user);
										
										TCComponent[] valid_signoffs=subTask[j].getRelatedComponents("valid_signoffs");
										for(int v=0;v<valid_signoffs.length;v++){
											nodeUser.add(valid_signoffs[v].toString().split("\\(")[0].trim());
										}
									}
								}
							}
						
						}
					
					}
				
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print("nj");

	}
	
	*/
public  String  getCurrentStates() {
		
		
		String    node_user1=null;
		try {
		    //node.removeAllElements();			
			nodeUser.removeAllElements();											                      //��仰��ʲô��˼   ,nodeUser��ʲô�������Ͱ�
			AIFComponentContext[] aif = form.getPrimary();                                                   //�õ�form���ϲ����ö���
			System.out.println("aif="+aif);
			if (aif != null && aif.length > 0) {
				for (int i = 0; i < aif.length; i++) {
					String type = aif[0].getComponent().getClass().toString();                                 //��䣿��
					//System.out.println(type);
					//System.out.println("���Ե���һ��");
					System.out.println("aif[0].getComponent().getClass().toString="+aif[0].getComponent().getClass().toString());
					if (type.indexOf("TCComponentItemRevision") > 0) {                                      //���Ͳ�֪��ʲô��˼��
						ir = (TCComponentItemRevision) aif[0].getComponent();                                 // �����ir��TCComponent��
						System.out.println("ir="+ir);
						String processRW = ir.getProperty("current_job");
						  System.out.println("processRW="+processRW);                                           //�����processRWΪ��
						if (processRW != null && !"".equals(processRW)) 
						{                                     	//�߲�������                 
							inProcess = true;                          				                      		//�����inProcess������                 
							TCComponentProcess tcp = ir.getCurrentJob();                               			//Ӧ����������õ��������̵�����
							//System.out.println("tcp="+ir.getCurrentJob());
							
							String auditInfo = tcp.getAuditInfo(false);                    
							System.out.println("auditinfo="+auditInfo);
							int l1 = auditInfo.split("��ʼ").length;                                               //���
							String newstr = auditInfo.split("��ʼ")[l1 - 1].split("-")[0];
							
						System.out.println("String newstr="+newstr);
							int l = newstr.length();
							//System.out.println("---------------------------2---------------");
							String node_name = newstr.substring(0, l - 5).trim();                                    //node_name��Srting������
							TCComponentTask[] subTask = tcp.getRootTask().getSubtasks();
							for (int j = 0; j < subTask.length; j++) 
							{
								String subTaskType = subTask[j].getTaskType();
								String subTaskName = subTask[j].getName();
								//System.out.println("subTaskName==>"+subTaskName);
								if (node_name.equals("ȱ���޸�")) 
								{
									currentTask = subTask[j];
									node_type = subTaskType;
									System.out.println("node_type"+node_type);
									if(subTaskName.equals("ȱ���޸�"))
									{
										System.out.println("-----2------");
										TCComponentSignoff[] childs=subTask[j].getUserSignoffs();
										TCComponentSignoff tcsignoff=(TCComponentSignoff) childs[0];
										node_user=tcsignoff.getProperty("signoff_member");   				//signoff_member��ʲô��
										TCComponent[] valid_signoffs=subTask[j].getRelatedComponents("valid_signoffs");
										for(int v=0;v<valid_signoffs.length;v++)
										{
											nodeUser.add(valid_signoffs[v].toString().split("\\(")[0].trim());
											System.out.println("nodeUser==>"+nodeUser);
											System.out.println("valid_signoffs[v].toString()==>"+valid_signoffs[v].toString());
											node_user1=valid_signoffs[v].toString();
										}
									}
									
							}
						}
					}
				}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("String  getCurrentStates()ִ�н���");
		System.out.println("noder_user=="+node_user);
		
		return node_user1;		
	}
	
	String  node_name1; 
	
	public void getCurrentName() {
		try {
			nodeUser.removeAllElements();
			AIFComponentContext[] aif = form.getPrimary();
			if (aif != null && aif.length > 0) {
				for (int i = 0; i < aif.length; i++) {
					String type = aif[0].getComponent().getClass().toString();
					if (type.indexOf("TCComponentItemRevision") > 0) {
						ir = (TCComponentItemRevision) aif[0].getComponent();
						String processRW = ir.getProperty("current_job");
						if (processRW != null && !"".equals(processRW)) {
							inProcess = true;
							TCComponentProcess tcp = ir.getCurrentJob();
							String auditInfo = tcp.getAuditInfo(false);	
							int l1 = auditInfo.split("��ʼ").length;
							String newstr = auditInfo.split("��ʼ")[l1 - 1].split("-")[0];
							int l = newstr.length();
							node_name1 = newstr.substring(0, l - 5).trim();
							TCComponentTask[] subTask = tcp.getRootTask().getSubtasks();
							for (int j = 0; j < subTask.length; j++) {
								String subTaskType = subTask[j].getTaskType();									    
							     String subTaskName = subTask[j].getName();												
								if (node_name1.equals(subTaskName)) {
									currentTask = subTask[j];		
									node_type = subTaskType;											
									current_node_name = node_name1;				
								}
							}
						
						}
					
					}
				
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	//	System.out.print("nj");

	}
	
	private String[] getTCPreferenceArray(TCSession tcSession, String preferenceName) {
		
		String[] preString = null;
	
		TCPreferenceService tcPreservice = tcSession.getPreferenceService();
		
		preString = tcPreservice.getStringArray(TCPreferenceService.TC_preference_site, preferenceName);

		return preString;
	}
	
}
