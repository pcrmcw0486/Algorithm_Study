package SolveByCategory.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*아이디어 및 접근 방식
일단 연속으로 놓여있는 3잔을 모두 마실 수 없음.
그렇다면 마시는 방법은 연속하여 1잔 또는 2잔 0잔은 무시함.
f(n,0) > n-th잔을 마시지 않음.
f(n,1) > n-th을 마시는데 그 전의 것을 마심
f(n,2) > n-th을 마시는데 그 전의 것을 마시지 않음.

*/
public class Q2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[3][N + 1];
        int[] wine = new int[N + 1];
        for (int i = 0; i < N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }
        // way I : SolveByCategory.dp 상태에 따른 2차원 배열 사용 처음 아이디어
        for (int i = 1; i < N + 1; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], Math.max(dp[1][i - 1], dp[2][i - 1]));
            dp[1][i] = wine[i - 1] + dp[2][i - 1];
            dp[2][i] = wine[i - 1] + dp[0][i - 1];
        }
        System.out.println(Math.max(dp[0][N], Math.max(dp[1][N], dp[2][N])));

        // way II : f(N)을 N-th 마실때 최대로 마실 수 있다. 라고 생각했을 때
        // Array 배열 크기 주의
        int[] newdp = new int[N + 1];
        newdp[1] = wine[0];
        newdp[2] = wine[0] + wine[1];
        newdp[3] = Math.max(newdp[2], Math.max(wine[0] + wine[2], wine[1] + wine[2]));
        for (int i = 4; i < N + 1; i++) {
            newdp[i] = Math.max(newdp[i - 1],
                    Math.max(newdp[i - 2] + wine[i - 1], newdp[i - 3] + wine[i - 1] + wine[i - 2]));
        }
        System.out.println(newdp[N]);
    }
}
