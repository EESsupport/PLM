package com.origin.rac.sac.sendbominfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.com.origin.util.OracleConnect;
import cn.com.origin.util.ProgressBarThread;
import cn.com.origin.util.ReadProperties;

import com.teamcenter.rac.aif.AbstractAIFOperation;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCComponentBOMViewRevision;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.util.MessageBox;

public class SendYXBomInfoOperation extends AbstractAIFOperation {

	public Connection conn = null;
	public ReadProperties read = null;
	public Statement stmt = null;
	public ResultSet reset = null;
	public TCComponentBOMLine select_bom;
	private ProgressBarThread progressbar = null ;
	private String zzdm_str = "";
	private String sfgd_str = "";
	private String whbm_str = "";
	private String sjry_str = "";
	private String wpxh_str = "";
	private String gs_str = "";
	private String[] properties = {"bl_sequence_no","S4masteroper","bl_quantity","S4ATTRIBUTE7","S4ATTRIBUTE8",
			"S4ATTRIBUTE9","S4ATTRIBUTE11","S4ATTRIBUTE10","S4ATTRIBUTE12","S4MEANING","S4SUPPLY_SUBIN","S4component_rem"};
	private String yxbom = "Y";
	private String userName = "";//������
	private String relation1 = "structure_revisions";
	
	
	public SendYXBomInfoOperation(TCComponentBOMLine bom,String str1,String str7,String str2,String str3,String str4,String str5,String str6){
		this.select_bom = bom;
		this.zzdm_str = str1;
		this.sfgd_str = str7;
		this.whbm_str = str2;
		this.sjry_str = str3;
		this.wpxh_str = str4;
		this.gs_str = str5;
		this.userName = str6;
		System.out.println("select_bom==>:"+select_bom);
		System.out.println("zzdm_str====>:"+zzdm_str);
		System.out.println("sfgd_str====>:"+sfgd_str);
		System.out.println("sjry_str------>:"+sjry_str);
		
	}
	
	
	@Override
	public void executeOperation() throws Exception {

		progressbar = new ProgressBarThread("ԭ��BOM����" ,"ԭ��BOM������,���Ե�...");
		progressbar.start();
		//���������������,��ѷ���������BOM��Ϣ�������м��
		AIFComponentContext[] boms = select_bom.getChildren();
		int k =boms.length;
		String sel_id = select_bom.getProperty("bl_item_item_id");
		// ʵ�������ݿ�����
		OracleConnect oraconn = new OracleConnect();
		conn = oraconn.getConnection();
		for (int i = 0; i < k; i++) {
			System.out.println("sel_id--->:"+sel_id);
			TCComponentBOMLine sub_bom = (TCComponentBOMLine) boms[i].getComponent();
			//�õ�BOM�ĺ������°汾
			TCComponentItemRevision sub_rev = sub_bom.getItem().getLatestItemRevision();
			//�õ�BOM���°汾��BOMVIEW
			TCComponentBOMViewRevision bomview = (TCComponentBOMViewRevision) sub_rev.getRelatedComponent(relation1);
			//���BOMVIEW��Ϊ��,�ж�������ֵ,���Ϊ��,���BOM������
			if(bomview!=null){
				String desc = bomview.getProperty("object_desc").toString();
				System.out.println("rev====----*desc====>:"+desc);
				if("Y".equals(desc)){
					continue;
				}
			}
			String sub_bom_id = sub_bom.getProperty("bl_item_item_id");
			System.out.println("sub_bom_id--->:"+sub_bom_id);
			String[] values = new String[12];
			values = sub_bom.getProperties(properties);
			System.out.println("values====00==>:"+values[0]);
			//���뵽ERP�����ϴ��ݵı�
			String sql = "insert into CUX.CUX_PLM_BOM_IFACE(IFACE_ID,ENG_ITEM_FLAG,ORGANIZATION_CODE,COMFIRM_FLAG,MAINTENANCE_DEPT,DISGNER,ITEM_MODEL,COMPANY_NAME,ITEM_NUM,ITEM_SEQUENCE," +
					"OPERATION_SEQ_NUM,COMPONENT_ITEM,COMPONENT_QUANTITY,COMPONENT_NUM1,COMPONENT_NUM2,COMPONENT_NUM3,VENDOR_NAME,SALES_DESCRIPTION,ABB_COMPONENT_NUM,SUPPLY_TYPE,SUPPLY_SUBINVENTORY," +
					"COMPONENT_REMARKS,CREATE_BY,CREATION_DATE,LAST_UPDATE_DATE,BATCH_ID) values ("
				+"CUX.CUX_PLM_BOM_IFACE_s.nextval"+",'"+ yxbom + "','"+ zzdm_str + "','"+ sfgd_str + "','"+ whbm_str + "','"+ sjry_str + "','"+ wpxh_str + "','"+ gs_str + "','" + sel_id + "','" 
				+ values[0] + "','" + values[1] + "','" + sub_bom_id + "','" + values[2] + "','" + values[3] + "','" + values[4] + "','" + values[5] + "','" + values[6] + "','" + values[7] + "','" 
				+ values[8] + "','" + values[9] + "','" + values[10] + "','" + values[11] +"','"+userName+"',"+"SYSDATE"+ ",SYSDATE" + ",'-1'" + ")";
			System.out.println("[sql���]=yxbom==>��"+sql);
			stmt = conn.createStatement();
			int j = 0;
			try {
				j = stmt.executeUpdate(sql);
			} catch (Exception e) {
				e.printStackTrace();
				progressbar.stopBar();
				MessageBox.post("���ݴ���,������ϢΪ:"+e.getMessage(),"����",MessageBox.ERROR);
				return;
			}
			if (j > 0) {
				System.out.println("��Ϣ����ɹ�");
			} else {
				System.out.println("����ʧ��");
			}
			stmt.close();
		}
		oraconn.closeConn(conn);
		progressbar.stopBar();
		MessageBox.post("���ݳɹ���ERP�м���ȴ�ERP����!", "��ʾ", MessageBox.INFORMATION);
	}

}
