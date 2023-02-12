import java.util.Arrays;
import java.util.Scanner;


import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;


public class task {
    static void printArr1(int arr[][]) {
        for (int con = 0; con < 100; con++) {
            for (int con1 = 0; con1 < 100; con1++) {
                System.out.print(arr[con][con1]+" ");
                } System.out.println();
        }

    }
    static void printArr(int arr[]) {
        for (int s : arr) {
            System.out.print(s + "\t");
        }
        System.out.println();
    }
    static int compare(int arr[],int arr1[]){
        if(arr.length!=arr1.length) return -1;
        else for(int count=0;count<arr.length;count++){
            if(arr[count]!=arr1[count]) return -1;

        }
        return 1;

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
        int n,m;
        System.out.println(("put the the length of first"));
        n=cin.nextInt();
        int arr[]=new int[n];
        System.out.println(("put the the member of array"));
        m=cin.nextInt();
       Arrays.fill(arr,m);
       printArr(arr);
       int arr1[][]=new int[100][100];
        for (int con = 0; con < 100; con++) {
            for (int con1 = 0; con1 < 100; con1++) {
            arr1[con][con1] = (int) (Math.random() * 100);}
        }
        int arr2[]=new int[100*100];
        for(int count=0;count<100;count++){
            System.arraycopy(arr1[count],0,arr2,count*100,100);
        }
        printArr(arr2);

        Arrays.sort(arr2);

        for(int count=0;count<100;count++){
            System.arraycopy(arr2,count*100,arr1[count],0,100);
        }
        printArr1(arr1);
        //printArr1(arr1);


    }
}


