package solvedac.level3;
/*
2021.12.06
문제번호 : Q14500
이름 및 난이도 : 테트로미노 Gold V
문제이해 
-----------------

접근 방법 :
Brute force + dfs
테트로미노 모양이 dfs depth4인것과 ㅏㅗㅜㅓ의 speical case만 처리한다면 
모든 모양에 대해 체크해보아야한다.

사실 모든 모양에 대해 계산하는 것이 모든 모양을 구한다면 더 빠르긴 하다.
계산만 하면되서.
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q14500 {
    static int N;
    static int M;
    static int[][] data;
    static boolean[][] visited;
    static int dir[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        data = new int[N][M];
        visited = new boolean[N][M];
        int result = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                result = Math.max(result, DFS(i, j, 1));
                result = Math.max(result, speical(i, j));
                visited[i][j] = false;
            }
        }
        System.out.println(result);
    }

    public static int DFS(int x, int y, int depth) {
        if (depth == 4)
            return data[x][y];
        int max = -1;
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
                continue;
            if (visited[nx][ny])
                continue;
            visited[nx][ny] = true;
            sum = DFS(nx, ny, depth + 1);
            visited[nx][ny] = false;
            if (sum > max) {
                max = sum;
            }
        }

        return data[x][y] + max;
    }

    public static int speical(int x, int y) {
        // ㅏ
        int max = -1;
        if (x - 1 >= 0 && x + 1 <= N - 1 && y + 1 <= M - 1) {
            max = Math.max(max, data[x - 1][y] + data[x][y] + data[x + 1][y] + data[x][y + 1]);
        }
        // ㅗ
        if (y - 1 >= 0 && y + 1 <= M - 1 && x - 1 >= 0) {
            max = Math.max(max, data[x][y - 1] + data[x][y] + data[x][y + 1] + data[x - 1][y]);
        }
        // ㅜ
        if (y - 1 >= 0 && y + 1 <= M - 1 && x + 1 <= N - 1) {
            max = Math.max(max, data[x][y - 1] + data[x][y] + data[x][y + 1] + data[x + 1][y]);
        }
        // ㅓ
        if (y - 1 >= 0 && x - 1 >= 0 && x + 1 <= N - 1) {
            max = Math.max(max, data[x][y - 1] + data[x][y] + data[x - 1][y] + data[x + 1][y]);
        }
        return max;
    }
}
