package DayByDay._0321;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q5582
 * @문제이름 : 공통 부분 문자열
 * @난이도 : Gold V
 * @date : 2022-03-21 오전 11:10
 * @문제이해 두 문자열에 모두 포함된 가장 긴 공통 부분 문자열을 찾는 프로그램
 * @알고리즘 공통 부분 문자열 길이 구하기
 * dp[i][j] = A (~i) B(~j)까지 최대 공통 부분 문자열의 길이.
 * @접근방법
 */
public class Q5582 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();
        int ans =0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        System.out.println(ans);
    }
}
