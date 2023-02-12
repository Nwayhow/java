import java.util.Scanner;
public class Main {
    static Scanner cin=new Scanner(System.in);
    interface putout{
        public  abstract void display();

    }
    static class  note implements putout{
        String content;
        note(){
            System.out.println("put the content of notice ");
            content=cin.next();
        }
        public void display(){
            System.out.println("the content of notice :"+content);
        }

    }
    static class car implements putout{
        int rest;
        car(){
            System.out.println("put the rest of car's oil");
            rest=cin.nextInt();
        }
        public void display(){
            System.out.println("the rest of car's oil :"+rest);

        }
    }
    static class ads implements putout{
        String content;
        ads(){
            System.out.println("put the content of advertisement ");
            content=cin.next();
        }
        public void display(){
            System.out.println("the content of advertisement :"+content);
        }
    }
    public static void main(String[] args){

        car car1=new car();
        car1.display();
        ads ads1=new ads();
        ads1.display();
        note note1=new note();
        note1.display();
        car car2=new car();
        car2.display();
        ads ads2=new ads();
        ads2.display();
    }
}
