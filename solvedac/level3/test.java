package solvedac.level3;

import java.util.*;
import java.io.*;

public class test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        int[] start = new int[2];
        int redCnt = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
                if (map[i][j] == 1) {
                    start[0] = i;
                    start[1] = j;
                }
                if (map[i][j] == 3) {
                    redCnt++;
                }
            }
        }
        // start 00
        int[][] minBreak = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(minBreak[i], redCnt + 1);
        }
        minBreak[start[0]][start[1]] = 0;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[] { start[0], start[1] });
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < dir.length; i++) {
                int nx = cur[0] + dir[i][0];
                int ny = cur[1] + dir[i][1];
                if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
                    continue;
                if (map[nx][ny] == 3)
                    continue;
                if (map[nx][ny] == 2) {
                    if (minBreak[cur[0]][cur[1]] + 1 < minBreak[nx][ny]) {
                        minBreak[nx][ny] = minBreak[cur[0]][cur[1]] + 1;
                        queue.add(new int[] { nx, ny });
                    }
                } else if (map[nx][ny] == 0 || map[nx][ny] == 4) {
                    if (minBreak[cur[0]][cur[1]] < minBreak[nx][ny]) {
                        minBreak[nx][ny] = minBreak[cur[0]][cur[1]];
                        queue.add(new int[] { nx, ny });
                    }
                }
            }
        }
        for (int[] line : minBreak) {
            for (int a : line) {
                System.out.print(a + " ");

            }
            System.out.println();
        }
    }
}
