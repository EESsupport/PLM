package com.gdnz.sac1.form;


import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;

import cn.com.origin.util.SACDocument;
 

class S4HDKJXMJZYB_UI {
	
	private static final long serialVersionUID = 1L;
	public static final String wordsTips = "��������ı�������Ӧ������1024���֣�";

	public String exportPath = null;

	
	public JTextField textPrjName = null;//��������
	public JTextField textChargeFirm = null;//�е���λ
	public JTextField textPrjNumber = null;//������
	public JTextField textPrjLeader = null;//�����鳤
	public JTextField textTelephone = null;//�绰���ֻ�
	public JTextField textTotalInvest = null;//��Ͷ��
	public JTextField textBudget = null;//�ƻ����
	public JTextArea textCurrentGain = null; //��ȡ�õĳɹ�
	public JTextArea textExistProblem = null; //���ڵ�����
	public JTextArea textNextPlan = null; //��һ���ƻ�����ʩ
	public JTextArea textDeadline = null; //Ԥ�����ʱ��
	
	public JTable tableBudget = null;//������Դ
	public JLabel lbPrjName = null;
	
	public JButton btnSelect = null;

	
	public SACDocument gainDoc = null;
	public SACDocument problemDoc = null;
	public SACDocument planDoc = null;
	public SACDocument deadlineDoc = null;
	
	public SACDocument numDoc = null;
	public SACDocument leaderDoc = null;
	public SACDocument phoneDoc = null;
	public SACDocument budgetDoc = null;
	
	public SACDocument nameDoc = null;
	public SACDocument investDoc = null;
	public SACDocument firmDoc = null;




	
	public JPanel panel = null;
	
	public JPanel getJPanel(){
		return panel;
	}
	
