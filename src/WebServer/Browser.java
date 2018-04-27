package WebServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Browser{  
    private String uri;//��Դ·��  
    private InputStream inputStream;//��ȡ����  
    private String encoding = "GBK";//������Դ�ı���  
      
    Browser(InputStream inputStream) {  
        this.inputStream = inputStream;  
    }  
      
    public String getEncoding() {  
        return encoding;  
    }  
      
    public String getUri() {  
        return uri;  
    }  
      
    public void parse() throws IOException{  
        System.out.println("�ͻ��˷��͵���Ϣ��------------------>");  
        //��ȡ��һ�У������ַ  
        String line = readLine(inputStream, 0);  
        //��ӡ�ײ���  
        System.out.print(line);  
          
        //��ȡ��Դ��·��  
        uri = line.substring(line.indexOf('/'),line.lastIndexOf('/') - 5);  
        //��ȡ���󷽷�  
        String method = new StringTokenizer(line).nextElement().toString();  
        //�����POST�������������Ϣ�峤��  
        int contentLength = 0;  
          
        //��ȡ����Ԫ���ݵ�HTTP�ײ�����ӡ  
        do{  
            line = readLine(inputStream, 0);  
            //�����Content-Length��Ϣͷʱ���  
            if(line.startsWith("Content-Length")){  
                contentLength = Integer.parseInt(line.split(":")[1].trim());  
            }  
            //��ӡԪ������Ϣ  
            System.out.print(line);  
            //���������һ�������Ļس����У����ʾ����ͷ����  
        }while(!line.equals("\r\n"));  
          
        //�����Post����,������Ϣ��  
        if("POST".equalsIgnoreCase(method)){  
            //����ֻ�Ǽ򵥵Ĵ�����ύ�Ĳ������������ϴ��ļ������ǲ������������  
            //��Ϊ�ϴ����ļ�ʱ��Ϣ�岻ֹһ�У����ж�����Ϣ��  
            System.out.print(readLine(inputStream, contentLength));  
        }  
          
        //�ͻ��˷�����Ϣ����  
        System.out.println("�ͻ��˷���������Ϣ����������������������������������");  
        System.out.println("�û��������Դ�ǣ�" + uri);  
        System.out.println("�û�����ķ��������ǣ� " + method);  
    }  
    /** 
     *  
     * ����ģ���ȡһ�У���Ϊ���ʹ��API�е�BufferedReaderʱ�����Ƕ�ȡ��һ���س����к�ŷ��أ� 
     * �������û�ж�ȡ����һֱ��������͵������POST����ʱ�����е�Ԫ������Ϣ�崫�ͣ���ʱ����Ϣ����ĩ 
     * ����׼��û�лس����еģ������ʱ��ʹ��BufferedReader����ȡʱ����POST�ύʱ�������������POST 
     * �ύʱ���ǰ�����Ϣ��ĳ���Context-Length����ȡ��Ϣ�壬�������ᱻ������ 
     */  
    private String readLine(InputStream inputStream, int contentLength) throws IOException{  
        ArrayList<Object> arrayList = new ArrayList<>();  
        byte readByte = 0;  
        int total = 0;  
        if(contentLength != 0){//post����  
            while(total < contentLength){  
                readByte = (byte)inputStream.read();  
                arrayList.add(Byte.valueOf(readByte));  
                total++;  
            }  
        }else{//get����  
            while(readByte != 10){  
                readByte = (byte)inputStream.read();  
                arrayList.add(Byte.valueOf(readByte));  
            }  
        }  
          
        byte[] tempByteArr = new byte[arrayList.size()];  
        for(int i = 0; i < arrayList.size(); i++){  
            tempByteArr[i] = ((Byte)arrayList.get(i)).byteValue();  
        }  
        arrayList.clear();  
          
        String tempStr = new String(tempByteArr,encoding);  
        /** 
         * HTTP�����е�header����һ��referer���ԣ�������Ե���˼���������ǰ�����Ǵӱ��ҳ�����ӹ����ģ� 
         * �Ǹ����Ծ����Ǹ�ҳ���url����������url��ֱ�Ӵ��������ַ������ľ�û�����ֵ���õ����ֵ����ʵ�� 
         * �ܶ����õĹ��ܣ��������������¼������Դ�Լ���ס�ղŷ��ʵ����ӵȡ����⣬��������������Referer 
         * ����ʱ����̶���utf-8����ģ�������GBK�³������룬�������һ�� 
         */  
        if(tempStr.startsWith("Referer")){//�����Refererͷʱ��ʹ��UTF-8����  
            tempStr = new String(tempByteArr,"UTF-8");  
        }  
        return tempStr;  
          
    }  
  
}  
  
