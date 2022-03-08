package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Review_17143
 * @문제이름 : 낚시왕
 * @난이도 : Gold II
 * @date : 2022-03-03 오전 11:30
 * @문제이해 구현
 * @알고리즘
 * @접근방법
 */
public class Review_17143 {
    static int R, C;
    static final int UP = 1, DOWN = 2, RIGHT = 3, LEFT = 4;
    static Shark[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new Shark[R][C];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            map[x][y] = new Shark(s, d, z);
        }
        int ans = 0;
        for (int i = 0; i < C; i++) {
            ans += fishing(i);
            moveShark();
        }
        System.out.println(ans);
    }

    static int fishing(int pos) {
        int ret = 0;
        for (int i = 0; i < R; i++) {
            if (map[i][pos] != null) {
                ret = map[i][pos].size;
                map[i][pos] = null;
                break;
            }
        }
        return ret;
    }

    // 0 1 2 3 4 5 6 7 8 9 2*(R-1)
    // 0 1 2 3 4 5 4 3 2 1
    //  순방향    |  역방향

    static void moveShark() {
        Shark[][] newMap = new Shark[R][C];
        int nx = 0, ny = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                Shark s = null;
                if (map[i][j] != null) {
                    nx = i;ny=j;
                    s = map[i][j];
                    switch (s.dir) {
                        case UP:
                            nx = (2 * (R - 1) - i) + s.speed;
                            nx %= 2 * (R - 1);
                            s.dir = DOWN;
                            if (nx >= R-1) {
                                nx = 2 * (R - 1) - nx;
                                s.dir = UP;
                            }
                            break;
                        case DOWN:
                            nx = i + s.speed;
                            nx %= 2 * (R - 1);
                            if (nx >= R-1) {
                                nx = 2 * (R - 1) - nx;
                                s.dir = UP;
                            }
                            break;
                        case RIGHT:
                            ny = j + s.speed;
                            ny %= 2 * (C - 1);
                            if (ny >= C-1) {
                                ny = 2 * (C - 1) - ny;
                                s.dir = LEFT;
                            }
                            break;
                        case LEFT:
                            ny = (2 * (C - 1) - j) + s.speed;
                            ny %= 2 * (C - 1);
                            s.dir = RIGHT;
                            if (ny >= C-1) {
                                ny = 2 * (C - 1) - ny;
                                s.dir = LEFT;
                            }
                            break;
                    }
                    if (newMap[nx][ny] == null || newMap[nx][ny].size < s.size) {
                        newMap[nx][ny] = s;
                    }
                }
            }
        }
        map = newMap;
    }

    static class Shark {
        int speed, dir, size;

        public Shark(int speed, int dir, int size) {
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
}
