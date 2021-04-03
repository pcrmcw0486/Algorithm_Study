package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Q2206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][][] visit = new int[N][M][2];
        int[][] map = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };
        queue.add(new int[] { 0, 0, 0, 0 });
        int result = -2;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            int dist = point[2];
            int count = point[3];
            if (x == N - 1 && y == M - 1) {
                result = dist;
                break;
            }
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                if (visit[nx][ny][count] == 0 || dist + 1 < visit[nx][ny][count]) {
                    if (map[nx][ny] == 0) {
                        visit[nx][ny][count] = dist + 1;
                        queue.offer(new int[] { nx, ny, dist + 1, count });
                    } else {
                        if (count == 0) {
                            visit[nx][ny][count + 1] = dist + 1;
                            queue.offer(new int[] { nx, ny, dist + 1, count + 1 });
                        }
                    }
                }
            }
        }
        System.out.println(result + 1);

    }
}
