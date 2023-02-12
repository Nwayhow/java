import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
public class Main  {

    JFrame f=new JFrame();// 创建窗体
    Container c;// 容器
    static JTextField ta;
    static JTextField tb;
    static JTextField tc;
    static JTextField td;
    static JTextField te;
    static JTextField tf;
    static JTextField tg;
    static JTextField th;
    static JTextField ti;
    static JTextField tj;
    JLabel stu;
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
    void way1(){
        c = f.getContentPane();// 容器c嵌入窗体f中
        c.setLayout(new FlowLayout());// 布局，流布局，flow（流动）
        f.setTitle("拍卖");
        f.setLocationRelativeTo(null);
        JLabel t1=new JLabel();
        JLabel t2=new JLabel();
        t1.setText("总拍卖轮数");
        t2.setText("文物总数量");
        JLabel t3=new JLabel();
        JLabel t4=new JLabel();
        t3.setText("快消品数量");
        t4.setText("奢侈品数量");
        JLabel t5=new JLabel();
        JLabel t6=new JLabel();
        t5.setText("拍卖者余额");
        t6.setText("文物起步价");
        JLabel t7=new JLabel();
        JLabel t8=new JLabel();
        t7.setText("快消品起步价格");
        t8.setText("奢侈品价格下限");
        JLabel t9=new JLabel();
        JLabel t10=new JLabel();
        t9.setText("奢侈品价格上限");
        t10.setText("隐藏属性的维度");
        stu=new JLabel();
        stu.setText("未模拟");
        //聊天记录输入区
        ta = new JTextField(10);
        ta.setText("10");
        tb= new JTextField(10);
        tb.setText("8");
        tc = new JTextField(10);
        tc.setText("8");
        td= new JTextField(10);
        td.setText("8");
        te= new JTextField(10);
        te.setText("1000000");
        tf = new JTextField(10);
        tf.setText("1000");
        tg = new JTextField(25);
        tg.setText("1000");
        th = new JTextField(25);
        th.setText("10000");
        ti = new JTextField(25);
        ti.setText("20000");
        tj = new JTextField(25);
        tj.setText("8");
        tf.requestFocus();//guang biao
        JButton jb = new JButton("模拟");
        JButton ja = new JButton("结果");
        c.add(t1);
        c.add(ta);
        c.add(t2);
        c.add(tb);
        c.add(t3);
        c.add(tc);
        c.add(t4);
        c.add(td);
        c.add(t5);
        c.add(te);
        c.add(t6);
        c.add(tf);
        c.add(t7);
        c.add(tg);
        c.add(t8);
        c.add(th);
        c.add(t9);
        c.add(ti);
        c.add(t10);
        c.add(tj);

        c.add(jb);
        c.add(stu);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jb.addActionListener(new ActionListener() {//注册
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isNumeric(ta.getText()) &&  isNumeric(tb.getText()) && isNumeric(tc.getText()) &&  isNumeric(td.getText()) && isNumeric(te.getText()) &&  isNumeric(tf.getText()) && isNumeric(tg.getText())&&  isNumeric(th.getText()) && isNumeric(ti.getText()) && isNumeric(tj.getText())){
                    try {
                        paimai pai=new paimai(Integer.parseInt(tj.getText()) ,Integer.parseInt(tb.getText()) ,Integer.parseInt(tc.getText()) ,Integer.parseInt(td.getText()),Integer.parseInt(ta.getText()) ,Integer.parseInt(te.getText()),Integer.parseInt(tf.getText()),Integer.parseInt(tg.getText()),Integer.parseInt(th.getText()),Integer.parseInt(ti.getText()));
                    pai.start();
                    pai.run();
                    pai.show();
                    pai.clear();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    stu.setText("存在非法输入");
                }
            }//即监听(实时)

        });


    }
    public void way2(){
        // Content(包含)，pane（窗格）
        c=f.getContentPane();// 容器c嵌入窗体f中
        c.setLayout(new FlowLayout());// 布局，流布局，flow（流动）
        f.setTitle("模拟拍卖(单位为元)");
        f.setBounds(600, 550, 380, 330);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) throws IOException{
        Main d=new Main();
        d.way1();// 窗体基本属性
        d.way2();// 容器。复选框。
       // paimai pai=new paimai(m,k1,k2,k3,n,money,Aprice,Fprice,Lprice1,Lprice2);
        //pai.start();
       // pai.run();
       // pai.show();
    }

}
class paimai{
    static int m;
    static int k1;
    static int k2;
    static int k3;
    static int n;
    static int money;
    static int Aprice;
    static int Fprice;
    static int Lprice1;
    static int Lprice2;
    static define D;
    JFrame f = new JFrame();// 创建窗体
    Container c;// 容器
    static JTextArea ta;
    static JTextArea tb;
    static ArrayList<define.thing> thinglist=new ArrayList<>();
    static ArrayList<define.people> peoplelist=new ArrayList<>();
   void clear(){
        thinglist.clear();
        peoplelist.clear();
    }
    void way1() {
        f.setBounds(400, 200, 500, 400);// 坐标、尺寸
        // Default(默认)，Operation(操作)
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点×即关闭
    }
    public void way2() throws IOException {
        // Content(包含)，pane（窗格）
        c=f.getContentPane();// 容器c嵌入窗体f中
        JLabel t1=new JLabel();
        JLabel t2=new JLabel();
        t1.setText("拍卖过程");
        t2.setText("物品价值和流拍率");
        c.setLayout(new FlowLayout());// 布局，流布局，flow（流动）
        f.setTitle("拍卖过程");
        f.setBounds(600, 150, 400, 700);
        f.setLocationRelativeTo(null);
        ta = new JTextArea(10, 40);
        ta.setEditable(false);
        JScrollPane p=new JScrollPane(ta);
        tb = new JTextArea(25, 40);
        tb.setEditable(false);
        JScrollPane q=new JScrollPane(tb);
        c.add(t1);
        c.add(q, BorderLayout.CENTER);
        c.add(t2);
        c.add(p, BorderLayout.CENTER);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                f.dispose();
            }
        });


    }
    paimai(int m,int k1,int k2,int k3,int n,int money,int Aprice,int Fprice,int Lprice1,int Lprice2) throws IOException {

        this.m=m;
        this.k1=k1;
        this.k2=k2;
        this.k3=k3;
        this.n=n;
        this.money=money;
        this.Aprice=Aprice;
        this.Fprice=Fprice;
        this.Lprice1=Lprice1;
        this.Lprice2=Lprice2;
        D=new define(m,money,Aprice,Fprice,Lprice1,Lprice2,n);
        way1();// 窗体基本属性
        way2();// 容器。复选框。

        f.setVisible(true);// 窗体可见
    }
    void start(){
        for(int con=0;con<n;con++){
            define.people d=D.new people(con);//
            peoplelist.add(d);
        }

        for( int con1=0;con1<k1;con1++){
            define.Artifacts d=D.new Artifacts(con1);
            thinglist.add(d);
        }
        for(int  con1=0;con1<k2;con1++){
            define.FMCG d=D.new FMCG(con1+k1);
            thinglist.add(d);
        }
        for(int con1=0 ;con1<k3;con1++){
            define.Luxury d=D.new Luxury(con1+k1+k2);
            thinglist.add(d);
        }
    }
    
    void  run(){//sort
        tb.setText("");
        for(int con=0;con<n;con++){
            Iterator<define.thing> it=thinglist.iterator();
            tb.append("第"+(con+1)+"轮拍卖开始"+"\n");
            while(it.hasNext()){
                define.people own = null;
                define.thing o=it.next();
                Iterator<define.people> it2=peoplelist.iterator();
                if(o.miss!=n) continue;
                while(it2.hasNext()){//remove
                    define.people q=it2.next();
                    if(buyornot(o,q)==1){
                        o.miss=con;
                        o.price= (int) (q.p*(q.mmoney-o.price)+o.price);
                        tb.append("客户"+q.num+"号为"+o.num+"号物品出价"+o.price+"元"+"\n");
                        own=q;
                    }
                }
            if (own!=null) {
                tb.append("客户"+own.num+"号成功拍下"+o.num+"号物品 价格为"+o.price+"元"+"\n");
                own.mmoney= own.mmoney-o.price;
                o.master=own.num;
                tb.append("买主客户"+own.num+"号的余额为"+own.mmoney+"元"+"\n");
            }
            }
        }

    }
    void show(){
        ta.setText("");
        Iterator<define.thing> it=thinglist.iterator();
        while(it.hasNext()){
            define.thing o=it.next();
            if(o.kind==1) ta.append(o.num+"号物品的类型为"+"文物"+"\n");
            if(o.kind==2) ta.append(o.num+"号物品的类型为"+"快消品"+"\n");
            if(o.kind==3) ta.append(o.num+"号物品的类型为"+"奢侈品"+"\n");
            if(o.miss==n) ta.append("流拍率为100%"+"\n");
            else ta.append("流拍率为"+(double)o.miss*100/(o.miss+1)+"%"+",价格为"+o.price+"元"+"\n");
            if(o.master!=-1) ta.append("物品的买主为"+o.master+"号"+"\n");
            else ta.append("物品未被拍下"+"\n");
        }
    }
    int buyornot(define.thing o,define.people q){
        if(o.price>=q.mmoney) return 0;
        double summ=0;
        double rate;
        for(int con=0;con<m;con++){
            System.out.println(con);
            summ=summ+(double)o.check[con]*q.check[con];
        }
        rate=summ/(q.sum*o.sum);
        rate=rate*o.willing*q.mmoney/money;
        Random r = new Random();
        if(r.nextDouble()<rate) return 1;
        else return 0;
    }


}
class define{
    define(int num,int num2,int num3,int num4,int num5,int num6,int num7){
        m=num;
        money=num2;
        Aprice=num3;
        Fprice=num4;
        Lprice1=num5;
        Lprice2=num6;
        n=num7;
    }
     int m;
    int n;
    int money;
    int Aprice;
    int Fprice;
    int Lprice1;
    int Lprice2;
     class thing{
        Random r = new Random();
        int num;
        int master=-1;
        int price;
        int year;
        int kind;
        double sum=0;
        double willing;
        int miss=n;
        double check[]=new double[m];
        void initcheck(){
            for(int con=0;con<m;con++){
                check[con]=r.nextDouble();
            }
            for(int con=0;con<m;con++){
                sum=sum+check[con]*check[con];
            }
            sum=Math.pow(sum, 1.0/2);
        }

    }
    class people{
        Random r = new Random();
        int num;
        double p;
        int mmoney;
        double sum=0;
        double check[]=new double[m];
        people(int num){
            p=r.nextDouble();
            mmoney=money;
            this.num=num;
            initcheck();
        }
        void initcheck(){
            for(int con=0;con<m;con++){
                check[con]=r.nextDouble();
            }
            for(int con=0;con<m;con++){
                sum=sum+check[con];
            }
            sum=Math.pow(sum, 1.0/2);
        }
    }
     class Artifacts extends thing {
        Artifacts(int num){
            this.num=num;
            kind=1;
            year=r.nextInt(450);
            year=year+50;
            price=Aprice;
            price=price*year;

            willing=(1-(double)year/500);
            initcheck();
        }
    }
    class FMCG extends thing {
        FMCG(int num){
            this.num=num;
            kind=2;
            year=r.nextInt(10);
            while(year==0){
                year=r.nextInt(10);
            }
            price=Fprice/year;
            willing=(double)year/10;
            initcheck();
        }
    }
    class Luxury  extends thing {
        Luxury(int num){
            this.num=num;
            kind=3;
            price=r.nextInt(Lprice2-Lprice1);
            price=Lprice1+price;
            year=r.nextInt(99);
            willing=(double)price/(Lprice2);
            initcheck();
        }
    }
}