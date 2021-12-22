package RHS_FC.class09_dp;
/*
2021.10.23
문제번호 : Q1495
이름 및 난이도 : 기타리스트 Silver I
문제이해 
-----------------
매번 곡이 시작하기 전에 볼륨을 바꾸고 연주한다.
볼륨 리스트 v[i] 는 i번째 곡을 연주하기 전에 바꿀 수 있는 볼륨을 의미한다.
리스트에 적힌 차이로만 볼륨을 바꿀 수 있다. 
0보다 작은 값이나 M보다 큰 값으로 바꿀 수 없다.
마지막 볼륨의 최댓값을 출력한다.
만약 볼륨 조절 못하면 -1을 출력한다.
접근 방법 :
배열 M+1 사이에서 가능한지 안한지 체크하면서 진행.
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q1495 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][S] = true;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            int v = Integer.parseInt(st.nextToken());
            for (int j = 0; j < M + 1; j++) {
                if (!dp[i - 1][j])
                    continue;
                if (j + v <= M)
                    dp[i][j + v] = true;
                if (j - v >= 0)
                    dp[i][j - v] = true;
            }
        }
        int ans = -1;
        for (int i = 0; i < M + 1; i++) {
            if (dp[N][i]) {
                ans = Math.max(ans, i);
            }
        }
        System.out.println(ans);
    }
}
