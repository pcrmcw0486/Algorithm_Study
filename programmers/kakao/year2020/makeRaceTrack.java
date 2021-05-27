package programmers.kakao.year2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

class Position1 {
    int x;
    int y;
    // curve count
    int curveCount;
    int prevDir;
    int cost;

    Position1(int x, int y, int curveCount, int prevDir, int cost) {
        this.x = x;
        this.y = y;
        this.curveCount = curveCount;
        this.prevDir = prevDir;
        this.cost = cost;
    }
}

public class makeRaceTrack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++)
            board[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int result = solution(board);
        System.out.println(result);
    }

    public static int solution(int[][] board) {
        int ans = Integer.MAX_VALUE;
        int N = board[0].length;
        int[][][] visit = new int[N][N][N * N * N];
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };
        Queue<Position1> routeQ = new LinkedList<>();

        routeQ.offer(new Position1(0, 0, 0, -1, 0));
        while (!routeQ.isEmpty() || ans == Integer.MAX_VALUE) {
            int size = routeQ.size();
            for (int i = 0; i < size; i++) {
                Position1 now = routeQ.poll();
                if (now.x == N - 1 && now.y == N - 1) {
                    ans = Math.min(ans, visit[now.x][now.y][now.curveCount] + 500 * now.curveCount);
                    break;
                }
                for (int j = 0; j < dx.length; j++) {
                    if (j == now.prevDir)
                        continue;
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];
                    int money = now.cost + 100;
                    while (true) {
                        if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1)
                            break;
                        if (board[nx][ny] == 1)
                            break;
                        if (visit[nx][ny][now.curveCount] == 0 || now.cost <= visit[nx][ny][now.curveCount]) {
                            visit[nx][ny][now.curveCount] = money;
                            routeQ.offer(new Position1(nx, ny, now.curveCount + 1, j, money));
                            money += 100;
                            nx += dx[j];
                            ny += dy[j];
                        }

                    }
                }
            }
        }
        return ans;
    }
}
