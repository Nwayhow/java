import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class test{
    JFrame f = new JFrame();// 创建窗体
    Container c=new Container();// 容器
    static JTextArea ta = new JTextArea(35, 40);
    static JTextArea tb = new JTextArea(35, 40);
    JFileChooser chooser=new JFileChooser(".");
    ExtensionFileFilter filter=new ExtensionFileFilter();
    static interpreter interp;
    static int Flag=0;//未被唤醒
    static int Testpoint=0;//测试点 强制跳出
    int limit;
    Count cd;
    public void way2(parser p1) throws IOException {

        // Content(包含)，pane（窗格）
        c = f.getContentPane();// 容器c嵌入窗体f中
        c.setLayout(new FlowLayout());// 布局，流布局，flow（流动）
        f.setTitle("与客服聊天");
        f.setBounds(600, 150, 400, 700);
        f.setLocationRelativeTo(null);
        //聊天记录显示区
        ta = new JTextArea(35, 30);
        tb = new JTextArea(35, 10);
        //聊天记录输入区
        ta.setEditable(false);
        tb.setEditable(false);
        JTextField tf = new JTextField(40);
        tf.requestFocus();//guang biao
        JButton jb = new JButton("发送");
        JButton jb1 = new JButton("导入");
        JButton jb3 = new JButton("重置");
        JButton jb4 = new JButton("测验");
        JButton jb5 = new JButton("删除");
        JScrollPane p=new JScrollPane(ta);
        JScrollPane p2=new JScrollPane(tb);
        c.add(p, BorderLayout.CENTER);
        c.add(p2, BorderLayout.CENTER);
        c.add(tf, BorderLayout.SOUTH);
        c.add(jb);
        c.add(jb1);
        c.add(jb3);
        c.add(jb4);
        c.add(jb5);
        ta.setText("聊天内容:"+"\n");
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        printallfile();

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Testpoint=0;
                String str = tf.getText();
                ta.append("我:"+str+"\n");
                tf.setText("");
                analyseWord(str);

            }//即监听(实时)

        });
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser=new JFileChooser(".");
                filter=new ExtensionFileFilter();
                filter.addExtension("txt");//设置文件过滤器
                chooser.addChoosableFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                int result=chooser.showOpenDialog(f);
                if(result == JFileChooser.APPROVE_OPTION)
                {
                    String path = chooser.getSelectedFile().getAbsolutePath();//绝对路径
                    File tempFile =new File( path.trim());//求文件名
                    String fileName = tempFile.getName();
                    if(isExist(fileName)){//检查文法是否已经存在
                        ta.append("该测试桩已存在,请修改文件名"+'\n');
                    }
                    else{//若不存在则开始进行导入
                        File source = new File(path);
                        File dest = new File("Test/"+fileName);
                        try {
                            copyFileUsingFileChannels(source, dest);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        ta.append(fileName+"测试桩上传成功"+'\n');
                        printallfile();
                    }

                }
            }//即监听(实时)

        });
        jb3.addActionListener(new ActionListener() {//重置按钮,用来刷新文法
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.setText("");
                tf.setText("");
                ta.setText("聊天内容"+"\n");
                interp=new interpreter(p1);

            }//即监听(实时)

        });
        jb4.addActionListener(new ActionListener() {//自动测试
            @Override
            public void actionPerformed(ActionEvent e) {
                File folder = new File("Test");
                File[] files = folder.listFiles();
                for(File file:files) {
                    try {
                        fileTest("Test/"+file.getName());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }//即监听(实时)

        });
        jb5.addActionListener(new ActionListener() {//重置按钮,用来刷新文法
            @Override
            public void actionPerformed(ActionEvent e) {

                String fileName = tf.getText();
                if(isExist(fileName) && !tf.getText ().trim ().equals ("")){
                    deleteFile(fileName);
                    ta.append("该测试桩删除完成"+'\n');
                    printallfile();

                }
                else{
                    ta.append("该测试桩不存在"+'\n');
                }

            }//即监听(实时)

        });
    }
    void fileTest(String path) throws IOException //测试文档,按行读取
    {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        Testpoint=0;
        while((line = br.readLine()) != null){
            ta.append("我:"+line+"\n");
            analyseWord(line);

        }
        Testpoint=1;
        while(cd.isAlive());

    }
    void deleteFile(String path)//删除测试桩
    {
        File folder = new File("Test");
        File[] files = folder.listFiles();
        for(File file:files) {
            if (file.getName().equals(path)) {
                file.delete();
            }
        }
    }
    void printallfile()//打印出已经拥有的测试桩
    {
        tb.setText("已有测试桩:\n");
        String path = "Test";		//要遍历的路径
        File file = new File(path);		//获取其file对象
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        for(File f:fs){					//遍历File[]数组
            if(!f.isDirectory())		//若非目录(即文件)，则打印
                tb.append(f.getName()+'\n');
        }
    }
    boolean isExist(String name){//判断测试桩是否在电脑里已经存在
        File file = new File("Test/"+name);
        return file.exists();
    }
    void analyseWord(String word)//分析每一句话,传给interpreter
    {
        if(Flag==0){//第一次进行操作
            printTime();
            Flag++;
            interp.interpreterWelcome();
            cd = new Count(interp.getExittime());
            cd.start();
        }
        if(cd.time<interp.getExittime()){
            interp.interpreterWord(word);
            cd.time=0;
        }


    }
    static class Count extends Thread {
        int time=0;
        int limit;

        Count(int lim){
            time=0;
            limit=lim;
        }
        public void run() {
            while (true) {
                try {
                    this.sleep(1000);
                    time++;
                    if((time>limit && Flag==1) || Testpoint==1){
                        printTime();
                        ta.append("机器人:");
                        interp.interpreterExit();
                        Flag=0;
                        ta.append("对话结束."+"\n");
                        ta.append("\n");
                        break;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }
    static void printTime()//打印时间
    {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        ta.append(formatter.format(date)+'\n');
    }
    void analyseTestdoc(){//分析测试桩

    }
    void way1() {
        f.setBounds(400, 200, 500, 400);// 坐标、尺寸
        // Default(默认)，Operation(操作)
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点×即关闭

    }


    test(parser p1) throws IOException {
        p1.ParseFile("current.txt");

        interp=new interpreter(p1);
        way1();// 窗体基本属性
        way2(p1);// 容器。复选框。
        f.setVisible(true);// 窗体可见
    }
    private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }
    class ExtensionFileFilter extends FileFilter {

        private ArrayList<String> extensions=new ArrayList<>();
        //自定义方法，用于添加文件后缀名
        public void addExtension(String extension) {
            if(!extension.startsWith("."))
                extension="."+extension;
            extensions.add(extension.toLowerCase());
        }
        public boolean accept(File file) {
            if(file.isDirectory()) return true;
            String name=file.getName().toLowerCase();
            for(String extension:extensions) {
                if(name.endsWith(extension)) return true;
            }
            return false;
        }
        @Override
        public String getDescription() {
            return null;
        }
    }
}