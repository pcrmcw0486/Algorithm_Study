package solvedac.level4.gold5;
/*
2021.12.10
문제번호 : Q9251
이름 및 난이도 : LCS 최장 공통 수열 Gold V
문제이해 
-----------------
관련 문제가 많을 것 같음. 그리고 이거 나는 string이라 어려웟음.

접근 방법 :
  1 .  dp
    dp[i][j] 2차원으로 두고 dp[i][j] = i,j까지의 최장 공통 수열.
    즉 dp[i][j] = Math.max(dp[i-1][j], dp[j-1][i]) + 같다면 +1 아니면 그냥.
제한 조건 : 
*/

import java.io.*;

public class Q9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        int[][] dp = new int[A.length() + 1][B.length() + 1];
        for (int i = 1; i < A.length() + 1; i++) {
            for (int j = 1; j < B.length() + 1; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1))
                    dp[i][j]++;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        System.out.println(dp[A.length()][B.length()]);
    }
}
