package solvedac.level4;
/*
2021.12.09
문제번호 : Q1932
이름 및 난이도 : 정수 삼각형 Silver I
문제이해 
-----------------
맨 위층 부터 시작하여 아래층으로 내려올 때 선택된 수의 합이 최대가 되는 경로
수는 선택된 수의 왼쪽 또는 오른쪽 대각선만 가능하다.
접근 방법 :
제한 조건 : 
수의 범위는 0이상 9999이하이고 삼각형 크기는 500이하
500 * 10000 = 10^8보다 작다. int범위 가능.
*/

import java.io.*;
import java.util.*;

public class Q1932 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] dp = new int[N][N];
        int max = -1;
        dp[0][0] = Integer.parseInt(br.readLine());
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][0];
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
                dp[i][j] += Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[N - 1][i]);
        }
        System.out.println(max);
    }
}
