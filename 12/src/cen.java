import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class cen {

    JFrame f = new JFrame();// 创建窗体
    Container c;// 容器
    static JTextArea ta;
    static JTextArea tb;
    static Socket socket;
    DataOutputStream os;

    public void way2() throws IOException {

        // Content(包含)，pane（窗格）
        c = f.getContentPane();// 容器c嵌入窗体f中
        c.setLayout(new FlowLayout());// 布局，流布局，flow（流动）
        f.setTitle("聊天室");
        f.setBounds(600, 150, 400, 800);
        f.setLocationRelativeTo(null);
        //聊天记录显示区
        ta = new JTextArea(35, 40);
        tb = new JTextArea(10, 40);
        //聊天记录输入区
        ta.setEditable(false);
        tb.setEditable(false);
        JTextField tf = new JTextField(20);
        tf.requestFocus();//guang biao
        JButton jb = new JButton("离线");
        JButton jb1 = new JButton("发送");
        JScrollPane p=new JScrollPane(ta);
        c.add(p, BorderLayout.CENTER);
        c.add(tb, BorderLayout.CENTER);
        c.add(tf, BorderLayout.SOUTH);
        c.add(jb);
        c.add(jb1);
        ta.setText("聊天内容"+"\n");
        tb.setText("在线的用户:"+"\n");
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("离线".equals(str)) {
                    jb.setText("在线");
                    try {

                        client();
                        new Thread(new Receive()).start();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    jb.setText("离线");
                    try {
                        socket.close();
                        os.close();


                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }//即监听(实时)

        });
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String str = tf.getText();
                if (!str.equals("")) {
                    try {

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    tf.setText("");
                }

            }//即监听(实时)

        });

    }

    void way1() {
        f.setBounds(400, 200, 500, 400);// 坐标、尺寸
        // Default(默认)，Operation(操作)
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点×即关闭

    }

    public static void main(String[] args) throws IOException {
        cen d = new cen();
        d.way1();// 窗体基本属性
        d.way2();// 容器。复选框。

        d.f.setVisible(true);// 窗体可见
    }
    public static void client() throws IOException {//客户端
        InetAddress inet =  InetAddress.getLocalHost();
        socket = new Socket(inet, 8899);
        boolean m=socket.isConnected();
        System.out.println(m);
    }




}