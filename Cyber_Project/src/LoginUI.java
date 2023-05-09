import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;


public class LoginUI extends JFrame implements ActionListener {
    private JLabel username, password;
    private JTextField username_txt;
    private JPasswordField password_txt;
    private JButton login_btn, register_btn;
    public JPanel panel;

    public LoginUI() {
        panel = new UIdesign.jPanelGradient();
        panel.setLayout(null);
        add(panel);
        setTitle("Client");
        setSize(400, 310);

        JLabel ratioLabel = new JLabel();
        ratioLabel.setIcon(UIdesign.createAutoAdjustIcon("src/pic/TALK.png", true));
        ratioLabel.setBounds(167,20, 65,72);
        panel.add(ratioLabel);

        init();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

        private void init() {

        username = new JLabel("Username");
        username.setBounds(100,90,70,20);
        username.setForeground(UIdesign.COLOR_TXT);
        username.setFont(UIdesign.FONT_TXT);
        panel.add(username);
        username_txt = new UIdesign.RoundJTextField();
        username_txt.setBounds(100, 110, 193, 28);
        username_txt.setForeground(UIdesign.COLOR_TXT);
        username_txt.setFont(UIdesign.FONT_TXT);
        panel.add(username_txt);

        password = new JLabel("Password");
        password.setBounds(100, 140, 70, 20);
        password.setForeground(UIdesign.COLOR_TXT);
        password.setFont(UIdesign.FONT_TXT);
        panel.add(password);
        password_txt = new UIdesign.RoundJPSWField();
        password_txt.setBounds(100, 160, 193, 28);
        password_txt.setForeground(UIdesign.COLOR_TXT);
        password_txt.setFont(UIdesign.FONT_TXT);
        panel.add(password_txt);

        login_btn = new UIdesign.RoundRectButton("Sign in");
        login_btn.setBounds(100, 204, 85, 30);
        login_btn.setFont(UIdesign.FONT_BTN);
        login_btn.setForeground(Color.WHITE);
        login_btn.setBackground(UIdesign.COLOR_TXT);
        login_btn.addActionListener(this);
        panel.add(login_btn);

        register_btn = new UIdesign.RoundRectButton("Sign up");
        register_btn.setBounds(207, 204, 85, 30);
        register_btn.setFont(UIdesign.FONT_BTN);
        register_btn.setForeground(Color.WHITE);
        register_btn.setBackground(UIdesign.COLOR_TXT);
        register_btn.addActionListener(this);
        panel.add(register_btn);
    }

    public void actionPerformed(ActionEvent e) {
        // If the login button is clicked Determine if the account exists on the server
        if(e.getSource()==login_btn){
            Users user=new Users();
            user.setUsername(username_txt.getText().trim());
            user.setPassword(new String(password_txt.getPassword()).trim());
            CmdTranser cmd =new CmdTranser();
            cmd.setData(user);
            cmd.setCmd("login");
            try {
                Client.socket=new Socket(Client.address, Client.port);
                // Send data to the server
                Client.sendData(cmd);
                // Get the data sent by the server
                cmd =Client.getData();
                JOptionPane.showMessageDialog(null, cmd.getResult());
            } catch (UnknownHostException e1) {
                JOptionPane.showMessageDialog(null, "The server is not start");
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "The server is not start");
            }
        }
        // If click the Register button to open the registration screen
        if(e.getSource()==register_btn){
            new RegisterUI();
        }
    }


}