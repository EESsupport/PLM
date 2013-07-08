package test;
import java.io.UnsupportedEncodingException;
 

public class CutString {
 /**  
     * �ж��Ƿ���һ�����ĺ���  
     *   
     * @param c  
     *            �ַ�  
     * @return true��ʾ�����ĺ��֣�false��ʾ��Ӣ����ĸ  
     * @throws UnsupportedEncodingException  
     *             ʹ����JAVA��֧�ֵı����ʽ  
     */  
    public static boolean isChineseChar(char c)   
            throws UnsupportedEncodingException {   
        // ����ֽ�������1���Ǻ���   
        // �����ַ�ʽ����Ӣ����ĸ�����ĺ��ֲ�����ʮ���Ͻ������������Ŀ�У������ж��Ѿ��㹻��   
        return String.valueOf(c).getBytes("GBK").length > 1;   
    }   
  
    /**  
     * ���ֽڽ�ȡ�ַ���  
     *   
     * @param orignal  
     *            ԭʼ�ַ���  
     * @param count  
     *            ��ȡλ��  
     * @return ��ȡ����ַ���  
     * @throws UnsupportedEncodingException  
     *             ʹ����JAVA��֧�ֵı����ʽ  
     */  
    /*public static String substring(String orignal, int count)   
            throws UnsupportedEncodingException {   
        // ԭʼ�ַ���Ϊnull��Ҳ���ǿ��ַ���   
        if (orignal != null && !"".equals(orignal)) {   
            // ��ԭʼ�ַ���ת��ΪGBK�����ʽ   
            //orignal = new String(orignal.getBytes(), "GBK");//
           // System.out.println(orignal);
          //  System.out.println(new String(orignal.getBytes("GBK"),"UTF-8"));
            // Ҫ��ȡ���ֽ�������0����С��ԭʼ�ַ������ֽ���   
            if (count > 0 && count < orignal.getBytes("GBK").length) {   
                StringBuffer buff = new StringBuffer();   
                char c;   
                for (int i = 0; i < count; i++) {   
                    c = orignal.charAt(i);   
                    buff.append(c);   
                    if (CutString.isChineseChar(c)) {   
                        // �������ĺ��֣���ȡ�ֽ�������1   
                        --count;   
                    }   
                }   
            //   System.out.println(new String(buff.toString().getBytes("GBK"),"UTF-8"));
                return new String(buff.toString().getBytes(),"UTF-8");   
            }   
        }   
        return orignal;   
    } */  
    
    public static String substring(String orignal, int count)   
                throws UnsupportedEncodingException {   
            // ԭʼ�ַ���Ϊnull��Ҳ���ǿ��ַ���   
            if (orignal != null && !"".equals(orignal)) {   
                // ��ԭʼ�ַ���ת��ΪGBK�����ʽ   
                orignal = new String(orignal.getBytes(), "GBK");   
               // Ҫ��ȡ���ֽ�������0����С��ԭʼ�ַ������ֽ���   
               if (count > 0 && count < orignal.getBytes("GBK").length) {   
                   StringBuffer buff = new StringBuffer();   
                   char c;   
                    for (int i = 0; i < count; i++) {   
                        c = orignal.charAt(i);   
                        buff.append(c);   
                        if (CutString.isChineseChar(c)) {   
                            // �������ĺ��֣���ȡ�ֽ�������1   
                            --count;   
                        }   
                    }   
                    return buff.toString();   
                }   
            }   
            return orignal;   
        } 

  
    public static void main(String[] args) {   
        // ԭʼ�ַ���   
        String s = "��ZWR��JAVA���꿼�����ݿ�ƻ���ʱ��ͼ����Ļ����ǵ绰";   
        System.out.println("ԭʼ�ַ�����" + s);   
        System.out.println("s--->:"+s.getBytes().length);
        if(s.getBytes().length>32){
        	String ha = null;
        	try {
        		ha = CutString.substring(s, 32);
        		System.out.println("ha===>:"+ha.getBytes().length);
        		if(ha.getBytes().length>32){
        			ha = CutString.substring(s, 31);
        			
        		}
        		System.out.println("ha=11111=>:"+ha);
        	} catch (UnsupportedEncodingException e) {   
                e.printStackTrace();   
            } 
        	
        }
        /*try {   
            System.out.println("��ȡǰ1λ��" + CutString.substring(s, 1));   
            System.out.println("��ȡǰ2λ��" + CutString.substring(s, 2));   
            System.out.println("��ȡǰ4λ��" + CutString.substring(s, 4));   
            System.out.println("��ȡǰ6λ��" + CutString.substring(s, 6));   
        } catch (UnsupportedEncodingException e) {   
            e.printStackTrace();   
        } */  
    }   


}
