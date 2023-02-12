
import java.util.Arrays;
import java.util.Scanner;


import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;


public class task {
    public static class Patient{
        String name;
        char sex;
        int age;
        float weight;
        Boolean allgeries;
        void setName( String s_name){
            name=s_name;
        }
        void setSex( char s_sex){
            sex=s_sex;
        }
        void setAge( int s_Age){
            age=s_Age;
        }
        void setWeight( float s_Weight){
            weight=s_Weight;
        }
        void setAllergies( Boolean s_all){
            allgeries=s_all;
        }
        String getName( ){
            return name;
        }
        char getSex(){
            return sex;
        }
        int getAge( ){
            return age;
        }
        float getWeight( ){
            return weight;
        }
        Boolean getAllergies( ){
            return allgeries;
        }
        @Override
        public String toString() {
            return "name=" + name + '\n' +
                    "sex=" + sex +'\n'+
                    "age=" + age +'\n'+
                    "weight=" + weight +'\n'+
                    "allergies=" + allgeries +'\n'
                    ;
        }
    }





    public static void main(String[] args){
        Patient april=new Patient();
        april.setName("zhangli");
        april.setSex('f');
        april.setAge(330);
        april.setWeight((float) 154.72);
        april.setAllergies(true);
        System.out.println("name:  "+april.getName());
        System.out.println("sex:  "+april.getSex());
        System.out.println("age:   "+april.getAge());
        System.out.println("weight:  "+april.getWeight());
        System.out.println("allergies:  "+april.getAllergies());
        System.out.println(april.toString());



    }
}
