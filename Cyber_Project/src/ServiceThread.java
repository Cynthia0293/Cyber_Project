
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServiceThread implements Runnable {
    private Socket socket;

    public ServiceThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            CmdTranser cmd=(CmdTranser) ois.readObject();
            // Process the data sent by the client
            cmd=execute(cmd);
            oos.writeObject(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                if(ois!=null)
                    ois.close();
                if(oos!=null){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private CmdTranser execute(CmdTranser cmd) {
        if("login".equals(cmd.getCmd())){
            UserService userService=new UserService();
            Users user=(Users) cmd.getData();
            cmd.setFlag(userService.checkUser(user));
            if(cmd.isFlag()){
                cmd.setResult("You are authenticated, Welcome <"+user.getUsername()+">");
            }else{
                cmd.setResult("Please enter correct username password");
            }
        }
        if("register".equals(cmd.getCmd())){
            UserService userService=new UserService();
            Users user=(Users) cmd.getData();
            int getR;
            getR=userService.addUser(user);
            if(getR==1){
                cmd.setResult("This username has been registered");
            }else if (getR==0){
                cmd.setResult("Registration Successful");
            }else{
                cmd.setResult("Registration failed");
            }
        }
        return cmd;
    }

}
