package solvedac.level4.gold4;
/*
2021.12.22
문제번호 : Q17144
이름 및 난이도 : 미세먼지 안녕! Gold IV
문제이해 
-----------------
미세먼지를 제거하기 위해 공기청정기를 설치한다.
공기청정기는 항상 1번 열에 설치되어 있고, 크기는 두행을 차지한다.

접근 방법 :
시뮬레이션임
BFS와 2차원 배열 순환.
제한 조건 : 


2차원 배열 rotate부분을 잘 못짜서 막힌거였음. 근데 뭐가 문제였지?
최대한 스왑보다는 그냥 달팽이마냥 전달해주는 식으로 생각합시다.
*/

import java.io.*;
import java.util.*;

public class Q17144 {
    static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // U R D L 시계방향
    static int R, C, T;
    static Queue<Point> dust;
    static int[] conditioner = new int[2];
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        dust = new LinkedList<Point>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    // visited[i][j] = true;
                    dust.add(new Point(i, j, map[i][j]));
                } else if (map[i][j] == -1) {
                    if (conditioner[0] > 0)
                        conditioner[1] = i;
                    else
                        conditioner[0] = i;
                }
            }
        }

        // simulation
        int tt = 0;
        while (tt < T) {
            // 미세먼지 확산
            // 중복은 queue에 넣는 것만 방문여부를 체크하는 것은 아님.
            spread();
            System.out.println("spread " + tt + "------------");
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }

            // 공기 순환 위쪽 반시계방향
            rotates();

            // 새로운 먼지 넣기.
            System.out.println("MOVE");
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(map[i][j] + " ");
                    if (map[i][j] > 0) {
                        dust.add(new Point(i, j, map[i][j]));
                    }
                }
                System.out.println();
            }
            tt++;
        }
        int ans = 0;
        while (!dust.isEmpty()) {
            ans += dust.poll().value;
        }
        System.out.println(ans);
    }

    public static void spread() {
        int[][] tmp = new int[R][C];
        tmp[conditioner[0]][0] = -1;
        tmp[conditioner[1]][0] = -1;
        int curDustSize = dust.size();

        for (int i = 0; i < curDustSize; i++) {
            Point cur = dust.poll();
            tmp[cur.x][cur.y] += cur.value;
            if (cur.value < 5)
                continue;
            int cnt = 0;
            int give = cur.value / 5;
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dir[d][0];
                int ny = cur.y + dir[d][1];
                if (nx < 0 || ny < 0 || nx > R - 1 || ny > C - 1)
                    continue;
                if (tmp[nx][ny] != -1) {
                    cnt++;
                    tmp[nx][ny] += give;
                }
            }
            tmp[cur.x][cur.y] -= give * cnt;
        }
        map = tmp;
    }

    public static void rotate() {
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        // i ==0 위쪽
        for (int i = 0; i < 2; i++) {
            int x = conditioner[i];
            int y = 0;
            int d = 2;
            int tb = i == 0 ? 1 : -1;

            int ceil = i == 0 ? 0 : x;
            int floor = i == 0 ? x + 1 : R;

            while (true) {
                int nx = x + dir[d][0];
                int ny = y + dir[d][1];
                if (nx < ceil || ny < 0 || nx >= floor || ny >= C) {
                    d += tb;
                    if (d == -1)
                        d = 3;
                    d %= 4;
                    continue;
                }
                if (map[nx][ny] == -1)
                    break;
                map[x][y] |= map[nx][ny];
                map[nx][ny] = 0;
                x = nx;
                y = ny;
            }
        }
    }

    public static void rotates() {
        int curX = conditioner[0];
        int curY = 0;
        int d = 0;
        while (true) {
            int nx = curX + dir[d][0];
            int ny = curY + dir[d][1];
            while (true) {
                if (nx < 0 || ny < 0 || nx > conditioner[0] || ny > C - 1)
                    d = (d + 1) % 4;
                else
                    break;
                nx = curX + dir[d][0];
                ny = curY + dir[d][1];
            }
            if (nx == conditioner[0] && ny == 0)
                break;
            map[curX][curY] = map[curX][curY] == -1 ? -1 : map[nx][ny];
            map[nx][ny] = 0;
            curX = nx;
            curY = ny;
        }

        curX = conditioner[1];
        curY = 0;
        d = 2;

        while (true) {
            int nx = curX + dir[d][0];
            int ny = curY + dir[d][1];
            while (true) {
                if (nx < conditioner[1] || ny < 0 || nx > R - 1 || ny > C - 1)
                    d = (d + 3) % 4;
                else
                    break;
                nx = curX + dir[d][0];
                ny = curY + dir[d][1];
            }
            if (nx == conditioner[1] && ny == 0)
                break;
            map[curX][curY] = map[curX][curY] == -1 ? -1 : map[nx][ny];
            map[nx][ny] = 0;
            curX = nx;
            curY = ny;
        }
    }

    static class Point {
        int x, y;
        int value;

        Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
