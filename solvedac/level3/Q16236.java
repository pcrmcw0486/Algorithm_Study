package solvedac.level3;

/*
2021.12.06
문제번호 : Q16236
이름 및 난이도 : 아기상어 Gold IV
문제이해 
-----------------
NN 크기 물고기 M마리 아기상어 1
첫 아기상어 크기 2
1초에 상하좌우 인접한칸씩 이동.

자신보다 큰 물고기가 있는 칸 못지나감.
자신보다 작은 물고기만 먹을 수 있다.
크기가 같다면 먹을 수 없지만 지나갈 수는 있다.
- 더 이상 먹을 물고기가 없다면 아기상어는 엄마상어에 도움 요청
- 먹을 수 있는 물고기가 1마리라면 물고기 먹으러감.
- 1마리보다 많다면 가장 가까운 물고기 먹으러감.
    - 최소값으로.
    - 거리가 가까운 물고기가 많다면 가장 위에 있는 물고기.
      그 중에서도 왼쪽.
    물고기를 먹으면 크기가 증가함.
    도움요청안하고 물고기를 먹을 수 있는가?
    물고기크기는 6.
    자신 크기와 같은 수의 물고기를 먹으면 크기가 증가함.
    ex) 크기가2라면 물고기 2마리 먹어야 크기가 3이됨.+

접근 방법 :
bfs로 접근해야 가장 빠르게 도착할 수 있다.

시작 전 먹을 수 있는 상어의 개수를 계산한다.
먹을 수 있는 상어의개수가 >0 이라면
먹으러간다.(BFS)  return cnt;
cnt =0 갈 수 없다.(끝)
cnt>0 갈 수 있다. 즉 도착했다.
도착 점에서
  현재 사이즈와 비교하여 먹을 수 있는 상어의 개수가 있다면 구하러간다.

Point는 자신보다 작은 물고기를 자신의 수만큼 잡아 먹어야 한다.
먹을 수 있는가? > 먹는다. > 커졌는가??(update)
먹을 수 있는 물고기는 가장 위 또는 가장 왼쪽인가?

 - 물고기 개수를 알고 있어야한다.
 - 현재 babyshark의 size는?
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q16236 {
    static Point babyShark;
    static int[][] dir = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } }; // 방향 유의
    static int[] count;
    static int sharkSize = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        count = new int[8];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int o = Integer.parseInt(st.nextToken());
                map[i][j] = o;
                if (o > 0 && o < 9)
                    count[o]++;
                if (o == 9) {
                    babyShark = new Point(i, j, 0);
                    map[i][j] = 0;
                }
            }
        }

        Queue<Point> queue;
        boolean[][] visited;
        int eatCnt = 0;

        while (true) {
            if (isPossible(sharkSize)) { // 먹을 수 있는가? (먹을 것이 있는가?)
                queue = new LinkedList<Point>();
                visited = new boolean[N][N];
                queue.add(babyShark);
                visited[babyShark.x][babyShark.y] = true;

                int dist = 0;
                Point next = new Point(-1, -1, 0);
                // 먹을 수 있는 물고기 중 가장 최소거리 물고기 찾기.
                // 이 때 최소거리 물고기 중 가장 위쪽 + 가장 왼쪽 이여야한다.
                // System.out.println(babyShark.x + " " + babyShark.y);
                while (!queue.isEmpty()) {
                    Point cur = queue.poll();

                    if (dist != 0 && cur.dist > dist) { // 이미 한번 갈 수 있는 곳을 확인하고 (최소 거리 확정) 이거보다 큰 경우 끝.
                        break;
                    }
                    int fishSize = map[cur.x][cur.y];
                    // Eatable
                    if (fishSize != 0 && fishSize < sharkSize) {
                        if (dist == 0) { // 처음 들어옴.
                            next.x = cur.x;
                            next.y = cur.y;
                            dist = cur.dist;
                            continue;
                        }
                        // 갈 수 있는 다른 값이 들어온다
                        else {
                            if (cur.x < next.x) {
                                next.x = cur.x;
                                next.y = cur.y;
                            } else if (cur.x == next.x && cur.y < next.y) {
                                next.x = cur.x;
                                next.y = cur.y;
                            }
                            continue;
                        }
                    }

                    // 방향 전파
                    for (int i = 0; i < 4; i++) {
                        int nx = cur.x + dir[i][0];
                        int ny = cur.y + dir[i][1];
                        if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
                            continue;
                        if (visited[nx][ny])
                            continue;
                        if (map[nx][ny] <= sharkSize) { // 크기가 같다면 갈 수 있다.
                            visited[nx][ny] = true;
                            queue.add(new Point(nx, ny, cur.dist + 1));
                        }
                    }
                }

                if (dist == 0) {
                    // 갈 곳이 없음.
                    break;
                }

                // update
                babyShark.x = next.x;
                babyShark.y = next.y;
                count[map[babyShark.x][babyShark.y]]--;
                map[babyShark.x][babyShark.y] = 0;
                eatCnt++;
                ans += dist;
                // 상어크기 update
                // 상어크기가 6보다 커진다면 6으로 고정.
                if (eatCnt == sharkSize) {
                    eatCnt = 0;
                    sharkSize++;
                    if (sharkSize > 7)
                        sharkSize = 7;
                }
            } else
                break;
        }

        System.out.println(ans);
    }

    public static boolean isPossible(int size) {
        // 상어 size보다 작은 물고기가 있는가?
        for (int i = 1; i < size; i++) {
            if (count[i] > 0)
                return true;
        }
        return false;
    }

    static class Point {
        int x, y, dist;

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
