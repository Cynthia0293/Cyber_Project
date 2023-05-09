import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartServer extends JFrame implements ActionListener {
    private JButton startServer_btn;
    private JButton endServer_btn;
    private Service startService;
    public JPanel panel;

    public StartServer() {


        panel = new UIdesign.jPanelGradient();
        panel.setLayout(null);
        add(panel);
        setTitle("Server");
        setSize(360, 300);

        JLabel ratioLabel = new JLabel();
        ratioLabel.setIcon(UIdesign.createAutoAdjustIcon("src/pic/cloud-server.png", true));
        ratioLabel.setBounds(140,40, 65,72);
        panel.add(ratioLabel);

        startServer_btn = new UIdesign.RoundRectButton("Start Service");
        startServer_btn.setBounds(80, 140, 193, 30);
        startServer_btn.setFont(UIdesign.FONT_BTN);
        startServer_btn.setForeground(Color.WHITE);
        startServer_btn.setBackground(UIdesign.COLOR_TXT);
        startServer_btn.addActionListener(this);
        panel.add(startServer_btn);

        endServer_btn = new UIdesign.RoundRectButton("Close Service");
        endServer_btn.setBounds(80, 180, 193, 30);
        endServer_btn.setFont(UIdesign.FONT_BTN);
        endServer_btn.setForeground(Color.WHITE);
        endServer_btn.setBackground(UIdesign.COLOR_TXT);
        endServer_btn.addActionListener(this);
        panel.add(endServer_btn);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    public static void main(String[] args) {
        new StartServer();
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startServer_btn){
            if(startService==null){

                new startServerThread().start();
            }
        }
        //Logging out of the server
        if(e.getSource()==endServer_btn){
            startService=null;
            System.exit(0);
        }
    }
    private class startServerThread extends Thread{
        public void run() {
            startService=new Service();
        }
    }
}