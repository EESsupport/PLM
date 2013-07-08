package com.teamcenter.rac.sac.projectinfo;

import java.util.HashMap;

import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.kernel.TCTextService;
import com.teamcenter.rac.util.MessageBox;

public class CreateProjectInfoCommand extends AbstractAIFCommand {

	private HashMap<String,String> map = new HashMap<String,String>();
	private TCSession session = null;
//	private String[] templates =null;
	private TCComponentItem item= null;
	private String relation ="IMAN_master_form";
	private String item_type1="S4YFprojXJ";
	private String muban = "ģ��";
	private String xmmuban = "��Ŀ�ĵ�";
	private String item_name = "";
	private TCComponentFolder templateObject;
	private String[] templateTypes = null;
	private String templateType = "";
	private String owner = "";
	private String tempalte_name = "";
	
	public CreateProjectInfoCommand(AbstractAIFUIApplication app) {
		TCComponent target = (TCComponent) app.getTargetComponent();
		session = (TCSession) app.getSession();
		if (target == null) {
			MessageBox.post("��ѡ��ָ�����Ͷ���", "��ʾ", MessageBox.INFORMATION);
			return;
		}
		if (!(target instanceof TCComponentItem)) {
			MessageBox.post("��ѡ��ָ�����Ͷ���", "��ʾ", MessageBox.INFORMATION);
			return;
		}
		try {
			TCSession session = (TCSession) app.getSession();
			String[] itemTypes = session.getPreferenceService().getStringArray(4, "SAC_Folder_Template_Name");
			if(itemTypes != null && itemTypes.length > 0){
				item = (TCComponentItem) target;
				boolean flag = false;
				for (int i = 0; i < itemTypes.length; i++) {
					System.out.println("itemTypes[i]--->:"+itemTypes[i]);
					String[] str = itemTypes[i].split("=");
					map.put(str[0], str[1]);
					if(item.getType().equals(str[0])){
						flag = true;
					}
				}
				owner = session.getPreferenceService().getString(4, "SAC_Folder_Template_Owner");
				if (owner == null || "".equals(owner.trim())) {
					MessageBox.post("δ������ѡ�SAC_Folder_Template_Owner��ֵ", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				templateTypes = session.getPreferenceService().getStringArray(4, "SAC_Folder_Template_Type");
				if(templateTypes == null || templateTypes.length == 0){
					MessageBox.post("δ������ѡ�SAC_Folder_Template_Type��ֵ", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				if(templateTypes.length!=2){
					MessageBox.post("δ��ȷ������ѡ�SAC_Folder_Template_Type��ֵ", "��ʾ", MessageBox.INFORMATION);
					return;
				}
				if(flag){
					if(item.getType().equals(item_type1)){
						templateType = templateTypes[0];
						String item_name1 = item.getProperty("object_name");
						item_name = item_name1 + xmmuban;
						TCComponentForm master_form = (TCComponentForm) item.getRelatedComponent(relation);
						String str_xmlx = master_form.getProperty("s4Projtype");
						System.out.println("str_xmlx======>:"+str_xmlx);
						if(str_xmlx==null || "".equals(str_xmlx)){
							MessageBox.post("��Ŀ����û��ά��������ά����Ŀ����!", "��ʾ", MessageBox.INFORMATION);
							return;
						}else{
							tempalte_name = str_xmlx+muban;
							/*templates = new String[1];
							templates[0] = str_xmlx+muban;*/
						}
					}else{
						templateType = templateTypes[1];
						String item_name1 = item.getProperty("object_name");
						item_name = item_name1 + xmmuban;
						tempalte_name = "������Ŀģ��";
						/*String item_name1 = item.getProperty("object_name");
						item_name = item_name1 + xmmuban;
						String tem = map.get(item.getType());
						String[] str = tem.split(",");
						templates = new String[str.length];
						if(str.length>1){
							for (int i = 0; i < str.length; i++) {
								templates[i] = str[i];
							}
						}else{
							templates[0] = tem;
						}*/
					}
					System.out.println("templateType===>:"+templateType);
					templateObject = getTemplateFolder(tempalte_name,templateType);
					if (templateObject != null) {
						// �����ṹ�Ի���
						ShowTemplateObjectsDialog dialog = new ShowTemplateObjectsDialog(app, templateObject, item_name,templateType);
						dialog.setVisible(true);
					} else {
						MessageBox.post("δ�ҵ�ѡ���ģ����������Ӧ��ģ�����", "��ʾ", MessageBox.WARNING);
						return;
					}
						
				}else{
					MessageBox.post("��ѡ��ָ�����Ͷ���", "��ʾ", MessageBox.INFORMATION);
					return;
				}
			}else{
				MessageBox.post("δ������ѡ�SAC_Folder_Template_Name��ֵ", "��ʾ", MessageBox.INFORMATION);
				return;
			}
		} catch (TCException e) {
			e.printStackTrace();
		}
	}
	
	// ��ȡ��Ӧ��ģ�����
	private TCComponentFolder getTemplateFolder(String templateName,String type) {
		TCComponentFolder folder = null;
		try {
			TCTextService tcTextService = session.getTextService();
			String askKey[] = { tcTextService.getTextValue("Name"), tcTextService.getTextValue("Type"), tcTextService.getTextValue("OwningUser") };
			String askValue[] = { templateName, type, owner };
			InterfaceAIFComponent objects[] = session.search("����...", askKey, askValue);
			if (objects != null && objects.length > 0) {
				folder = (TCComponentFolder) objects[0];
			}
		} catch (TCException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return folder;
	}
}
