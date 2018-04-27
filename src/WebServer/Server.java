package WebServer;

import java.io.OutputStream;
import java.io.PrintWriter;

class Server{  
    private Client request;//用于读取资源的uri  
    private OutputStream outputStream;//用于输出资源  
    
    public void setOutputStream(OutputStream outputStream) {  
        this.outputStream = outputStream;  
    }  
      
    public void setRequest(Client request) {  
        this.request = request;  
    }  
      
    public void sentResponse(){  
        PrintWriter out = new PrintWriter(outputStream);  
         //返回一个状态行  
        out.println("HTTP/1.0 200 OK");   
        //返回一个首部  
        out.println("Content-Type:text/html;charset=" + request.getEncoding());    
        // 根据 HTTP 协议, 空行将结束头信息    
        out.println();  
          
        // 输出请求资源  
        out.println("<h1 style='color: green'> Hello Http Server</h1>");    
        out.println("你好, 这是一个 Java HTTP 服务器 demo 应用.<br>");    
        out.println("您请求的路径是: " + request.getUri() + "<br>");    
        out.close();    
    }  
      
}  