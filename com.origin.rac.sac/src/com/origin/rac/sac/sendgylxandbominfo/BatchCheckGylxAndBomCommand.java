package com.origin.rac.sac.sendgylxandbominfo;

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
import cn.com.origin.util.S4Bypass;

import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMViewRevision;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.kernel.TCTextService;
import com.teamcenter.rac.util.MessageBox;

public class BatchCheckGylxAndBomCommand extends AbstractAIFCommand {

	private TCSession session = null;
	public Connection conn = null;
	public ReadProperties read = null;
	private Statement stmt = null;
	private ResultSet reset = null;
	private boolean bom_flag = false;
	private boolean bom_flag1 = false;
	private boolean bom_flag2 = false;
	private boolean gylx_flag = false;
	private boolean gylx_flag1 = false;
	private boolean gylx_flag2 = false;
	private HashMap<String,String> map = new HashMap<String,String>();
	private String relation1 = "structure_revisions";
	private String attri = "object_desc";
	private String relation = "IMAN_specification";
	private String error = "s4error";
	private String tranlogo = "s4Tranlogo";
	private S4Bypass bypass = null;
	private ProgressBarThread progressbar = null ;
	private String userName = "";
	private String rzname= "����BOM������·���������ݼ����־";
	private String dname= "";
	
	
	public BatchCheckGylxAndBomCommand(AbstractAIFUIApplication app) {
		session = (TCSession) app.getSession();
		bypass = new S4Bypass(session);
		//ִ�в˵�����Ա
		userName = session.getUser().toString();
		System.out.println("userName====>:"+userName);
		dname = rzname+getDate()+".txt";
		Vector<String> vect_gylxerror_FormName = new Vector<String>();
		Vector<String> vect_gylxerror_info = new Vector<String>();
		Vector<String> vect_gylxerror_ID = new Vector<String>();
		Vector<String> vect_gylxsucess_ID = new Vector<String>();
		Vector<String> vect_gylxkong_ID = new Vector<String>();
		Vector<String> vect_bomerror_info = new Vector<String>();
		Vector<String> vect_bomerror_ID = new Vector<String>();
		Vector<String> vect_bomsucess_ID = new Vector<String>();
		Vector<String> vect_bomkong_ID = new Vector<String>();
		try {
			progressbar = new ProgressBarThread("����BOM������·���������ݼ��" ,"����BOM������·���������ݼ����,���Ե�...");
			progressbar.start();
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			//----------��ѯ�м���Ǵ���״̬��N�Ĺ���·��------------
			String sql_gylxN_String = "select * from CUX.CUX_PLM_ROUTING_IFACE where PROCESS_FLAG='"
				+ "N" + "' and Created_By='"+ userName +"'" ;
			System.out.println("����ѯ��� N��" + sql_gylxN_String);
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sql_gylxN_String);
			System.out.println("reset==>:"+reset);
			while(reset.next()){
				gylx_flag = true;
				String errorId = reset.getString("ITEM_NUMBER");
				String errorFormName = reset.getString("FORM_NAME");
				String log = reset.getString("ERROR_MSG");
				String error_info = errorId + ",������ϢΪ:" + log;
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
			String sql_gylxY_String = "select * from CUX.CUX_PLM_ROUTING_IFACE where PROCESS_FLAG='"
				+ "Y" + "' and Created_By='"+ userName + "'";
			System.out.println("����ѯ��� Y��" + sql_gylxY_String);
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sql_gylxY_String);
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
			String sql_gylxK_String = "select * from CUX.CUX_PLM_ROUTING_IFACE where (PROCESS_FLAG='"
				+ "" + "' or PROCESS_FLAG is null" + ") and Created_By='"+ userName +"'";
			System.out.println("����ѯ��� NULL��" + sql_gylxK_String);
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sql_gylxK_String);
			while(reset.next()){
				gylx_flag2 = true;
				String kongFormName = reset.getString("ITEM_NUMBER");
				System.out.println("kong_Id----gylx-->:"+kongFormName);
				if(!vect_gylxkong_ID.contains(kongFormName)){
					vect_gylxkong_ID.add(kongFormName);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);
			
			
			conn = oraconn.getConnection();
			//��ѯ�м���Ǵ���״̬��N��BOM
			String sql_bomN_String = "select * from CUX.CUX_PLM_BOM_IFACE where PROCESS_FLAG='"
					+ "N" + "'" + " and ENG_ITEM_FLAG = 'N'" + "and CREATE_BY = '" + userName +"'";
			System.out.println("����ѯ��䡿==gc=>" + sql_bomN_String);
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sql_bomN_String);
			while(reset.next()){
				bom_flag = true;
				String errorId = reset.getString("COMPONENT_ITEM");
				System.out.println("buyId---gc--->:"+errorId);
				String parent_Id = reset.getString("ITEM_NUM");
				String log = reset.getString("ERROR_MSG");
				String error_info = "��ID��"+parent_Id+",��ID��"+errorId + ",������ϢΪ:" + log;
//				String error_info = "������ϢΪ:" + log;
				map.put(parent_Id, "N");
				if(!vect_bomerror_info.contains(error_info)){
					vect_bomerror_info.add(error_info);
				}
				if(!vect_bomerror_ID.contains(parent_Id)){
					vect_bomerror_ID.add(parent_Id);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);
			//��ѯ�м���Ǵ���״̬��Y��BOM
			conn = oraconn.getConnection();
			System.out.println("conn====gc>>>:"+conn);
			String sql_Y_String = "select * from CUX.CUX_PLM_BOM_IFACE where PROCESS_FLAG='"
				+ "Y" + "'" + " and ENG_ITEM_FLAG = 'N'" + "and CREATE_BY = '" + userName +"'";
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sql_Y_String);
			while(reset.next()){
				bom_flag1 = true;
				String parent_Id = reset.getString("ITEM_NUM");
				System.out.println("parent_Id---gc--->:"+parent_Id);
				if(!vect_bomsucess_ID.contains(parent_Id)){
					vect_bomsucess_ID.add(parent_Id);
				}
				map.put(parent_Id, "Y");
			}
			stmt.close();
			oraconn.closeConn(conn);
			//��ѯ�м���Ǵ���״̬�ǿյ�BOM
			conn = oraconn.getConnection();
			System.out.println("conn=====22gc=====>:"+conn);
			String sqlString12 = "select * from CUX.CUX_PLM_BOM_IFACE where (PROCESS_FLAG is null or PROCESS_FLAG = '')��and ENG_ITEM_FLAG = 'N'"
				+ "and CREATE_BY = '" + userName +"'";
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sqlString12);
			while(reset.next()){
				bom_flag2 = true;
				String kong_Id = reset.getString("ITEM_NUM");
				System.out.println("kong_Id---gc--->:"+kong_Id);
				if(!vect_bomkong_ID.contains(kong_Id)){
					vect_bomkong_ID.add(kong_Id);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);
			//����м����PROCESS_FLAG��N������ʾ������Ϣ
			if(gylx_flag || bom_flag){
				writeTxtMessage(vect_gylxerror_info,vect_bomerror_info, dname);
			}
			//����м����PROCESS_FLAG��Y������ʾ�ɹ���Ϣ
			if(bom_flag1 || bom_flag){
				//����bomview����������ֵ
				setDescProperty();
			}
			
			//��ԭ���е����ݸ��Ƶ����ݱ�CUX.CUX_PLM_ROUTING_HIST����
			copygylxdataTotable();

			//��ԭ����PROCESS_FLAG��Y������N������ɾ��
			deletegylxdataFromtable();
			
			//��ԭ���е����ݸ��Ƶ����ݱ�CUX.CUX_PLM_BOM_HIST����
			copybomdataTotable();
			
			//��ԭ����PROCESS_FLAG��Y������N������ɾ��
			deletebomdataFromtable();
			
			progressbar.stopBar();
			//�г����п�����
			if(gylx_flag && !gylx_flag1 && !gylx_flag2 && !bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && !gylx_flag2 && !bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && gylx_flag2 && !bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && !gylx_flag2 && bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && !gylx_flag2 && !bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && !gylx_flag2 && !bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && !gylx_flag2 && !bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && gylx_flag2 && bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n" 
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && gylx_flag2 && !bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && !gylx_flag2 && bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n" 
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && !gylx_flag2 && bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && gylx_flag2 && !bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && !gylx_flag2 && !bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && gylx_flag2 && bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && !gylx_flag2 && !bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && gylx_flag2 && bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && gylx_flag2 && !bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n" 
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && !gylx_flag2 && bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && !gylx_flag2 && bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n" 
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && gylx_flag2 && !bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && !gylx_flag2 && !bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n" 
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && gylx_flag2 && bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && !gylx_flag2 && !bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n" 
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && gylx_flag2 && bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && gylx_flag2 && bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n" 
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && !gylx_flag2 && !bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && gylx_flag2 && !bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n" 
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && !gylx_flag2 && bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && gylx_flag2 && !bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n" 
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && !gylx_flag2 && bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && !gylx_flag2 && bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_bomerror_ID+"BOM����ʧ��!" + "\n" 
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && gylx_flag2 && !bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && !gylx_flag2 && bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_bomerror_ID+"BOM����ʧ��!" + "\n" 
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && gylx_flag2 && !bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && !gylx_flag2 && !bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n" 
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && gylx_flag2 && bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && gylx_flag2 && bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && gylx_flag2 && bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && !gylx_flag2 && bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && gylx_flag2 && !bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && gylx_flag2 && bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && gylx_flag2 && bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && gylx_flag2 && !bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && !gylx_flag2 && bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_bomerror_ID+"BOM����ʧ��!!" + "\n" 
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && !gylx_flag2 && bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && gylx_flag2 && !bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!!" + "\n" 
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && !gylx_flag2 && !bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && gylx_flag2 && bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!!" + "\n" 
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && !gylx_flag2 && !bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && gylx_flag2 && bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!!" + "\n" 
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && gylx_flag2 && bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && !gylx_flag2 && !bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!!" + "\n" 
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && gylx_flag2 && !bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && !gylx_flag2 && bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!!" + "\n" 
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && gylx_flag2 && !bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && !gylx_flag2 && bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!!" + "\n" 
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && !gylx_flag2 && bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && gylx_flag2 && !bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && !gylx_flag2 && bom_flag && !bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && gylx_flag2 && !bom_flag && bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && !gylx_flag1 && !gylx_flag2 && !bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && gylx_flag1 && gylx_flag2 && bom_flag && !bom_flag1 && !bom_flag2){
				MessageBox.post(vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!!" + "\n" 
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(gylx_flag && gylx_flag1 && gylx_flag2 && bom_flag && bom_flag1 && bom_flag2){
				MessageBox.post(vect_gylxerror_ID+"����·�ߴ���ʧ��!" + "\n" 
						+ vect_gylxsucess_ID+"����·���Ѿ��ɹ�����ERP!" + "\n"
						+ vect_gylxkong_ID+"����·���Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ vect_bomerror_ID+"BOM����ʧ��!" + "\n"
						+ vect_bomsucess_ID+"BOM�Ѿ��ɹ�����ERP!" + "\n"
						+ vect_bomkong_ID+"BOM�Ѿ��������м��ȴ�ERP����ȷ��!" + "\n"
						+ "����BOM������·���������ݼ�����!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!gylx_flag && !gylx_flag1 && !gylx_flag2 && !bom_flag && !bom_flag && !bom_flag2){
				MessageBox.post("Ŀǰû������Ҫ���Ĺ���·�ߺ�BOM,������!", "��ʾ", MessageBox.INFORMATION);
			}
			//�Զ�����־�ļ�
			if(gylx_flag || bom_flag){
				Runtime.getRuntime().exec("cmd /c "+System.getenv("TEMP")+"/"+dname);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//����bomview����������ֵ
	public void setDescProperty(){
		System.out.println("map=gc===>:"+map.size());
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String item_id = (String) iterator.next();
			String value = map.get(item_id);
			InterfaceAIFComponent[] items = query(item_id);
			if(items == null || items.length==0){
				System.out.println("ERP���ܺ�û����������Ӧ��Item");
			}else{
				TCComponentItem sucess_item = (TCComponentItem) items[0];
				try {
					//����bomview����������ֵ
					TCComponentItemRevision rev = sucess_item.getLatestItemRevision();
					TCComponentBOMViewRevision bomview = (TCComponentBOMViewRevision) rev.getRelatedComponent(relation1);
					if(bomview!=null){
						bypass.setpass();
						bomview.setProperty(attri, value);
					}
				} catch (TCException e) {
					e.printStackTrace();
				}
			}
		}
		bypass.closepass();
	}
	
	
	//��ԭ���е����ݸ��Ƶ����ݱ�CUX.CUX_PLM_BOM_HIST����
	public void copybomdataTotable(){
		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			String sql_copy = "insert into CUX.CUX_PLM_BOM_HIST select * from CUX.CUX_PLM_BOM_IFACE where (PROCESS_FLAG='"
				+ "Y" + "' or PROCESS_FLAG='" + "N" +"')" + " and ENG_ITEM_FLAG = 'N'" + "and CREATE_BY = '" + userName +"'";
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
	
	//��ԭ���е����ݸ��Ƶ����ݱ�CUX.CUX_PLM_ROUTING_HIST����
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
	public void deletebomdataFromtable(){
		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			System.out.println("conn---gc====>:"+conn);
			String sql_delete = "delete from CUX.CUX_PLM_BOM_IFACE where (PROCESS_FLAG='"
				+ "Y" + "' or PROCESS_FLAG='" + "N" +"')" + " and ENG_ITEM_FLAG = 'N'" + "and CREATE_BY = '" + userName +"'";
			System.out.println("��ɾ����䡿===gc==>:" + sql_delete);
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
								bypass.setpass();
								tccomponentForm.getFormTCProperty(this.tranlogo).setStringValue(tranlogo);
							}
							if(error != null){
								bypass.setpass();
								tccomponentForm.getFormTCProperty(this.error).setStringValue(tranlogo);
							}
						}
					}
				}
			} catch (TCException e) {
				e.printStackTrace();
			}
		}
		bypass.closepass();
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
	
	//д����־
	public void writeTxtMessage(Vector<String> vec_gylxerror,Vector<String> vec_bomerror,String name) {
		try {
			PrintWriter txt = new PrintWriter(new FileWriter(System.getenv("TEMP")+"/"+name,true),true);
			if(gylx_flag && bom_flag){
				txt.print("                    ����·����־��Ϣ��                 "+"\n");
				for (int i = 0; i < vec_gylxerror.size(); i++) {
					String gylx_values = vec_gylxerror.get(i);
					txt.print(gylx_values+"\n");
				}
				txt.print("                    BOM��־��Ϣ��                 "+"\n");
				for (int i = 0; i < vec_bomerror.size(); i++) {
					String bom_values = vec_bomerror.get(i);
					txt.print(bom_values+"\n");
				}
			}
			if(gylx_flag && !bom_flag){
				txt.print("                    ����·����־��Ϣ��             "+"\n");
				for (int i = 0; i < vec_gylxerror.size(); i++) {
					String gylx_values = vec_gylxerror.get(i);
					txt.print(gylx_values+"\n");
				}
				txt.print("                    BOM��־��Ϣ��                 "+"\n");
				txt.print("BOMû�д���"+"\n");
			}
			if(!gylx_flag && bom_flag){
				txt.print("                    ����·����־��Ϣ��             "+"\n");
				txt.print("����·��û�д���"+"\n");
				txt.print("                    BOM��־��Ϣ��                 "+"\n");
				for (int i = 0; i < vec_bomerror.size(); i++) {
					String values = vec_bomerror.get(i);
					txt.print(values+"\n");
				}
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
	
}
