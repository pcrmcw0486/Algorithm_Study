package solvedac.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Review_14442
 * @문제이름 : 벽 부수고 이동하기 2
 * @난이도 : Gold III
 * @date : 2022-02-26 오후 11:31
 * @author : pcrmcw0486
 * @문제이해

 * @알고리즘

 * @접근방법

*/
public class Review_14442 {
    static int N, M, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        int ans =0;
        int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0,-1}};
        boolean[][][] visited = new boolean[K + 1][N][M];
        visited[0][0][0] = true;
        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(0, 0, 1, 0));
        while (!queue.isEmpty()) {
            Status cur = queue.poll();
            if (cur.x == N - 1 && cur.y == M - 1) {
                ans = cur.moves;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dir[i][0];
                int ny = cur.y + dir[i][1];
                if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
                if(visited[cur.broken][nx][ny]) continue;
                visited[cur.broken][nx][ny] = true;
                if (map[nx][ny] == '1') {
                    if(K<cur.broken+1) continue;
                    queue.add(new Status(nx, ny, cur.moves + 1, cur.broken + 1));
                }else
                    queue.add(new Status(nx, ny, cur.moves + 1, cur.broken));
            }
        }
    }
    static class Status{
        int x, y, moves, broken;

        public Status(int x, int y, int moves, int broken) {
            this.x = x;
            this.y = y;
            this.moves = moves;
            this.broken = broken;
        }
    }
}
