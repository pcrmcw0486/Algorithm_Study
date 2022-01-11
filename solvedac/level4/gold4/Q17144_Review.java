package solvedac.level4.gold4;
/*
2022.01.07

이름 및 난이도 : 미세먼지 안녕! Gold IV samsung 
문제이해 
-----------------
 공기청정기는 항상 1번열
- 동작과정
    1. 미세먼지 확산 
        - 인접한 네 방향으로 확산
        - 확산되는 양은 A/5
        - 남은 먼지는 A - (A/5)* 확산된 방향
         ** 순서에 상관이 없어야 하지만 각각의 먼지가 분산되는 과정에서 영향을 줄 수 있다.
         ** 영향을 주지 않게 하기 위해 상태를 따로 저장해야한다.
           > 1-1 새로운 Map을 생성하여 반영하는 방법  >> 2차원 배열
           > 1-2 새로운 List를 생성하여 반영하는 방법. >> arrayList(but, 안에 많이 들어감)
    2. 공기청정기 작동
        - 위쪽은 반 시계 방향
        - 아래쪽은 시계방향.

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q17144_Review {
    static int R, C, T;
    static int[][] map;
    static int[] cleanerPosition;
    static ArrayList<Point> dustList;
    // dir 시계방향. ^ > V <
    static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        cleanerPosition = new int[2]; // 0 위쪽 1 아래쪽
        dustList = new ArrayList<Point>();

        int idx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (j == 0 && map[i][j] == -1) {
                    cleanerPosition[idx++] = i;
                } else if (map[i][j] > 0) {
                    dustList.add(new Point(i, j));
                }
            }
        }
    }

    public static void solution() {
        while (T-- > 0) {
            dustDiffusion();
           // printMap();
            doCleaner();
            //printMap();
        }
        int ans = findAnswer();
        System.out.println(ans);
    }

    public static int findAnswer() {
        int ans = 0;
        for (Point p : dustList) {
            ans += map[p.x][p.y];
        }
        return ans;
    }

    public static void dustDiffusion() {
        // 영향을 배제하기 위한 새로운 맵 생성 (feat. clenar)
        int[][] newMap = new int[R][C];
        newMap[cleanerPosition[0]][0] = -1;
        newMap[cleanerPosition[1]][0] = -1;

        // 가지고 있는 dust들에 대해 확산시킴.
        for (Point cur : dustList) {
            // 5보다 낮으면 영향 X
            if (map[cur.x][cur.y] < 5) {
                newMap[cur.x][cur.y] += map[cur.x][cur.y];
                continue;
            }
            int cnt = 0;
            int diffuseSize = map[cur.x][cur.y] / 5;
            for (int i = 0; i < 4; i++) {
                int nx = dir[i][0] + cur.x;
                int ny = dir[i][1] + cur.y;
                if (nx < 0 || ny < 0 || nx > R - 1 || ny > C - 1)
                    continue;
                if (map[nx][ny] == -1)
                    continue;
                newMap[nx][ny] += diffuseSize;
                cnt++;
            }
            newMap[cur.x][cur.y] += map[cur.x][cur.y] - cnt * diffuseSize;
        }

        // map Update
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = newMap[i][j];
            }
        }
    }

    // 빨아들이는 느낌으로 간다. swap과 같은 과정이 필요 없기 때문.
    public static void doCleaner() {
        // 위방향
        int x = cleanerPosition[0] - 1;
        int y = 0;
        int d = 0;
        int offset = 1;
        int nx = -1;
        int ny = -1;
        while (true) {
            // 다음 칸 찾기.
            while (true) {
                nx = x + dir[d][0];
                ny = y + dir[d][1];
                if (nx < 0 || ny < 0 || nx > cleanerPosition[1] - 1 || ny > C - 1)
                    d = (d + offset) % 4;
                else
                    break;
            }
            if (map[nx][ny] == -1) {
                map[x][y] = 0;
                break;
            }
            map[x][y] = map[nx][ny];
            x = nx;
            y = ny;
        }

        // printMap();
        // 아래쪽 (아래 오른쪽 위쪽 왼쪽)
        x = cleanerPosition[1] + 1;
        y = 0;
        d = 2;
        offset = 3;

        while (true) {
            while (true) {
                nx = x + dir[d][0];
                ny = y + dir[d][1];
                if (nx < cleanerPosition[1] || ny < 0 || nx > R - 1 || ny > C - 1)
                    d = (d + offset) % 4;
                else
                    break;
            }
            if (map[nx][ny] == -1) {
                map[x][y] = 0;
                break;
            }
            map[x][y] = map[nx][ny];
            x = nx;
            y = ny;
        }
        // 새로운 미세먼지 Lis t생 성
        dustList = new ArrayList<Point>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    dustList.add(new Point(i, j));
                }
            }
        }
    }

    public static void printMap() {
        System.out.println();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
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
