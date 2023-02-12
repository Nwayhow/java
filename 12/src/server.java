import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class server {
    JFrame f=new JFrame();// 创建窗体
    Container c;// 容器
    static JTextArea ta,tb;
    DataOutputStream os;
    ArrayList<Clientcon> cclist=new ArrayList<>();
    ServerSocket ss;

    public void way2() throws IOException {
        // Content(包含)，pane（窗格）
        c=f.getContentPane();// 容器c嵌入窗体f中
        c.setLayout(new FlowLayout());// 布局，流布局，flow（流动）
        f.setTitle("服务器");
        f.setBounds(600, 150, 400, 700);
        f.setLocationRelativeTo(null);
        ta = new JTextArea(25, 40);
        ta.setEditable(false);
        tb = new JTextArea(15, 40);
        tb.setEditable(false);
        JScrollPane p=new JScrollPane(ta);
        JScrollPane q=new JScrollPane(tb);
        c.add(p, BorderLayout.CENTER);
        c.add(q, BorderLayout.CENTER);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void way1(){
        f.setBounds(400,200,500,400);// 坐标、尺寸
        // Default(默认)，Operation(操作)
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点×即关闭

    }
    public static void main(String[] args) throws IOException {
        server d=new server();
        d.way1();// 窗体基本属性
        d.way2();// 容器。复选框。

        d.f.setVisible(true);// 窗体可见
    }

    class Clientcon implements Runnable{
        String name;
        Socket s=null;
        public  Clientcon(Socket s,String name){
            this.name=name;
            this.s=s;
            (new Thread(this)).start();
        }

        @Override
        public void run() {
            try {
                DataInputStream is = new DataInputStream(s.getInputStream());
            while(true) {
             String str = is.readUTF();
             String s1=name+" 说:"+str ;
                  ta.append(s1 + '\n');
                Iterator<Clientcon> it=cclist.iterator();
                while(it.hasNext()){
                    Clientcon o=it.next();
                    o.sent(s1);
                }

}
            } catch (IOException e) {
                tb.setText("登录用户有:\n");
                Iterator<Clientcon> it=cclist.iterator();
                while(it.hasNext()){
                    Clientcon o=it.next();
                    if(o.name.equals(this.name)){
                        it.remove();
                    }
                }
                int con=1;
                it=cclist.iterator();
                while(it.hasNext()){
                    Iterator<Clientcon> it1=cclist.iterator();
                    Clientcon o=it.next();
                    try {
                        o.sent("ECD028");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    while(it1.hasNext()){
                        Clientcon m=it1.next();
                        try {
                            o.sent(con+"."+m.name+"\n");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        con++;
                    }
                    con=1;
                    try {
                        o.sent("ECD029");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    tb.append(o.name+"\n");
                }
                throw new RuntimeException(e);
            }
        }
        public void sent(String str) throws IOException {
            os =new DataOutputStream(this.s.getOutputStream());
            os.writeUTF(str);


        }
    }

    public void server() throws IOException {
        ss=new ServerSocket(8899);
        while(true) {
            Socket socket = ss.accept();
            ta.append("\n" + "一个客户端连接服务器" + socket.getInetAddress() +"\n");
            tb.setText("登录用户有:\n");
            cclist.add(new Clientcon(socket,String.valueOf(socket.getPort())));
            Iterator<Clientcon> it=cclist.iterator();
            int con=1;
            while(it.hasNext()){
                Iterator<Clientcon> it1=cclist.iterator();
                Clientcon o=it.next();
                o.sent("ECD028");
                while(it1.hasNext()){
                    Clientcon m=it1.next();
                    o.sent(con+"."+m.name+"\n");
                    con++;
                }
                o.sent("ECD029");
                con=1;
                tb.append(o.name+"\n");
            }


        }
    }
}
