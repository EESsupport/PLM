package com.origin.rac.sac.eco;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class JieCopyFile {
	public void copyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// �½��ļ����������������л���
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			// �½��ļ���������������л���
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			// ��������
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// ˢ�´˻���������
			outBuff.flush();
		} finally {
			// �ر���
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	// ��ָ����Ŀ¼�£��ж��Ƿ����name���ļ�
	public Boolean isFileExist(String path, String name) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}

		String[] str = file.list();
		for (String string : str) {
			if (string.equals(name)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * �����ļ�(�Գ�����ٶȸ����ļ�)
	 * @param srcFile
	 * Դ�ļ�File
	 * @param destDir
	 * Ŀ��Ŀ¼File
	 * @param newFileName
	 * ���ļ���
	 * @return ʵ�ʸ��Ƶ��ֽ���������ļ���Ŀ¼�����ڡ��ļ�Ϊnull���߷���IO�쳣������-1
	 */
	public String copyFile(File srcFile, File destDir, String newFileName) {

		long copySizes = 0;

		if (!srcFile.exists()) {
			System.out.println("Դ�ļ�������");
			copySizes = -1;

		} else if (!destDir.exists()) {
			System.out.println("Ŀ��Ŀ¼������");
			copySizes = -1;

		} else if (newFileName == null) {
			System.out.println("�ļ���Ϊnull");
			copySizes = -1;

		} else {

			try {
				FileInputStream fis = new FileInputStream(srcFile);
				FileOutputStream fos = new FileOutputStream(new File(destDir,
						newFileName));

				FileChannel fcin = fis.getChannel();
				FileChannel fcout = fos.getChannel();

				// long size = fcin.size();
				fcin.transferTo(0, fcin.size(), fcout);
				fcin.close();
				fcout.close();
				fis.close();
				fos.close();
				return destDir + "\\" + newFileName;

			} catch (FileNotFoundException e) {
				e.printStackTrace();

			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		return newFileName;
	}
}
