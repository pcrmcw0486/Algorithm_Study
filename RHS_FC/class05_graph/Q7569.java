package RHS_FC.class05_graph;

/*
토마토 Silver I
4방향 + 위/아래
토마토가 다 익는 최소 일수
 */
import java.io.*;
import java.util.*;

public class Q7569 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        Queue<int[]> queue = new LinkedList<int[]>();
        int[][][] map = new int[H][N][M];
        boolean[][][] visited = new boolean[H][N][M];
        int totalSize = H * N * M;
        int[][] dir = { { 0, 1, 0 }, { 0, 0, 1 }, { 0, -1, 0 }, { 0, 0, -1 }, { 1, 0, 0 }, { -1, 0, 0 } };
        for (int h = 0; h < H; h++) {
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    map[h][r][c] = Integer.parseInt(st.nextToken());
                    if (map[h][r][c] == 1)
                        queue.add(new int[] { h, r, c });
                    if (map[h][r][c] != 0)
                        totalSize--;
                }
            }
        }
        if (totalSize == 0) {
            System.out.println(0);
            return;
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] cur = queue.poll();
                for (int d = 0; d < dir.length; d++) {
                    int nh = cur[0] + dir[d][0];
                    int nx = cur[1] + dir[d][1];
                    int ny = cur[2] + dir[d][2];
                    if (nh < 0 || nx < 0 || ny < 0 || nh > H - 1 || nx > N - 1 || ny > M - 1)
                        continue;
                    if (visited[nh][nx][ny])
                        continue;
                    if (map[nh][nx][ny] == 0) {
                        visited[nh][nx][ny] = true;
                        totalSize--;
                        queue.add(new int[] { nh, nx, ny });
                    }
                }
            }
            step++;
        }
        System.out.println(totalSize == 0 ? step - 1 : -1);
    }

}