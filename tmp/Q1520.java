package tmp;
/*
Q1520 내리막길 Gold IV
dfs문제
의식의 흐름대로
1. dfs로 푼다.
2. pruning필요. how?
    -> 해당 위치에서 갈수있는 방법의 수
 */

import java.util.*;
import java.util.stream.Stream;

import java.io.*;

public class Q1520 {

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int[][] map;
    static int[][] dp;
    static int N;
    static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));

    }

    private static int dfs(int x, int y) {
        // 종료 조건
        if (x == N - 1 && y == M - 1)
            return 1;
        if (dp[x][y] >= 0)
            return dp[x][y];
        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
                continue;
            if (map[nx][ny] < map[x][y])
                dp[x][y] += dfs(nx, ny);
        }

        return dp[x][y];

    }

}
