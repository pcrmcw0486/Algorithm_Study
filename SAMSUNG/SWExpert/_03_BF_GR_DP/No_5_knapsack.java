package SAMSUNG.SWExpert._03_BF_GR_DP;

import java.util.Scanner;

public class No_5_knapsack {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int T =sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1 ; test_case<=T;test_case++){
            sb.append('#').append(test_case).append(' ');
            int N = sc.nextInt();
            int K = sc.nextInt();
            //담을 수 있는 물건의 기준은 ? 가치가 아니라 무게입니다. 그 다음 고려할 것이 가치가 되겠지요.
            // 그렇다면 무게부터 생각해봅시다.
            //
            int[] dp = new int[K+1];
            for(int i =0;i<N;i++){
                int V = sc.nextInt(); //부피
                int C = sc.nextInt(); // 가치
                for(int weight = K;weight>=V;weight--){
                    dp[weight] = Math.max(dp[weight],dp[weight-V]+C);//넣어주거나 안 넣는 행위
                }
            }
            sb.append(dp[K]).append('\n');
        }
        System.out.print(sb.toString());
    }
}
