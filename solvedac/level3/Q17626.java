package solvedac.level3;
/*
2021.11.05
문제번호 : Q17626
이름 및 난이도 : 
문제이해 
-----------------

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q17626 {
    public static void main(String[] args) throws IOException {
        int[] dp = new int[50001];
        for (int i = 0; i < (int) Math.sqrt(50001); i++) {
            dp[i * i] = 1;
        }
        for (int i = 2; i < 50001; i++) {
            if (dp[i] == 1)
                continue;
            int ans = Integer.MAX_VALUE;
            for (int j = 1; j < Math.sqrt(i / 2) + 1; j++) {
                ans = Math.min(ans, dp[j * j] + dp[i - (j * j)]);
            }
            dp[i] = ans;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(dp[N]);
    }

}
