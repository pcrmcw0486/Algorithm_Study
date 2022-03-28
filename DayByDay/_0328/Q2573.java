package DayByDay._0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q2573
 * @문제이름 : 빙산
 * @난이도 : Gold IV
 * @date : 2022-03-28 오후 4:47
 * @문제이해 빙산이 녹고 있다. 빙산 이외의 바다에 해당되는 칸에는 0이 저장된다.
 * 빙산의 높이는 바닷물이 많이 접해있는 부분에서 더 빨리 줄어든다.
 * 0이 저장된 칸 개수 만큼 줄어든다.
 * 빙산이 2개로 분리되는 시간.
 * @알고리즘
 * @접근방법
 */
public class Q2573 {
    static int[][] map;
    static int[][] newMap;
    static boolean[][] isVisited;
    static int N, M;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        Queue<Point> iceBerg = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0) size++;
            }
        }

        //grouping을 체크해야함.
        boolean isGrouped = false;
        int time =0;
        while (size > 0) {

            newMap = new int[N][M];
            isVisited = new boolean[N][M];
            int groupCnt =0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0 && !isVisited[i][j]) {
                        if (groupCnt > 0) {
                            isGrouped = true;
                            break;
                        }
                        isVisited[i][j] = true;
                        melting(i,j);
                        groupCnt++;
                    }
                }
            }

            if (isGrouped) break;
            size =0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = newMap[i][j];
                    if(map[i][j] != 0) size++;
                }
            }
            time++;
        }
        if(!isGrouped && size ==0) System.out.println(0);
        else System.out.println(time);
    }

    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static void melting(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) continue;
            if (isVisited[nx][ny]) continue;
            if (map[nx][ny] == 0) cnt++;
            else if (map[nx][ny] != 0) {
                isVisited[nx][ny] = true;
                melting(nx, ny);
            }
        }
        newMap[x][y] = Math.max(map[x][y] - cnt, 0);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
