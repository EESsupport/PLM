package com.origin.rac.sac.cgsqdcd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.com.origin.util.OracleConnect;
import cn.com.origin.util.ProgressBarThread;

import com.teamcenter.rac.aif.AbstractAIFOperation;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.kernel.TCComponentUser;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class S4CGSQDCDOperation extends AbstractAIFOperation {

	
	private AbstractAIFUIApplication app = null;
	private TCSession session;
	private String ywst_str = "";//ҵ��ʵ��
	private String kczz_str = "";//�����֯
	private String mddlx_str = "";//Ŀ�ĵ�����
	private String ly_str = "";//��Դ
	private String cgsq_str = "";//�ɹ���������
	private ProgressBarThread progressbar = null ;
	private ArrayList<S4WLInfo> wlinfolist = null;
	//������ر���
	private String applyNumber = "";
	private boolean flag_cgsq = false;
	private String error_msg = "";
	
	
	
	public S4CGSQDCDOperation(AbstractAIFUIApplication application,ArrayList<S4WLInfo> list,String s1,String s2,String s3,
			String s4,String s5,String s6){
		this.app = application;
		session= (TCSession) app.getSession();
		this.wlinfolist =list;
		this.ywst_str = s1;
		this.kczz_str = s2;
		this.applyNumber = s3;
		this.cgsq_str = s4;
		this.mddlx_str = s5;
		this.ly_str = s6;
		
		
	}
	
	@Override
	public void executeOperation() throws Exception {
		
		progressbar = new ProgressBarThread("�ɹ����뵥����" ,"�ɹ����뵥������,���Ե�...");
		progressbar.start();
		System.out.println("ywst_str--->:"+ywst_str);
		System.out.println("kczz_str===--->:"+kczz_str);
		System.out.println("cgsq_str====--->:"+cgsq_str);
		boolean isOK = insertintotable();
		System.out.println("flag_cgsq---->:"+flag_cgsq);
		if(flag_cgsq){
			MessageBox.post("���ݴ���,������ϢΪ:"+error_msg,"����",MessageBox.ERROR);
			return;
		}else{
			if(isOK)
			{
				progressbar.stopBar();
				
				MessageBox.post("�ɹ����뵥�Ѿ����ݵ��м��,�ȴ�ERP����!","��ʾ",MessageBox.WARNING);
				return;
			}else{
				progressbar.stopBar();
				MessageBox.post("�ɹ����봫��ʧ��","��ʾ",MessageBox.WARNING);
				return;
			}
		}
	}
	
	
	//�����м��
	private boolean insertintotable()
	{
		boolean isOK = false;
		if(wlinfolist!=null && wlinfolist.size()>0)
		{			
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			try {
				Connection conn = oraconn.getConnection();
				
				//����׼��
				
//				//PLM����ʱ��
//				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				Date date=new Date();   
//				String dtime=sdf.format(date);  
				//��ǰ�û�
				String user = session.getUser().toString();
				int m = user.indexOf(" (");
				String userName = user.substring(0, m);
				System.out.println("userName--->:"+userName);
				String userName1 = userName + ",";
				System.out.println("userName1--======->:"+userName1);

				for(int i=0;i<wlinfolist.size();i++)
				{
					S4WLInfo oneinfo = wlinfolist.get(i);
					String sql = "insert into CUX_PLM_PR_IMP_INFACE(" +
					"OPERATING_UNIT," +
					"DEST_ORGANIZATION," +
					"DELIVER_TO_LOCATION," +
					"SEGMENT1," +
					"DESCRIPTION," +
					
					"DOCUMENT_TYPE_DISPLAY," +
					"PROJECT_NUMBER," +
					"PROJECT_NAME," +
					"ITEM_NUMBER," +
					"QUANTITY," +
					"NEED_BY_DATE," +
					"REQUESTOR," +
					"DESTINATION_TYPE_DISP," +
					"SOURCE_TYPE_DISP," +

					
					"IFACE_ID," +
					
					"CREATED_BY," +
					"CREATION_DATE," +
					"LAST_UPDATE_DATE," +
					"BATCH_ID" +")" +
					"values(" +
					"'"+ywst_str+"'," +
					"'"+kczz_str+"'," +
					"'"+oneinfo.getAddress()+"'," +
					"'"+applyNumber+"'," +	
					"'"+oneinfo.getRemark()+"'," +	
					"'"+cgsq_str+"'," +	
					"'"+oneinfo.getHetonghao()+"'," +
					"'"+oneinfo.getGcmingcheng()+"'," +
					"'"+oneinfo.getWlbianma()+"'," +
					""+oneinfo.getShuliang()+"," +
					"fnd_conc_date.STRING_TO_DATE('"+oneinfo.getNeedtime()+"')," +
					"'"+userName1+"'," +
					"'"+mddlx_str+"'," +
					"'"+ly_str+"'," +
					"CUX_PLM_PR_IMP_INFACE_s.Nextval," +
					"'"+userName1+"'," +
					"SYSDATE," +
					"SYSDATE," +
					"-1)";
					System.out.println(sql);
					try {
						Statement stmt = conn.createStatement();
						int k = 0;
						try {
							k = stmt.executeUpdate(sql);
						} catch (Exception e) {
							flag_cgsq = true;
							e.printStackTrace();
							error_msg = e.getMessage();
							progressbar.stopBar();
							break;
						}
						if (k > 0) {
							System.out.println("��Ϣ����ɹ�");
						} else {
							System.out.println("����ʧ��");
						}
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				oraconn.closeConn(conn);
			} catch (IOException e) {
				e.printStackTrace();
			}
			isOK = true;
		}
		return isOK;
	}

}
