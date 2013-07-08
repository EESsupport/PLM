package com.origin.rac.sac.htjs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.com.origin.util.OracleConnect;
import cn.com.origin.util.ProgressBarThread;
import cn.com.origin.util.S4Bypass;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFOperation;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentFolderType;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemType;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCProperty;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.kernel.TCTextService;
import com.teamcenter.rac.util.MessageBox;

public class S4HTJSOperation extends AbstractAIFOperation {

	private AbstractAIFApplication app = null;
	private TCComponentFolder targetfolder = null;
	private TCSession session = null;
	private String str_type = "S4Gcproj";
	private String relation ="IMAN_master_form";
	private ArrayList<String[]> hetonglist = new ArrayList<String[]>();
	private ProgressBarThread progressbar = null ;
	private S4Bypass bypass = null;
	private String xmmuban = "��Ŀ�ĵ�";
	private String projectName;
	private String templateType = "S4MZPrj_Folder";
	private String owner = "";
	private String tempalte_name = "������Ŀģ��";
	private TCComponentFolder templateObject;
	private String target_item_id=null;
	private String releation = "IMAN_reference";
	private String attri = "gov_classification";
	
	
	
	public S4HTJSOperation(AbstractAIFApplication app,TCComponentFolder targetfolder){
		this.app = app;
		this.session = (TCSession) this.app.getSession();
		this.targetfolder = targetfolder;
		bypass = new S4Bypass(session);
		owner = session.getPreferenceService().getString(4, "SAC_Folder_Template_Owner");
		if (owner == null || "".equals(owner.trim())) {
			MessageBox.post("δ������ѡ�SAC_Folder_Template_Owner��ֵ", "��ʾ", MessageBox.INFORMATION);
			return;
		}
	}
	
	
	@Override
	public void executeOperation() throws Exception {

		progressbar = new ProgressBarThread("��ͬ����" ,"��ͬ������,���Ե�...");
		progressbar.start();
		//����߼�
		checkHTData();
		
	}

