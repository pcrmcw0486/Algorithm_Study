package solvedac.level4.gold4;

/*
2021.12.19
문제번호 : Q2206
이름 및 난이도 : 벽 부수고 이동하기. Gold IV
문제이해 
-----------------
N*M 0 이동 1 X
N,M까지 가는 최단경로(이 때 벽 하나는 부셔도됨)
접근 방법 :
 1. 최단 거리 bfs!
 2. 상태 벽을 부쉈나? && 위치 && 거리
제한 조건 : 

다른방법 : 
    visit 배열 초기값을 매우 큰 값으로 두고
    벽 안깨고 방문시 0 벽깨고 방문시 1 가능
    차피 벽깨나 안깨나 최단거리로 오는 거리는 하나의 거리이기 때문에
    한번만 체크해주면 된다는 것에서 착안하여 visit배열을 통해
    중복 체크를 함(2차원으로)
*/

import java.io.*;
import java.util.*;

public class Q2206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        int[][][] dist = new int[N][M][2];
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(0, 0, 0, 0));
        dist[0][0][0] = 1;
        int ans = -1;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.x == N - 1 && cur.y == M - 1) {
                ans = cur.dist + 1;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dir[i][0];
                int ny = cur.y + dir[i][1];
                if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
                    continue;
                if (dist[nx][ny][cur.possible] != 0)
                    continue; // 중복체크 이미 온곳
                if (map[nx][ny] == 0) {
                    dist[nx][ny][cur.possible] = cur.dist + 1;
                    queue.add(new Point(nx, ny, cur.possible, cur.dist + 1));
                } else if (map[nx][ny] == 1 && cur.possible == 0) {
                    dist[nx][ny][cur.possible + 1] = cur.dist + 1;
                    queue.add(new Point(nx, ny, cur.possible + 1, cur.dist + 1));
                }
            }
        }
        System.out.println(ans);

    }

    static class Point {
        int x, y;
        int possible;
        int dist;

        Point(int x, int y, int possible, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.possible = possible;
        }
    }
}
