package WebServer;

import java.io.OutputStream;
import java.io.PrintWriter;

class Server{  
    private Client request;//���ڶ�ȡ��Դ��uri  
    private OutputStream outputStream;//���������Դ  
    
    public void setOutputStream(OutputStream outputStream) {  
        this.outputStream = outputStream;  
    }  
      
    public void setRequest(Client request) {  
        this.request = request;  
    }  
      
    public void sentResponse(){  
        PrintWriter out = new PrintWriter(outputStream);  
         //����һ��״̬��  
        out.println("HTTP/1.0 200 OK");   
        //����һ���ײ�  
        out.println("Content-Type:text/html;charset=" + request.getEncoding());    
        // ���� HTTP Э��, ���н�����ͷ��Ϣ    
        out.println();  
          
        // ���������Դ  
        out.println("<h1 style='color: green'> Hello Http Server</h1>");    
        out.println("���, ����һ�� Java HTTP ������ demo Ӧ��.<br>");    
        out.println("�������·����: " + request.getUri() + "<br>");    
        out.close();    
    }  
      
}  