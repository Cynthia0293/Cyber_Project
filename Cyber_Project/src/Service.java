
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;


public class Service {
    public Service(){
        try {
            ServerSocket ss=new ServerSocket(6666);
            Socket socket=null;
            JOptionPane.showMessageDialog(null, "The server is up! Please connect...");
            while(true){
                socket=ss.accept();
                ServiceThread serviceThread=new ServiceThread(socket);
                Thread thread=new Thread(serviceThread);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}