package com.origin.rac.sac.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import com.origin.rac.sac.htjs.S4HTJSAction;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCAccessControlService;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class S4HTJSHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		
		AbstractAIFUIApplication app = AIFUtility.getCurrentApplication();
		TCSession session = (TCSession) app.getSession();
		
		InterfaceAIFComponent[] targets = app.getTargetComponents();
		
		if(targets.length == 1)
		{
			InterfaceAIFComponent target = targets[0];
			System.out.println("��������"+target.getType());
			if(target instanceof TCComponentFolder)
			{
				TCComponentFolder targetfolder = (TCComponentFolder) target;
				String type = targetfolder.getType();
				System.out.println(type);
				if(type.equals("Folder"))
				{
					try {
						TCAccessControlService s = session.getTCAccessControlService();
						boolean canwrite = s.checkPrivilege(targetfolder,"WRITE");
						if(!canwrite)
						{
							MessageBox.post("���Ը��ļ���û��д��Ȩ�ޣ�������ѡ��!", "��ʾ", MessageBox.INFORMATION);
							return null;
						}
						else
						{
							S4HTJSAction action = new S4HTJSAction(app,null,"",targetfolder);
							new Thread(action).start();
						}
					} catch (TCException e) {
						e.printStackTrace();
					}
				}
				else
				{
					MessageBox.post("��ѡ�񵥸��ļ��ж���","��ʾ",MessageBox.WARNING);
					return null;
				}
			}else {
				MessageBox.post("��ѡ�񵥸��ļ��ж���","��ʾ",MessageBox.WARNING);
				return null;
			}
		}
		else
		{
			MessageBox.post("��ѡ�񵥸��ļ��ж���","��ʾ",MessageBox.WARNING);
			return null;
		}
		
		return null;
	}

}
