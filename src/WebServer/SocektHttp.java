package WebServer;

import java.net.ServerSocket;
import java.net.Socket;

public class SocektHttp{  
    private static int PORT = 8089;  
      
    public static void main(String[] args) {  
        try{  
            ServerSocket serverSocket = new ServerSocket(PORT);  
            while(true){  
                try{  
                    Socket connection = serverSocket.accept();  
                    Thread task = new TestThread(connection);  
                    task.start();  
                    System.out.println("HTTP服务器正在运行,端口：" + PORT);  
                }catch(Exception ex){  
                      
                }  
            }  
        }catch(Exception ex){  
            System.exit(0);  
        }  
          
    }  
      
  
}  
