package WebServer;

import java.io.IOException;
import java.net.Socket;

class TestThread extends Thread{  
    private Socket connection;//客户端与服务器之间的连接  
      
    public TestThread(Socket connection) {  
        this.connection = connection;  
    }  
      
    @Override  
    public void run() {  
        if(connection != null){  
            System.out.println("线程：" + Thread.currentThread().getName());  
            System.out.println("连接到服务器的用户：" + connection);  
            try{  
                Client request = new Client(connection.getInputStream());  
                request.parse();  
                Server response = new Server();  
                response.setRequest(request);  
                response.setOutputStream(connection.getOutputStream());  
                response.sentResponse();  
  
                //因为http1.0是无状态协议，所以必须关闭连接  
                closeSocket(connection);    
        
            }catch(Exception ex){  
                System.out.println("HTTP服务器错误：" + ex.getLocalizedMessage());  
            }  
        }   
    }  
      
    /**  
    * 关闭客户端 socket 并打印一条调试信息.  
    * @param socket 客户端 socket.  
    */    
    void closeSocket(Socket socket) {    
        try {    
            socket.close();    
        } catch (IOException ex) {    
            ex.printStackTrace();    
        }    
        System.out.println(socket + "离开了HTTP服务器");    
    } 
}