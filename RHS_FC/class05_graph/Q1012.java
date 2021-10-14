package RHS_FC.class05_graph;

/*
유기농 배추 Silver II
배추 묶음마다 하나씩 있으면 해충으로 부터 보호받을 수 있음.
M 50 N 50 
 */
import java.io.*;
import java.util.*;

public class Q1012 {
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] map = new int[N][M];
            boolean[][] visit = new boolean[N][M];
            int[][] cabbage = new int[K][2];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
                cabbage[i][0] = x;
                cabbage[i][1] = y;
            }
            int ans = 0;
            for (int i = 0; i < K; i++) {
                if (!visit[cabbage[i][0]][cabbage[i][1]]) {
                    bfs(map, visit, cabbage[i][0], cabbage[i][1]);
                    ans++;
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }

    public static void bfs(int[][] map, boolean[][] visited, int x, int y) {
        Queue<int[]> queue = new LinkedList<int[]>();
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        visited[x][y] = true;
        queue.add(new int[] { x, y });
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nx = now[0] + dir[k][0];
                int ny = now[1] + dir[k][1];
                if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
                    continue;
                if (visited[nx][ny] || map[nx][ny] == 0)
                    continue;
                visited[nx][ny] = true;
                queue.add(new int[] { nx, ny });
            }
        }
    }
}
