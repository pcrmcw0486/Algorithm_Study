package RHS_FC.class05_graph;

/*
미로탈출 Silver I
기본적인 bfs 최소 이동 문제
 */
import java.util.*;
import java.io.*;

public class Q2178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        boolean[][] visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[] { 0, 0, 1 });
        visit[0][0] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            if (point[0] == N - 1 && point[1] == M - 1) {
                System.out.println(point[2]);
                break;
            }
            for (int k = 0; k < 4; k++) {
                int nx = point[0] + dir[k][0];
                int ny = point[1] + dir[k][1];
                int dist = point[2];
                if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
                    continue;
                if (map[nx][ny] == 0)
                    continue;
                if (visit[nx][ny])
                    continue;
                visit[nx][ny] = true;
                queue.add(new int[] { nx, ny, dist + 1 });
            }
        }

    }
}
