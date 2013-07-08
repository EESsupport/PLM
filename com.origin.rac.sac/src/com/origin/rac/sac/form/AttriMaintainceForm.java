package com.origin.rac.sac.form;
/**
 * @file AttriMaintainceForm.java
 *
 * @brief ���ϰ汾Form���ƻ�
 * 
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import cn.com.origin.util.JAutoCompleteComboBox;


import cn.com.origin.util.SACDocument;
import cn.com.origin.util.SACJTextField128;
import cn.com.origin.util.SACJTextField160;
import cn.com.origin.util.SACJTextField32;
import cn.com.origin.util.SACMyDocument;

import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.form.AbstractTCForm;
import com.teamcenter.rac.kernel.*;
import com.teamcenter.rac.util.*;

public class AttriMaintainceForm extends AbstractTCForm
{
    private TCFormProperty[] formProperties = null;
	private String[] optionKeys = null;
	private String preferenceName = "SAC_AMF";

    private Registry registry = Registry.getRegistry(this);
    private TCComponentForm form;
    private TCSession tcsession;
    private Font font;
    private SACJTextField32 wlbmiTextField;//���ϱ���
    private JComboBox wlmbiTextField;//����ģ��
    private SACJTextField160 wlmsiTextField;//��������
    private JComboBox wlztiTextField;//����״̬
    private SACJTextField32 sccsiTextField;//��������
    private SACJTextField32 dhdmiTextField;//��������
    private SACJTextField32 dydyiTextField;//��Դ��ѹ
    private SACJTextField32 ctdianliTextField;//CT����
    private JCheckBox kcwliTextField;//�������
    private JComboBox zydwiTextField;//��Ҫ��λ
    private JCheckBox yxgxsmiTextField;//�������˵��
    private SACJTextField32 gxhiTextField;//�����
    private JComboBox sfyxwliTextField;//�Ƿ�ԭ������
    
    private JComboBox zzdmiTextField1;             //��֯����1
    private JComboBox mrcgyiTextField1;//Ĭ�ϲɹ�Ա���1
    private JComboBox jihyiTextField1;//�ƻ�Ա���1
    private iTextField tqq_gdiTextField1;//�̶���ǰ��1
    private JComboBox kclbjiTextField1;//SAC_������1
    private JComboBox cwlbjiTextField1;//SAC_�������1
    private JComboBox jhlbjiTextField1;//SAC_�ƻ����1
    private JComboBox cplbjiTextField1;//SAC_��Ʒ���1
    private JComboBox zkciTextField1;//�ӿ��1
    private SACJTextField32 hwiTextField1;//��λ1
    private iTextField gdgytsiTextField1;//�̶���Ӧ����1
    private iTextField gdplzjiTextField1;//�̶���������1
    private JComboBox kcjhffiTextField1;//���ƻ�����1
    private SACJTextField32 jmbjgiTextField1;//��Ŀ��۸�1
    private JComboBox zldwiTextField1;//������λ1
    private SACJTextField32 dwzliTextField1;//��λ����1
    private JComboBox tjdwiTextField1;//�����λ1
    private SACJTextField32 dwtjiTextField1;//��λ���1
    
    private JComboBox zzdmiTextField2;                //��֯����2
    private JComboBox mrcgyiTextField2;//Ĭ�ϲɹ�Ա���2
    private JComboBox jihyiTextField2;//�ƻ�Ա���2
    private iTextField tqq_gdiTextField2;//�̶���ǰ��2
//    private JComboBox kclbjiTextField2;//SAC_������2
    private JComboBox cwlbjiTextField2;//SAC_�������2
    private JComboBox jhlbjiTextField2;//SAC_�ƻ����2
    private JComboBox cplbjiTextField2;//SAC_��Ʒ���2
    private JComboBox zkciTextField2;//�ӿ��2
    private SACJTextField32 hwiTextField2;//��λ2
    private iTextField gdgytsiTextField2;//�̶���Ӧ����2
    private iTextField gdplzjiTextField2;//�̶���������2
    private JComboBox kcjhffiTextField2;//���ƻ�����2
    private SACJTextField32 jmbjgiTextField2;//��Ŀ��۸�2
    private JComboBox zldwiTextField2;//������λ2
    private SACJTextField32 dwzliTextField2;//��λ����2
    private JComboBox tjdwiTextField2;//�����λ2
    private SACJTextField32 dwtjiTextField2;//��λ���2
    private String[] sfyxwl_str = {"N","Y"};
    private HashMap<String, Object> component_map = new HashMap<String, Object>();
    private String Yes = "Y";
    private String No = "N";
    private String str1=null;   //��֯����1
    private String str2=null;   //��֯����2
    private String[] kc_str=null;  		 //������
    private String[] jhybh_str=null;	 //�ƻ�Ա���
    private String[] zzdm_str =null;
    private String[] wlzt_str =null;
    private String[] mrcgy_str = null;
    private String[] kclbj_str = null;
    private String[] cwlbj_str = null;
    private String[] jhlbj_str = null;
    private String[] cplbj_str = null;
    private String[] zkc_str = null;
    private String[] kcjhff_str = null;
    private String[] dw_str = null;
    private String[] wlmb_str = null;
    public DefaultTableModel tableModel = null;
    private String allow_num = "[0-9]";
    private SACMyDocument doc_gdtqq = null;
    private SACMyDocument doc_gdgyts = null;
    private SACMyDocument doc_gdplzj = null;
    private String attri1 = "s4Passing_State";
    private String attri2 = "s4Primary_Unit_of_M";
    private String attri3 = "s4Wpromaterials";
    private String attri4 = "s4Inventory_Item";
    private String cdzt_str = "";//����״̬��ֵ
    private Color color;
    
    private boolean is_first=false;
    private boolean is_first0=false;//��֤��֯����һ�����ӿ��ǰִ��
    
    public AttriMaintainceForm(TCComponentForm tccomponentform) throws Exception {
    	super(tccomponentform);
		form = tccomponentform;
		tcsession = (TCSession) form.getSession();
		font=new Font("����",Font.BOLD,12);
		cdzt_str = form.getProperty(attri1).toString();
		System.out.println("cdzt_str===>:"+cdzt_str);
		String[] all_str = tcsession.getPreferenceService().getStringArray(4, "SAC_Attri_Lov");
		if(all_str == null || all_str.length == 0){
			MessageBox.post("δ������ѡ�SAC_Attri_Lov", "��ʾ", MessageBox.INFORMATION);
			return;
		}else{
			//��֯����LOV
			String s_zzdm = all_str[0];
			String zzdm[] = null;
			zzdm = s_zzdm.split(":")[1].split(",");
			zzdm_str = new String[zzdm.length+1];
			for (int i = 0; i < zzdm.length; i++) {
				zzdm_str[0] = "";
				zzdm_str[i+1] = zzdm[i];
			}
			//����״̬LOV
			String s_wlzt = all_str[1];
			String wlzt[] = null;
			wlzt = s_wlzt.split(":")[1].split(",");
			wlzt_str = new String[wlzt.length+1];
			for (int i = 0; i < wlzt.length; i++) {
				wlzt_str[0] = "";
				wlzt_str[i+1] = wlzt[i];
			}
			//Ĭ�ϲɹ�ԱLOV
			String s_mrcgy = all_str[2];
			String mrcgy[] = null;
			mrcgy = s_mrcgy.split(":")[1].split(",");
			mrcgy_str = new String[mrcgy.length+1];
			for (int i = 0; i < mrcgy.length; i++) {
				mrcgy_str[0] = "";
				mrcgy_str[i+1] = mrcgy[i];
			}
			//SAC_������LOV
			String s_kclbj = all_str[3];
			String kclbj[] = null;
			kclbj = s_kclbj.split(":")[1].split(",");
			kclbj_str = new String[kclbj.length+1];
			for (int i = 0; i < kclbj.length; i++) {
				kclbj_str[0] = "";
				kclbj_str[i+1] = kclbj[i];
			}
			//SAC_�������LOV
			String s_cwlbj = all_str[4];
			String cwlbj[] = null;
			cwlbj = s_cwlbj.split(":")[1].split(",");
			cwlbj_str = new String[cwlbj.length+1];
			for (int i = 0; i < cwlbj.length; i++) {
				cwlbj_str[0] = "";
				cwlbj_str[i+1] = cwlbj[i];
			}
			//SAC_�ƻ����LOV
			String s_jhlbj = all_str[5];
			String jhlbj[] = null;
			jhlbj = s_jhlbj.split(":")[1].split(",");
			jhlbj_str = new String[jhlbj.length+1];
			for (int i = 0; i < jhlbj.length; i++) {
				jhlbj_str[0] = "";
				jhlbj_str[i+1] = jhlbj[i];
			}
			//SAC_��Ʒ���LOV
			String s_cplbj = all_str[6];
			String cplbj[] = null;
			cplbj = s_cplbj.split(":")[1].split(",");
			cplbj_str = new String[cplbj.length+1];
			for (int i = 0; i < cplbj.length; i++) {
				cplbj_str[0] = "";
				cplbj_str[i+1] = cplbj[i];
			}
			//�ӿ��LOV
			String s_zkc = all_str[7];
			String zkc[] = null;
			zkc = s_zkc.split(":")[1].split(",");
			zkc_str = new String[zkc.length+1];
			for (int i = 0; i < zkc.length; i++) {
				zkc_str[0] = "";
				zkc_str[i+1] = zkc[i];
			}
			//���ƻ�����LOV
			String s_kcjhff = all_str[8];
			String kcjhff[] = null;
			kcjhff = s_kcjhff.split(":")[1].split(",");
			kcjhff_str = new String[kcjhff.length+1];
			for (int i = 0; i < kcjhff.length; i++) {
				kcjhff_str[0] = "";
				kcjhff_str[i+1] = kcjhff[i];
			}
			//��Ҫ��λLOV
			String s_dw = all_str[9];
			String dw[] = null;
			dw = s_dw.split(":")[1].split(",");
			dw_str = new String[dw.length+1];
			for (int i = 0; i < dw.length; i++) {
				dw_str[0] = "";
				dw_str[i+1] = dw[i];
			}
			//����ģ��LOV
			String s_wlmb = all_str[10];
			String wlmb[] = null;
			wlmb = s_wlmb.split(":")[1].split(";");
			wlmb_str = new String[wlmb.length+1];
			for (int i = 0; i < wlmb.length; i++) {
				wlmb_str[0] = "";
				wlmb_str[i+1] = wlmb[i];
			}
			//�ƻ�Ա���
			String s_jhybh=all_str[11];
			String jhybh[]=null;
			jhybh= s_jhybh.split(":")[1].split(",");
			jhybh_str=new String[jhybh.length+1];
			for (int i = 0; i < jhybh.length; i++) {
				jhybh_str[0] = "";
				//System.out.println(wlmb_str[i]);
				jhybh_str[i+1] = jhybh[i];
			}
		}
		//�����ǶԿ������������޸�
		String[] kc_str1 = tcsession.getPreferenceService().getStringArray(4, "SAC_KC_Lov");
		if(kc_str1 == null || kc_str1.length == 0){
			MessageBox.post("δ������ѡ�SAC_KC_Lov", "��ʾ", MessageBox.INFORMATION);
			return;
		}else{
			//������
			String  s_kclb=kc_str1[0];
			String  kclb[]=null;
			kclb=s_kclb.split(":")[1].split(",");
			kc_str=new String[kclb.length+1];
			for (int i = 0; i < kclb.length; i++) {
				kc_str[0] = "";
				//System.out.println(wlmb_str[i]);
				kc_str[i+1] = kclb[i];
			}
		}
		optionKeys = getTCPreferenceArray(tcsession, preferenceName);
		if(optionKeys == null || optionKeys.length == 0){
			MessageBox.post("δ������ѡ�SAC_AMF", "��ʾ", MessageBox.INFORMATION);
			return;
		}
		initializeUI();
		if("Y".equals(cdzt_str)){
			zydwiTextField.getComponent(0).removeMouseListener(zydwiTextField.getMouseListeners()[0]);
			zydwiTextField.removeMouseListener(zydwiTextField.getMouseListeners()[0]);
			sfyxwliTextField.getComponent(0).removeMouseListener(sfyxwliTextField.getMouseListeners()[0]);
			sfyxwliTextField.removeMouseListener(sfyxwliTextField.getMouseListeners()[0]);
		}
		loadForm();
    }
    /*
     * Form��������
     * 
     * */
    public void loadForm() throws TCException {
    	try {
    		formProperties = form.getAllFormProperties();
    		int k = formProperties.length;
    		for (int i = 0; i < k; i++) {
    			String str = formProperties[i].getPropertyName();
    		/*	if(formProperties[i].getPropertyName().equals("s4tissue15")){
    				str="s4Childstock1";
    			};
    			if(formProperties[i].getPropertyName().equals("s4Childstock1")){
    				str="s4tissue15";
    			}
    		*/
    			if(formProperties[i].getPropertyName().equals("s4Childstock" )&&is_first==false){
    				str="s4tissue14";
    			}
    			if(formProperties[i].getPropertyName().equals("s4tissue14")&&is_first==true){
    				str="s4Childstock";
    			};

    			if(formProperties[i].getPropertyName().equals("s4Childstock1")&&is_first0==false){
    				str="s4tissue15";
    			}
    			if(formProperties[i].getPropertyName().equals("s4tissue15")&&is_first0==true){
    				str="s4Childstock1";
    			};
    			if(str.equals("s4Materialt")){
    				if(formProperties[i].getStringValue()==null ||"".equals(formProperties[i].getStringValue()))
    				{}
    				else
    					wlmbiTextField.setSelectedItem(formProperties[i].getStringValue());	
    			} else if(str.equals("s4Mdescription")){
    				wlmsiTextField.setText(formProperties[i].getStringValue());	
    			} else if(str.equals("s4Item_Status")){
    				wlztiTextField.setSelectedItem(formProperties[i].getStringValue());	
    			} else if(str.equals("s4vendor")){
    				sccsiTextField.setText(formProperties[i].getStringValue());	
    			} else if(str.equals("s4contact_maker")){
    				dhdmiTextField.setText(formProperties[i].getStringValue());	
    			} else if(str.equals("s4Supply_vol")){
    				dydyiTextField.setText(formProperties[i].getStringValue());	
    			} else if(str.equals("s4CT_current")){
    				ctdianliTextField.setText(formProperties[i].getStringValue());	
    			} /*else if(str.equals("s4Inventory_Item")){
    				if(Yes.equals(formProperties[i].getStringValue())){
    					kcwliTextField.setSelected(true);
    				}else{
    					kcwliTextField.setSelected(false);
    				}
    			} */else if(str.equals("s4Primary_Unit_of_M")){
    				zydwiTextField.setSelectedItem(formProperties[i].getStringValue());	
    			} else if(str.equals("s4Allow_Description_U")){
    				if(Yes.equals(formProperties[i].getStringValue())){
    					yxgxsmiTextField.setSelected(true);
    				}else{
    					yxgxsmiTextField.setSelected(false);
    				}
    			} else if(str.equals("s4opernumber")){
    				gxhiTextField.setText(formProperties[i].getStringValue());	
    			} else if(str.equals("s4Wpromaterials")){
    				sfyxwliTextField.setSelectedItem(formProperties[i].getStringValue());	
    			} else if(str.equals("s4tissue14")){	                   
    				if(!"Y".equals(cdzt_str)){
    					is_first=true;
    						if(form.getTCProperty("s4tissue14").getStringValue()==null ||"".equals(form.getTCProperty("s4tissue14").getStringValue()))
    	    				{}
    	    				else
    						   zzdmiTextField1.setSelectedItem(form.getTCProperty("s4tissue14").getStringValue());
//    						zzdmiTextField1.setSelectedItem(form.getTCProperty("s4tissue14").getStringValue());
    				}
    			} else if(str.equals("s4Default_Buyer")){
    				if(!"Y".equals(cdzt_str)){
    					mrcgyiTextField1.setSelectedItem(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4Planner")){                         //�ƻ�Ա���
    				if(!"Y".equals(cdzt_str)){
    					System.out.println("formProperties[i]==>"+formProperties[i].getStringValue());
    					String str1=formProperties[i].getStringValue();
    					String str2=str1.split("\\*")[0];
    					System.out.println("str2==>"+str2);
    					jihyiTextField1.setEditable(true);
    					jihyiTextField1.setSelectedItem(str2);	
    					jihyiTextField1.setEditable(false);
    				}
    			} else if(str.equals("s4Fixed_Lead_Time")){
    				if(!"Y".equals(cdzt_str)){
    					tqq_gdiTextField1.setText(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4SAC_Inventory_c")){
    			//	if(!"Y".equals(cdzt_str)){
    					//kclbjiTextField1.setSelectedItem(formProperties[i].getStringValue().split("\\*")[0]);	
    					kclbjiTextField1.setEditable(true);
    					kclbjiTextField1.setSelectedItem(formProperties[i].getStringValue());
    					kclbjiTextField1.setEditable(false);
    			//	}
    			
    			} else if(str.equals("s4SAC_Financial_c")){
    				if(!"Y".equals(cdzt_str)){										//�Բ�����𼯽��е��޸�
    				//	cwlbjiTextField1.setSelectedItem(formProperties[i].getStringValue());	
    					if((formProperties[i].getStringValue()==null)||"".equals(formProperties[i].getStringValue()))
    					{
    						cwlbjiTextField1.setEditable(true);
    						cwlbjiTextField1.setSelectedItem("01.0");
    						cwlbjiTextField1.setEditable(false);
    					}
    					else
    					{
    						//cwlbjiTextField1.setSelectedItem(formProperties[i].getStringValue().split("\\*")[0]);	
    						cwlbjiTextField1.setEditable(true);
    						cwlbjiTextField1.setSelectedItem(formProperties[i].getStringValue());
    						cwlbjiTextField1.setEditable(false);
    					}
    				}
    			} else if(str.equals("s4SAC_Plan_c")){								//�Լƻ���𼯽��е��޸�
    				if(!"Y".equals(cdzt_str)){												
    					//jhlbjiTextField1.setSelectedItem(formProperties[i].getStringValue());
    					
    					if((formProperties[i].getStringValue()==null)||"".equals(formProperties[i].getStringValue()))
    					{
    						if(form.getType().equals("S4DQDMRevisionMaster")||form.getType().equals("S4RKDMRevisionMaster")){
    						jhlbjiTextField1.setEditable(true);
    						jhlbjiTextField1.setSelectedItem("MZ0.W0");
    						jhlbjiTextField1.setEditable(false);
    					     }
    					}
    					else
    					{
    						jhlbjiTextField1.setEditable(true);
    						jhlbjiTextField1.setSelectedItem(formProperties[i].getStringValue().split("\\*")[0]);	
    						jhlbjiTextField1.setEditable(false);
    					}
    					
    				}
    			} else if(str.equals("s4SAC_Pro_c")){
    				if(!"Y".equals(cdzt_str)){
    					cplbjiTextField1.setEditable(true);
    					cplbjiTextField1.setSelectedItem(formProperties[i].getStringValue());	
    					cplbjiTextField1.setEditable(false);
    				}
    			} else if(str.equals("s4Childstock")){
    				if(!"Y".equals(cdzt_str)){
    					System.out.println("********��֯һ�ӿ��*********"+form.getTCProperty("s4Childstock").getStringValue());
    					zkciTextField1.setEditable(true);
    					zkciTextField1.setSelectedItem(form.getTCProperty("s4Childstock").getStringValue());
    					zkciTextField1.setEditable(false);
    					System.out.println("********��֯һ�ӿ��11111*********"+form.getTCProperty("s4Childstock").getStringValue());
    				}
    			} else if(str.equals("s4cargo_s")){
    				if(!"Y".equals(cdzt_str)){
    					hwiTextField1.setText(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4Fixed_Days_Supply")){
    				if(!"Y".equals(cdzt_str)){
    					gdgytsiTextField1.setText(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4Fixed_Lot_Size_M")){
    				if(!"Y".equals(cdzt_str)){
    					gdplzjiTextField1.setText(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4Inventory_Planning_M")){
    				if(!"Y".equals(cdzt_str)){
    					kcjhffiTextField1.setSelectedItem(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4List_Price")){
    				if(!"Y".equals(cdzt_str)){
    					jmbjgiTextField1.setText(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4Weight_Unit_of_Mea")){
    				if(!"Y".equals(cdzt_str)){
    					zldwiTextField1.setSelectedItem(formProperties[i].getStringValue());	
    				}
    			}  else if(str.equals("s4Unit_Weight")){
    				if(!"Y".equals(cdzt_str)){
    					dwzliTextField1.setText(formProperties[i].getStringValue());	
    				}
    			}  else if(str.equals("s4Volume_Unit_of_Mea")){
    				if(!"Y".equals(cdzt_str)){
    					tjdwiTextField1.setSelectedItem(formProperties[i].getStringValue());	
    				}
    			}  else if(str.equals("s4Unit_Volume")){
    				if(!"Y".equals(cdzt_str)){
    					dwtjiTextField1.setText(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4tissue15")){
    				if(!"Y".equals(cdzt_str)){
    					is_first0=true;
    					zzdmiTextField2.setSelectedItem(form.getTCProperty("s4tissue15").getStringValue());	//��֯����2
    				}
    			} else if(str.equals("s4Default_Buyer1")){
    				if(!"Y".equals(cdzt_str)){
    					mrcgyiTextField2.setSelectedItem(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4Planner1")){
    				if(!"Y".equals(cdzt_str)){
    					jihyiTextField2.setEditable(true);
    					jihyiTextField2.setSelectedItem(formProperties[i].getStringValue());	
    					jihyiTextField2.setEditable(false);
    				}
    			} else if(str.equals("s4Fixed_Lead_Time1")){
    				if(!"Y".equals(cdzt_str)){
    					tqq_gdiTextField2.setText(formProperties[i].getStringValue());	
    				}
    			} /*else if(str.equals("s4SAC_Inventory_c1")){
    				if(!"Y".equals(cdzt_str)){
    					kclbjiTextField2.setEditable(true);
    					kclbjiTextField2.setSelectedItem(formProperties[i].getStringValue().split("\\*")[0]);	
    					kclbjiTextField2.setEditable(false);
    				}
    			}*/ else if(str.equals("s4SAC_Financial_c1")){                     //�Բ�����𼯽��е��޸�
    				if(!"Y".equals(cdzt_str)){
    					//cwlbjiTextField2.setSelectedItem(formProperties[i].getStringValue());	
    				/*	if((formProperties[i].getStringValue()==null)||"".equals(formProperties[i].getStringValue()))
    					{
    						cwlbjiTextField2.setEditable(true);
    						cwlbjiTextField2.setSelectedItem("01.0");
    						cwlbjiTextField2.setEditable(false);
    					}
    					else
    					{
    					*/	cwlbjiTextField2.setEditable(true);
    						cwlbjiTextField2.setSelectedItem(formProperties[i].getStringValue().split("\\*")[0]);
    						cwlbjiTextField2.setEditable(false);
    				/*	}*/
    				}
    			} else if(str.equals("s4SAC_Plan_c1")){
    				if(!"Y".equals(cdzt_str)){
    					//jhlbjiTextField2.setSelectedItem(formProperties[i].getStringValue());
    				/*	if((formProperties[i].getStringValue()==null)||"".equals(formProperties[i].getStringValue()))
    					{
    						jhlbjiTextField2.setEditable(true);
    						jhlbjiTextField2.setSelectedItem("MZ0.W0");
    						jhlbjiTextField2.setEditable(false);
    					}
    					else
    					{*/
    						jhlbjiTextField2.setEditable(true);
    						jhlbjiTextField2.setSelectedItem(formProperties[i].getStringValue().split("\\*")[0]);
    						jhlbjiTextField2.setEditable(false);
    				/*	}*/
    				
    				}
    			} else if(str.equals("s4SAC_Pro_c1")){
    				if(!"Y".equals(cdzt_str)){
    					cplbjiTextField2.setEditable(true);
    					cplbjiTextField2.setSelectedItem(formProperties[i].getStringValue());	
    					cplbjiTextField2.setEditable(false);
    				}
    			} else if(str.equals("s4Childstock1")){
    				if(!"Y".equals(cdzt_str)){    					
    					System.out.println("********��֯���ӿ��*********"+form.getTCProperty("s4Childstock1").getStringValue());
    					zkciTextField2.setEditable(true);
    					zkciTextField2.setSelectedItem(form.getTCProperty("s4Childstock1").getStringValue());
    					System.out.println("********��֯���ӿ��22222*********"+zkciTextField2.getSelectedItem().toString());
    					zkciTextField2.setEditable(false);
    				}
    			} else if(str.equals("s4cargo_s1")){
    				if(!"Y".equals(cdzt_str)){
    					hwiTextField2.setText(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4Fixed_Days_Supply1")){
    				if(!"Y".equals(cdzt_str)){
    					gdgytsiTextField2.setText(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4Fixed_Lot_Size_M1")){
    				if(!"Y".equals(cdzt_str)){
    					gdplzjiTextField2.setText(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4Inventory_Planning_M1")){
    				if(!"Y".equals(cdzt_str)){
    					kcjhffiTextField2.setSelectedItem(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4List_Price1")){
    				if(!"Y".equals(cdzt_str)){
    					jmbjgiTextField2.setText(formProperties[i].getStringValue());	
    				}
    			} else if(str.equals("s4Weight_Unit_of_Mea1")){
    				if(!"Y".equals(cdzt_str)){
    					zldwiTextField2.setSelectedItem(formProperties[i].getStringValue());	
    				}
    			}  else if(str.equals("s4Unit_Weight1")){
    				if(!"Y".equals(cdzt_str)){
    					dwzliTextField2.setText(formProperties[i].getStringValue());	
    				}
    			}  else if(str.equals("s4Volume_Unit_of_Mea1")){
    				if(!"Y".equals(cdzt_str)){
    					tjdwiTextField2.setSelectedItem(formProperties[i].getStringValue());	
    				}
    			}  else if(str.equals("s4Unit_Volume1")){
    				if(!"Y".equals(cdzt_str)){
    					dwtjiTextField2.setText(formProperties[i].getStringValue());	
    				}
    			}
    			else if(str.equals("s4Materialid")){
    				if(formProperties[i].getStringValue().equals("")||formProperties[i].getStringValue()==null){
    					try {
    						wlbmiTextField.setText(getItemId());
    					} catch (TCException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
    					}
    					else
    					wlbmiTextField.setText(formProperties[i].getStringValue());	
    					
    					wlbmiTextField.setEnabled(false);
    			}
    	        
    			
    			
    		
    		/*if("Y".equals(cdzt_str)){
    			if(zydwiTextField.getComponentCount()>0){
    				int a = zydwiTextField.getMouseListeners().length;
    				System.out.println("a->:"+a);
    				zydwiTextField.getComponent(0).removeMouseListener(zydwiTextField.getMouseListeners()[0]);
    				zydwiTextField.removeMouseListener(zydwiTextField.getMouseListeners()[0]);
    			}
    			zydwiTextField.getComponent(0).removeMouseListener(zydwiTextField.getMouseListeners()[0]);
    			zydwiTextField.removeMouseListener(zydwiTextField.getMouseListeners()[0]);
    			sfyxwliTextField.getComponent(0).removeMouseListener(sfyxwliTextField.getMouseListeners()[0]);
    			sfyxwliTextField.removeMouseListener(sfyxwliTextField.getMouseListeners()[0]);
    		}*/
    		
    		if (wlztiTextField.getSelectedItem().equals("")) {
    			wlztiTextField.setSelectedItem("��Ч");
    		}
    		}
    	} catch (TCException tcexception) {
    		throw tcexception;
    	}
    }
    
    @Override
    public boolean isFormSavable(boolean arg0) {
    	
    	Vector<String> vec_t = new Vector<String>();
		int size = component_map.size();
		Iterator iterator = component_map.keySet().iterator();
		while (iterator.hasNext()) {
			String info_name = (String) iterator.next();
			Object value = component_map.get(info_name);
			if (value != null) {
				String jcomponent_type = value.getClass().toString();
				if (jcomponent_type.indexOf("SACJTextField32") > 0) {
					SACJTextField32 textfield = (SACJTextField32) value;
					String str_com = textfield.getText().toString();
					if(str_com == null || "".equals(str_com)){
						vec_t.add(info_name);
					}
				} else if(jcomponent_type.indexOf("JComboBox") > 0){
					JComboBox combomx = (JComboBox) value;
					String str_com = combomx.getSelectedItem().toString();
					if(str_com == null || "".equals(str_com)){
						vec_t.add(info_name);
					}
				} else if(jcomponent_type.indexOf("SACJTextField160") > 0){
					SACJTextField160 textfield = (SACJTextField160) value;
					String str_com = textfield.getText().toString();
					if(str_com == null || "".equals(str_com)){
						vec_t.add(info_name);
					}
				}
			}
		}
		
		if(vec_t.size()>0){
			MessageBox.post(vec_t+"��Щ���Բ���Ϊ��,����", "��ʾ",
					MessageBox.INFORMATION);
			return false;
		}
		if(form.getType().equals("S4DQDMRevisionMaster")||form.getType().equals("S4RKDMRevisionMaster")){
		String contactNum=null;
		try {
			contactNum =getContactNum();
		} catch (TCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(contactNum.equals("")||contactNum==null)
		{
			MessageBox.post("�뽫�����Ϸ��ڹ�����Ŀ�ļ�����,�����������Բ�����ͬ��,����ά������!", "��ʾ",MessageBox.INFORMATION);
			return false;
		}
		else if(contactNum.equals("ERROR"))
		{
			MessageBox.post("ͬһ�����̵�һ�������ϲ���������ڶ����ͬ�У����ֶ�����", "��ʾ",MessageBox.INFORMATION);
			return false;
		}
		else {
			try {
				setContactNum(contactNum);
			} catch (TCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		if (!isAllEmptyField()) {
			System.out.println("-----2-----");
			

			if (isEmpty(zzdmiTextField2.getSelectedItem().toString())
					|| isEmpty(cwlbjiTextField2.getSelectedItem().toString())
					|| isEmpty(jhlbjiTextField2.getSelectedItem().toString())) {

    	   		MessageBox.post("��֯2�д� *���ֶ�Ϊ������", "��ʾ", MessageBox.INFORMATION);
    	   		return false;
			}
		
		}
		/*if(!"Y".equals(cdzt_str)){
			//�ж���֯1����֯2�е���֯�����Ƿ���ͬ
			String zzdm1_str = zzdmiTextField1.getSelectedItem().toString();
			String zzdm2_str = zzdmiTextField2.getSelectedItem().toString();
			if(zzdm1_str.equals(zzdm2_str)){
				MessageBox.post("��֯1����֯2�е���֯������ͬ,����", "��ʾ",
						MessageBox.INFORMATION);
				return false;
				
			}
		}*/
		/*//�������޸ĵ�ʱ�����һЩ���Բ����޸�
		try {
			//����Ѿ����ݹ�ERP,��������֯����������ֵ�����޸�
			if("Y".equals(cdzt_str)){
				String zydw = form.getProperty(attri2).toString();
				System.out.println("zydw==---=>:"+zydw);
				String sfyxwl = form.getProperty(attri3).toString();
				System.out.println("sfyxwl==--sfyxwl-=>:"+sfyxwl);
				String zydw_combox = zydwiTextField.getSelectedItem().toString();
				System.out.println("zydw_combox---->:"+zydw_combox);
				String sfyxwl_combox = sfyxwliTextField.getSelectedItem().toString();
				System.out.println("sfyxwl_combox--====-->:"+sfyxwl_combox);
				String kcwl = form.getProperty(attri4).toString();
				boolean kcwl_flag = false;
				if("Y".equals(kcwl)){
					kcwl_flag = true;
				}
				boolean kcwl_checkbox = kcwliTextField.isSelected();
				String kcwl_flag = "";
				if(kcwl_checkbox){
					kcwl_flag = "Y";
				}else{
					kcwl_flag = "N";
				}
				if(!zydw.equals(zydw_combox) || !sfyxwl.equals(sfyxwl_combox) || !kcwl.equals(kcwl_flag)){
					MessageBox.post("�����޸���Ҫ��λ�����Ƿ�ԭ�����ϻ��߿�����ϵ�ֵ,����", "��ʾ",
							MessageBox.INFORMATION);
					return false;
				}
    		}
			
		} catch (TCException e) {
			e.printStackTrace();
		}*/
    	
    	return super.isFormSavable(arg0);
    	
    }
    
    
    /*
     * Form��������
     * 
     * */
    public void saveForm() {
    	
    	try {

 //   		String contactNum=getContactNum();
 //  		System.out.println("************��ͬ��***************"+contactNum);
 //   		if(contactNum.equals("")||contactNum==null)
 //   		{
 //   			MessageBox.post("�����ϲ��ڹ�����Ŀ�ļ����£������к�ͬ��", "��ʾ",MessageBox.INFORMATION);
 //   			return;
 //   		}
    		
    		int k = formProperties.length;
			for (int i = 0; i < k; i++) {
				String str = formProperties[i].getPropertyName();
				if(str.equals("s4Materialt")){
					formProperties[i].setStringValueData(wlmbiTextField.getSelectedItem().toString());
    			} else if(str.equals("s4Mdescription")){
    				if(form.getType().equals("S4DQDMRevisionMaster")||form.getType().equals("S4RKDMRevisionMaster")){
    				      if(wlmsiTextField.getText().endsWith("("+getContactNum()+")"))
    				         {
    					      formProperties[i].setStringValueData(wlmsiTextField.getText());
    				         }
    				      else
    				        formProperties[i].setStringValueData(wlmsiTextField.getText()+"("+getContactNum()+")");
    				}
    				else     
    					formProperties[i].setStringValueData(wlmsiTextField.getText());
    			} else if(str.equals("s4Item_Status")){
    				formProperties[i].setStringValueData(wlztiTextField.getSelectedItem().toString());
    			} else if(str.equals("s4vendor")){
    				formProperties[i].setStringValueData(sccsiTextField.getText());
    			} else if(str.equals("s4contact_maker")){
    				formProperties[i].setStringValueData(dhdmiTextField.getText());
    			} else if(str.equals("s4Supply_vol")){
    				formProperties[i].setStringValueData(dydyiTextField.getText());
    			} else if(str.equals("s4CT_current")){
    				formProperties[i].setStringValueData(ctdianliTextField.getText());
    			} /*else if(str.equals("s4Inventory_Item")){
    				if(kcwliTextField.isSelected()){
    					formProperties[i].setStringValueData(Yes);
    				}else{
    					formProperties[i].setStringValueData(No);
    				}
    			} */else if(str.equals("s4Primary_Unit_of_M")){
    				formProperties[i].setStringValueData(zydwiTextField.getSelectedItem().toString());
    			} else if(str.equals("s4Allow_Description_U")){
    				if(yxgxsmiTextField.isSelected()){
    					formProperties[i].setStringValueData(Yes);
    				}else{
    					formProperties[i].setStringValueData(No);
    				}
    			} else if(str.equals("s4opernumber")){
    				formProperties[i].setStringValueData(gxhiTextField.getText());
    			} else if(str.equals("s4Wpromaterials")){
    				formProperties[i].setStringValueData(sfyxwliTextField.getSelectedItem().toString());
    			} else if(str.equals("s4tissue14")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(zzdmiTextField1.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4Default_Buyer")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(mrcgyiTextField1.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4Planner")){
    				if(!"Y".equals(cdzt_str)){
    					if(jihyiTextField1.getSelectedItem()==null){
    					formProperties[i].setStringValueData("");
					}
					else
    					formProperties[i].setStringValueData(jihyiTextField1.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4Fixed_Lead_Time")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(tqq_gdiTextField1.getText());
    				}
    			} else if(str.equals("s4SAC_Inventory_c")){
    				if(!"Y".equals(cdzt_str)){
    					System.out.println("22=======>:"+kclbjiTextField1.getSelectedItem().toString());
    					System.out.println("33333333333333=======>:"+kclbjiTextField1.getSelectedItem().toString().getBytes().length);
    					formProperties[i].setStringValueData(kclbjiTextField1.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4SAC_Financial_c")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(cwlbjiTextField1.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4SAC_Plan_c")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(jhlbjiTextField1.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4SAC_Pro_c")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(cplbjiTextField1.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4Childstock")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(zkciTextField1.getSelectedItem().toString());
    				}
    			} /*else if(str.equals("s4cargo_s")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(hwiTextField1.getText());
    				}
    			} */else if(str.equals("s4Fixed_Days_Supply")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(gdgytsiTextField1.getText());
    				}
    			} else if(str.equals("s4Fixed_Lot_Size_M")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(gdplzjiTextField1.getText());
    				}
    			} else if(str.equals("s4Inventory_Planning_M")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(kcjhffiTextField1.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4List_Price")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(jmbjgiTextField1.getText());
    				}
    			} else if(str.equals("s4Weight_Unit_of_Mea")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(zldwiTextField1.getSelectedItem().toString());
    				}
    			}  else if(str.equals("s4Unit_Weight")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(dwzliTextField1.getText());
    				}
    			}  else if(str.equals("s4Volume_Unit_of_Mea")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(tjdwiTextField1.getSelectedItem().toString());
    				}
    			}  else if(str.equals("s4Unit_Volume")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(dwtjiTextField1.getText());
    				}
    			}  else if(str.equals("s4tissue15")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(zzdmiTextField2.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4Default_Buyer1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(mrcgyiTextField2.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4Planner1")){
    				if(!"Y".equals(cdzt_str)){
    					if(jihyiTextField2.getSelectedItem()==null){
    						formProperties[i].setStringValueData("");
    					}
    					else
    					formProperties[i].setStringValueData(jihyiTextField2.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4Fixed_Lead_Time1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(tqq_gdiTextField2.getText());
    				}
    			}/* else if(str.equals("s4SAC_Inventory_c1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(kclbjiTextField2.getSelectedItem().toString());
    				}
    			}*/ else if(str.equals("s4SAC_Financial_c1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(cwlbjiTextField2.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4SAC_Plan_c1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(jhlbjiTextField2.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4SAC_Pro_c1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(cplbjiTextField2.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4Childstock1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(zkciTextField2.getSelectedItem().toString());
    				}
    			} /*else if(str.equals("s4cargo_s1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(hwiTextField2.getText());
    				}
    			} */else if(str.equals("s4Fixed_Days_Supply1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(gdgytsiTextField2.getText());
    				}
    			} else if(str.equals("s4Fixed_Lot_Size_M1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(gdplzjiTextField2.getText());
    				}
    			} else if(str.equals("s4Inventory_Planning_M1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(kcjhffiTextField2.getSelectedItem().toString());
    				}
    			} else if(str.equals("s4List_Price1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(jmbjgiTextField2.getText());
    				}
    			} else if(str.equals("s4Weight_Unit_of_Mea1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(zldwiTextField2.getSelectedItem().toString());
    				}
    			}  else if(str.equals("s4Unit_Weight1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(dwzliTextField2.getText());
    				}
    			}  else if(str.equals("s4Volume_Unit_of_Mea1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(tjdwiTextField2.getSelectedItem().toString());
    				}
    			}  else if(str.equals("s4Unit_Volume1")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(dwtjiTextField2.getText());
    				}
    			}  
    			else if(str.equals("s4Materialid")){
    				if(!"Y".equals(cdzt_str)){
    					formProperties[i].setStringValueData(wlbmiTextField.getText());
    				}
    			}
				
			
			}
			form.setTCProperties(formProperties);
		} catch (Exception exception) {
				exception.printStackTrace();
				MessageBox.post(exception);
			}
   }
    
    private JPanel buildMainInformation(){
    	JPanel all_panel = new JPanel(new GridLayout(1, 0));
    	color = all_panel.getBackground();
    	TitledBorder titleBorder = BorderFactory.createTitledBorder("ά������");
    	titleBorder.setTitlePosition(2);
    	titleBorder.setTitleFont(font);
		all_panel.setBorder(titleBorder);
		all_panel.setLayout(new BoxLayout(all_panel, BoxLayout.Y_AXIS));
    	JPanel jpanel = new JPanel(new PropertyLayout());
    	JPanel jpanel2 = new JPanel(new PropertyLayout());
    	JPanel jpanel3 = new JPanel(new PropertyLayout());
    	//ly--
    	JLabel wlbm = new JLabel("���ϱ���");
    	wlbmiTextField =new SACJTextField32(35);
    	//--ly
    	JLabel wlms = new JLabel("��������*");
    	wlmsiTextField = new SACJTextField160(35);
    	/*//ly--
		SACDocument docwlmsiTextField=new SACDocument();
		docwlmsiTextField.setMaxLength(512);
		wlmsiTextField.setDocument(docwlmsiTextField);
		//--ly
*/    	component_map.put("��������", wlmsiTextField);
    	JLabel wlzt = new JLabel("����״̬*");
    	wlztiTextField = new JComboBox(wlzt_str);
    	wlztiTextField.setPreferredSize(new Dimension(216,23));
    	component_map.put("����״̬", wlztiTextField);
    	JLabel sccs = new JLabel("��������");
    	sccsiTextField = new SACJTextField32(35);
    	JLabel dhdm = new JLabel("��������");
    	dhdmiTextField = new SACJTextField32(35);
    	JLabel dydy = new JLabel("��Դ��ѹ");
    	dydyiTextField = new SACJTextField32(35);
    	JLabel kclbj = new JLabel("SAC_������*");
    	if(form.getType().equals("S4DQDMRevisionMaster")||form.getType().equals("S4RKDMRevisionMaster"))
    	{
    		kclbjiTextField1 = new JComboBox(kc_str);
    	}
    	else
    		kclbjiTextField1 = new JComboBox(kclbj_str);
    	kclbjiTextField1.setPreferredSize(new Dimension(216,23));
    	kclbjiTextField1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(kclbjiTextField1.getSelectedItem().toString().contains("*")){
					String s1=kclbjiTextField1.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					kclbjiTextField1.setEditable(true);
					kclbjiTextField1.setSelectedItem(s2);
					kclbjiTextField1.setEditable(false);
				}
			}
    		
    	});
    	component_map.put("SAC_������", kclbjiTextField1);
    	
    	JLabel ctdianl = new JLabel("CT����");
    	ctdianliTextField = new SACJTextField32(35);
    	JLabel kcwl = new JLabel("�������");
    	kcwliTextField = new JCheckBox();
    	JLabel zydw = new JLabel("��Ҫ��λ*");
    	zydwiTextField = new JComboBox(dw_str);
    	component_map.put("��Ҫ��λ", zydwiTextField);
    	zydwiTextField.setPreferredSize(new Dimension(216,23));
    	JLabel yxgxsm = new JLabel("�������˵��");
    	yxgxsmiTextField = new JCheckBox();
    	JLabel wlmb = new JLabel("����ģ��*");
    	wlmbiTextField = new JComboBox(wlmb_str);
    	wlmbiTextField.setPreferredSize(new Dimension(216,23));
    	wlmbiTextField.setSelectedItem("SAC�⹺��(��ʽ)");
    	component_map.put("����ģ��", wlmbiTextField);
    	JLabel gxh = new JLabel("�����");
    	gxhiTextField = new SACJTextField32(35);
    	JLabel sfyxwl = new JLabel("�Ƿ�ԭ������*");
    	sfyxwliTextField = new JComboBox(sfyxwl_str);
    	sfyxwliTextField.setPreferredSize(new Dimension(216,23));
    	
    	jpanel2.add("1.1.right",new JLabel(" "));
    	jpanel2.add("2.1.right",wlbm);
    	jpanel2.add("2.2.right",wlbmiTextField);
    	jpanel2.add("3.1.right",wlmb);
    	jpanel2.add("3.2.right",wlmbiTextField);
    	jpanel2.add("4.1.right",wlms);
    	jpanel2.add("4.2.right",wlmsiTextField);
    	jpanel2.add("5.1.right",wlzt);
    	jpanel2.add("5.2.right",wlztiTextField);
    	jpanel2.add("6.1.right",sccs);
    	jpanel2.add("6.2.right",sccsiTextField);
    	jpanel2.add("7.1.right",dhdm);
    	jpanel2.add("7.2.right",dhdmiTextField);
    	jpanel2.add("8.1.right",dydy);
    	jpanel2.add("8.2.right",dydyiTextField);
//   	jpanel2.add("8.1.right",kclbj);
//    	jpanel2.add("8.2.right",kclbjiTextField1);
    	
    	
    	jpanel3.add("1.1.right",new JLabel(" "));
    	jpanel3.add("2.1.right",ctdianl);
    	jpanel3.add("2.2.right",ctdianliTextField);
    	jpanel3.add("3.1.right",zydw);
    	jpanel3.add("3.2.right",zydwiTextField);
    	jpanel3.add("4.1.right",yxgxsm);
    	jpanel3.add("4.2.right",yxgxsmiTextField);
 //   	jpanel3.add("5.1.right",kcwl);
 //   	jpanel3.add("5.2.right",kcwliTextField);
    	jpanel3.add("5.1.right",kclbj);
    	jpanel3.add("5.2.right",kclbjiTextField1);
    	
    	jpanel3.add("6.1.right",gxh);
    	jpanel3.add("6.2.right",gxhiTextField);
    	jpanel3.add("7.1.right",sfyxwl);
    	jpanel3.add("7.2.right",sfyxwliTextField);
    	
    	jpanel.add("1.1.right",new JLabel("       "));
    	jpanel.add("1.2.right",jpanel2);
    	jpanel.add("1.3.right",new JLabel("       "));
    	jpanel.add("1.4.right",jpanel3);
    	jpanel.add("1.5.right",new JLabel("       "));
    	all_panel.add(jpanel);
    	return all_panel;
    }

    private JPanel buildZuZhi1Information(){                               //��֯����1
    	JPanel all_panel = new JPanel(new GridLayout(1, 0));
    	TitledBorder titleBorder = BorderFactory.createTitledBorder("ά������");
    	titleBorder.setTitlePosition(2);
    	titleBorder.setTitleFont(font);
		all_panel.setBorder(titleBorder);
		all_panel.setLayout(new BoxLayout(all_panel, BoxLayout.Y_AXIS));
    	JPanel jpanel = new JPanel(new PropertyLayout());
    	JPanel jpanel2 = new JPanel(new PropertyLayout());
    	JPanel jpanel3 = new JPanel(new PropertyLayout());
    	JLabel zzdm = new JLabel("��֯����*");
    	zzdmiTextField1 = new JComboBox(zzdm_str);
    	zzdmiTextField1.setPreferredSize(new Dimension(216,23));
    	if(form.getType().equals("S4DQDMRevisionMaster")||form.getType().equals("S4RKDMRevisionMaster")){
		      zzdmiTextField1.setSelectedItem("MZ0"); //��֯����1
		}
    	zzdmiTextField1.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
        	   	str1=zzdmiTextField1.getSelectedItem().toString();	
        	   	System.out.println("str1==>"+str1);
        	   	if(str1.equals(str2)&&str1!=null&&str2!=null&&!"".equals(str1)&&!"".equals(str2))
        	   	{
        	   		MessageBox.post("����֯2����֯�����ظ���", "��ʾ", MessageBox.INFORMATION);
        	   		zzdmiTextField1.setSelectedItem("");
        	   	}
        	   	
        	   	if (zzdmiTextField1.getSelectedItem().equals("")) {
		        	zkciTextField1.removeAllItems();
		        	zkciTextField1.addItem("");
		        	zkciTextField1.setSelectedIndex(0);
		        }
        	   	if(zzdmiTextField1.getSelectedItem().toString().trim().equals("P31")){
        	    //	jihyiTextField1 = new JComboBox(jhybh_str);    //�ƻ�Ա���
        	   		jihyiTextField1.removeAllItems();
        	    	for(int i=0;i<jhybh_str.length;i++){
        	    		jihyiTextField1.addItem(jhybh_str[i]);//�ƻ�Ա���
        	    	}
        	    }
        	    else 
        	    	{
        	    	jihyiTextField1.removeAllItems();
        	    	}
				for(int i=0;i<optionKeys.length;i++)
				{
					
					if(zzdmiTextField1.getSelectedItem().equals(optionKeys[i].split("=")[0].trim()))
					{
						zkciTextField1.removeAllItems();
						System.out.println("**********1******");
						zkciTextField1.addItem("");
						String[] kczz = optionKeys[i].split("=")[1].split(",");
						for (int j=0; j<kczz.length; j++) {  							
						//	System.out.println(kczz[j]);
							zkciTextField1.addItem( kczz[j].trim());
						}
						zkciTextField1.setSelectedIndex(0);
						System.out.println("**********1-end******");
						}
					else{}
						}
    		}
    	  }
   		);
    	
    	component_map.put("��֯����", zzdmiTextField1);
    	JLabel mrcgy = new JLabel("Ĭ�ϲɹ�Ա");
    	Arrays.sort(mrcgy_str);
    	mrcgyiTextField1 = new JAutoCompleteComboBox(mrcgy_str);
    	mrcgyiTextField1.setSelectedIndex(-1);
    	mrcgyiTextField1.setPreferredSize(new Dimension(216,23));
    	JLabel jihy = new JLabel("�ƻ�Ա���");
    	jihyiTextField1 = new JComboBox();    //�ƻ�Ա���
    	jihyiTextField1.setPreferredSize(new Dimension(216,23));
    	jihyiTextField1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jihyiTextField1.getSelectedItem()==null){}
				else if(jihyiTextField1.getSelectedItem().toString().contains("*")){
					String s1=jihyiTextField1.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					jihyiTextField1.setEditable(true);
					jihyiTextField1.setSelectedItem(s2);
					jihyiTextField1.setEditable(false);
				}
				
			}
    		
    		
    	});
 /*
    	jihyiTextField1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jihyiTextField1.getSelectedItem().toString().contains("*")){
					String s1=jihyiTextField1.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					jihyiTextField1.setEditable(true);
					jihyiTextField1.setSelectedItem(s2);
					jihyiTextField1.setEditable(false);
				}
			}
    		
    		
    	});
  */  	
    	JLabel tqq_gd = new JLabel("�̶���ǰ��");
    	tqq_gdiTextField1 = new iTextField(35);
    	doc_gdtqq = new SACMyDocument();
    	tqq_gdiTextField1.setDocument(doc_gdtqq);
    	doc_gdtqq.setMaxLength(32);
    	doc_gdtqq.setCharLimit(allow_num);		
    	JLabel cwlbj = new JLabel("SAC_�������*");
    	cwlbjiTextField1 = new JComboBox(cwlbj_str);
    	cwlbjiTextField1.setPreferredSize(new Dimension(216,23));
    	cwlbjiTextField1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cwlbjiTextField1.getSelectedItem().toString().contains("*")){
					String s1=cwlbjiTextField1.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					cwlbjiTextField1.setEditable(true);
					cwlbjiTextField1.setSelectedItem(s2);
					cwlbjiTextField1.setEditable(false);
				}
				
			}
    		
    	});
    	component_map.put("SAC_�������", cwlbjiTextField1);
    	JLabel jhlbj = new JLabel("SAC_�ƻ����*");
    	jhlbjiTextField1 = new JComboBox(jhlbj_str);
    	jhlbjiTextField1.setPreferredSize(new Dimension(216,23));
    	jhlbjiTextField1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jhlbjiTextField1.getSelectedItem().toString().contains("*")){
					String s1=jhlbjiTextField1.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					jhlbjiTextField1.setEditable(true);
					jhlbjiTextField1.setSelectedItem(s2);
					jhlbjiTextField1.setEditable(false);
				}
			}
    		
    	});
    	component_map.put("SAC_�ƻ����", jhlbjiTextField1);
    	JLabel cplbj = new JLabel("SAC_��Ʒ���");
    	cplbjiTextField1 = new JComboBox(cplbj_str);
    	cplbjiTextField1.setPreferredSize(new Dimension(216,23));
    	cplbjiTextField1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cplbjiTextField1.getSelectedItem().toString().contains("*")){
					String s1=cplbjiTextField1.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					cplbjiTextField1.setEditable(true);
					cplbjiTextField1.setSelectedItem(s2);
					cplbjiTextField1.setEditable(false);
				}
			}
    		
    	});
    	
    	JLabel zkc = new JLabel("��Ӧ�ӿ��");
    	zkciTextField1 = new JComboBox(zkc_str);
    	zkciTextField1.setSelectedItem("");
    	zkciTextField1.setPreferredSize(new Dimension(216,23));
    	zkciTextField1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(zkciTextField1.getSelectedItem()==null){}
				else if(zkciTextField1.getSelectedItem().toString().contains("*")){
					String s1=zkciTextField1.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					zkciTextField1.setEditable(true);
					zkciTextField1.setSelectedItem(s2);
					zkciTextField1.setEditable(false);
				}
			}
    	});

    	JLabel hw = new JLabel("��λ");
    	hwiTextField1 = new SACJTextField32(35);
    	hwiTextField1.setEnabled(false);
    	JLabel gdgyts = new JLabel("�̶���Ӧ����");
    	gdgytsiTextField1 = new iTextField(35);
    	doc_gdgyts = new SACMyDocument();
    	gdgytsiTextField1.setDocument(doc_gdgyts);
    	doc_gdgyts.setMaxLength(32);
    	doc_gdgyts.setCharLimit(allow_num);
    	JLabel gdplzj = new JLabel("�̶���������");
    	gdplzjiTextField1 = new iTextField(35);
    	doc_gdplzj = new SACMyDocument();
    	gdplzjiTextField1.setDocument(doc_gdplzj);
    	doc_gdplzj.setMaxLength(32);
    	doc_gdplzj.setCharLimit(allow_num);
    	JLabel kcjhff = new JLabel("���ƻ�����");
    	kcjhffiTextField1 = new JComboBox(kcjhff_str);
    	kcjhffiTextField1.setPreferredSize(new Dimension(216,23));
    	JLabel jmbjg = new JLabel("��Ŀ��۸�");
    	jmbjgiTextField1 = new SACJTextField32(35);
    	JLabel zldw = new JLabel("������λ");
    	zldwiTextField1 = new JComboBox(dw_str);
    	zldwiTextField1.setPreferredSize(new Dimension(216,23));
    	JLabel dwzl = new JLabel("��λ����");
    	dwzliTextField1 = new SACJTextField32(35);
    	JLabel tjdw = new JLabel("�����λ");
    	tjdwiTextField1 = new JComboBox(dw_str);
    	tjdwiTextField1.setPreferredSize(new Dimension(216,23));
    	JLabel dwtj = new JLabel("��λ���");
    	dwtjiTextField1 = new SACJTextField32(35);
    	
    	
    	
    	jpanel2.add("1.1.right",new JLabel(" "));
    	jpanel2.add("2.1.right",zzdm);
    	jpanel2.add("2.2.right",zzdmiTextField1);
    	jpanel2.add("3.1.right",mrcgy);
    	jpanel2.add("3.2.right",mrcgyiTextField1);
    	jpanel2.add("4.1.right",jihy);
    	jpanel2.add("4.2.right",jihyiTextField1);
    	jpanel2.add("5.1.right",tqq_gd);
    	jpanel2.add("5.2.right",tqq_gdiTextField1);
    	jpanel2.add("6.1.right",cwlbj);
    	jpanel2.add("6.2.right",cwlbjiTextField1);
    	jpanel2.add("7.1.right",jhlbj);
    	jpanel2.add("7.2.right",jhlbjiTextField1);
/*    	
    	jpanel2.add("8.1.right",cplbj);
    	jpanel2.add("8.2.right",cplbjiTextField1);
    	jpanel2.add("9.1.right",kcjhff);
    	jpanel2.add("9.2.right",kcjhffiTextField1);
*/    	
    	
    	jpanel3.add("1.1.right",new JLabel(" "));
    	jpanel3.add("2.1.right",gdgyts);
    	jpanel3.add("2.2.right",gdgytsiTextField1);
    	jpanel3.add("3.1.right",gdplzj);
    	jpanel3.add("3.2.right",gdplzjiTextField1);
    	jpanel3.add("4.1.right",jmbjg);
    	jpanel3.add("4.2.right",jmbjgiTextField1);
/*    	
    	jpanel3.add("5.1.right",zldw);
    	jpanel3.add("5.2.right",zldwiTextField1);
    	jpanel3.add("6.1.right",dwzl);
    	jpanel3.add("6.2.right",dwzliTextField1);
    	jpanel3.add("7.1.right",tjdw);
    	jpanel3.add("7.2.right",tjdwiTextField1);
    	jpanel3.add("8.1.right",dwtj);
    	jpanel3.add("8.2.right",dwtjiTextField1);
*/   	
    	jpanel3.add("5.1.right",cplbj);
    	jpanel3.add("5.2.right",cplbjiTextField1);
    	jpanel3.add("6.1.right",kcjhff);
    	jpanel3.add("6.2.right",kcjhffiTextField1);
    	jpanel3.add("7.1.right",zkc);
    	jpanel3.add("7.2.right",zkciTextField1);
    	
    	jpanel.add("1.1.right",new JLabel("       "));
    	jpanel.add("1.2.right",jpanel2);
    	jpanel.add("1.3.right",new JLabel("       "));
    	jpanel.add("1.4.right",jpanel3);
    	jpanel.add("1.5.right",new JLabel("       "));
    	all_panel.add(jpanel);
    	return all_panel;
    } 
    
    private JPanel buildZuZhi2Information(){                                 //��֯����2
    	JPanel all_panel = new JPanel(new GridLayout(1, 0));
    	TitledBorder titleBorder = BorderFactory.createTitledBorder("ά������");
    	titleBorder.setTitlePosition(2);
    	titleBorder.setTitleFont(font);
		all_panel.setBorder(titleBorder);
		all_panel.setLayout(new BoxLayout(all_panel, BoxLayout.Y_AXIS));
    	JPanel jpanel = new JPanel(new PropertyLayout());
    	JPanel jpanel2 = new JPanel(new PropertyLayout());
    	JPanel jpanel3 = new JPanel(new PropertyLayout());
    	JLabel zzdm = new JLabel("��֯����*");
    	zzdmiTextField2 = new JComboBox(zzdm_str);
//    	zzdmiTextField2.setSelectedIndex(0);
    	zzdmiTextField2.setPreferredSize(new Dimension(216,23));       //������Ľ������޸�
    	zzdmiTextField2.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
        	   	str2=zzdmiTextField2.getSelectedItem().toString();	
        	   	if(str2.equals(str1)&&str1!=null&&str2!=null&&!"".equals(str1)&&!"".equals(str2))
        	   	{
        	   		MessageBox.post("����֯1����֯�����ظ�!", "��ʾ", MessageBox.INFORMATION);
        	   		zzdmiTextField2.setSelectedItem("");
        	   	}
        	   	
        	    if (zzdmiTextField2.getSelectedItem().equals("")) {
		        	zkciTextField2.removeAllItems();
		        	zkciTextField2.addItem("");
		        	zkciTextField2.setSelectedIndex(0);
		        }
        		if(zzdmiTextField2.getSelectedItem().toString().trim().equals("P31")){
            	    //	jihyiTextField1 = new JComboBox(jhybh_str);    //�ƻ�Ա���
        			   jihyiTextField2.removeAllItems();
            	    	for(int i=0;i<jhybh_str.length;i++){
            	    		jihyiTextField2.addItem(jhybh_str[i]);//�ƻ�Ա���
            	    	}
            	    }
            	 else 
            	    {
            	    	jihyiTextField2.removeAllItems();
            	    }
        	   	
        		for(int i=0;i<optionKeys.length;i++)
				{	
					if(zzdmiTextField2.getSelectedItem().equals(optionKeys[i].split("=")[0].trim()))
					{
						zkciTextField2.removeAllItems();
						System.out.println("**********2******");
						zkciTextField2.addItem("");
						String[] kczz = optionKeys[i].split("=")[1].split(",");
						for (int j=0; j<kczz.length; j++) {
						//	System.out.println(kczz[j]);
							zkciTextField2.addItem( kczz[j].trim());
						}
						zkciTextField2.setSelectedIndex(0);
						System.out.println("**********2-end******"+zkciTextField2.getSelectedItem().toString());
						}
					else{}
						}					
					
    		}
    	  }
   		);
    	
    	
    	JLabel mrcgy = new JLabel("Ĭ�ϲɹ�Ա");
    	mrcgyiTextField2 = new JAutoCompleteComboBox(mrcgy_str);
    	Arrays.sort(mrcgy_str);
    	mrcgyiTextField2.setSelectedIndex(-1);
    	mrcgyiTextField2.setPreferredSize(new Dimension(216,23));
    	JLabel jihy = new JLabel("�ƻ�Ա���");
    	jihyiTextField2 = new JComboBox(jhybh_str);    //�ƻ�Ա���
    	jihyiTextField2.setPreferredSize(new Dimension(216,23));
    	jihyiTextField2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jihyiTextField2.getSelectedItem()==null){}
				else if(jihyiTextField2.getSelectedItem().toString().contains("*")){
					String s1=jihyiTextField2.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					jihyiTextField2.setEditable(true);
					jihyiTextField2.setSelectedItem(s2);
					jihyiTextField2.setEditable(false);
				}
			}   		
    	});
    	JLabel tqq_gd = new JLabel("�̶���ǰ��");
    	tqq_gdiTextField2 = new iTextField(35);
    	doc_gdtqq = new SACMyDocument();
    	tqq_gdiTextField2.setDocument(doc_gdtqq);
    	doc_gdtqq.setMaxLength(32);
    	doc_gdtqq.setCharLimit(allow_num);
