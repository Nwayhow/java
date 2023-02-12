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
        for(int con=0;con<n;con++){
            arr[con]=cin.nextInt();
        }
        printArr(arr);
        String arr1 = Arrays.toString(arr);
        System.out.println(arr1);
    }

}
