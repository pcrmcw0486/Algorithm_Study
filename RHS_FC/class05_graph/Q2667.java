package RHS_FC.class05_graph;

/*
https://www.acmicpc.net/problem/2667
단지 번호 붙이기 Silver I
1은 집이 있고 0은 없음.
단지 수 오름차순 정렬 출력.
각 단지 수에 속하는 집의 수를 오름차순 정렬.
N <=25( 정점개수 25^2)
간선개수

bfs로 할래.
 */
import java.io.*;
import java.util.*;

public class Q2667 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<int[]> candidate = new ArrayList<>();
        int[][] map = new int[N][N];
        boolean[][] visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
                if (map[i][j] == 1)
                    candidate.add(new int[] { i, j });
            }
        }
        ArrayList<Integer> answer = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        int dx[] = { -1, 1, 0, 0 };
        int dy[] = { 0, 0, -1, 1 };
        for (int[] point : candidate) {
            if (visit[point[0]][point[1]])
                continue;
            int count = 0;
            queue.add(point);
            while (!queue.isEmpty()) {
                int[] now = queue.poll();
                if (visit[now[0]][now[1]]) {
                    continue;
                }
                count++;
                visit[now[0]][now[1]] = true;
                for (int i = 0; i < 4; i++) {
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
                        continue;
                    if (map[nx][ny] == 1 && !visit[nx][ny])
                        queue.add(new int[] { nx, ny });
                }
            }
            answer.add(count);
        }
        StringBuilder sb = new StringBuilder();

        Collections.sort(answer);
        sb.append(answer.size()).append('\n');
        for (int a : answer) {
            sb.append(a).append('\n');
        }
        System.out.print(sb);
    }
}