/*    	JLabel kclbj = new JLabel("SAC_������*");
    	if(form.getType().equals("S4DQDMRevisionMaster")||form.getType().equals("S4RKDMRevisionMaster"))
    	{
    		kclbjiTextField2= new JComboBox(kc_str);
    	}
    	else
    		kclbjiTextField2 = new JComboBox(kclbj_str);
    	kclbjiTextField2.setPreferredSize(new Dimension(216,23));
    	kclbjiTextField2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(kclbjiTextField2.getSelectedItem().toString().contains("*")){
					String s1=kclbjiTextField2.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					kclbjiTextField2.setEditable(true);
					kclbjiTextField2.setSelectedItem(s2);
					kclbjiTextField2.setEditable(false);
				}
			}
    		
    	});
    	*/
    	JLabel cwlbj = new JLabel("SAC_�������*");
    	cwlbjiTextField2 = new JComboBox(cwlbj_str);
    	cwlbjiTextField2.setPreferredSize(new Dimension(216,23));
    	cwlbjiTextField2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cwlbjiTextField2.getSelectedItem().toString().contains("*")){
					String s1=cwlbjiTextField2.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					cwlbjiTextField2.setEditable(true);
					cwlbjiTextField2.setSelectedItem(s2);
					cwlbjiTextField2.setEditable(false);
				}
			}
    		
    	});
    	JLabel jhlbj = new JLabel("SAC_�ƻ����*");
    	jhlbjiTextField2 = new JComboBox(jhlbj_str);
    	jhlbjiTextField2.setPreferredSize(new Dimension(216,23));
    	jhlbjiTextField2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jhlbjiTextField2.getSelectedItem().toString().contains("*")){
					String s1=jhlbjiTextField2.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					jhlbjiTextField2.setEditable(true);
					jhlbjiTextField2.setSelectedItem(s2);
					jhlbjiTextField2.setEditable(false);
				}
			}
    		
    	});
    	JLabel cplbj = new JLabel("SAC_��Ʒ���");
    	cplbjiTextField2 = new JComboBox(cplbj_str);
    	cplbjiTextField2.setPreferredSize(new Dimension(216,23));
    	cplbjiTextField2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cplbjiTextField2.getSelectedItem().toString().contains("*")){
					String s1=cplbjiTextField2.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					cplbjiTextField2.setEditable(true);
					cplbjiTextField2.setSelectedItem(s2);
					cplbjiTextField2.setEditable(false);
				}
			}
    		
    	});
    	
    	JLabel zkc = new JLabel("��Ӧ�ӿ��");
    	zkciTextField2 = new JComboBox(zkc_str);
    	zkciTextField2.setSelectedItem("");
    	zkciTextField2.setPreferredSize(new Dimension(216,23));
    	zkciTextField2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(zkciTextField2.getSelectedItem()==null){}
				else if(zkciTextField2.getSelectedItem().toString().contains("*")){
					String s1=zkciTextField2.getSelectedItem().toString();
					String s2=s1.split("\\*")[0];
					zkciTextField2.setEditable(true);
					zkciTextField2.setSelectedItem(s2);
					zkciTextField2.setEditable(false);
				}
			}
    		
    	});
    	JLabel hw = new JLabel("��λ");
    	hwiTextField2 = new SACJTextField32(35);
    	hwiTextField2.setEnabled(false);
    	JLabel gdgyts = new JLabel("�̶���Ӧ����");
    	gdgytsiTextField2 = new iTextField(35);
    	doc_gdgyts = new SACMyDocument();
    	gdgytsiTextField2.setDocument(doc_gdgyts);
    	doc_gdgyts.setMaxLength(32);
    	doc_gdgyts.setCharLimit(allow_num);
    	JLabel gdplzj = new JLabel("�̶���������");
    	gdplzjiTextField2 = new iTextField(35);
    	doc_gdplzj = new SACMyDocument();
    	gdplzjiTextField2.setDocument(doc_gdplzj);
    	doc_gdplzj.setMaxLength(32);
    	doc_gdplzj.setCharLimit(allow_num);
    	JLabel kcjhff = new JLabel("���ƻ�����");
    	kcjhffiTextField2 = new JComboBox(kcjhff_str);
    	kcjhffiTextField2.setPreferredSize(new Dimension(216,23));
    	JLabel jmbjg = new JLabel("��Ŀ��۸�");
    	jmbjgiTextField2 = new SACJTextField32(35);
    	JLabel zldw = new JLabel("������λ");
    	zldwiTextField2 = new JComboBox(dw_str);
    	zldwiTextField2.setPreferredSize(new Dimension(216,23));
    	JLabel dwzl = new JLabel("��λ����");
    	dwzliTextField2 = new SACJTextField32(35);
    	JLabel tjdw = new JLabel("�����λ");
    	tjdwiTextField2 = new JComboBox(dw_str);
    	tjdwiTextField2.setPreferredSize(new Dimension(216,23));
    	JLabel dwtj = new JLabel("��λ���");
    	dwtjiTextField2 = new SACJTextField32(35);
    	
    	
    	
    	jpanel2.add("1.1.right",new JLabel(" "));
    	jpanel2.add("2.1.right",zzdm);
    	jpanel2.add("2.2.right",zzdmiTextField2);
    	jpanel2.add("3.1.right",mrcgy);
    	jpanel2.add("3.2.right",mrcgyiTextField2);
    	jpanel2.add("4.1.right",jihy);
    	jpanel2.add("4.2.right",jihyiTextField2);
    	jpanel2.add("5.1.right",tqq_gd);
    	jpanel2.add("5.2.right",tqq_gdiTextField2);