	/*
	 * 1.���ȴ��м���м���ͬ����ϵͳ���ǲ��Ǵ���
	 * 2.���������,�򴴽������ͬ,�����ñ����Ժ͸������ݿ���Ϣ
	 * 3.�������,�����ж�PROCESS_FLAGֵ,��ΪY����N,�����κδ���
	 * 4.�������,�����ж�PROCESS_FLAGֵ,��PROCESS_FLAGֵΪ��,�������²���
	 */
	public void checkHTData() {
		hetonglist.clear();
		OracleConnect oraconn = new OracleConnect();
		try {
			Connection conn = oraconn.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select * from CUX.CUX_PLM_OM_CONT_IFACE where rowid in(select min(rowid) from " +
					"CUX.CUX_PLM_OM_CONT_IFACE group by CONTRACT_NUMBER)";
			ResultSet result = stmt.executeQuery(sql);
			if(result!=null )
			{
				while(result.next())
				{
					String[] tempvalue = new String[3];
//					tempvalue[0] = String.valueOf(result.getInt("CONTRACT_NUMBER"));
					tempvalue[0] = String.valueOf(result.getBigDecimal("CONTRACT_NUMBER"));
					System.out.println("tempvalue[0]====>:"+tempvalue[0]);
					tempvalue[1] = result.getString("PROJECT_NAME");
					tempvalue[2] = result.getString("PROCESS_FLAG");
					System.out.println("tempvalue[2]----->:"+tempvalue[2]);
					hetonglist.add(tempvalue);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(hetonglist!=null && hetonglist.size()>0)
		{
			for (int i = 0; i < hetonglist.size(); i++) {
				String[] tempvalue = hetonglist.get(i);
				//�õ���ͬ��
				String item_id = tempvalue[0];
				System.out.println("item_id---->:"+item_id);
				//�õ���ͬ����
				String item_name = tempvalue[1];
				//�õ����ݱ�ǵ�ֵ
				String process_flag = tempvalue[2];
				System.out.println("process_flag--->:"+process_flag);
				InterfaceAIFComponent[] items = query(item_id);
				//���������,�򴴽������ͬ,�����ñ����Ժ͸������ݿ���Ϣ
				if(items==null || items.length==0)
				{
					System.out.println(item_id+"���ID�ĺ�ͬ����ϵͳ�в�����!");
					TCComponentItem item = createItem(item_id, item_name);
					try {
						if(item!=null){
							//��ô���ITEM��ID
							target_item_id=item.getProperty("item_id");
							//��ö���ģ���ļ��е�����
							projectName = item.getProperty("object_name")+xmmuban;
							//���������ļ���
							TCComponentFolder rootFolder = createFolder(templateType, projectName, item);
							//���ģ���ļ���
							templateObject = getTemplateFolder(tempalte_name);
							if(templateObject == null){
								progressbar.stopBar();
								MessageBox.post("δ�ҵ�������Ŀģ�����", "��ʾ", MessageBox.WARNING);
								return;
							}
							//��ģ���ļ�������Ľṹ�½��ҽ����½���ITEM������
							addObjectToFolder(templateObject,rootFolder);
							targetfolder.add("contents", item);
						}
						else
						{
							progressbar.stopBar();
							MessageBox.post(item_id+"��ͬ�޷�����,����ʧ��!","��ʾ",MessageBox.WARNING);
							return;
						}
							
						//�õ���Ҫ�������Եı�����
						TCComponentForm master_form = (TCComponentForm) item.getRelatedComponent(relation);
						//���ñ�����
						boolean set_result = setFormProperties(master_form, item_id,item_name);
						//�����м���ͱ��
						updateTable(item_id,set_result,"����ʧ��");
						if(set_result)
						{
							continue;
						}
						else
						{
							progressbar.stopBar();
							MessageBox.post(item_id+"��ͬ����ʧ��!","��ʾ",MessageBox.WARNING);
							return;
						}
					} catch (TCException e1) {
						e1.printStackTrace();
					}
				}
				else
				{
					System.out.println(item_id+"���ID��ϵͳ���Ѿ�����!");
					
					//�������,�����ж�PROCESS_FLAGֵ,��ΪY����N,�����κδ���
					if("Y".equals(process_flag) || "N".equals(process_flag)){
						continue;
					}else{
						//��PROCESS_FLAGֵΪ��,�������²���
						try {
							TCComponentItem gx_item = (TCComponentItem) items[0];
							TCComponentForm gx_master_form = (TCComponentForm) gx_item.getRelatedComponent(relation);
							boolean gx_result = updateForm(gx_master_form, item_id);
							updateTable(item_id,gx_result,"����ʧ��");
							if(gx_result)
							{
								continue;
							}
							else
							{
								progressbar.stopBar();
								MessageBox.post(item_id+"��ͬ���Ը���ʧ��!","��ʾ",MessageBox.WARNING);
								return;
							}
							
						} catch (TCException e) {
							e.printStackTrace();
						}
					}
				}
			}
			progressbar.stopBar();
			MessageBox.post("��ͬ���ܼ��������!","��ʾ",MessageBox.INFORMATION);
		}else{
			progressbar.stopBar();
			MessageBox.post("��ͬ�м������û������!","��ʾ",MessageBox.INFORMATION);
			return;
		}
	}
	
	
	//���ñ�����
	private boolean setFormProperties(TCComponentForm master_form,String itemid,String object_name)
	{
		boolean isOK = false;
		//��������������������
		try {
			master_form.setProperty("s4contractno", itemid);
			master_form.setProperty("s4projectnam", object_name);
		} catch (TCException e1) {
			e1.printStackTrace();
		}
		ArrayList<S4HeToneInfo> hetonginfolist = new ArrayList<S4HeToneInfo>();
	
		OracleConnect oraconn = new OracleConnect();
		try {
			Connection conn = oraconn.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select * from CUX.CUX_PLM_OM_CONT_IFACE where CONTRACT_NUMBER="+itemid+"";
			ResultSet result = stmt.executeQuery(sql);
			if(result!=null )
			{
				while(result.next())
				{
					S4HeToneInfo oneinfo = new S4HeToneInfo();
					
					String total_price = String.valueOf(result.getBigDecimal("TOTAL_PRICE"))==null?"":String.valueOf(result.getBigDecimal("TOTAL_PRICE"));
					if(total_price==null || "null".equals(total_price)){
						total_price = "";
					}
					oneinfo.setS4totalprice(total_price);
					oneinfo.setS4orderunit(result.getString("CUSTOMER_NAME")==null?"":result.getString("CUSTOMER_NAME"));
					Date date = result.getDate("DELIVERY_DATE");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(date == null){
						oneinfo.setS4deliverydate("");
					}else{
						oneinfo.setS4deliverydate(sdf.format(result.getDate("DELIVERY_DATE"))==null?"":sdf.format(result.getDate("DELIVERY_DATE")));
					}
					oneinfo.setS4vdc(result.getString("VDC")==null?"":result.getString("VDC"));
					oneinfo.setS4ddcc(result.getString("DDCC")==null?"":result.getString("DDCC"));
					oneinfo.setS4babintasize(result.getString("BABINTASIZE")==null?"":result.getString("BABINTASIZE"));
					
					//�����ӱ��������
					oneinfo.setS4Gcode(result.getString("ORDERED_ITEM_DSP")==null?"":result.getString("ORDERED_ITEM_DSP"));
					oneinfo.setS4Gdescription(result.getString("ITEM_DESCRIPTION")==null?"":result.getString("ITEM_DESCRIPTION"));
					//�ٺ������������ͱ�ע
					oneinfo.setS4declass(result.getString("DEMAND_CLASS")==null?"":result.getString("DEMAND_CLASS"));
					oneinfo.setS4remarkss(result.getString("ATTRIBUTE16")==null?"":result.getString("ATTRIBUTE16"));
					//�ٺ������кš��������������۵���
					String line_num = String.valueOf(result.getBigDecimal("LINE_NUMBER"))==null?"":String.valueOf(result.getBigDecimal("LINE_NUMBER"));
					if(line_num==null || "null".equals(line_num)){
						line_num = "";
					}
					oneinfo.setS4linenum(line_num);
					String ordered_quantity = String.valueOf(result.getBigDecimal("ORDERED_QUANTITY"))==null?"":String.valueOf(result.getBigDecimal("ORDERED_QUANTITY"));
					if(ordered_quantity==null || "null".equals(ordered_quantity)){
						ordered_quantity = "";
					}
					oneinfo.setS4Maternum(ordered_quantity);
					String unit_selling_price = String.valueOf(result.getBigDecimal("UNIT_SELLING_PRICE"))==null?"":String.valueOf(result.getBigDecimal("UNIT_SELLING_PRICE"));
					if(unit_selling_price==null || "null".equals(unit_selling_price)){
						unit_selling_price = "";
					}
					oneinfo.setS4Saleo(unit_selling_price);

					
					hetonginfolist.add(oneinfo);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//���±���Ӧ����
		if(hetonginfolist!=null && hetonginfolist.size()>0)
		{
			try {
				master_form.setProperty("s4totalprice", hetonginfolist.get(0).getS4totalprice());
				master_form.setProperty("s4orderunit", hetonginfolist.get(0).getS4orderunit());
				master_form.setProperty("s4deliverydate", hetonginfolist.get(0).getS4deliverydate());
			} catch (TCException e) {
				e.printStackTrace();
			}
			
			try {
				String[] attris = new String[]{"s4linenum","s4coding1","s4discriptions1","s4vdc1","s4ddcc1","s4babintasize1","s4declass","s4remarkss","s4Maternum","s4Saleo"};
				String[][] tempvalues = new String[hetonginfolist.size()][10];
				TCProperty[] pros = master_form.getTCProperties(attris);
				for(int i=0;i<hetonginfolist.size();i++)
				{
					S4HeToneInfo oneinfo = hetonginfolist.get(i);
					tempvalues[i][0] = oneinfo.getS4linenum();
					tempvalues[i][1] = oneinfo.getS4Gcode();
					tempvalues[i][2] = oneinfo.getS4Gdescription();
					tempvalues[i][3] = oneinfo.getS4vdc();
					tempvalues[i][4] = oneinfo.getS4ddcc();
					tempvalues[i][5] = oneinfo.getS4babintasize();
					tempvalues[i][6] = oneinfo.getS4declass();
					tempvalues[i][7] = oneinfo.getS4remarkss();
					tempvalues[i][8] = oneinfo.getS4Maternum();
					tempvalues[i][9] = oneinfo.getS4Saleo();
					System.out.println("tempvalues["+i+"][0]"+tempvalues[i][0]);
					System.out.println("tempvalues["+i+"][1]"+tempvalues[i][1]);
					System.out.println("tempvalues["+i+"][2]"+tempvalues[i][2]);
					System.out.println("tempvalues["+i+"][3]"+tempvalues[i][3]);
					System.out.println("tempvalues["+i+"][4]"+tempvalues[i][4]);
					System.out.println("tempvalues["+i+"][5]"+tempvalues[i][5]);
					System.out.println("tempvalues["+i+"][6]"+tempvalues[i][6]);
					System.out.println("tempvalues["+i+"][7]"+tempvalues[i][7]);
					System.out.println("tempvalues["+i+"][8]"+tempvalues[i][8]);
					System.out.println("tempvalues["+i+"][9]"+tempvalues[i][9]);
				}
				
				for(int i=0;i<pros.length;i++)
				{
					TCProperty onepor = pros[i];
					String[] values = new String[tempvalues.length];
					for(int j=0;j<tempvalues.length;j++)
					{
						values[j] = tempvalues[j][i];
					}
					onepor.setStringValueArray(values);
					
				}
				master_form.setTCProperties(pros);
				isOK = true;
			} catch (TCException e) {
				e.printStackTrace();
			}
			
		}
		return isOK;
	}
	
	//�������ݿ�PROCESS_FLAGֵ
	private void updateTable(String itemid,boolean result,String errmessage)
	{
		String state = "N";
		if(result)
		{
			state = "Y";
			errmessage = "";
		}
		
		// ʵ�������ݿ�����
		OracleConnect oraconn = new OracleConnect();
		try {
			Connection conn = oraconn.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "update CUX.CUX_PLM_OM_CONT_IFACE set PROCESS_FLAG='"+state+"',ERROR_MSG='"+errmessage+"' " +
					"where CONTRACT_NUMBER="+itemid+"";
			int k = stmt.executeUpdate(sql);
			if(k>0)
				System.out.println("���º�ͬ��ɹ�");
			
			stmt.close();
			oraconn.closeConn(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//���±�����
	private boolean updateForm(TCComponentForm master_form,String itemid)
	{
		bypass.setpass();
		boolean isOK = false;
		
		ArrayList<S4HeToneInfo> hetonginfolist = new ArrayList<S4HeToneInfo>();
	
		OracleConnect oraconn = new OracleConnect();
		try {
			Connection conn = oraconn.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select * from CUX.CUX_PLM_OM_CONT_IFACE where CONTRACT_NUMBER="+itemid+"";
			ResultSet result = stmt.executeQuery(sql);
			if(result!=null )
			{
				while(result.next())
				{
					S4HeToneInfo oneinfo = new S4HeToneInfo();
					oneinfo.setS4projectnam(result.getString("PROJECT_NAME")==null?"":result.getString("PROJECT_NAME"));
					
					String total_price = String.valueOf(result.getBigDecimal("TOTAL_PRICE"))==null?"":String.valueOf(result.getBigDecimal("TOTAL_PRICE"));
					if(total_price==null || "null".equals(total_price)){
						total_price = "";
					}
					oneinfo.setS4totalprice(total_price);
					oneinfo.setS4orderunit(result.getString("CUSTOMER_NAME")==null?"":result.getString("CUSTOMER_NAME"));
					Date date = result.getDate("DELIVERY_DATE");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(date == null){
						oneinfo.setS4deliverydate("");
					}else{
						oneinfo.setS4deliverydate(sdf.format(result.getDate("DELIVERY_DATE"))==null?"":sdf.format(result.getDate("DELIVERY_DATE")));
					}
					oneinfo.setS4vdc(result.getString("VDC")==null?"":result.getString("VDC"));
					oneinfo.setS4ddcc(result.getString("DDCC")==null?"":result.getString("DDCC"));
					oneinfo.setS4babintasize(result.getString("BABINTASIZE")==null?"":result.getString("BABINTASIZE"));
					
					//�����ӱ��������
					oneinfo.setS4Gcode(result.getString("ORDERED_ITEM_DSP")==null?"":result.getString("ORDERED_ITEM_DSP"));
					oneinfo.setS4Gdescription(result.getString("ITEM_DESCRIPTION")==null?"":result.getString("ITEM_DESCRIPTION"));
					//�ٺ������������ͱ�ע
					oneinfo.setS4declass(result.getString("DEMAND_CLASS")==null?"":result.getString("DEMAND_CLASS"));
					oneinfo.setS4remarkss(result.getString("ATTRIBUTE16")==null?"":result.getString("ATTRIBUTE16"));
					//�ٺ������кš��������������۵���
					String line_num = String.valueOf(result.getBigDecimal("LINE_NUMBER"))==null?"":String.valueOf(result.getBigDecimal("LINE_NUMBER"));
					if(line_num==null || "null".equals(line_num)){
						line_num = "";
					}
					oneinfo.setS4linenum(line_num);
					String ordered_quantity = String.valueOf(result.getBigDecimal("ORDERED_QUANTITY"))==null?"":String.valueOf(result.getBigDecimal("ORDERED_QUANTITY"));
					if(ordered_quantity==null || "null".equals(ordered_quantity)){
						ordered_quantity = "";
					}
					oneinfo.setS4Maternum(ordered_quantity);
					String unit_selling_price = String.valueOf(result.getBigDecimal("UNIT_SELLING_PRICE"))==null?"":String.valueOf(result.getBigDecimal("UNIT_SELLING_PRICE"));
					if(unit_selling_price==null || "null".equals(unit_selling_price)){
						unit_selling_price = "";
					}
					oneinfo.setS4Saleo(unit_selling_price);
					
					hetonginfolist.add(oneinfo);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(hetonginfolist!=null && hetonginfolist.size()>0)
		{
			try {
				bypass.setpass();
				master_form.setProperty("s4projectnam", hetonginfolist.get(0).getS4projectnam());
				master_form.setProperty("s4totalprice", hetonginfolist.get(0).getS4totalprice());
				master_form.setProperty("s4orderunit", hetonginfolist.get(0).getS4orderunit());
				master_form.setProperty("s4deliverydate", hetonginfolist.get(0).getS4deliverydate());
			} catch (TCException e) {
				e.printStackTrace();
			}
			
			try {
				String[] attris = new String[]{"s4linenum","s4coding1","s4discriptions1","s4vdc1","s4ddcc1","s4babintasize1","s4declass","s4remarkss","s4Maternum","s4Saleo"};
				String[][] tempvalues = new String[hetonginfolist.size()][10];
				TCProperty[] pros = master_form.getTCProperties(attris);
				for(int i=0;i<hetonginfolist.size();i++)
				{
					S4HeToneInfo oneinfo = hetonginfolist.get(i);
					tempvalues[i][0] = oneinfo.getS4linenum();
					tempvalues[i][1] = oneinfo.getS4Gcode();
					tempvalues[i][2] = oneinfo.getS4Gdescription();
					tempvalues[i][3] = oneinfo.getS4vdc();
					tempvalues[i][4] = oneinfo.getS4ddcc();
					tempvalues[i][5] = oneinfo.getS4babintasize();
					tempvalues[i][6] = oneinfo.getS4declass();
					tempvalues[i][7] = oneinfo.getS4remarkss();
					tempvalues[i][8] = oneinfo.getS4Maternum();
					tempvalues[i][9] = oneinfo.getS4Saleo();
					System.out.println("tempvalues["+i+"][0]"+tempvalues[i][0]);
					System.out.println("tempvalues["+i+"][1]"+tempvalues[i][1]);
					System.out.println("tempvalues["+i+"][2]"+tempvalues[i][2]);
					System.out.println("tempvalues["+i+"][3]"+tempvalues[i][3]);
					System.out.println("tempvalues["+i+"][4]"+tempvalues[i][4]);
					System.out.println("tempvalues["+i+"][5]"+tempvalues[i][5]);
					System.out.println("tempvalues["+i+"][6]"+tempvalues[i][6]);
					System.out.println("tempvalues["+i+"][7]"+tempvalues[i][7]);
					System.out.println("tempvalues["+i+"][8]"+tempvalues[i][8]);
					System.out.println("tempvalues["+i+"][9]"+tempvalues[i][9]);
				}
				
				for(int i=0;i<pros.length;i++)
				{
					TCProperty onepor = pros[i];
					String[] values = new String[tempvalues.length];
					for(int j=0;j<tempvalues.length;j++)
					{
						values[j] = tempvalues[j][i];
					}
					onepor.setStringValueArray(values);
					
				}
				bypass.setpass();
				master_form.setTCProperties(pros);
				isOK = true;
				bypass.closepass();
			} catch (TCException e) {
				e.printStackTrace();
			}
			
		}
		return isOK;
	}
	
	
	
	//����ϵͳ����� ID��ѯ
	public InterfaceAIFComponent[] query(String id){
		InterfaceAIFComponent[] items = null;
		try {
			TCTextService tcService =session.getTextService();
			String askKey[]={tcService.getTextValue("ItemID")};
			String askValue[]={id};
			items =  session.search("����� ID", askKey, askValue);
		} catch (TCException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}
	
	//����ITEM
	public TCComponentItem createItem(String id,String name) {
		try {
			TCComponentItemType itemtype = (TCComponentItemType) session.getTypeComponent(str_type);
			TCComponentItem item = itemtype.create(id, "",
					str_type, name, str_type, null);
			return item;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// ��ȡ��Ӧ��ģ�����
	private TCComponentFolder getTemplateFolder(String templateName) {
		TCComponentFolder folder = null;
		try {
			TCTextService tcTextService = session.getTextService();
			String askKey[] = { tcTextService.getTextValue("Name"), tcTextService.getTextValue("Type"), tcTextService.getTextValue("OwningUser") };
			String askValue[] = { templateName, templateType, owner };
			InterfaceAIFComponent objects[] = session.search("����...", askKey, askValue);
			if (objects != null && objects.length > 0) {
				folder = (TCComponentFolder) objects[0];
			}
		} catch (TCException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return folder;
	}
	
	// �������ͺ����ƴ����ļ��ж���
	private TCComponentFolder createFolder(String type, String name, TCComponentItem parent) {
		TCComponentFolder folder = null;
		if(name.equals("���������ϼ�ͼֽ"))
			name="���������ϼ�ͼֽ"+"("+target_item_id+")";
		try {
			TCComponentFolderType folderType = (TCComponentFolderType) session.getTypeComponent(type);
			folder = folderType.create(name, "", type);
			folder.setProperty(attri, target_item_id);
			if (folder != null)
				parent.add(releation, folder);
		} catch (TCException e) {
			e.printStackTrace();
		}
		return folder;
	}
	
	// �������ͺ����ƴ����ļ��ж���
	private TCComponentFolder createFolder(String type, String name, TCComponentFolder parent) {
		TCComponentFolder folder = null;
		if(name.equals("���������ϼ�ͼֽ"))
			name="���������ϼ�ͼֽ"+"("+target_item_id+")";
		try {
			TCComponentFolderType folderType = (TCComponentFolderType) session.getTypeComponent(type);
			folder = folderType.create(name, "", type);
			folder.setProperty(attri, target_item_id);
			if (folder != null)
				parent.add("contents", folder);
		} catch (TCException e) {
			e.printStackTrace();
		}
		return folder;
	}
	
	// �������ͺ����ƴ������������
	private void createItem(String type, String name, TCComponentFolder parent) {
		try {
			TCComponentItemType itemType = (TCComponentItemType) session.getTypeComponent(type);
			String itemId = itemType.getNewID(); // ��ȡ����
			String itemRev = itemType.getNewRev(null); // ��ȡ�汾����
			TCComponentItem item = itemType.create(itemId, itemRev, type, name, "", null);
			item.setProperty(attri,target_item_id);
			item.getLatestItemRevision().setProperty(attri,target_item_id);
			item.getLatestItemRevision().getRelatedComponent("IMAN_master_form_rev").setProperty(attri,target_item_id);
			if (item != null)
			{
				parent.add("contents", item);	
			}
		} catch (TCException e) {
			e.printStackTrace();
		}
	}
	
	private void addObjectToFolder(TCComponentFolder folder,TCComponentFolder rootfolder) {//folderΪģ����ڵ��ļ��ж���
		try {
			TCComponent[] children = folder.getRelatedComponents("contents");
			for (int i = 0; i < children.length; i++) {
				TCComponent child = children[i];
				String name = child.getProperty("object_name");
				String type = child.getType();
				if (child instanceof TCComponentFolder) {
					addObjectToFolder((TCComponentFolder) child, createFolder(
							type, name, rootfolder));
				} else if (child instanceof TCComponentItem) {
					createItem(type, name, rootfolder);
				}
			}
		} catch (TCException e) {
			e.printStackTrace();
		}
	}
	
}
