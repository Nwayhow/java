import java.util.Scanner;
public class Main {

    static class MyThread implements Runnable {
        //适合多个数据一起处理,比如卖票
        Scanner cin=new Scanner(System.in);
        static Object obj = new Object();//不能放run()里面 否则就不是一把锁了
        private static int thing = 0;
        @Override
        public void run() {
            while (true) {
                synchronized (obj) {
                    if(Thread.currentThread().getName().equals("one") || Thread.currentThread().getName().equals("two")){
                        System.out.println(Thread.currentThread().getName()+" put");
                        int index=cin.nextInt();
                        if(index>20 || index<=0){
                            System.out.println("over limits");
                        }
                        else if(index+thing>50){

                            while(true) {
                                System.out.println("over warehouse's limits");
                                try {
                                    obj.wait();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }finally {
                                    System.out.println(Thread.currentThread().getName()+" put "+index+" again");
                                    if(index>20 || index<=0){
                                        System.out.println("over limits");
                                    }
                                    else if(thing+index>50) continue;
                                    else {
                                        System.out.println("put success");
                                        thing=thing-index;
                                        break;
                                    }
                                }
                            }
                        }
                        else {
                            System.out.println("put success");
                            thing=thing+index;
                        }
                        obj.notify();

                    }
                    if(Thread.currentThread().getName().equals("three") || Thread.currentThread().getName().equals("four")){
                        System.out.println(Thread.currentThread().getName()+" get");
                        int index=cin.nextInt();
                        if(index>20 || index<=0){
                            System.out.println("over limits");
                        }
                        else if(thing-index<0){
                            while(true) {
                                System.out.println("below warehouse's limits");
                                try {
                                    obj.wait();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }finally {
                                    System.out.println(Thread.currentThread().getName()+" get "+index+" again");
                                    if(index>20 || index<=0){
                                        System.out.println("over limits");
                                    }
                                    else if(thing-index<0) {continue;}
                                    else {
                                        System.out.println("get success");
                                        thing=thing-index;
                                        break;
                                    }
                                }
                            }
                        }

                        else {
                            System.out.println("get success");
                            thing=thing-index;
                        }

                    }
                    obj.notify();
                    System.out.println("now the total is "+thing);
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }
    public static void main(String[] args) throws InterruptedException {

        Scanner cin=new Scanner(System.in);
        MyThread t = new MyThread();
        Thread A = new Thread(t);
        Thread B = new Thread(t);
        Thread C = new Thread(t);
        Thread D = new Thread(t);
        A.setName("one");

        B.setName("two");
        C.setName("three");
        D.setName("four");
        A.start();
        Thread.sleep(100);
        B.start();
        Thread.sleep(100);
        C.start();
        Thread.sleep(100);
        D.start();

    }
}
