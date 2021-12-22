package solvedac.level3;
/*
2021.12.03
문제번호 : Q7569
이름 및 난이도 : 토마토 Silver I
문제이해 
-----------------
하루가 지나면 인접한 곳에 익지 않은 토마토들은 영향을 받아 익는다.
위, 아래, 왼쪽 오른쪽, 앞, 뒤 총 6방향에 영향을 준다. 
0 익지 않은 토마도 
1 익은 토마토
-1 없는 칸.
접근 방법 :
순차적으로 익게 만들면서 day 계산하면된다. day를 계산하는 좋은 방법이 없을까..?
queue마다 따로 for문으로 보통했었는데 더 나은 방법은 없을까?
BFS 
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q7569 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 가로
        int N = Integer.parseInt(st.nextToken()); // 세로
        int H = Integer.parseInt(st.nextToken()); // 높이
        int[][][] map = new int[H][N][M];
        int zeroTomato = 0;

        Queue<Point> queue = new LinkedList<Point>();
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    map[h][n][m] = Integer.parseInt(st.nextToken());
                    if (map[h][n][m] == 1) {
                        queue.add(new Point(h, n, m));
                    } else if (map[h][n][m] == 0) {
                        zeroTomato++;
                    }
                }
            }
        }
        int dir[][] = { { 1, 0, 0 }, { -1, 0, 0, }, { 0, -1, 0 }, { 0, 0, 1 }, { 0, 1, 0 }, { 0, 0, -1 } };
        int day = -1;
        if (zeroTomato == 0)
            System.out.println(0);
        else {
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int s = 0; s < size; s++) {
                    Point cur = queue.poll();
                    for (int d = 0; d < 6; d++) {
                        int nh = cur.h + dir[d][0];
                        int nx = cur.x + dir[d][1];
                        int ny = cur.y + dir[d][2];
                        if (nh < 0 || nx < 0 || ny < 0 || nh > H - 1 || nx > N - 1 || ny > M - 1)
                            continue;
                        if (map[nh][nx][ny] == 0) {
                            zeroTomato--;
                            map[nh][nx][ny] = 1;
                            queue.add(new Point(nh, nx, ny));
                        }
                    }
                }
                day++;
            }
            if (zeroTomato == 0)
                System.out.println(day);
            else
                System.out.println(-1);
        }
    }

    static class Point {
        int x, y, h;

        Point(int h, int x, int y) {
            this.h = h;
            this.x = x;
            this.y = y;
        }
    }
}
