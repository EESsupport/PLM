package com.gdnz.sac1.form;
import java.awt.Color;  


import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
 
import com.lowagie.text.Cell;  
import com.lowagie.text.Document;  
import com.lowagie.text.DocumentException;  
import com.lowagie.text.Font;  
import com.lowagie.text.PageSize;  
import com.lowagie.text.Paragraph;  
import com.lowagie.text.Table;  
import com.lowagie.text.rtf.RtfWriter2;  
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCException;

class PrjWord {
	
	public PrjWord(String exPath,TCComponentForm target_form) {
		Document document = new Document(PageSize.A4); 
		
		try {  
			
			
			if (target_form.getTCProperty("s4Prj_name").getStringValue().length() > 0) {
			RtfWriter2.getInstance(document,
					new FileOutputStream(exPath));  
			}
			
			else {
				RtfWriter2.getInstance(document,
						new FileOutputStream(exPath));  
			}
			
			
			document.open();  

					// ����
	     	         
					Paragraph ph = new Paragraph();  
					Font f  = new Font();  

					Paragraph p = new Paragraph(target_form.getTCProperty("s4Prj_name").getStringValue(),
					new Font(Font.NORMAL, 16, Font.BOLD, new Color(0, 0, 0)) );  
					p.setAlignment(1);  
					document.add(p);  
					ph.setFont(f);  
	 
					document.add(new Paragraph(""));  

					//��
					Table table = new Table(8,10);  

					table.setBorderWidth(1);  
					table.setBorderColor(Color.BLACK);  
					table.setPadding(0);  
					table.setSpacing(0);
					table.setWidth(100);
	    
					makeCell("��Ŀ����", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4Prj_name").getStringValue(), true, 1, 3, table);
					makeCell("�������", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4prj_id").getStringValue(), true, 1, 3, table);
					
					makeCell("��Ŀ����", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4Projtype").getStringValue(), true, 1, 3, table);
					makeCell("������Ŀ", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4teamevent").getStringValue(), true, 1, 1, table);
					makeCell("seiban��", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4seiban").getStringValue(), true, 1, 1, table);

					makeCell("��\nĿ\n��\n��\n��\n��", true, 75, 1, table);

					makeCell("\n��Ŀ������\n", true, 1, 7, table);
					
					makeCell("����", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4name2").getStringValue(), false, 1, 1, table);
					makeCell("ְ��", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4title2").getStringValue(), false, 1, 1, table);
					makeCell("רҵ", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4profession").getStringValue(), false, 1, 2, table);
					makeCell("���Ƶ�λ", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4department").getStringValue(), false, 1, 3, table);
					makeCell("���Ʋ���", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4prj_class").getStringValue(), false, 1, 2, table);
					makeCell("��ϵ��ʽ", true, 3, 1, table);
					makeCell(target_form.getTCProperty("s4contact_way").getStringValue(), false, 3, 3, table);
					makeCell("Email", true, 3, 1, table);
					makeCell(target_form.getTCProperty("s4Email").getStringValue(), false, 3, 2, table);
					
					makeCell("����", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4name1").getStringValue(), false, 1, 1, table);
					makeCell("ְ��", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4title1").getStringValue(), false, 1, 1, table);
					makeCell("רҵ", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4profession1").getStringValue(), false, 1, 2, table);
					makeCell("���Ƶ�λ", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4department1").getStringValue(), false, 1, 3, table);
					makeCell("���Ʋ���", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4prj_class2").getStringValue(), false, 1, 2, table);
					makeCell("��ϵ��ʽ", true, 3, 1, table);
					makeCell(target_form.getTCProperty("s4contact_way1").getStringValue(), false, 3, 3, table);
					makeCell("Email", true, 3, 1, table);
					makeCell(target_form.getTCProperty("s4Email1").getStringValue(), false, 3, 2, table);
					
					makeCell("����", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4name3").getStringValue(), false, 1, 1, table);
					makeCell("ְ��", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4title3").getStringValue(), false, 1, 1, table);
					makeCell("רҵ", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4profession3").getStringValue(), false, 1, 2, table);
					makeCell("���Ƶ�λ", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4department3").getStringValue(), false, 1, 3, table);
					makeCell("���Ʋ���", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4prj_class3").getStringValue(), false, 1, 2, table);
					makeCell("��ϵ��ʽ", true, 3, 1, table);
					makeCell(target_form.getTCProperty("s4contact_way3").getStringValue(), false, 3, 3, table);
					makeCell("Email", true, 3, 1, table);
					makeCell(target_form.getTCProperty("s4Email3").getStringValue(), false, 3, 2, table);
					
					
					makeCell("\n��Ŀ���\n", true, 1, 7, table);
					
					makeCell("���÷�Χ", true, 3, 1, table);
					makeCell(target_form.getTCProperty("s4scope").getStringValue(), false, 3, 6, table);
					makeCell("��ʼ����", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4timed").getStringValue(), false, 1, 3, table);
					makeCell("��������", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4timedup").getStringValue(), false, 1, 2, table);
					makeCell("�е���λ", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4tandd").getStringValue(), false, 1, 3, table);
					makeCell("�е�����", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4tandd1").getStringValue(), false, 1, 2, table);
					makeCell("���Է�ʽ", true, 3, 1, table);
					makeCell(target_form.getTCProperty("s4Grind_testw").getStringValue(), false, 3, 6, table);
					makeCell("������λ", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4Cooperator").getStringValue(), false, 1, 3, table);
					makeCell("������ʽ", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4Cooperation").getStringValue(), false, 1, 2, table);
					makeCell("��Ŀ��Ԥ��(��Ԫ)", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4prj_budget").getStringValue(), false, 1, 3, table);
					makeCell("�ʽ���Դ", true, 1, 1, table);
					makeCell(target_form.getTCProperty("s4cap_source").getStringValue(), false, 1, 2, table);
					
					makeCell("\nԤ��Ŀ�꼰�ɹ�\n", true, 1, 7, table);
					
					makeCell("Ŀ�꼼��\nˮƽ", true, 11, 1, table);
					makeCell(target_form.getTCProperty("s4goal_resu").getStringValue(), false, 11, 6, table);
					makeCell("ר��", true, 3, 1, table);
					makeCell(target_form.getTCProperty("s4patent").getStringValue(), false, 3, 6, table);
					makeCell("����Ȩ", true, 3, 1, table);
					makeCell(target_form.getTCProperty("s4copyright").getStringValue(), false, 3, 6, table);
					makeCell("����", true, 3, 1, table);
					makeCell(target_form.getTCProperty("s4thesis").getStringValue(), false, 3, 6, table);
					makeCell("�ɹ�����\n��ʽ", true, 6, 1, table);
					makeCell(target_form.getTCProperty("s4Results_acceway").getStringValue(), false, 6, 6, table);
					
					makeCell("\n��Ŀ����\n", true, 1, 7, table);
					
					makeCell(target_form.getTCProperty("s4prj_content").getStringValue(), false, 7, 7, table);
					makeCell(target_form.getTCProperty("s4ado_stand").getStringValue(), false, 6, 7, table);
					
					makeCell("\n���Ȱ���\n", true, 1, 7, table);
					
					makeCell("�׶�", true, 1, 1, table);
					makeCell("�׶�����", true, 1, 2, table);
					makeCell("��ʼʱ��", true, 1, 2, table);
					makeCell("��ֹʱ��", true, 1, 2, table);

					makeCell("A", true, 1, 1, table);
					makeCell("����׶�", true, 1, 2, table);
					if (target_form.getTCProperty("s4uptime").getStringArrayValue().length > 0) {
						
						makeCell(target_form.getTCProperty("s4start_t").getStringArrayValue()[0], true, 1, 2, table);
						makeCell(target_form.getTCProperty("s4uptime").getStringArrayValue()[0], true, 1, 2, table);
					}
					else {
						
						makeCell("", true, 1, 2, table);
						makeCell("", true, 1, 2, table);	
						
					}
					
					makeCell("B", true, 1, 1, table);
					makeCell("��ƽ׶�", true, 1, 2, table);
					
					if (target_form.getTCProperty("s4uptime").getStringArrayValue().length > 0) {
						
						makeCell(target_form.getTCProperty("s4start_t").getStringArrayValue()[1], true, 1, 2, table);
						makeCell(target_form.getTCProperty("s4uptime").getStringArrayValue()[1], true, 1, 2, table);
						
					}
					else {
						
						
						makeCell("", true, 1, 2, table);
						makeCell("", true, 1, 2, table);
					
					}
					
					makeCell("C", true, 1, 1, table);
					makeCell("�������ƽ׶�", true, 1, 2, table);
					
					if (target_form.getTCProperty("s4uptime").getStringArrayValue().length > 0) {

						makeCell(target_form.getTCProperty("s4start_t").getStringArrayValue()[2], true, 1, 2, table);
						makeCell(target_form.getTCProperty("s4uptime").getStringArrayValue()[2], true, 1, 2, table);
					}
					else {
						
						makeCell("", true, 1, 2, table);
						makeCell("", true, 1, 2, table);	
					}
						
					makeCell("D", true, 1, 1, table);
					makeCell("���Լ����н׶�", true, 1, 2, table);
					
					if (target_form.getTCProperty("s4uptime").getStringArrayValue().length > 0) {

						makeCell(target_form.getTCProperty("s4start_t").getStringArrayValue()[3], true, 1, 2, table);
						makeCell(target_form.getTCProperty("s4uptime").getStringArrayValue()[3], true, 1, 2, table);
					}
					else {
						
						makeCell("", true, 1, 2, table);
						makeCell("", true, 1, 2, table);	
					}
					
					makeCell("E", true, 1, 1, table);
					makeCell("��Ŀ����", true, 1, 2, table);
					
					if (target_form.getTCProperty("s4uptime").getStringArrayValue().length > 0) {

						makeCell(target_form.getTCProperty("s4start_t").getStringArrayValue()[4], true, 1, 2, table);
						makeCell(target_form.getTCProperty("s4uptime").getStringArrayValue()[4], true, 1, 2, table);
					}
					else {
						
						makeCell("", true, 1, 2, table);
						makeCell("", true, 1, 2, table);	
					}
					makeCell("\n��Ŀ����Ա\n", true, 1, 8, table);
					makeCell("����", true, 1, 1, table);
					makeCell("�Ա�", true, 1, 1, table);
					makeCell("ְ��", true, 1, 1, table);
					makeCell("����", true, 1, 1, table);
					makeCell("�е�����Ŀ��Ҫ����", true, 1, 2, table);
					makeCell("Ͷ������", true, 1, 1, table);
					makeCell("��ע", true, 1, 1, table);
					
					for (int i = 0; i < target_form.getTCProperty("s4name").getStringArrayValue().length; i++) {
						makeHumCell(""+target_form.getTCProperty("s4name").getStringArrayValue()[i], 
								""+target_form.getTCProperty("s4sex").getStringArrayValue()[i],
								""+target_form.getTCProperty("s4title").getStringArrayValue()[i],
								""+target_form.getTCProperty("s4age").getStringArrayValue()[i],
								""+target_form.getTCProperty("s4mainwork").getStringArrayValue()[i],
								""+target_form.getTCProperty("s4inmonth").getStringArrayValue()[i],
								""+target_form.getTCProperty("s4remark").getStringArrayValue()[i],
								table);
				}



					Table cosTable = new Table(10,10);  

					cosTable.setBorderWidth(1);  
					cosTable.setBorderColor(Color.BLACK);  
					cosTable.setPadding(0);  
					cosTable.setSpacing(0);
					cosTable.setWidth(100);
					
					for (int i = 0; i < 5; i++) {
						 if(i==0)
							makeCell("������Դ", true, 1, 2, cosTable);
						else if(i==1)
							makeCell("�����ܹ�˾����", true, 1, 2, cosTable);
						else if(i==2)
							makeCell("���빫˾������", true, 1, 2, cosTable);
						else if(i==3)
							makeCell("���Ʋ����Գ���", true, 1, 2, cosTable);
						else if(i==4)
							makeCell("��		��", true, 1, 2, cosTable);
						 
						if (target_form.getTCProperty("s4ina").getStringArrayValue().length > 0) {
							
							makeCell(""+target_form.getTCProperty("s4ina").getStringArrayValue()[i], true, 1, 2, cosTable);
						}
						else {
							
							makeCell("", true, 1, 2, cosTable);	
						}
						
						if (target_form.getTCProperty("s4inb").getStringArrayValue().length > 0) {
							
							makeCell(""+target_form.getTCProperty("s4inb").getStringArrayValue()[i], true, 1, 2, cosTable);
						}
						else {
							
							makeCell("", true, 1, 2, cosTable);	
						}

						if (target_form.getTCProperty("s4inc").getStringArrayValue().length > 0) {
	
							makeCell(""+target_form.getTCProperty("s4inc").getStringArrayValue()[i], true, 1, 2, cosTable);
						}
						else {
	
							makeCell("", true, 1, 2, cosTable);	
						}

						if (target_form.getTCProperty("s4remark1").getStringArrayValue().length > 0) {
	
							makeCell(""+target_form.getTCProperty("s4remark1").getStringArrayValue()[i], true, 1, 2, cosTable);
						}
						else {
	
							makeCell("", true, 1, 2, cosTable);	
						}
											
					}
					
					makeCell("Ԥ��֧����Ŀ", true, 1, 2, cosTable);
					makeCell("Ԥ�н׶�(��Ԫ)", true, 1, 1, cosTable);
					makeCell("�������(��Ԫ)", true, 1, 1, cosTable);
					makeCell("��������(��Ԫ)", true, 1, 1, cosTable);
					makeCell("�����������(��Ԫ)", true, 1, 2, cosTable);
					makeCell("���ȷ��(��Ԫ)", true, 1, 1, cosTable);
					makeCell("������ݼ�����", true, 1, 2, cosTable);
					
					String []str={"(һ)ֱ�ӷ���","1.��Ա��","(1)�о���Ա����","(2)��ʱ������","2.�豸�������","(1)����","(2)����","3.ҵ���","(1)����,Ԫ��������","(2)�����ӹ���","(3)���������","(4)���Ϸ�","(5)�����","(6)���÷�","4.���̻���Ʒ�","5.����ֱ�ӷ���","(��)��ӷ���","1.���������豸ʹ�÷�","2.ֱ�ӹ������","3.������ӷ���","(��)�����о�֧��","1.����֧��1","2.����֧��2","�� ��"};
					
					if (target_form.getTCProperty("s4pre_stage").getStringArrayValue().length > 0) {
						
			
						for (int i = 0; i < 24; i++) {
							makeCostCell(
								str[i],
								""+target_form.getTCProperty("s4pre_stage").getStringArrayValue()[i], 
								""+target_form.getTCProperty("s4prj_design").getStringArrayValue()[i],
								""+target_form.getTCProperty("s4pro_test").getStringArrayValue()[i],
								""+target_form.getTCProperty("s4tandc").getStringArrayValue()[i],
								""+target_form.getTCProperty("s4designcom").getStringArrayValue()[i],
								""+target_form.getTCProperty("s4cbareson").getStringArrayValue()[i],
								cosTable);
						}
					}
					
					else {
						
						for (int i = 0; i < 24; i++) {
							makeCostCell(
								str[i],"","","","","","",cosTable);
						}
					}
					

					document.add(table); 
					document.newPage();
					document.add(new Paragraph(""));
					document.add(new Paragraph("\n��Ŀ�ܷ��ã�      "+
							target_form.getTCProperty("s4prj_cost").getStringValue()+"��Ԫ"));  
					document.add(cosTable); 




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
	
	private void makeCell(String content, boolean inMid,
			int rows, int cols, Table table) {
		
		if (content.equals(""+"null")) {
			content = "";
		}
		Cell cell = new Cell(content);  
		cell.setRowspan(rows);
		cell.setColspan(cols);
		table.addCell(cell);
		
		if (inMid) {
			cell.setUseAscender(true);
			cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(cell.ALIGN_CENTER);
		}
		
	}
	
	private void makeCostCell(String s1, String s2,String s3,
			String s4,String s5,String s6,String s7,Table table) {
		
		
		makeCell(s1, false, 1, 2, table);
		makeCell(s2, true, 1, 1, table);
		makeCell(s3, true, 1, 1, table);
		makeCell(s4, true, 1, 1, table);
		makeCell(s5, true, 1, 2, table);
		makeCell(s6, true, 1, 1, table);
		makeCell(s7, true, 1, 2, table);
	}
	
	private void makeHumCell(String s1, String s2,String s3,
			String s4,String s5,String s6,String s7,Table table) {
		
		
		makeCell(s1, true, 1, 1, table);
		makeCell(s2, true, 1, 1, table);
		makeCell(s3, true, 1, 1, table);
		makeCell(s4, true, 1, 1, table);
		makeCell(s5, true, 1, 2, table);
		makeCell(s6, true, 1, 1, table);
		makeCell(s7, true, 1, 1, table);
	}
	

}
