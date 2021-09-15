/*
 * 섬의 개수 Silver II EZ bfs 또는 dfs로 해결. 내 개인적으로는 dfs를 좋아하는 편.
 * 이게 그냥 2차원 돌리는게 더 빠르네 2500이라 최대.;;
 */
package tmp;

import java.io.*;
import java.util.*;

public class Q4963 {
    static int[][] map;
    static boolean[][] visited;
    static int w, h;
    static int[] dx = new int[] { -1, 1, 0, 0, 1, 1, -1, -1 };
    static int[] dy = new int[] { 0, 0, -1, 1, 1, -1, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0)
                break;
            map = new int[h][w];
            visited = new boolean[h][w];
            int count = 0;
            ArrayList<int[]> list = new ArrayList<int[]>();
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        list.add(new int[] { i, j });
                    }
                }
            }
            for (int[] position : list) {
                int x = position[0];
                int y = position[1];
                if (!visited[x][y]) {
                    count++;
                    dfs(x, y);
                }
            }
            sb.append(String.valueOf(count)).append("\n");

        }
        System.out.print(sb.toString());

    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx > h - 1 || ny > w - 1 || visited[nx][ny])
                continue;
            if (map[nx][ny] == 1)
                dfs(nx, ny);
        }
    }

}
