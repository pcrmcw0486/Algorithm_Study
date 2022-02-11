/*
달이 차오른다, 가자 Gold I
의식의 흐름대로 
1. 탐색 방법에 대해 bfs, dfs
 -> 이 때 고려할 점, visit체크로 하는게 아니다..
 -> 다시 돌아와야하는 경우를 고려하여야 한다. 
    -> 그렇다면 돌아오는 경우는 언제인가?
    -> 막다른길이 벽이라면? 가지 않음.
    -> 열쇠가 필요한 문이라면? 문이있다면 가볼만 함.
    -> 열쇠를 찾았다면?
      -> 더 갈수도, 돌아올 수도.
      그렇다면 visit의 상태 체크는?
 -> 최단거리 -> priorityQueue?
 > 지금 생각나는 방법은 열쇠를 찾았을 때
 > 새롭게 탐색 시작하는 방법인데,
 > f a 0 b d 1 이라면?
 > b에서 다시 시작. -> d/0위치 
 > 이때 visitcheck에 대하여 d에서 b를 가는 순간
 > 다시 b에서 다시시작 -> d/0 위치 와 같이 무한루프가 걸릴 수 있는데
 > 만약 1 f a B 0 b d . .  이면? 가지고 있는 열쇠상태를 체크하면서
 > 갈지 안갈지 check하는방법? 무한루프의 위험성. 또는 스택이 가득찰 가능성.
 > 해당 키가 처음이라면 새로 시작.
 > 처음이 아니라 가지고 있는 키라면 . 으로 인식하고 
 키는 배열로도 가능하고 bit로도 가능하고.
2. 가장빠르게 찾는 방법?


isAlphabetic이 생각보다 시간 많이 잡아먹음.
 -> 
 */
package tmp;

import java.util.*;
import java.io.*;

public class Q1194 {
    private static class Position implements Comparable<Position> {
        int x;
        int y;
        int status;
        int dist;

        public Position(int x, int y, int status, int dist) {
            this.x = x;
            this.y = y;
            this.status = status;
            this.dist = dist;
        }

        @Override
        public int compareTo(Position o) {

            return this.dist - o.dist;
        }
    }

    static boolean[][][] visited;
    static char[][] map;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][1 << 6];
        PriorityQueue<Position> queue = new PriorityQueue<Position>();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '0') {
                    visited[i][j][0] = true;
                    queue.add(new Position(i, j, 0, 0));
                }
            }
        }
        int answer = -1;
        while (!queue.isEmpty()) {
            Position cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int dist = cur.dist;
            int status = cur.status;

            if (map[x][y] == '1') {
                answer = dist;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int newStatus = status;
                if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1 || visited[nx][ny][status])
                    continue;
                if (map[nx][ny] == '#')
                    continue;

                if (Character.isAlphabetic(map[nx][ny])) {
                    if (Character.isLowerCase(map[nx][ny])) {
                        newStatus = status | (1 << (map[nx][ny] - 'a'));
                    }
                    if (Character.isUpperCase(map[nx][ny])) {
                        if (((1 << (map[nx][ny] - 'A')) & status) == 0)
                            continue;
                    }
                }
                visited[nx][ny][newStatus] = true;
                queue.add(new Position(nx, ny, newStatus, dist + 1));

            }
        }
        System.out.println(answer);
    }
}