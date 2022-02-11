package RHS_FC.class09_dp;

/*
2021.10.21
문제번호 : Q11066
이름 및 난이도 : 파일합치기 Gold III
문제이해 
-----------------
SolveByCategory.dp[i][j] : i~j 까지 연속된 방법으로 합쳤을 때 가장 최소값을 가지는.
 SolveByCategory.dp[i][j]  = Math.min(SolveByCategory.dp[i][k] + SolveByCategory.dp[k][j])
접근 방법 :
제한 조건 : 
*/
import java.io.*;
import java.util.*;

public class Q11066 {
    static int[][] dp;
    static int[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            dp = new int[K + 1][K + 1];
            sum = new int[K + 1][K + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < K + 1; i++) {
                sum[i][i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i < K + 1; i++) 
                for (int j = i + 1; j < K + 1; j++) 
                    sum[i][j] += sum[i][j - 1] + sum[j][j];

            for(int len = 2; len < K+1;len++){
                for(int i = 1;i<=K-len+1;i++){
                    int j = i + len -1;
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int k = i;k<j;k++){
                        dp[i][j] = Math.min(dp[i][j],dp[i][k] + dp[k+1][j] + sum[i][j]);
                    }
                }
            }
            sb.append(dp[1][K]).append('\n');
        } // iteration for Test
        System.out.print(sb);
    }

}
