package com.origin.rac.sac.createdateset;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.com.origin.util.PublicMethod;
import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentDataset;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentTcFile;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.kernel.TCTextService;
import com.teamcenter.rac.util.MessageBox;

public class SJGGDatesetCreateCommand extends AbstractAIFCommand {

	private String itemType ="S4SJGG";
	private TCSession session = null;
	private String releation = "S4Affix_C";
//	private String[] str_names = {"ģ��1","ģ��2","ģ��3"};
	private String[] str_names = null;
	private List all_dateset = new ArrayList();
	
	
	public SJGGDatesetCreateCommand(AbstractAIFUIApplication app) {
		session = (TCSession) app.getSession();
		TCComponent target = (TCComponent) app.getTargetComponent();
		if (target == null || !(target instanceof TCComponentItem)) {
			MessageBox.post("��ѡ����Ƹ��ĵ����������!", "��ʾ", MessageBox.INFORMATION);
			return;
		}else{
			try {
				str_names = session.getPreferenceService().getStringArray(4, "SAC_MSWord_Template_Name");
				if(str_names == null || str_names.length == 0){
					MessageBox.post("δ������ѡ�SAC_MSWord_Template_Name��ֵ", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				for (int i = 0; i < str_names.length; i++) {
					
					System.out.println("str_names--->:"+str_names[i]);
				}
				TCComponentItem item = (TCComponentItem) target;
				//�ж�ѡ��������ǲ�����Ƹ��������
				if(itemType.equals(item.getType().toString())){
					TCComponentItemRevision latest_rev = item.getLatestItemRevision();
					String str = latest_rev.getProperty("release_status_list");
					if("".equals(str)){
						TCComponent[] dasets = latest_rev.getRelatedComponents(releation);
						if(dasets!=null && dasets.length>0){
							MessageBox.post("���ĸ����Ѿ��������ݼ�,���ܴ���,����!", "��ʾ", MessageBox.INFORMATION);
							return;
						}else{
							// �������ݼ�ģ��
							boolean flag = false;
							for (int i = 0; i < str_names.length; i++) {
								InterfaceAIFComponent[] datesets = queryDateset(str_names[i]);
								if(datesets==null || datesets.length==0){
									flag = true;
									break;
								}
							}
							if(flag){
								MessageBox.post("����infodba�´������ݼ�ģ��!", "��ʾ", MessageBox.INFORMATION);
								return;
							}else{
								//�������ݼ���ָ����ϵ��
								for (int i = 0; i < str_names.length; i++) {
									InterfaceAIFComponent[] datesets = queryDateset(str_names[i]);
									for (int j = 0; j < datesets.length; j++) {
										TCComponentDataset daset = (TCComponentDataset) datesets[j];
										String name = daset.getProperty("object_name");
										TCComponentDataset daset1 = daset.saveAs(name);
										all_dateset.add(daset1);
									}
								}
								System.out.println("all_dateset--->:"+all_dateset);
								latest_rev.add(releation, all_dateset);
								MessageBox.post("��Ƹ��ĵ����������ɹ�!", "��ʾ", MessageBox.INFORMATION);
							}
						}
					}else{
						MessageBox.post("���°汾�Ѿ��������������ɸ��ĸ���!", "��ʾ", MessageBox.INFORMATION);
						return;
					}
				}else{
					MessageBox.post("��ѡ����Ƹ��ĵ����������!", "��ʾ", MessageBox.INFORMATION);
					return;
				}
			} catch (TCException e) {
				e.printStackTrace();
			}
			
			
			
		}
	}
	
	//����ϵͳ����� ID��ѯ
	public InterfaceAIFComponent[] queryDateset(String name){
		InterfaceAIFComponent[] items = null;
		try {
			TCTextService tcService =session.getTextService();
			String askKey[]={tcService.getTextValue("Name"),tcService.getTextValue("OwningUser")};
			String askValue[]={name,"infodba"};
			items =  session.search("���ݼ�...", askKey, askValue);
		} catch (TCException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}
	
}
