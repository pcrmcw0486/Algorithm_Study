package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//점화식
//f(n)은 n까지의 최대합. 더한게 크거나 더한게 작으면 현재부터 시작.
//즉 f(n) = Math.max(f(n-1) + data[n], data[n])
public class Q1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = Integer.MIN_VALUE;
        int[] dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            int x = Integer.parseInt(st.nextToken());
            dp[i] = Math.max(dp[i - 1] + x, x);
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
