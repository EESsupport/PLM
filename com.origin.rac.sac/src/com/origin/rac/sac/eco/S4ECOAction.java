package com.origin.rac.sac.eco;

import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aif.common.actions.AbstractAIFAction;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMViewRevision;
import com.teamcenter.rac.kernel.TCComponentDataset;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.util.MessageBox;

public class S4ECOAction extends AbstractAIFAction{

	private AbstractAIFUIApplication app = null;
	private TCComponentItemRevision target = null;

	private String relationForm = "IMAN_master_form_rev";
	private static final String relation = "structure_revisions";
	public String description = null;
	private String relation1 = "structure_revisions";
	private String before_zzdm = "";
	private String after_zzdm = "";
	
	
	public S4ECOAction(AbstractAIFUIApplication arg0, TCComponentItemRevision target, String arg1) {
		super(arg0, arg1);
		this.app = arg0;
		this.target = target;
	}

	public void run() {
		try {
			TCComponent[] before = target.getReferenceListProperty("S4Before_C");
			TCComponent[] after = target.getReferenceListProperty("S4After_C");
			if(before.length ==1 && after.length ==1){
				TCComponentItemRevision beforeItemRev = (TCComponentItemRevision)before[0];
				TCComponent[] before_views = beforeItemRev.getRelatedComponents(relation1);
				if(before_views.length>1){
					MessageBox.post("����ǰ�����ܴ���������ͼ�汾!","��ʾ",MessageBox.WARNING);
					return;
				}
				TCComponentBOMViewRevision before_bomview = (TCComponentBOMViewRevision) beforeItemRev.getRelatedComponent(relation1);
				if(before_bomview!=null){
					String str_view = before_bomview.toString();
					before_zzdm = str_view.substring(str_view.length()-3,str_view.length());
				}
				System.out.println("before_zzdm---->:"+before_zzdm);
				TCComponentItemRevision afterItemRev = (TCComponentItemRevision)after[0];
				TCComponent[] after_views = afterItemRev.getRelatedComponents(relation1);
				if(after_views.length>1){
					MessageBox.post("���ĺ�����ܴ���������ͼ�汾!","��ʾ",MessageBox.WARNING);
					return;
				}
				TCComponentBOMViewRevision after_bomview = (TCComponentBOMViewRevision) afterItemRev.getRelatedComponent(relation1);
				if(after_bomview!=null){
					String str_view = after_bomview.toString();
					after_zzdm = str_view.substring(str_view.length()-3,str_view.length());
				}
				System.out.println("���ĺ���֯����----------->:"+after_zzdm);
				if(!before_zzdm.equals(after_zzdm)){
					MessageBox.post("����ǰ����͸��ĺ������ͼ�汾��һ��!","��ʾ",MessageBox.WARNING);
					return;
				}
				
				String beforeItemId = beforeItemRev.getProperty("item_id");
				String afterItemId = afterItemRev.getProperty("item_id");
				if(beforeItemId.equals(afterItemId)){
					String beforeItemRevId = beforeItemRev.getProperty("item_revision_id");
					String afterItemRevId = afterItemRev.getProperty("item_revision_id");
					if(!beforeItemRevId.equals(afterItemRevId)){

						TCComponent beforeComponent = beforeItemRev.getRelatedComponent(relation);
						TCComponent afterComponent = afterItemRev.getRelatedComponent(relation);
						if(beforeComponent != null && afterComponent != null){
							TCComponentBOMViewRevision beforeBOMViewRevision = 
								(TCComponentBOMViewRevision)beforeComponent;
							TCComponentBOMViewRevision afterBOMViewRevision = 
								(TCComponentBOMViewRevision)afterComponent;
							//�õ�˵��
							TCComponent[] tccomponent = beforeItemRev.getRelatedComponents(relationForm);
							for (int i = 0; i < tccomponent.length; i++) {
								if(tccomponent[i] instanceof TCComponentForm){
									TCComponentForm tccomponentForm = (TCComponentForm)tccomponent[i];
									description = tccomponentForm.getProperty("s4Mdescription");
								}
							}
							//��������ݼ��Ͳ�Ҫ�ڽ�����
							TCComponent[] specstand = target.getReferenceListProperty("S4ECO");
							if(specstand.length != 0){
/*								for (int n = 0; n < specstand.length; n++) {
									if(specstand[n] instanceof TCComponentDataset){
										TCComponentDataset temp = (TCComponentDataset)specstand[n];
										target.remove("S4ECO", temp);
									}
								}*/
								MessageBox.post("ECO�Ѿ����ɹ���","��ʾ",MessageBox.WARNING);
								return;
							}
							
							S4ECOCommand s4ECOCommand = new S4ECOCommand(app,
									beforeBOMViewRevision, afterBOMViewRevision, description, target,before_zzdm);
							try {
								s4ECOCommand.executeModal();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						else{
							if(beforeComponent == null){
								MessageBox.post("����ǰ������û��bomview��","��ʾ",MessageBox.WARNING);
								return;
							}
							else{
								MessageBox.post("���ĺ������û��bomview��","��ʾ",MessageBox.WARNING);
								return;
							}
						}
					}
					else{
						MessageBox.post("�뱣֤����ǰ���������ͬһ���ϵĲ�ͬ�汾��","��ʾ",MessageBox.WARNING);
						return;
					}
				}
				else{
					MessageBox.post("�뱣֤����ǰ���������ͬһ���ϵĲ�ͬ�汾��","��ʾ",MessageBox.WARNING);
					return;
				}
			}
			else{
				MessageBox.post("�뱣֤����ǰ�͸��ĺ�Ϊ�գ���ֻ��һ��bom��","��ʾ",MessageBox.WARNING);
				return;
			}
		} catch (TCException e1) {
			e1.printStackTrace();
		}
	}
}
