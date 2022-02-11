package solvedac.level4.gold5;
/*
2021.12.19
문제번호 : Q17070
이름 및 난이도 : 파이프 옮기기 1 Gold V
문제이해 
-----------------
새 집의 크기는 N*N
파이프는 연속된 두 칸은 차지하는 크기.
 --  | \ 3가지 방향이가능.
 파이프가 벽을 긁으면 안됨. 항상 빈칸만 차지해야한다.
 첫파이프는 (1,1)과 (1,2)를 차지하고 있다.
 파이프 한 쪽 끝을 N,N으로 이동시키는 방법의 개수를 구해보자.
 
접근 방법 :
어떠한 칸 (i,j)에 도착햇을 때 할 수 있는 방법의 수를 기록한다.
 빈칸이 아니라면 방법이 없다. SolveByCategory.dp[i][j][0], [1] , [2] = 0; continue;
어떠한 칸 i,j칸이 빈칸일 때,
SolveByCategory.dp[i][j][0] : i,j칸에 가로로 놓을 수 있는 방법의 수
    SolveByCategory.dp[i][j][0] = SolveByCategory.dp[i][j-1][0] (그 전 가로) + SolveByCategory.dp[i-1][j-1][2] (대각 위치)
SolveByCategory.dp[i][j][1] : i,j칸에 세로로 놓을 수 있는 방법의 수
    SolveByCategory.dp[i][j][1] = SolveByCategory.dp[i-1][j][1] (그전 세로) + SolveByCategory.dp[i-1][j-1][2] (대각 위치)
SolveByCategory.dp[i][j][2] : i,j칸에 대각선으로 놓을 수 있는 방법의 수
    i,j칸 왼쪽과 위쪽이 비어있을 때 
    SolveByCategory.dp[i][j][2] = SolveByCategory.dp[i-1][j-1][0] + SolveByCategory.dp[i-1][j-1][1] + SolveByCategory.dp[i-1][j-1][2];


제한 조건 : 
이동시킬 수 없는 경우 0을 출려한다

22분
*/

import java.io.*;
import java.util.*;

public class Q17070 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 1][N + 1];
        int[][][] dp = new int[N + 1][N + 1][3];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[1][2][0] = 1;

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == 1 && (j == 1 || j == 2))
                    continue;
                if (map[i][j] == 0) {
                    dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
                    dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
                    if (map[i - 1][j] == 0 && map[i][j - 1] == 0) {
                        dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                    }
                }
            }
        }
        int ans = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
        System.out.println(ans);
    }
}
