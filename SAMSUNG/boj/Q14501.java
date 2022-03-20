package SAMSUNG.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q14501
 * @문제이름 : 퇴사
 * @난이도 : Silver III
 * @date : 2022-03-12 오후 6:50
 * @author : pcrmcw0486
 * @문제이해
 * 오늘부터 N+1일째 되는날 퇴사
 * N일동안 최대한 많은 상담
 *
 * @알고리즘
 * dp[i] i날까지 얻을 수 있는 최대 이익
 * k일 걸림 Ti cost[i] = Pi
 * dp[i+k] = Math.max(d[i+k], dp[i-1] +cost[i])
 * @접근방법

*/
public class Q14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        //N일 안에
        int[] dp = new int[N + 1];
        dp[0]= 0;
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (i + length-1 <= N) {
                dp[i + length-1] = Math.max(dp[i+length-1], dp[i-1] + cost);
                //dp[i + length - 1] = Math.max(dp[i + length - 1], dp[i - 1] + cost);
            }
            //상담 진행을 안하는 경우 dp[i] = dp[i-1]
            dp[i] = Math.max(dp[i], dp[i - 1]);
            //wlsgodgksms ruddn dp[i] = cost + dp[i-t]?
        }
        System.out.println(dp[N]);
    }
}
