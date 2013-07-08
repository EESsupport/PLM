package com.origin.rac.sac.gylx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import cn.com.origin.util.OracleConnect;
import cn.com.origin.util.ProgressBarThread;
import cn.com.origin.util.S4Bypass;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFOperation;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentFormType;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCFormProperty;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

/**
 * �������	OPERATION_SEQ_NUM
 * �������	STANDARD_OPERATION_CODE
 * ���Ŵ���	DEPARTMENT_CODE
 * ��֯����	ORGANIZATION_CODE
 * ��Դ���	RESOURCE_SEQ_NUM
 * ��Դ����	RESOURCE_CODE
 * �깤�ӿ��	COMPLETION_SUBINVENTORY
 * �깤��λ	COMPLETION_LOCATOR
 * 
 * ���ϱ���	ITEM_NUMBER ??
 */

/**
 * public String[] gongxuHao = null;
 * public String[] gongxuDaima = null;
 * public String[] bumenDaima = null;
 * public String[] zuzhiDaima = null;
 * public String[] ziyuanHao = null;
 * public String[] ziyuangDaima = null;
 * public String zhiKuCun = null;
 * public String huoWei = null;
 * 
 * IMAN_specification
 */

public class S4GYLXToErpOperation extends AbstractAIFOperation{

	//private AbstractAIFApplication app = null;
	private TCSession session = null;
	private S4GYLXToErpCommand command = null;

	public Connection conn = null;
	private Statement stmt = null;
	private ResultSet reset = null;

	private TCFormProperty formProperties[] = null;
	private String sql = null;
	private boolean flag = false;
	private S4Bypass bypass = null;

	private String relation = "IMAN_specification";
	private TCComponent[] tccomponent;
	private boolean flag_fp = false;//�����ж�ѡ��BOM����֯��ERP����û�з���
	private String zzdm_str = "";

	public S4GYLXToErpOperation(S4GYLXToErpCommand command, 
			AbstractAIFApplication app, TCSession session){
		//this.app = app;
		this.session = session;
		this.command = command;
		bypass = new S4Bypass(session);
	}

