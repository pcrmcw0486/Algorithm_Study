package RHS_FC.class05_graph;

/*
https://www.acmicpc.net/problem/14502
연구소 Gold V
하 시발 아닌거 같은데? 라고 생각하면 시간 복잡도를 직접 계산해보자.
3<=N,M<=8 최대 64 중 비어있는 곳 세개를 고른다고 하면 최대
64C3 = 41,664
각 경우마다 바이러스가 퍼져나감O(N^2) 
*/
import java.io.*;
import java.util.*;

public class Q14502 {
    static ArrayList<int[]> candidate;
    static int[][] map;
    static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    static boolean[][] visit;
    static int N, M;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        candidate = new ArrayList<int[]>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    candidate.add(new int[] { i, j });
                }

            }
        }
        solution(0, 0);
        System.out.println(ans);

    }

    public static void solution(int idx, int selected) {
        if (selected == 3) {
            bfs();
            return;
        } else if (idx == candidate.size()) {
            return;
        }

        int[] point = candidate.get(idx);
        map[point[0]][point[1]] = 1;
        solution(idx + 1, selected + 1);

        map[point[0]][point[1]] = 0;
        solution(idx + 1, selected);
    }

    public static void bfs() {
        visit = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    queue.add(new int[] { i, j });
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0], ny = y + dir[k][1];
                if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
                    continue;
                if (map[nx][ny] != 0)
                    continue;
                if (visit[nx][ny])
                    continue;
                visit[nx][ny] = true;
                queue.add(new int[] { nx, ny });
            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] == 0 && !visit[i][j])
                    cnt++;
        ans = Math.max(ans, cnt);
    }

}
