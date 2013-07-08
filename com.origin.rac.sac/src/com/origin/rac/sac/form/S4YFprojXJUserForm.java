package com.origin.rac.sac.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.TableCellRenderer;

import cn.com.origin.util.SACGridBagLaiYRenderer;
import cn.com.origin.util.SACTextAreaEditor1280;
import cn.com.origin.util.SACTextFieldEditorLov;
import cn.com.origin.util.SACDocument1280;
import cn.com.origin.util.SACDocument256;
import cn.com.origin.util.SACDocument32;
import cn.com.origin.util.SACDocument4000;
import cn.com.origin.util.SACGridBagRenderer;
import cn.com.origin.util.SACJTextField128;
import cn.com.origin.util.SACJTextField1280;
import cn.com.origin.util.SACJTextField32;
import cn.com.origin.util.SACJTextField64;
import cn.com.origin.util.SACTextFieldEditor1280;
import cn.com.origin.util.SACTextFieldEditor32;
import cn.com.origin.util.SACTextFieldEditor64;
import cn.com.origin.util.SACTextFieldEditorSJ;
import cn.com.origin.util.SAC_TableCellRenderer;


import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.commands.namedreferences.ImportFilesOperation;
import com.teamcenter.rac.commands.open.OpenFormDialog;
import com.teamcenter.rac.form.AbstractTCForm;
import com.teamcenter.rac.kernel.ListOfValuesInfo;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentDataset;
import com.teamcenter.rac.kernel.TCComponentDatasetType;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentFormType;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemType;
import com.teamcenter.rac.kernel.TCComponentListOfValues;
import com.teamcenter.rac.kernel.TCComponentListOfValuesType;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCFormProperty;
import com.teamcenter.rac.kernel.TCProperty;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.DateButton;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.PropertyLayout;                               //���ֵ��µķ���
import com.teamcenter.rac.util.iTextArea;

public class S4YFprojXJUserForm extends AbstractTCForm {

	private static final long serialVersionUID = 1L;
	private TCComponentForm form = null;
	private TCSession session = null;
	private TCProperty prjName = null;//����Ŀ����
	//private TCProperty prjId = null;//�������
	// private TCProperty prjClass = null;//���Ʋ���
	// private TCProperty prjMan = null;//��Ŀ������
	private TCProperty prjContent = null;// ��Ŀ����
	private TCProperty adoStand = null;// ���ñ�׼
	private TCProperty startT = null;// ��ʼʱ��
	private TCProperty endT = null;// ��ʼʱ��
	private TCProperty name = null;// ����
	private TCProperty sex = null;// �Ա�
	private TCProperty title = null;// ְ��
	private TCProperty age = null;// ����
	private TCProperty mainWork = null;// �е�����Ŀ��Ҫ����
	private TCProperty inMonth = null;// Ͷ������
	private TCProperty remark = null;// ��ע
	private TCProperty prjCost = null;// ��Ŀ�ܷ���
	private TCProperty ina = null;// ����a��
	private TCProperty inb = null;// ����b��
	private TCProperty inc = null;// ����c��
	private TCProperty remark1 = null;// ��ע1
	private TCProperty preStage = null;// Ԥ�н׶�
	private TCProperty prjDesign = null;// �������
	private TCProperty proTest = null;// ��������
	private TCProperty standC = null;// �����������
	private TCProperty designCom = null;// ���ȷ��
	private TCProperty cbareson = null;// ������ݼ�����
	// private TCProperty proposer = null;//������
	// private TCProperty tddHead = null;//���Ʋ��ż���������
	// private TCProperty tsection = null;//��������
	// private TCProperty xiangmuyueb = null;//��Ŀ�±�
	private TCProperty fujian =null;//����

	private String[] strStartT = null;
	private String[] strEndT = null;
	private String[] strName = null;
	private String[] strSex = null;
	private String[] strTitle = null;
	private String[] strAge = null;
	private String[] strMainWork = null;
	private String[] strInMonth = null;
	private String[] strRemark = null;
	private String[] StrInta = null;
	private String[] StrIntb = null;
	private String[] StrIntc = null;
	private String[] strRemark1 = null;
	private String[] strPreStage = null;
	private String[] strPrjDesign = null;
	private String[] strProTest = null;
	private String[] strStandC = null;
	private String[] strDesignCom = null;
	private String[] strCbareson = null;
	// private String[] strXiangmyb = null;
	private String strfujian[] = null;//����
	

	public SACJTextField128 textPrjName = null;// ����Ŀ����
	public SACJTextField32 textPrjId = null;// �������
	// public SACJTextField128 textPrjClass = null;//���Ʋ���
	// public SACJTextField32 textPrjMan = null;//��Ŀ������
	public JTextArea textPrjContent = null;// ��Ŀ����
	public JTextArea textAdoStand = null;// ���ñ�׼
	public JTable tableJinDuAnPai = null;// ���Ȱ���
	public JTable tableXiangMuZuRenYuan = null;// ��Ŀ����Ա
	public SACJTextField32 textPrjCost = null;// ��Ŀ�ܷ���
	public JTable tableJingJiLaiYuan = null;// ������Դ
	public JTable tableYuSuanZhiChu = null;// Ԥ��֧��
	// public JTable tableXiangMuYueBao = null;//��Ŀ�±�
	// public SACJTextField32 textProposer = null;//����
	// public SACJTextField32 textTddhead = null;//���Ʋ��ż���������
	// public SACJTextField32 textTsection = null;//��������

	// public JTextField textseiban = null;//seiban��
	public SACJTextField32 textseiban = null;                // seiban��
	public JComboBox projCheckbox = null;// ��Ŀ����ѡ���
	public JComboBox teamCheckbox = null;// ������Ŀѡ���
	// public String[] proj_str =
	// {"","װ����С����Ŀ","װ���������Ŀ","�����С����Ŀ","����������Ŀ","��������Ŀ","������Ŀ","ϵͳ����Ŀ"};//Ĭ����Ŀ����ѡ��������
	public String[] proj_str1;
	public String[] proj_str;
	public String[] folder_str;
	public String[] team_str = { "", "��", "��" };// Ĭ�ϼ�����Ŀѡ��������
	public HashMap<String, String> map_all = new HashMap<String, String>();
	public HashMap<String, Vector<String>> map_1 = new HashMap<String, Vector<String>>();
	public HashMap<String, Vector<String>> map_2 = new HashMap<String, Vector<String>>();
	public HashMap<String, Vector<String>> map_3 = new HashMap<String, Vector<String>>();
	public boolean flag = false;
	public boolean flag1 = false;
	public Vector<String> v_info = new Vector<String>();                       //���������Ƿ�Ϊ���������
	public Vector<TCComponentFolder> v_folder = new Vector<TCComponentFolder>();

	//private SACJTextField1280 syfwiTextField;// ���÷�Χ (��Ҫ�޸ĵĵط�)

    private JTextArea syfwiTextField;            //���÷�Χ
	private DateButton qznyidatebutton;// ��ֹ����
	private DateButton jznyidatebutton;// ��ֹ����
	//private SACJTextField128 cddwybmiTextField;// �е���λ�벿��
	private JComboBox  cddwybmiTextField=null;
	private SACJTextField128 cbdwTextField;//�а첿��                                                �����ӵĲ���
	
	private JTextArea ysfsiTextField; // ��ʽ��ʽ
	private SACJTextField32 hzdwiTextField;// ������λ
	private SACJTextField32 hzfsiTextField;// ������ʽ
	private SACJTextField64 xmzysiTextField;// ��Ŀ��Ԥ��
	private SACJTextField64 zjlyiTextField;// �ʽ���Դ
	public JTextArea mbjsspiTextField;// Ŀ�꼼��ˮƽ
	private iTextArea zliTextField;// ר��
	private iTextArea zzqiTextField;// ����Ȩ
	private iTextArea lwiTextField;// ����
	public JTextArea cgysfsiTextField;// �ɹ����շ�ʽ
	private SACJTextField32 xmiTextField1;// ����1
	private JComboBox zc_Box1;// ְ��1
	private SACJTextField32 zyiTextField1;// רҵ1
	private JComboBox yanzdw_Box1;// ���Ƶ�λ1
	private SACJTextField32 yanziTextField1;// ���Ʋ���1
	private JTextArea lxfsiTextField1;// ��ϵ��ʽ1
	private JTextArea emailiTextField1;// EMail1
	private SACJTextField32 xmiTextField2;// ����2
	private JComboBox zc_Box2;// ְ��2
	private SACJTextField32 zyiTextField2;// רҵ2
	private JComboBox yanzdw_Box2;// ���Ƶ�λ2
	private SACJTextField32 yanziTextField2;// ���Ʋ���2
	private JTextArea lxfsiTextField2;// ��ϵ��ʽ2
	private JTextArea emailiTextField2;// EMail2
	private SACJTextField32 xmiTextField3;// ����3
	private JComboBox zc_Box3;// ְ��3
	private SACJTextField32 zyiTextField3;// רҵ3
	private JComboBox yanzdw_Box3;// ���Ƶ�λ3
	private SACJTextField32 yanziTextField3;// ���Ʋ���3
	private JTextArea lxfsiTextField3;// ��ϵ��ʽ3
	private JTextArea emailiTextField3;// EMail3
	private String syfw_str = "[����д���ַ�����Ϊ1280][���÷�Χ����Ŀ�����������ɹ���Ʒ��ʹ�÷�Χ]";//���÷�Χ����ʾ�ַ�
	private String hzfs_str = "[���������������������]";
	private String zjly_str = "[��˾�Գ�������ϼ���λ������ļ����]";
	private String yafs_str = "[ע���������з����������ԡ������Ӳ��ȣ����к����������˵�����������������������]";
	private String mbjssp_str = "[����д���ַ�����Ϊ1280][��������ˮƽ�������Ƚ�ˮƽ����������ˮƽ�������Ƚ�ˮƽ]";
	private String cgysfs_str = "[���Ҽ���ʡ������������ҵ�����������Ż��û���֯����]";
	private String tixing_str = "[����д���ַ�����Ϊ4000]";
	private String tel_str = "Tel:" + "\n" + "Mobile:";
	private String  str_seiban="[�ɽӿ���Ա��д]";                         //��Ҫ���صĵط�
	private Font font;
	public DefaultTableModel tableModelTwo = null;
	public DefaultTableModel tableModelThree = null;
	public JTabbedPane jTabbedPane = null;
	public int num = 0;
	public int num1 = 0;
	private String relation = "IMAN_reference";
	private String yc_relation = "S4Prj_evadata";
	private TCFormProperty formProperties[] = null;
	private TCComponentItem item = null;
	private AIFComponentContext[] inter;
	private String[] properties_name = { "object_name", "item_id" };
	private String[] values = null;
	private String proj_name = "";
	private String proj_id = "";
	// private String default_date = "2013-03-08";
	private String[] dw_str = null;
	private String[] zc_str = null;
	private HashMap<String, Object> component_map = new HashMap<String, Object>();
	
	
	public String path=null;//����·��
	public int fujiancount=0;//��������
	public LinkLabel filelabel[]=new LinkLabel[50];
	public JPanel accessoryPanel=new JPanel(new GridLayout(1,1));
	
	public JPanel p[]=new JPanel[50];
	JPanel panel3=new JPanel(new BorderLayout());

	public S4YFprojXJUserForm(TCComponentForm arg0) throws Exception {
		super(arg0);
		form = arg0;
		session = (TCSession) form.getSession();
		proj_str1 = session.getPreferenceService().getStringArray(4,
				"SAC_Project_Name");
		if (proj_str1 == null || proj_str1.length == 0) {
			MessageBox.post("δ������ѡ�SAC_Project_Name��ֵ", "��ʾ",
					MessageBox.INFORMATION);
			return;
		} else {
			proj_str = new String[proj_str1.length + 1];
			for (int i = 0; i < proj_str1.length; i++) {
				proj_str[0] = "";
				proj_str[i + 1] = proj_str1[i];
			}
		}
		folder_str = session.getPreferenceService().getStringArray(4,
				"SAC_Auto_Create_Item");
		if (folder_str == null || folder_str.length == 0) {
			MessageBox.post("δ������ѡ�SAC_Auto_Create_Item��ֵ", "��ʾ",
					MessageBox.INFORMATION);
			return;
		} else {
			for (int i = 0; i < folder_str.length; i++) {
				String[] str = folder_str[i].split(":");
				map_all.put(str[0], str[1]);
			}
		}
		// �õ�ְ�Ƶ�LOVֵ
		String title_str[] = session.getPreferenceService().getStringArray(4,
				"SAC_title_Lov");
		if (title_str == null || title_str.length == 0) {
			MessageBox.post("δ������ѡ�SAC_title_Lov��ֵ", "��ʾ",
					MessageBox.INFORMATION);
			return;
		} else {
			zc_str = new String[title_str.length + 1];
			zc_str[0] = "";
			for (int i = 0; i < title_str.length; i++) {
				zc_str[i + 1] = title_str[i];

			}
		}
		// �õ�ְ�Ƶ�LOVֵ
		String department_str[] = session.getPreferenceService()
				.getStringArray(4, "SAC_Department_Lov");
		if (department_str == null || department_str.length == 0) {
			MessageBox.post("δ������ѡ�SAC_Department_Lov��ֵ", "��ʾ",
					MessageBox.INFORMATION);
			return;
		} else {
			dw_str = new String[department_str.length + 1];
			dw_str[0] = "";
			for (int i = 0; i < department_str.length; i++) {
				dw_str[i + 1] = department_str[i];

			}
		}
		v_info.removeAllElements();
		num = form.getTCProperty("s4name").getStringArrayValue().length;          //�������Ǹ����
		num1 = form.getTCProperty("s4Projmonthly").getStringArrayValue().length;

		inter = form.getPrimary();                              //Ӧ���ǵõ�ҳ�������
		if (inter.length > 0) {
			// �õ�Item
			item = (TCComponentItem) inter[0].getComponent();
		}
		values = item.getProperties(properties_name);
		proj_name = values[0];
		System.out.println("proj_name==>"+proj_name);
	//	proj_id = values[1];                                                      //��������Ž����޸ĵĵط�
		System.out.println("proj_id==>"+proj_id);
		initUI();
		loadForm();
	}

