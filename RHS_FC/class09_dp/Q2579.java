package RHS_FC.class09_dp;

/*
계단 오르기 Silver III
1. 계단은 한 계단 또는 두 계단씩 오를 수 있다.
2. 연속된 세개의 계단을 모두 밟아서는 안된다.(Point)
3. 마지막 도착계단은 반드시 밟아야된다.
점수의 최댓값.

점수는 10_000
계단 개수는 300 이하 Int형으로 커버 가능.
SolveByCategory.dp[i] i계단까지의 합.
상태 정의를 해보자
i단계에 도착했을 때
1+1로왔을 수도  ?? SolveByCategory.dp[i-1][0]인데
+1로 왔을 수도  SolveByCategory.dp[i-1][1]
+2로 왔을 수도. SolveByCategory.dp[i-2]
이라고 한다면, 
그냥 상태 3개면
1칸 오른상태가 j
SolveByCategory.dp[i][0] : 1칸오르기 => SolveByCategory.dp[i-2][1] SolveByCategory.dp[i-2][0]  SolveByCategory.dp[i-1][0] = > i-1 번째를 안밟고 도착
SolveByCategory.dp[i][1] : 1칸 오른 상태가 1 => SolveByCategory.dp[i-1][1] -> i-1번째를 밟고 도착.

 */
import java.io.*;
import java.util.*;

public class Q2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[301];
        int[][] dp = new int[301][2];
        for (int i = 1; i < N + 1; i++)
            data[i] = Integer.parseInt(br.readLine());
        dp[1][0] = 0;
        dp[1][1] = data[1];
        if (N >= 2) {
            dp[2][0] = data[2];
            dp[2][1] = data[1] + data[2];
        }

        for (int i = 3; i < N + 1; i++) {
            dp[i][0] = data[i] + Math.max(dp[i - 2][0], dp[i - 2][1]);
            dp[i][1] = data[i] + dp[i - 1][0];
        }
        System.out.println(Math.max(dp[N][0], dp[N][1]));
    }
}
