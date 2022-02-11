package SAMSUNG.SWExpert._03_BF_GR_DP;

import java.util.Scanner;

public class No_4_LCS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case<=T;test_case++){
            sb.append('#').append(test_case).append(' ');
            char[] textA = sc.next().toCharArray();
            char[] textB = sc.next().toCharArray();
            int[][] dp = new int[textA.length+1][textB.length+1];
            //dp[i][j]는 A문자열 i번째까지와 B문자열 j번째 까지의 최장 공통 수열임.
            // if textA[i] == textB[j] 번째 가 같다면 dp[i][j] = dp[i-1][j-1] +1;
            //else dp[i][j] = dp[i-1][j] , dp[i][j-1]
            for(int i =1 ;i<textA.length+1;i++){
                for(int j = 1;j<textB.length+1;j++){
                    if(textA[i-1] == textB[j-1]){
                        dp[i][j] = dp[i-1][j-1]+1;
                    }else{
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
            sb.append(dp[textA.length][textB.length]).append('\n');
        }
        System.out.print(sb.toString());
    }
}
