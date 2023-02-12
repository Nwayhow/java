import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;


public class task {
    static int search(int arr[],int x,int longs){
        for(int count=0;count<longs;count++) {
            if(arr[count]==x) return count;
        }
        return -1;
    }
    static void printArr(int arr[]) {
        for (int s : arr) {
            System.out.print(s + "\t");
        }
        System.out.println();
    }
    public static void main(String[] args){
        Scanner cin=new Scanner(System.in);
        int arr[] = new int[100];
        System.out.println(" array is:");
        for (int con = 0; con < 100; con++) {
            arr[con] = (int) (Math.random() * 100);
        }
        printArr(arr);
        System.out.println("select number is:");
        int x=cin.nextInt();
        for(int count=0;count<2;count++) {
            if(count==0) System.out.println("search:");
            if(count==1) System.out.println("binarySearch:");

            if(count==0) {
                System.out.println(search(arr,x,100));
            }
            if(count==1){
                Arrays.sort(arr);
                if(Arrays.binarySearch(arr,x)>=0) System.out.println(Arrays.binarySearch(arr,x));
                else System.out.println(("-1"));

                }

            }


        }
    }


