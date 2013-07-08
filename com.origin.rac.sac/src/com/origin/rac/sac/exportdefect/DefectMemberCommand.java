package com.origin.rac.sac.exportdefect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import cn.com.origin.util.ProgressBarThread;

import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.common.Activator;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCFormProperty;
import com.teamcenter.rac.kernel.TCProperty;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class DefectMemberCommand extends AbstractAIFCommand{
	
	private TCSession session = null;
	public TCComponent target = null;
	private String releation = "IMAN_reference";
	private TCComponentFolder folder ;
	private String releation1 = "IMAN_master_form";
	private String releation2 = "IMAN_master_form_rev";
	private String formType ="S4CSQXBGRevisionMaster";
	private String formType1="S4YFprojXJMaster";
	private String folder_name = "ȱ�ݱ���";
	private TCFormProperty ddformProperties[] = null;
	private TCProperty[] formProperties = null;
	//ȱ�ݱ����������
	private String 	 defectid=null;		//ȱ�ݱ��
	private String	 reporter=null;		//������
	private Date   	 reportdate=null;   //��������
	private String   reportdate1=null;  //string�͵ı�������
	private String   assets=null;       //������Ʒ
	private String   testobj=null;      //�������
	private String   nowrevision=null;  //��ǰ�汾��
	private String   state=null;		//״̬
	private String   defectdescrip=null;//ȱ������
	private String   defectclass=null;  //ȱ�ݷ���
	private String   severerank=null;   //���صȼ�
	private String   dorank=null;       //�������ȼ�
	private String   mendsuggest=null;  //�޸�����
	private String   solverevision=null;//�����汾��
	private String   solveperson=null;  //�����Ա
	private String   changerecord=null;  //���ļ�¼
	private String   explanation=null;  //ȱ���޸�ע��
	private String   mendexplanation=null; //�ع����ע��
	private String   addition="";     //����
	private String   addition1[]=null;   //����������
	private int   count=0;              //�����ĸ���
	private static String exportexcel=null;
	private String exportexcel1=null;
	private String filename1=null;
	//private static File file=null;
	private boolean flag=false;
	private boolean flag1=false;
	//private WritableSheet ws=null;
	private int h=1;
	private ProgressBarThread progressBarThread = null;
	private String text_content="ȱ�ݻ��ܱ�";
	

	public DefectMemberCommand(AbstractAIFUIApplication application) throws TCException {
		// TODO Auto-generated constructor stub
		
		target = (TCComponent) application.getTargetComponent();
		if (target == null || !(target instanceof TCComponentItem)) {
			MessageBox.post("��ѡ����������������!", "��ʾ", MessageBox.INFORMATION);
			return;
		}else{
			
					TCComponentItem item = (TCComponentItem) target;
					TCComponentForm master_form = (TCComponentForm) item.getRelatedComponent(releation1);
					System.out.println("master_form==>"+master_form.getType());
					if(formType1.equals(master_form.getType()))
					{
						session = (TCSession) application.getSession();
						System.out.println("��ʱ��targetΪ"+target);
						TCComponent[] coms = item.getRelatedComponents(releation);
						System.out.println("coms--->:"+coms.length);		
						WritableFont titleFont = new WritableFont(WritableFont
							.createFont("��Բ"), 26, WritableFont.BOLD);
						WritableFont contentFont = new WritableFont(WritableFont
							.createFont("����"), 16, WritableFont.BOLD);   
						WritableFont contentFont1=new WritableFont(WritableFont
							.createFont("���� _GB2312"),12,WritableFont.NO_BOLD);
        
						WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
						WritableCellFormat contentFormat = new WritableCellFormat(
							contentFont);                 
						WritableCellFormat contentFormat2 = new WritableCellFormat(
							contentFont1);             //��������ֵ���д��ʽ       
						
						try{
							//ExcelHandle excelHandle = new ExcelHandle();	
							//exportexcel();
							//contentFormat2.setAlignment(jxl.format.Alignment.CENTRE);	
							contentFormat2.setBorder(Border.ALL, BorderLineStyle.THIN,
				                    Colour.BLACK);
							if(coms!=null && coms.length>0){
								System.out.println("123");
								for (int i = 0; i < coms.length; i++) {	
									folder = (TCComponentFolder) coms[i];
									TCComponent[] folders=folder.getRelatedComponents("contents");
									for(int j=0;j<folders.length;j++){
										//System.out.println()
										TCComponentItem item1 = (TCComponentItem) folders[j];
										TCComponentItemRevision item_rev = item1.getLatestItemRevision();
										TCComponentForm tccomponentForm =(TCComponentForm)item_rev.getRelatedComponent(releation2);
										if(formType.equals(tccomponentForm.getType())){
											flag1=true;
									}
								}
							}
							if(flag1==true){
								System.out.println("flag1==>"+flag1);
								JFileChooser fc=new JFileChooser();
								fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);			//�˾���Ϊ��Ҫ��������JFileChooserѡ���ļ�������
								fc.setDialogTitle("����excel");
								final JTextField text;
								text=getTextField(fc);
								text.setText("ȱ�ݻ��ܱ�");
								
								text.addFocusListener(new FocusListener(){

									@Override
									public void focusGained(FocusEvent e) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void focusLost(FocusEvent e) {
										// TODO Auto-generated method stub
										text_content=text.getText();
									}
									
									
									
								});
								fc.addPropertyChangeListener(new PropertyChangeListener(){
									public void propertyChange(PropertyChangeEvent event)
									{
										
										text.setText(text_content);
									}

									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										System.out.println("456");
									}
								});
								//fc.setSelectedFile(new File("ȱ�ݻ��ܱ�"));
								int intRetVal=fc.showSaveDialog(null);
						
								if(intRetVal==JFileChooser.APPROVE_OPTION){
									exportexcel=fc.getSelectedFile().getPath();
									//fc.setName("ȱ��huizong");
									//fc.removeActionListener();
									//fc.setFileFilter(new FileNameExtensionFilter("xls",saveType));
									System.out.println("exportexcel==>"+exportexcel);
							//		File file=new File(exportexcel+"/ȱ�ݻ��ܱ�.xls");
									
									File file=new File(fc.getSelectedFile().getParent(),fc.getSelectedFile().getName()+".xls");
									//fc.setSelectedFile(new File("ȱ�ݻ��ܱ�"));
									WritableWorkbook wwb=null;
									if(!file.exists()){
										wwb = Workbook
										.createWorkbook(file);
										// ����Excel������ ָ�����ƺ�λ��
										System.out.println("file==>"+file);        
										WritableSheet ws = wwb.createSheet("Sheet1", 0);
										writeExcelfirst(file,ws);
										flag=true;
										System.out.println("�ļ�������");	
										System.out.println("��ʱ�� coms.length==>"+coms.length);
										progressBarThread = new ProgressBarThread("��ʾ", "�ĵ����ڵ��������Ժ�...");
										progressBarThread.start();
										for (int i = 0; i < coms.length; i++) {				
											folder = (TCComponentFolder) coms[i];	
											TCComponent[] folders=folder.getRelatedComponents("contents");
											for(int j=0;j<folders.length;j++){					
												System.out.println("folders==>"+folders[j].toString());
												TCComponentItem item1 = (TCComponentItem) folders[j];
												TCComponentItemRevision item_rev = item1.getLatestItemRevision();
												System.out.println("TCComponentItemRevision=="+item_rev.toString());
												TCComponentForm tccomponentForm =(TCComponentForm)item_rev.getRelatedComponent(releation2); //����������Ǳ������б�ܵĶ�����relation2���������ֵ
												System.out.println("tccomponentForm==>"+tccomponentForm.toString());
												if(formType.equals(tccomponentForm.getType())){                           
													Vector<String> v_all=new Vector<String>();
													System.out.println(tccomponentForm.getTCProperty("s4reporter"));
													String str1= tccomponentForm.getTCProperty("s4reporter").getStringValue();
													reporter =  tccomponentForm.getProperty("s4reporter");     //��������
													if(tccomponentForm.getTCProperty("s4repdate1").getDateValue()==null)         //��ʱ��ĸ�ֵ
														//DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
														//strRepDate=df.format(new Date());
														reportdate=new Date();
													else
													{
														
														reportdate = tccomponentForm.getTCProperty("s4repdate1").getDateValue();
														System.out.println("reportdate==>"+reportdate.toString());
													}
													reportdate1=new SimpleDateFormat("yyyy/MM/dd").format(reportdate);
													System.out.println("reportdate1"+reportdate1);				//�������
													assets=tccomponentForm.getProperty("s4Assets").toString();
													testobj = tccomponentForm.getProperty("s4measurand");					//�������																	//��ǰ�汾��
													nowrevision = tccomponentForm.getProperty("s4nowversion");
													System.out.println("nowrevision"+nowrevision);							      
													defectdescrip = tccomponentForm.getProperty("s4Description");                  //ȱ������
													System.out.println("defectdescrip"+defectdescrip);																									
													defectclass =tccomponentForm.getProperty("s4DefectClass");				//ȱ�ݷ���
													System.out.println("defectclass"+defectclass);							       
													severerank = tccomponentForm.getProperty("s4Severlevel");			//���صȼ�
													System.out.println("severerank"+severerank);						
													dorank =tccomponentForm.getProperty("s4proprior");  			//�������ȼ�
													System.out.println("dorank"+dorank);
													mendsuggest = tccomponentForm.getProperty("s4suggestrepa"); 					//�޸�����
													System.out.println("mendsuggest"+mendsuggest);							            
													state = tccomponentForm.getProperty("s4statuss");           //״̬
													System.out.println("state"+state);							        
													solverevision = tccomponentForm.getProperty("s4solvversion");  //�����İ汾��
													System.out.println("solverevision"+solverevision);							         
													solveperson =tccomponentForm.getProperty("s4Resolvingpro");       //�����Ա
													System.out.println("solveperson"+solveperson);							      	 
													changerecord = tccomponentForm.getProperty("s4ChangeRe"); //���ļ�¼
													System.out.println("changerecord"+changerecord);							        	
													explanation = tccomponentForm.getProperty("s4exegesis"); //ȱ���޸�ע��
													System.out.println("explanation"+explanation);							            
													mendexplanation = tccomponentForm.getProperty("s4exegesis1"); //�ع����ע��
													System.out.println("mendexplanation"+mendexplanation);							        
													count=tccomponentForm.getTCProperty("s4acc").getStringValueArray().length;; //����
													addition1=tccomponentForm.getTCProperty("s4acc").getStringValueArray();
													for(int m=0;m<count;m++){
														addition=addition+addition1[m]+"; ";
													}
													System.out.println("addition"+addition);
													System.out.println("flag==>"+flag);
													/*if(flag=true){
														exportexcelsecond();
													}*/
													//exportexcel(reporter,reportdate,testobj,nowrevision,defectdescrip,defectclass,severerank,dorank,mendsuggest,state,solverevision,changerecord,explanation,mendexplanation,addition);
													h++;
													ws.addCell(new Label(0,h,tccomponentForm.toString(),contentFormat2));  //ȱ�ݱ��
													System.out.println("��ʱ��tccomponentForm.toString"+tccomponentForm.toString());
													ws.addCell(new Label(1,h,reporter,contentFormat2));    //������
													System.out.println("��ʱ��reporter==>"+reporter);
													ws.addCell(new Label(2,h,reportdate1,contentFormat2));  //��������
													ws.addCell(new Label(3,h,assets,contentFormat2));      //������Ʒ
													ws.addCell(new Label(4,h,testobj,contentFormat2));     //�������
													ws.addCell(new Label(5,h,nowrevision,contentFormat2)); //��ǰ�汾��
													ws.addCell(new Label(6,h,state,contentFormat2));       //״̬
													ws.addCell(new Label(7,h,defectdescrip,contentFormat2)); //ȱ������
													ws.addCell(new Label(8,h,defectclass,contentFormat2));   //ȱ�ݷ���
													ws.addCell(new Label(9,h,severerank,contentFormat2));    //���صȼ�
													ws.addCell(new Label(10,h,dorank,contentFormat2));       //�������ȼ�
													ws.addCell(new Label(11,h,mendsuggest,contentFormat2));   //�޸�����
													ws.addCell(new Label(12,h,solverevision,contentFormat2));   //�����İ汾��
													ws.addCell(new Label(13,h,solveperson,contentFormat2));     //�����Ա
													ws.addCell(new Label(14,h,changerecord,contentFormat2));    //���ļ�¼
													ws.addCell(new Label(15,h,explanation,contentFormat2));    //ȱ���޸�ע��
													ws.addCell(new Label(16,h,mendexplanation,contentFormat2));    //�ع����ע��
													ws.addCell(new Label(17,h,addition,contentFormat2));      //����
							            	
													System.out.println("��ʱ��h==>"+h);
													addition="";
										
												}
											
											
											
											
										}

									}
								
								
										wwb.write();									
										wwb.close();
										progressBarThread.stopBar();
										MessageBox.post("�ĵ������ɹ�!", "��ʾ", MessageBox.INFORMATION);
								//	wwb.write();	
								//	MessageBox.post("�ĵ������ɹ�!", "��ʾ", MessageBox.INFORMATION);
								}
								else{
									int  n=JOptionPane.showConfirmDialog(null, "�ļ��Ѵ��ڣ�ȷ�ϸ�����","��ʾ",JOptionPane.YES_NO_OPTION);
									if(n==JOptionPane.YES_OPTION){
										//JOptionPane.
										flag=true;
										progressBarThread = new ProgressBarThread("��ʾ", "�ĵ����ڵ��������Ժ�...");
										progressBarThread.start();
										//	writeExcelfirst(file,ws);
										//	writeExcelsecond(file,ws);
										System.out.println("�ļ��Ѵ���");
										wwb = Workbook
											.createWorkbook(file);
										// ����Excel������ ָ�����ƺ�λ��
										System.out.println("file==>"+file);        
										WritableSheet ws = wwb.createSheet("Sheet1", 0);
										writeExcelfirst(file,ws);
										flag=true;
										System.out.println("�ļ�������");															
										for (int i = 0; i < coms.length; i++) {				
											folder = (TCComponentFolder) coms[i];	
											TCComponent[] folders=folder.getRelatedComponents("contents");
											for(int j=0;j<folders.length;j++){					
												System.out.println("folders==>"+folders[j].toString());
												TCComponentItem item1 = (TCComponentItem) folders[j];
												TCComponentItemRevision item_rev = item1.getLatestItemRevision();
												System.out.println("TCComponentItemRevision=="+item_rev.toString());
												TCComponentForm tccomponentForm =(TCComponentForm)item_rev.getRelatedComponent(releation2); //����������Ǳ������б�ܵĶ�����relation2���������ֵ
												System.out.println("tccomponentForm==>"+tccomponentForm.toString());
												if(formType.equals(tccomponentForm.getType())){
													Vector<String> v_all=new Vector<String>();
													System.out.println(tccomponentForm.getTCProperty("s4reporter"));
													String str1= tccomponentForm.getTCProperty("s4reporter").getStringValue();
													reporter =  tccomponentForm.getProperty("s4reporter");     //��������	
													if(tccomponentForm.getTCProperty("s4repdate1").getDateValue()==null)         //��ʱ��ĸ�ֵ
														//DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
														//strRepDate=df.format(new Date());
														reportdate=new Date();
													else
													{
														
														reportdate = tccomponentForm.getTCProperty("s4repdate1").getDateValue();
														System.out.println("reportdate==>"+reportdate.toString());
													}												
													//reportdate = tccomponentForm.getTCProperty("s4repdate1").getDateValue();
													reportdate1=new SimpleDateFormat("yyyy/MM/dd").format(reportdate);
													System.out.println("reportdate1"+reportdate1);				//�������
													assets=tccomponentForm.getProperty("s4Assets").toString();
													testobj = tccomponentForm.getProperty("s4measurand");					//�������																	//��ǰ�汾��
													nowrevision = tccomponentForm.getProperty("s4nowversion");
													System.out.println("nowrevision"+nowrevision);							      
													defectdescrip = tccomponentForm.getProperty("s4Description");                  //ȱ������
													System.out.println("defectdescrip"+defectdescrip);																									
													defectclass =tccomponentForm.getProperty("s4DefectClass");				//ȱ�ݷ���
													System.out.println("defectclass"+defectclass);							       
													severerank = tccomponentForm.getProperty("s4Severlevel");			//���صȼ�
													System.out.println("severerank"+severerank);						
													dorank =tccomponentForm.getProperty("s4proprior");  			//�������ȼ�
													System.out.println("dorank"+dorank);
													mendsuggest = tccomponentForm.getProperty("s4suggestrepa"); 					//�޸�����
													System.out.println("mendsuggest"+mendsuggest);							            
													state = tccomponentForm.getProperty("s4statuss");           //״̬
													System.out.println("state"+state);							        
													solverevision = tccomponentForm.getProperty("s4solvversion");  //�����İ汾��
													System.out.println("solverevision"+solverevision);							         
													solveperson =tccomponentForm.getProperty("s4Resolvingpro");       //�����Ա
													System.out.println("solveperson"+solveperson);							      	 
													changerecord = tccomponentForm.getProperty("s4ChangeRe"); //���ļ�¼
													System.out.println("changerecord"+changerecord);							        	
													explanation = tccomponentForm.getProperty("s4exegesis"); //ȱ���޸�ע��
													System.out.println("explanation"+explanation);							            
													mendexplanation = tccomponentForm.getProperty("s4exegesis1"); //�ع����ע��
													System.out.println("mendexplanation"+mendexplanation);							        
													count=tccomponentForm.getTCProperty("s4acc").getStringValueArray().length;; //����
													addition1=tccomponentForm.getTCProperty("s4acc").getStringValueArray();
													for(int m=0;m<count;m++){
														addition=addition+addition1[m]+"; ";
													}
													System.out.println("addition"+addition);
													System.out.println("flag==>"+flag);
													/*if(flag=true){
														exportexcelsecond();
													}*/
										//exportexcel(reporter,reportdate,testobj,nowrevision,defectdescrip,defectclass,severerank,dorank,mendsuggest,state,solverevision,changerecord,explanation,mendexplanation,addition);
													h++;
													ws.addCell(new Label(0,h,tccomponentForm.toString(),contentFormat2));  //ȱ�ݱ��
													System.out.println("��ʱ��tccomponentForm.toString"+tccomponentForm.toString());
									            	ws.addCell(new Label(1,h,reporter,contentFormat2));    //������
									            	System.out.println("��ʱ��reporter==>"+reporter);
									            	ws.addCell(new Label(2,h,reportdate1,contentFormat2));  //��������
									            	ws.addCell(new Label(3,h,assets,contentFormat2));      //������Ʒ
									            	ws.addCell(new Label(4,h,testobj,contentFormat2));     //�������
									            	ws.addCell(new Label(5,h,nowrevision,contentFormat2)); //��ǰ�汾��
									            	ws.addCell(new Label(6,h,state,contentFormat2));       //״̬
									            	ws.addCell(new Label(7,h,defectdescrip,contentFormat2)); //ȱ������
									            	ws.addCell(new Label(8,h,defectclass,contentFormat2));   //ȱ�ݷ���
									            	ws.addCell(new Label(9,h,severerank,contentFormat2));    //���صȼ�
									            	ws.addCell(new Label(10,h,dorank,contentFormat2));       //�������ȼ�
									            	ws.addCell(new Label(11,h,mendsuggest,contentFormat2));   //�޸�����
									            	ws.addCell(new Label(12,h,solverevision,contentFormat2));   //�����İ汾��
									            	ws.addCell(new Label(13,h,solveperson,contentFormat2));     //�����Ա
									            	ws.addCell(new Label(14,h,changerecord,contentFormat2));    //���ļ�¼
									            	ws.addCell(new Label(15,h,explanation,contentFormat2));    //ȱ���޸�ע��
									            	ws.addCell(new Label(16,h,mendexplanation,contentFormat2));    //�ع����ע��
									            	ws.addCell(new Label(17,h,addition,contentFormat2));      //����
									            	
									            	System.out.println("��ʱ��h==>"+h);
									            	addition="";
												
												}
											}

										}																				
										/*wwb.write();
										MessageBox.post("�ĵ������ɹ�!", "��ʾ", MessageBox.INFORMATION);*/
										wwb.write();									
										wwb.close();
										progressBarThread.stopBar();
										MessageBox.post("�ĵ������ɹ�!", "��ʾ", MessageBox.INFORMATION);
									
								}
								else if(n==JOptionPane.NO_OPTION)
								{
								     flag=false;
								     System.out.println("��ѡ���˲����ǣ�");
								}
						 }
								/*	wwb.write();									
									wwb.close();
									MessageBox.post("�ĵ������ɹ�!", "��ʾ", MessageBox.INFORMATION);*/
					}		    //��һ�����Ŷ�Ӧ��ʲô		
				}
					else{
							MessageBox.post("�Ҳ���ȱ�ݱ������!", "��ʾ", MessageBox.INFORMATION);
						}
							
							
							
			}
		else{
			MessageBox.post("�Ҳ�����Ӧ����,����!", "��ʾ", MessageBox.INFORMATION);
			}						
				
		}catch(Exception e){
			e.printStackTrace();
			MessageBox.post("�Ҳ�����Ӧ����,����!", "��ʾ", MessageBox.INFORMATION);
		}
		}else{
			MessageBox.post("��ѡ���з���Ŀ��,����!", "��ʾ", MessageBox.INFORMATION);		
			}
		}
		
	}
		
    private JTextField getTextField(Container c) {
		JTextField textfield=null;
		for(int i=0;i<c.getComponentCount();i++){
			Component cnt=c.getComponent(i);
			if(cnt instanceof JTextField){
				return (JTextField)cnt;
			}
			if(cnt instanceof Container){
				textfield=getTextField((Container)cnt);
				if(textfield!=null){
					return textfield;
				}
			}
		}
		return textfield;
	}

	public void writeExcelfirst(File file, WritableSheet ws) {
        try {   
            ws.setColumnView(0, 30);//��һ�п�14   ȱ�ݱ��
            ws.setColumnView(1, 12);   //������
            ws.setColumnView(2, 25);   //��������
            ws.setColumnView(3, 20);   //������Ʒ
            ws.setColumnView(4, 20);   //�������
            ws.setColumnView(5, 20);   //��ǰ�汾��
            ws.setColumnView(6, 20);   //״̬
            ws.setColumnView(7, 20);   //ȱ������
            ws.setColumnView(8, 26);   //ȱ�ݷ���
            ws.setColumnView(9, 20);   //���صȼ�
            ws.setColumnView(10,20);   //�������ȼ�
            ws.setColumnView(11,20);   //�޸�����
            ws.setColumnView(12, 30);  //�����汾��
            ws.setColumnView(13, 60);   //�����
            ws.setColumnView(14, 20);  //���ļ�¼
            ws.setColumnView(15, 30);  //ȱ���޸�ע��
            ws.setColumnView(16, 30); //�ع����ע��
            ws.setColumnView(17, 45);  //����
            

// **************�����������������*****************

//���������ʽ������Ϊ����Բ��26�ţ��Ӵ�
            WritableFont titleFont = new WritableFont(WritableFont
                    .createFont("��Բ"), 26, WritableFont.BOLD);
            WritableFont contentFont = new WritableFont(WritableFont
                    .createFont("����"), 16, WritableFont.BOLD);   
            WritableFont contentFont1=new WritableFont(WritableFont
            		.createFont("���� _GB2312"),12,WritableFont.NO_BOLD);
            
            WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
            WritableCellFormat contentFormat = new WritableCellFormat(
                    contentFont);                 
            WritableCellFormat contentFormat2 = new WritableCellFormat(
                    contentFont1);             //��������ֵ���д��ʽ

            contentFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
                    Colour.BLACK);
            //���ø�ʽ���ж���
            titleFormat.setAlignment(jxl.format.Alignment.CENTRE);
            contentFormat.setAlignment(jxl.format.Alignment.CENTRE);
           // contentFormat.setBackground(jxl.format.Colour.BLUE);
            contentFormat2.setAlignment(jxl.format.Alignment.CENTRE);

            // ***************������õĵ�Ԫ����ӵ���������*****************
            ws.mergeCells(0, 0, 18, 0);// �ϲ���Ԫ��A-G��7��
            ws.addCell(new Label(0, 0, "��Ŀȱ�ݻ��ܱ�", titleFormat));
            ws.addCell(new Label(0, 1, "ȱ�ݱ��", contentFormat));
            ws.addCell(new Label(1,1,"������",contentFormat));
            ws.addCell(new Label(2,1,"��������",contentFormat));
            ws.addCell(new Label(3,1,"������Ʒ",contentFormat));
            ws.addCell(new Label(4,1,"�������",contentFormat));
            ws.addCell(new Label(5,1,"��ǰ�汾��",contentFormat));
            ws.addCell(new Label(6,1,"״̬",contentFormat));
            ws.addCell(new Label(7,1,"ȱ������",contentFormat));
            ws.addCell(new Label(8,1,"ȱ�ݷ���",contentFormat));
            ws.addCell(new Label(9,1,"���صȼ�",contentFormat));
            ws.addCell(new Label(10,1,"�������ȼ�",contentFormat));
            ws.addCell(new Label(11,1,"�޸�����",contentFormat));
            ws.addCell(new Label(12,1,"�����汾��",contentFormat));
            ws.addCell(new Label(13,1,"�����",contentFormat));
            ws.addCell(new Label(14,1,"���ļ�¼",contentFormat));
            ws.addCell(new Label(15,1,"ȱ���޸�ע��",contentFormat));
            ws.addCell(new Label(16,1,"�ع����ע��",contentFormat));
            ws.addCell(new Label(17,1,"����",contentFormat));         
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
