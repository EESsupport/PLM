package com.origin.rac.sac.projectmember;

import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.common.Activator;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.util.MessageBox;

public class ProjectMemberCommand extends AbstractAIFCommand {

	private String[] str_types = {"S4YFprojXJ","S4Gcproj"};
	private boolean flag = false;
	

	public ProjectMemberCommand(AbstractAIFUIApplication app) {
		TCComponent target = (TCComponent) app.getTargetComponent();
		if (target == null || !(target instanceof TCComponentItem)) {
			MessageBox.post("��ѡ����Ҫ����Ŀ���Աָ�ɵ���Ŀ����!", "��ʾ", MessageBox.INFORMATION);
			return;
		}else{
			TCComponentItem item = (TCComponentItem) target;
			System.out.println("item.getType()===>:"+item.getType());
			//�ж�ѡ��������ǲ��Ƿ���Ҫ��
			for (int i = 0; i < str_types.length; i++) {
				if(str_types[i].equals(item.getType().toString())){
					flag=true;
					break;
				}
			}
			if(flag){
				String s = "com.teamcenter.rac.project.ProjectPerspective";
	            Activator.getDefault().openPerspective(s);
	            Activator.getDefault().openComponents(s, null);
			}else{
				MessageBox.post("��ѡ����Ҫ����Ŀ���Աָ�ɵ���Ŀ����!", "��ʾ", MessageBox.INFORMATION);
				return;
			}
		}
	}
	
}
