package RHS_FC.class09_dp;

/*
Q9095 1,2,3 더하기
4를 1,2,3의 합으로 나타내는 방법 총 7 가지
n이 주어질 때 n을 1,2,3의 합으로 나타내는 방법의 수를 구하시오
순서가 바뀌어도됨. 표시 방법이라.
접근방법 : 
n이 주어질 때,
n>3일 때
n-1 + 1 = n
n-2 + 2 = n
n-3 + 3 = n
dp[1] = 1    //1 
dp[2] = 2   // 1+1 // 2
dp[3] = 4   // 1+1+1 // 2+1 // 3 // 1+2 
dp[4] = 생각한대로한다면
 dp[4] = dp[1] + dp[2] + dp[3]  = 7 
  
 */
import java.io.*;

public class Q9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] dp = new int[11];
        init(dp);
        for (int i = 0; i < N; i++) {
            int t = Integer.parseInt(br.readLine());
            sb.append(dp[t]).append('\n');
        }
        System.out.print(sb);
    }

    public static void init(int[] dp) {
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
    }
}
