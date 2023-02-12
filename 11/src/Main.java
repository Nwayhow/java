import java.awt.*;
import java.awt.event.ActionListener;// 监听器
import java.awt.event.ActionEvent;// 事件
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

class Main{
    JFrame f=new JFrame();// 创建窗体
    Container c;// 容器
    JFileChooser chooser=new JFileChooser(".");
    ExtensionFileFilter filter=new ExtensionFileFilter();

    void way2(){

        // Content(包含)，pane（窗格）
        c=f.getContentPane();// 容器c嵌入窗体f中
        c.setLayout(new FlowLayout());// 布局，流布局，flow（流动）
        f.setTitle("图形用户界面");
        f.setBounds(600, 150, 500, 150);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        //创建菜单
        JMenuBar jmb = new JMenuBar();
        //不能设定位置，会自动放在最上部
        f.setJMenuBar(jmb);
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Type");
        JMenuItem item1 = new JMenuItem("打开");
        JMenuItem item2 = new JMenuItem("退出");
        JCheckBox c_box_1=new JCheckBox("*");// 复选框，check(打勾)
        JCheckBox c_box_2=new JCheckBox("DOC和DOCX");// check(打勾)
        JCheckBox c_box_3=new JCheckBox("TXT");// check(打勾)
        //添加菜单项至菜单上
        menu1.add(item1);
        menu1.add(item2);
        menu2.add(c_box_1);
        menu2.add(c_box_2);
        menu2.add(c_box_3);
        c_box_1.setSelected(true);
        //将菜单加入至菜单条
        jmb.add(menu1);
        jmb.add(menu2);

        c_box_1.setSelected(true);// 默认选中，Selected(选中)。
        // action(动作)，listener(监听)
        c_box_1.addActionListener(new ActionListener(){// 增加监听动作
            // performed(执行)
            public void actionPerformed(ActionEvent e){
               // 满足 动作 事件 的选择后触发以下事件
                chooser=new JFileChooser(".");
                filter=new ExtensionFileFilter();
                if(c_box_1.isSelected()){
                    chooser.setAcceptAllFileFilterUsed(true);
                }
                else if(c_box_2.isSelected() && c_box_3.isSelected()){

                    filter.addExtension("doc");
                    filter.addExtension("docx");
                    filter.addExtension("txt");
                    chooser.addChoosableFileFilter(filter);
                    chooser.setAcceptAllFileFilterUsed(false);
                }
                else if(c_box_2.isSelected() ){
                    filter.addExtension("doc");
                    filter.addExtension("docx");
                    chooser.addChoosableFileFilter(filter);
                    chooser.setAcceptAllFileFilterUsed(false);

                }
                else if(c_box_3.isSelected()){
                    filter.addExtension("txt");
                    chooser.addChoosableFileFilter(filter);
                    chooser.setAcceptAllFileFilterUsed(false);
                }

            }
        });
        c_box_2.addActionListener(new ActionListener(){// 增加监听动作
            // performed(执行)
            public void actionPerformed(ActionEvent e){// 满足 动作 事件 的选择后触发以下事件
                chooser=new JFileChooser(".");
                filter=new ExtensionFileFilter();
                if(c_box_1.isSelected()){
                    chooser.setAcceptAllFileFilterUsed(true);
                }
                else if(c_box_2.isSelected() && c_box_3.isSelected()){

                    filter.addExtension("doc");
                    filter.addExtension("docx");
                    filter.addExtension("txt");
                    chooser.addChoosableFileFilter(filter);
                    chooser.setAcceptAllFileFilterUsed(false);
                }
                else if(c_box_2.isSelected() ){
                    filter.addExtension("doc");
                    filter.addExtension("docx");
                    chooser.addChoosableFileFilter(filter);
                    chooser.setAcceptAllFileFilterUsed(false);

                }
                else if(c_box_3.isSelected()){
                    filter.addExtension("txt");
                    chooser.addChoosableFileFilter(filter);
                    chooser.setAcceptAllFileFilterUsed(false);
                }

            }
        });
        c_box_3.addActionListener(new ActionListener(){// 增加监听动作
            // performed(执行)

            public void actionPerformed(ActionEvent e){// 满足 动作 事件 的选择后触发以下事件
                chooser=new JFileChooser(".");
                filter=new ExtensionFileFilter();
                if(c_box_1.isSelected()){
                    chooser.setAcceptAllFileFilterUsed(true);
                }
                else if(c_box_2.isSelected() && c_box_3.isSelected()){

                    filter.addExtension("doc");
                    filter.addExtension("docx");
                    filter.addExtension("txt");
                    chooser.addChoosableFileFilter(filter);
                    chooser.setAcceptAllFileFilterUsed(false);
                }
                else if(c_box_2.isSelected() ){
                    filter.addExtension("doc");
                    filter.addExtension("docx");
                    chooser.addChoosableFileFilter(filter);
                    chooser.setAcceptAllFileFilterUsed(false);
                }
                else if(c_box_3.isSelected()){
                    filter.addExtension("txt");
                    chooser.addChoosableFileFilter(filter);
                    chooser.setAcceptAllFileFilterUsed(false);
                }

            }
        });
        item1.addActionListener(new ActionListener(){// 增加监听动作
            // performed(执行)
            public void actionPerformed(ActionEvent e){// 满足 动作 事件 的选择后触发以下事件
                String str = e.getActionCommand();
                if("打开".equals(str)) {
                    int result=chooser.showOpenDialog(f);

                }  }
        });
        item2.addActionListener(new ActionListener(){// 增加监听动作
            // performed(执行)
            public void actionPerformed(ActionEvent e){
                String str = e.getActionCommand();
                if("退出".equals(str)) {
                    System.exit(0);
                }

            }
        });

    }

    void way1(){
        f.setBounds(400,200,500,400);// 坐标、尺寸
        // Default(默认)，Operation(操作)
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点×即关闭

    }

    public static void main(String[] args) {
        Main d=new Main();
        d.way1();// 窗体基本属性
        d.way2();// 容器。复选框。

        d.f.setVisible(true);// 窗体可见
    }
    class ExtensionFileFilter extends FileFilter{

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

