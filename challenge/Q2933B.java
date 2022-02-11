package challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import java.awt.Point;

public class Q2933B {
    static int R, C;
    static char[][] map;
    static int N;
    static int[] floors;
    static int[] dx = { 0, 0, -1, 1 }, dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // String[] rc = br.readLine().split(" ");
        // R = Integer.parseInt(rc[0]);
        // C = Integer.parseInt(rc[1]);

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        N = Integer.parseInt(br.readLine());
        floors = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        simulation();

        print2();
    }

    public static void simulation() {
        boolean left = true;

        for (int floor : floors) {

            breakMineral(R - floor, left);
            boolean[][] visited = new boolean[R][C];
            // 땅과 연결되어 있는 모든 미네랄을 boolean으로 check
            visitFloorMineral(visited);
            Queue<Point> floatCluster = getFloatingCluster(visited);
            if (floatCluster.size() > 0) {
                moveCluster(floatCluster, visited);
            }
            left = !left;

        }
    }

    public static void breakMineral(int floor, boolean left) {
        if (left) {
            for (int i = 0; i < C; i++) {
                if (map[floor][i] == 'x') {
                    map[floor][i] = '.';
                    break;
                }
            }
        } else {
            for (int i = C - 1; i >= 0; i--) {
                if (map[floor][i] == 'x') {
                    map[floor][i] = '.';
                    break;
                }
            }
        }
    }

    public static void visitFloorMineral(boolean[][] visited) {
        Queue<Point> queue = new LinkedList<>();
        // 바닥의 미네랄과 연결되어 있다면 떨어지지 X
        for (int y = 0; y < C; y++) {
            if (map[R - 1][y] == 'x') {
                queue.add(new Point(R - 1, y));
                visited[R - 1][y] = true;
            }
        }
        // 바닥에 붙은 미네랄과 연결되어 있는 클러스터 check

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (isIn(nx, ny) && map[nx][ny] == 'x' && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }

    }

    public static boolean isIn(int x, int y) {
        if (x >= 0 && x < R && y >= 0 && y < C)
            return true;
        return false;
    }

    public static Queue<Point> getFloatingCluster(boolean[][] visited) {
        Queue<Point> list = new LinkedList<>();
        // 밑에서 부터 담으면 안됨..
        // 밑으로 끌어내려야 하기 때문에
        // 바닥과 가까운 미네랄 부터 방문+담음
        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && map[i][j] == 'x')
                    list.add(new Point(i, j));
            }
        }
        return list;
    }

    // 이건 Queue안쓰고 그냥 list써도 될듯.
    public static void moveCluster(Queue<Point> list, boolean[][] visited) {
        int moveFloor = Integer.MAX_VALUE;
        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                // x고 방문하지 않음? > 떠있는 클러스터 미네랄
                if (map[i][j] == 'x' && !visited[i][j]) {
                    int move = 1;
                    while (isIn(i + move, j) && map[i + move][j] == '.')
                        move++;
                    move--;
                    moveFloor = move != 0 ? Math.min(moveFloor, move) : moveFloor;
                    for (int k = i; k >= 0; k--) {
                        visited[k][j] = true;
                    }
                }
            }
        }
        // 이동이 없다.
        if (moveFloor == Integer.MAX_VALUE)
            return;

        while (!list.isEmpty()) {
            Point mineral = list.poll();
            map[mineral.x + moveFloor][mineral.y] = 'x';
            map[mineral.x][mineral.y] = '.';
        }
    }

    public static void print2() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            sb.append(map[i]).append("\n");
        }
        System.out.print(sb.toString());
    }
}
