package solvedac.level4;
/*
2021.12.09
문제번호 : Q1149
이름 및 난이도 : RGB거리
문제이해 
-----------------
RGB거리 집 N 1번부터 N번 집이 순서대로 있음.
RGB로 칠하는 비용이 주어질 때 아래 규칙을 만족해라.

접근 방법 :
SolveByCategory.dp[i][3] : 순서에 따라 i번째 집을 R,G,B로 칠할 때 비용.
sliding window로 두개로 해서 next prev로 풀어도 가능하다.
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][3];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dp[i][0] = r;
            dp[i][1] = g;
            dp[i][2] = b;
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] += Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] += Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] += Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        int min = Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2]));
        System.out.println(min);
    }
}
