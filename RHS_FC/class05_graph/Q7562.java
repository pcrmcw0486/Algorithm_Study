package RHS_FC.class05_graph;

import java.io.*;
import java.util.*;

public class Q7562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int I = Integer.parseInt(br.readLine()); // 체스 한변 길이
            int[] startPoint = new int[2];
            st = new StringTokenizer(br.readLine());
            startPoint[0] = Integer.parseInt(st.nextToken());
            startPoint[1] = Integer.parseInt(st.nextToken());

            int[] endPoint = new int[2];
            st = new StringTokenizer(br.readLine());
            endPoint[0] = Integer.parseInt(st.nextToken());
            endPoint[1] = Integer.parseInt(st.nextToken());
            sb.append(bfs(I, startPoint, endPoint)).append('\n');
        }
        System.out.print(sb);
    }

    public static int bfs(int N, int[] start, int[] end) {
        Queue<int[]> queue = new LinkedList<int[]>();
        int[][] visit = new int[N][N];
        queue.add(start);
        visit[start[0]][start[1]] = 0;
        int[][] dir = { { 2, 1 }, { 2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }, { -2, 1 }, { -2, -1 } };
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == end[0] && cur[1] == end[1]) {
                return visit[cur[0]][cur[1]];
            }
            for (int d = 0; d < dir.length; d++) {
                int nx = cur[0] + dir[d][0];
                int ny = cur[1] + dir[d][1];
                if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1 || visit[nx][ny] != 0)
                    continue;
                queue.add(new int[] { nx, ny });
                visit[nx][ny] = visit[cur[0]][cur[1]] + 1;
            }
        }
        return -1;
    }
}