//    	jpanel2.add("6.1.right",kclbj);
//    	jpanel2.add("6.2.right",kclbjiTextField2);
    	jpanel2.add("6.1.right",cwlbj);
    	jpanel2.add("6.2.right",cwlbjiTextField2);
    	jpanel2.add("7.1.right",jhlbj);
    	jpanel2.add("7.2.right",jhlbjiTextField2);
/*    	
    	jpanel2.add("8.1.right",cplbj);
    	jpanel2.add("8.2.right",cplbjiTextField2);
    	jpanel2.add("9.1.right",kcjhff);
    	jpanel2.add("9.2.right",kcjhffiTextField2);
*/   	
    	
    	jpanel3.add("1.1.right",new JLabel(" "));
    	jpanel3.add("2.1.right",gdgyts);
    	jpanel3.add("2.2.right",gdgytsiTextField2);
    	jpanel3.add("3.1.right",gdplzj);
    	jpanel3.add("3.2.right",gdplzjiTextField2);
    	jpanel3.add("4.1.right",jmbjg);
    	jpanel3.add("4.2.right",jmbjgiTextField2);
/*    	
    	jpanel3.add("5.1.right",zldw);
    	jpanel3.add("5.2.right",zldwiTextField2);
    	jpanel3.add("6.1.right",dwzl);
    	jpanel3.add("6.2.right",dwzliTextField2);
    	jpanel3.add("7.1.right",tjdw);
    	jpanel3.add("7.2.right",tjdwiTextField2);
    	jpanel3.add("8.1.right",dwtj);
    	jpanel3.add("8.2.right",dwtjiTextField2);
*/
    	jpanel3.add("5.1.right",cplbj);
    	jpanel3.add("5.2.right",cplbjiTextField2);
    	jpanel3.add("6.1.right",kcjhff);
    	jpanel3.add("6.2.right",kcjhffiTextField2);
    	jpanel3.add("7.1.right",zkc);
    	jpanel3.add("7.2.right",zkciTextField2);
    	
    	jpanel.add("1.1.right",new JLabel("       "));
    	jpanel.add("1.2.right",jpanel2);
    	jpanel.add("1.3.right",new JLabel("       "));
    	jpanel.add("1.4.right",jpanel3);
    	jpanel.add("1.5.right",new JLabel("       "));
    	all_panel.add(jpanel);
    	return all_panel;
    }
    
    
    /*
     * Form�����ʼ��
     * 
     * */
    private void initializeUI() {
    	setLayout(new VerticalLayout(4, 4, 4, 4, 4));
    	JTabbedPane jtabbedPane = new JTabbedPane();
    	jtabbedPane.setTabPlacement(JTabbedPane.TOP);// ���ñ�ǩ�÷�λ��
    	jtabbedPane.setPreferredSize(new Dimension(1300,600));
//    	jtabbedPane.addTab("����֯", buildMainInformation());
//		jtabbedPane.addTab("��֯1", buildZuZhi1Information());
//		jtabbedPane.addTab("��֯2", buildZuZhi2Information());
    	if("Y".equals(cdzt_str)){
    		jtabbedPane.addTab("����֯", buildMainInformation());
    	}else{
    		jtabbedPane.addTab("����֯", buildMainInformation());
    		jtabbedPane.addTab("��֯1", buildZuZhi1Information());
    		jtabbedPane.addTab("��֯2", buildZuZhi2Information());
    	}
		add("top", jtabbedPane);

	}
    
    private String[] getTCPreferenceArray(TCSession tcSession, String preferenceName) {
		
		String[] preString = null;
	
		TCPreferenceService tcPreservice = tcSession.getPreferenceService();
		
		preString = tcPreservice.getStringArray(TCPreferenceService.TC_preference_site, preferenceName);

		return preString;
	}
	public String getContactNum() throws TCException {
	//	String[] temp=null;
		Vector<String> temp = new Vector<String>();
		AIFComponentContext[] aif = form.getPrimary();
		AIFComponentContext[] aif1 = null;
		AIFComponentContext[] aif2 = null;

		if (aif != null && aif.length > 0) {
			TCComponentItemRevision	ir = (TCComponentItemRevision) aif[0].getComponent();
			aif1 = ir.whereReferenced();
			if (aif1 != null && aif1.length > 0) {
			TCComponentItem	item = (TCComponentItem) aif1[0].getComponent();
			aif2 = item.whereReferenced();
			int j=0;
            if (aif2 != null && aif2.length > 0) {	
            //	temp=new String[aif2.length];
            	for(int i=0;i<aif2.length;i++){
                     TCComponentFolder folder = (TCComponentFolder) aif2[i].getComponent();
                     String s=folder.getTCProperty("gov_classification").getStringValue();
                     if(s.equals("")||s==null)
                      {}
                     else
                      { 
                     //temp[j]=s;
                     //j++;
                    	temp.add(s);
                      }
            	}
            }
			}
		}
		System.out.println("************��ȥ��ǰ************"+aif2.length);
		System.out.println("************��ȥ�պ�************"+temp.size());
		if(temp.size()==0) //����ֵȫΪ��
			return "";
		else if(temp.size()==1)//����ֵ��һ����Ϊ�գ�����ȫΪ��
			return temp.elementAt(0);
		else      //����ֵ�ж����Ϊ��
		{
		  for(int i=0;i<temp.size()-1;i++){
			if(temp.elementAt(i).trim().equals(temp.elementAt(i+1).trim())==false)
				return "ERROR";
		  }
		  return temp.elementAt(0);
		}
	}
	public void setContactNum(String contactNumber) throws TCException {
		AIFComponentContext[] aif = form.getPrimary();
		AIFComponentContext[] aif1 = null;

		if (aif != null && aif.length > 0) {
			TCComponentItemRevision	ir = (TCComponentItemRevision) aif[0].getComponent();
			if(ir.getProperty("gov_classification")==null||ir.getProperty("gov_classification").equals(""))
			  ir.setProperty("gov_classification", contactNumber);
			aif1 = ir.whereReferenced();
			if (aif1 != null && aif1.length > 0) {
			TCComponentItem	item = (TCComponentItem) aif1[0].getComponent();
			if(item.getProperty("gov_classification")==null||item.getProperty("gov_classification").equals(""))
			item.setProperty("gov_classification", contactNumber);
			}
	}
	}
	public String getItemId() throws TCException {
		AIFComponentContext[] aif = form.getPrimary();
		AIFComponentContext[] aif1 = null;

		if (aif != null && aif.length > 0) {
			TCComponentItemRevision	ir = (TCComponentItemRevision) aif[0].getComponent();;
			aif1 = ir.whereReferenced();
			if (aif1 != null && aif1.length > 0) {
			TCComponentItem	item = (TCComponentItem) aif1[0].getComponent();
		
			 System.out.println("item_id_________________"+item.getProperty("item_id"));
			 return item.getProperty("item_id");
			}
	}
		return null;
	}
	
	private boolean isAllEmptyField() {
		
		if (mrcgyiTextField2 == null
			|| tqq_gdiTextField2 == null
			|| cwlbjiTextField2 == null
			|| zzdmiTextField2 == null
			|| jhlbjiTextField2 == null
			|| zkciTextField2 == null
			|| gdgytsiTextField2 == null
			|| gdplzjiTextField2 == null
			|| kcjhffiTextField2 == null
			|| jmbjgiTextField2 == null
			|| zkciTextField2 == null) {
			
			return true;
		}
    	if (isEmpty(mrcgyiTextField2.getSelectedItem().toString())
    			&& isEmpty(tqq_gdiTextField2.getText())
    			&& isEmpty(zzdmiTextField2.getSelectedItem().toString())
    			&& isEmpty(cwlbjiTextField2.getSelectedItem().toString())
    			&& isEmpty(jhlbjiTextField2.getSelectedItem().toString())
    			&& isEmpty(zkciTextField2.getSelectedItem().toString())
    			&& isEmpty(gdgytsiTextField2.getText())
    			&& isEmpty(gdplzjiTextField2.getText())
    			&& isEmpty(kcjhffiTextField2.getSelectedItem().toString())
    			&& isEmpty(jmbjgiTextField2.getText()) 
    			&& isEmpty(zkciTextField2.getSelectedItem().toString())) {
    		return true;
    	}
    	return false;
    }
    
    private boolean isEmpty(String input) {
    	if (input == null || input.equals("")) {
    		return true;
    	}
    	else return false;
    }

}