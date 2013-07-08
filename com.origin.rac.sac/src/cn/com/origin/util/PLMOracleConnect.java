package cn.com.origin.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cn.com.origin.util.ReadProperties;

	
	public class PLMOracleConnect {
		
		// ����ORACLE�����ݿ���������
		public static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
		// ����ORACLE���ݿ�����ӵ�ַ
		public static String DBURL = null;

		public Connection getConnection() throws IOException {
			ReadProperties read=new ReadProperties();
			String ip_oracle=read.readProperties("plm_ip_oracle");
			String port_oracle=read.readProperties("plm_port_oracle");
			String SID=read.readProperties("plm_SID");
			
			DBURL="jdbc:oracle:thin:@"+ip_oracle+":"+port_oracle+":"+SID;
			// ORACLE���ݿ�������û���
			String DBUSER=read.readProperties("plm_DBUSER");
			// ORACLE���ݿ����������
			String DBPASS=read.readProperties("plm_DBPASS");
			
			Connection conn = null; // ���ݿ�����
			try {
				Class.forName(DBDRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // ������������
			System.out.println("���������ɹ���");
			try {
				conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("���ӳɹ���");
			return conn;
		}

		//�ر�����
		public void closeConn(Connection conn)
		{
			 try{   
		            if(conn != null){   
		                conn.close();   
		            }   
		        }   
		        catch(Exception e){   
		            System.out.println("���ݿ�ر�ʧ��");   
		            e.printStackTrace();   
		        }   
		}

}
