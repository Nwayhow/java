import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

class manage{
    JFrame f = new JFrame();// 创建窗体
    Container c;// 容器
    JFileChooser chooser=new JFileChooser(".");
    ExtensionFileFilter filter=new ExtensionFileFilter();
    static JTextArea ta;
    static JTextArea tb;

    public void way2(parser p1) throws IOException {

        // Content(包含)，pane（窗格）
        c = f.getContentPane();// 容器c嵌入窗体f中
        c.setLayout(new FlowLayout());// 布局，流布局，flow（流动）
        f.setTitle("修改文法导入");
        f.setBounds(600, 150, 400, 700);
        f.setLocationRelativeTo(null);
        //聊天记录显示区
        ta = new JTextArea(10, 40);
        tb = new JTextArea(25, 40);
        //聊天记录输入区
        ta.setEditable(false);
        JTextField tf = new JTextField(40);
        tf.requestFocus();//guang biao
        JButton jb = new JButton("导入文法");
        JButton jb1 = new JButton("删除文法");
        JButton jb2 = new JButton("查看+选择文法");
        JScrollPane p=new JScrollPane(ta);
        c.add(p, BorderLayout.CENTER);
        p=new JScrollPane(tb);
        c.add(p, BorderLayout.CENTER);
        c.add(tf, BorderLayout.SOUTH);
        c.add(jb);
        c.add(jb1);
        c.add(jb2);
        ta.setText("聊天内容"+"\n");
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        printallfile();
        jb.addActionListener(new ActionListener() {
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
                        tb.append("该文法已存在,请修改文件名"+'\n');
                    }
                    else{//若不存在则开始进行导入
                        File source = new File(path);
                        File dest = new File("grammer/"+fileName);
                        try {
                            copyFileUsingFileChannels(source, dest);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        tb.append(fileName+"上传成功"+'\n');
                        printallfile();
                    }

                }


            }//即监听(实时)

        });
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fileName = tf.getText();
                if(isExist(fileName) && !tf.getText ().trim ().equals ("")){
                    deleteFile(fileName);
                    tb.append("该文法删除完成"+'\n');
                    printallfile();

                }
                else{
                    tb.append("该文法不存在"+'\n');
                }

            }//即监听(实时)

        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = tf.getText();
                if(isExist(fileName) && !tf.getText ().trim ().equals ("")){
                    tb.append(fileName+"内容为:"+'\n');
                    try {
                        readFile("grammer/"+fileName);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    File source = new File("grammer/"+fileName);
                    File dest = new File("current.txt");

                    int jud= 0;
                    try {
                        jud = p1.ParseFile("grammer/"+fileName);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    if(jud==0) {
                        tb.append("选择该文法成功!" + '\n');
                        try {
                            copyFileUsingFileChannels(source, dest);//把文件拷贝到current.txt
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else{
                        tb.append("选择该文法失败!" + '\n');
                        judge(jud);//判断失败原因
                    }
                }
                else{
                    tb.append("文件"+fileName+" 不存在"+'\n');
                }

            }//即监听(实时)

        });

    }

    void judge(int i){
        int line=i/100;
        int error=i-line*100;
        if (error==10){
            tb.append("在第" +line+"行"+"出现错误:"+"行数过多"+ '\n');
        }
        else if (error==1){
            tb.append("在第" +line+"行"+"出现错误:"+"非法关键字"+ '\n');
        }
        else if (error==2){
            tb.append("在第" +line+"行"+"出现错误:"+"关键词错误"+ '\n');
        }
        else if (error==3){
            tb.append("在第" +line+"行"+"出现错误:"+"process后不只跟着一个函数"+ '\n');
        }
        else if (error==4){
            tb.append("在第" +line+"行"+"出现错误:"+"使用变量未定义"+ '\n');
        }
        else if (error==5){
            tb.append("在第" +line+"行"+"出现错误:"+"语句没用\"\"包围"+ '\n');
        }
        else if (error==7){
            tb.append("在第" +line+"行"+"出现错误:"+"exit格式错误"+ '\n');
        }
        else if (error==8){
            tb.append("在第" +line+"行"+"出现错误:"+"声明变量已存在"+ '\n');
        }
        else if (error==9){
            tb.append("在第" +line+"行"+"出现错误:"+"exit后面没有跟着整数"+ '\n');
        }
        else if (error==6){
            tb.append("在第" +line+"行"+"出现错误:"+"声明变量不规范"+ '\n');
        }
        else if (error==11){
            tb.append("在第" +line+"行"+"出现错误:"+"函数未声明或不规范"+ '\n');
        }
        else if (error==12){
            tb.append("在第" +line+"行"+"出现错误:"+"branch格式错误"+ '\n');
        }
        else if (error==13){
            tb.append("在第" +line+"行"+"出现错误:"+"branch中的词重复出现"+ '\n');
        }
        else if (error==14){
            tb.append("在第" +line+"行"+"出现错误:"+"unknown出现多次"+ '\n');
        }
        else if (error==15){
            tb.append("在第" +line+"行"+"出现错误:"+"welcome出现多次"+ '\n');
        }
        else if (error==16){
            tb.append("在第" +line+"行"+"出现错误:"+"exit出现多次"+ '\n');
        }
        else if (error==17){
            tb.append("在第" +line+"行"+"出现错误:"+" 函数名和关键词冲突"+ '\n');
        }
        else if (error==18){
            tb.append("在第" +line+"行"+"出现错误:"+" speak 没有跟语句"+ '\n');
        }
        else if (error==19){
            tb.append("在第" +line+"行"+"出现错误:"+"同一句branch出现过多次"+ '\n');
        }
    }
    boolean isExist(String name){//判断文法是否在电脑里已经存在
        File file = new File("grammer/"+name);
        return file.exists();
    }
    void deleteFile(String path)//删除文法
    {
        File folder = new File("grammer");
        File[] files = folder.listFiles();
        for(File file:files) {
            if (file.getName().equals(path)) {
                file.delete();
            }
        }
    }
    void readFile(String fileName) throws IOException //打印出选择的文法
    {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int count=0;
        while((line = br.readLine()) != null){
            count++;
            // 一行一行地处理...
            tb.append(count+": "+line+'\n');
        }

    }
    void printallfile()//打印出已经拥有的文法
    {
        ta.setText("已有文法:\n");
        String path = "grammer";		//要遍历的路径
        File file = new File(path);		//获取其file对象
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        for(File f:fs){					//遍历File[]数组
            if(!f.isDirectory())		//若非目录(即文件)，则打印
                ta.append(f.getName()+'\n');
        }
    }
    private static void copyFileUsingFileChannels(File source, File dest) throws IOException //拷贝选择成功的代码到程序内
    {//拷贝文件到程序内
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


    void way1() {
        f.setBounds(400, 200, 500, 400);// 坐标、尺寸
        // Default(默认)，Operation(操作)
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点×即关闭

    }
    static class ExtensionFileFilter extends FileFilter {

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

    manage(parser p) throws IOException {
        way1();// 窗体基本属性
        way2(p);// 容器。复选框。
        f.setVisible(true);// 窗体可见
    }
}