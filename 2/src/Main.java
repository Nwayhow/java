
import java.util.Scanner;
import java.util.Arrays;
public class Main {
    static void printArr(int arr[]) {
        for (int s : arr) {
            System.out.print(s + "\t");
        }
        System.out.println();
    }


    public static void main(String[] args){
        Scanner cin=new Scanner(System.in);
        int n=cin.nextInt();
        int arr[]=new int[n];
        int arr1[]=new int[n];
        for(int con=0;con<n;con++){
            arr[con]=cin.nextInt();
        }
        System.arraycopy(arr, 0, arr1, 0, n);
        int[] arr2=Arrays.copyOfRange(arr, 0, n);//dont include n
        int[] arr3 = Arrays.copyOf(arr, n);//start with 0
        printArr(arr);
        printArr(arr1);
        printArr(arr2);
        printArr(arr3);


    }

}
