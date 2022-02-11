package SolveByCategory.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q9184 {
    static int dp[][][] = new int[21][21][21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                for (int k = 0; k < 21; k++) {
                    dp[i][j][k] = solution(i, j, k);
                }
            }
        }
        String str = "";
        while (!(str = br.readLine()).equals("-1 -1 -1")) {
            StringTokenizer st = new StringTokenizer(str);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int result = solution(a, b, c);
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(result)
                    .append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    public static int solution(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0)
            return 1;
        else if (a > 20 || b > 20 || c > 20) {
            return solution(20, 20, 20);
        } else if (a < b && b < c) {
            return dp[a][b][c - 1] + dp[a][b - 1][c - 1] - dp[a][b - 1][c];
        } else {
            return dp[a - 1][b][c] + dp[a - 1][b - 1][c] + dp[a - 1][b][c - 1] - dp[a - 1][b - 1][c - 1];
        }
    }

}

/*
 * DP이고 문제에서 메모이제이션이 포인트라는 것을 알았음. 이미 본 것을 다시 보지 않아도 되는데 if로 체크를 하더라도 그 자체로 시간이
 * 걸림 간단한 1 1 1 과 같은 입력으로 끝이날 경우에는 더 빠르지만 길게 봤을 때 시간이 더 걸림. import
 * java.io.BufferedReader; import java.io.BufferedWriter; import
 * java.io.IOException; import java.io.InputStreamReader; import
 * java.io.OutputStreamWriter; import java.util.StringTokenizer;
 * 
 * public class Main { static int SolveByCategory.dp[][][] = new int[21][21][21];
 * 
 * public static void main(String[] args) throws IOException { BufferedReader br
 * = new BufferedReader(new InputStreamReader(System.in)); BufferedWriter bw =
 * new BufferedWriter(new OutputStreamWriter(System.out)); StringBuilder sb =
 * new StringBuilder();
 * 
 * // dp값 초기화 for (int i = 0; i < 21; i++) { for (int j = 0; j < 21; j++) {
 * SolveByCategory.dp[0][i][j] = 1; SolveByCategory.dp[i][0][j] = 1; SolveByCategory.dp[i][j][0] = 1; } } while (true) {
 * StringTokenizer st = new StringTokenizer(br.readLine()); int a =
 * Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());
 * int c = Integer.parseInt(st.nextToken());
 * 
 * if (a == -1 && b == -1 && c == -1) break; int result = solution(a, b, c);
 * sb.append("w(" + a + ", " + b + ", " + c + ") = " + result + "\n"); }
 * bw.write(sb.toString()); bw.flush(); bw.close(); br.close(); }
 * 
 * public static int solution(int a, int b, int c) { if (a <= 0 || b <= 0 || c
 * <= 0) return 1; else if (a > 20 || b > 20 || c > 20) { a = 20; b = 20; c =
 * 20; } for (int i = 1; i < a + 1; i++) { for (int j = 1; j < b + 1; j++) { for
 * (int k = 1; k < c + 1; k++) { if (SolveByCategory.dp[i][j][k] != 0) continue; if (i < j && j
 * < k) { SolveByCategory.dp[i][j][k] = SolveByCategory.dp[i][j][k - 1] + SolveByCategory.dp[i][j - 1][k - 1] - SolveByCategory.dp[i][j - 1][k];
 * } else { SolveByCategory.dp[i][j][k] = SolveByCategory.dp[i - 1][j][k] + SolveByCategory.dp[i - 1][j - 1][k] + SolveByCategory.dp[i - 1][j][k
 * - 1] - SolveByCategory.dp[i - 1][j - 1][k - 1]; } } } } return SolveByCategory.dp[a][b][c]; }
 * 
 * }
 */
