import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class Main{
    public static void main(String[] args)
    {
        JFrame frame=new JFrame("test");
        JPanel jp=new JPanel();    //创建面板
        JLabel label1=new JLabel("字体大小：");    //创建标签

        JComboBox cmb=new JComboBox();    //创建JComboBox
        cmb.addItem("--请选择--");    //向下拉列表中添加一项
        cmb.addItem("10");
        cmb.addItem("20");
        cmb.addItem("30");
        cmb.addItem("40");

        jp.add(label1);
        jp.add(cmb);
        frame.add(jp);
        frame.setBounds(300,200,400,100);
        frame.setVisible(true);
        cmb.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {     // 查看是否为新选中的选项触发
                    if (e.getItem().equals("10") ) {
                        int k=10;
                        label1.setFont(new java.awt.Font("Dialog", 0, k));
                    }
                    else if (e.getItem().equals("20") ) {
                        int k=20;
                        label1.setFont(new java.awt.Font("Dialog", 0, k));

                    }else if (e.getItem().equals("30") ){
                        int k=30;
                        label1.setFont(new java.awt.Font("Dialog", 0, k));

                    }else if (e.getItem().equals("40") ){
                        int k=40;
                        label1.setFont(new java.awt.Font("Dialog", 0, k));

                    }
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
