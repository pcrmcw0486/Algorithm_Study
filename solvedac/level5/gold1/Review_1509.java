package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @문제번호 : Review_1509
 * @문제이름 : 팰린드롬 분할
 * @난이도 : Gold I
 * @date : 2022-03-07 오전 11:41
 * @author : pcrmcw0486
 * @문제이해
 *
 * @알고리즘
 *
 * @접근방법

*/
public class Review_1509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] text = br.readLine().toCharArray();
        boolean[][] check = new boolean[text.length][text.length];
        int[] dp = new int[text.length];
        for (int i = 0; i < text.length; i++) {
            check[i][i] = true;
            if (i != text.length - 1) {
                check[i][i + 1] = text[i] == text[i + 1];
            }
        }
        for (int i = 2; i < text.length; i++) {
            for (int j = 0; j < text.length-i; j++) {
                if (text[j] != text[i + j]) continue;
                check[j][j + i] = check[j + 1][j + i - 1];
            }
        }

        dp[0] = 1;
        for (int i = 1; i < text.length; i++) {
            dp[i] = dp[i-1] +1;
            if(check[0][i]){
                dp[i] = 1;
                continue;
            }
            for (int j = 1; j <= i; j++) {
                if (check[j][i]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        System.out.println(dp[dp.length-1]);
    }
}
