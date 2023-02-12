import java.util.Arrays;
import java.util.Scanner;


import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;


public class task {
    static double f(double n) {
        int x=1;
        for(int c=1;c<=n;c++){
            x=x*c;
        }
        return x;


    }

    public static void main(String[] args){
        Scanner cin=new Scanner(System.in);
        System.out.println("method 1:");
        double x=0;
        for(double count=1;count<=Math.pow(10,8);count++){
            x=Math.pow(1+1/count,count);
        }
        System.out.println(x);
        System.out.println("method 2:");
        x=1;
        for(double count=1;count<=30;count++){
            x=x+1/f(count);
        }
        System.out.println(x);
        //当阶乘过大时会超过double自己的范围。



    }
}