	public S4HDKJXMJZYB_UI(){
		panel = new JPanel(new GridLayout(1,1));
		panel.setBounds(0, 0, 800, 800);
		panel.add(applyPanel());
	
	}
	
	
	public JPanel applyPanel(){
	
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		TitledBorder nameTitleBorder = BorderFactory.createTitledBorder("");
		nameTitleBorder.setTitlePosition(2);
		namePanel.setBorder(nameTitleBorder);
		
		textPrjName = new JTextField();
		lbPrjName = new JLabel("  ��������");
		lbPrjName.setPreferredSize(new Dimension(80, 20));
		nameDoc = new SACDocument();
		nameDoc.setMaxLength(64);
		textPrjName.setDocument(nameDoc);


		namePanel.add(lbPrjName);
		namePanel.add(textPrjName);
		
		JPanel PrjInfoPanel = new JPanel(new GridLayout(2, 2, 2, 2));
		TitledBorder infoTitleBorder = BorderFactory.createTitledBorder("");
		infoTitleBorder.setTitlePosition(2);
		PrjInfoPanel.setBorder(infoTitleBorder);

		
		JPanel PrjInfo1Panel = new JPanel();
		PrjInfo1Panel.setLayout(new BoxLayout(PrjInfo1Panel, BoxLayout.X_AXIS));
		
		
		JPanel PrjInfo2Panel = new JPanel();
		PrjInfo2Panel.setLayout(new BoxLayout(PrjInfo2Panel, BoxLayout.X_AXIS));
		
		JPanel PrjInfo3Panel = new JPanel();
		PrjInfo3Panel.setLayout(new BoxLayout(PrjInfo3Panel, BoxLayout.X_AXIS));
		
		JPanel PrjInfo4Panel = new JPanel();
		PrjInfo4Panel.setLayout(new BoxLayout(PrjInfo4Panel, BoxLayout.X_AXIS));
		
		JLabel lbPrjFirm = new JLabel("  �е���λ");
		lbPrjFirm.setPreferredSize(new Dimension(80, 20));
		textChargeFirm = new JTextField();
		firmDoc = new SACDocument();
		firmDoc.setMaxLength(32);
		textChargeFirm.setDocument(firmDoc);
		
		JLabel lbPrjId = new JLabel("  ������");
		lbPrjId.setPreferredSize(new Dimension(80, 20));
		textPrjNumber = new JTextField();
		numDoc = new SACDocument();
		numDoc.setMaxLength(32);
		textPrjNumber.setDocument(numDoc);
		
		JLabel lbPrjLeader = new JLabel("  �����鳤");
		lbPrjLeader.setPreferredSize(new Dimension(80, 20));
		textPrjLeader = new JTextField();
		leaderDoc = new SACDocument();
		leaderDoc.setMaxLength(32);
		textPrjLeader.setDocument(leaderDoc);
		
		JLabel lbPhone = new JLabel("  �绰/�ֻ� ");
		lbPhone.setPreferredSize(new Dimension(80, 20));
		textTelephone = new JTextField();
		phoneDoc = new SACDocument();
		phoneDoc.setMaxLength(32);
		textTelephone.setDocument(phoneDoc);
		
		PrjInfo1Panel.add(lbPrjFirm);
		PrjInfo1Panel.add(textChargeFirm);
		PrjInfo2Panel.add(lbPrjId);
		PrjInfo2Panel.add(textPrjNumber);
		PrjInfo3Panel.add(lbPrjLeader);
		PrjInfo3Panel.add(textPrjLeader);
		PrjInfo4Panel.add(lbPhone);
		PrjInfo4Panel.add(textTelephone);
		
		PrjInfoPanel.add(PrjInfo1Panel);
		PrjInfoPanel.add(PrjInfo2Panel);
		PrjInfoPanel.add(PrjInfo3Panel);
		PrjInfoPanel.add(PrjInfo4Panel);

		
		JPanel costPanel = new JPanel();
		costPanel.setLayout(new BoxLayout(costPanel, BoxLayout.Y_AXIS));	

		
		TitledBorder costTitlePanel = BorderFactory.createTitledBorder("����ʹ�����: ");
		costTitlePanel.setTitlePosition(2);
		costPanel.setBorder(costTitlePanel);
		
		JPanel investPanel = new JPanel(new GridLayout(1,2));
		
		JPanel invest1Panel = new JPanel();
		invest1Panel.setLayout(new BoxLayout(invest1Panel, BoxLayout.X_AXIS));	
		JPanel invest2Panel = new JPanel();
		invest2Panel.setLayout(new BoxLayout(invest2Panel, BoxLayout.X_AXIS));	
		JLabel lbTotalInvest = new JLabel("  ��Ͷ��");
		lbTotalInvest.setPreferredSize(new Dimension(80, 20));
		textTotalInvest = new JTextField();
		investDoc = new SACDocument();
		investDoc.setMaxLength(32);
		textTotalInvest.setDocument(investDoc);
		
		
		JLabel lbBudget = new JLabel("   �ƻ����");
		lbBudget.setPreferredSize(new Dimension(80, 20));
		textBudget = new JTextField();
		budgetDoc = new SACDocument();
		budgetDoc.setMaxLength(32);
		textBudget.setDocument(budgetDoc);
		
		invest1Panel.add(lbTotalInvest);
		invest1Panel.add(textTotalInvest);
		invest2Panel.add(lbBudget);
		invest2Panel.add(textBudget);
		
		investPanel.add(invest1Panel);
		investPanel.add(invest2Panel);

		
		JPanel monthPanel = new JPanel(new GridLayout(1, 0));
		TitledBorder monthTitleBorder = BorderFactory.createTitledBorder("�ƻ����� ����λ����Ԫ��");
		monthTitleBorder.setTitlePosition(2);
		monthPanel.setBorder(monthTitleBorder);
		
		Object[] columnBudget = {"", "", "", "", "",""};
		Object[][] dataBudget = {{"���귢���ۼ�", "С��", "  ������", "  ������", "  ������", "  ������"}
		, {"", "", "", "", "", ""}};


		DefaultTableCellRenderer dtc = new DefaultTableCellRenderer(){  

			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table,  
					Object value, boolean isSelected, boolean hasFocus,  
					int row, int column) {  
				if(row == 0)  {
					//setForeground(Color.GRAY);
					setBackground(lbPrjName.getBackground());
				}
				else{
					setBackground(Color.WHITE);
				}
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  

			}  
		};
		
