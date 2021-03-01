/*말이 되고픈 원숭이(Gold V)
test */
package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Q1600 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][] map = new int[H][W];
        boolean[][][] visit = new boolean[H][W][K + 1];
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };
        int[] jumpx = { -1, -1, 1, 1, -2, -2, 2, 2 };
        int[] jumpy = { 2, -2, 2, -2, 1, -1, 1, -1 };
        Queue<int[]> q = new LinkedList<int[]>();
        int result = -1;
        for (int i = 0; i < H; i++) {
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        q.offer(new int[] { 0, 0, 0, 0 });
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int x = point[0];
            int y = point[1];
            int jump_num = point[2];
            int dist = point[3];

            if (x == H - 1 && y == W - 1) {
                result = dist;
                break;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
                    if (map[nx][ny] != 1 && !visit[nx][ny][jump_num]) {
                        visit[nx][ny][jump_num] = true;
                        q.offer(new int[] { nx, ny, jump_num, dist + 1 });
                    }
                }
            }
            // 말처럼 뛰기
            jump_num++;
            if (jump_num < K + 1) {
                for (int i = 0; i < jumpx.length; i++) {
                    int nx = x + jumpx[i];
                    int ny = y + jumpy[i];
                    if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
                        if (map[nx][ny] != 1 && !visit[nx][ny][jump_num]) {
                            visit[nx][ny][jump_num] = true;
                            q.offer(new int[] { nx, ny, jump_num, dist + 1 });
                        }
                    }
                }
            }

        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
