package cn.com.origin.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cn.com.origin.util.ReadProperties;

	
	public class OracleConnect {
		
		// ����ORACLE�����ݿ���������
		public static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
		// ����ORACLE���ݿ�����ӵ�ַ
		public static String DBURL = null;

		public Connection getConnection() throws IOException {
			ReadProperties read=new ReadProperties();
			String ip_oracle=read.readProperties("ip_oracle");
			String port_oracle=read.readProperties("port_oracle");
			String SID=read.readProperties("SID");
			
			DBURL="jdbc:oracle:thin:@"+ip_oracle+":"+port_oracle+":"+SID;
			// ORACLE���ݿ�������û���
			String DBUSER=read.readProperties("DBUSER");
			// ORACLE���ݿ����������
			String DBPASS=read.readProperties("DBPASS");
			
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
