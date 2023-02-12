import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;


public class task {
    static void printArr(int arr[]) {
        for (int s : arr) {
            System.out.print(s + "\t");
        }
        System.out.println();
    }
    static void select(int arr[],int bon){
        for(int count=0;count-1<bon;count++){
            if(arr[count]>=arr[bon]) {
                move(arr,count,bon);break;}
        }

    }
    static void bud(int arr[],int bon){
        for(int count=0;count<=bon-1;count++){
            if(arr[count]>arr[count+1]){
                int t=arr[count+1];
                arr[count+1]=arr[count];
                arr[count]=t;
            }
        }

    }

    static void move(int arr[],int left,int right){
        int target=arr[right];
        for(int count=right-1;count>=left;count--){
            arr[count+1]=arr[count];
        }
        arr[left]=target;

    }
    public static void main(String[] args){
        Scanner cin=new Scanner(System.in);
        for(int count=0;count<3;count++) {
            if(count==0) System.out.println("sort:");
            if(count==1) System.out.println("select:");
            if(count==2) System.out.println("buble:");
            int arr[] = new int[100];
            for (int con = 0; con < 100; con++) {
                arr[con] = (int) (Math.random() * 100);
            }
            printArr(arr);
            if(count==0) Arrays.sort(arr);
            if(count==1){
                for(int bound=1;bound<=99;bound++){
                    select(arr,bound);
                }

            }
            if(count==2){
                for(int bound=99;bound>0;bound--){
                    bud(arr,bound);
                }
            }
            printArr(arr);
        }
    }

}
