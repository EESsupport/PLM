package com.origin.rac.sac.gylx;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import cn.com.origin.util.OracleConnect;
import cn.com.origin.util.ProgressBarThread;
import cn.com.origin.util.ReadProperties;

import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.kernel.TCTextService;
import com.teamcenter.rac.util.MessageBox;

public class S4GYLXCheckingCommand extends AbstractAIFCommand {

	//private AbstractAIFUIApplication app = null;
	private TCSession session = null;
	//public InterfaceAIFComponent target = null;

	public Connection conn = null;
	public ReadProperties read = null;
	private Statement stmt = null;
	private ResultSet reset = null;

	private boolean gylx_flag = false;
	private boolean gylx_flag1 = false;
	private boolean gylx_flag2 = false;

	//private HashMap<String,String> mapYes = new HashMap<String,String>();
	//private HashMap<String,String> mapNo = new HashMap<String,String>();

	private String relation = "IMAN_specification";
	private String error = "s4error";
	private String tranlogo = "s4Tranlogo";
	//private TCComponent[] tccomponent;
	private String userName = "";

	private String rzname= "����·�ߴ��ݼ����־";
	private String dname= "";

	/**
	 * 
	 * CUX.CUX_PLM_ROUTING_IFACE
	 * CUX.CUX_PLM_ROUTING_HIST
	 * 
	 */
	public S4GYLXCheckingCommand(AbstractAIFUIApplication app){
		//this.app = app;
		//this.target = target;
		session = (TCSession) app.getSession();
	}

	public void executeModal() throws Exception {

		ProgressBarThread progressBar = new ProgressBarThread("����·�߼����...", "����У����Ե�...");
		progressBar.start();

		//TCComponentBOMLine itemLine = (TCComponentBOMLine)target;
		//String itemId = itemLine.getProperty("bl_item_item_id");
		//TCComponentItemRevision itemRev = itemLine.getItemRevision();
		//itemRev.getReferenceListProperty(relationship);
		//tccomponent = itemRev.getRelatedComponents(relation);
		userName = session.getUser().toString();
		dname = rzname+getDate()+".txt";

		Vector<String> vect_gylxerror_FormName = new Vector<String>();
		Vector<String> vect_gylxerror_info = new Vector<String>();
		Vector<String> vect_gylxerror_ID = new Vector<String>();
		Vector<String> vect_gylxsucess_ID = new Vector<String>();
		Vector<String> vect_gylxkong_ID = new Vector<String>();

		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			//----------��ѯ�м���Ǵ���״̬��N�Ĺ���·��------------
			String sql_N_String = "select * from CUX.CUX_PLM_ROUTING_IFACE where PROCESS_FLAG='"
				+ "N" + "' and Created_By='"+ userName +"'" ;
			System.out.println("����ѯ��� N��" + sql_N_String);
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sql_N_String);
			System.out.println("reset==>:"+reset);
			while(reset.next()){
				gylx_flag = true;
				String errorId = reset.getString("ITEM_NUMBER");
				String errorFormName = reset.getString("FORM_NAME");
				String log = reset.getString("ERROR_MSG");
				String error_info = errorFormName + ",������ϢΪ:" + log;
				if(!vect_gylxerror_info.contains(error_info)){
					vect_gylxerror_info.add(error_info);
				}
				if(!vect_gylxerror_ID.contains(errorId)){
					vect_gylxerror_ID.add(errorId);
				}
				if(!vect_gylxerror_FormName.contains(errorFormName)){
					vect_gylxerror_FormName.add(errorFormName);
					setFormProperty(errorId, errorFormName, "N", log);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);

			//--------------��ѯ�м���Ǵ���״̬��Y�Ĺ���·��--------------------------
			conn = oraconn.getConnection();
			//System.out.println("conn====^&&&>>>:"+conn);
			String sql_Y_String = "select * from CUX.CUX_PLM_ROUTING_IFACE where PROCESS_FLAG='"
				+ "Y" + "' and Created_By='"+ userName + "'";
			System.out.println("����ѯ��� Y��" + sql_Y_String);
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sql_Y_String);
			while(reset.next()){
				gylx_flag1 = true;
				String sucessId = reset.getString("ITEM_NUMBER");
				String sucessFormName = reset.getString("FORM_NAME");
				if(!vect_gylxsucess_ID.contains(sucessFormName)){
					vect_gylxsucess_ID.add(sucessFormName);
					setFormProperty(sucessId, sucessFormName, "Y", null);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);
			//-----------------------��ѯ�м���Ǵ���״̬�ǿյĹ���·��---------------------
			conn = oraconn.getConnection();
			String sqlString12 = "select * from CUX.CUX_PLM_ROUTING_IFACE where (PROCESS_FLAG='"
				+ "" + "' or PROCESS_FLAG is null" + ") and Created_By='"+ userName +"'";
			System.out.println("����ѯ��� NULL��" + sqlString12);
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sqlString12);
			while(reset.next()){
				gylx_flag2 = true;
				String kongFormName = reset.getString("ITEM_NUMBER");
				System.out.println("kong_Id------>:"+kongFormName);
				if(!vect_gylxkong_ID.contains(kongFormName)){
					vect_gylxkong_ID.add(kongFormName);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);

			//����м����PROCESS_FLAG��N������ʾ������Ϣ
			if(gylx_flag){
				writeTxtMessage(vect_gylxerror_info,dname);
				MessageBox.post(vect_gylxerror_ID+"����ʧ��!", "��ʾ", MessageBox.INFORMATION);
			}

			//����м����PROCESS_FLAG��Y������ʾ�ɹ���Ϣ
			if(gylx_flag1){
				MessageBox.post(vect_gylxsucess_ID+"�Ѿ��ɹ�����ERP!", "��ʾ", MessageBox.INFORMATION);
			}

			//����м����PROCESS_FLAG�ǿյ�����ʾ��Ϣ
			if(gylx_flag2){
				MessageBox.post(vect_gylxkong_ID+"�Ѿ��������м��ȴ�ERP����ȷ��!", "��ʾ", MessageBox.INFORMATION);
			}

			//��ԭ���е����ݸ��Ƶ����ݱ�CUX.CUX_PLM_ROUTING_HIST����
			copygylxdataTotable();

			//��ԭ����PROCESS_FLAG��Y������N������ɾ��
			deletegylxdataFromtable();

			if(gylx_flag){
				Runtime.getRuntime().exec("cmd /c "+System.getenv("TEMP")+"/"+dname);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			progressBar.stopBar();
		}

	}

