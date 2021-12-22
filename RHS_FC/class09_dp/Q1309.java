package RHS_FC.class09_dp;
/*
2021.10.22
문제번호 : Q1309
이름 및 난이도 : 동물원 Silver I
문제이해 
-----------------
동물원에 가로로 두칸 세로로 N칸인 우리가 있다.
사자들은 가로로도 세로로도 붙어있게 배치할 수 없다.
2*N배열에 사자를 배치하는 경우의 수
사자를 한마리도 배치하지 않는 경우도 하나의 경우의수
우리의 상태 (0 0), (1 0 ) (0 1) 
각각의 상태를 0 , 1, 2 라고 하자 
이전 우리가 0 상태였다면 1, 2 상태가 가능하다.
이전 우리가 1상태일 때 0, 2 가 가능하다
이전 우리가 2 상태일 때 0,1 이 가능하다.
접근 방법 : 좌우 반대임.
제한 조건 : 
N우리크기 100_000
시간 2초
*/

import java.io.*;
import java.util.*;

public class Q1309 {
    public static void main(String[] args) throws IOException {
        int[][] dp = new int[100002][2];
        dp[1][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i < 100002; i++) {
            dp[i][0] = (dp[i - 1][0] + 2 * dp[i - 1][1]) % 9901;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(dp[N + 1][0]);
        // solve(N);
    }
    // 더 간단하게 할 수 있음.
    // public static void solve(int target) {
    // int dp[] = new int[100002];
    // dp[0] = 1;
    // dp[1] = 3;
    // for (int i = 2; i < 100002; i++) {
    // dp[i] = (dp[i - 2] + 2 * dp[i - 1]) % 9901;
    // }
    // System.out.println(dp[target]);
    // }
}
