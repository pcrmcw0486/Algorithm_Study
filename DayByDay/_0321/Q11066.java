package DayByDay._0321;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @문제번호 : Q11066
 * @문제이름 : 파일 합치기
 * @난이도 : Gold III
 * @date : 2022-03-21 오전 11:30
 * @author : pcrmcw0486
 * @문제이해
 * 파일을 합치는데 필요한 최소 비용.
 * dp[A][B] A~B까지 합치는데 필요한 최소 비용.
 * @알고리즘

 * @접근방법

*/
public class Q11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int ans = solve(N, data);
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }

    private static int solve(int N, int[] data) {
        int[][] dp = new int[N][N];
        int[] prefixSum = new int[N+1];;
        for (int i = 1; i < N+1; i++) {
            prefixSum[i] = prefixSum[i - 1] + data[i-1];
        }
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], 987654321);
       for(int i =0;i<N;i++) dp[i][i] = 0;
        for (int size = 1; size < N; size++) {
            for (int l = 0; l < N - size; l++) {
                for (int r = l; r < l + size; r++) {
                    dp[l][l + size] = Math.min(dp[l][l+size], dp[l][r] + dp[r + 1][l+size] + prefixSum[l+size+1]-prefixSum[l]);
                }
            }
        }
        return dp[0][N - 1];
    }
}
