package SAMSUNG.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q13460
 * @문제이름 : 구슬탈출 2
 * @난이도 : Gold I
 * @date : 2022-02-11 오후 1:41
 * @author : pcrmcw0486
 * @문제이해
 * 빨간 구슬과 파란 구슬이 보드 안에 있는데 움직여서 구멍에 맞춰 빼내야함
 * 게임의 목표는 "빨간 구슬을 구멍을 통해 빼내는 것"
 *  단, "파란 구슬이 구멍에 들어가면 안됨"
 * 왼쪽, 오른쪽, 위쪽, 아래쪽으로 기울인다. 공은 동시에 움직인다.
 * 파란 구슬이 구멍에 빠지면 실패 동시에 빠져도 실패
 * 기울이는 동작은 더이상 구슬이 움직이지 않을 때 까지.
 * "최소 몇 번 만에 빨간 구슬이 구멍을 통해 나올 수 있을까?"
 * 10번이하로 빼내지 못하면 -1
 * @알고리즘
 * 그래프 탐색
 * @접근방법
 * 여러 조건이 있음
 * 방향을 각 step 마다 4번씩 돌려보아야함.
 * 각 step마다 방향을 움직일때, 무엇을 먼저 움직일지 정해야함. 그리고 같이 움직여야함.
 * 하나의 상태는 다음을 포함해야함
 *  (빨간 공) (파란 공) (현재까지의 스텝) (그 전 방향?) 다시 반대로 갈 필요는 없으니깡.
 *   도착했을 때 blue도 끝까지 갈수있는지 확인해봐야함.
 *   ** 움직이는 순서 정하기 **
 *   빨강이냐 파랑이냐 먼저 정해야함..
 *   순서를 제대로 정하면 될거같음.

*/
public class Q13460 {
    static class Point{
        int x, y;
        Point(int x, int y) {
            this.x =x;
            this.y =y;
        }
    }
    static class Status{
        Point red;
        Point blue;
       // int prevDir;
        int step;
        public Status(Point red, Point blue, int step) {
            this.red = red;
            this.blue = blue;
           // this.prevDir = prevDir;
            this.step = step;
        }

        @Override
        public String toString() {
            return "Status{" +
                    "red= (" + red.x + " , " + red.y +
                    "), blue= (" + blue.x + " , " + blue.y +
                    "), prevDir=" +
                    ", step=" + step +
                    '}';
        }
    }
    static char[][] map;
    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        boolean[][][][] visited = new boolean[N][M][N][M];
        Point red = null;
        Point blue = null;
        for(int i =0;i<N;i++){
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                map[i][j] = c;
                if(c=='R'){
                    red = new Point(i,j);
                }if(c=='B'){
                    blue= new Point(i,j);
                }
            }
        }

        Status status = new Status(red, blue, 0);
        Queue<Status> queue = new LinkedList<>();
        queue.add(status);
        visited[red.x][red.y][blue.x][blue.y] = true;
        int ans = -1;
        //BFS
        while(!queue.isEmpty()){
            Status cur = queue.poll();
         //   System.out.println(cur);
            if(cur.step>10) break;
            if(map[cur.blue.x][cur.blue.y]=='O') continue;
            if (map[cur.red.x][cur.red.y]=='O') {
                    ans = cur.step;
                    break;
            }
            for(int i =0;i<4;i++){
            //getPriority
//                if(cur.prevDir != -1){
//                    //반대방향은 안해요.
//                    if(cur.prevDir == i) continue;
//                    if(((cur.prevDir+2)%4)==i) continue;
//                }
                Point nRed = new Point(cur.red.x,cur.red.y);
                Point nBlue = new Point(cur.blue.x,cur.blue.y);
                //red가 먼저 움직일때.
                if(getPriority(cur.red,cur.blue, i)){
                    move(nRed, nBlue, i);
                    move(nBlue,nRed,i);
                }else{
                    move(nBlue,nRed,i);
                    move(nRed,nBlue,i);
                }
                if(!visited[nRed.x][nRed.y][nBlue.x][nBlue.y]){
                    visited[nRed.x][nRed.y][nBlue.x][nBlue.y] = true;
                    queue.add(new Status(nRed, nBlue, cur.step + 1));
                }
            }
        }
        System.out.println(ans);
    }
    public static boolean getPriority(Point red, Point blue, int dir){
        if(dir ==0) return red.x<=blue.x;
        else if(dir ==1) return red.y>=blue.y;
        else if(dir ==2) return red.x>=blue.x;
        else return red.y<=blue.y;
    }
    public static void move(Point movingBall, Point anotherBall, int d){
        int nx = movingBall.x;
        int ny = movingBall.y;
        while(map[nx][ny]!='#'){
            if(map[anotherBall.x][anotherBall.y] != 'O'){
                if(nx==anotherBall.x && ny == anotherBall.y)
                    break;
            }
            movingBall.x = nx;
            movingBall.y = ny;
            if(map[nx][ny] == 'O') break;
            nx += dir[d][0];
            ny += dir[d][1];
        }
    }
}
