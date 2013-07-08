package com.origin.rac.sac.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.origin.rac.sac.cgsqdcd.S4CGSQDCDAction;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.util.MessageBox;

public class S4CGSQDCDHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		// TODO Auto-generated method stub
		
		AbstractAIFUIApplication app = AIFUtility.getCurrentApplication();
		
		InterfaceAIFComponent[] targets = app.getTargetComponents();
		
		if(targets.length == 1)
		{
			InterfaceAIFComponent target = targets[0];
			//System.out.println("��������"+target.getType());
			if(target instanceof TCComponentItem)
			{
				TCComponentItem targetitem = (TCComponentItem) target;
				String type = targetitem.getType();
				//System.out.println(type);
				if(type.equals("S4QGD"))
				{
					S4CGSQDCDAction action = new S4CGSQDCDAction(app,null,"",targetitem);
					new Thread(action).start();
				}
				else
				{
					MessageBox.post("��ѡ�񵥸��ɹ����뵥����","��ʾ",MessageBox.WARNING);
					return null;
				}
			}
			else
			{
				MessageBox.post("��ѡ�񵥸��ɹ����뵥����","��ʾ",MessageBox.WARNING);
				return null;
			}
		}
		else
		{
			MessageBox.post("��ѡ�񵥸��ɹ����뵥����","��ʾ",MessageBox.WARNING);
			return null;
		}
		
		return null;
	}

}
