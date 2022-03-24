package DayByDay._0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q4179
 * @문제이름 : 불!
 * @난이도 : Gold IV
 * @date : 2022-03-24 오후 3:59
 * @문제이해 지훈이는 미로에서 일을 한다. 탈출하게 도와주자
 * 지훈이의 위치와 불이 붙은 위치를 감안하여, 지훈이가 불 타기전 탈출 여부, 얼마나 빨리 탈출할 수 있는지 화깅ㄴ
 * 지훈이와 불은 매분마다 한칸씩 수평 또는 수직으로 이동한다.
 * 지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.
 * 지훈이와 불은 벽이 있는 공간은 통과하지 못한다.
 * 불 map
 * 지훈이는 따로 이동한다.
 * 지훈이가 먼저 움직여야할까 불이 먼저 움직여야할까?
 * 불이 먼저 움직이고 지훈이가 움직이는게 맞다.
 * 불이 있는 곳에 지훈이는 못가기 때문이다.
 * queue는 결국 지훈이만 움직이면된다.
 * 지훈이는 미로의 가장자리에 도착하면 끝난다.
 * 현재 지훈이가 존재한다면, 불에 타고 있지않는 상태이다
 * 지훈이가 움직여서불이 있으면 타는거임. 불이막는거지.
 * @알고리즘 BFS
 * @접근방법
 * Queue 하나에 담아서 해도되겟네 지훈이인지만 확인시켜줘도됨
 * 이런 문제는 '순서를 어떻게 정하냐의 문제가 매우 중요함'
 * Queue하나에 담는다면 queue안에서 처리할 순서를 잘 정해주어야 한다.
 * 또 다른 풀이는 불 bfs minimum으로 다 채워주고
 * 지훈이가 bfs돌면서 time보다 작은 시간에 도착할 때만 가능한 식으로 구현하여도 가능하다.
 * 결국 보는 횟수는 같긴한데, 이게 더 나을지도.
 */
public class Q4179 {
    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        boolean[][] isFire = new boolean[N][M];
        boolean[][] isVisitied = new boolean[N][M];
        Queue<Point> firePos = new LinkedList<>();
        Queue<Point> personPos = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'J') {
                    isVisitied[i][j] = true;
                    personPos.add(new Point(i, j));
                } else if (map[i][j] == 'F') {
                    isFire[i][j] = true;
                    firePos.add(new Point(i, j));
                }
            }
        }
        int ans = -1;
        int time = 0;
        int nx, ny;
        while (!personPos.isEmpty()) {
            time++;

            //불이 먼저 번진다.
            int size = firePos.size();
            for (int i = 0; i < size; i++) {
                Point cur = firePos.poll();
                for (int j = 0; j < 4; j++) {
                    nx = cur.x + dir[j][0];
                    ny = cur.y + dir[j][1];
                    if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) continue;
                    if(map[nx][ny] == '#') continue;
                    if (!isFire[nx][ny]) {
                        isFire[nx][ny] = true;
                        firePos.add(new Point(nx, ny));
                    }
                }
            }
            size = personPos.size();
            for (int i = 0; i < size; i++) {
                Point cur = personPos.poll();
                if(cur.x == 0 || cur.x==N-1||cur.y ==0||cur.y==M-1){
                 ans = time;
                 personPos.clear();
                 break;
                }
                for (int j = 0; j < 4; j++) {
                    nx = cur.x + dir[j][0];
                    ny = cur.y + dir[j][1];
                    if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) continue;
                    if(map[nx][ny] == '#') continue;
                    if (!isFire[nx][ny] && !isVisitied[nx][ny]) {
                        isVisitied[nx][ny] = true;
                        personPos.add(new Point(nx, ny));
                    }
                }
            }
        }
        System.out.println(ans==-1?"IMPOSSIBLE":ans);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
