package RHS_FC.class09_dp;
/*
2021.10.22
문제번호 : Q1562
이름 및 난이도 : 계단 수 Gold I
문제이해 
-----------------
ex) 45656 이란 수를 보자. 인접한 모든 자리의 차이가 1이다.
이런수를 계단 수 라고 합니다.
N이 주어질 때 길이가 N이면서 0부터9까지 모두 등장하는 계단의 수가 총 몇개있는가.
0으로 시작하는 수는 계단수가 아니다.

접근 방법 :
제한 조건 : 
N<=100
*/

import java.io.*;
import java.util.*;

public class Q1562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[101][10][1 << 10];

        for (int i = 0; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i < 101; i++) {
            for (int j = 0; j < 10; j++) {
                for (int p = 0;p< 1<<10;p++) {
                    if (j == 0) {
                        dp[i][j][p | 1 << 0] = (dp[i][j][p | 1 << 0] + dp[i - 1][1][p]) % 1000000000;
                        continue;
                    }
                    if(j==9){
                        dp[i][j][p|1<<9] = (dp[i][j][p|1<<9] + dp[i-1][8][p]) % 1000000000;
                        continue;
                      }
                    dp[i][j][p | 1 << (j)] = (dp[i][j][p | 1 << j] + dp[i - 1][j - 1][p]) % 1000000000;
                    dp[i][j][p | 1 << (j)] = (dp[i][j][p | 1 << j] + dp[i - 1][j + 1][p]) % 1000000000;
                }
            }
        }
        int sum = 0;

        for (int j = 1; j < 10; j++) {
            sum = (sum + dp[N][j][(1 << 10) - 1]) % 1000000000;
        }
        System.out.println(sum);
    }

    //dfs로 짜기?
}
