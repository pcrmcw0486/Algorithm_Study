package RHS_FC.class09_dp;

/*
오르막 수 Silver I
문제 이해
오르막 수는 자리가 오름차순을 이루는 수를 말함. 
인접한수가 같아도 오름차순
2234 3678 , 11119 오름차순
수의길이 N이 주어질 때 오르막 수의 개수를 구하는 프로그램을 구하시오.
길이1자리라면
0,1,2,3,4,5,6,7,8,9,10
길이 2라면
10,11 202122, 30313233
SolveByCategory.dp[n][i] : 수열의 길이가 n일때 오름차순 가능한 가장 앞자리수
SolveByCategory.dp[2][i] : 수열의 길이가 2일 때
           i보다 작고 0보다 큰 수 중 그 전 수열의 값은 보장된다.
           즉 SolveByCategory.dp[2][1] = SolveByCategory.dp[1][0] + SolveByCategory.dp[1][1] + SolveByCategory.dp[1][2]
SolveByCategory.dp[3][i]  SolveByCategory.dp[3][3] SolveByCategory.dp[2][0] SolveByCategory.dp[2][1] SolveByCategory.dp[2][2] SolveByCategory.dp[2][3] 끝.
3 () ()
수는 0으로 시작할수 있음. 하지만 SolveByCategory.dp[1][0] 이나 SolveByCategory.dp[2][0] 이나 0은 중복밖에 안되므로
SolveByCategory.dp[x][0] = 1;
 */
import java.io.*;

public class Q11057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][10];
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }
        dp[1][0] = 1;
        for (int i = 2; i < N + 1; i++) {
            dp[i][0] = 1;
            for (int j = 1; j < 10; j++) {
                dp[i][j] += (dp[i][j - 1] + dp[i - 1][j]) % 10007;
            }
        }
        int sum = 0;
        for (int a : dp[N]) {
            sum += a;
            sum %= 10007;
        }
        System.out.println(sum);
    }

}
