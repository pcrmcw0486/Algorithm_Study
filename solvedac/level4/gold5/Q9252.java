package solvedac.level4.gold5;

/*

문제번호 : Q9252
이름 및 난이도 : LCS2
문제이해 
-----------------
최장 공통 부분 수열.
LCS길이와 LCS를 출력한다.
0이라면 출력하지 않는다.

접근 방법 :
 LCS문제 + 출력(backtracking)
 dp[i][j] = 문자열 A의 i번째까지와 문자열 B의 j번까지의 최장 공통 수열.
 if(A[i]== B[j]) dp[i-1][j-1] +1
 else{
     dp[i][j] = MAth.max(dp[i-1][j], dp[i][j-1]);
 }
 무슨 말이냐하면.
 ACAYKP
 CAPCAK  
 A CA > A(1)
 AC C  > C (1)

 AC CA라면 두개중 하나여야함 A또는 C
 여기서 만약
 ACK CAK라고 한다면
 AC와 CA까지의 최장 공통 수열 길이(A또는 C)에 대해 K가 공통이므로
 이를 더해준다.
  수가 바뀌는 순간 그 곳이 공통적인 부분의 마지막임.
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q9252 {
    static char[] A;
    static char[] B;
    static int[][] dp;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
    }

    public static void solve() {
        sb = new StringBuilder();
        LCS();
        sb.append(dp[A.length][B.length]).append('\n');
        for (int i = 0; i < A.length + 1; i++) {
            for (int j = 0; j < B.length + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        if (dp[A.length][B.length] != 0)
            printing();
        System.out.println(sb.toString());
    }

    public static void printing() {
        int x = A.length;
        int y = B.length;
        int v = dp[x][y];
        Stack<Character> stack = new Stack<Character>();

        while (v != 0) {
            if (dp[x - 1][y] == v) {
                x--;
            } else if (dp[x][y - 1] == v) {
                y--;
            } else {
                stack.push(B[y - 1]);
                x--;
                y--;
            }
            v = dp[x][y];
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
    }

    public static void LCS() {
        dp = new int[A.length + 1][B.length + 1];
        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < B.length + 1; j++) {
                if (A[i - 1] == B[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
    }
}
