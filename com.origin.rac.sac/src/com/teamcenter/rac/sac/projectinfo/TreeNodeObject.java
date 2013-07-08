package com.teamcenter.rac.sac.projectinfo;

import com.teamcenter.rac.kernel.TCComponent;

public class TreeNodeObject {
	private boolean ifSelected; // �Ƿ�ѡ��״̬
	private String nodeName; // �ڵ�����
	private int nodeType; // �ڵ����ͣ�0���ļ��У�1���������2������
	private TCComponent nodeObject; // �ڵ����

	public TreeNodeObject(boolean select, String nodeName, int nodeType, TCComponent nodeObject) {
		super();
		this.ifSelected = select;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.nodeObject = nodeObject;
	}

	@Override
	public String toString() {
		return nodeName;
	}

	public boolean isIfSelected() {
		return ifSelected;
	}

	public void setIfSelected(boolean ifSelected) {
		this.ifSelected = ifSelected;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getNodeType() {
		return nodeType;
	}

	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}

	public TCComponent getNodeObject() {
		return nodeObject;
	}

	public void setNodeObject(TCComponent nodeObject) {
		this.nodeObject = nodeObject;
	}

}
