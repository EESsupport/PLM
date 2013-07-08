package com.gdnz.sac1.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;

import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;


public class S4ExportWordCommand extends AbstractAIFCommand {

	private TCSession session = null;
	private TCComponentForm target_form;
	private String  exportPath;
	private JTextField jf;
    private String jfText;
	
	public S4ExportWordCommand(AbstractAIFUIApplication app) throws TCException {
		session = (TCSession) app.getSession();
		InterfaceAIFComponent target = app.getTargetComponent();
		if(target instanceof TCComponentForm){
			target_form=(TCComponentForm)target;
			String formType = target_form.getType();
			if(formType.equals("S4HDKJXMJZYBRevisionMaster")){
				 
				/* 
				    ������Ϊ��Ҫ�ġ���ΪJFileChooserĬ�ϵ���ѡ���ļ�������ҪѡĿ¼�� 
				    ��Ҫ��DIRECTORIES_ONLYװ��ģ�� 
				���⣬��ѡ���ļ���������˾� 
				*/    System.out.println("B");
				      JFileChooser fc = new JFileChooser(); 
				      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
				      
			             fc.setCurrentDirectory(new File("C:/Users/Administrator/Documents"));
			             jf=getTextField(fc);
			             jf.setText("�й����缯�Ź�˾�Ƽ���Ŀ�о���չ�±���");
			             jfText=jf.getText();
			             jf.addFocusListener(new FocusListener(){

							
							@Override
							public void focusGained(FocusEvent e) {
								// TODO Auto-generated method stub
								
							}
							@Override
							public void focusLost(FocusEvent e) {
								// TODO Auto-generated method stub
				            	 jfText=jf.getText();

							}
			            	
			            	 
			             });
			             fc.addPropertyChangeListener(new PropertyChangeListener(){

							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								// TODO Auto-generated method stub
								jf.setText(jfText);	

								
							}
			             });
				      
				      int intRetVal = fc.showSaveDialog(null); 
				      if( intRetVal == JFileChooser.APPROVE_OPTION){ 
				    	  System.out.println("A");
				    	  exportPath = fc.getSelectedFile().getPath();
				    	
				    	  File file = new File(exportPath+".doc");
				    	  if (!file.exists()) {
				    		  S4HDKJXMJZYBRevisionMaster_exportWord(exportPath+".doc");
				    	  }
				    	  
				    	  else {
				    		  int n = JOptionPane.showConfirmDialog(null, "�ļ��Ѵ��ڣ�ȷ�ϸ�����",
				    				  "��ʾ", JOptionPane.YES_NO_OPTION);
				    		  if (n == JOptionPane.YES_OPTION) {
				    			  S4HDKJXMJZYBRevisionMaster_exportWord(exportPath+".doc");
				    		  } 
				    	  }
				      }  
			}
			else if(formType.equals("S4SJGGRevisionMaster")){
				 System.out.println("C");
			      JFileChooser fc = new JFileChooser(); 
			      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			      
			      fc.setCurrentDirectory(new File("C:/Users/Administrator/Documents"));
		             jf=getTextField(fc);
		             jf.setText("��Ƹ��������");
		             jfText=jf.getText();
		             jf.addFocusListener(new FocusListener(){

						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
			            	 jfText=jf.getText();

						}
		            	
		            	 
		             });
		             fc.addPropertyChangeListener(new PropertyChangeListener(){

						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							// TODO Auto-generated method stub
							jf.setText(jfText);	

							
						}
		             });
		             
