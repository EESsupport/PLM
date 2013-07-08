package com.teamcenter.rac.sac.projectinfo;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import cn.com.origin.util.ProgressBarThread;

import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aif.AbstractAIFOperation;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMViewRevisionType;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentFolderType;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemType;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.VerticalLayout;

public class ShowTemplateObjectsDialog extends AbstractAIFDialog {

	private static final long serialVersionUID = 1L;
	private AbstractAIFUIApplication application;
	private TCSession session;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JScrollPane jsp;
	public static JTree tree;
	private DefaultMutableTreeNode rootNode;
	public static TCComponentFolder template;
	private String projectName;
	private String templateType;
	private Vector<String> allTypes = new Vector<String>();
	private ProgressBarThread progressBarThread = null;
	private ProgressBarThread progressBarThread1 = null;
	private String releation = "IMAN_reference";
	private Vector<String> v_all_info = new Vector<String>();
	private Vector<String> selected_moren = new Vector<String>();
	CheckTreeManager manager;
	
	public ShowTemplateObjectsDialog(AbstractAIFUIApplication app, TCComponentFolder templateFolder, String proName,String type) {
		super(true);
		application = app;
		session = (TCSession) application.getSession();
		template = templateFolder;
		templateType = type;
		projectName = proName;
		v_all_info.removeAllElements();
		selected_moren.removeAllElements();
		progressBarThread1 = new ProgressBarThread("��ʾ", "���ڼ���ģ�����ݣ����Ժ�...");
		progressBarThread1.start();
		try {
			allTypes = getNeedSelects(template.getProperty("object_name"));
//			System.out.println("allTypes---->:"+allTypes);
		} catch (TCException e) {
			e.printStackTrace();
		}
		initUI();
		progressBarThread1.stopBar();
	}

	// ����������
	private void initUI() {
		setTitle("ͨ����Ŀģ�崴����Ŀ����");
		setLayout(new VerticalLayout(5, 2, 2, 2, 2));
		setPreferredSize(new Dimension(600, 630));

		rootNode = new DefaultMutableTreeNode(new TreeNodeObject(false, projectName, 0, null));
		tree = new JTree(rootNode);
		
		
		// ���û�ѡ���ģ���µĶ�����ӵ����ṹ��
		addObjectToTree(template, rootNode);
		
		manager = new CheckTreeManager(tree,selected_moren);

//		System.out.println("selected_moren=======daxiao====>:"+selected_moren.size());
//		System.out.println("selected_moren---->:"+selected_moren);
		
		// չ�����нڵ�
		TreePath tp = new TreePath(tree.getModel().getRoot());
		expandAll(tp, true);
		/*Enumeration nodes = rootNode.children();
		while (nodes.hasMoreElements()) {
			TreeNode kid = (TreeNode) nodes.nextElement();
			tree.collapsePath(new TreePath(((DefaultTreeModel) tree.getModel()).getPathToRoot(kid )));
		}*/
		
		// ����Ĭ��ѡ�нڵ�
		int size = selectedNodes.size(); // ������ѡ�еĽڵ������
		TreePath[] selectedPaths = new TreePath[size];
		for (int i = 0; i < selectedPaths.length; i++) {
			selectedPaths[i] = new TreePath(selectedNodes.get(i).getPath());
		}
		manager.getSelectionModel().setSelectionPaths(selectedPaths);
		
		jsp = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setPreferredSize(new Dimension(600, 550));
		jsp.setViewportView(tree);
		btnConfirm = new JButton("ȷ������");
		btnConfirm.setPreferredSize(new Dimension(100, 25));
		btnConfirm.addActionListener(confirmListener(rootNode));
		btnCancel = new JButton("�ء�����");
		btnCancel.setPreferredSize(new Dimension(100, 25));
		btnCancel.addActionListener(cancelListener());
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(btnConfirm);
		buttonPanel.add(new JLabel("��������"));
		buttonPanel.add(btnCancel);
		buttonPanel.setPreferredSize(new Dimension(655, 40));

		getContentPane().add("top.bind", jsp);
		getContentPane().add("bottom.bind", buttonPanel);
		centerToScreen();
		pack();
	}

	// "ȷ��"��ť�ļ����¼�
	private ActionListener confirmListener(final DefaultMutableTreeNode rootNode) {
		ActionListener listener = new ActionListener() {

			public void actionPerformed(ActionEvent e1) {
				v_all_info= manager.getVecinfo();
				System.out.println("v_all_info--->:"+v_all_info);
				session.queueOperation(new AbstractAIFOperation("������...") {
					public void executeOperation() throws Exception {
						progressBarThread = new ProgressBarThread("��ʾ", "���ڴ�����Ŀ���ϣ����Ժ�...");
						progressBarThread.start();
						
//						TCComponentFolder templateFolder = (TCComponentFolder) application.getTargetComponent();
						TCComponentItem item = (TCComponentItem) application.getTargetComponent();
						System.out.println("item---->:"+item);
//						TCComponent[] folders = item.getRelatedComponents(releation);
//						TCComponentFolder templateFolder = (TCComponentFolder) folders[0];
						//����Ŀ�ļ��д�������ѡitem������
						TCComponentFolder rootFolder = createFolder(templateType, projectName, item);
						CreateFolderAndItems(rootNode, rootFolder);

						progressBarThread.stopBar();
						MessageBox.post("������Ŀ���ϳɹ�", "��ʾ", MessageBox.INFORMATION);
						if(v_all_info.size()>0){
							MessageBox.post("��ע��"+v_all_info+"����ʱû��ѡ��", "��ʾ", MessageBox.INFORMATION);
						}
					}
				});

				// �رնԻ���
				setVisible(false);
				dispose();

			}

		};
		return listener;
	}

