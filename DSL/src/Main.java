import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Main {

    JFrame f = new JFrame();// 创建窗体
    Container c;// 容器
    parser p=new parser();

    public Main() throws IOException {
    }

    public void way2() throws IOException {

        // Content(包含)，pane（窗格）

        c = f.getContentPane();// 容器c嵌入窗体f中
        c.setLayout(new FlowLayout());// 布局，流布局，flow（流动）
        f.setTitle("自动客服机器人");
        f.setBounds(600, 150, 400, 300);
        f.setLocationRelativeTo(null);
        JButton jb = new JButton("测试");
        JButton jb3 = new JButton("管理文法");
        ImageIcon imgicon = new ImageIcon("1.png");//图片插入，更改图片路径，需要注意后缀
        JLabel imgjla = new JLabel(imgicon);
        Dimension dim = new Dimension(360,203);
        imgjla.setPreferredSize(dim);
        c.add(imgjla);
        c.add(jb);
        c.add(jb3);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                try {
                    test t=new test(p);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }//即监听(实时)

        });
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manage manager=new manage(p);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
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
        Main d = new Main();
        d.way1();// 窗体基本属性
        d.way2();// 容器。复选框。

        d.f.setVisible(true);// 窗体可见
    }
}


