/*dp는 상태의 이해를 통한 점화식 세우기가 매우 중요. */
package SolveByCategory.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        int[][] dp = new int[str1.length + 1][str2.length + 1];

        for (int i = 1; i < str1.length + 1; i++) {
            for (int j = 1; j < str2.length + 1; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        for (int[] b : dp) {
            for (int n : b) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
        System.out.println(dp[str1.length][str2.length]);
    }
}
