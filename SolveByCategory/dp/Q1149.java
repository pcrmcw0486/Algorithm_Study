package SolveByCategory.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//f(n,0), f(n,1), f(n,2)
// 0 :빨간색, 1 : 초록색, 2 : 파랑색
// 1, 2 
// n n-1(인접 X)
// ㅑ qjs wlqdml tordms i-1, i+1
// 즉 그냥 인접한거는 색이 달라야 한다.
public class Q1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int dp[][] = new int[2][3];
        int prev = 0;
        int next = 1;
        int temp = 1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            dp[next][0] = Math.min(dp[prev][1], dp[prev][2]) + R;
            dp[next][1] = Math.min(dp[prev][0], dp[prev][2]) + G;
            dp[next][2] = Math.min(dp[prev][0], dp[prev][1]) + B;
            temp = next;
            next = prev;
            prev = temp;
        }
        System.out.println(Math.min(dp[prev][0], Math.min(dp[prev][1], dp[prev][2])));
    }
}
