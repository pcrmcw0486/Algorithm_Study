package DayByDay._0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q11123
 * @문제이름 : 양 한마리... 양 두마리 ...
 * @난이도 : Silver I
 * @date : 2022-04-03 오후 4:04
 * @author : pcrmcw0486
 * @문제이해
 * 양을 #으로 나타내고 . 으로 풀을 나타내는 거야
 * 서로 다른 # 두개 이상이 붙어있다면 한 무리의 양들이 있는 거지.
 * 초원에서 풀을 뜯고 있는 양들을 그리드로 표현해보는거야.
 * 몇 개의 양무리?
 * @알고리즘
 * bfs 또는 dfs
 * @접근방법

*/
public class Q11123 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            sb.append(solve(br)).append('\n');
        }
        System.out.print(sb);
    }

    static int N, M;
    static char[][] map;
    static boolean isVisited[][];
    private static int solve(BufferedReader br) throws IOException {
        init(br);
        return findShipGroupCount();
    }

    private static int findShipGroupCount() {
        int groupCnt =0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '#' && !isVisited[i][j]) {
                    checkShipGroup(i,j);
                    groupCnt++;
                }
            }
        }
        return groupCnt;
    }

    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static void checkShipGroup(int x, int y) {
        isVisited[x][y] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dir[i][0];
                int ny = cur.y + dir[i][1];
                if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
                if(map[nx][ny] == '.' || isVisited[nx][ny]) continue;
                isVisited[nx][ny] = true;
                queue.add(new Point(nx, ny));
            }
        }
    }


    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
