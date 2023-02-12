import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Scanner;
import static java.lang.Thread.holdsLock;
import static java.lang.Thread.sleep;
public class Main {
    static class MyThread implements Runnable {
        //适合多个数据一起处理,比如卖票
        private int ticket = 100;
        Object control = new Object();//不能放run()里面 否则就不是一把锁了


        @Override
        public void run() {
            synchronized (control) {
                System.out.println(Thread.currentThread().getName() + " is start");
                try {
                    control.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " is end");
            }

        }

    }

    //isAlive ()判断线程是否还活着
    //Tread.currentThread().getPriority 获取优先级 set就是设置优先级 优先级高只能说说明有更高的概率先发生
    //Tread.currentThread().getName 获取姓名 set即设置姓名
    //优先级有MAX_PRIORITY:10  MIN_PRIORITY:1 NORM_PRIORITY:5(默认)
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread t = new MyThread();
        Thread A = new Thread(t);
        Thread B = new Thread(t);


        A.setName("A");
        B.setName("B");
        A.start();
        B.start();
        Thread C = new Thread(new MyThread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (t.control) {


                    t.control.notifyAll();
                }
            }
        });
        C.start();






    }
}