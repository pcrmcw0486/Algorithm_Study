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

        if (N < 10)
            System.out.println(0);
        else if (N == 10)
            System.out.println(1);
        else {
            int[][] dp = new int[101][10];
            dp[10][0] = dp[10][9] = 1;
            for (int i = 11; i < 101; i++) {
                for (int j = 0; j < 10; j++) {
                    if (j == 0) {
                        dp[i][j] = dp[i - 1][1] % 1000000000;
                        continue;
                    } else if (j == 9) {
                        dp[i][j] = dp[i - 1][8] % 1000000000;
                        continue;
                    }
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
                }
            }
            int sum = 0;
            for (int i = 1; i < 10; i++) {
                sum = (sum + dp[N][i]) % 1000000000;
            }
            System.out.println(sum);

        }

    }
}
