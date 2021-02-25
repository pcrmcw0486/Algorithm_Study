/*단지 번호붙이기*/

package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Q2667 {
    static int[][] map;
    static boolean[][] visit;
    static int N;
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];
        ArrayList<Integer> count = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    count.add(dfs(i, j) + 1);
                }
            }
        }
        bw.write(String.valueOf(count.size()) + "\n");
        Collections.sort(count);
        for (int k : count) {
            bw.write(String.valueOf(k) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dfs(int x, int y) {
        int count = 0;
        visit[x][y] = true;
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (map[nx][ny] == 1 && !visit[nx][ny]) {
                    count++;
                    count += dfs(nx, ny);
                }
            }
        }
        return count;
    }
}
