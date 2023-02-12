import java.util.Random;
public class Main {
    public static void main(String[] args){
        Random r = new Random();
        double num = r.nextDouble()+r.nextInt(99);//1~100
        System.out.println((int)num);//输出四舍五入
        double a=Math.pow(num, 1.0/2);
        System.out.println((a));//输出开方
        a=Math.pow(num, 2);
        System.out.println((a));//输出平方
        double sum=1;
        double x;
        int n=100;
        for (int i=n;i>=1;--i){
            x = 1.0;  //给定初值为1
            for(int j=i;j>=1;--j){  //根据题目从1阶乘开始计算
                x*=j;
            }
            sum+= 1.0/ x;   //进行阶乘累加求和
        }
        System.out.printf("%.10f",sum);//输出e
        System.out.println();//输出平方
        double sex = Math.sqrt(2); //45度角的 斜边长
        int k = 25; //切割次数
        int numberOfSides = 4 << k; //多边形边数
        for (int i = 0 ; i <= k; i ++){
            sex = Math.sqrt( 2 * sex * sex  + 2 * Math.sqrt(sex * sex - 1) * sex) ;
        }
        System.out.println( numberOfSides * 2  / sex );
    }

    }


