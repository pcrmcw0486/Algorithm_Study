package solvedac.level3;
/*
2021.12.02
문제번호 : Q2178
이름 및 난이도 : 미로탐색 Silver I
문제이해 
-----------------
N*M 크기 배열 미로.
1은 이동 가능 칸, 0은 불가능.
(1,1) 부터 (N,M)의 위치로 이동할 때 지나야하는 최소 칸수.
시작위치 및 도착 위치 포함.

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q2178 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        // data input
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        // queue Setting
        // visit check by MapValue 1(unvisited) X(visited)
        // BFS 특성상 먼저 도착한 곳이 더 빠름.다시 check필요 X
        int ans = -1;
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(0, 0));
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.x == N - 1 && cur.y == M - 1) {
                ans = map[N - 1][M - 1];
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dir[i][0];
                int ny = cur.y + dir[i][1];
                // border Check
                if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
                    continue;
                if (map[nx][ny] == 0 || map[nx][ny] != 1)
                    continue; // <==> map[nx][ny] ==1
                map[nx][ny] = map[cur.x][cur.y] + 1;
                queue.add(new Point(nx, ny));
            }

        }
        System.out.println(ans);

    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
