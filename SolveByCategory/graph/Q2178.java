package SolveByCategory.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/* 
 그래프 기본 미로찾기 연습 
*/
public class Q2178 {
    static Queue<int[]> q = new LinkedList<>();
    static int N;
    static int M;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        for (int i = 0; i < N; i++) {
            graph[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        for (int[] a : graph) {
            for (int b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        // bw.write(String.valueOf(bfs(0, 0)));
        bw.write(String.valueOf(newsolution(0, 0)));
        bw.flush();
        System.out.println();
        for (int[] a : graph) {
            for (int b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        bw.flush();
        br.close();
        bw.close();

    }

    public static int newsolution(int x, int y) {
        int[] dx = new int[] { -1, 1, 0, 0 };
        int[] dy = new int[] { 0, 0, -1, 1 };
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { x, y });
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int i = 0; i < dx.length; i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < M && graph[nx][ny] == 1) {
                    graph[nx][ny] = graph[point[0]][point[1]] + 1;
                    queue.add(new int[] { nx, ny });
                }
            }
        }
        return graph[N - 1][M - 1];
    }

    public static int bfs(int i, int j) {
        int max_length = N * M;
        q.offer(new int[] { i, j });
        while (!q.isEmpty()) {
            int[] position = q.poll();
            int x = position[0];
            int y = position[1];
            int length = graph[x][y];
            if (x == N - 1 && y == M - 1) {
                if (length < max_length)
                    max_length = length;
            }
            if (length < max_length) {
                update(x - 1, y, length);
                update(x + 1, y, length);
                update(x, y - 1, length);
                update(x, y + 1, length);
            }
        }
        return graph[N - 1][M - 1];
    }

    public static void update(int i, int j, int length) {
        if (i >= 0 && i < N && j >= 0 && j < M) {
            if (graph[i][j] != 0 && (graph[i][j] == 1 || graph[i][j] > length + 1)) {
                graph[i][j] = length + 1;
                if (!q.contains(new int[] { i, j }))
                    q.offer(new int[] { i, j });
            }
        }
    }

}

/*
 * 내가 걱정했던 것은 변경된 SolveByCategory.graph[i]][j]가 다른 길을 통해 update될 수 있다는 점이였다. bfs 특성을 생각했을 때 이미
 * 도착한 곳은 사실상 먼저 도착한 것과 동일하다. 하여 따로 length에 대한 생각을 해줄 필요가 없다. 그렇기 때문에 check를 해주지
 * 않아서 메모리 초과가 났었음. check를 queue에 포함되어 있는지로만 확인하였기 때문에 메모리가 초과되었던 것. 또한 방향에 대해
 * dx, dy 배열 활용을 잊어버렸었음. 결론) bfs 특징을 제대로 파악하지 못했음.
 */