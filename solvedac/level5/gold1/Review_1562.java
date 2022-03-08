package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Review_1562 {
    static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[10][N + 1][1024];
        //dp 초기화
        for (int i = 0; i < 10; i++) {
            dp[i][1][1<<i] = 1;
        }

        for (int i = 2; i < N+1; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024; k++) {
                    switch (j) {
                        case 0:
                            dp[0][i][k | 1] = (dp[0][i][k | 1] +dp[1][i-1][k])%MOD;
                            break;
                        case 9:
                            dp[9][i][k | (1<<9)] += (dp[9][i][k | (1<<9)] +dp[8][i-1][k])%MOD;
                            break;
                        default:
                            dp[j][i][k | (1<<j)] = (dp[j][i][k | (1<<j)] + dp[j-1][i-1][k])%MOD;
                            dp[j][i][k | (1<<j)] += (dp[j][i][k | (1<<j)] + dp[j+1][i-1][k])%MOD;
                    }
                }
            }
        }
        int ans =0;
            for (int i = 1; i < 10; i++) {
                ans += dp[i][N][1023];
                ans %= MOD;
            }
        System.out.println(ans);
    }
}
