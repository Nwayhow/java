
import java.util.Arrays;
import java.util.Scanner;


import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;


public class task {

    public static class fushu{
        Scanner cin=new Scanner(System.in);
        String num;
        int truenum;
        int flasenum;
        char i='i';
        int kind;
        fushu(){
           num=cin.nextLine();
           int l=num.length();
           if(num.charAt(0)=='-' && num.charAt(l-1)=='i' && l==2){//-i
               kind=5;
               flasenum=-1;
               truenum=0;
           }
           else if(num.charAt(l-1)=='i' && l!=1){

               unpurenum1(l);
           }
           else if(num.charAt(l-1)=='i' && l==1){//i
               kind=6;
               flasenum=1;
               truenum=0;
           }
           else{//1
               kind=7;
               truenum=Integer.parseInt(num);
               flasenum=0;
           }

        }
        void unpurenum1(int l){
            for(int count=0;count<l;count++){
                    if(num.charAt(count+1)==i){
                        if(num.charAt(count)=='-' ) kind=2;
                        else if (num.charAt(count)=='+' ) kind=1;
                        else{
                            kind=4;
                            for(int count1=1;count1<l;count1++){
                                if(num.charAt(count1)=='-' || num.charAt(count1)=='+'){
                                    kind=3;
                                    count=count1;break;
                                }
                            }
                        }
                        char f[]=num.toCharArray();
                        if(kind!=4){
                        String A=String.valueOf(f,0,count);
                        truenum=Integer.parseInt(A);}
                        else truenum=0;
                        if(kind==2) flasenum=-1;
                        else if(kind==1) flasenum=1;
                        else {
                            for(int count1=1;count1<l;count1++){
                                if(num.charAt(count1)==i){
                                    String B;
                                    if(num.charAt(count)=='+') B=String.valueOf(f,count+1,count1-count-1);
                                    else B=String.valueOf(f,count,count1-count);
                                    flasenum=Integer.parseInt(B);break;
                                }
                            }
                        }
                        break;
                    }

                }
            }

        void add(fushu b){
            int A=truenum+ b.truenum;
           int B=flasenum+b.flasenum;
           if(A==0 && B==0) System.out.println("0");
            if(A==0 && B!=0) System.out.println(B+"i");
            if(A!=0 && B==0) System.out.println(A);
            if(A!=0 && B>0) System.out.println(A+"+"+B+"i");
            if(A!=0 && B<0) {System.out.print(A);System.out.println(B+"i");}

        }
        void mul( fushu b){
            int B=truenum* b.flasenum+flasenum* b.truenum;
            int A=truenum*b.truenum-flasenum* b.flasenum;
            if(A==0 && B==0) System.out.println("0");
            if(A==0 && B!=0) System.out.println(B+"i");
            if(A!=0 && B==0) System.out.println(A);
            if(A!=0 && B>0) System.out.println(A+"+"+B+"i");
            if(A!=0 && B<0) {System.out.print(A);System.out.println(B+"i");}

        }
        void sub( fushu b){
            int A=truenum- b.truenum;
            int B=flasenum-b.flasenum;
            if(A==0 && B==0) System.out.println("0");
            if(A==0 && B!=0) System.out.println(B+"i");
            if(A!=0 && B==0) System.out.println(A);
            if(A!=0 && B>0) System.out.println(A+"+"+B+"i");
            if(A!=0 && B<0) {System.out.print(A);System.out.println(B+"i");}
        }

        @Override
        public String toString() {
            if(kind==6) return  "i";
            else if(kind==5) return "-"+i;
            else if(kind==7) return String.valueOf(truenum);
            else if(kind==1) return String.valueOf(truenum)+"+"+i;
            else if(kind==2) return String.valueOf(truenum)+"-"+i;
            else if(kind==4) return String.valueOf(flasenum)+i;
            else if(flasenum>0) return String.valueOf(truenum)+"+"+String.valueOf(flasenum)+i;
            else return String.valueOf(truenum)+String.valueOf(flasenum)+i;


        }
    }





    public static void main(String[] args){
        System.out.println("put num1");
       fushu A=new fushu();
        System.out.println("put num2");
        fushu B=new fushu();
        System.out.print(A.toString()+" + "+B.toString()+" = ");
        A.add(B);
        System.out.print(A.toString()+" * "+B.toString()+" = ");
        A.mul(B);
        System.out.print(A.toString()+" - "+B.toString()+" = ");
        A.sub(B);



    }
}
