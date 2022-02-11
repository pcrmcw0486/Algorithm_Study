package SolveByCategory.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q2146 {
    static int N;
    static int[][] map;
    static int[][] dist_arr;
    static int[][] group_arr;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist_arr = new int[N][N];
        group_arr = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist_arr[i][j] = -1;
            }
        }
        solution();
    }

    public static void solution() {
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };
        int group_num = 0;
        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    visit[i][j] = true;
                    group_num++;
                    queue.offer(new int[] { i, j });
                    while (!queue.isEmpty()) {
                        int point[] = queue.poll();
                        int x = point[0];
                        int y = point[1];
                        group_arr[x][y] = group_num;
                        dist_arr[x][y] = 0;
                        for (int k = 0; k < dx.length; k++) {
                            int nx = x + dx[i];
                            int ny = y + dy[i];
                            if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 1) {
                                queue.offer(new int[] { nx, ny });
                            }
                        }
                    }
                }
            }
        }
    }

}
