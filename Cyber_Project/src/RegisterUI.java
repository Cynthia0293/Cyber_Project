import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;

public class RegisterUI extends JFrame implements ActionListener{
    public JPanel panel;
    private JLabel username, password,password2;
    private JTextField username_txt;
    private JPasswordField password_txt,password_txt2;
    private JButton register_btn;
    public RegisterUI(){
        panel = new UIdesign.jPanelGradient();
        panel.setLayout(null);
        add(panel);
        init();
        setTitle("Register");
        setSize(400, 310);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
    private void init() {
        username = new JLabel("Username");
        username.setBounds(100,35,70,20);
        username.setForeground(UIdesign.COLOR_TXT);
        username.setFont(UIdesign.FONT_TXT);
        panel.add(username);
        username_txt = new UIdesign.RoundJTextField();
        username_txt.setBounds(100, 55, 193, 28);
        username_txt.setForeground(UIdesign.COLOR_TXT);
        username_txt.setFont(UIdesign.FONT_TXT);
        panel.add(username_txt);

        password = new JLabel("Password");
        password.setBounds(100, 85, 70, 20);
        password.setForeground(UIdesign.COLOR_TXT);
        password.setFont(UIdesign.FONT_TXT);
        panel.add(password);
        password_txt = new UIdesign.RoundJPSWField();
        password_txt.setBounds(100, 105, 193, 28);
        password_txt.setForeground(UIdesign.COLOR_TXT);
        password_txt.setFont(UIdesign.FONT_TXT);
        panel.add(password_txt);


        password2 = new JLabel("Password again");
        password2.setBounds(100, 135, 150, 20);
        password2.setForeground(UIdesign.COLOR_TXT);
        password2.setFont(UIdesign.FONT_TXT);
        panel.add(password2);
        password_txt2 = new UIdesign.RoundJPSWField();
        password_txt2.setBounds(100, 155, 193, 28);
        password_txt2.setForeground(UIdesign.COLOR_TXT);
        password_txt2.setFont(UIdesign.FONT_TXT);
        panel.add(password_txt2);

        register_btn = new UIdesign.RoundRectButton("Create account");
        register_btn.setBounds(100, 200, 193, 31);
        register_btn.setFont(UIdesign.FONT_BTN);
        register_btn.setForeground(Color.WHITE);
        register_btn.setBackground(UIdesign.COLOR_TXT);
        register_btn.addActionListener(this);
        panel.add(register_btn);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==register_btn){
            String username=username_txt.getText().trim();
            String password1=new String(password_txt.getPassword()).trim();
            String password2=new String(password_txt2.getPassword()).trim();
            if(username==null||"".equals(username)){
                JOptionPane.showMessageDialog(null, "Please enter your username!");
                return ;
            }
            if(password1==null||"".equals(password1)){
                JOptionPane.showMessageDialog(null, "Please enter your password!");
                return ;
            }
            if(!password1.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$")){
                JOptionPane.showMessageDialog(null, "At least 8 characters long but more is better. A combination of uppercase letters, lowercase letters, numbers, and symbols.");
                return ;
            }
            if(!(password1.equals(password2))){
                JOptionPane.showMessageDialog(null, "The two entered passwords do not match!");
                return ;
            }
            // Send registration information to the server
            Users user=new Users(username, password1);
            CmdTranser cmd=new CmdTranser();
            cmd.setCmd("register");
            cmd.setData(user);
            try {
                Client.socket=new Socket(Client.address, Client.port);
                // Send data to the server
                Client.sendData(cmd);
                // Get the data sent by the server
                cmd =Client.getData();
                JOptionPane.showMessageDialog(null, cmd.getResult());
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}