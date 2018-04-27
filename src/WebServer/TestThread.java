package WebServer;

import java.io.IOException;
import java.net.Socket;

class TestThread extends Thread{  
    private Socket connection;//�ͻ����������֮�������  
      
    public TestThread(Socket connection) {  
        this.connection = connection;  
    }  
      
    @Override  
    public void run() {  
        if(connection != null){  
            System.out.println("�̣߳�" + Thread.currentThread().getName());  
            System.out.println("���ӵ����������û���" + connection);  
            try{  
                Client request = new Client(connection.getInputStream());  
                request.parse();  
                Server response = new Server();  
                response.setRequest(request);  
                response.setOutputStream(connection.getOutputStream());  
                response.sentResponse();  
  
                //��Ϊhttp1.0����״̬Э�飬���Ա���ر�����  
                closeSocket(connection);    
        
            }catch(Exception ex){  
                System.out.println("HTTP����������" + ex.getLocalizedMessage());  
            }  
        }   
    }  
      
    /**  
    * �رտͻ��� socket ����ӡһ��������Ϣ.  
    * @param socket �ͻ��� socket.  
    */    
    void closeSocket(Socket socket) {    
        try {    
            socket.close();    
        } catch (IOException ex) {    
            ex.printStackTrace();    
        }    
        System.out.println(socket + "�뿪��HTTP������");    
    } 
}