	public void executeOperation() throws Exception {

		TCComponentBOMLine itemLine = (TCComponentBOMLine)command.target;
		zzdm_str = command.zuzhiDaima[0];
		System.out.println("zzdm_str===>:"+zzdm_str);
		String itemId = itemLine.getProperty("bl_item_item_id");
		TCComponentItemRevision itemRev = itemLine.getItemRevision();
		ProgressBarThread progressBar = new ProgressBarThread("����·�߷�����...", "�����У����Ե�...");
		progressBar.start();
		tccomponent = itemRev.getRelatedComponents(relation);
		if(tccomponent.length > 0){
			for(int i = 0; i < tccomponent.length; i++){
				TCComponentForm tccomponentForm = (TCComponentForm)tccomponent[i];
				String[] tmpFormZuzhi = tccomponentForm.getFormTCProperty("s4texture").getStringArrayValue();
				System.out.println(tmpFormZuzhi[0]);
				if(tmpFormZuzhi[0].equals(zzdm_str)){
					String tranlogo = tccomponentForm.getFormTCProperty("s4Tranlogo").getStringValue();
					if(tranlogo.equals("Y")){
						progressBar.stopBar();
						MessageBox.post("����֯�µ������Ѿ����Ͳ��ɹ����գ������ٴη���!", "��ʾ", MessageBox.INFORMATION);
						return;
					}
				}
			}
		}
		flag_fp = isInOrganization(itemId,zzdm_str);
		if(flag_fp){
			progressBar.stopBar();
			MessageBox.post("��ѡ��BOM���ڵ���֯��ERP��û�з���,����!", "��ʾ", MessageBox.INFORMATION);
			return;
		}
		
		String formName = itemId+"-"+getNowTime();
		String userName = session.getUser().toString();
		System.out.println("userName ==>"+userName);

		OracleConnect oraconn = new OracleConnect();
		conn = oraconn.getConnection();
		String sql_gylx_String = "select * from CUX.CUX_PLM_ROUTING_IFACE where ORGANIZATION_CODE='"
			+ command.zuzhiDaima[0] +"' and ITEM_NUMBER='" + itemId +"'";
		System.out.println("����ѯ��� ��" + sql_gylx_String);
		stmt = conn.createStatement();
		reset = stmt.executeQuery(sql_gylx_String);
		flag = false;
		if(reset.next()){
			flag = true;
		}
		stmt.close();
		oraconn.closeConn(conn);
		if(flag){
			progressBar.stopBar();
			MessageBox.post("����֯�µ������Ѿ����͹��ˣ��ȴ�������!", "��ʾ", MessageBox.INFORMATION);
			return;
		}
		
		try {
			// ʵ�������ݿ�����
			conn = oraconn.getConnection();
			for (int i = 0; i < command.gongxuHao.length; i++) {
				System.out.println("command.ziyuanHao ====>["+command.zhiKuCun+"]");
				if(command.zhiKuCun == null || command.huoWei == null 
						|| command.zhiKuCun.equals(" ") || command.huoWei.equals(" ")
						|| command.zhiKuCun.equals("")|| command.huoWei.equals("")){

					sql = "insert into CUX.CUX_PLM_ROUTING_IFACE(IFACE_ID,OPERATION_SEQ_NUM,STANDARD_OPERATION_CODE,DEPARTMENT_CODE," +
					"ORGANIZATION_CODE,ITEM_NUMBER,FORM_NAME," +
					"Creation_Date,Created_By,Last_Updated_By,Last_Update_Date,Last_Update_Login,Batch_Id) values ("
					+"CUX.CUX_PLM_ROUTING_IFACE_s.Nextval" + ",'" + command.gongxuHao[i] + "','" + command.gongxuDaima[i] + "','" + command.bumenDaima[i] + "','" + command.zuzhiDaima[i] + 
					"','" + itemId + "','" + formName +"',SYSDATE,'" + userName+ "','-1',SYSDATE,'-1','-1' )";
				}
				else{
					sql = "insert into CUX.CUX_PLM_ROUTING_IFACE(IFACE_ID,OPERATION_SEQ_NUM,STANDARD_OPERATION_CODE,DEPARTMENT_CODE," +
					"ORGANIZATION_CODE,COMPLETION_SUBINVENTORY,COMPLETION_LOCATOR,ITEM_NUMBER,FORM_NAME," +
					"Creation_Date,Created_By,Last_Updated_By,Last_Update_Date,Last_Update_Login,Batch_Id) values ("
					+"CUX.CUX_PLM_ROUTING_IFACE_s.Nextval" + ",'" + command.gongxuHao[i] + "','" + command.gongxuDaima[i] + "','" + command.bumenDaima[i] + "','" + command.zuzhiDaima[i] + 
					"','" + command.zhiKuCun + "','" + command.huoWei + "." + 
					"','" + itemId + "','" + formName +"',SYSDATE,'" + userName+ "','-1',SYSDATE,'-1','-1' )";
				}
				System.out.println("sql ==> "+sql);

				stmt = conn.createStatement();
				int k = stmt.executeUpdate(sql);
				if (k > 0) {
					System.out.println("��Ϣ����ɹ�");
				} else {
					System.out.println("����ʧ��");
				}
				stmt.close();
			}
			oraconn.closeConn(conn);

		} catch (Exception e) {
			e.printStackTrace();
		}

		bypass.setpass();

		TCComponentFormType tccomponentFormType = (TCComponentFormType)session.getTypeComponent("S4GYLUPZ");
		TCComponentForm tccomponentForm = tccomponentFormType.create(formName, "", "S4GYLUPZ");
		formProperties = tccomponentForm.getAllFormProperties();
		for(int n = 0; n < formProperties.length; n++)
		{
			String str = formProperties[n].getPropertyName();
			if(str.equals("s4opernumber")){
				formProperties[n].setStringValueArray(command.gongxuHao);
			}
			else if(str.equals("s4operationcode")){
				formProperties[n].setStringValueArray(command.gongxuDaima);
			}
			else if(str.equals("s4department")){
				formProperties[n].setStringValueArray(command.bumenDaima);
			}
			else if(str.equals("s4texture")){
				formProperties[n].setStringValueArray(command.zuzhiDaima);
			}
			else if(str.equals("s4Childstock")){
				if(command.zhiKuCun != null && !command.zhiKuCun.equals(" ") && !command.zhiKuCun.equals("")){
					formProperties[n].setStringValue(command.zhiKuCun);
				}
			}
			else if(str.equals("s4cargo_s")){
				if(command.huoWei != null && !command.huoWei.equals(" ") && !command.huoWei.equals("")){
					formProperties[n].setStringValue(command.huoWei);
				}
			}
		}
		tccomponentForm.setTCProperties(formProperties);
		itemRev.add("IMAN_specification", tccomponentForm);
		bypass.closepass();
		progressBar.stopBar();
		MessageBox.post("����·���Ѿ����ݵ��м���ȴ�ERP����ȷ��!", "��ʾ", MessageBox.INFORMATION);

	}
	public String getNowTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		return dateFormat.format(calendar.getTime());
	}
	
	//ͨ��ѡ����·��ȥ������ڵ���֯��ERP�������֯�µı����ǲ��Ǵ���
	public boolean isInOrganization(String bomid,String zzdm){
		boolean flag_org = false;
		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			String sql = "select * from CUX_PLM_ORG_ITEM_V where Org_Code = '"+ zzdm + "'" + " and Item_Num = '"+ bomid +"'";
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sql);
			if(reset!=null && reset.next())
			{
				
			}else{
				flag_org = true;
			}
			stmt.close();
			
			oraconn.closeConn(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag_org;
	}
	
}