			      int intRetVal = fc.showSaveDialog(null); 
			      if( intRetVal == JFileChooser.APPROVE_OPTION){ 
			    	  exportPath = fc.getSelectedFile().getPath();
			    	  
			    	  File file = new File(exportPath+".doc");
			    	  if (!file.exists()) {
			    		  S4SJGGRevisionMaster_exportWord(exportPath+".doc");
			    	  }
			    	  else {
			    		  int n = JOptionPane.showConfirmDialog(null, "�ļ��Ѵ��ڣ�ȷ�ϸ�����",
			    				  "��ʾ", JOptionPane.YES_NO_OPTION);
			    		  if (n == JOptionPane.YES_OPTION) {
			    			  S4SJGGRevisionMaster_exportWord(exportPath+".doc");
			    		  } 
			    	  }
			      } 
			
			}

			else if (formType.equals("S4YFprojXJMaster")){

				 System.out.println("D");
			      JFileChooser fc = new JFileChooser(); 
			      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			      
			      fc.setCurrentDirectory(new File("C:/Users/Administrator/Documents"));
		             jf=getTextField(fc);
		             jf.setText(target_form.getTCProperty("s4Prj_name").getStringValue());
		             jfText=jf.getText();
		             jf.addFocusListener(new FocusListener(){

						
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
			            	 jfText=jf.getText();

						}
		            	
		            	 
		             });
		             fc.addPropertyChangeListener(new PropertyChangeListener(){

						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							// TODO Auto-generated method stub
							jf.setText(jfText);	

							
						}
		             });
		             
			      int intRetVal = fc.showSaveDialog(null); 
			      if( intRetVal == JFileChooser.APPROVE_OPTION){
			    	  
			    	  exportPath = fc.getSelectedFile().getPath();
			    	  File file;
					
						file = new File(exportPath+".doc");
					
			    	  if (!file.exists()) {
				    	  new PrjWord(exportPath+".doc",target_form);
			    	  }
			    	  else {
			    		  int n = JOptionPane.showConfirmDialog(null, "�ļ��Ѵ��ڣ�ȷ�ϸ�����",
			    				  "��ʾ", JOptionPane.YES_NO_OPTION);
			    		  if (n == JOptionPane.YES_OPTION) {
					    	  new PrjWord(exportPath+".doc",target_form);
			    		  } 
			    	  }
					
			      }	
			}
			
			MessageBox.post("word�ļ�������ϡ�", "��ʾ", MessageBox.INFORMATION);

		}
	}
	public void S4HDKJXMJZYBRevisionMaster_exportWord(String exPath) { 
		
		System.out.println("AA");
		Document document = new Document(PageSize.A4);

		
		try {  
			RtfWriter2.getInstance(document,
			new FileOutputStream(exPath));  
			document.open();  
 

 
			Paragraph ph = new Paragraph();  
			Font f  = new Font();  
 
			Paragraph p = new Paragraph("�й����缯�Ź�˾�Ƽ���Ŀ�о���չ�±���", 
			new Font(Font.NORMAL, 14, Font.BOLD, new Color(0, 0, 0)) );  
			p.setAlignment(1);  
			document.add(p);  
			ph.setFont(f);  


			
			document.add(new Paragraph(""));  

	
			Table table = new Table(8,10);  

			table.setBorderWidth(1);  
			table.setBorderColor(Color.BLACK);  
			table.setPadding(0);  
			table.setSpacing(0);  
			
							
			Cell cName = new Cell("��������");
			cName.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cName.setColspan(2);
			cName.setUseAscender(true); 
			cName.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cName.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cName); 
			
			Cell cinName;
			cinName = new Cell(target_form.getTCProperty("s4prjName").getStringValue());
			cinName.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cinName.setColspan(6);
			table.addCell(cinName); 
			
			Cell cFirm = new Cell("�е���λ");
			cFirm.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cFirm.setColspan(2);
			cFirm.setUseAscender(true); 
			cFirm.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cFirm.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cFirm); 
			
			Cell cinFirm = new Cell(target_form.getTCProperty("s4chargeFirm").getStringValue());
			cinFirm.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cinFirm.setColspan(2);
			table.addCell(cinFirm); 
			
			Cell cNum = new Cell("������");
			cNum.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cNum.setColspan(2);
			cNum.setUseAscender(true); 
			cNum.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cNum.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cNum); 
			
			Cell cinNum = new Cell(target_form.getTCProperty("s4prjNumber").getStringValue());
			cinNum.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cinNum.setColspan(2);
			table.addCell(cinNum);
			
			Cell cLeader = new Cell("�����鳤");
			cLeader.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cLeader.setColspan(2);
			cLeader.setUseAscender(true); 
			cLeader.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cLeader.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cLeader); 
			
			Cell cinLeader = new Cell(target_form.getTCProperty("s4prjLeader").getStringValue());
			cinLeader.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cinLeader.setColspan(2);
			table.addCell(cinLeader);
			
			Cell cPhone = new Cell("�绰/�ֻ�");
			cPhone.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cPhone.setColspan(2);
			cPhone.setUseAscender(true); 
			cPhone.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cPhone.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cPhone); 
			
			Cell cinPhone = new Cell(target_form.getTCProperty("s4telephone").getStringValue());
			cinPhone.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cinPhone.setColspan(2);
			table.addCell(cinPhone);
			
			Cell cCost = new Cell("���� \nʹ�� \n���");
			cCost.setRowspan(4);//��ǰ��Ԫ��ռ����,������  
			cCost.setColspan(1);
			cCost.setUseAscender(true); 
			cCost.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cCost.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cCost); 
			
			Cell cSum = new Cell("��Ͷ��");
			cSum.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cSum.setColspan(1);
			cSum.setUseAscender(true); 
			cSum.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cSum.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cSum); 
			
			Cell cinSum = new Cell(target_form.getTCProperty("s4totalInvest").getStringValue());
			cinSum.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cinSum.setColspan(2);
			table.addCell(cinSum); 
			 
			Cell cBudget = new Cell("�ƻ����");
			cBudget.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cBudget.setColspan(2);
			cBudget.setUseAscender(true); 
			cBudget.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cBudget.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cBudget); 
			
			Cell cinBudget = new Cell(target_form.getTCProperty("s4budget").getStringValue());
			cinBudget.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cinBudget.setColspan(2);
			table.addCell(cinBudget); 
		
			Cell cCount = new Cell("���귢�� \n�ۼ�");
			cCount.setRowspan(2);//��ǰ��Ԫ��ռ����,������  
			cCount.setColspan(2);
			cCount.setUseAscender(true); 
			cCount.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cCount.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cCount); 
			
			Cell cPlan = new Cell("�ƻ����ţ���λ����Ԫ��");
			cPlan.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cPlan.setColspan(5);
			cPlan.setUseAscender(true); 
			cPlan.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cPlan.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cPlan); 
			
			
			table.addCell(new Paragraph("  С��")); 
			
			if (target_form.getTCProperty("s4month1").getStringArrayValue().length  > 0) {
			table.addCell(new Paragraph(""+target_form.getTCProperty("s4month1").getStringArrayValue()[0])); 
			}
			else {
				table.addCell(new Paragraph(" �� ����")); 

			}
			if (target_form.getTCProperty("s4month2").getStringArrayValue().length  > 0) {

				table.addCell(new Paragraph(""+target_form.getTCProperty("s4month2").getStringArrayValue()[0])); 
			}
			else {
				table.addCell(new Paragraph(" �� ����")); 

			}
			if (target_form.getTCProperty("s4month3").getStringArrayValue().length  > 0) {

				table.addCell(new Paragraph(""+target_form.getTCProperty("s4month3").getStringArrayValue()[0])); 
			}
			else {
				table.addCell(new Paragraph(" �� ����")); 

			}
			if (target_form.getTCProperty("s4month4").getStringArrayValue().length  > 0) {

				table.addCell(new Paragraph(""+target_form.getTCProperty("s4month4").getStringArrayValue()[0])); 
			}
			else {
				table.addCell(new Paragraph(" �� ����")); 

			}
			Cell cinCount = new Cell("  "+target_form.getTCProperty("s4countYear").getStringValue());
			cinCount.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cinCount.setColspan(2);
			table.addCell(cinCount); 
			
			
			table.addCell(new Paragraph("  "+target_form.getTCProperty("s4monthSum").getStringValue())); 
			if (target_form.getTCProperty("s4month1").getStringArrayValue().length  > 0) {
				table.addCell(new Paragraph(""+target_form.getTCProperty("s4month1").getStringArrayValue()[1])); 
				}
				else {
					table.addCell(new Paragraph("")); 

				}
				if (target_form.getTCProperty("s4month2").getStringArrayValue().length  > 0) {

					table.addCell(new Paragraph(""+target_form.getTCProperty("s4month2").getStringArrayValue()[1])); 
				}
				else {
					table.addCell(new Paragraph("")); 

				}
				if (target_form.getTCProperty("s4month3").getStringArrayValue().length  > 0) {

					table.addCell(new Paragraph(""+target_form.getTCProperty("s4month3").getStringArrayValue()[1])); 
				}
				else {
					table.addCell(new Paragraph("")); 

				}
				if (target_form.getTCProperty("s4month4").getStringArrayValue().length  > 0) {

					table.addCell(new Paragraph(""+target_form.getTCProperty("s4month4").getStringArrayValue()[1])); 
				}
				else {
					table.addCell(new Paragraph("")); 

				}


			Cell cSituation = new Cell("����\n�о�\n���");
			cSituation.setRowspan(12);//��ǰ��Ԫ��ռ����,������  
			cSituation.setColspan(1);
			cSituation.setUseAscender(true); 
			cSituation.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cSituation.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cSituation); 
			
			
			Cell cGain = new Cell("��ȡ�õĳɹ�");
			cGain.setRowspan(3);//��ǰ��Ԫ��ռ����,������  
			cGain.setColspan(2);
			cGain.setUseAscender(true); 
			cGain.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cGain.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cGain); 
			
			Cell cinGain =  new Cell(target_form.getTCProperty("s4currentGain").getStringValue());
			cinGain.setRowspan(3);//��ǰ��Ԫ��ռ����,������  
			cinGain.setColspan(5);
			table.addCell(cinGain); 
			
			Cell cProblem = new Cell("���ڵ�����");
			cProblem.setRowspan(3);//��ǰ��Ԫ��ռ����,������  
			cProblem.setColspan(2);
			cProblem.setUseAscender(true); 
			cProblem.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cProblem.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cProblem); 
			
			Cell cinProblem = new Cell(target_form.getTCProperty("s4existProblem").getStringValue());
			cinProblem.setRowspan(3);//��ǰ��Ԫ��ռ����,������  
			cinProblem.setColspan(5);
			table.addCell(cinProblem); 
			
			Cell cNext = new Cell("��һ���ƻ�����ʩ");
			cNext.setRowspan(3);//��ǰ��Ԫ��ռ����,������  
			cNext.setColspan(2);
			cNext.setUseAscender(true); 
			cNext.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cNext.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cNext); 
			
			Cell cinNext = new Cell(target_form.getTCProperty("s4nextPlan").getStringValue());
			cinNext.setRowspan(3);//��ǰ��Ԫ��ռ����,������  
			cinNext.setColspan(5);
			table.addCell(cinNext); 
			
	
			Cell cTime = new Cell("Ԥ�����ʱ��");
			cTime.setRowspan(3);//��ǰ��Ԫ��ռ����,������  
			cTime.setColspan(2);
			cTime.setUseAscender(true); 
			cTime.setVerticalAlignment(Cell.ALIGN_MIDDLE); 
			cTime.setHorizontalAlignment(Cell.ALIGN_CENTER);
			table.addCell(cTime); 
		
			
			Cell cinTime = new Cell(target_form.getTCProperty("s4deadline").getStringValue());
			cinTime.setRowspan(3);//��ǰ��Ԫ��ռ����,������  
			cinTime.setColspan(5);
			table.addCell(cinTime); 
			
		
			document.add(table); 
			document.add(new Paragraph(""));  
			document.add(new Paragraph("           �ֹ��쵼��             ��ˣ�                  �����鳤��"));  
			document.add(new Paragraph("           ��ˣ�             �绰/�ֻ���                ����ڣ�"));  


			document.close();  
			} catch (TCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (FileNotFoundException e) {  
				e.printStackTrace();  
			} catch (DocumentException e) {  
				e.printStackTrace();  
			} catch (IOException e) {  
				e.printStackTrace();  
			} 	
	}  
	public void S4SJGGRevisionMaster_exportWord(String exPath) { 
		System.out.println("AA");
		Document document = new Document(PageSize.A4); 
		
		try {  
			

			RtfWriter2.getInstance(document,
					new FileOutputStream(exPath));  
					document.open();  
	     
	   //���ú�ͬͷ  
	     
					Paragraph ph = new Paragraph();  
					Font f  = new Font();  
	     
					Paragraph p = new Paragraph("�����Ͼ��Զ����ɷ����޹�˾", 
					new Font(Font.NORMAL, 14, Font.BOLD, new Color(0, 0, 0)) );  
					p.setAlignment(1);  
					document.add(p);  
					ph.setFont(f);  
	    
	    //���ñ���
					
					document.add(new Paragraph(""));  

	    
					Paragraph ph2 = new Paragraph();  
	      
					Paragraph p2 = new Paragraph("��Ƹ��������", 
					new Font(Font.NORMAL, 16, Font.BOLD, new Color(0, 0, 0)) );  
					p2.setAlignment(1);  
					document.add(p2);  
					ph2.setFont(f);  
	  
					document.add(new Paragraph(""));  

					
					Table table = new Table(4,10);  
					document.add(new Paragraph("           �� �ţ�                                             ��  ��  ��"));  

					table.setBorderWidth(1);  
					table.setBorderColor(Color.BLACK);  
					table.setPadding(0);  
					table.setSpacing(0);  
	    
					table.addCell(new Paragraph("��Ŀ����"));  
					
					Cell cinName = new Cell(target_form.getTCProperty("s4Prjname").getStringValue());  
					cinName.setRowspan(1);
					cinName.setColspan(3);
					table.addCell(cinName); 
					
					table.addCell(new Paragraph("��Ŀ���"));
					table.addCell(new Paragraph(target_form.getTCProperty("s4Prjnumber").getStringValue()));  				
					table.addCell(new Paragraph("��Ŀ������")); 
					table.addCell(new Paragraph(target_form.getTCProperty("s4changereson").getStringValue()));  
					
					table.addCell(new Paragraph("��Ƶ�λ")); 
					table.addCell(new Paragraph(target_form.getTCProperty("s4DesignD").getStringValue()));  
					table.addCell(new Paragraph("��Ʋ���")); 
					table.addCell(new Paragraph(target_form.getTCProperty("s4DesignDpart").getStringValue()));  

	    // ��������  
					Cell cell = new Cell("����ԭ��\n" +target_form.getTCProperty("s4changereson").getStringValue()+"\n");  
					cell.setRowspan(2);//��ǰ��Ԫ��ռ����,������  
					cell.setColspan(4);
					table.addCell(cell); 
	    
					Cell cWay = new Cell("������ķ�����\n"+target_form.getTCProperty("s4changeway").getStringValue()+"\n");  
			
					cWay.setRowspan(2);//��ǰ��Ԫ��ռ����,������  
					cWay.setColspan(4);
					table.addCell(cWay); 
	    
					Cell cDoc = new Cell("��Ҫ���ĵ�ͼֽ���ļ���\n"+target_form.getTCProperty("s4needdocmen").getStringValue()+"\n");  
					cDoc.setRowspan(2);//��ǰ��Ԫ��ռ����,������  
					cDoc.setColspan(4);
					table.addCell(cDoc);
					
					Cell cImpact = new Cell("Ӱ���Է������������\n"+
							"����Χ: \n"+target_form.getTCProperty("s4demand").getStringValue()+"\n\n���Է������������: \n"
							+target_form.getTCProperty("s4testcase").getStringValue()+"\n\n����ģ����ϵͳ���: \n"+target_form.getTCProperty("s4design").getStringValue()	+"\n"
							);  
					cImpact.setRowspan(2);//��ǰ��Ԫ��ռ����,������  
					cImpact.setColspan(4);
					table.addCell(cImpact);
	    
					Cell cRemark = new Cell("��ע��\n"+target_form.getTCProperty("s4remarks").getStringValue()+"\n");  
					cRemark.setRowspan(2);//��ǰ��Ԫ��ռ����,������  
					cRemark.setColspan(4);
					table.addCell(cRemark);
	    
					document.add(table); 
					document.add(new Paragraph(""));   
					document.add(new Paragraph("           �����ˣ�                 ����ˣ�                  ��׼�ˣ�"));  


					document.close();  
			} catch (TCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (FileNotFoundException e) {  
				e.printStackTrace();  
			} catch (DocumentException e) {  
				e.printStackTrace();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		
	} 
	
	public JTextField getTextField(Container c){
		JTextField textField =null;
		for(int i=0;i<c.getComponentCount();i++){
			Component cnt=c.getComponent(i);
				if(cnt instanceof JTextField){
					return (JTextField)cnt;
				}
				if(cnt instanceof Container){
					textField=getTextField((Container)cnt);
					if(textField !=null)
						return textField;
				}
			}			
		return textField;
	}
	
}

