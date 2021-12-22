package RHS_FC.class05_graph;

/*
https://www.acmicpc.net/problem/18404
현명한 나이트 Silver I
N*N 체스판 M개의 상대편말 위치 값 주어질 때 
각 상대편 말 잡기 위한 나이트의 최소 이동 수를 solve

startPoint 에서 갈 수 있는 모든 수를 구하고 M을 쓰는 것이 빠름
각 M마다 bfs할 경우 최악의 경우를 M번해야하지만
다 구하는 경우 최악의 경우를 한번만 구하여도 가능.이라고 생각함.

 */
import java.io.*;
import java.util.*;

public class Q18404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] startPoint = new int[2];
        st = new StringTokenizer(br.readLine());
        startPoint[0] = Integer.parseInt(st.nextToken()) - 1;
        startPoint[1] = Integer.parseInt(st.nextToken()) - 1;
        int[][] map = bfs(N, startPoint);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int[] endPoint = new int[2];
            endPoint[0] = Integer.parseInt(st.nextToken()) - 1;
            endPoint[1] = Integer.parseInt(st.nextToken()) - 1;
            sb.append(map[endPoint[0]][endPoint[1]]).append(' ');
        }
        System.out.println(sb);
    }

    public static int[][] bfs(int N, int[] startPoint) {
        int[][] map = new int[N][N];
        int[][] dir = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 } };
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(startPoint);

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < dir.length; i++) {
                int nx = cur[0] + dir[i][0];
                int ny = cur[1] + dir[i][1];
                if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1 || map[nx][ny] != 0)
                    continue;
                if (nx == startPoint[0] && ny == startPoint[1])
                    continue;
                map[nx][ny] = map[cur[0]][cur[1]] + 1;
                queue.add(new int[] { nx, ny });
            }
        }
        return map;
    }
}