	//��ԭ���е����ݸ��Ƶ����ݱ�CUX.CUX_PLM_INV_ITEM_HIST����
	public void copygylxdataTotable(){
		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			String sql_copy = "insert into CUX.CUX_PLM_ROUTING_HIST select * from CUX.CUX_PLM_ROUTING_IFACE where (PROCESS_FLAG='"
				+ "Y" + "' or PROCESS_FLAG='" + "N" +"')" + "and Created_By = '" + userName +"'";
			System.out.println("��������䡿" + sql_copy);
			stmt = conn.createStatement();
			int rs_copy = stmt.executeUpdate(sql_copy);
			if (rs_copy > 0) {
				System.out.println("��Ϣ�����ɹ�");
			} else {
				System.out.println("��Ϣ����ʧ��");
			}
			stmt.close();
			oraconn.closeConn(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//��ԭ����PROCESS_FLAG��Y������N������ɾ��
	public void deletegylxdataFromtable(){
		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			System.out.println("conn---2====>:"+conn);
			String sql_delete = "delete from CUX.CUX_PLM_ROUTING_IFACE where (PROCESS_FLAG='"
				+ "Y" + "' or PROCESS_FLAG='" + "N" +"')" + "and Created_By = '" + userName +"'";
			System.out.println("��ɾ����䡿" + sql_delete);
			stmt = conn.createStatement();
			int rs12 = stmt.executeUpdate(sql_delete);
			if (rs12 > 0) {
				System.out.println("��Ϣɾ���ɹ�");
			} else {
				System.out.println("��Ϣɾ��ʧ��");
			}
			stmt.close();
			oraconn.closeConn(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//д����־
	public void writeTxtMessage(Vector<String> vec_error, String name) {
		try {
			PrintWriter txt = new PrintWriter(new FileWriter(System.getenv("TEMP")+"/"+name,true),true);
			txt.print("                    ��־��Ϣ��                 "+"\r\n");
			for (int i = 0; i < vec_error.size(); i++) {
				String values = vec_error.get(i);
				txt.print(values+"\r\n");
			}
			txt.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDate() {
		Calendar date = Calendar.getInstance();
		java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("ddMMyyyyHHmmss");
		String str = sim.format(date.getTime());
		return str;

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

	//����formֵ
	public void setFormProperty(String item_id, String formName, String tranlogo, String error){
		InterfaceAIFComponent[] items = query(item_id);
		if(items == null || items.length==0){
			System.out.println("ERP���ܺ�û����������Ӧ��Item");
		}else{
			try {
				TCComponentItem sucess_item = (TCComponentItem)items[0];
				TCComponentItemRevision sucess_itemrev = sucess_item.getLatestItemRevision();
				TCComponent[] tccomponent = sucess_itemrev.getRelatedComponents(relation);
				for(int i = 0; i < tccomponent.length; i++){
					if(tccomponent[i] instanceof TCComponentForm){
						TCComponentForm tccomponentForm = (TCComponentForm)tccomponent[i];
						String tmpFormName = tccomponentForm.getProperty("object_name");
						if(tmpFormName.equals(formName)){
							if(tranlogo != null){
								tccomponentForm.getFormTCProperty(this.tranlogo).setStringValue(tranlogo);
							}
							if(error != null){
								tccomponentForm.getFormTCProperty(this.error).setStringValue(tranlogo);
							}
						}
					}
				}
			} catch (TCException e) {
				e.printStackTrace();
			}
		}
	}
}


















































/*public void setFormProperty(String formName, String tranlogo, String error) throws Exception{
	for (int i = 0; i < tccomponent.length; i++) {
		if(tccomponent[i] instanceof TCComponentForm){
			TCComponentForm tccomponentForm = (TCComponentForm)tccomponent[i];
			String tmpFormName = tccomponentForm.getProperty("object_name");
			if(tmpFormName.equals(formName)){
				if(tranlogo != null){
					tccomponentForm.getFormTCProperty(this.tranlogo).setStringValue(tranlogo);
				}
				if(error != null){
					tccomponentForm.getFormTCProperty(this.error).setStringValue(tranlogo);
				}
			}
		}
	}
}*/

/*			//��ԭ���е����ݸ��Ƶ����ݱ�CUX.CUX_PLM_ROUTING_HIST����
conn = oraconn.getConnection();
String sql_copy = "insert into CUX.CUX_PLM_ROUTING_HIST select * from CUX.CUX_PLM_ROUTING_IFACE where (PROCESS_FLAG='"
	+ "Y" + "' or PROCESS_FLAG='" + "N" + "') and ITEM_NUMBER='"+ itemId +"'";
System.out.println("��������䡿" + sql_copy);
stmt = conn.createStatement();
int rs_copy = stmt.executeUpdate(sql_copy);
if (rs_copy > 0) {
	System.out.println("��Ϣ�����ɹ�");
} else {
	System.out.println("��Ϣ����ʧ��");
}
stmt.close();
oraconn.closeConn(conn);

//��ԭ����PROCESS_FLAG��Y������N������ɾ��
conn = oraconn.getConnection();
System.out.println("conn---2====>:"+conn);
String sql_delete = "delete from CUX.CUX_PLM_ROUTING_IFACE where (PROCESS_FLAG='"
	+ "Y" + "' or PROCESS_FLAG='" + "N" + "') and ITEM_NUMBER='"+ itemId +"'";
System.out.println("��ɾ����䡿" + sql_delete);
stmt = conn.createStatement();
int rs12 = stmt.executeUpdate(sql_delete);
if (rs12 > 0) {
	System.out.println("��Ϣɾ���ɹ�");
} else {
	System.out.println("��Ϣɾ��ʧ��");
}
stmt.close();
oraconn.closeConn(conn);*/