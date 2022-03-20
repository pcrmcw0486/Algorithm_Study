package SAMSUNG.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q3190
 * @문제이름 : 뱀
 * @난이도 : Gold V
 * @date : 2022-03-11 오후 12:50
 * @문제이해 뱀이 돌아다님.
 * 뱀의 길이는 1임
 * 처음에는 오른쪽을 향함
 * 다음을 시뮬레이션함
 * 1. 뱀은 몸 길이를 늘려 머리를 다음칸에 위치시킴
 * 2. 이동칸에 사과가 있다면 사과가 없어지고 꼬리는 움직이지않음.
 * 3. 이동칸에 사과가 없다면 몸길이를 줄여 꼬리가 위치한 칸을 비움.
 * 다음 K개 줄에 사과 위치가 주어짐.(1,1)에는 사과가 없다.
 * 뱀의 방향 변환 횟수
 * x초가 끝난 후에 (방향을 돌린다)
 * 게임이 몇 초 후에 끝날까?
 * @알고리즘
 * @접근방법
 */
public class Q3190 {
    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        boolean[][] isApple = new boolean[N + 1][N + 1];
        boolean[][] isSnake = new boolean[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            isApple[u][v] = true;
        }
        int[] change = new int[10001];
        //시간이 문제임.
        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int c = st.nextToken().charAt(0) == 'D' ? 5 : 3;
            change[t] = c;
        }

        ArrayDeque<int[]> snake = new ArrayDeque<>();
        snake.add(new int[]{1, 1});
        isSnake[1][1] = true;
        int d = 0;
        int t = 0;
        while (true) {
            t++;
            int[] s = snake.peekFirst();
            int nx = s[0] + dir[d][0];
            int ny = s[1] + dir[d][1];
            if(nx<1||ny<1||nx>N||ny>N) break;
            if(isSnake[nx][ny]) break;
            isSnake[nx][ny] = true;
            snake.addFirst(new int[]{nx, ny});
            if (isApple[nx][ny]) {
                isApple[nx][ny] = false;
            } else {
                int[] a = snake.removeLast();
                isSnake[a[0]][a[1]] = false;
            }
            d = (d + change[t])%4;
        }
        System.out.println(t);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