		DefaultTableModel tableModel = new DefaultTableModel(dataBudget,columnBudget){   
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column){ 
				if ((column < 1 && row == 0)||(row == 0 && column == 1)) {
					return false;
				} else {
					return true;
				}
			}
		};

		tableBudget = new JTable(tableModel);
		tableBudget.getTableHeader().setReorderingAllowed( false ) ;
		tableBudget.setPreferredScrollableViewportSize(new Dimension(100, 40));
		tableBudget.getColumnModel().getColumn(0).setCellRenderer(dtc);
		tableBudget.getColumnModel().getColumn(1).setCellRenderer(dtc);
		tableBudget.getColumnModel().getColumn(2).setCellRenderer(dtc);
		tableBudget.getColumnModel().getColumn(3).setCellRenderer(dtc);
		tableBudget.getColumnModel().getColumn(4).setCellRenderer(dtc);
		tableBudget.getColumnModel().getColumn(5).setCellRenderer(dtc);
	
		JScrollPane scrollBudget = new JScrollPane(tableBudget);
		monthPanel.add(scrollBudget);

		investPanel.setSize(new Dimension(100, 40));
		costPanel.add(investPanel);
		costPanel.add(monthPanel);
		
		

		
		JPanel statusPanel = new JPanel(new GridLayout(4, 1, 2, 2));
		TitledBorder statusTitlePanel = BorderFactory.createTitledBorder("�����о����");
		statusTitlePanel.setTitlePosition(2);
		statusPanel.setBorder(statusTitlePanel);
		
		JPanel gainPanel = new JPanel(new BorderLayout());
		
		TitledBorder gainTitlePanel = BorderFactory.createTitledBorder("��ȡ�õĳɹ���");
		gainTitlePanel.setTitlePosition(2);
		gainPanel.setBorder(gainTitlePanel);
	
		textCurrentGain = new JTextArea(5, 10);
		textCurrentGain.setText("");
		gainDoc = new SACDocument();
		gainDoc.setMaxLength(2048);
		textCurrentGain.setDocument(gainDoc);
		textCurrentGain.setLineWrap(true);
		JScrollPane scrolGain=new JScrollPane(textCurrentGain);
		gainPanel.add(scrolGain);
		
		JPanel problemPanel = new JPanel(new GridLayout(1, 1, 2, 2));
		
		TitledBorder problemTitlePanel = BorderFactory.createTitledBorder("���ڵ����⣺");
		problemTitlePanel.setTitlePosition(2);
		problemPanel.setBorder(problemTitlePanel);
		
		textExistProblem = new JTextArea(5, 10);
		textExistProblem.setText("");
		problemDoc = new SACDocument();
		problemDoc.setMaxLength(2048);
		textExistProblem.setDocument(problemDoc);
		textExistProblem.setLineWrap(true);
		JScrollPane scrolProblem = new JScrollPane(textExistProblem);	
		problemPanel.add(scrolProblem);
	
		JPanel planPanel = new JPanel(new GridLayout(1, 1, 2, 2));
		
		TitledBorder planTitlePanel = BorderFactory.createTitledBorder("��һ���ƻ�����ʩ��");
		planTitlePanel.setTitlePosition(2);
		planPanel.setBorder(planTitlePanel);
		
		textNextPlan = new JTextArea(5, 10);
		textNextPlan.setText("");
		planDoc = new SACDocument();
		planDoc.setMaxLength(2048);
		textNextPlan.setDocument(planDoc);
		textNextPlan.setLineWrap(true);
		JScrollPane scrolPlan = new JScrollPane(textNextPlan);	
		planPanel.add(scrolPlan);
		
		JPanel timePanel = new JPanel(new GridLayout(1, 1, 2, 2));
		
		TitledBorder timeTitlePanel = BorderFactory.createTitledBorder("Ԥ�����ʱ�䣺");
		timeTitlePanel.setTitlePosition(2);
		timePanel.setBorder(timeTitlePanel);
		
		textDeadline = new JTextArea(5, 10);
		textDeadline.setText("");
		deadlineDoc = new SACDocument();
		deadlineDoc.setMaxLength(512);
		textDeadline.setDocument(deadlineDoc);
		textDeadline.setLineWrap(true);
		JScrollPane scrolTime = new JScrollPane(textDeadline);	
		timePanel.add(scrolTime);
		
		
		statusPanel.add(gainPanel);
		statusPanel.add(problemPanel);
		statusPanel.add(planPanel);
		statusPanel.add(timePanel);
		
		JPanel exportPanel = new JPanel();
		btnSelect = new JButton("����word��");
		btnSelect.addActionListener(new ActionListener() {

		
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fc = new JFileChooser(); 
				if(e.getSource()==btnSelect){ 
					/* 
					    ������Ϊ��Ҫ�ġ���ΪJFileChooserĬ�ϵ���ѡ���ļ�������ҪѡĿ¼�� 
					    ��Ҫ��DIRECTORIES_ONLYװ��ģ�� 
					���⣬��ѡ���ļ���������˾� 
					*/  System.out.println("B");
					      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
					      int intRetVal = fc.showOpenDialog(null); 
					      if( intRetVal == JFileChooser.APPROVE_OPTION){ 
					    	  System.out.println("A");
					    	  exportPath = fc.getSelectedFile().getPath();
					    	
					    	  File file = new File(exportPath+"/�й����缯�Ź�˾�Ƽ���Ŀ�о���չ�±���.doc");
					    	  if (!file.exists()) {
						    	  exportWord(exportPath);

					    	  }
					    	  
					    	  else {
					    		  int n = JOptionPane.showConfirmDialog(null, "�ļ��Ѵ��ڣ�ȷ�ϸ�����",
					    				  "��ʾ", JOptionPane.YES_NO_OPTION);
					    		  
					    		  if (n == JOptionPane.YES_OPTION) {
					    			  exportWord(exportPath);
					    		  } 
					    		  
					    	  }
					      } 
				}
			}
		});
		exportPanel.add(btnSelect);
		
		
		panel.add(namePanel);
		panel.add(PrjInfoPanel);
		panel.add(costPanel);
		panel.add(statusPanel);

	
		textCurrentGain.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (textCurrentGain.getText().equals(wordsTips)) {
					textCurrentGain.setText("");
				}	
					
			}  

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}		
		});
		
		
		textExistProblem.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (textExistProblem.getText().equals(wordsTips)) {
					textExistProblem.setText("");
				}	
					
			}  

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}		
		});
		
			
		textNextPlan.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (textNextPlan.getText().equals(wordsTips)) {
					textNextPlan.setText("");
				}	
					
			}  

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}		
		});
		
			
		return panel;
	}
	
	public void exportWord(String exPath) { 
		
		System.out.println("AA");
		Document document = new Document(PageSize.A4); 
		
		try {  
			RtfWriter2.getInstance(document,
			new FileOutputStream(exPath+"/�й����缯�Ź�˾�Ƽ���Ŀ�о���չ�±���.doc"));  
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
			
			Cell cinName = new Cell(textPrjName.getText());
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
			
			Cell cinFirm = new Cell(textChargeFirm.getText());
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
			
			Cell cinNum = new Cell(textPrjNumber.getText());
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
			
			Cell cinLeader = new Cell(textPrjLeader.getText());
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
			
			Cell cinPhone = new Cell(textTelephone.getText());
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
			
			Cell cinSum = new Cell(textTotalInvest.getText());
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
			
			Cell cinBudget = new Cell(textBudget.getText());
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
			table.addCell(new Paragraph(""+tableBudget.getValueAt(0, 2))); 
			table.addCell(new Paragraph(""+tableBudget.getValueAt(0, 3))); 
			table.addCell(new Paragraph(""+tableBudget.getValueAt(0, 4))); 
			table.addCell(new Paragraph(""+tableBudget.getValueAt(0, 5))); 
			
			Cell cinCount = new Cell("  "+tableBudget.getValueAt(1, 0));
			cinCount.setRowspan(1);//��ǰ��Ԫ��ռ����,������  
			cinCount.setColspan(2);
			table.addCell(cinCount); 
			
			
			table.addCell(new Paragraph("  "+tableBudget.getValueAt(1, 1))); 
			table.addCell(new Paragraph("  "+tableBudget.getValueAt(1, 2))); 
			table.addCell(new Paragraph("  "+tableBudget.getValueAt(1, 3))); 
			table.addCell(new Paragraph("  "+tableBudget.getValueAt(1, 4))); 
			table.addCell(new Paragraph("  "+tableBudget.getValueAt(1, 5))); 


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
			
			Cell cinGain = new Cell(textCurrentGain.getText());
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
			
			Cell cinProblem = new Cell(textExistProblem.getText());
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
			
			Cell cinNext = new Cell(textNextPlan.getText());
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
		
			
			Cell cinTime = new Cell(textDeadline.getText());
			cinTime.setRowspan(3);//��ǰ��Ԫ��ռ����,������  
			cinTime.setColspan(5);
			table.addCell(cinTime); 
			
		
			document.add(table); 
			document.add(new Paragraph(""));  
			document.add(new Paragraph("           �ֹ��쵼��             ��ˣ�                  �����鳤��"));  
			document.add(new Paragraph("           ��ˣ�             �绰/�ֻ���                ����ڣ�"));  


			document.close();  
			} catch (FileNotFoundException e) {  
				e.printStackTrace();  
			} catch (DocumentException e) {  
				e.printStackTrace();  
			} catch (IOException e) {  
				e.printStackTrace();  
			} 
		
	}  
	

}
