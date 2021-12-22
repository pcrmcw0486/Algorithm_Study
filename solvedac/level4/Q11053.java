package solvedac.level4;
/*
2021.12.09
문제번호 : Q11053
이름 및 난이도 : 가장 긴 증가하는 부분 수열.
문제이해 
-----------------
LIS라고 하는 유명한 문제이다. 할때마다 헷갈리네.
dp

접근 방법 :
dp[i] 는 i번째까지 증가했던 가장 긴 수열.
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        int[] dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        int max = -1;
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (data[i] > data[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            System.out.println(dp[i]);
            max = Math.max(dp[i], max);
        }
        System.out.println(max);

    }

}
