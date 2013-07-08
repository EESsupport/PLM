package com.origin.rac.sac.itemchangesendinfo;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;
import cn.com.origin.util.OracleConnect;
import cn.com.origin.util.ProgressBarThread;
import cn.com.origin.util.ReadProperties;
import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.kernel.TCTextService;
import com.teamcenter.rac.util.MessageBox;

public class ItemChangeCheckInfoCommand extends AbstractAIFCommand {

	private TCSession session = null;
	public Connection conn = null;
	public ReadProperties read = null;
	private Statement stmt = null;
	private ResultSet reset = null;
	private boolean flag = false;
	private boolean flag1 = false;
	private boolean flag2 = false;
	private ProgressBarThread progressbar = null ;
	private String userName = "";
	private String rzname= "�����޸Ĵ��ݼ����־";
	private String dname= "";
	
	public ItemChangeCheckInfoCommand(AbstractAIFUIApplication app) {
		session = (TCSession) app.getSession();
		//ִ�в˵�����Ա
		userName = session.getUser().toString();
		System.out.println("userName====>:"+userName);
		dname = rzname+getDate()+".txt";
		Vector<String> vect_error_info = new Vector<String>();
		Vector<String> vect_error_ID = new Vector<String>();
		Vector<String> vect_sucess_ID = new Vector<String>();
		Vector<String> vect_kong_ID = new Vector<String>();
		
		try {
			progressbar = new ProgressBarThread("�����޸Ĵ��ݼ��" ,"�����޸Ĵ��ݼ����,���Ե�...");
			progressbar.start();
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			//��ѯ�м���Ǵ���״̬��N������
			String sql_N_String = "select * from CUX.CUX_PLM_INV_ITEM_ATT_IFACE where PROCESS_FLAG='"
					+ "N" + "'" + "and CREATE_BY = '" + userName +"'";
			System.out.println("����ѯ��䡿" + sql_N_String);
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sql_N_String);
			while(reset.next()){
				flag = true;
				String errorId = reset.getString("ITEM_NUMBER");
				System.out.println("buyId---xiugai--->:"+errorId);
				String log = reset.getString("ERROR_MSG");
				String error_info = errorId + ",������ϢΪ:" + log;
//				String error_info = "������ϢΪ:" + log;
				if(!vect_error_info.contains(error_info)){
					vect_error_info.add(error_info);
				}
				if(!vect_error_ID.contains(errorId)){
					vect_error_ID.add(errorId);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);
			//��ѯ�м���Ǵ���״̬��Y������
			conn = oraconn.getConnection();
			String sql_Y_String = "select * from CUX.CUX_PLM_INV_ITEM_ATT_IFACE where PROCESS_FLAG='"
				+ "Y" + "'" + "and CREATE_BY = '" + userName +"'";
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sql_Y_String);
			while(reset.next()){
				flag1 = true;
				String sucess_Id = reset.getString("ITEM_NUMBER");
				System.out.println("sucess_Id----xiugai-->:"+sucess_Id);
				if(!vect_sucess_ID.contains(sucess_Id)){
					vect_sucess_ID.add(sucess_Id);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);
			//��ѯ�м���Ǵ���״̬�ǿյ�����
			conn = oraconn.getConnection();
			String sqlString12 = "select * from CUX.CUX_PLM_INV_ITEM_ATT_IFACE where (PROCESS_FLAG is null or PROCESS_FLAG = '')" 
				+ "and CREATE_BY = '" + userName +"'";
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sqlString12);
			while(reset.next()){
				flag2 = true;
				String kong_Id = reset.getString("ITEM_NUMBER");
				System.out.println("kong_Id---xiugai--->:"+kong_Id);
				if(!vect_kong_ID.contains(kong_Id)){
					vect_kong_ID.add(kong_Id);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);
			//����м����PROCESS_FLAG��N������ʾ������Ϣ
			if(flag){
				writeTxtMessage(vect_error_info, dname);
			}
			
			//��ԭ���е����ݸ��Ƶ����ݱ�CUX.CUX_PLM_INV_ITEM_ATT_HIST����
			copydataTotable();
			
			//��ԭ����PROCESS_FLAG��Y������N������ɾ��
			deletedataFromtable();
			
			progressbar.stopBar();
			//����м����PROCESS_FLAG����Y����N���пյ�����ʾ��Ϣ
			if(flag1 && flag && flag2){
				MessageBox.post(vect_error_ID+"����ʧ��!"
						+"\n"+ vect_sucess_ID+"�Ѿ��ɹ�����ERP!"
						+"\n" + vect_kong_ID+"�Ѿ��������м��ȴ�ERP����ȷ��!", "��ʾ", MessageBox.INFORMATION);
			}
			//����м����PROCESS_FLAGֻ��Y������ʾ��Ϣ
			if(flag1 && !flag && !flag2){
				MessageBox.post(vect_sucess_ID+"�Ѿ��ɹ�����ERP!", "��ʾ", MessageBox.INFORMATION);
			}
			//����м����PROCESS_FLAG��Y��N������ʾ��Ϣ
			if(flag1 && flag && !flag2){
				MessageBox.post(vect_error_ID+"����ʧ��!"
						+"\n"+ vect_sucess_ID+"�Ѿ��ɹ�����ERP!", "��ʾ", MessageBox.INFORMATION);
			}
			//����м����PROCESS_FLAG�пպ�Y������ʾ��Ϣ
			if(flag1 && !flag && flag2){
				MessageBox.post(vect_sucess_ID+"�Ѿ��ɹ�����ERP!!"
						+"\n"+ vect_kong_ID+"�Ѿ��������м��ȴ�ERP����ȷ��!", "��ʾ", MessageBox.INFORMATION);
			}
			//����м����PROCESS_FLAGֻ�ǿյ�����ʾ��Ϣ
			if(!flag1 && !flag && flag2){
				MessageBox.post(vect_kong_ID+"�Ѿ��������м��ȴ�ERP����ȷ��!", "��ʾ", MessageBox.INFORMATION);
			}
			//����м����PROCESS_FLAG��Ϊ�պ�N������ʾ��Ϣ
			if(!flag1 && flag && flag2){
				MessageBox.post(vect_error_ID+"����ʧ��!"
						+"\n"+ vect_kong_ID+"�Ѿ��������м��ȴ�ERP����ȷ��!", "��ʾ", MessageBox.INFORMATION);
			}
			//����м����PROCESS_FLAGֻ��N������ʾ��Ϣ
			if(!flag1 && flag && !flag2){
				MessageBox.post(vect_error_ID+"����ʧ��!", "��ʾ", MessageBox.INFORMATION);
			}
			if(!flag && !flag1 && !flag2){
				MessageBox.post("Ŀǰû������Ҫ��������,������!", "��ʾ", MessageBox.INFORMATION);
			}
			//�Զ�����־�ļ�
			if(flag){
				Runtime.getRuntime().exec("cmd /c "+System.getenv("TEMP")+"/"+dname);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//��ԭ���е����ݸ��Ƶ����ݱ�CUX.CUX_PLM_INV_ITEM_ATT_HIST����
	public void copydataTotable(){
		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			String sql_copy = "insert into CUX.CUX_PLM_INV_ITEM_ATT_HIST select * from CUX.CUX_PLM_INV_ITEM_ATT_IFACE where (PROCESS_FLAG='"
				+ "Y" + "' or PROCESS_FLAG='" + "N" +"')" + "and CREATE_BY = '" + userName +"'";
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
	public void deletedataFromtable(){
		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			String sql_delete = "delete from CUX.CUX_PLM_INV_ITEM_ATT_IFACE where (PROCESS_FLAG='"
				+ "Y" + "' or PROCESS_FLAG='" + "N" +"')" + "and CREATE_BY = '" + userName +"'";
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
	public void writeTxtMessage(Vector<String> vec_error,String name) {
		try {
			PrintWriter txt = new PrintWriter(new FileWriter(System.getenv("TEMP")+"/"+name,true),true);
			txt.print("                    ��־��Ϣ��                 "+"\n");
			for (int i = 0; i < vec_error.size(); i++) {
				String values = vec_error.get(i);
				txt.print(values+"\n");
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