	// ����ģ����ѡ����ļ��м������
	private void CreateFolderAndItems(DefaultMutableTreeNode node, TCComponentFolder parent) {
		if (node.getChildCount() > 0) {
			Enumeration e = node.children();
			while (e.hasMoreElements()) {
				DefaultMutableTreeNode kid = (DefaultMutableTreeNode) e.nextElement();
				TreeNodeObject tno = (TreeNodeObject) kid.getUserObject();
				// ������ļ��нڵ�
				if (tno.getNodeType() == 0) {
					String fType = tno.getNodeObject().getType();
					String fName = tno.getNodeName();
					TCComponentFolder folder = createFolder(fType, fName, parent);
					// �����ݹ�
					CreateFolderAndItems(kid, folder);
				} else if (tno.getNodeType() == 1) {
					if(selected_moren.contains(tno.getNodeName())){
						String iType = tno.getNodeObject().getType();
						String iName = tno.getNodeName();
						createItem(iType, iName, parent);
					}else{
						if (tno.isIfSelected()) {
							String iType = tno.getNodeObject().getType();
							String iName = tno.getNodeName();
							createItem(iType, iName, parent);
						}
					}
				}
			}
		}
	}

	// �������ͺ����ƴ����ļ��ж���
	private TCComponentFolder createFolder(String type, String name, TCComponentItem parent) {
		TCComponentFolder folder = null;
		System.out.println("parent======>:"+parent);
		try {
			TCComponentFolderType folderType = (TCComponentFolderType) session.getTypeComponent(type);
			folder = folderType.create(name, "", type);
			if (folder != null)
				parent.add(releation, folder);
		} catch (TCException e) {
			e.printStackTrace();
		}
		return folder;
	}
	
	// �������ͺ����ƴ����ļ��ж���
	private TCComponentFolder createFolder(String type, String name, TCComponentFolder parent) {
		TCComponentFolder folder = null;
		try {
			TCComponentFolderType folderType = (TCComponentFolderType) session.getTypeComponent(type);
			folder = folderType.create(name, "", type);
			if (folder != null)
				parent.add("contents", folder);
		} catch (TCException e) {
			e.printStackTrace();
		}
		return folder;
	}

	// �������ͺ����ƴ������������
	private void createItem(String type, String name, TCComponentFolder parent) {
		try {
			TCComponentItemType itemType = (TCComponentItemType) session.getTypeComponent(type);
			String itemId = itemType.getNewID(); // ��ȡ����
			String itemRev = itemType.getNewRev(null); // ��ȡ�汾����
			TCComponentItem item = itemType.create(itemId, itemRev, type, name, "", null);
			if (item != null)
				parent.add("contents", item);
		} catch (TCException e) {
			e.printStackTrace();
		}
	}

	// "�ر�"��ť�ļ����¼�
	private ActionListener cancelListener() {
		ActionListener listener = new ActionListener() {

			public void actionPerformed(ActionEvent e1) {
				// �رնԻ���
				setVisible(false);
				dispose();
			}

		};
		return listener;
	}

	Vector<DefaultMutableTreeNode> selectedNodes = new Vector<DefaultMutableTreeNode>();

	// ���û�ѡ���ģ���µĶ�����ӵ����ṹ��
	private void addObjectToTree(TCComponentFolder folder, DefaultMutableTreeNode dmtn) {
		try {
			TCComponent[] children = folder.getRelatedComponents("contents");
			for (int i = 0; i < children.length; i++) {
				TCComponent child = children[i];
				String name = child.getProperty("object_name");
				if (child instanceof TCComponentFolder) {
					TreeNodeObject tno = new TreeNodeObject(true, name, 0, child);
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(tno);
					dmtn.add(node);
					addObjectToTree((TCComponentFolder) child, node);
				} else if (child instanceof TCComponentItem) {
					boolean select = allTypes.contains(child.getType());
					TreeNodeObject tno = new TreeNodeObject(select, name, 1, child);
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(tno);
					dmtn.add(node);
					if (select){
						selectedNodes.add(node);
						selected_moren.add(node.getUserObject().toString());
					}
				}
			}
		} catch (TCException e) {
			e.printStackTrace();
		}
	}

	// ��ȡ��ѡ�������õĵ�ǰģ����������Ҫѡ�еĽڵ�����
	private Vector<String> getNeedSelects(String name) {
		Vector<String> vector = new Vector<String>();
		String[] preferences = session.getPreferenceService().getStringArray(4, "SAC_PrjFolder_Filter");
		if (preferences != null && preferences.length > 0) {
			for (int j = 0; j < preferences.length; j++) {
				String preference = preferences[j];
				String[] str = preference.split(":");
				if (name.equals(str[0]) && "true".equals(str[2])) {
					vector.add(str[1]);
				}
			}
		}
		return vector;
	}

	// չ�����нڵ�
	private void expandAll(TreePath parent, boolean expand) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(path, expand);
			}
		}
		tree.expandPath(parent);
	}

}
