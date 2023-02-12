import java.util.Arrays;
import java.util.Scanner;


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
        System.out.println(("put the the members of array"));
            int arr[] = new int[n];
            for (int con = 0; con < n; con++) {
                arr[con] = cin.nextInt();
            }
        System.out.println(("put the the length of second"));
        m=cin.nextInt();
        System.out.println(("put the the members of array"));
        int arr1[] = new int[m];
        for (int con = 0; con < m; con++) {
            arr1[con] = cin.nextInt();
        }
            if(compare(arr,arr1)==1) System.out.println(("same"));
            else System.out.println(("different"));
        }
    }