	public void loadForm() throws TCException {

		formProperties = form.getAllFormProperties();
		for (int n = 0; n < formProperties.length; n++) {
			String str = formProperties[n].getPropertyName();
			if (str.equals("s4Prj_name")) {                                  //��Ŀ����
				prjName = formProperties[n];
			}
			else if (str.equals("s4prj_content")) {                                  //��Ŀ����
				prjContent = formProperties[n];
			}else if(str.equals("s4prj_id")){
				textPrjId.setText(formProperties[n].getStringValue());                    //�޸ĵĵط�
				System.out.println("s4prj_id==>"+formProperties[n].getStringValue());
			}			
			else if (str.equals("s4ado_stand")) {
				adoStand = formProperties[n];
			} else if (str.equals("s4start_t")) {
				startT = formProperties[n];
			} else if (str.equals("s4uptime")) {
				endT = formProperties[n];
			} else if (str.equals("s4name")) {                            //����
				name = formProperties[n];
			} else if (str.equals("s4sex")) {
				sex = formProperties[n];
			} else if (str.equals("s4title")) {
				title = formProperties[n];
			} else if (str.equals("s4age")) {
				age = formProperties[n];
			} else if (str.equals("s4mainwork")) {
				mainWork = formProperties[n];
			} else if (str.equals("s4inmonth")) {
				inMonth = formProperties[n];
			} else if (str.equals("s4remark")) {
				remark = formProperties[n];
			} else if (str.equals("s4prj_cost")) {
				prjCost = formProperties[n];
			} else if (str.equals("s4ina")) {
				ina = formProperties[n];
			} else if (str.equals("s4inb")) {
				inb = formProperties[n];
			} else if (str.equals("s4inc")) {
				inc = formProperties[n];
			} else if (str.equals("s4remark1")) {
				remark1 = formProperties[n];
			} else if (str.equals("s4pre_stage")) {
				preStage = formProperties[n];
			} else if (str.equals("s4prj_design")) {
				prjDesign = formProperties[n];
			} else if (str.equals("s4pro_test")) {
				proTest = formProperties[n];
			} else if (str.equals("s4tandc")) {
				standC = formProperties[n];
			} else if (str.equals("s4designcom")) {
				designCom = formProperties[n];
			} else if (str.equals("s4cbareson")) {
				cbareson = formProperties[n];
			} else if (str.equals("s4scope")) {
				if (formProperties[n].getStringValue() != null
						&& !"".equals(formProperties[n].getStringValue()
								.toString())) {
					syfwiTextField.setText(formProperties[n].getStringValue());
				}				
				else {
					syfwiTextField.setText(syfw_str);           
				}				
			} else if (str.equals("s4timed")) {
				if (formProperties[n].getStringValue() != null
						&& !"".equals(formProperties[n].getStringValue())) {
					qznyidatebutton.setDate(formProperties[n].getStringValue());              //DateButton�ؼ���ϵͳ��ȡֵ
				}
			} else if (str.equals("s4timedup")) {
				if (formProperties[n].getStringValue() != null
						&& !"".equals(formProperties[n].getStringValue())) {
					jznyidatebutton.setDate(formProperties[n].getStringValue());
				}
			} else if (str.equals("s4tandd")) {
				cddwybmiTextField.setSelectedItem(formProperties[n].getStringValue());        //�����޸ĵĵط�
			} else if(str.equals("s4tandd1")){
				cbdwTextField.setText(formProperties[n].getStringValue());  
			}
						
			else if (str.equals("s4Grind_testw")) {
				if (formProperties[n].getStringValue() != null
						&& !"".equals(formProperties[n].getStringValue()
								.toString())) {
					ysfsiTextField.setText(formProperties[n].getStringValue());
				} else {
					ysfsiTextField.setText(yafs_str);
				}
			} else if (str.equals("s4Cooperator")) {
				hzdwiTextField.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4Cooperation")) {
				if (formProperties[n].getStringValue() != null
						&& !"".equals(formProperties[n].getStringValue()
								.toString())) {
					hzfsiTextField.setText(formProperties[n].getStringValue());
				}
			} else if (str.equals("s4prj_budget")) {
				xmzysiTextField.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4cap_source")) {
				if (formProperties[n].getStringValue() != null
						&& !"".equals(formProperties[n].getStringValue()
								.toString())) {
					zjlyiTextField.setText(formProperties[n].getStringValue());
				}
			} else if (str.equals("s4goal_resu")) {
				if (formProperties[n].getStringValue() != null
						&& !"".equals(formProperties[n].getStringValue()
								.toString())) {
					mbjsspiTextField
							.setText(formProperties[n].getStringValue());
				} else {
					mbjsspiTextField.setText(mbjssp_str);
				}
			} else if (str.equals("s4patent")) {
				zliTextField.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4copyright")) {
				zzqiTextField.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4thesis")) {
				lwiTextField.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4Results_acceway")) {
				if (formProperties[n].getStringValue() != null
						&& !"".equals(formProperties[n].getStringValue()
								.toString())) {
					cgysfsiTextField
							.setText(formProperties[n].getStringValue());
				} else {
					cgysfsiTextField.setText(cgysfs_str);
				}
			} else if (str.equals("s4name2")) {
				xmiTextField1.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4title2")) {
				zc_Box1.setSelectedItem(formProperties[n].getStringValue());
			} else if (str.equals("s4profession")) {
				zyiTextField1.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4department")) {
				yanzdw_Box1.setSelectedItem(formProperties[n].getStringValue());
			} else if (str.equals("s4prj_class")) {
				yanziTextField1.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4contact_way")) {
				if (formProperties[n].getStringValue() != null
						&& !"".equals(formProperties[n].getStringValue()
								.toString())) {
					lxfsiTextField1.setText(formProperties[n].getStringValue());
				} else {
					lxfsiTextField1.setText(tel_str);           
				}
			} else if (str.equals("s4Email")) {
				emailiTextField1.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4name1")) {
				xmiTextField2.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4title1")) {
				zc_Box2.setSelectedItem(formProperties[n].getStringValue());
			} else if (str.equals("s4profession1")) {
				zyiTextField2.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4department1")) {
				yanzdw_Box2.setSelectedItem(formProperties[n].getStringValue());
			} else if (str.equals("s4prj_class2")) {
				yanziTextField2.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4contact_way1")) {
				if (formProperties[n].getStringValue() != null
						&& !"".equals(formProperties[n].getStringValue()
								.toString())) {
					lxfsiTextField2.setText(formProperties[n].getStringValue());
				} else {
					lxfsiTextField2.setText(tel_str);
				}
			} else if (str.equals("s4Email1")) {
				emailiTextField2.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4name3")) {
				xmiTextField3.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4title3")) {
				zc_Box3.setSelectedItem(formProperties[n].getStringValue());
			} else if (str.equals("s4profession3")) {
				zyiTextField3.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4department3")) {
				yanzdw_Box3.setSelectedItem(formProperties[n].getStringValue());
			} else if (str.equals("s4prj_class3")) {
				yanziTextField3.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4contact_way3")) {
				if (formProperties[n].getStringValue() != null
						&& !"".equals(formProperties[n].getStringValue()
								.toString())) {
					lxfsiTextField3.setText(formProperties[n].getStringValue());
				} else {
					lxfsiTextField3.setText(tel_str);
				}
			} else if (str.equals("s4Email3")) {
				emailiTextField3.setText(formProperties[n].getStringValue());
			} else if (str.equals("s4seiban")) {                          //�޸ĵĵط�  �����
				if (formProperties[n].getStringValue() != null
						&& !"".equals(formProperties[n].getStringValue()
								.toString())) {
					textseiban.setText(formProperties[n].getStringValue());
				} else {
					textseiban.setText(str_seiban);
				}								
			} else if (str.equals("s4Projtype")) {
				projCheckbox
						.setSelectedItem(formProperties[n].getStringValue());
			} else if (str.equals("s4teamevent")) {
				teamCheckbox
						.setSelectedItem(formProperties[n].getStringValue());
			}
			 else if (str.equals("s4Conreattach")) {
			fujian=form.getTCProperty("s4Conreattach");
			 }
		}

		textPrjName.setText(proj_name);
	//	textPrjId.setText(prjId.getStringValue());
		if (prjContent.getStringValue() == null
				|| prjContent.getStringValue().equals("")) {
			textPrjContent.setText(tixing_str);
		} else {
			textPrjContent.setText(prjContent.getStringValue());
		}
		if (adoStand.getStringValue() == null
				|| adoStand.getStringValue().equals("")) {
			textAdoStand.setText("���ñ�׼��" + tixing_str);
		} else {
			textAdoStand.setText(adoStand.getStringValue());
		}

		strStartT = startT.getStringArrayValue();
		for (int i = 0; i < strStartT.length; i++) {
			tableJinDuAnPai.setValueAt(strStartT[i], i, 2);
		}
		strEndT = endT.getStringArrayValue();
		for (int i = 0; i < strEndT.length; i++) {
			tableJinDuAnPai.setValueAt(strEndT[i], i, 3);        
		}

		textPrjCost.setText(prjCost.getStringValue());

		strName = name.getStringArrayValue();
		for (int i = 0; i < strName.length; i++) {
			tableXiangMuZuRenYuan.setValueAt(strName[i], i, 0);
		}
		strSex = sex.getStringArrayValue();
		for (int i = 0; i < strSex.length; i++) {
			tableXiangMuZuRenYuan.setValueAt(strSex[i], i, 1);            //��Ŀ����Ա���Ա�
		}
		strTitle = title.getStringArrayValue();
		for (int i = 0; i < strTitle.length; i++) {
			tableXiangMuZuRenYuan.setValueAt(strTitle[i], i, 2);            //��Ŀ����Ա��ְ��
		}
		strAge = age.getStringArrayValue();
		for (int i = 0; i < strAge.length; i++) {
			tableXiangMuZuRenYuan.setValueAt(strAge[i], i, 3);
		}
		strMainWork = mainWork.getStringArrayValue();
		for (int i = 0; i < strMainWork.length; i++) {
			tableXiangMuZuRenYuan.setValueAt(strMainWork[i], i, 4);
		}
		strInMonth = inMonth.getStringArrayValue();
		for (int i = 0; i < strInMonth.length; i++) {
			tableXiangMuZuRenYuan.setValueAt(strInMonth[i], i, 5);
		}
		strRemark = remark.getStringArrayValue();
		for (int i = 0; i < strRemark.length; i++) {
			tableXiangMuZuRenYuan.setValueAt(strRemark[i], i, 6);
		}

		StrInta = ina.getStringArrayValue();
		for (int i = 0; i < StrInta.length; i++) {
			tableJingJiLaiYuan.setValueAt(StrInta[i], i, 1);
		}
		StrIntb = inb.getStringArrayValue();
		for (int i = 0; i < StrIntb.length; i++) {
			tableJingJiLaiYuan.setValueAt(StrIntb[i], i, 2);
		}
		StrIntc = inc.getStringArrayValue();
		for (int i = 0; i < StrIntc.length; i++) {
			tableJingJiLaiYuan.setValueAt(StrIntc[i], i, 3);
		}
		strRemark1 = remark1.getStringArrayValue();
		for (int i = 0; i < strRemark1.length; i++) {
			tableJingJiLaiYuan.setValueAt(strRemark1[i], i, 4);
		}

		strPreStage = preStage.getStringArrayValue();
		for (int i = 0; i < strPreStage.length; i++) {
			tableYuSuanZhiChu.setValueAt(strPreStage[i], i, 1);
		}
		strPrjDesign = prjDesign.getStringArrayValue();
		for (int i = 0; i < strPrjDesign.length; i++) {
			tableYuSuanZhiChu.setValueAt(strPrjDesign[i], i, 2);
		}
		strProTest = proTest.getStringArrayValue();
		for (int i = 0; i < strProTest.length; i++) {
			tableYuSuanZhiChu.setValueAt(strProTest[i], i, 3);
		}
		strStandC = standC.getStringArrayValue();
		for (int i = 0; i < strStandC.length; i++) {
			tableYuSuanZhiChu.setValueAt(strStandC[i], i, 4);
		}
		strDesignCom = designCom.getStringArrayValue();
		for (int i = 0; i < strDesignCom.length; i++) {
			tableYuSuanZhiChu.setValueAt(strDesignCom[i], i, 5);
		}
		strCbareson = cbareson.getStringArrayValue();
		for (int i = 0; i < strCbareson.length; i++) {
			tableYuSuanZhiChu.setValueAt(strCbareson[i], i, 6);
		}
		
		fujiancount=fujian.getStringArrayValue().length;//��������
        strfujian=fujian.getStringArrayValue();
		
		if(fujiancount>0){
		accessoryPanel=new JPanel(new GridLayout(1,1));
		for(int i=0;i<fujiancount;i++)
	 	{
    	p[i]=new JPanel(new FlowLayout(FlowLayout.LEFT));
    	//System.out.println(form.getProperty("s4accessorylink").toString());
    	//s4CSQXBGRevisionUI.filelabel[i]=new LinkLabel(strfujian[i],new File(form.getProperty("s4accessorylink").toString()));
    	filelabel[i]=new LinkLabel(strfujian[i],new File(""));
    	filelabel[i].setForeground(Color.BLUE);
    	//System.out.println(s4CSQXBGRevisionUI.filelabel[i].getText());
    	p[i].add(filelabel[i]);
       	final JLabel label_delete=new JLabel("ɾ��");
    	p[i].add(label_delete);
    	accessoryPanel.setLayout(new GridLayout(accessoryPanel.getComponentCount()+1,1));//��������Panel�Ĳ���
    	accessoryPanel.add(	p[i]);
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
					accessoryPanel.setLayout(new GridLayout(accessoryPanel.getComponentCount()-1,1));//��������Panel�Ĳ���
					int j=0;
					for(int i=0;i<fujiancount;i++)
						if(filelabel[i].getParent()==e.getComponent().getParent())//ɾ��--����
							{
							   j=i;//ѡ��ɾ�����Ǹ�����
							   try {  
									
									AbstractAIFApplication app= AIFUtility.getCurrentApplication();
									TCSession session=(TCSession)app.getSession();
									TCComponentDatasetType tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("Dataset");
									TCComponentDataset dataset=tccomponentDatasetType.find(filelabel[i].getText());
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
					for(;j<fujiancount-1;j++){
						filelabel[j].setText(filelabel[j+1].getText());
						filelabel[j].setPath(filelabel[j+1].getPath());
					}
					fujiancount--;
					accessoryPanel.remove(p[fujiancount]);
					accessoryPanel.setLayout(new GridLayout(fujiancount,1));
				//	accessoryPanel.repaint();
					jTabbedPane.repaint();//ʵʱˢ�±�ǩ�ϵ�����
				//	jTabbedPane.getParent().repaint();
				//	jTabbedPane.setBounds(0, 0, 800, 800);
					jTabbedPane.setBounds(0, 0, jTabbedPane.getWidth(), jTabbedPane.getHeight());
					
				} 
				}
    	}
			);
	 	}
    	panel3.add(accessoryPanel,BorderLayout.CENTER);
	//	s4CSQXBGRevisionUI.textstatus.setEditable(false);
		}
	    System.out.println("********loading end*********");
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
				if (jcomponent_type.indexOf("JComboBox") > 0) {
					JComboBox combomx = (JComboBox) value;
					String str_com = combomx.getSelectedItem().toString();
					if (str_com == null || "".equals(str_com)) {
						vec_t.add(info_name);
					}
				} else if (jcomponent_type.indexOf("SACJTextField32") > 0) {
					SACJTextField32 jTextField32 = (SACJTextField32) value;
					String str_32 = jTextField32.getText().toString();
					if (str_32 == null || "".equals(str_32)) {
						vec_t.add(info_name);
					} else {
						if (str_32.equals(hzfs_str)) {
							vec_t.add(info_name);
						}
					}
				} else if (jcomponent_type.indexOf("SACJTextField1280") > 0) {
					SACJTextField1280 jTextField1280 = (SACJTextField1280) value;
					String str_1280 = jTextField1280.getText().toString();
					if (str_1280 == null || "".equals(str_1280)) {
						vec_t.add(info_name);
					} else {
						if (str_1280.equals(syfw_str)) {
							vec_t.add(info_name);
						}
					}
				} else if (jcomponent_type.indexOf("SACJTextField64") > 0) {
					SACJTextField64 jTextField64 = (SACJTextField64) value;
					String str_64 = jTextField64.getText().toString();
					if (str_64 == null || "".equals(str_64)) {
						vec_t.add(info_name);
					} else {
						if (str_64.equals(zjly_str)) {         
							vec_t.add(info_name);
						}
					}
				} else if (jcomponent_type.indexOf("SACJTextField128") > 0) {
					SACJTextField128 jTextField128 = (SACJTextField128) value;
					String str_128 = jTextField128.getText().toString();
					if (str_128 == null || "".equals(str_128)) {
						vec_t.add(info_name);
					}
				} else if (jcomponent_type.indexOf("JTextArea") > 0) {
					JTextArea jtextarea = (JTextArea) value;
					String str_area = jtextarea.getText().toString();
					if (str_area == null || "".equals(str_area)) {
						vec_t.add(info_name);
					} else {
						if (str_area.equals(yafs_str)
								|| str_area.equals(cgysfs_str)
								|| str_area.equals(tixing_str)
								|| str_area.equals(tel_str)
								|| str_area.equals(mbjssp_str)
								|| str_area.equals("���ñ�׼��" + tixing_str)
								|| str_area.equals("���ñ�׼��")) {
							vec_t.add(info_name);
						}
					}
				} else if (jcomponent_type.indexOf("iTextArea") > 0) {
					iTextArea itextarea = (iTextArea) value;
					String str_iarea = itextarea.getText().toString();
					if (str_iarea == null || "".equals(str_iarea)) {
						vec_t.add(info_name);
					}
				}
			}
		}
		// �ж�tableJinDuAnPai���ǲ�����û��û��
		if (tableJinDuAnPai.getEditingRow() <= tableJinDuAnPai.getRowCount()
				&& tableJinDuAnPai.getCellEditor() != null) {
			tableJinDuAnPai.getCellEditor().stopCellEditing();
		}
		Object objTempqssj = null;
		Object objTempjzsj = null;
		String strTempqssj = null;
		String strTempjzsj = null;
		for (int i = 0; i < tableJinDuAnPai.getRowCount(); i++) {
			objTempqssj = tableJinDuAnPai.getValueAt(i, 2);
			strTempqssj = String.valueOf(objTempqssj);
			objTempjzsj = tableJinDuAnPai.getValueAt(i, 3);
			strTempjzsj = String.valueOf(objTempjzsj);
			if (objTempqssj == null || "".equals(strTempqssj)) {
				if (!vec_t.contains("��ʼʱ��")) {
					vec_t.add("��ʼʱ��");
				}
			}
			if (objTempjzsj == null || "".equals(strTempjzsj)) {
				if (!vec_t.contains("����ʱ��")) {
					vec_t.add("����ʱ��");
				}
			}
		}
		// �ж�tableXiangMuZuRenYuan���ǲ�����û��û��
		System.out.println("tableXiangMuZuRenYuan--->:"
				+ tableXiangMuZuRenYuan.getRowCount());
		if (tableXiangMuZuRenYuan.getRowCount() <= 0) {
			if (!vec_t.contains("��Ŀ����Ա")) {
				vec_t.add("��Ŀ����Ա");
			}
		} else {
			if (tableXiangMuZuRenYuan.getEditingRow() <= tableXiangMuZuRenYuan
					.getRowCount()
					&& tableXiangMuZuRenYuan.getCellEditor() != null) {
				tableXiangMuZuRenYuan.getCellEditor().stopCellEditing();
			}
			String strTempxm = null;
			String strTempxb = null;
			String strTempzc = null;
			String strTempnl = null;
			String strTempzygz = null;
			String strTemptrys = null;
			String strTempbz = null;
			for (int i = 0; i < tableXiangMuZuRenYuan.getRowCount(); i++) {
				strTempxm = (String) tableXiangMuZuRenYuan.getValueAt(i, 0);
				strTempxb = (String) tableXiangMuZuRenYuan.getValueAt(i, 1);
				strTempzc = (String) tableXiangMuZuRenYuan.getValueAt(i, 2);
				strTempnl = (String) tableXiangMuZuRenYuan.getValueAt(i, 3);
				strTempzygz = (String) tableXiangMuZuRenYuan.getValueAt(i, 4);
				strTemptrys = (String) tableXiangMuZuRenYuan.getValueAt(i, 5);
				strTempbz = (String) tableXiangMuZuRenYuan.getValueAt(i, 6);      //��ע
				if (strTempxm == null || "".equals(strTempxm)) {
					if (!vec_t.contains("��Ŀ����Ա����")) {
						vec_t.add("��Ŀ����Ա����");
					}
				}
				if (strTempxb == null || "".equals(strTempxb)) {
					if (!vec_t.contains("��Ŀ����Ա�Ա�")) {
						System.out.println("dfff");
						vec_t.add("��Ŀ����Ա�Ա�");
					}
				}
				if (strTempzc == null || "".equals(strTempzc)) {
					if (!vec_t.contains("��Ŀ����Աְ��")) {
						System.out.println("dfff");
						vec_t.add("��Ŀ����Աְ��");
					}
				}
				if (strTempnl == null || "".equals(strTempnl)) {
					if (!vec_t.contains("��Ŀ����Ա����")) {
						System.out.println("dfff");
						vec_t.add("��Ŀ����Ա����");
					}
				}
				if (strTempzygz == null || "".equals(strTempzygz)) {
					if (!vec_t.contains("�е�����Ŀ��Ҫ����")) {
						System.out.println("dfff");
						vec_t.add("�е�����Ŀ��Ҫ����");
					}
				}
				if (strTemptrys == null || "".equals(strTemptrys)) {
					if (!vec_t.contains("Ͷ������")) {
						System.out.println("dfff");
						vec_t.add("Ͷ������");
					}
				}
				/*if (strTempbz == null || "".equals(strTempbz)) {                    //�������޸ĵĵط�
					if (!vec_t.contains("��Ŀ����Ա��ע")) {
						System.out.println("dfff");
						vec_t.add("��Ŀ����Ա��ע");
					}
				}*/
			}
		}
		// �ж�tableJingJiLaiYuan���ǲ�����û��û��
		if (tableJingJiLaiYuan.getEditingRow() <= tableJingJiLaiYuan
				.getRowCount()
				&& tableJingJiLaiYuan.getCellEditor() != null) {
			tableJingJiLaiYuan.getCellEditor().stopCellEditing();
		}
		String strTempa = null;
		String strTempjflybz = null;
		int j=0;
		String strheji=null;
		strheji=(String) tableJingJiLaiYuan.getValueAt(4,1);
		System.out.println("strheji==>"+strheji);
		for (int i = 0; i < tableJingJiLaiYuan.getRowCount()-1; i++) {             //������Ҫ�����޸�
			strTempa = (String) tableJingJiLaiYuan.getValueAt(i, 1);
			strTempjflybz = (String) tableJingJiLaiYuan.getValueAt(i, 4);
			if (strTempa == null || "".equals(strTempa)) {
				//if (!vec_t.contains("����a��")) {
				//	vec_t.add("����a��");
				//}
				j++;
				//System.out.println("��ʱ��j==>"+j);
			} else if ("���У�����*".equals(strTempa)) {
				if (!vec_t.contains("����a��")) {
					vec_t.add("����a��");
				}
			}
			
			/*if(j==0){
				if (!vec_t.contains("����a��")) {
					vec_t.add("����a��");
					}
			}*/
			/*if (strTempjflybz == null || "".equals(strTempjflybz)) {
				if (!vec_t.contains("������Դ��ע")) {
					vec_t.add("������Դ��ע");
				}
			}*/
		}
		System.out.println("��ʱ��j==>"+j);
		if(j>2){
			if (!vec_t.contains("����a��")) {
				vec_t.add("����a��");
			}
		}
		
		if(strheji==null||"".equals(strheji)){
			System.out.println("����ִ�е�����");
			if (!vec_t.contains("������Դ�ϼ�")) {
				vec_t.add("������Դ�ϼ�");
			}
		}
		/*
		 * //�ж�tableYuSuanZhiChu���ǲ�����û��û��
		 * if(tableYuSuanZhiChu.getEditingRow()<=tableYuSuanZhiChu
		 * .getRowCount()&&tableYuSuanZhiChu.getCellEditor()!=null){
		 * tableYuSuanZhiChu.getCellEditor().stopCellEditing(); } String
		 * strTempyyjd = null; String strTempfasj = null; String strTempyjcs =
		 * null; String strTempjcysyx = null; String strTempsjqr = null; String
		 * strTempjsgj = null; for (int i = 0; i <
		 * tableYuSuanZhiChu.getRowCount(); i++) { strTempyyjd=(String)
		 * tableYuSuanZhiChu.getValueAt(i, 1); strTempfasj = (String)
		 * tableYuSuanZhiChu.getValueAt(i, 2); strTempyjcs = (String)
		 * tableYuSuanZhiChu.getValueAt(i, 3); strTempjcysyx = (String)
		 * tableYuSuanZhiChu.getValueAt(i, 4); strTempsjqr = (String)
		 * tableYuSuanZhiChu.getValueAt(i, 5); strTempjsgj = (String)
		 * tableYuSuanZhiChu.getValueAt(i, 6); if(strTempyyjd == null ||
		 * "".equals(strTempyyjd)){ if(!vec_t.contains("Ԥ�н׶�")){
		 * vec_t.add("Ԥ�н׶�"); } } if(strTempfasj == null ||
		 * "".equals(strTempfasj)){ if(!vec_t.contains("�������")){
		 * System.out.println("dfff"); vec_t.add("�������"); } } if(strTempyjcs ==
		 * null || "".equals(strTempyjcs)){ if(!vec_t.contains("��������")){
		 * System.out.println("dfff"); vec_t.add("��������"); } } if(strTempjcysyx
		 * == null || "".equals(strTempjcysyx)){ if(!vec_t.contains("�����������")){
		 * System.out.println("dfff"); vec_t.add("�����������"); } } if(strTempsjqr
		 * == null || "".equals(strTempsjqr)){ if(!vec_t.contains("���ȷ��")){
		 * System.out.println("dfff"); vec_t.add("���ȷ��"); } } if(strTempjsgj ==
		 * null || "".equals(strTempjsgj)){ if(!vec_t.contains("������ݼ�����")){
		 * System.out.println("dfff"); vec_t.add("������ݼ�����"); } } }
		 */
		if (vec_t.size() > 0) {
			MessageBox.post(vec_t + "��" +
					"Щ���Բ���Ϊ��,����", "Message",
					MessageBox.INFORMATION);
			return false;
		}
		return super.isFormSavable(arg0);
	}

	public void saveForm() {
		try {
			// �õ���Ŀ��ѡ�������
			String proj_name = projCheckbox.getSelectedItem().toString();
			// �ж������Ŀ��������ѡ������û������,���û����������Ҫ���
			String tem = map_all.get(proj_name);
			if (tem != null) {
				// �����ѡ�����������Ŀ����,�������õ����ӵ��ĵ������ڵ��ĵ��ṹ����û�д���
				String[] s1 = tem.split(";");
				for (int j = 0; j < s1.length; j++) {
					String[] s2 = s1[j].split(",");
					String[] s3 = s2[1].split("\\*");
					Vector<String> v = new Vector<String>();
					for (int i = 0; i < s3.length; i++) {
						v.add(s3[i]);
					}
					map_1.put(s2[0], v);
				}
				TCComponent[] coms = item.getRelatedComponents(relation);
				TCComponentFolder parentFolder = null;
				if (coms != null && coms.length > 0) {
					for (int i = 0; i < coms.length; i++) {
						if ("S4Prj_Folder".equals(coms[i].getType().toString())) {
							parentFolder = (TCComponentFolder) coms[i];
						}

					}
					if (parentFolder != null) {
						TCComponent[] com_folders = parentFolder
								.getRelatedComponents("contents");
						if (com_folders != null && com_folders.length > 0) {
							for (int j = 0; j < com_folders.length; j++) {
								if (com_folders[j] instanceof TCComponentFolder) {
									TCComponentFolder subFolder = (TCComponentFolder) com_folders[j];
									getAllFolders(subFolder);
								}
							}
							if (flag) {
								// System.out.println("v_folder---->:"+v_folder);
								// System.out.println("map_2====>:"+map_2);
								Vector<String> vec_item = new Vector<String>();
								// Vector<String> vec_item_name = new
								// Vector<String>();
								Vector<String> vec_item_type = new Vector<String>();
								for (int i = 0; i < v_folder.size(); i++) {
									TCComponentFolder folder = v_folder.get(i);
									System.out.println("folder====>:"
											+ folder.toString());
									vec_item = map_1.get(folder.toString());
									System.out.println("vec_item===>:"
											+ vec_item);
									if (vec_item != null) {
										// vec_item_name =
										// map_2.get(folder.toString());
										vec_item_type = map_3.get(folder
												.toString());
										for (int j = 0; j < vec_item.size(); j++) {
											String str = vec_item.get(j);
											String[] s = str.split("=");
											if (!vec_item_type.contains(s[1])) {
												createItem(s[1], s[0], folder);
												if (!v_info.contains(s[0])) {
													v_info.add(s[0]);
												}
											}
										}
									}
								}
								if (v_info.size() > 0) {
									MessageBox.post(
											"��Ŀ���͸��Ĳ�����������Ŀ�ĵ�:" + v_info, "",
											MessageBox.INFORMATION);
								}
							}
						}
					}
				}
			}

			for (int j = 0; j < formProperties.length; j++) {                                   //���������ݵı���
				String string = formProperties[j].getPropertyName();
				if (string.equals("s4scope")) {
					formProperties[j].setStringValueData(syfwiTextField
							.getText());
				} else if (string.equals("s4timed")) {
					formProperties[j].setStringValueData(qznyidatebutton
							.getDateString());
				} else if (string.equals("s4timedup")) {
					formProperties[j].setStringValueData(jznyidatebutton
							.getDateString());
				} else if (string.equals("s4tandd")) {                          //�޸ĵĵط�
					formProperties[j].setStringValueData(cddwybmiTextField
							.getSelectedItem().toString());
				} else if (string.equals("s4tandd1")) {                          //�޸ĵĵط�
					formProperties[j].setStringValueData(cbdwTextField
							.getText());
				} else if(string.equals("s4prj_id")){                                 //�������
					formProperties[j].setStringValueData(textPrjId.getText());
					System.out.println("textPrjId.getText()"+textPrjId.getText());
				}								
				else if (string.equals("s4Grind_testw")) {
					formProperties[j].setStringValueData(ysfsiTextField
							.getText());
				} else if (string.equals("s4Cooperator")) {
					formProperties[j].setStringValueData(hzdwiTextField
							.getText());
				} else if (string.equals("s4Cooperation")) {
					formProperties[j].setStringValueData(hzfsiTextField
							.getText());
				} else if (string.equals("s4prj_budget")) {
					formProperties[j].setStringValueData(xmzysiTextField
							.getText());
				} else if (string.equals("s4cap_source")) {
					formProperties[j].setStringValueData(zjlyiTextField
							.getText());
				} else if (string.equals("s4goal_resu")) {
					formProperties[j].setStringValueData(mbjsspiTextField
							.getText());
				} else if (string.equals("s4patent")) {
					formProperties[j]
							.setStringValueData(zliTextField.getText());
				} else if (string.equals("s4copyright")) {
					formProperties[j].setStringValueData(zzqiTextField
							.getText());
				} else if (string.equals("s4thesis")) {
					formProperties[j]
							.setStringValueData(lwiTextField.getText());
				} else if (string.equals("s4Results_acceway")) {
					formProperties[j].setStringValueData(cgysfsiTextField
							.getText());
				} else if (string.equals("s4name2")) {
					formProperties[j].setStringValueData(xmiTextField1
							.getText());
				} else if (string.equals("s4title2")) {
					formProperties[j].setStringValue((String) zc_Box1
							.getSelectedItem().toString());
				} else if (string.equals("s4profession")) {
					formProperties[j].setStringValueData(zyiTextField1
							.getText());
				} else if (string.equals("s4department")) {
					formProperties[j].setStringValue((String) yanzdw_Box1
							.getSelectedItem().toString());
				} else if (string.equals("s4prj_class")) {
					formProperties[j].setStringValue(yanziTextField1.getText());
				} else if (string.equals("s4contact_way")) {
					formProperties[j].setStringValueData(lxfsiTextField1
							.getText());
				} else if (string.equals("s4Email")) {
					formProperties[j].setStringValueData(emailiTextField1
							.getText());
				} else if (string.equals("s4name1")) {
					formProperties[j].setStringValueData(xmiTextField2
							.getText());
				} else if (string.equals("s4title1")) {
					formProperties[j].setStringValue((String) zc_Box2
							.getSelectedItem().toString());
				} else if (string.equals("s4profession1")) {
					formProperties[j].setStringValueData(zyiTextField2
							.getText());
				} else if (string.equals("s4department1")) {
					formProperties[j].setStringValue((String) yanzdw_Box2
							.getSelectedItem().toString());
				} else if (string.equals("s4prj_class2")) {
					formProperties[j].setStringValue(yanziTextField2.getText());
				} else if (string.equals("s4contact_way1")) {
					formProperties[j].setStringValueData(lxfsiTextField2
							.getText());
				} else if (string.equals("s4Email1")) {
					formProperties[j].setStringValueData(emailiTextField2
							.getText());
				} else if (string.equals("s4name3")) {
					formProperties[j].setStringValueData(xmiTextField3
							.getText());
				} else if (string.equals("s4title3")) {
					formProperties[j].setStringValue((String) zc_Box3
							.getSelectedItem().toString());
				} else if (string.equals("s4profession3")) {
					formProperties[j].setStringValueData(zyiTextField3
							.getText());
				} else if (string.equals("s4department3")) {
					formProperties[j].setStringValue((String) yanzdw_Box3
							.getSelectedItem().toString());
				} else if (string.equals("s4prj_class3")) {
					formProperties[j].setStringValue(yanziTextField3.getText());
				} else if (string.equals("s4contact_way3")) {
					formProperties[j].setStringValueData(lxfsiTextField3
							.getText());
				} else if (string.equals("s4Email3")) {
					formProperties[j].setStringValueData(emailiTextField3
							.getText());
				} else if (string.equals("s4seiban")) {                
					if(textseiban.getText().equals(str_seiban))
					{
					}
					else
					{
					     formProperties[j].setStringValueData(textseiban.getText());
					}
				} else if (string.equals("s4Projtype")) {
					formProperties[j].setStringValueData(projCheckbox
							.getSelectedItem().toString());
				} else if (string.equals("s4teamevent")) {
					formProperties[j].setStringValueData(teamCheckbox
							.getSelectedItem().toString());
				}
			}
			// prjName.setStringValueData(textPrjName.getText());
		  // prjId.setStringValueData(textPrjId.getText());                 //�޸��˵ĵط�    �������
			// prjClass.setStringValueData(textPrjClass.getText());
			// prjMan.setStringValueData(textPrjMan.getText());
			prjContent.setStringValueData(textPrjContent.getText());
			adoStand.setStringValueData(textAdoStand.getText());

			if (tableJinDuAnPai.getCellEditor() != null) {
				tableJinDuAnPai.getCellEditor().stopCellEditing();
			}
			strStartT = new String[tableJinDuAnPai.getRowCount()];
			for (int i = 0; i < tableJinDuAnPai.getRowCount(); i++) {
				if ((String) tableJinDuAnPai.getValueAt(i, 2) == null) {
					strStartT[i] = "";
				} else {
					strStartT[i] = (String) tableJinDuAnPai.getValueAt(i, 2);
				}

			}
			strEndT = new String[tableJinDuAnPai.getRowCount()];
			for (int i = 0; i < tableJinDuAnPai.getRowCount(); i++) {
				if ((String) tableJinDuAnPai.getValueAt(i, 3) == null) {
					strEndT[i] = "";
				} else {
					strEndT[i] = (String) tableJinDuAnPai.getValueAt(i, 3);
				}

			}
			startT.setStringValueArray(strStartT);
			endT.setStringValueArray(strEndT);

			if (tableXiangMuZuRenYuan.getCellEditor() != null) {
				tableXiangMuZuRenYuan.getCellEditor().stopCellEditing();
			}
			strName = new String[tableXiangMuZuRenYuan.getRowCount()];
			strSex = new String[tableXiangMuZuRenYuan.getRowCount()];
			strTitle = new String[tableXiangMuZuRenYuan.getRowCount()];
			strAge = new String[tableXiangMuZuRenYuan.getRowCount()];
			strMainWork = new String[tableXiangMuZuRenYuan.getRowCount()];
			strInMonth = new String[tableXiangMuZuRenYuan.getRowCount()];
			strRemark = new String[tableXiangMuZuRenYuan.getRowCount()];
			for (int i = 0; i < tableXiangMuZuRenYuan.getRowCount(); i++) {
				if ((String) tableXiangMuZuRenYuan.getValueAt(i, 0) == null) {
					strName[i] = "";
				} else {
					strName[i] = (String) tableXiangMuZuRenYuan
							.getValueAt(i, 0);
				}
				if ((String) tableXiangMuZuRenYuan.getValueAt(i, 1) == null) {
					strSex[i] = "";
				} else {
					strSex[i] = (String) tableXiangMuZuRenYuan.getValueAt(i, 1);
				}
				if ((String) tableXiangMuZuRenYuan.getValueAt(i, 2) == null) {
					strTitle[i] = "";
				} else {
					strTitle[i] = (String) tableXiangMuZuRenYuan.getValueAt(i,
							2);
				}
				if ((String) tableXiangMuZuRenYuan.getValueAt(i, 3) == null) {
					strAge[i] = "";
				} else {
					strAge[i] = (String) tableXiangMuZuRenYuan.getValueAt(i, 3);
				}
				if ((String) tableXiangMuZuRenYuan.getValueAt(i, 4) == null) {
					strMainWork[i] = "";
				} else {
					strMainWork[i] = (String) tableXiangMuZuRenYuan.getValueAt(
							i, 4);
				}
				if ((String) tableXiangMuZuRenYuan.getValueAt(i, 5) == null) {
					strInMonth[i] = "";
				} else {
					strInMonth[i] = (String) tableXiangMuZuRenYuan.getValueAt(
							i, 5);
				}
				if ((String) tableXiangMuZuRenYuan.getValueAt(i, 6) == null) {
					strRemark[i] = "";
				} else {
					strRemark[i] = (String) tableXiangMuZuRenYuan.getValueAt(i,
							6);
				}
			}
			name.setStringValueArray(strName);
			sex.setStringValueArray(strSex);
			title.setStringValueArray(strTitle);
			age.setStringValueArray(strAge);
			mainWork.setStringValueArray(strMainWork);
			inMonth.setStringValueArray(strInMonth);
			remark.setStringValueArray(strRemark);

			if (tableJingJiLaiYuan.getCellEditor() != null) {
				tableJingJiLaiYuan.getCellEditor().stopCellEditing();
			}
			StrInta = new String[tableJingJiLaiYuan.getRowCount()];
			StrIntb = new String[tableJingJiLaiYuan.getRowCount()];
			StrIntc = new String[tableJingJiLaiYuan.getRowCount()];
			strRemark1 = new String[tableJingJiLaiYuan.getRowCount()];
			for (int i = 0; i < tableJingJiLaiYuan.getRowCount(); i++) {
				if ((String) tableJingJiLaiYuan.getValueAt(i, 1) == null) {
					if (i == 0) {
						StrInta[i] = "���У�����";
					} else {
						StrInta[i] = "";
					}
				} else {
					StrInta[i] = (String) tableJingJiLaiYuan.getValueAt(i, 1);
				}
				if ((String) tableJingJiLaiYuan.getValueAt(i, 2) == null) {
					if (i == 0) {
						StrIntb[i] = "���У�����";
					} else {
						StrIntb[i] = "";
					}
				} else {
					StrIntb[i] = (String) tableJingJiLaiYuan.getValueAt(i, 2);
				}
				if ((String) tableJingJiLaiYuan.getValueAt(i, 3) == null) {
					if (i == 0) {
						StrIntc[0] = "���У�����";
					} else {
						StrIntc[i] = "";
					}
				} else {
					StrIntc[i] = (String) tableJingJiLaiYuan.getValueAt(i, 3);
				}
				if ((String) tableJingJiLaiYuan.getValueAt(i, 4) == null) {
					strRemark1[i] = "";
				} else {
					strRemark1[i] = (String) tableJingJiLaiYuan
							.getValueAt(i, 4);
				}
			}
			ina.setStringValueArray(StrInta);
			inb.setStringValueArray(StrIntb);
			inc.setStringValueArray(StrIntc);
			remark1.setStringValueArray(strRemark1);

			if (tableYuSuanZhiChu.getCellEditor() != null) {
				tableYuSuanZhiChu.getCellEditor().stopCellEditing();
			}
			strPreStage = new String[tableYuSuanZhiChu.getRowCount()];
			strPrjDesign = new String[tableYuSuanZhiChu.getRowCount()];
			strProTest = new String[tableYuSuanZhiChu.getRowCount()];
			strStandC = new String[tableYuSuanZhiChu.getRowCount()];
			strDesignCom = new String[tableYuSuanZhiChu.getRowCount()];
			strCbareson = new String[tableYuSuanZhiChu.getRowCount()];
			for (int i = 0; i < tableYuSuanZhiChu.getRowCount(); i++) {
				if ((String) tableYuSuanZhiChu.getValueAt(i, 1) == null) {
					strPreStage[i] = "";
				} else {
					strPreStage[i] = (String) tableYuSuanZhiChu
							.getValueAt(i, 1);
				}
				if ((String) tableYuSuanZhiChu.getValueAt(i, 2) == null) {
					strPrjDesign[i] = "";
				} else {
					strPrjDesign[i] = (String) tableYuSuanZhiChu.getValueAt(i,
							2);
				}
				if ((String) tableYuSuanZhiChu.getValueAt(i, 3) == null) {
					strProTest[i] = "";
				} else {
					strProTest[i] = (String) tableYuSuanZhiChu.getValueAt(i, 3);
				}
				if ((String) tableYuSuanZhiChu.getValueAt(i, 4) == null) {
					strStandC[i] = "";
				} else {
					strStandC[i] = (String) tableYuSuanZhiChu.getValueAt(i, 4);
				}
				if ((String) tableYuSuanZhiChu.getValueAt(i, 5) == null) {
					strDesignCom[i] = "";
				} else {
					strDesignCom[i] = (String) tableYuSuanZhiChu.getValueAt(i,
							5);
				}
				if ((String) tableYuSuanZhiChu.getValueAt(i, 6) == null) {
					strCbareson[i] = "";
				} else {
					strCbareson[i] = (String) tableYuSuanZhiChu
							.getValueAt(i, 6);
				}
			}

			/*
			 * if(tableXiangMuYueBao.getCellEditor()!=null){
			 * tableXiangMuYueBao.getCellEditor().stopCellEditing(); }
			 * strXiangmyb = new String[tableXiangMuYueBao.getRowCount()]; for
			 * (int i = 0; i < tableXiangMuYueBao.getRowCount(); i++) {
			 * if((String)tableXiangMuYueBao.getValueAt(i, 0) == null){
			 * strXiangmyb[i] = ""; } else{ strXiangmyb[i] =
			 * (String)tableXiangMuYueBao.getValueAt(i, 0); } }
			 */

			preStage.setStringValueArray(strPreStage);
			prjDesign.setStringValueArray(strPrjDesign);
			proTest.setStringValueArray(strProTest);
			standC.setStringValueArray(strStandC);
			designCom.setStringValueArray(strDesignCom);
			cbareson.setStringValueArray(strCbareson);
			// xiangmuyueb.setStringValueArray(strXiangmyb);

			prjCost.setStringValueData(textPrjCost.getText());
			
			prjName.setStringValueData(item.getProperties(properties_name)[0]);
			
			strfujian=new String[fujiancount];
			for(int i=0;i<fujiancount;i++)
			{  
				strfujian[i]=(String)filelabel[i].getText();
			}
			fujian.setStringValueArray(strfujian);
			System.out.println("**** save ǰ����������*****"+fujiancount);
			

			// proposer.setStringValueData(textProposer.getText());
			// tddHead.setStringValueData(textTddhead.getText());
			// tsection.setStringValueData(textTsection.getText());

			TCProperty[] tcProperty = new TCProperty[24];
			// tcProperty[0] = prjName;
			// tcProperty[1] = prjId;
			// tcProperty[2] = prjClass;
			// tcProperty[3] = prjMan;
			tcProperty[0] = prjContent;
			tcProperty[1] = adoStand;
			tcProperty[2] = startT;
			tcProperty[3] = endT;
			tcProperty[4] = name;
			tcProperty[5] = sex;
			tcProperty[6] = title;
			tcProperty[7] = age;
			tcProperty[8] = mainWork;
			tcProperty[9] = inMonth;
			tcProperty[10] = remark;
			tcProperty[11] = prjCost;
			tcProperty[12] = ina;
			tcProperty[13] = inb;
			tcProperty[14] = inc;
			tcProperty[15] = remark1;
			tcProperty[16] = preStage;
			tcProperty[17] = prjDesign;
			tcProperty[18] = proTest;
			tcProperty[19] = standC;
			tcProperty[20] = designCom;
			tcProperty[21] = cbareson;
			
			tcProperty[22] = fujian;
			tcProperty[23] = prjName;
		//	tcProperty[22] = prjId;
			// tcProperty[25] = proposer;
			// tcProperty[26] = tddHead;
			// tcProperty[27] = tsection;
			// tcProperty[28] = xiangmuyueb;
			form.setTCProperties(formProperties);
			form.setTCProperties(tcProperty);
			
			
			for(int i=0;i<fujiancount;i++){
				
				AbstractAIFApplication app= AIFUtility.getCurrentApplication();
				TCSession session=(TCSession)app.getSession();
				TCComponentDatasetType tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("Dataset");
				TCComponentDataset predataset=tccomponentDatasetType.find(filelabel[i].getText());
				if(predataset==null)
				try{

				//	AbstractAIFApplication app= AIFUtility.getCurrentApplication();
				//  TCComponentItemRevision mItemRevision=(TCComponentItemRevision)app.getTargetContext().getParentComponent();
				//  TCSession session=(TCSession)app.getSession();
				//	TCComponentDatasetType tccomponentDatasetType=null;
					if(filelabel[i].getText().endsWith(".doc"))
			        tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("MSWord");
					else	if(filelabel[i].getText().endsWith(".docx"))
					tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("MSWordX");	
					
					else	if(filelabel[i].getText().endsWith(".xls"))
				        tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("MSExcel");
					else	if(filelabel[i].getText().endsWith(".xlsx"))
				        tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("MSExcelX");
					
					else	if(filelabel[i].getPath().toString().endsWith(".txt"))
				        tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("Text");
					else	
				        tccomponentDatasetType=(TCComponentDatasetType) session.getTypeComponent("Text");
				//	NewDatasetCommand datasetCmd=new NewDatasetCommand(session,s4CSQXBGRevisionUI.filelabel[i].getText(),"",s4CSQXBGRevisionUI.filelabel[i].getText(),"",null,tccomponentDatasetType.toString(),null);
				//	NewDatasetOperation datasetOp=new NewDatasetOperation(datasetCmd);
				//	datasetOp.executeOperation();
				//	TCComponentDataset dataset =(TCComponentDataset) datasetOp.getNewDataset();	
					
			   TCComponentDataset dataset =tccomponentDatasetType.create(filelabel[i].getText(), tccomponentDatasetType.toString(),  tccomponentDatasetType.toString());
	           ImportFilesOperation fileOp=null;
	            if(filelabel[i].getText().endsWith(".doc"))
	        	    fileOp=new ImportFilesOperation(dataset,filelabel[i].getPath(),"MSWord","BINARY","word",null); 
	            else   if(filelabel[i].getText().endsWith(".docx"))
	        	    fileOp=new ImportFilesOperation(dataset,filelabel[i].getPath(),"MSWordX","BINARY","word",null);  
	            
	            else	if(filelabel[i].getText().endsWith(".xls"))
					fileOp=new ImportFilesOperation(dataset,filelabel[i].getPath(),"MSExcel","BINARY","excel",null); 
	            else	if(filelabel[i].getText().endsWith(".xlsx"))
					fileOp=new ImportFilesOperation(dataset,filelabel[i].getPath(),"MSExcelX","BINARY","excel",null);  
	            else	if(filelabel[i].getText().endsWith(".txt"))
					fileOp=new ImportFilesOperation(dataset,filelabel[i].getPath(),"Text","BINARY","Text",null);  
	            else	
		        	    fileOp=new ImportFilesOperation(dataset,filelabel[i].getPath(),"Text","BINARY","Text",null); 

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

	/*
	 * �ݹ��ȡ������Folder
	 */
	public void getAllFolders(TCComponentFolder folder) {
		try {
			String folder_name = folder.toString();
			Vector<String> vec_item = new Vector<String>();
			vec_item = map_1.get(folder_name);
			TCComponent[] coms = folder.getRelatedComponents("contents");
			Vector<String> vec_item_name = new Vector<String>();
			Vector<String> vec_item_type = new Vector<String>();
			if (coms != null && coms.length > 0) {
				for (int j = 0; j < coms.length; j++) {
					if (coms[j] instanceof TCComponentItem) {
						TCComponentItem item = (TCComponentItem) coms[j];
						String item_name = item.getProperty("object_name")
								.toString();
						String item_type = item.getType().toString();
						// �������ѡ���������������ĵ�,�����ж���û�����ĵ��ṹ�д���
						if (vec_item != null) {
							for (int i = 0; i < vec_item.size(); i++) {
								String str = vec_item.get(i);
								String[] s = str.split("=");
								if (!/* item_name.equals(s[0]) && */item_type
										.equals(s[1])) {
									flag = true;
									if (!v_folder.contains(folder)) {
										v_folder.add(folder);
									}
								}
							}
						}
						vec_item_name.add(item_name);
						vec_item_type.add(item_type);
					} else if (coms[j] instanceof TCComponentFolder) {
						TCComponentFolder subFolder = (TCComponentFolder) coms[j];
						getAllFolders(subFolder);
					}
				}
			} else {
				if (!v_folder.contains(folder)) {
					v_folder.add(folder);
				}
			}
			map_2.put(folder_name, vec_item_name);
			map_3.put(folder_name, vec_item_type);
		} catch (TCException e) {
			e.printStackTrace();
		}
	}

	// �������ͺ����ƴ������������
	private void createItem(String type, String name, TCComponentFolder parent) {
		try {
			TCComponentItemType itemType = (TCComponentItemType) session
					.getTypeComponent(type);
			String itemId = itemType.getNewID(); // ��ȡ����
			String itemRev = itemType.getNewRev(null); // ��ȡ�汾����
			TCComponentItem item = itemType.create(itemId, itemRev, type, name,
					"", null);
			if (item != null)
				parent.add("contents", item);
		} catch (TCException e) {
			e.printStackTrace();
		}
	}

	public void initUI() {
		setLayout(new GridLayout(1, 1));
		jTabbedPane = new JTabbedPane(JTabbedPane.NORTH);
		jTabbedPane.setBounds(0, 0, 800, 800);

		jTabbedPane.addTab("��Ŀ��������", firstPanel());
		jTabbedPane.addTab("��Ŀ����", SecondPanel());
		add(jTabbedPane);

	}

	public JPanel firstPanel() {
		// JPanel panel_all = new JPanel(new PropertyLayout());
		JPanel panel_all = new JPanel();
		panel_all.setLayout(new BoxLayout(panel_all, BoxLayout.Y_AXIS));
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panelOne = new JPanel();
		panelOne.setLayout(new BoxLayout(panelOne, BoxLayout.X_AXIS));
		// JPanel panelOne = new JPanel(new GridLayout(4, 5));
		// TitledBorder titleBorder = BorderFactory.createTitledBorder("");
		// titleBorder.setTitlePosition(2);
		// panelOne.setBorder(titleBorder);
		JPanel panelOne1 = new JPanel(new PropertyLayout());
		JPanel panelOne2 = new JPanel(new PropertyLayout());
		JPanel panelOne3 = new JPanel(new PropertyLayout());

		JLabel lbPrjName = new JLabel("  ��Ŀ����");
		textPrjName = new SACJTextField128(70);
		textPrjName.setEnabled(false);
		JLabel lbPrjId = new JLabel(" �������");
		textPrjId = new SACJTextField32(74);
		textPrjId.setEnabled(true);                         //�����޸ĵĵط�
		/*
		 * JLabel lbPrjClass = new JLabel("  ���Ʋ���"); textPrjClass = new
		 * SACJTextField128(70); JLabel lbPrjMan = new JLabel(" ��Ŀ������");
		 * textPrjMan = new SACJTextField32(74);
		 */
		JLabel lbXmlx = new JLabel("  ��Ŀ����*");
		projCheckbox = new JComboBox(proj_str);
		projCheckbox.setPreferredSize(new Dimension(410, 23));
		JLabel lbJtxm = new JLabel(" ������Ŀ*");
		teamCheckbox = new JComboBox(team_str);
		component_map.put("������Ŀ", teamCheckbox);
		teamCheckbox.setPreferredSize(new Dimension(62, 23));
		JLabel lbseiban = new JLabel(" seiban��");
		textseiban = new SACJTextField32(53);
		textseiban.setText(str_seiban);
		textseiban.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (str_seiban.equals(textseiban.getText().toString())) {
					textseiban.setText("");
				}

			}
		});
		
		
		
		
		
		
		JLabel lbKong = new JLabel("  ");

		panelOne1.add("1.1.rihgt", lbPrjName);
		panelOne1.add("1.2.rihgt", textPrjName);
		// panelOne1.add("2.1.rihgt",lbPrjClass);
		// panelOne1.add("2.2.rihgt",textPrjClass);
		panelOne1.add("2.1.rihgt", lbXmlx);
		panelOne1.add("2.2.rihgt", projCheckbox);

		panelOne2.add("1.1.rihgt", lbKong);

		panelOne3.add("1.1.rihgt", lbPrjId);
		panelOne3.add("1.2.rihgt", textPrjId);
		// panelOne3.add("2.1.rihgt",lbPrjMan);
		// panelOne3.add("2.2.rihgt",textPrjMan);
		panelOne3.add("2.1.rihgt", lbJtxm);
		panelOne3.add("2.2.rihgt", teamCheckbox);
		panelOne3.add("2.3.rihgt", lbseiban);
		panelOne3.add("2.4.rihgt", textseiban);

		panelOne.add("1.1.rihgt", panelOne1);
		panelOne.add("1.2.rihgt", panelOne2);
		panelOne.add("1.3.rihgt", panelOne3);

		font = new Font("����", Font.BOLD, 12);
		JPanel panelFive = new JPanel(new GridLayout(1, 1));
		TitledBorder titleBorderFive = BorderFactory.createTitledBorder("");
		titleBorderFive.setTitlePosition(2);
		panelFive.setBorder(titleBorderFive);
		// ��Ŀ������
		JPanel xmjk_Panel = bulidxmjk_Panel();
		// xmjk_Panel.setPreferredSize(new Dimension(1200,350));
		// ������Ŀ�����ǩ
		TitledBorder xmjkBorder = BorderFactory.createTitledBorder("��Ŀ���");
		xmjkBorder.setTitlePosition(TitledBorder.TOP);
		xmjkBorder.setTitleFont(font);
		xmjk_Panel.setBorder(xmjkBorder);
		panelFive.add(xmjk_Panel);

		JPanel panelSix = new JPanel(new GridLayout(1, 1));
		TitledBorder titleBorderSix = BorderFactory.createTitledBorder("");
		titleBorderSix.setTitlePosition(2);
		panelSix.setBorder(titleBorderSix);
		// ��Ŀ���������
		JPanel xmfzr_Panel = bulidxmfzr_Panel();
		// ������Ŀ�����˱�ǩ
		TitledBorder xmfzrBorder = BorderFactory.createTitledBorder("��Ŀ������");
		xmfzrBorder.setTitlePosition(TitledBorder.TOP);
		xmfzrBorder.setTitleFont(font);
		xmfzr_Panel.setBorder(xmfzrBorder);
		panelSix.add(xmfzr_Panel);

		JPanel panelTwo = new JPanel(new GridLayout(2, 1, 2, 2));
		TitledBorder titleBorderPrjContent = BorderFactory
				.createTitledBorder("��Ŀ����*");
		titleBorderPrjContent.setTitleFont(font);
		titleBorderPrjContent.setTitlePosition(2);
		panelTwo.setBorder(titleBorderPrjContent);
		textPrjContent = new JTextArea(5, 20);
		component_map.put("��Ŀ����", textPrjContent);
		textPrjContent.setLineWrap(true);
		textPrjContent.setText(tixing_str);
		textPrjContent.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (tixing_str.equals(textPrjContent.getText().toString())) {
					textPrjContent.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

		});
		// ������Ŀ�����������ֵ
		SACDocument4000 prjContent_doc = new SACDocument4000();
		textPrjContent.setDocument(prjContent_doc);
		JScrollPane textPrjContent_scrollPane = new JScrollPane(textPrjContent);
		textAdoStand = new JTextArea(5, 10);
		component_map.put("���ñ�׼", textAdoStand);
		textAdoStand.setText("���ñ�׼��" + tixing_str);
		textAdoStand.setLineWrap(true);
		textAdoStand.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (("���ñ�׼��" + tixing_str).equals(textAdoStand.getText()
						.toString())) {
					textAdoStand.setText("���ñ�׼��");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

		});
		// ���ò��ñ�׼�������ֵ
		SACDocument4000 adoStand_doc = new SACDocument4000();
		textAdoStand.setDocument(adoStand_doc);
		JScrollPane textAdoStand_scrollPane = new JScrollPane(textAdoStand);
		panelTwo.add(textPrjContent_scrollPane);
		panelTwo.add(textAdoStand_scrollPane);

		JPanel panelThree = new JPanel(new GridLayout(1, 0));
		TitledBorder titleBorderJinDuAnPai = BorderFactory
				.createTitledBorder("���Ȱ���*");
		titleBorderJinDuAnPai.setTitleFont(font);
		titleBorderJinDuAnPai.setTitlePosition(2);
		panelThree.setBorder(titleBorderJinDuAnPai);
		Object[] columnNamesJinDuAnPai = { "�׶�*", "�׶�����*", "��ʼʱ��*", "��ֹʱ��*" };
		Object[][] dataJinDuAnPai = { { "A", "����׶�", "", "" },
				{ "B", "��ƽ׶�", "", "" }, { "C", "�������ƽ׶�", "", "" },
				{ "D", "���Լ����н׶�", "", "" }, { "E", "��Ŀ����", "", "" } };

		DefaultTableModel tableModel = new DefaultTableModel(dataJinDuAnPai,
				columnNamesJinDuAnPai) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column < 2) {
					return false;
				} else {
					return true;
				}
			}
		};
		tableJinDuAnPai = new JTable(tableModel);
		tableJinDuAnPai.getTableHeader().setReorderingAllowed(false);
		tableJinDuAnPai.setRowHeight(20);
		// tableJinDuAnPai.setValueAt("2012-08-10", 0, 2);
		tableJinDuAnPai.setPreferredScrollableViewportSize(new Dimension(500,
				105));
		// tableJinDuAnPai.getColumnModel().getColumn(2).setCellEditor(new
		// SACTextFieldEditorSJ(tableJinDuAnPai));
		// tableJinDuAnPai.getColumnModel().getColumn(3).setCellEditor(new
		// SACTextFieldEditorSJ(tableJinDuAnPai));
		for (int i = 0; i < 4; i++) {
			if (i == 2 || i == 3) {
				tableJinDuAnPai.getColumnModel().getColumn(i).setCellEditor(
						new SACTextFieldEditorSJ(tableJinDuAnPai));
				// tableJinDuAnPai.getColumnModel().getColumn(i).setCellRenderer(new
				// SAC_TableCellRenderer());
			}
		}

		JScrollPane scrollPaneJinDuAnPai = new JScrollPane(tableJinDuAnPai);
		panelThree.add(scrollPaneJinDuAnPai);

		// JPanel panelFour = new JPanel(new GridLayout(2, 1));
		JPanel panelFour = new JPanel();
		panelFour.setLayout(new BoxLayout(panelFour, BoxLayout.Y_AXIS));
		TitledBorder titleBorderXiangMuZuRenYuan = BorderFactory
				.createTitledBorder("��Ŀ����Ա");
		titleBorderXiangMuZuRenYuan.setTitleFont(font);
		titleBorderXiangMuZuRenYuan.setTitlePosition(2);
		panelFour.setBorder(titleBorderXiangMuZuRenYuan);
		Object[] columnNamesXiangMuZuRenYuan = { "����*", "�Ա�*", "ְ��*", "����*",
				"�е�����Ŀ��Ҫ����*", "Ͷ������*", "��ע" };
		Object[][] dataXiangMuZuRenYuan = new Object[num][7];
		tableModelTwo = new DefaultTableModel(dataXiangMuZuRenYuan,
				columnNamesXiangMuZuRenYuan);
		tableXiangMuZuRenYuan = new JTable(tableModelTwo);
		tableXiangMuZuRenYuan.getTableHeader().setReorderingAllowed(false);
		tableXiangMuZuRenYuan.getColumnModel().getColumn(4).setPreferredWidth(
				180);
		tableXiangMuZuRenYuan.getColumnModel().getColumn(6).setPreferredWidth(
				250);
		// tableXiangMuZuRenYuan.setRowHeight(50);
		tableXiangMuZuRenYuan.setPreferredScrollableViewportSize(new Dimension(
				500, 180));
		for (int i = 0; i < 7; i++) {
			if (i == 4) {
				tableXiangMuZuRenYuan
						.getColumnModel()
						.getColumn(i)
						.setCellEditor(
								new SACTextFieldEditor64(tableXiangMuZuRenYuan));
			} else if (i == 6) {
				tableXiangMuZuRenYuan
						.getColumnModel()
						.getColumn(i)
						.setCellEditor(
								new SACTextAreaEditor1280(tableXiangMuZuRenYuan));
				tableXiangMuZuRenYuan.getColumnModel().getColumn(i)
						.setCellRenderer(new SACGridBagRenderer());
			} else if (i == 2) {
				tableXiangMuZuRenYuan.getColumnModel().getColumn(i)
						.setCellEditor(
								new SACTextFieldEditorLov(
										tableXiangMuZuRenYuan, zc_str));
			} else {
				tableXiangMuZuRenYuan
						.getColumnModel()
						.getColumn(i)
						.setCellEditor(
								new SACTextFieldEditor32(tableXiangMuZuRenYuan));
			}
		}
		JScrollPane scrollPaneXiangMuZuRenYuan = new JScrollPane(
				tableXiangMuZuRenYuan);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		JButton addButton = new JButton("�����");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] newRow = { "", "", "", "", "", "", "" };
				tableModelTwo.addRow(newRow);
				// tableXiangMuZuRenYuan = new JTable(tableModelTwo);
			}
		});
		JButton deleteButton = new JButton("ɾ����");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRow = tableXiangMuZuRenYuan.getSelectedRows();// ���ѡ���е�����
				for (int i = 0; i < selectedRow.length; i++) {
					if (selectedRow[i] != -1) // ����ѡ����
					{
						tableModelTwo.removeRow(tableXiangMuZuRenYuan
								.getSelectedRow()); // ɾ����
						// tableXiangMuZuRenYuan = new JTable(tableModelTwo);
					}
				}
			}
		});
		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);

		
		TitledBorder titleBorderPingShenJieLunFuJian = BorderFactory.createTitledBorder("������۸���");
		titleBorderPingShenJieLunFuJian.setTitleFont(font);
		titleBorderPingShenJieLunFuJian.setTitlePosition(2);
		panel3.setBorder(titleBorderPingShenJieLunFuJian);
		JPanel panel3_explore=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton explore_button=new JButton("���");
			explore_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fDialog=new JFileChooser();
				fDialog.setDialogTitle("��ѡ������ӵĸ���");
				fDialog.setApproveButtonText("��");
				fDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if(JFileChooser.APPROVE_OPTION==fDialog.showOpenDialog(new Frame())){
					for(int i=0;i<fujiancount;i++){
						System.out.println(filelabel[i].getText());
						if(filelabel[i].getText().equals(fDialog.getSelectedFile().getName()))
						{
							JOptionPane.showMessageDialog(null, "��ܰ��ʾ��������ӵ��ļ��Ѵ��ڣ����������������","��ʾ",JOptionPane.ERROR_MESSAGE);
							return;

						}
					}
					path=fDialog.getSelectedFile().getPath();
					filelabel[fujiancount]=new LinkLabel(fDialog.getSelectedFile().getName(),new File(path));
					filelabel[fujiancount].setForeground(Color.BLUE);
					p[fujiancount]=new JPanel(new FlowLayout(FlowLayout.LEFT));
					p[fujiancount].add(filelabel[fujiancount]);
					final JLabel label_delete=new JLabel("ɾ��");
					p[fujiancount].add(label_delete);
					accessoryPanel.setLayout(new GridLayout(accessoryPanel.getComponentCount()+1,1));//��������Panel�Ĳ���
					accessoryPanel.add(p[fujiancount]);
					label_delete.addMouseListener(new MouseAdapter(){
					public void mouseExited(MouseEvent e) {  
						label_delete.setForeground(Color.BLACK);
								    	  }                     
					public void mouseEntered(MouseEvent e) { 
						label_delete.setForeground(Color.RED);
						label_delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
						
						public void mouseClicked(MouseEvent e) {
						//	JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ���ø�����","ɾ������",JOptionPane.YES_NO_OPTION);
							int result=JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ���ø�����","ɾ������",JOptionPane.YES_NO_OPTION);
							if(result==JOptionPane.YES_OPTION){
							accessoryPanel.setLayout(new GridLayout(accessoryPanel.getComponentCount()-1,1));//��������Panel�Ĳ���
							int j=0;
							for(int i=0;i<fujiancount;i++)
								if(filelabel[i].getParent()==e.getComponent().getParent())
									{
									   j=i;//ѡ��ɾ�����Ǹ�����  
									   break;
									}
							for(;j<fujiancount-1;j++){
								filelabel[j].setText(filelabel[j+1].getText());
								filelabel[j].setPath(filelabel[j+1].getPath());
								//filelabel[j]=new LinkLabel(filelabel[j+1].getText(),filelabel[j+1].getPath());
								//filelabel[j].setSize(j-1);
								System.out.println("ɾ������һ����������Ϊ��"+filelabel[j].getText());
								
							}
						
							//accessoryPanel.remove(p);
							fujiancount--;
							accessoryPanel.remove(p[fujiancount]);
							accessoryPanel.setLayout(new GridLayout(fujiancount,1));
						//	accessoryPanel.repaint();
						    jTabbedPane.repaint();//ʵʱˢ�±�ǩ�ϵ�����
						//	jTabbedPane.getParent().repaint();
						//   jTabbedPane.getParent().setPreferredSize(new Dimension(accessoryPanel.getWidth(),accessoryPanel.getHeight()));
							//	jTabbedPane.setBounds(0, 0, 800, 800);
						//	jTabbedPane.setBounds(0, 0, 800, 800);
							jTabbedPane.setBounds(0, 0, jTabbedPane.getWidth(), jTabbedPane.getHeight());
					
							}
					}
						}
					);
					fujiancount++;
					jTabbedPane.repaint();//ʵʱˢ�±�ǩ�ϵ�����

				}
				
			}
			});
			panel3_explore.add(explore_button);
			panel3.add(panel3_explore,BorderLayout.NORTH);
			panel3.add(accessoryPanel,BorderLayout.CENTER);
		
		
		JLabel lbZhushi = new JLabel(
				"ע������Ͷ�������㣬Ҫ������Ŀ��ģ����Աͬʱ�������Ŀ���������Ǳ���Ŀ��Ա��Ͷ�������     "+"\n"+   "          ��*�ŵ�Ϊ����ѡ��");
		lbZhushi.setPreferredSize(new Dimension(300, 15));
		panelFour.add(scrollPaneXiangMuZuRenYuan);
		panelFour.add(buttonPanel);
		
		panelFour.add(panel3);
		panelFour.add(lbZhushi);

		/*
		 * JPanel panelSeven = new JPanel(); panelSeven.setLayout(new
		 * BoxLayout(panelSeven, BoxLayout.Y_AXIS)); TitledBorder
		 * titleBorderSeven = BorderFactory.createTitledBorder("��Ŀ�±�");
		 * titleBorderSeven.setTitlePosition(2);
		 * panelSeven.setBorder(titleBorderSeven); Object[]
		 * columnNamesXiangMuYueBao = {"��Ŀ�±�"}; Object[][] dataXiangMuYueBao =
		 * new Object[num1][1]; tableModelThree = new
		 * DefaultTableModel(dataXiangMuYueBao,columnNamesXiangMuYueBao);
		 * tableXiangMuYueBao = new JTable(tableModelThree);
		 * tableXiangMuYueBao.getTableHeader().setReorderingAllowed(false); //
		 * tableXiangMuYueBao
		 * .getColumnModel().getColumn(0).setPreferredWidth(180);
		 * tableXiangMuYueBao.setRowHeight(20);
		 * tableXiangMuYueBao.setPreferredScrollableViewportSize(new
		 * Dimension(500, 100)); JScrollPane scrollPaneXiangMuYueBao = new
		 * JScrollPane(tableXiangMuYueBao);
		 * 
		 * JPanel buttonPanel1 = new JPanel(new GridLayout(1, 2)); JButton
		 * addButton1 = new JButton("�����"); addButton1.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) {
		 * String[] newRow = {""}; tableModelThree.addRow(newRow); } }); JButton
		 * deleteButton1 = new JButton("ɾ����");
		 * deleteButton1.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { int[] selectedRow =
		 * tableXiangMuYueBao.getSelectedRows();//���ѡ���е����� for(int i = 0; i <
		 * selectedRow.length; i++){ if(selectedRow[i]!=-1) //����ѡ���� {
		 * tableModelThree.removeRow(tableXiangMuYueBao.getSelectedRow()); //ɾ����
		 * } } } }); buttonPanel1.add(addButton1);
		 * buttonPanel1.add(deleteButton1);
		 * 
		 * panelSeven.add(scrollPaneXiangMuYueBao);
		 * panelSeven.add(buttonPanel1);
		 */

		panel.add(panelSix);
		panel.add(panelFive);
		panel.add(panelTwo);
		panel.add(panelThree);
		panel.add(panelFour);
		// panel.add(panelSeven);
		
	//	panel_all.add("1.1.left", panelOne);
	//	panel_all.add("2.1.left", panel);
		
		JPanel ptemp0 = new JPanel();
		ptemp0.setLayout(new BoxLayout(ptemp0, BoxLayout.Y_AXIS));
		
		ptemp0.add("1.1.left", panelOne);
		ptemp0.add("2.1.left", panel);
		
		JScrollPane sp=new JScrollPane(ptemp0);
		
		panel_all.add(sp);

		return panel_all;
	}

	JLabel lbPrjCost;

	public JPanel SecondPanel() {
		// JPanel panel = new JPanel(new GridLayout(3, 1));
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panelOne = new JPanel(new PropertyLayout());
		// panelOne.setPreferredSize(new Dimension(800, 30));
		// TitledBorder titleBorder = BorderFactory.createTitledBorder("");
		// titleBorder.setTitlePosition(2);
		// panelOne.setBorder(titleBorder);
		lbPrjCost = new JLabel("    ��Ŀ�ܷ���*��");

		textPrjCost = new SACJTextField32(25);
		component_map.put("��Ŀ�ܷ���", textPrjCost);
		JLabel lbCost = new JLabel("  ��Ԫ ");
		panelOne.add("1.1.left", new JLabel(""));
		panelOne.add("2.1.left", lbPrjCost);
		panelOne.add("2.2.left", textPrjCost);
		panelOne.add("2.3.left", lbCost);

		JPanel panelTwo = new JPanel();
		panelTwo.setLayout(new BoxLayout(panelTwo, BoxLayout.Y_AXIS));
		TitledBorder titleBorderJingJiLaiYuan = BorderFactory
				.createTitledBorder("");
		titleBorderJingJiLaiYuan.setTitlePosition(2);
		panelTwo.setBorder(titleBorderJingJiLaiYuan);
		Object[] columnNamesJingJiLaiYuan = { "", "", "", "", "" };
		Object[][] dataJingJiLaiYuan = {
				{ "������Դ", "���У�����*", "���У�����", "���У�����", "��ע" },
				{ "�����ܹ�˾����", "", "", "", "" }, { "���빫˾������", "", "", "", "" },
				{ "���Ʋ����Գ���", "", "", "", "" }, { "��    ��", "", "", "", "" } };

		DefaultTableCellRenderer dtc = new DefaultTableCellRenderer() {

			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				if (row == 0) {
					// setForeground(Color.GRAY);
					setBackground(lbPrjCost.getBackground());
				} else {
					setBackground(Color.WHITE);
				}
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);

			}
		};

		DefaultTableModel tableModel = new DefaultTableModel(dataJingJiLaiYuan,
				columnNamesJingJiLaiYuan) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column < 1 || (row == 0 && column == 4)) {
					return false;
				} else {
					return true;
				}
			}
		};
		tableJingJiLaiYuan = new JTable(tableModel);
		tableJingJiLaiYuan.getTableHeader().setReorderingAllowed(false);
		tableJingJiLaiYuan.getColumnModel().getColumn(0).setCellRenderer(dtc);
		tableJingJiLaiYuan.getColumnModel().getColumn(1).setCellRenderer(dtc);
		tableJingJiLaiYuan.getColumnModel().getColumn(2).setCellRenderer(dtc);
		tableJingJiLaiYuan.getColumnModel().getColumn(3).setCellRenderer(dtc);
		tableJingJiLaiYuan.getColumnModel().getColumn(4).setCellRenderer(dtc);
		tableJingJiLaiYuan.getColumnModel().getColumn(4).setPreferredWidth(300);
		tableJingJiLaiYuan.setPreferredScrollableViewportSize(new Dimension(
				1000, 145));
		tableJingJiLaiYuan.setRowHeight(0, 20);
		for (int i = 1; i < tableJingJiLaiYuan.getRowCount(); i++) {
			tableJingJiLaiYuan.setRowHeight(i, 50);
		}
		JScrollPane scrollPaneJinDuAnPai = new JScrollPane(tableJingJiLaiYuan);
		for (int i = 0; i < 5; i++) {
			if (i == 4) {
				tableJingJiLaiYuan.getColumnModel().getColumn(i).setCellEditor(
						new SACTextAreaEditor1280(tableJingJiLaiYuan));
				tableJingJiLaiYuan.getColumnModel().getColumn(i)
						.setCellRenderer(
								new SACGridBagLaiYRenderer(lbPrjCost
										.getBackground()));
			} else {
				tableJingJiLaiYuan.getColumnModel().getColumn(i).setCellEditor(
						new SACTextFieldEditor32(tableJingJiLaiYuan));
			}
		}
		panelTwo.add(scrollPaneJinDuAnPai);

		JPanel panelThree = new JPanel(new GridLayout(1, 0));
		TitledBorder titleBorderYuSuanZhiChu = BorderFactory
				.createTitledBorder("");
		titleBorderYuSuanZhiChu.setTitlePosition(2);
		panelThree.setBorder(titleBorderYuSuanZhiChu);
		Object[] columnNamesYuSuanZhiChu = { "Ԥ��֧����Ŀ", "Ԥ�н׶�(��Ԫ)",
				"�������(��Ԫ)", "��������(��Ԫ)", "�����������(��Ԫ)", "���ȷ��(��Ԫ)", "������ݼ�����" };
		Object[][] dataXiangYuSuanZhiChu = {
				{ "��һ��ֱ�ӷ���", "", "", "", "", "", "" },
				{ "1.��Ա��", "", "", "", "", "", "" },
				{ " ��1���о���Ա����", "", "", "", "", "", "" },
				{ " ��2����ʱ������", "", "", "", "", "", "" },
				{ "2.�豸�������", "", "", "", "", "", "" },
				{ " ��1������", "", "", "", "", "", "������������˴���" },
				{ " ��2������", "", "", "", "", "", "" },
				{ "3.ҵ���", "", "", "", "", "", "" },
				{ " ��1�����ϡ�Ԫ��������", "", "", "", "", "", "������������˴���" },
				{ " ��2�������ӹ���", "", "", "", "", "", "" },
				{ " ��3�����������", "", "", "", "", "", "������������˴���" },
				{ " ��4�����Ϸ�", "", "", "", "", "", "" },
				{ " ��5�������", "", "", "", "", "", "" },
				{ " ��6�����÷�", "", "", "", "", "", "" },
				{ "4.���̻���Ʒ�", "", "", "", "", "", "" },
				{ "5.����ֱ�ӷ���", "", "", "", "", "", "" },
				{ "��������ӷ���", "", "", "", "", "", "" },
				{ "1.���������豸ʹ�÷�", "", "", "", "", "", "" },
				{ "2.ֱ�ӹ������", "", "", "", "", "", "" },
				{ "3.������ӷ���", "", "", "", "", "", "" },
				{ "�����������о�֧��", "", "", "", "", "", "" },
				{ "1.����֧��1", "", "", "", "", "", "" },
				{ "2.����֧��2", "", "", "", "", "", "" },
				{ "��    ��", "", "", "", "", "", "" } };
		DefaultTableModel tableModelYuSuanZhiChu = new DefaultTableModel(
				dataXiangYuSuanZhiChu, columnNamesYuSuanZhiChu) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column < 1 || (row == 5 && column == 6)
						|| (row == 8 && column == 6)
						|| (row == 10 && column == 6)) {
					return false;
				} else {
					return true;
				}
			}
		};
		tableYuSuanZhiChu = new JTable(tableModelYuSuanZhiChu);
		tableYuSuanZhiChu.getTableHeader().setReorderingAllowed(false);
		tableYuSuanZhiChu.getColumnModel().getColumn(0).setPreferredWidth(140);
		tableYuSuanZhiChu.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableYuSuanZhiChu.setPreferredScrollableViewportSize(new Dimension(
				1000, 410));
		tableYuSuanZhiChu.setRowHeight(20);
		// tableXiangMuZuRenYuan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < 7; i++) {
			if (i == 1) {
				tableYuSuanZhiChu.getColumnModel().getColumn(i).setCellEditor(
						new SACTextFieldEditor1280(tableYuSuanZhiChu));
			} else if (i == 6) {
				tableYuSuanZhiChu.getColumnModel().getColumn(i).setCellEditor(
						new SACTextFieldEditor64(tableYuSuanZhiChu));
			} else {
				tableYuSuanZhiChu.getColumnModel().getColumn(i).setCellEditor(
						new SACTextFieldEditor32(tableYuSuanZhiChu));
			}
		}
		JScrollPane scrollPaneXiangMuZuRenYuan = new JScrollPane(
				tableYuSuanZhiChu);
		tableYuSuanZhiChu.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int row = tableYuSuanZhiChu.getSelectedRow();
					int column = tableYuSuanZhiChu.getSelectedColumn();
					if (row == 5 && column == 6) {
						String form_name = "�豸��������÷�";
						String form_type = "S4SRGZF";
						openform(form_name, form_type);
					} else if (row == 8 && column == 6) {
						String form_name = "�ؼ����ϡ�Ԫ������";
						String form_type = "S4GCYF";
						openform(form_name, form_type);
					} else if (row == 10 && column == 6) {
						String form_name = "���ԡ������";
						String form_type = "S4CSSYF";
						openform(form_name, form_type);
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

		});
		panelThree.add(scrollPaneXiangMuZuRenYuan);

		// JPanel panelFour = new JPanel(new PropertyLayout());
		// panelFour.setSize(new Dimension(800,90));
		// TitledBorder titleBorderA = BorderFactory.createTitledBorder("");
		// titleBorderA.setTitlePosition(2);
		// panelFour.setBorder(titleBorderA);

		// JLabel lbProposer = new JLabel("    ����");
		// textProposer = new SACJTextField32(25);
		// JLabel lbA = new JLabel("��ǩ����");
		// JLabel lbTddhead = new JLabel("���Ʋ��ż���������  ");
		// textTddhead = new SACJTextField32(25);
		// JLabel lbB = new JLabel("��ǩ����");
		// JLabel lbTsection = new JLabel("    ��������");
		// textTsection = new SACJTextField32(25);
		// JLabel lbC = new JLabel("��ǩ����");
		//
		// panelFour.add("1.1.left",new JLabel(""));
		// panelFour.add("2.1.left",lbProposer);
		// panelFour.add("2.2.left",textProposer);
		// panelFour.add("2.3.left",lbA);
		// panelFour.add("2.4.left",new JLabel("        "));
		// panelFour.add("2.5.left",lbTddhead);
		// panelFour.add("2.6.left",textTddhead);
		// panelFour.add("2.7.left",lbB);
		// panelFour.add("3.1.left",lbTsection);
		// panelFour.add("3.2.left",textTsection);
		// panelFour.add("3.3.left",lbC);

		panel.add("1.1.left", panelOne);
		panel.add("2.1.left", panelTwo);
		panel.add("3.1.left", panelThree);
		JPanel panelFour = new JPanel(new GridLayout(1, 0));
		JPanel panelFive = new JPanel(new GridLayout(1, 0));
		JPanel panelsix = new JPanel(new GridLayout(1, 0));
		JPanel panelseven = new JPanel(new GridLayout(1, 0));
		JPanel paneleight = new JPanel(new GridLayout(1, 0));
		JPanel panelnine = new JPanel(new GridLayout(1, 0));
		JPanel panel1 = new JPanel(new GridLayout(1, 0));
		JPanel panel2 = new JPanel(new GridLayout(1, 0));
		JPanel panel3 = new JPanel(new GridLayout(1, 0));
		panel.add("4.1.left", panelFour);
		panel.add("5.1.left", panelFive);
		panel.add("6.1.left", panelsix);
		panel.add("7.1.left", panelseven);
		panel.add("8.1.left", paneleight);
		panel.add("9.1.left", panelnine);
		panel.add("10.1.left", panel1);
		panel.add("11.1.left", panel2);
		panel.add("12.1.left", panel3);

		return panel;
	}

	/**
	 * ������Ӧ���Ԫ���form
	 * */
	public void openform(String form_name, String form_type) {
		try {
			// �����ж���û�����form
			/*
			 * TCComponent[] coms = item.getRelatedComponents(relation);
			 * TCComponentFolder parentFolder = null; if(coms!=null &&
			 * coms.length>0){ for (int i = 0; i < coms.length; i++) {
			 * if("Folder".equals(coms[i].getType().toString())){ parentFolder =
			 * (TCComponentFolder) coms[i]; } }
			 * 
			 * }
			 */
			TCComponent[] forms = item.getRelatedComponents(yc_relation);
			System.out.println("length====>:" + forms.length);
			System.out.println("form_name====>:" + form_name);
			if (forms != null && forms.length > 0) {
				TCComponentForm open_form = null;
				for (int i = 0; i < forms.length; i++) {
					System.out.println("2222----->:"
							+ forms[i].getProperty("object_name").toString());
					// if(form_type(forms[i].getType().toString())){
					if (form_name.equals(forms[i].getProperty("object_name")
							.toString())) {
						open_form = (TCComponentForm) forms[i];
					}
				}
				if (open_form != null) {
					System.out.println("gz_form===>;"
							+ open_form.getProperty("object_name"));
					OpenFormDialog openbzjkjjd = new OpenFormDialog(open_form);
					openbzjkjjd.showDialog();
				} else {
					TCComponentForm create_form = createrjgzfyForm(form_name,
							form_type);
					item.add(yc_relation, create_form);
					OpenFormDialog openbzjkjjd = new OpenFormDialog(create_form);
					openbzjkjjd.showDialog();
				}
			} else {
				TCComponentForm create_form = createrjgzfyForm(form_name,
						form_type);
				item.add(yc_relation, create_form);
				OpenFormDialog openbzjkjjd = new OpenFormDialog(create_form);
				openbzjkjjd.showDialog();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * ������Ŀ�����Panel
	 */
	public JPanel bulidxmjk_Panel() {
		JPanel panel_xmjk = new JPanel();
		panel_xmjk.setLayout(new BoxLayout(panel_xmjk, BoxLayout.Y_AXIS));
		JPanel panel_1 = new JPanel(new PropertyLayout());
		            //�������޸ĵĵط�
		JLabel syfw = new JLabel("���÷�Χ*");
		syfwiTextField = new JTextArea(3, 152);    //���ڽ��еĵط�
		component_map.put("���÷�Χ", syfwiTextField);
		
		syfwiTextField .setLineWrap(true);
		syfwiTextField.setText(syfw_str); 
		SACDocument1280 syfw_doc = new SACDocument1280();
		syfwiTextField.setDocument(syfw_doc);
		syfwiTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (syfw_str.equals(syfwiTextField.getText().toString())) {
					syfwiTextField.setText("");
				}

			}
		});
				
		//syfwiTextField = new SACJTextField1280(152);
		
		/*syfwiTextField.setText(syfw_str);
		syfwiTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (syfw_str.equals(syfwiTextField.getText().toString())) {
					syfwiTextField.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

		});*/
		JLabel qzny = new JLabel("��ʼ����*");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		qznyidatebutton = new DateButton(sdf);
		// qznyidatebutton.setDate(default_date);
		qznyidatebutton.setPreferredSize(new Dimension(420, 22));
		JLabel jzny = new JLabel("��ֹ����*");
		jznyidatebutton = new DateButton(sdf);
		// jznyidatebutton.setDate(default_date);
		jznyidatebutton.setPreferredSize(new Dimension(440, 22));
		JLabel cddwybm = new JLabel("�е���λ*");                      //�޸ĵĵط�
		cddwybmiTextField = new JComboBox(dw_str);;
		component_map.put("�е���λ", cddwybmiTextField);
		JLabel cb_bm = new JLabel("�е�����*");                      //�޸ĵĵط�
		cbdwTextField = new SACJTextField128(70);
		component_map.put("�е�����", cbdwTextField);
		
		
		
		
		JLabel yzfs = new JLabel("���Է�ʽ*");
		ysfsiTextField = new JTextArea(3, 152);                                //��Ҫ�޸ĵĵط�
		component_map.put("���Է�ʽ", ysfsiTextField);
		ysfsiTextField.setLineWrap(true);
		ysfsiTextField.setText(yafs_str);
		// �������Է�ʽ�������ֵ
		SACDocument256 yzfs_doc = new SACDocument256();
		ysfsiTextField.setDocument(yzfs_doc);
		ysfsiTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (yafs_str.equals(ysfsiTextField.getText().toString())) {
					ysfsiTextField.setText("");
				}

			}
		});
		JScrollPane ysfs_jspane = new JScrollPane(ysfsiTextField);
		JLabel hzdw = new JLabel("������λ*");
		hzdwiTextField = new SACJTextField32(70);
		component_map.put("������λ", hzdwiTextField);
		JLabel hzfs = new JLabel("������ʽ*");
		hzfsiTextField = new SACJTextField32(71);
		component_map.put("������ʽ", hzfsiTextField);
		hzfsiTextField.setText(hzfs_str);
		hzfsiTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (hzfs_str.equals(hzfsiTextField.getText().toString())) {
					hzfsiTextField.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

		});
		JLabel xmzys = new JLabel("��Ŀ��Ԥ��(��Ԫ)*");
		xmzysiTextField = new SACJTextField64(70);
		component_map.put("��Ŀ��Ԥ��", xmzysiTextField);
		JLabel zjly = new JLabel("�ʽ���Դ*");
		zjlyiTextField = new SACJTextField64(71);
		component_map.put("�ʽ���Դ", zjlyiTextField);
		zjlyiTextField.setText(zjly_str);
		zjlyiTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (zjly_str.equals(zjlyiTextField.getText().toString())) {
					zjlyiTextField.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

		});
		// Ԥ��Ŀ��Panel
		JPanel panel_yqmb = new JPanel(new PropertyLayout());
		// ����Ԥ��Ŀ�꼰�ɹ���ǩ
		TitledBorder yqmbBorder = BorderFactory.createTitledBorder("Ԥ��Ŀ�꼰�ɹ�");
		yqmbBorder.setTitlePosition(TitledBorder.TOP);
		yqmbBorder.setTitleFont(font);
		panel_yqmb.setBorder(yqmbBorder);

		JPanel panel_mb = new JPanel(new PropertyLayout());
		JLabel mbjssp = new JLabel("Ŀ�꼼��ˮƽ*");
		mbjsspiTextField = new JTextArea(10, 50);
		component_map.put("Ŀ�꼼��ˮƽ", mbjsspiTextField);
		mbjsspiTextField.setLineWrap(true);
		mbjsspiTextField.setText(mbjssp_str);
		// ����Ŀ�꼼��ˮƽ�������ֵ
		SACDocument1280 mbjssp_doc = new SACDocument1280();
		mbjsspiTextField.setDocument(mbjssp_doc);
		JScrollPane mbjssp_jspane = new JScrollPane(mbjsspiTextField);
		mbjsspiTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (mbjssp_str.equals(mbjsspiTextField.getText().toString())) {
					mbjsspiTextField.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

		});

		JPanel panel_zl = new JPanel(new PropertyLayout());
		JLabel zlzzlw = new JLabel("ר��/����Ȩ/����*");
		// ר��������Panel
		JPanel panel_zlylw = new JPanel(new PropertyLayout());
		JLabel zl = new JLabel("ר��*");
		zliTextField = new iTextArea(3, 40);
		component_map.put("ר��", zliTextField);
		zliTextField.setLineWrap(true);
		// ����ר���������ֵ                                                                                              ���ֵ��Ȼֻ��32���ַ�
		SACDocument32 zl_doc = new SACDocument32();
		zliTextField.setDocument(zl_doc);
		JScrollPane zl_jspane = new JScrollPane(zliTextField);
		JLabel zzq = new JLabel("����Ȩ*");
		zzqiTextField = new iTextArea(3, 40);
		component_map.put("����Ȩ", zzqiTextField);
		zzqiTextField.setLineWrap(true);
		// ��������Ȩ�������ֵ
		SACDocument32 zzq_doc = new SACDocument32();
		zzqiTextField.setDocument(zzq_doc);
		JScrollPane zzq_jspane = new JScrollPane(zzqiTextField);
		JLabel lw = new JLabel("����*");
		lwiTextField = new iTextArea(3, 40);
		component_map.put("����", lwiTextField);
		lwiTextField.setLineWrap(true);
		// ���������������ֵ
		SACDocument32 lw_doc = new SACDocument32();
		lwiTextField.setDocument(lw_doc);
		JScrollPane lw_jspane = new JScrollPane(lwiTextField);

		panel_mb.add("1.1.left", mbjssp);
		panel_mb.add("2.1.left", mbjssp_jspane);

		panel_zlylw.add("1.1.left", zl);
		panel_zlylw.add("1.2.left", zl_jspane);
		panel_zlylw.add("2.1.left", zzq);
		panel_zlylw.add("2.2.left", zzq_jspane);
		panel_zlylw.add("3.1.left", lw);
		panel_zlylw.add("3.2.left", lw_jspane);

		panel_zl.add("1.1.left", zlzzlw);
		panel_zl.add("2.1.left", panel_zlylw);

		JPanel panel_cg = new JPanel(new PropertyLayout());
		JLabel cgysfs = new JLabel("�ɹ����շ�ʽ*");
		cgysfsiTextField = new JTextArea(10, 52);
		component_map.put("�ɹ����շ�ʽ", cgysfsiTextField);
		cgysfsiTextField.setLineWrap(true);
		cgysfsiTextField.setText(cgysfs_str);
		// ���óɹ����շ�ʽ�������ֵ
		SACDocument256 cgysfs_doc = new SACDocument256();
		cgysfsiTextField.setDocument(cgysfs_doc);
		JScrollPane cgysfs_jspane = new JScrollPane(cgysfsiTextField);
		cgysfsiTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (cgysfs_str.equals(cgysfsiTextField.getText().toString())) {
					cgysfsiTextField.setText("");
				}

			}
		});
		panel_cg.add("1.1.left", cgysfs);                                          //�ɹ����շ�ʽ
		panel_cg.add("2.1.left", cgysfs_jspane);

		panel_yqmb.add("1.1.left", new JLabel("               "));
		panel_yqmb.add("1.2.left", panel_mb);
		panel_yqmb.add("1.3.left", panel_zl);
		panel_yqmb.add("1.4.left", panel_cg);

		// panel_1.add("1.1.left",xmmc);
		// panel_1.add("1.2.left",xmmciTextField);
		// panel_1.add("2.1.left",yzlh);
		// panel_1.add("2.2.left",yzlhiTextField);
		panel_1.add("1.1.left", syfw);
		panel_1.add("1.2.left", syfwiTextField);
		panel_1.add("2.1.left", qzny);
		panel_1.add("2.2.left", qznyidatebutton);
		panel_1.add("2.3.left", jzny);
		panel_1.add("2.4.left", jznyidatebutton);
		panel_1.add("3.1.left", cddwybm);
		panel_1.add("3.2.left", cddwybmiTextField);
		panel_1.add("3.3.left",cb_bm);
		panel_1.add("3.4.left",cbdwTextField);
		panel_1.add("4.1.left", yzfs);
		panel_1.add("4.2.left", ysfs_jspane);
		panel_1.add("5.1.left", hzdw);
		panel_1.add("5.2.left", hzdwiTextField);
		panel_1.add("5.3.left", hzfs);
		panel_1.add("5.4.left", hzfsiTextField);
		panel_1.add("6.1.left", xmzys);
		panel_1.add("6.2.left", xmzysiTextField);
		panel_1.add("6.3.left", zjly);
		panel_1.add("6.4.left", zjlyiTextField);
		panel_xmjk.add("1.1.left", panel_1);
		panel_xmjk.add("2.1.left", panel_yqmb);

		return panel_xmjk;

	}

	/**
	 * ������Ŀ�����˵�Panel
	 * */
	public JPanel bulidxmfzr_Panel() {
		JPanel panel_fz = new JPanel();
		panel_fz.setLayout(new BoxLayout(panel_fz, BoxLayout.Y_AXIS));

		JPanel panel_fzr = new JPanel(new PropertyLayout());
		JLabel xm1 = new JLabel("����*");
		xmiTextField1 = new SACJTextField32(46);
		component_map.put("����", xmiTextField1);
		JLabel zc1 = new JLabel("ְ��*");
		zc_Box1 = new JComboBox(zc_str);
		component_map.put("ְ��", zc_Box1);
		zc_Box1.setPreferredSize(new Dimension(278, 20));
		JLabel zy1 = new JLabel("רҵ*");
		zyiTextField1 = new SACJTextField32(47);
		component_map.put("רҵ", zyiTextField1);
		JLabel yzdw1 = new JLabel("���Ƶ�λ*");
		yanzdw_Box1 = new JComboBox(dw_str);
		component_map.put("���Ƶ�λ", yanzdw_Box1);
		yanzdw_Box1.setPreferredSize(new Dimension(282, 20));
		JLabel yzbm1 = new JLabel("���Ʋ���*");
		yanziTextField1 = new SACJTextField32(95);
		component_map.put("���Ʋ���", yanziTextField1);
		JLabel lxfs1 = new JLabel("��ϵ��ʽ*");
		lxfsiTextField1 = new JTextArea(3, 72);
		component_map.put("��ϵ��ʽ", lxfsiTextField1);
		lxfsiTextField1.setLineWrap(true);
		lxfsiTextField1.setText(tel_str);
		// ������ϵ��ʽ�������ֵ
		SACDocument32 lxfs1_doc = new SACDocument32();
		lxfsiTextField1.setDocument(lxfs1_doc);
		lxfsiTextField1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (tel_str.equals(lxfsiTextField1.getText().toString())) {
					lxfsiTextField1.setText("");
				}

			}
		});
		JScrollPane lxfs1_jspane = new JScrollPane(lxfsiTextField1);
		JLabel email1 = new JLabel(" Email*");
		emailiTextField1 = new JTextArea(3, 71);
		component_map.put("Email", emailiTextField1);
		emailiTextField1.setLineWrap(true);
		// ����Email�������ֵ
		SACDocument32 email1_doc = new SACDocument32();
		emailiTextField1.setDocument(email1_doc);
		JScrollPane email1_jspane = new JScrollPane(emailiTextField1);

		JLabel xm2 = new JLabel("����");
		xmiTextField2 = new SACJTextField32(46);
		JLabel zc2 = new JLabel("ְ��");
		zc_Box2 = new JComboBox(zc_str);
		zc_Box2.setPreferredSize(new Dimension(278, 20));
		JLabel zy2 = new JLabel("רҵ");
		zyiTextField2 = new SACJTextField32(47);
		JLabel dwbm2 = new JLabel("���Ƶ�λ");
		yanzdw_Box2 = new JComboBox(dw_str);
		yanzdw_Box2.setPreferredSize(new Dimension(282, 20));
		JLabel yzbm2 = new JLabel("���Ʋ���");
		yanziTextField2 = new SACJTextField32(95);
		JLabel lxfs2 = new JLabel("��ϵ��ʽ");
		lxfsiTextField2 = new JTextArea(3, 72);
		lxfsiTextField2.setText(tel_str);
		lxfsiTextField2.setLineWrap(true);
		// ������ϵ��ʽ�������ֵ
		SACDocument32 lxfs2_doc = new SACDocument32();
		lxfsiTextField2.setDocument(lxfs2_doc);
		lxfsiTextField2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (tel_str.equals(lxfsiTextField2.getText().toString())) {
					lxfsiTextField2.setText("");
				}

			}
		});
		JScrollPane lxfs2_jspane = new JScrollPane(lxfsiTextField2);
		JLabel email2 = new JLabel(" Email");
		emailiTextField2 = new JTextArea(3, 71);
		emailiTextField2.setLineWrap(true);
		// ����Email�������ֵ
		SACDocument32 email2_doc = new SACDocument32();
		emailiTextField2.setDocument(email2_doc);
		JScrollPane email2_jspane = new JScrollPane(emailiTextField2);

		JLabel xm3 = new JLabel("����");
		xmiTextField3 = new SACJTextField32(46);
		JLabel zc3 = new JLabel("ְ��");
		zc_Box3 = new JComboBox(zc_str);
		zc_Box3.setPreferredSize(new Dimension(278, 20));
		JLabel zy3 = new JLabel("רҵ");
		zyiTextField3 = new SACJTextField32(47);
		JLabel dwbm3 = new JLabel("���Ƶ�λ");
		yanzdw_Box3 = new JComboBox(dw_str);
		yanzdw_Box3.setPreferredSize(new Dimension(282, 20));
		JLabel yzbm3 = new JLabel("���Ʋ���");
		yanziTextField3 = new SACJTextField32(95);
		JLabel lxfs3 = new JLabel("��ϵ��ʽ");
		lxfsiTextField3 = new JTextArea(3, 72);
		lxfsiTextField3.setText(tel_str);
		// ������ϵ��ʽ�������ֵ
		SACDocument32 lxfs3_doc = new SACDocument32();
		lxfsiTextField3.setDocument(lxfs3_doc);
		lxfsiTextField3.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (tel_str.equals(lxfsiTextField3.getText().toString())) {
					lxfsiTextField3.setText("");
				}

			}
		});
		JScrollPane lxfs3_jspane = new JScrollPane(lxfsiTextField3);
		JLabel email3 = new JLabel(" Email");
		emailiTextField3 = new JTextArea(3, 71);
		emailiTextField3.setLineWrap(true);
		// ����Email�������ֵ
		SACDocument32 email3_doc = new SACDocument32();
		emailiTextField3.setDocument(email3_doc);
		JScrollPane email3_jspane = new JScrollPane(emailiTextField3);

		panel_fzr.add("1.1.left", xm1);
		panel_fzr.add("1.2.left", new JLabel("      "));
		panel_fzr.add("1.3.left", xmiTextField1);
		panel_fzr.add("1.4.left", zc1);
		panel_fzr.add("1.5.left", zc_Box1);
		panel_fzr.add("1.6.left", zy1);
		panel_fzr.add("1.7.left", zyiTextField1);
		panel_fzr.add("2.1.left", yzdw1);
		panel_fzr.add("2.2.left", new JLabel("      "));
		panel_fzr.add("2.3.left", yanzdw_Box1);
		panel_fzr.add("2.4.left", yzbm1);
		panel_fzr.add("2.5.left", yanziTextField1);
		panel_fzr.add("3.1.left", lxfs1);
		panel_fzr.add("3.2.left", new JLabel("      "));
		panel_fzr.add("3.3.left", lxfs1_jspane);
		panel_fzr.add("3.4.left", email1);
		panel_fzr.add("3.5.left", email1_jspane);
		panel_fzr.add("4.1.left", xm2);
		panel_fzr.add("4.2.left", new JLabel("      "));
		panel_fzr.add("4.3.left", xmiTextField2);
		panel_fzr.add("4.4.left", zc2);
		panel_fzr.add("4.5.left", zc_Box2);
		panel_fzr.add("4.6.left", zy2);
		panel_fzr.add("4.7.left", zyiTextField2);
		panel_fzr.add("5.1.left", dwbm2);
		panel_fzr.add("5.2.left", new JLabel("      "));
		panel_fzr.add("5.3.left", yanzdw_Box2);
		panel_fzr.add("5.4.left", yzbm2);
		panel_fzr.add("5.5.left", yanziTextField2);
		panel_fzr.add("6.1.left", lxfs2);
		panel_fzr.add("6.2.left", new JLabel("      "));
		panel_fzr.add("6.3.left", lxfs2_jspane);
		panel_fzr.add("6.4.left", email2);
		panel_fzr.add("6.5.left", email2_jspane);
		panel_fzr.add("7.1.left", xm3);
		panel_fzr.add("7.2.left", new JLabel("      "));
		panel_fzr.add("7.3.left", xmiTextField3);
		panel_fzr.add("7.4.left", zc3);
		panel_fzr.add("7.5.left", zc_Box3);
		panel_fzr.add("7.6.left", zy3);
		panel_fzr.add("7.7.left", zyiTextField3);
		panel_fzr.add("8.1.left", dwbm3);
		panel_fzr.add("8.2.left", new JLabel("      "));
		panel_fzr.add("8.3.left", yanzdw_Box3);
		panel_fzr.add("8.4.left", yzbm3);
		panel_fzr.add("8.5.left", yanziTextField3);
		panel_fzr.add("9.1.left", lxfs3);
		panel_fzr.add("9.2.left", new JLabel("      "));
		panel_fzr.add("9.3.left", lxfs3_jspane);
		panel_fzr.add("9.4.left", email3);
		panel_fzr.add("9.5.left", email3_jspane);

		panel_fz.add(panel_fzr);
		return panel_fz;

	}

	/**
	 * ����Form
	 * 
	 * */
	public TCComponentForm createrjgzfyForm(String name, String form_type) {
		try {
			TCComponentFormType type = (TCComponentFormType) session
					.getTypeComponent(form_type);
			TCComponentForm form = type.create(name, "", form_type);
			return form;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ���Lov�б�ֵ
	 * 
	 * @param strLOVName
	 * @return
	 */
	private Object[] getListOfValues(String strLOVName) {

		Object[] arrayObject = null;
		try {
			TCComponentListOfValuesType listType = (TCComponentListOfValuesType) session
					.getTypeComponent("ListOfValuesString");
			TCComponentListOfValues[] listValues = listType.find(strLOVName);
			if (listValues != null) {
				for (int i = 0; i < listValues.length; i++) {
					ListOfValuesInfo listValuesInfo = listValues[i]
							.getListOfValues();
					arrayObject = listValuesInfo.getListOfValues();
				}
			}
		} catch (TCException e) {
			e.printStackTrace();
		}

		return arrayObject;
	}
}
