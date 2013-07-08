package com.origin.rac.sac.sendbominfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import cn.com.origin.util.OracleConnect;
import cn.com.origin.util.ProgressBarThread;
import cn.com.origin.util.ReadProperties;

import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCComponentBOMViewRevision;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCProperty;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class SendYXBomInfoCommand extends AbstractAIFCommand {

	private InterfaceAIFComponent[] targets = null;
	private AbstractAIFUIApplication application = null;
	private TCComponentBOMLine select_bom;
	private String relation = "IMAN_master_form_rev";
	private String relation1 = "structure_revisions";
	private String relation2 = "IMAN_specification";
	private TCSession session = null;
	private String[] attris = {"s4Passing_State","s4Wpromaterials"};
	private boolean flag1 = false;
	private boolean flag2 = false;
	private boolean flag3 = false;
	private boolean flag5 = false;
	private boolean flag6 = false;
	private boolean flag7 = false;
	private boolean flag_gylx = false;//������ʶѡ��������ǲ��������˹���·��
	private boolean flag_gylx1 = false;//������ʶѡ��������ǲ��������˹���·��
	public Connection conn = null;
	public ReadProperties read = null;
	public Statement stmt = null;
	public ResultSet reset = null;
	private Vector<String> vec_1 = new Vector<String>();
	private Vector<String> vec_2 = new Vector<String>();
	private Vector<String> vec_ID = new Vector<String>();//�������ѡ��BOM�������ӽṹID���ڵ���֯��ERP���Ƿ����
	private Vector<String> vec_error_ID = new Vector<String>();//������ʾѡ���BOM�������ӽṹID������������֯���治���ڵ�ID
	private Vector<String> vec_form_type = new Vector<String>();//�����洢û�����ù���·�ߵ�ID
	private String userName = "";//������
	private String error_cd = "N";
	private String gylxform_type = "S4GYLUPZ";
	private String attri1 = "s4Tranlogo";
	private String attri2 = "s4texture";
	private ProgressBarThread progressbar = null ;
	private String[] properties = {"bl_sequence_no","S4masteroper","bl_quantity","S4ATTRIBUTE7","S4ATTRIBUTE8",
				"S4ATTRIBUTE9","S4ATTRIBUTE11","S4ATTRIBUTE10","S4ATTRIBUTE12","S4MEANING","S4SUPPLY_SUBIN","S4component_rem"};
	private String[] all_str = null;
	private String zzdm_str = "";
	private TCProperty formProperties = null;
	private String[] Texture_array;
	private Vector<String> vec_form_zzdm = new Vector<String>();//�����洢���ù���·�ߵ���֯����
	
	public SendYXBomInfoCommand(AbstractAIFUIApplication app) {
		application =app;
		session = (TCSession) application.getSession();
		targets = (InterfaceAIFComponent[]) application.getTargetComponents();
		//ִ�в˵�����Ա
		userName = session.getUser().toString();
		//��ȡLOV
		all_str = session.getPreferenceService().getStringArray(4, "SAC_YXBOM_Lov");
		if(all_str == null || all_str.length == 0){
			MessageBox.post("δ������ѡ�SAC_YXBOM_Lov", "��ʾ", MessageBox.INFORMATION);
			return;
		}
//		int m = user.indexOf("(");
//		userName = user.substring(0, m);
		if(targets.length==1){
			try {
				progressbar = new ProgressBarThread("ԭ��BOM����" ,"ԭ��BOM�����У����Ե�...");
				progressbar.start();
				//���ȼ��ѡ�����ϵ���ṹ����������ERP���ǲ����Ѿ�����
				select_bom = (TCComponentBOMLine) targets[0];
				String zzdm = select_bom.getProperty("bl_view_type").toString();
				zzdm_str = zzdm.substring(2,zzdm.length());
				String sel_bom_id = select_bom.getProperty("bl_item_item_id").toString();
				if(!vec_ID.contains(sel_bom_id)){
					vec_ID.add(sel_bom_id);
				}
				String sel_bom_name = select_bom.getProperty("bl_item_object_name").toString();
				String error_info = sel_bom_id +"," + sel_bom_name;
				TCComponentItemRevision sel_rev = select_bom.getItem().getLatestItemRevision();
				String str_fb = sel_rev.getProperty("release_status_list");
				if("".equals(str_fb) || str_fb==null){
					progressbar.stopBar();
					MessageBox.post("��ѡ���BOMû�з���,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				TCComponentBOMViewRevision sel_bomview = (TCComponentBOMViewRevision) sel_rev.getRelatedComponent(relation1);
				if(sel_bomview!=null){
					String sel_desc = sel_bomview.getProperty("object_desc").toString();
					System.out.println("rev====----*sel_desc====>:"+sel_desc);
					if("Y".equals(sel_desc)){
						progressbar.stopBar();
						MessageBox.post("��ѡ���BOM�Ѿ����ݹ�ERP,�������ٴδ���,����!", "��ʾ", MessageBox.INFORMATION);
						return;
					}
				}
				//�ж�ѡ��BOM�����ǲ��������˹���·��
				TCComponent[] components = sel_rev.getRelatedComponents(relation2);
				if(components==null || components.length==0){
					flag_gylx = true;
				}else{
					for (int j = 0; j < components.length; j++) {
						String type = components[j].getType().toString();
						if(!vec_form_type.contains(type)){
							vec_form_type.add(type);
						}
					}
					if(!vec_form_type.contains(gylxform_type)){
						flag_gylx1 = true;
						
					}
				}
				//�ж�ѡ���BOM�ǲ����Ѿ����ù�����·��
				if(flag_gylx){
					progressbar.stopBar();
					MessageBox.post("��ѡ���BOMû�����ù���·��,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ���BOM�ǲ����Ѿ����ù�����·��
				if(flag_gylx1){
					progressbar.stopBar();
					MessageBox.post("��ѡ���BOMû�����ù���·��,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�õ�ѡ��bom�İ汾��������ù���·�ߵ�form
				TCComponentForm gylx_form = null;
				for (int i = 0; i < components.length; i++) {
					String type = components[i].getType().toString();
					if(gylxform_type.equals(type)){
						gylx_form = (TCComponentForm) components[i];
						formProperties = gylx_form.getFormTCProperty(attri2);
						Texture_array = formProperties.getStringValueArray();
						String texture = Texture_array[0];
						System.out.println("texture---------->:"+texture);
						if(!vec_form_zzdm.contains(texture)){
							vec_form_zzdm.add(texture);
						}
					}
				}
				System.out.println("vec_form_zzdm----------->:"+vec_form_zzdm);
				if(gylx_form!=null){
					if(!vec_form_zzdm.contains(zzdm_str)){
						progressbar.stopBar();
						MessageBox.post("��ѡ���BOM����֯�������õĹ���·�ߵ���֯��һ��,����!","����",MessageBox.ERROR);
						return;
					}
					String tranlogo = gylx_form.getProperty(attri1).toString();
					if(!tranlogo.equals("Y")){
						progressbar.stopBar();
						MessageBox.post("���ȴ��ݹ���·�ߺ��ٴ���BOM!","����",MessageBox.ERROR);
						return;
					}
				}
				//�õ�ѡ��BOM�İ汾�����form
				TCComponentForm form = (TCComponentForm) sel_rev.getRelatedComponent(relation);
				String[] values = form.getProperties(attris);
				if(!"Y".equals(values[0])){
					flag2 = true;
					if(!vec_2.contains(error_info)){
						vec_2.add(error_info);
					}
				}
				/*if("Y".equals(values[1])){
					flag4 = true;
				}
				if(!flag4){
					progressbar.stopBar();
					MessageBox.post("��ѡ���BOM�в���ԭ������,����!","����",MessageBox.ERROR);
					return;
				}*/
				AIFComponentContext[] coms = select_bom.getChildren();
				if(coms.length>0 && coms!=null){
					for (int j = 0; j < coms.length; j++) {
						TCComponentBOMLine sub_bom = (TCComponentBOMLine) coms[j].getComponent();
						String sub_bom_id = sub_bom.getProperty("bl_item_item_id").toString();
						if(!vec_ID.contains(sub_bom_id)){
							vec_ID.add(sub_bom_id);
						}
						String sub_bom_name = sub_bom.getProperty("bl_item_object_name").toString();
						String sub_error_info = sub_bom_id +"," + sub_bom_name;
						TCComponentItemRevision sub_rev = sub_bom.getItem().getLatestItemRevision();
						TCComponentForm sub_form = (TCComponentForm) sub_rev.getRelatedComponent(relation);
						String sub_passing_state = sub_form.getProperty(attris[0]).toString();
						if(!"Y".equals(sub_passing_state)){
							flag2 = true;
							if(!vec_2.contains(sub_error_info)){
								vec_2.add(sub_error_info);
							}
						}
						
						TCComponentItemRevision rev = sub_bom.getItem().getLatestItemRevision();
						TCComponentBOMViewRevision bomview = (TCComponentBOMViewRevision) rev.getRelatedComponent(relation1);
						if(bomview!=null){
							String desc = bomview.getProperty("object_desc").toString();
							System.out.println("rev====----*desc====>:"+desc);
							if("".equals(desc) || error_cd.equals(desc)){
								flag1 = true;
								vec_1.add(sub_bom_id);
							}
						}
					}
				}
				if(flag2){
					progressbar.stopBar();
					MessageBox.post(vec_2+"��Щ������ERP��û�д���,���ȴ��ݸ����Ϻ��ٴ���BOM!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				if(flag1){
					progressbar.stopBar();
					MessageBox.post("���Ƚ�"+vec_1+"��BOM������ERP���ٳ��Դ��ݸ�BOM!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				flag6 = isInOrganization(vec_ID,zzdm_str);
				if(flag6){
					progressbar.stopBar();
					MessageBox.post(vec_error_ID+"���ڵ���֯��ERP��û�з���,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//���������������,��ѷ���������BOM��Ϣ�������м��
				AIFComponentContext[] boms = select_bom.getChildren();
				String parent_name = select_bom.getProperty("bl_formatted_parent_name").toString();
				if(parent_name!=null && !"".equals(parent_name)){
					String[] p_values = new String[12];
					p_values = select_bom.getProperties(properties);
					if(p_values[1]==null || "".equals(p_values[1])){
						flag5 = true;
					}
					if(p_values[2]==null || "".equals(p_values[2])){
						flag7 = true;
					}
				}
				int k =boms.length;
				if(k>0){
					//�ȼ��BOM�ϱ������û����
					for (int i = 0; i < k; i++) {
						TCComponentBOMLine sub_bom = (TCComponentBOMLine) boms[i].getComponent();
						String[] sub_values = new String[12];
						sub_values = sub_bom.getProperties(properties);
						if(sub_values[1]==null || "".equals(sub_values[1])){
							flag5 = true;
						}
						if(sub_values[2]==null || "".equals(sub_values[2])){
							flag7 = true;
						}
					}
					if(flag5){
						progressbar.stopBar();
						MessageBox.post("BOM�ϵ���Ҫ_���Ϊ��,����!", "��ʾ", MessageBox.INFORMATION);
						return;
					}
					if(flag7){
						progressbar.stopBar();
						MessageBox.post("BOM�ϵ�����Ϊ��,����!", "��ʾ", MessageBox.INFORMATION);
						return;
					}
					//�����ж�ѡ���������ԭ�����ǲ����Ѿ�����
					String sel_id = select_bom.getProperty("bl_item_item_id");
					flag3 = checkTableData(sel_id);
					if(flag3){
						progressbar.stopBar();
						MessageBox.post("��BOM�Ѿ������м��,�ȴ�ERP����!", "��ʾ", MessageBox.INFORMATION);
						return;
					}else{
						progressbar.stopBar();
						//��ԭ��BOM����ǰ��Ҫά��һЩ����
						new SendYXBomInfoDialog(application,select_bom,userName,all_str,zzdm_str);
					}
				}else{
					//���ѡ�����û���Ӳ�ṹ��bom,�򱨴�
					progressbar.stopBar();
					MessageBox.post("��ѡ�����Ӳ�ṹ��BOM����!","����",MessageBox.ERROR);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			MessageBox.post("��ѡ�񵥸�BOM����!", "��ʾ", MessageBox.INFORMATION);
			return;
		}
	}
	
	//ͨ��ѡ��BOM���ӽṹIDȥ���ѡ��BOM���ڵ���֯��ERP�������֯�µı����ǲ��Ǵ���
	public boolean isInOrganization(Vector<String> vec_bomid,String zzdm){
		boolean flag_org = false;
		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			for (int i = 0; i < vec_bomid.size(); i++) {
				String bomid = vec_bomid.get(i);
				String sql = "select * from CUX_PLM_ORG_ITEM_V where Org_Code = '"+ zzdm + "'" + " and Item_Num = '"+ bomid +"'";
				stmt = conn.createStatement();
				reset = stmt.executeQuery(sql);
				if(reset!=null && reset.next())
				{
					
				}else{
					flag_org = true;
					if(!vec_error_ID.contains(bomid)){
						vec_error_ID.add(bomid);
					}
				}
				stmt.close();
			}
			oraconn.closeConn(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag_org;
	}
	
	
	
	
	
	//���ѡ���BOM���м�����ǲ����Ѿ�����
	public boolean checkTableData(String sel_id){
		boolean flag_check = false;
		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			//�����ж�ѡ���������ԭ�����ǲ����Ѿ�����
			conn = oraconn.getConnection();
			String sqlString = "select COMPONENT_ITEM from CUX.CUX_PLM_BOM_IFACE where ITEM_NUM='"
				+ sel_id + "'";
			System.out.println("����ѯ��䡿---->" + sqlString);
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sqlString);
			String zijian_id = "";
			if (reset != null && reset.next()) {
				zijian_id = reset.getString("COMPONENT_ITEM");
			}
			stmt.close();
			if(!"".equals(zijian_id) && zijian_id!=null){
				flag_check = true;
			}
			oraconn.closeConn(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag_check;
	}
	
}
