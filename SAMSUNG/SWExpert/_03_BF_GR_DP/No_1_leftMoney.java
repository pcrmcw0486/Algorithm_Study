package SAMSUNG.SWExpert._03_BF_GR_DP;

import java.util.Scanner;

public class No_1_leftMoney {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int  T = sc.nextInt();
        for(int test_case  =1 ; test_case<=T;test_case++){
            sb.append('#').append(test_case).append("\n");
            int N = sc.nextInt();
            int bill = 10000;
            int idx =0 ;
            while(bill > 1){
                int cnt = N/bill;
                int five_cnt = cnt/5;
                int one_cnt = cnt%5;
                sb.append(five_cnt).append(' ').append(one_cnt).append(' ');
                N = N%bill;
                bill/=10;
            }

            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
