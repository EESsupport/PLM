package com.origin.rac.sac.sendgylxandbominfo;

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
import com.teamcenter.rac.kernel.TCComponentBOMWindow;
import com.teamcenter.rac.kernel.TCComponentBOMWindowType;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCProperty;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class BatchSendGylxAndBomCommand extends AbstractAIFCommand {

	private InterfaceAIFComponent[] targets = null;
	private AbstractAIFUIApplication application = null;
	private String relation = "IMAN_master_form_rev";
	private String relation1 = "structure_revisions";
	private String relation2 = "IMAN_specification";
	private String relation3 = "contents";
	private String relation4 = "S4MZ0";
	private TCSession session = null;
	private String attri = "s4Passing_State";
	private String attri1 = "s4Tranlogo";
	private String gylxform_type = "S4GYLUPZ";
	private boolean flag_lx = false;//������ʶѡ��Ķ����ǲ������϶���
	private boolean flag_2 = false;//������ʶѡ��Ķ����ǲ���ITEM
	private boolean flag_3 = false;//������ʶѡ��Ķ����ǲ����ļ���
	private boolean flag_sfdd = false;//������ʶѡ��Ķ����ǲ��Ƕ���������
	private boolean flag_sffb = false;//����ѡ��Ķ��������ǲ�����BOMVIEW
	private boolean flag_view = false;//����ѡ��Ķ��������ǲ�����BOMVIEW
	private boolean flag_haveview = false;//����ѡ��Ķ������ϵ�BOMVIEW�ǲ�����BOM
	private boolean flag_f_hydd = false;//������ʶ�ļ������Ƿ��ж�������
	private boolean flag_f_sfdd = false;//������ʶ�ļ������Ƿ񶩵�����
	private boolean flag_f_sfdd1 = false;//������ʶ�ļ������Ƿ񶩵�����
	private boolean flag_f_sffb = false;//������ʶ�ļ����¶��������Ƿ񷢲�
	private boolean flag_f_view = false;//������ʶ�ļ����¶��������ǲ�����BOMVIEW
	private boolean flag_f_haveview = false;//������ʶ�ļ����¶������ϵ�BOMVIEW�ǲ�����BOM
	private boolean flag_desc_cd = false;//������ʶѡ��Ķ��������ǲ����Ѿ����ݹ�ERP
	private boolean flag_vtp_cd = false;//������ʶѡ��Ķ������ϵ�BOMVIEW�����ǲ���S4MZ0
	private boolean flag_f_desc_cd = false;//������ʶѡ���ļ����µĶ��������ǲ����Ѿ����ݹ�ERP
	private boolean flag_f_vtp_cd = false;//������ʶѡ���ļ����µĶ������ϵ�BOMVIEW�����ǲ���S4MZ0
	private boolean flag1 = false;//������ʶѡ������ϵ���BOM����bomview���ǲ����Ѿ����ݹ�ERP
	private boolean flag_f_1 = false;//������ʶѡ���ļ����µ����ϵ���BOM����bomview���ǲ����Ѿ����ݹ�ERP
	private boolean flag2 = false;//������ʶѡ��������ǲ��Ǵ��ݹ�ERP
	private boolean flag_f_2 = false;//������ʶѡ���ļ�������������ǲ��Ǵ��ݹ�ERP
	private boolean flag_bt = false;//������ʶѡ�����ϵ���BOM�ϵı�������
	private boolean flag_f_bt = false;//������ʶѡ���ļ��������ϵ���BOM�ϵı�������
	private boolean flag3 = false;//������ʶѡ����������м�����ǲ����Ѿ�����
	private boolean flag4 = false;//������ʶѡ�����ϵĹ���·�����м�����ǲ����Ѿ�����
	private boolean flag6 = false;//������ʶѡ���BOM���ӽṹID������֯��ERP����û��
	private boolean flag_gylx = false;//������ʶѡ��Ķ��������ǲ��������˹���·��
	private boolean flag_gylx1= false;//������ʶѡ��Ķ��������ǲ��������˹���·��
	private boolean flag_gylx_cd= false;//������ʶѡ��Ķ��������ǲ����Ѿ�����
	private boolean flag_f_gylx_cd= false;//������ʶѡ��Ķ��������ǲ����Ѿ�����
	private boolean flag_f_gylx = false;//������ʶѡ���ļ����µĶ��������ǲ��������˹���·��
	private boolean flag_f_gylx1 = false;//������ʶѡ���ļ����µĶ��������ǲ��������˹���·��
	private boolean flag_cdgycg = false;//������ʶ�Ƿ�ɹ������˹���·��
	private boolean flag_cdbomcg = false;//������ʶ�Ƿ�ɹ�������BOM
	private String yxbom = "N";
	public Connection conn = null;
	public ReadProperties read = null;
	public Statement stmt = null;
	public ResultSet reset = null;
	private Vector<String> vec_1 = new Vector<String>();
	private Vector<String> vec_f_1 = new Vector<String>();
	private Vector<String> vec_2 = new Vector<String>();
	private Vector<String> vec_f_2 = new Vector<String>();
	private Vector<String> vec_sfdd = new Vector<String>();
	private Vector<String> vec_f_sfdd = new Vector<String>();
	private Vector<String> vec_desc_cd = new Vector<String>();
	private Vector<String> vec_vtp_cd = new Vector<String>();
	private Vector<String> vec_f_desc_cd = new Vector<String>();
	private Vector<String> vec_f_vtp_cd = new Vector<String>();
	private Vector<String> vec_sffb = new Vector<String>();
	private Vector<String> vec_f_sffb = new Vector<String>();
	private Vector<String> vec_view = new Vector<String>();
	private Vector<String> vec_haveview = new Vector<String>();
	private Vector<String> vec_f_view = new Vector<String>();
	private Vector<String> vec_f_haveview = new Vector<String>();
	private Vector<String> vec_bt = new Vector<String>();
	private Vector<String> vec_f_bt = new Vector<String>();
	private Vector<String> vec_3 = new Vector<String>();
	private Vector<String> vec_4 = new Vector<String>();
	private Vector<String> vec_ID = new Vector<String>();//�������ѡ��BOM�������ӽṹID���ڵ���֯��ERP���Ƿ����
	private Vector<String> vec_error_ID = new Vector<String>();//������ʾѡ���BOM�������ӽṹID������������֯���治���ڵ�ID
	private Vector<String> vec_gylx = new Vector<String>();//�����洢û�����ù���·�ߵ�ID
	private Vector<String> vec_gylx1 = new Vector<String>();//�����洢û�����ù���·�ߵ�ID
	private Vector<String> vec_f_gylx = new Vector<String>();//�����洢û�����ù���·�ߵ�ID
	private Vector<String> vec_f_gylx1 = new Vector<String>();//�����洢û�����ù���·�ߵ�ID
	private Vector<String> vec_form_type = new Vector<String>();//�����洢ѡ�񶩵������ϰ汾���������
	private Vector<String> vec_f_form_type = new Vector<String>();//�����洢ѡ�񶩵������ϰ汾���������
	private Vector<String> vec_gylx_cd = new Vector<String>();//�����洢ѡ�񶩵������Ϲ���·�ߺ�BOM�ǲ��Ƕ��Ѿ�����
	private Vector<String> vec_f_gylx_cd = new Vector<String>();//�����洢ѡ���ļ����¶��������Ϲ���·�ߺ�BOM�ǲ��Ƕ��Ѿ�����
	private String[] properties = {"bl_sequence_no","S4masteroper","bl_quantity","S4ATTRIBUTE7","S4ATTRIBUTE8",
								"S4ATTRIBUTE9","S4ATTRIBUTE11","S4ATTRIBUTE10","S4ATTRIBUTE12","S4MEANING","S4SUPPLY_SUBIN","S4component_rem"};
	private ProgressBarThread progressbar = null ;
	private String userName = "";//������
	private String error_cd = "N";
	private String zzdm = "MZ0";
	private String check_item_type = "S4CP";
	private TCProperty[] formProperties = null;
	private String[] propertyNames = {"object_name","s4opernumber","s4operationcode","s4department","s4texture","s4Childstock","s4cargo_s"};
	private String[] Opernumber_array,Operationcode_array,Department_array,Texture_array;
	
	public BatchSendGylxAndBomCommand(AbstractAIFUIApplication app) {
		application =app;
		session = (TCSession) application.getSession();
		targets = (InterfaceAIFComponent[]) application.getTargetComponents();
		System.out.println("targets==2222=>:"+targets.length);
		//ִ�в˵�����Ա
		userName = session.getUser().toString();
//		int m = user.indexOf("(");
//		userName = user.substring(0, m);
		if(targets.length>=1){
			try {
				progressbar = new ProgressBarThread("����BOM������·����������" ,"����BOM������·������������,���Ե�...");
				progressbar.start();
				
				for (int i = 0; i < targets.length; i++) {
					if(!(targets[i] instanceof TCComponentItem) && !(targets[i] instanceof TCComponentFolder) && !(targets[i] instanceof TCComponentItemRevision)){
						flag_lx = true;
					}
					if(targets[i] instanceof TCComponentItem || targets[i] instanceof TCComponentItemRevision){
						flag_2 = true;
						TCComponentItem item = null;
						TCComponentItemRevision item_rev =null;
						String viewtype = "";
						if(targets[i] instanceof TCComponentItem){
							item = (TCComponentItem) targets[i];
							item_rev = item.getLatestItemRevision();
							System.out.println("item_rev-888888888---->:"+item_rev);
						}else if(targets[i] instanceof TCComponentItemRevision){
							item_rev = (TCComponentItemRevision) targets[i];
							item = item_rev.getItem();
							System.out.println("item_rev---9999---->:"+item_rev);
						}
						String erro_id = item.getProperty("item_id");
						if(!check_item_type.equals(item.getType().toString())){
							flag_sfdd = true;
							if(!vec_sfdd.contains(erro_id)){
								vec_sfdd.add(erro_id);
							}
						}else{
							//���ѡ��Ķ����Ƕ��������϶���,�����ж���û��BOMVIEW
							TCComponentBOMViewRevision sel_bomview = (TCComponentBOMViewRevision) item_rev.getRelatedComponent(relation1);
							if(sel_bomview==null){
								flag_view = true;
								if(!vec_view.contains(erro_id)){
									vec_view.add(erro_id);
								}
							}else{
								TCComponent[] components = item_rev.getRelatedComponents(relation4);
								if(components==null || components.length ==0){
									flag_haveview = true;
									if(!vec_haveview.contains(erro_id)){
										vec_haveview.add(erro_id);
									}
								}
								String sel_viewtype = sel_bomview.toString();
								viewtype =  sel_viewtype.substring(sel_viewtype.length()-5, sel_viewtype.length());
								System.out.println("viewtype====>:"+viewtype);
								if(!"S4MZ0".equals(viewtype)){
									flag_vtp_cd = true;
									if(!vec_vtp_cd.contains(erro_id)){
										vec_vtp_cd.add(erro_id);
									}
								}
							}
							//���ѡ��Ķ����Ƕ��������϶���,��ȡ�ж��ǲ��Ƿ���
							String str_status = item_rev.getProperty("release_status_list");
							//�ж������Ƿ񷢲�
							if("".equals(str_status) || str_status==null){
								flag_sffb = true;
								if(!vec_sffb.contains(erro_id)){
									vec_sffb.add(erro_id);
								}
							}
							//�ж�ѡ��BOM�����ǲ��������˹���·��
							TCComponent[] forms = item_rev.getRelatedComponents(relation2);
							if(forms==null || forms.length==0){
								flag_gylx = true;
								if(!vec_gylx.contains(erro_id)){
									vec_gylx.add(erro_id);
								}
							}else{
								for (int j = 0; j < forms.length; j++) {
									String type = forms[j].getType().toString();
									if(!vec_form_type.contains(type)){
										vec_form_type.add(type);
									}
								}
								if(!vec_form_type.contains(gylxform_type)){
									flag_gylx1 = true;
									if(!vec_gylx1.contains(erro_id)){
										vec_gylx1.add(erro_id);
									}
								}
							}
						}
					}else if(targets[i] instanceof TCComponentFolder){
						//��ʶ�ǲ���ѡ����ļ��ж���
						flag_3 = true;
						TCComponentFolder folder = (TCComponentFolder) targets[i];
						TCComponent[] coms = folder.getRelatedComponents(relation3);
						//�ж�ѡ����ļ������ǲ����ж���
						if (coms != null && coms.length > 0) {
							for (int j = 0; j < coms.length; j++) {
								//�ж�ѡ����ļ������ǲ��Ƕ��������϶���
								if(coms[j] instanceof TCComponentItem){
									TCComponentItem item = (TCComponentItem) coms[j];
									String erro_f_id = item.getProperty("item_id");
									if(!check_item_type.equals(item.getType().toString())){
										flag_f_sfdd = true;
										if(!vec_f_sfdd.contains(erro_f_id)){
											vec_f_sfdd.add(erro_f_id);
										}
									}else{
										TCComponentItemRevision item_rev = item.getLatestItemRevision();
										//���ѡ��Ķ����Ƕ��������϶���,�����ж���û��BOMVIEW
										TCComponentBOMViewRevision sel_bomview = (TCComponentBOMViewRevision) item_rev.getRelatedComponent(relation1);
										if(sel_bomview==null){
											flag_f_view = true;
											if(!vec_f_view.contains(erro_f_id)){
												vec_f_view.add(erro_f_id);
											}
										}else{
											TCComponent[] components = item_rev.getRelatedComponents(relation4);
											if(components==null || components.length ==0){
												flag_f_haveview = true;
												if(!vec_f_haveview.contains(erro_f_id)){
													vec_f_haveview.add(erro_f_id);
												}
											}
											String sel_viewtype = sel_bomview.toString();
											String viewtype =  sel_viewtype.substring(sel_viewtype.length()-5, sel_viewtype.length());
											System.out.println("viewtype====>:"+viewtype);
											if(!"S4MZ0".equals(viewtype)){
												flag_f_vtp_cd = true;
												if(!vec_f_vtp_cd.contains(erro_f_id)){
													vec_f_vtp_cd.add(erro_f_id);
												}
											}
										}
										//���ѡ��Ķ����Ƕ��������϶���,��ȡ�ж��ǲ��Ƿ���
										String str_status = item_rev.getProperty("release_status_list");
										//�ж������Ƿ񷢲�
										if("".equals(str_status) || str_status==null){
											flag_f_sffb = true;
											if(!vec_f_sffb.contains(erro_f_id)){
												vec_f_sffb.add(erro_f_id);
											}
										}
										//�ж�ѡ��BOM�����ǲ��������˹���·��
										TCComponent[] forms = item_rev.getRelatedComponents(relation2);
										if(forms==null || forms.length==0){
											flag_f_gylx = true;
											if(!vec_f_gylx.contains(erro_f_id)){
												vec_f_gylx.add(erro_f_id);
											}
										}else{
											for (int k = 0; k < forms.length; k++) {
												String type = forms[k].getType().toString();
												if(!vec_f_form_type.contains(type)){
													vec_f_form_type.add(type);
												}
											}
											if(!vec_f_form_type.contains(gylxform_type)){
												flag_f_gylx1 = true;
												if(!vec_f_gylx1.contains(erro_f_id)){
													vec_f_gylx1.add(erro_f_id);
												}
											}
										}
									}
								}else{
									//��ʶѡ����ļ������ǲ��Ƕ��������϶���
									flag_f_sfdd1 = true;
								}
							}
						}else{
							//��ʶѡ����ļ������ǲ����ж���������
							flag_f_hydd = true;
						}
					}
				}
				if(flag_lx){
					progressbar.stopBar();
					MessageBox.post("��ѡ�񶩵������϶�������ļ��ж���!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ��Ķ����ܼ����ļ��ж�������item����
				if(flag_2 && flag_3){
					progressbar.stopBar();
					MessageBox.post("��ѡ�񶩵������϶�������ļ��ж���!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				if(flag_f_hydd){
					progressbar.stopBar();
					MessageBox.post("��ѡ����ļ�������û�ж��������϶���!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ��Ķ����ǲ��Ƕ��������϶���
				if(flag_sfdd){
					progressbar.stopBar();
					MessageBox.post("��ѡ�����"+ vec_sfdd +"���Ƕ��������϶���,������ѡ��!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ���ļ��������ǲ��Ƕ���������
				if(flag_f_sfdd){
					progressbar.stopBar();
					MessageBox.post("��ѡ���ļ��ж�����"+ vec_f_sfdd +"���Ƕ��������϶���,������ѡ��!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ���ļ��������ǲ��Ƕ���������
				if(flag_f_sfdd1){
					progressbar.stopBar();
					MessageBox.post("��ѡ���ļ��ж����²��Ƕ��������϶���,������ѡ��!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�ж�ѡ��Ķ���������������û��BOMVIEW
				if(flag_view){
					progressbar.stopBar();
					MessageBox.post("��ѡ�����"+ vec_view +"û��BOM�ṹ,������ѡ��!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ��Ķ��������������BOMVIEW�ǲ��ǿյ�BOMVIEW
				if(flag_haveview){
					progressbar.stopBar();
					MessageBox.post("��ѡ�����"+ vec_haveview +"BOM�ṹΪ��,������ѡ��!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�ж�ѡ���BOM����ͼ�����ǲ���S4MZ0
				if(flag_vtp_cd){
					progressbar.stopBar();
					MessageBox.post("��ѡ���"+vec_vtp_cd+"����MZ0��֯,��ѡ��MZ0��֯��BOM���д���!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�ж�ѡ��Ķ����������ǲ��Ƿ���
				if(flag_sffb){
					progressbar.stopBar();
					MessageBox.post("��ѡ�����"+ vec_sffb +"û�з���,������ѡ��!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ���ļ����µĶ���������������û��BOMVIEW
				if(flag_f_view){
					progressbar.stopBar();
					MessageBox.post("��ѡ���ļ��ж�����"+ vec_f_view +"û��BOM�ṹ,������ѡ��!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ���ļ����µĶ��������������BOMVIEW�ǲ��ǿյ�BOMVIEW
				if(flag_f_haveview){
					progressbar.stopBar();
					MessageBox.post("��ѡ���ļ��ж�����"+ vec_f_haveview +"BOM�ṹΪ��,������ѡ��!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�ж�ѡ���ļ����µ�BOM����ͼ�����ǲ���S4MZ0
				if(flag_f_vtp_cd){
					progressbar.stopBar();
					MessageBox.post("��ѡ���"+vec_f_vtp_cd+"����MZ0��֯,��ѡ��MZ0��֯��BOM���д���!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�ж�ѡ���ļ����µĶ����������ǲ��Ƿ���
				if(flag_f_sffb){
					progressbar.stopBar();
					MessageBox.post("��ѡ���ļ��ж�����"+ vec_f_sffb +"û�з���,������ѡ��!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�ж�ѡ���BOM�ǲ����Ѿ����ù�����·��
				if(flag_gylx){
					progressbar.stopBar();
					MessageBox.post("��ѡ���"+vec_gylx+"û�����ù���·��,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ���BOM�ǲ����Ѿ����ù�����·��
				if(flag_gylx1){
					progressbar.stopBar();
					MessageBox.post("��ѡ���"+vec_gylx1+"û�����ù���·��,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�ж�ѡ���ļ����µ�BOM�ǲ����Ѿ����ù�����·��
				if(flag_f_gylx){
					progressbar.stopBar();
					MessageBox.post("��ѡ���"+vec_f_gylx+"û�����ù���·��,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ���ļ����µ�BOM�ǲ����Ѿ����ù�����·��
				if(flag_f_gylx1){
					progressbar.stopBar();
					MessageBox.post("��ѡ���"+vec_f_gylx1+"û�����ù���·��,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�����������������,�����BOM����ǰ�ļ�鹤��,��BOM���ݼ���߼�һ��
				checkSendBOMInfo();
				
				//�жϹ���·�ߺ�BOM�ǲ��Ƕ��Ѿ�����
				if(flag_gylx_cd && flag_desc_cd){
					progressbar.stopBar();
					MessageBox.post("��ѡ���"+vec_gylx_cd+"BOM�Ѿ��ɹ�����,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�ж�ѡ���ļ����µĹ���·�ߺ�BOM�ǲ��Ƕ��Ѿ�����
				if(flag_f_gylx_cd && flag_f_desc_cd){
					progressbar.stopBar();
					MessageBox.post("��ѡ���"+vec_f_gylx_cd+"BOM�Ѿ��ɹ�����,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�ж�ѡ���BOM�ǲ����Ѿ����ݹ�ERP
				if(flag_desc_cd){
					progressbar.stopBar();
					MessageBox.post("��ѡ���BOM"+vec_desc_cd+"�Ѿ����ݹ�ERP,�������ٴδ���,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ���BOM����������ǲ��������Ѿ����ݹ�ERP
				if(flag2){
					progressbar.stopBar();
					MessageBox.post(vec_2+"��Щ������ERP��û�д���,���ȴ��ݸ����Ϻ��ٴ���BOM!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ���BOM������BOM(��BOM�ṹ)���ǲ����Ѿ����ݹ�ERP
				if(flag1){
					progressbar.stopBar();
					MessageBox.post("���Ƚ�"+vec_1+"��BOM������ERP���ٳ��Դ��ݸ�BOM!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�ж�ѡ���ļ����µ�BOM�ǲ����Ѿ����ݹ�ERP
				if(flag_f_desc_cd){
					progressbar.stopBar();
					MessageBox.post("��ѡ���BOM"+vec_f_desc_cd+"�Ѿ����ݹ�ERP,�������ٴδ���,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ���ļ����µ�BOM����������ǲ��������Ѿ����ݹ�ERP
				if(flag_f_2){
					progressbar.stopBar();
					MessageBox.post(vec_f_2+"��Щ������ERP��û�д��������ȴ��ݸ����Ϻ��ٴ���BOM!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ���ļ����µ�BOM������BOM(��BOM�ṹ)���ǲ����Ѿ����ݹ�ERP
				if(flag_f_1){
					progressbar.stopBar();
					MessageBox.post("���Ƚ�"+vec_f_1+"��BOM������ERP���ٳ��Դ��ݸ�BOM!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				flag6 = isInOrganization(vec_ID,"MZ0");
				if(flag6){
					progressbar.stopBar();
					MessageBox.post(vec_error_ID+"���ڵ���֯��ERP��û�з���,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				
				//�ж�ѡ���BOM������ӽṹ�ϵı�������
				if(flag_bt){
					progressbar.stopBar();
					MessageBox.post("ѡ���"+vec_bt+"����������Ҫά�����ܴ���,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ���BOM������ӽṹ�ϵı�������
				if(flag_f_bt){
					progressbar.stopBar();
					MessageBox.post("ѡ���ļ����µ�"+vec_f_bt+"����������Ҫά�����ܴ���,����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				//�ж�ѡ�񶩵������ϵĹ���·�����м�����ǲ����Ѿ�����
				if(flag4){
					progressbar.stopBar();
					MessageBox.post(vec_4+"BOM�Ѿ������м��,�ȴ�ERP����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�ж�ѡ��Ķ������������м�����ǲ����Ѿ�����
				if(flag3){
					progressbar.stopBar();
					MessageBox.post(vec_3+"BOM�Ѿ������м��,�ȴ�ERP����!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				
				//�����������������,�򽫹���·�ߴ��ݵ�ERP
				for (int i = 0; i < targets.length; i++) {
					if(targets[i] instanceof TCComponentItem || targets[i] instanceof TCComponentItemRevision){
						TCComponentItem item = null;
						TCComponentItemRevision item_rev =null;
						if(targets[i] instanceof TCComponentItem){
							item = (TCComponentItem) targets[i];
							item_rev = item.getLatestItemRevision();
						}else if(targets[i] instanceof TCComponentItemRevision){
							item_rev = (TCComponentItemRevision) targets[i];
							item = item_rev.getItem();
						}
						System.out.println("==���ݹ���·��==ѡ������==");
						String sel_id = item.getProperty("item_id").toString();
						System.out.println("sel_id----------ѡ������----------->:"+sel_id);
						TCComponentForm gylx_form = null;
						//�õ�����·�����õ�FORM
						TCComponent[] forms = item_rev.getRelatedComponents(relation2);
						for (int j = 0; j < forms.length; j++) {
							if(gylxform_type.equals(forms[j].getType().toString())){
								gylx_form = (TCComponentForm) forms[j];
							}
						}
						if(gylx_form!=null){
							String tranlogo = gylx_form.getProperty(attri1).toString();
							if(tranlogo.equals("Y")){
								continue;
							}
						}
						//������·�����ݲ��뵽�м��
						insertGYLXDataToTable(sel_id,gylx_form);
					}else if(targets[i] instanceof TCComponentFolder){
						TCComponentFolder folder = (TCComponentFolder) targets[i];
						System.out.println("==================���ݹ���·��==ѡ���ļ���=================");
						TCComponent[] coms = folder.getRelatedComponents(relation3);
						for (int j = 0; j < coms.length; j++) {
							TCComponentItem item = (TCComponentItem) coms[j];
							String sel_id = item.getProperty("item_id").toString();
							System.out.println("sel_id===========ѡ���ļ���============>:"+sel_id);
							TCComponentItemRevision item_rev = item.getLatestItemRevision();
							TCComponentForm gylx_form = null;
							//�õ�����·�����õ�FORM
							TCComponent[] forms = item_rev.getRelatedComponents(relation2);
							for (int k = 0; k < forms.length; k++) {
								if(gylxform_type.equals(forms[k].getType().toString())){
									gylx_form = (TCComponentForm) forms[k];
								}
							}
							System.out.println("gylx_form------����·��form-------------->:"+gylx_form);
							if(gylx_form!=null){
								String tranlogo = gylx_form.getProperty(attri1).toString();
								if(tranlogo.equals("Y")){
									continue;
								}
							}
							//������·�����ݲ��뵽�м��
							insertGYLXDataToTable(sel_id,gylx_form);
						}
					}
				}
				
				//�����������������,��BOM���ݵ�ERP
				for (int i = 0; i < targets.length; i++) {
					if(targets[i] instanceof TCComponentItem || targets[i] instanceof TCComponentItemRevision){
						TCComponentItem item = null;
						TCComponentItemRevision item_rev =null;
						if(targets[i] instanceof TCComponentItem){
							item = (TCComponentItem) targets[i];
							item_rev = item.getLatestItemRevision();
						}else if(targets[i] instanceof TCComponentItemRevision){
							item_rev = (TCComponentItemRevision) targets[i];
							item = item_rev.getItem();
						}
						String sel_id = item.getProperty("item_id").toString();
						//ת��BOMLINE
						TCComponentBOMLine sel_bomline = getProBomLine(item_rev);
						AIFComponentContext[] boms=sel_bomline.getChildren();
						//��BOM���ݲ��뵽�м��
						insertDataToTable(sel_id,boms);
					}else if(targets[i] instanceof TCComponentFolder){
						TCComponentFolder folder = (TCComponentFolder) targets[i];
						TCComponent[] coms = folder.getRelatedComponents(relation3);
						for (int j = 0; j < coms.length; j++) {
							TCComponentItem item = (TCComponentItem) coms[j];
							String sel_id = item.getProperty("item_id").toString();
							TCComponentItemRevision item_rev = item.getLatestItemRevision();
							//ת��BOMLINE
							TCComponentBOMLine sel_bomline = getProBomLine(item_rev);
							AIFComponentContext[] boms=sel_bomline.getChildren();
							//��BOM���ݲ��뵽�м��
							insertDataToTable(sel_id,boms);
						}
					}
				}
				progressbar.stopBar();
				if(!flag_cdgycg && !flag_cdbomcg){
					MessageBox.post("���ݳɹ���ERP�м��,�ȴ�ERP����!", "��ʾ", MessageBox.INFORMATION);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			MessageBox.post("��ѡ����ȷ�������Ͳ���!", "��ʾ", MessageBox.INFORMATION);
			return;
		}
	}
	
	//�����������������,�����BOM����ǰ�ļ�鹤��,��BOM���ݼ���߼�һ��
	public void checkSendBOMInfo(){
		try {
			for (int i = 0; i < targets.length; i++) {
				if(targets[i] instanceof TCComponentItem || targets[i] instanceof TCComponentItemRevision){
					TCComponentItem item = null;
					TCComponentItemRevision item_rev =null;
					if(targets[i] instanceof TCComponentItem){
						System.out.println("DDDDDDDDDDDDDDDDD");
						item = (TCComponentItem) targets[i];
						item_rev = item.getLatestItemRevision();
						System.out.println("item_rev----item---->:"+item_rev);
						//BOM����ǰ�ļ�鹤������鹤��·���ǲ��Ǵ��ݹ�
						checkSelectBOMData(item_rev);
					}else if(targets[i] instanceof TCComponentItemRevision){
						System.out.println("FFFFFFFFFFFFFFFFFFFFFF");
						item_rev = (TCComponentItemRevision) targets[i];
						System.out.println("item_rev======================>:"+item_rev);
						item = item_rev.getItem();
						//BOM����ǰ�ļ�鹤������鹤��·���ǲ��Ǵ��ݹ�
						checkSelectBOMData(item_rev);
					}
					
					String sel_id = item.getProperty("item_id").toString();
					//�ж�ѡ�����ϵ���ԭ�����ǲ����Ѿ�����
					flag4 = checkSelGYLXTableData(sel_id);
					//�ж�ѡ���������ԭ�����ǲ����Ѿ�����
					flag3 = checkSelTableData(sel_id);
				}else if(targets[i] instanceof TCComponentFolder){
					TCComponentFolder folder = (TCComponentFolder) targets[i];
					TCComponent[] coms = folder.getRelatedComponents(relation3);
					for (int j = 0; j < coms.length; j++) {
						TCComponentItem item = (TCComponentItem) coms[j];
						String sel_id = item.getProperty("item_id").toString();
						//BOM����ǰ�ļ�鹤��
						checkFolderBOMData(item);
						//�ж�ѡ�����ϵ���ԭ�����ǲ����Ѿ�����
						flag4 = checkSelGYLXTableData(sel_id);
						//�ж�ѡ���������ԭ�����ǲ����Ѿ�����
						flag3 = checkSelTableData(sel_id);
					}
				}
			}
		} catch (TCException e) {
			e.printStackTrace();
		}
	}
	
	
	//����BOM����ǰ�ļ�鹤��,��BOM���ݼ���߼�һ��(���ѡ��Ķ��������� )
	public void checkSelectBOMData(TCComponentItemRevision item_rev){
		try {
			TCComponentForm gylx_form = null;
			String sel_bom_id = item_rev.getItem().getProperty("item_id").toString();
			if(!vec_ID.contains(sel_bom_id)){
				vec_ID.add(sel_bom_id);
			}
//			String sel_bom_name = item.getProperty("object_name").toString();
//			String error_info = sel_bom_id +"-" + sel_bom_name;
			System.out.println("item_rev[=======>:"+item_rev);
			//�ж�ѡ��BOM�����ǲ��������˹���·��
			TCComponent[] forms = item_rev.getRelatedComponents(relation2);
			for (int i = 0; i < forms.length; i++) {
				String type = forms[i].getType().toString();
				if(gylxform_type.equals(type)){
					gylx_form = (TCComponentForm) forms[i];
				}
			}
			if(gylx_form!=null){
				String tranlogo = gylx_form.getProperty(attri1).toString();
				if(tranlogo.equals("Y")){
					flag_gylx_cd = true;
				}
			}
			TCComponentBOMViewRevision sel_bomview = (TCComponentBOMViewRevision) item_rev.getRelatedComponent(relation1);
			if(sel_bomview!=null){
				String sel_desc = sel_bomview.getProperty("object_desc").toString();
				System.out.println("rev====----*sel_desc====>:"+sel_desc);
				if("Y".equals(sel_desc)){
					flag_desc_cd = true;
					if(!vec_desc_cd.contains(sel_bom_id)){
						vec_desc_cd.add(sel_bom_id);
					}
				}
			}
			if(flag_gylx_cd && flag_desc_cd){
				if(!vec_gylx_cd.contains(sel_bom_id)){
					vec_gylx_cd.add(sel_bom_id);
				}
			}
			/*TCComponentForm form = (TCComponentForm) item_rev.getRelatedComponent(relation);
			String passing_state = form.getProperty(attri).toString();
			//�ж�ѡ���BOM����������û�д�����ERP��
			if(!"Y".equals(passing_state)){
				flag2 = true;
				if(!vec_2.contains(error_info)){
					vec_2.add(error_info);
				}
			}*/
			//ת��BOMLINE
			TCComponentBOMLine sel_bomline = getProBomLine(item_rev);
			AIFComponentContext[] childs = sel_bomline.getChildren();
			for (int j = 0; j < childs.length; j++) {
				TCComponentBOMLine sub_bomline = (TCComponentBOMLine) childs[j].getComponent();
				String[] values = new String[12];
				values = sub_bomline.getProperties(properties);
				System.out.println("values===0===>:"+values[0]);
				System.out.println("values===1===>:"+values[1]);
				System.out.println("values===2===>:"+values[2]);
				//�ж�ѡ���BOM����BOM�ϵı�������
				if(values[2]==null || "".equals(values[2])){
					flag_bt = true;
					if(!vec_bt.contains(sel_bom_id)){
						vec_bt.add(sel_bom_id);
					}
				}
				//�õ���BOM
				TCComponentItem sub_bom = sub_bomline.getItem();
				System.out.println("sub_bom---->:"+sub_bom);
				String sub_bom_id = sub_bom.getProperty("item_id").toString();
				if(!vec_ID.contains(sub_bom_id)){
					vec_ID.add(sub_bom_id);
				}
				String sub_bom_name = sub_bom.getProperty("object_name").toString();
				String sub_error_info = sub_bom_id +"-" + sub_bom_name;
				//�õ�BOM���°汾
				TCComponentItemRevision sub_rev = sub_bom.getLatestItemRevision();
				System.out.println("sub_rev===>;"+sub_rev);
				TCComponentForm sub_form = (TCComponentForm) sub_rev.getRelatedComponent(relation);
				System.out.println("sub_bom=---===>:"+sub_bom);
				String sub_passing_state = sub_form.getProperty(attri).toString();
				//�ж���BOM��û�д�����ERP��
				if(!"Y".equals(sub_passing_state)){
					flag2 = true;
					if(!vec_2.contains(sub_error_info)){
						vec_2.add(sub_error_info);
					}
				}
				//�õ�BOM���°汾��BOMVIEW
				TCComponentBOMViewRevision sub_bomview = (TCComponentBOMViewRevision) sub_rev.getRelatedComponent(relation1);
				System.out.println("rev====----*****===sub_bomview====>:"+sub_bomview);
				//���BOMVIEW��Ϊ��,�ж�������ֵ
				if(sub_bomview!=null){
					String desc = sub_bomview.getProperty("object_desc").toString();
					System.out.println("rev====----*desc====>:"+desc);
					if("".equals(desc) || error_cd.equals(desc)){
						flag1 = true;
						if(!vec_1.contains(sub_bom_id)){
							vec_1.add(sub_bom_id);
						}
					}
				}
			}
		} catch (TCException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//����BOM����ǰ�ļ�鹤��,��BOM���ݼ���߼�һ��(���ѡ���ļ����µĶ���������)
	public void checkFolderBOMData(TCComponentItem item){
		try {
			TCComponentForm gylx_form = null;
			String sel_bom_id = item.getProperty("item_id").toString();
			if(!vec_ID.contains(sel_bom_id)){
				vec_ID.add(sel_bom_id);
			}
//			String sel_bom_name = item.getProperty("object_name").toString();
//			String error_info = sel_bom_id +"-" + sel_bom_name;
			System.out.println("sel_bom===>:"+item);
			TCComponentItemRevision item_rev = item.getLatestItemRevision();
			//�ж�ѡ��BOM�����ǲ��������˹���·��
			TCComponent[] forms = item_rev.getRelatedComponents(relation2);
			for (int i = 0; i < forms.length; i++) {
				String type = forms[i].getType().toString();
				if(gylxform_type.equals(type)){
					gylx_form = (TCComponentForm) forms[i];
				}
			}
			if(gylx_form!=null){
				String tranlogo = gylx_form.getProperty(attri1).toString();
				if(tranlogo.equals("Y")){
					flag_f_gylx_cd = true;
				}
			}
			
			
			TCComponentBOMViewRevision sel_bomview = (TCComponentBOMViewRevision) item_rev.getRelatedComponent(relation1);
			System.out.println("====sel_bomview====>:"+sel_bomview);
			if(sel_bomview!=null){
				String sel_desc = sel_bomview.getProperty("object_desc").toString();
				System.out.println("rev====----*sel_desc====>:"+sel_desc);
				if("Y".equals(sel_desc)){
					flag_f_desc_cd = true;
					if(!vec_f_desc_cd.contains(sel_bom_id)){
						vec_f_desc_cd.add(sel_bom_id);
					}
				}
			}
			if(flag_f_gylx_cd && flag_f_desc_cd){
				if(!vec_f_gylx_cd.contains(sel_bom_id)){
					vec_f_gylx_cd.add(sel_bom_id);
				}
			}
			
			/*TCComponentForm form = (TCComponentForm) item_rev.getRelatedComponent(relation);
			System.out.println("form===>:"+form);
			String passing_state = form.getProperty(attri).toString();
			//�ж�ѡ���BOM����������û�д�����ERP��
			if(!"Y".equals(passing_state)){
				flag_f_2 = true;
				if(!vec_f_2.contains(error_info)){
					vec_f_2.add(error_info);
				}
			}*/
			//ת��BOMLINE
			TCComponentBOMLine sel_bomline = getProBomLine(item_rev);
			AIFComponentContext[] childs = sel_bomline.getChildren();
			for (int j = 0; j < childs.length; j++) {
				TCComponentBOMLine sub_bomline = (TCComponentBOMLine) childs[j].getComponent();
				System.out.println("sub_bomline---->:"+sub_bomline);
				String[] values = new String[12];
				values = sub_bomline.getProperties(properties);
				System.out.println("values===0===>:"+values[0]);
				System.out.println("values===1===>:"+values[1]);
				System.out.println("values===2===>:"+values[2]);
				//�ж�ѡ���BOM����BOM�ϵı�������
				if(values[2]==null || "".equals(values[2])){
					flag_f_bt = true;
					if(!vec_f_bt.contains(sel_bom_id)){
						vec_f_bt.add(sel_bom_id);
					}
				}
				//�õ���BOM
				TCComponentItem sub_bom = sub_bomline.getItem();
				System.out.println("sub_bom---->:"+sub_bom);
				String sub_bom_id = sub_bom.getProperty("item_id").toString();
				if(!vec_ID.contains(sub_bom_id)){
					vec_ID.add(sub_bom_id);
				}
				String sub_bom_name = sub_bom.getProperty("object_name").toString();
				String sub_error_info = sub_bom_id +"-" + sub_bom_name;
				//�õ�BOM���°汾
				TCComponentItemRevision sub_rev = sub_bom.getLatestItemRevision();
				System.out.println("sub_rev===>;"+sub_rev);
				TCComponentForm sub_form = (TCComponentForm) sub_rev.getRelatedComponent(relation);
				System.out.println("sub_bom=---===>:"+sub_bom);
				String sub_passing_state = sub_form.getProperty(attri).toString();
				//�ж���BOM��û�д�����ERP��
				if(!"Y".equals(sub_passing_state)){
					flag_f_2 = true;
					if(!vec_f_2.contains(sub_error_info)){
						vec_f_2.add(sub_error_info);
					}
				}
				//�õ�BOM���°汾��BOMVIEW
				TCComponentBOMViewRevision sub_bomview = (TCComponentBOMViewRevision) sub_rev.getRelatedComponent(relation1);
				System.out.println("rev====----*****===sub_bomview====>:"+sub_bomview);
				//���BOMVIEW��Ϊ��,�ж�������ֵ
				if(sub_bomview!=null){
					String desc = sub_bomview.getProperty("object_desc").toString();
					System.out.println("rev====----*desc====>:"+desc);
					if("".equals(desc) || error_cd.equals(desc)){
						flag_f_1 = true;
						if(!vec_f_1.contains(sub_bom_id)){
							vec_f_1.add(sub_bom_id);
						}
					}
				}
			}
		} catch (TCException e) {
			e.printStackTrace();
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
	
	//���ѡ��BOM�Ĺ���·�����м�����ǲ����Ѿ�����
	public boolean checkSelGYLXTableData(String sel_id){
		boolean flag_check = false;
		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			String sql_gylx_String = "select * from CUX.CUX_PLM_ROUTING_IFACE where ORGANIZATION_CODE='"
				+ "MZ0" +"' and ITEM_NUMBER='" + sel_id +"'";
			System.out.println("����ѯ��� ��" + sql_gylx_String);
			stmt = conn.createStatement();
			reset = stmt.executeQuery(sql_gylx_String);
			if(reset!=null && reset.next()){
				flag_check = true;
				if(!vec_4.contains(sel_id)){
					vec_4.add(sel_id);
				}
			}
			stmt.close();
			oraconn.closeConn(conn);
			oraconn.closeConn(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag_check;
		
	}
	
	//���ѡ���BOM���м�����ǲ����Ѿ�����
	public boolean checkSelTableData(String sel_id){
		boolean flag_check = false;
		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			//�����ж�ѡ���������ԭ�����ǲ����Ѿ�����
			conn = oraconn.getConnection();
			//sql��ѯ���
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
				if(!vec_3.contains(sel_id)){
					vec_3.add(sel_id);
				}
			}
			oraconn.closeConn(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag_check;
		
	}
	
	//������·�����ݲ��뵽�м��
	public void insertGYLXDataToTable(String sel_id,TCComponentForm form){
		try {
			String formName = "";
			String zhiKuCun = "";
			String huoWei = "";
			String cdhuowei = "";
			formProperties = form.getFormTCProperties(propertyNames);
			int propertySize = formProperties.length;
			for (int i = 0; i < propertySize; i++) {
				String propertyName = formProperties[i] == null ? "" : formProperties[i].getPropertyName();
				if (propertyName.equals("s4opernumber")) {
					Opernumber_array = formProperties[i].getStringValueArray();
				} else if (propertyName.equals("s4operationcode")) {
					Operationcode_array = formProperties[i].getStringValueArray();
				} else if (propertyName.equals("s4department")) {
					Department_array = formProperties[i].getStringValueArray();
				} else if (propertyName.equals("s4texture")) {
					Texture_array = formProperties[i].getStringValueArray();
				} else if (propertyName.equals("object_name")) {
					formName = formProperties[i].getStringValue();
				} else if (propertyName.equals("s4Childstock")) {
					zhiKuCun = formProperties[i].getStringValue();
				} else if (propertyName.equals("s4cargo_s")) {
					huoWei = formProperties[i].getStringValue();
				} 
			}
			if(huoWei==null || "".equals(huoWei)){
				cdhuowei = huoWei;
			}else{
				cdhuowei = huoWei+ ".";
			}
			System.out.println("cdhuowei===>:"+cdhuowei);
			OracleConnect oraconn = new OracleConnect();
			// ʵ�������ݿ�����
			conn = oraconn.getConnection();
			for (int i = 0; i < Opernumber_array.length; i++) {
				String sql = "insert into CUX.CUX_PLM_ROUTING_IFACE(IFACE_ID,OPERATION_SEQ_NUM,STANDARD_OPERATION_CODE,DEPARTMENT_CODE," +
				"ORGANIZATION_CODE,COMPLETION_SUBINVENTORY,COMPLETION_LOCATOR,ITEM_NUMBER,FORM_NAME," +
				"Creation_Date,Created_By,Last_Updated_By,Last_Update_Date,Last_Update_Login,Batch_Id) values ("
				+"CUX.CUX_PLM_ROUTING_IFACE_s.Nextval" + ",'" + Opernumber_array[i] + "','" + Operationcode_array[i] + "','" + Department_array[i] + "','" + Texture_array[i] + 
				"','" + zhiKuCun + "','" + cdhuowei + 
				"','" + sel_id + "','" + formName +"',SYSDATE,'" + userName+ "','-1',SYSDATE,'-1','-1' )";
				System.out.println("sql ===����·��====> "+sql);
				stmt = conn.createStatement();
				int j = 0;
				try {
					j = stmt.executeUpdate(sql);
				} catch (Exception e) {
					flag_cdgycg = true;
					e.printStackTrace();
					progressbar.stopBar();
					MessageBox.post(sel_id+"���ݴ���,������ϢΪ:"+e.getMessage(),"����",MessageBox.ERROR);
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

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TCException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//��BOM���ݲ��뵽�м��
	public void insertDataToTable(String sel_id,AIFComponentContext[] boms){
		try {
			// ʵ�������ݿ�����
			OracleConnect oraconn = new OracleConnect();
			conn = oraconn.getConnection();
			int k = boms.length;
			for (int i = 0; i < k; i++) {
				System.out.println("sel_id-------bom->:"+sel_id);
				TCComponentBOMLine sub_bom = (TCComponentBOMLine) boms[i].getComponent();
				//�õ�BOM�ĺ������°汾
				TCComponentItemRevision sub_rev = sub_bom.getItem().getLatestItemRevision();
				//�õ�BOM���°汾��BOMVIEW
				TCComponentBOMViewRevision bomview = (TCComponentBOMViewRevision) sub_rev.getRelatedComponent(relation1);
				//���BOMVIEW��Ϊ��,�ж�������ֵ,���Ϊ��,���BOM������
				if(bomview!=null){
					String desc = bomview.getProperty("object_desc").toString();
					System.out.println("rev====----*desc==bom==>:"+desc);
					if("Y".equals(desc)){
						continue;
					}
				}
				String sub_bom_id = sub_bom.getProperty("bl_item_item_id");
				System.out.println("sub_bom_id-------bom->:"+sub_bom_id);
				String[] values = new String[12];
				values = sub_bom.getProperties(properties);
				System.out.println("values====00==>:"+values[0]);
				//���뵽ERP�����ϴ��ݵı�
				String sql = "insert into CUX.CUX_PLM_BOM_IFACE(IFACE_ID,ENG_ITEM_FLAG,ORGANIZATION_CODE,ITEM_NUM,ITEM_SEQUENCE,OPERATION_SEQ_NUM,COMPONENT_ITEM,COMPONENT_QUANTITY" +
						",COMPONENT_NUM1,COMPONENT_NUM2,COMPONENT_NUM3,VENDOR_NAME,SALES_DESCRIPTION,ABB_COMPONENT_NUM,SUPPLY_TYPE,SUPPLY_SUBINVENTORY,COMPONENT_REMARKS,CREATE_BY" +
						",CREATION_DATE,BATCH_ID) values ("
					+"CUX.CUX_PLM_BOM_IFACE_s.nextval" + ",'" + yxbom + "','" + zzdm + "','" + sel_id + "','" + values[0] +  "','10','" + sub_bom_id + "','" + values[2] + "','" + values[3] + "','" 
					+ values[4] + "','" + values[5] + "','" + values[6] + "','" + values[7] + "','" + values[8] + "','" + values[9] + "','" + values[10] + "','" + values[11] +"','"+userName+"',"+"SYSDATE" + ",'-1'" + ")";
				System.out.println("sql----bomgc->"+sql);
				stmt = conn.createStatement();
				int j = 0;
				try {
					j = stmt.executeUpdate(sql);
				} catch (Exception e) {
					flag_cdbomcg = true;
					e.printStackTrace();
					progressbar.stopBar();
					MessageBox.post(sel_id+"���ݴ���,������ϢΪ:"+e.getMessage(),"����",MessageBox.ERROR);
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
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TCException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//ͨ������һ������汾,�õ�����BOMLINE
	public TCComponentBOMLine getProBomLine(TCComponentItemRevision itemRev) {
		TCComponentBOMLine bomLine = null;
		try {
			TCComponentBOMWindowType bomWinType = (TCComponentBOMWindowType) session
					.getTypeComponent("BOMWindow");
			TCComponentBOMWindow bomWin = bomWinType.create(null);
			bomWin.lock();
			bomLine = bomWin.setWindowTopLine(itemRev.getItem(), itemRev, null,
					null);
			bomWin.unlock();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bomLine;
	}
	
}
