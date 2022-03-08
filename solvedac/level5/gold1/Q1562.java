package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @문제번호 : Q1562
 * @문제이름 : 계단수
 * @난이도 : Gold I
 * @date : 2022-02-27 오후 5:36
 * @author : pcrmcw0486
 * @문제이해
 * 인접한 모든 자리의 차이가 1인수를 계단수라고 한다.
 * N이 주어질 때, N이면서 0~9까지 숫자가 모두 등장하는 계단수가 총 몇개인가?
 * 0으로 시작하는 수는 계단수가 아니다.
 * @알고리즘
 * dp.
 * @접근방법
*/
public class Q1562 {
    static int MOD =  1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[101][10][1024];
        for(int i = 0;i<10;i++) dp[1][i][1<<i] = 1;
        for (int i = 2; i < 101; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024;k++) {
                    if(j==0) dp[i][j][k|(1<<j)] = (dp[i][j][k|(1<<j)] + dp[i-1][1][k])%MOD;
                    else if(j==9) dp[i][j][k|(1<<j)] = (dp[i][j][k|(1<<j)] + dp[i-1][8][k])%MOD;
                    else{
                        dp[i][j][k|(1<<j)] = (dp[i][j][k|(1<<j)] + dp[i - 1][j + 1][k])%MOD;
                        dp[i][j][k|(1<<j)] = (dp[i][j][k|(1<<j)] + dp[i - 1][j - 1][k])%MOD;
                    }
                }
            }
        }

        int ans =0;
        for (int i = 1; i < 10; i++) {
            ans += dp[N][i][1023];
            ans%=MOD;
        }
        System.out.println(ans);
    }
//    public static int solution(int SIZE) {
//        int[][][] dp = new int[SIZE+1][10][1024];
//        for(int i = 0;i<10;i++) dp[1][i][1<<i] = 1;
//        for (int i = 2; i < SIZE+1; i++) {
//            for (int j = 0; j < 10; j++) {
//                for (int k = 0; k < 1024;k++) {
//                    if(j==0) dp[i][j][k|(1<<j)] = (dp[i][j][k|(1<<j)] + dp[i-1][1][k])%MOD;
//                    else if(j==9) dp[i][j][k|(1<<j)] = (dp[i][j][k|(1<<j)] + dp[i-1][8][k])%MOD;
//                    else{
//                        dp[i][j][k|(1<<j)] = (dp[i][j][k|(1<<j)] + dp[i - 1][j + 1][k])%MOD;
//                        dp[i][j][k|(1<<j)] = (dp[i][j][k|(1<<j)] + dp[i - 1][j - 1][k])%MOD;
//                    }
//                }
//            }
//        }
//
//       int ans =0;
//        for (int i = 1; i < 10; i++) {
//            ans += dp[SIZE][i][1023];
//           ans%=MOD;
//        }
//        return ans;
//    }
}
