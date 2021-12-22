package RHS_FC.class09_dp;
/*
2021.10.22
문제번호 : Q9465
이름 및 난이도 : 스티커 Silver I
문제이해 
-----------------
스티거 2n개 구매
한장 떼면 변을 공유하는 스티커는 모두 찢어져서 사용할수가 없다.
2n스티커 중에서 점수의 합이 최대가 되면서 서로 변을 공유하지 않는 스티커 집합

접근 방법 :
n열 고를 때 상태는 세가지 (위 선택, 아래 선택, X선택.) X선택은 그 이전의 최대
d[n][0] :위에 것이 살아있다.
        그전 상태는 아래를 골랐어야함
        또는 그 전전 열에서 위를 골랐을 때

         0  1 100
        70  0  0  과 같은 상황에서 70을 고른다 치고 그다음 1을 골라버리면? 100을 놓침.
               
d[n][1] : 아래 것 그전 상태는 위를 골랐어야함.

위를 고를 때 d[n][0] = data[i] + Math.max(dp[i-1][1], dp[i-2][0], dp[n-2][1]);
아래 고를 때 d[n][1] = data[i] + Math.max(dp[n-1][0], dp[n-2][0], dp[n-2][1]);

제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[][] data = new int[2][N + 1];
            int[][] dp = new int[2][N + 1];
            for (int n = 0; n < 2; n++) {
                st = new StringTokenizer(br.readLine());
                for (int i = 1; i < N + 1; i++) {
                    data[n][i] = Integer.parseInt(st.nextToken());
                }
            }
            dp[0][1] = data[0][1];
            dp[1][1] = data[1][1];
            for (int i = 2; i < N + 1; i++) {
                // 사실 왼쪽에 하나도 안 가진다고 가정하면
                // 밑을 뗄 때, 고려할 건 반대 방향 대각선 밖에 없음.
                // i번째 밑과 i-2번째 밑은 i-1번째 위가 포함되는 것이 더 좋으므로.
                // O X O
                // O O O
                dp[0][i] = data[0][i] + Math.max(dp[1][i - 1], dp[1][i - 2]);
                dp[1][i] = data[1][i] + Math.max(dp[0][i - 1], dp[0][i - 2]);
            }
            sb.append(Math.max(dp[0][N], dp[1][N])).append('\n');
        }

        System.out.print(sb);
    }
}
