package solvedac.level4.gold5;
/*
2021.12.16
문제번호 : Q14502
이름 및 난이도 : 연구소 Gold V
문제이해 
-----------------
바이러스 확산을 막기 위해 연구소에 벽을 세운다.
N*M 직사각형
상하좌우 인접한 빈칸으로 모두 퍼져나간다. 벽은 "꼭" 3개를 세워야한다.
안전영역 크기의 최대값을 구하시오.

접근 방법 :
제한 조건 : 
3<=N ,M <= 8 
64C3 = 41664
*/

import java.io.*;
import java.util.*;

public class Q14502 {
    static int N, M;
    static int[][] map;
    static ArrayList<Point> safeZone;
    static int min;
    static int cnt;
    static int dir[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static boolean[][] baseVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        min = Integer.MAX_VALUE;
        cnt = 0;
        safeZone = new ArrayList<Point>();
        baseVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    safeZone.add(new Point(i, j));
                }
                if (map[i][j] == 2) {
                    baseVisited[i][j] = true;
                }
            }
        }
        solution(0, 0);
        System.out.println(safeZone.size() - 3 - min);
    }

    public static void solution(int depth, int idx) {
        if (depth == 3) {
            // bfs
            int c = 0;
            Queue<Point> queue = new LinkedList<Point>();
            boolean[][] visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 2) {
                        queue.add(new Point(i, j));
                        visited[i][j] = true;
                    }
                }
            }
            // visited = baseVisited;
            // 또 static 관련해서 문제가 생겼네..
            while (!queue.isEmpty()) {
                Point cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dir[i][0];
                    int ny = cur.y + dir[i][1];
                    if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
                        continue;
                    if (visited[nx][ny])
                        continue;
                    if (map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.add(new Point(nx, ny));
                        c++;
                    }
                }
            }
            min = Math.min(min, c);
            return;
        }
        for (int i = idx; i < safeZone.size(); i++) {
            Point p = safeZone.get(i);
            map[p.x][p.y] = 1;
            solution(depth + 1, i + 1);
            map[p.x][p.y] = 0;
        }

    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
