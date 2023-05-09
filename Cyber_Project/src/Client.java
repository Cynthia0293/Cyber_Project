import java.io.*;
import java.net.Socket;


public class Client {
    public static Socket socket=null;
    public static int port=6666;
    public static String address="127.0.0.1";
    public static CmdTranser getData() {
        ObjectInputStream ois=null;
        CmdTranser res=null;
        try {
            ois=new ObjectInputStream(Client.socket.getInputStream());
            res=(CmdTranser) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }
    public static void sendData(CmdTranser cmd) {
        ObjectOutputStream oos=null;
        try {
            oos=new ObjectOutputStream(Client.socket.getOutputStream());
            oos.writeObject(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}