import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;//控制程序循环运行的条件
        Scanner in = new Scanner(System.in);
        while (flag) {
            try {
                int m = in.nextInt();
                int n = in.nextInt();
                if(n==0){
                    throw new InputMismatchException();//抛出异常
                }
                else{
                    System.out.println("result:"+m*1.0/n);
                    flag=false;
                }
            }catch (InputMismatchException e){//异常处理
                System.out.println("0 cannot be dived");
            }
        }
    }
}
