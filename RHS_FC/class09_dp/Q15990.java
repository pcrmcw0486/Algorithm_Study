package RHS_FC.class09_dp;
/*
2021.10.23
문제번호 : Q15990
이름 및 난이도 : 1,2,3 더하기 5
문제이해 
-----------------
1,2,3의 합으로 나타내는 방법 중 같은 수를 두 번 이상 (연속해서) 사용하면 안됨.
SolveByCategory.dp[1]= 1
SolveByCategory.dp[2] =1
SolveByCategory.dp[3] = 3 // 1+2 // 2+1// 인데 인데..? 방법은 3가지 인데.
SolveByCategory.dp[4] = 3 +1 // 1 + 3 // 1 +2 + 1
      SolveByCategory.dp[i][0] : 1로 끝나는 경우
      SolveByCategory.dp[i][1] : 2로 끝나는 경우
      SolveByCategory.dp[i][2] : 3으로 끝나는 경우
  SolveByCategory.dp[3]의 경우
  SolveByCategory.dp[3][0] = 1
  SolveByCategory.dp[3][1] = 1
  SolveByCategory.dp[3][2] = 1
  ---------------------
   SolveByCategory.dp[4][0] = SolveByCategory.dp[i-1][1] + SolveByCategory.dp[i-1][2] >> 2
   SolveByCategory.dp[4][1] = SolveByCategory.dp[i-2][0] + SolveByCategory.dp[i-2][2] >> 0
   SolveByCategory.dp[4][2] = SolveByCategory.dp[i-3][0] + SolveByCategory.dp[i-3][1] >> 1 //3으로 끝난다면
접근 방법 :
제한 조건 : 
*/

import java.io.*;
public class Q15990 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[][] dp = new int[100001][3];
        dp[1][0] = 1;
        dp[2][1] = 1;
        dp[3][0] = 1; //2 +1
        dp[3][1] = 1; // 1+ 2
        dp[3][2] = 1; // 3
        for(int i = 4;i<100001;i++){
            dp[i][0] = (dp[i-1][1] + dp[i-1][2])%1000000009;
            dp[i][1] = (dp[i-2][0] + dp[i-2][2])%1000000009;
            dp[i][2] = (dp[i-3][0] + dp[i-3][1])%1000000009;
        }
        int T = Integer.parseInt(br.readLine());
        for(int i =0;i<T;i++){
            int sum =0;
            int target = Integer.parseInt(br.readLine());
            for(int j=0;j<3;j++){
                sum = (sum + dp[target][j])%1000000009;
            }
            sb.append(sum).append('\n');
        }
        System.out.print(sb);
    }
}
