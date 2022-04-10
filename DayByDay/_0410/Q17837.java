package DayByDay._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q17837
 * @문제이름 : 새로운 게임 2
 * @난이도 : Gold II
 * @date : 2022-04-10 오후 4:15
 * @문제이해 N*N체스판, K개의 사용하는 말
 * 하나의 말 위에 다른 말을 올릴 수 있다.
 * 체스판은 흰,빨, 파 셋중 하나이다.
 * 말은 1~K까지 번호가 있고, 이동방향도 미리 정해져있따.
 * 턴이 진행되면서 말이 4개 이상 쌓이면 종료
 * 턴 한번은 1~K까지 한번 움직이는게 턴 한번임.
 * A번 말이 이동하려는 칸이
 * -흰색 : 칸으로 이동, 칸에 이미 있다면 위에 A를 올림
 * A번 말 위에 다른 말이 있다면, A번 말과 모두 같이 이동
 * - 빨강 : 이동 후 A번 말과 그 위 모든 말의 쌓인 순서를 바꾼다.
 * - 파랑 : A번말의 이동방향을 반대로 하고 한칸 이동, 반대로 바꾼후 파란색이면 가만히
 * - 체스판을 벗어난다면, 파란색과 같이 행동한다.
 * @알고리즘
 * @접근방법
 */
public class Q17837 {
    static int[][] dir = new int[][]{{0,0},{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int WHITE =0, RED = 1, BLUE = 2;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N + 1][N + 1];
        HashMap<Point, Stack<Integer>> stackMap = new HashMap<>();
        Point[] horses = new Point[K + 1];
        for (int i = 0; i < N + 1; i++) Arrays.fill(map[i], -1);

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i < K + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

                horses[i] = new Point(i, x, y, dir);
                Stack<Integer> info = new Stack<>();
                info.push(i);
                stackMap.put(horses[i], info);
        }
        int turn = 0;
        int ans = -1;
        boolean gameOver = false;
        while (!gameOver) {
            turn++;
            for (int targetNum = 1; targetNum < K + 1; targetNum++) {
                Point p = horses[targetNum];
                int nx = p.x + dir[p.dir][0];
                int ny = p.y + dir[p.dir][1];
                if (outOfBound(nx, ny, N) || map[nx][ny] == BLUE){
                    p.dir = changeDirection(p.dir);
                    nx = p.x + dir[p.dir][0];
                    ny = p.y + dir[p.dir][1];
                }
                if (outOfBound(nx, ny, N) || map[nx][ny] == BLUE) continue;

                if (map[nx][ny] == WHITE) {
                    Point nextPoint = new Point(0, nx, ny, 0);
                    if (!stackMap.containsKey(nextPoint)) {
                        stackMap.put(nextPoint, new Stack<Integer>());
                    }
                    Stack<Integer> nextStack = stackMap.get(nextPoint);
                    Stack<Integer> prevStack = stackMap.get(p);
                    Stack<Integer> temp = new Stack<>();
                    int movedBlockNum = -1;
                    while (!prevStack.isEmpty()) {
                        if (prevStack.peek() == targetNum) {
                            temp.push(prevStack.pop());
                            break;
                        }
                        movedBlockNum = prevStack.pop();
                        horses[movedBlockNum].x = nx;
                        horses[movedBlockNum].y = ny;
                        temp.push(movedBlockNum);
                    }
                    if (prevStack.isEmpty()) {
                        stackMap.remove(p);
                    }
                    horses[targetNum].x = nx;
                    horses[targetNum].y= ny;
                    if(nextStack.size() + temp.size() >= 4){
                        gameOver =true;
                        break;
                    }
                    while (!temp.isEmpty()) {
                        nextStack.push(temp.pop());
                    }
                } else if(map[nx][ny] == RED){
                    Point nextPoint = new Point(0, nx, ny, 0);
                    if (!stackMap.containsKey(nextPoint)) {
                        stackMap.put(nextPoint, new Stack<Integer>());
                    }
                    Stack<Integer> nextStack = stackMap.get(nextPoint);
                    Stack<Integer> prevStack = stackMap.get(p);
                    while(!prevStack.isEmpty()){
                        if (prevStack.peek() == targetNum) {
                            nextStack.push(prevStack.pop());
                            break;
                        }
                        int movedNum = prevStack.pop();
                        horses[movedNum].x = nx;
                        horses[movedNum].y = ny;
                        nextStack.push(movedNum);
                    }
                    if (prevStack.isEmpty()) {
                        stackMap.remove(p);
                    }
                    horses[targetNum].x = nx;
                    horses[targetNum].y = ny;
                    if(nextStack.size()>= 4){
                        gameOver = true;
                        break;
                    }
                }
            }
            if (turn > 1000) {
                break;
            }
        }
        if(gameOver) ans = turn;
        System.out.println(ans);
    }

    public static int changeDirection(int dir) {
        if (dir == 1) return 2;
        else if (dir == 2) return 1;
        else if (dir == 3) return 4;
        else return 3;
    }

    public static boolean outOfBound(int x, int y, int SIZE) {
        return x < 1 || y < 1 || x > SIZE || y > SIZE;
    }

    static class Point {
        int num;
        int x, y;
        int dir;

        public Point(int num, int x, int y, int dir) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return N*x+y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "num=" + num +
                    ", x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    '}';
        }
    }
}
