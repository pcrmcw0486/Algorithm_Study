/* 치즈 Q2638
Gold 4
bfs 활용 */
package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2638 {
    static int N;
    static int M;
    static int[][] map;
    static int all_cheese;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        all_cheese = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)
                    all_cheese++;
            }
        }
        int dx[] = { -1, 1, 0, 0 };
        int dy[] = { 0, 0, -1, 1 };
        int phase = 0;
        while (all_cheese > 0) {
            phase++;
            boolean[][] visit = new boolean[N][M];
            Queue<int[]> air_queue = new LinkedList<>();
            air_queue.offer(new int[] { 0, 0 });
            visit[0][0] = true;
            while (!air_queue.isEmpty()) {
                int point[] = air_queue.poll();
                for (int i = 0; i < dx.length; i++) {
                    int nx = point[0] + dx[i];
                    int ny = point[1] + dy[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (map[nx][ny] == 0 && visit[nx][ny] == true)
                            continue;
                        else if (map[nx][ny] == 0 && visit[nx][ny] != true) {
                            visit[nx][ny] = true;
                            air_queue.offer(new int[] { nx, ny });
                        } else if (map[nx][ny] == 1 && visit[nx][ny] == false)
                            visit[nx][ny] = true;
                        else if (map[nx][ny] == 1 && visit[nx][ny] == true) {
                            map[nx][ny] = 0;
                            all_cheese--;
                        }
                    }
                }
            }
        }
        bw.write(String.valueOf(phase));
        bw.flush();
        bw.close();
        br.close();

    }
}
