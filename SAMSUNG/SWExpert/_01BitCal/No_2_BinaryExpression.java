package SAMSUNG.SWExpert._01BitCal;

import java.util.Scanner;

/*
정수 N,M 이 주어질 때 이진수 표현의 마지막 N비트가 모두 1로 켜져있는지 아닌지를 판별하여 출력.
마지막 N비트? ㅇㅋ
* */
public class No_2_BinaryExpression {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for(int test_case =1; test_case <=T;test_case++){
            int N = sc.nextInt();
            int M = sc.nextInt();
            int testVal = (int)Math.pow(2,N);
            if(M%testVal == testVal-1)
                System.out.println("ON");
            else
                System.out.println("OFF");
            if(checkMBit(N,M)){
                System.out.println("ON");
            }else{
                System.out.println("OFF");
            }
        }
    }
    public static boolean checkMBit(int N, int M){
        int checkVal = (int)Math.pow(2,N)-1;
        return (M&checkVal) == checkVal;
    }
}
