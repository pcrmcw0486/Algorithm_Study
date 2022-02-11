/*
https://www.acmicpc.net/problem/6087
레이저 통신 Gold IV
*** 접근 방향 및 문제 조건***
1초, 128MB
W*H 크기의 지도에 레이저(C)가 무조건 두개 주어짐(연결 보장)
벽을 통과하여 다른 레이저로 보내는데, 거울은 90도의 역할.
최소 거울 개수
> 문제 간소화하면 최소 길이 미로찾기에서 방향이 바뀌는 횟수 최소화.
근데 중요한걸 최소 길이가 아니라 최소 방향이라는거?
즉, 최소 길이가 아니더라도 최소방향으로 갈수가 있다.
근데 이게 맞는 말인가? 맞는말이지
. . . . .
. * * * C
. * * . .
. * . . *
C . . * *  라면 최소 길이는 6 이지만, 방향이 5번바뀌고
최소 방향은 2번이니까.
즉, 최소 길이를 구하는 것이 아님.
*** 알고리즘 자료구조 스킬 ***
그렇다면 한방향으로 쭉가서 막힐 때 변경하는 것이 좋은가?
. * * C *
. * * . .
. . . . .
. * * * * 
C . . . .  끝까지 한방향으로 가는 것이 항상 좋은 것은 아니다.
그렇다면 갈 수 있는 길을 모두 구해야하는가 그 때 시간은?
100 * 100 dfs 잘못짜면 ㅈ망.

dp는 어떤가? dp로 방향을 설정할 수가 있나?
4방향 기준 SolveByCategory.dp?

그냥 쭉가서 backtracking하는게 맞는 것 같은데.
ㅇㅋ 이 방법으로 간다. 기존의 최소 길이구하는 것이랑은 다른 문제임.
한 방향 기준 쭉 간다음에 안되면 back
그렇다면 promising은? 1. 외부, visit 이면될듯.

*** 자료구조 알고리즘 스킬 ***
dfs, Character배열을 사용.
 */
package challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node1 {
    int x;
    int y;
    int dist;
    int prevDir;

    Node1(int x, int y, int prevDir, int dist) {
        this.x = x;
        this.y = y;
        this.prevDir = prevDir;
        this.dist = dist;
    }
}

public class Q6087 {
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int W;
    static int H;
    static int[][] visit;
    static char[][] map;
    static Node1[] lazerPosition;
    static BufferedReader br = null;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        init();
        solution();
    }

    public static void init() throws IOException {
        map = new char[H][W];
        visit = new int[H][W];
        lazerPosition = new Node1[2];
        int lazerIdx = 0;
        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                visit[i][j] = -1;
                map[i][j] = line.charAt(j);
                if (line.charAt(j) == 'C') {
                    lazerPosition[lazerIdx] = new Node1(i, j, -1, 0);
                    lazerIdx++;
                }
            }
        }
    }

    public static void solution() {
        Queue<Node1> routeQ = new LinkedList<>();
        routeQ.offer(lazerPosition[0]);
        visit[lazerPosition[0].x][lazerPosition[0].y] = -1;
        int dist = 0;
        boolean success = false;
        while (!routeQ.isEmpty() && !success) {
            int size = routeQ.size();
            for (int i = 0; i < size; i++) {
                Node1 now = routeQ.poll();
                if (now.x == lazerPosition[1].x && now.y == lazerPosition[1].y) {
                    success = true;
                    System.out.println(dist - 1);
                    break;
                }
                for (int j = 0; j < dx.length; j++) {
                    if (j == now.prevDir)
                        continue; // 굳이 이전꺼 어차피 안되는데 여기서 끊자
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];
                    while (true) {
                        if (nx < 0 || nx > H - 1 || ny < 0 || ny > W - 1)
                            break;
                        if (map[nx][ny] == '*')
                            break;
                        if (visit[nx][ny] >= 0) {
                            if (visit[nx][ny] == dist) {
                                nx += dx[j];
                                ny += dy[j];
                                continue;
                            } else
                                break;
                        } else {
                            visit[nx][ny] = dist;
                            routeQ.offer(new Node1(nx, ny, j, dist));
                            nx += dx[j];
                            ny += dy[j];
                        }
                    }
                }
            }
            dist++;
        }
    }

}
