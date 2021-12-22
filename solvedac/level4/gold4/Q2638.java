package solvedac.level4.gold4;
/*
2021.12.21
문제번호 : Q2638
이름 및 난이도 : 치즈 Gold IV
문제이해 
-----------------
N*M 치즈
2변 이상 공기와 접촉한 것은 정확히 한 시간만에 녹아 없어진다.
주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 구하시오.
공기type은 두가지 (외부 공기, 내부공기)
치즈 총 3가지.

접근 방법 :
 외부 공기를 찾는다.
 (치즈와 만나면 count해주고 더이상 진행하지 못한다.)
 치즈 count가 2인 애들은 삭제한다.
  >> 치즈 관리를 어떻게 할건가.
  반복.
  1. 가장 Naiive하게 해본다.
    1.1 치즈 queue를 저장해둔다.
    1.2 외부 공기를 돌리면서 치즈가 있던 부분을 counting한다.(count배열)
    1.3 치즈queue를 돌면서 해당 좌표의 count를 보면서 2이상이면 삭제해준다.
    1.4 치즈queue가빌 때 까지 1.1~ 1.3을 반복한다.
   
제한 조건 : 
N,M <=100
모눈종이 맨 가장자리에는 치즈가 놓이지 않는다.

*/

import java.io.*;
import java.util.*;

public class Q2638 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] count = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        Queue<Point> air = new LinkedList<Point>();
        // Queue<Point> cheese = new LinkedList<Point>();
        int cheeseCnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    // cheese.add(new Point(i, j));
                    cheeseCnt++;
                }
            }
        }

        int time = 0;

        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        air.add(new Point(0, 0));
        visited[0][0] = true;
        while (cheeseCnt > 0) {
            // 외부 공기 확산(새로 추가된 것들만)
            while (!air.isEmpty()) {
                Point cur = air.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dir[i][0];
                    int ny = cur.y + dir[i][1];
                    if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
                        continue;
                    if (visited[nx][ny])
                        continue;
                    // 치즈인경우
                    if (map[nx][ny] == 1) {
                        count[nx][ny]++;
                    }
                    // 새로운 공기 발견
                    else {
                        visited[nx][ny] = true;
                        air.add(new Point(nx, ny));
                    }
                }
            }

            time++;
            // 치즈 삭제

            // type I
            // int size = cheese.size();
            // for (int i = 0; i < size; i++) {
            // Point ch = cheese.poll();
            // if (count[ch.x][ch.y] >= 2) {
            // visited[ch.x][ch.y] = true;
            // map[ch.x][ch.y] = 0;
            // air.add(new Point(ch.x, ch.y));
            // } else {
            // cheese.add(ch);
            // }
            // }

            // type II
            System.out.println("---------------\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (count[i][j] >= 2 && map[i][j] == 1) {
                        // 삭제 및 새로운 공기로 추가.
                        cheeseCnt--;
                        visited[i][j] = true;
                        map[i][j] = 0;
                        air.add(new Point(i, j));
                    }
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }

        System.out.println(time);
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
