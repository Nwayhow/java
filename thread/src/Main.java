import java.util.Scanner;
public class Main {


    public static void main(String[] args) throws InterruptedException {
        Scanner cin=new Scanner(System.in);
        System.out.println("请输入几个数并用空格隔开：");
        String str = cin.nextLine().toString();
        String[] arr  = str.split(" ");
        int[] b = new int[arr.length];
        for(int j = 0; j<b.length;j++) {
            b[j] = Integer.parseInt(arr[j]);
        }
        final int[] average = {0};
        final int[] min = {0};
        final int[] max = {0};
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum=0;
                for(int j = 0; j<b.length;j++) {
                    sum=sum+b[j];
                }
                average[0] =sum/b.length;

            }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                min[0] =b[0];
                for(int j = 0; j<b.length;j++) {
                    if(min[0]>b[j]){
                        min[0]=b[j];
                    }
                }

            }
        });
        Thread C = new Thread(new Runnable() {
            @Override
            public void run() {
                max[0] =b[0];
                for(int j = 0; j<b.length;j++) {
                    if(max[0]<b[j]){
                        max[0]=b[j];
                    }
                }

            }
        });
        A.start();
        B.start();
        C.start();
        while(true){
            if(!A.isAlive() && !B.isAlive() && !C.isAlive()){
                System.out.println("平均值是 "+ average[0] +" 最小值是 "+ min[0] +" 最大值是 "+ max[0]);
                break;
            }
        }



    }
}
