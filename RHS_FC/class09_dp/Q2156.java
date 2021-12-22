package RHS_FC.class09_dp;
/*
2021.10.22
문제번호 : Q2156
이름 및 난이도 : 포도주 마시기 Silver I
문제이해 
-----------------
연속 제한 조건 연습 용으로 한번 해봄.
1. 포도주 잔 선택하면 다 마셔야함. 
2. 연속으로 3잔을 모두 못마심. 
  >> 최대 두잔까지 지금 잔 마실꺼면 전전잔마시면안됨.
dp[i] >> i잔까지의 최대량.
최대로 먹으려면?
그렇다면 한잔 또는 두잔 마시야됨.
dp[x][0] = x-1잔을 마시지 않을 때
dp[x][1] = x-1잔을 마실 때
dp[x][0] = Math.max(dp[i-1][0], dp[i-1][1]) // 
dp[x][1] = dp[i-1][0] 
접근 방법 :
 1) x-1잔을 마시지 않는다 // 마신다. (이 경우 x잔은 무조건 마시는 경우임)
  x-1잔은 마시지 않는다 : dp[x][0] = Math.max(dp[x-2][0],dp[x-2][1]);
  x-1잔을 마신다. : dp[x][1] = Math.max(dp[x-1][0])
  

 2) x잔을 마신다// 마시지 않는다.
  기저조건 2까지 채워주야함.
   마신다 : dp[x][0] = data[x] + Math.max(data[x-1] + dp[x-2][0], data[x-1][1])
   마시지않는다 : dp[x][1] = Math.max(dp[x-1][0], dp[x-1][1])
제한 조건 : 
나는 먹는 걸로 한번 해볼게.
*/

import java.io.*;

public class Q2156 {
    static int[][] dp;
    static int[] data;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2];
        data = new int[N + 1];
        for (int i = 1; i < N + 1; i++)
            data[i] = Integer.parseInt(br.readLine());

        System.out.println(Math.max(dp[N][0], dp[N][1]));
        int ans = solve1();
        System.out.println(ans);
        ans = solve2();
        System.out.println(ans);
    }

    // i를 마신다 0 // 안마신다 1
    public static int solve1() {
        dp[1][0] = data[1];
        for (int i = 2; i < N + 1; i++) {
            dp[i][0] = data[i] + Math.max(data[i - 1] + dp[i - 2][1], dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }
        return Math.max(dp[N][0], dp[N][1]);
    }

    // x를 마실 때 dp[x] 는 최대 마실 수 있는 양
    public static int solve2() {
        int d[] = new int[N + 1];
        d[1] = data[1];
        if (N > 1)
            d[2] = data[1] + data[2];
        for (int i = 3; i < N + 1; i++) {
            // 현재 안마시고 그 전이 더 많다 d[i-1]
            // i-1을 안마신다 data[i] + d[i-2]
            // i-1을 마신다 data[i] + data[i-1] + d[i-3]
            d[i] = Math.max(d[i - 1], Math.max(d[i - 2] + data[i], d[i - 3] + data[i - 1] + data[i]));
        }
        return d[N];
    }

}
