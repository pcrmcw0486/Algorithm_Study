/** int로 했는데 될거같은데 안되면 long 
 * 문제에서 long파악은 힘든거 같은데?
 * 다른예로
 * SolveByCategory.dp[i-2] + SolveByCategory.dp[i-3]도 존재함.
 */
package SolveByCategory.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9461 {
    static long[] dp = new long[101];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp[0] = 0L;
        dp[1] = 1L;
        dp[2] = 1L;
        dp[3] = 1L;
        dp[4] = 2L;
        for (int i = 5; i < 101; i++) {
            dp[i] = dp[i - 5] + dp[i - 1];
        }
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.print(sb.toString());
    }
}
