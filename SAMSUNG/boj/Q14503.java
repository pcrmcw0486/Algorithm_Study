package SAMSUNG.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q14503
 * @문제이름 : 로봇 청소기
 * @난이도 : Gold V
 * @date : 2022-03-21 오후 12:53
 * @author : pcrmcw0486
 * @문제이해
 * 로봇 청소기 N*M 직사각형 1*1 벽 또는 빈칸, 바라보는 방향이 있음.
 * (r,c)
 * 1. 현재 위치 청소
 * 2. 현재 방향을 기준으로 왼쪽방향부터 탐색
 *  2.1 왼쪽 방향에 청소하지 않은 공간이 있다면, 그 방향으로 회전하여 다음 한칸을 전진함
 *  2.2 왼쪽방향에 청소할 공간이 없으면 일단 회전하고 다시 탐색
 *  2.3 네 방향 모두 청소가 이미 되어 있다면 바라보는 방향을 유지한 채로 한칸 후진하고 2번으로 돌아감
 *  2.4 네 방향 모두 청소가 이미 되어있거나 벽(움직이지 못하고) 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우 작동을 멈춘다.
 * @알고리즘

 * @접근방법
    0 북쪽 1 동쪽 2 남쪽 3 서쪽
*/
public class Q14503 {
    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int EMPTY =0;
    static int WALL = 1;
    static int CLEAN = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        //map true이면 갈 수 있음 false면 벽
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        RobotCleaner rc = new RobotCleaner(x, y, d);
        int cnt =0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean isDone = false;
        while (!isDone) {
            //1. 현재 위치를 청소한다.
            if(map[rc.x][rc.y] ==EMPTY){
                map[rc.x][rc.y] = CLEAN;
                cnt++;
            }
           // System.out.println("( " + rc.x + " " + rc.y + " " + rc.dir + " )");
            //2. 방향을 돈다
            boolean flag = false;
            d = rc.dir;
            for (int i = 0; i < 4; i++) {
                d = (d+3)%4;
                int nx = rc.x + dir[d][0];
                int ny = rc.y + dir[d][1];
                if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
                if(map[nx][ny]==WALL || map[nx][ny] == CLEAN) continue;
                flag = true;
                rc.x = nx;
                rc.y = ny;
                rc.dir = d;
                break;
            }
            //갈 곳이 없다면
            if(!flag){
                //후진방향
                int reverseDir = (rc.dir+6)%4;
                // 후진 check 종료 또는
                int nx = rc.x, ny = rc.y;
                    nx = nx + dir[reverseDir][0];
                    ny = ny + dir[reverseDir][1];
                    if(nx<0||ny<0||nx>N-1||ny>M-1 || map[nx][ny]==WALL){
                        isDone = true;
                        break;
                    }
                    rc.x = nx;
                    rc.y = ny;
            }
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    if (rc.x == i && rc.y == j) {
//                        System.out.print("$ ");
//                    } else if (map[i][j] == EMPTY) {
//                        System.out.print(0 + " ");
//                    }else
//                        System.out.print(1 + " ");
//                }
//                System.out.println();
//            }
        }
        System.out.println(cnt);
    }

    static class RobotCleaner{
        int x,y;
        int dir;

        public RobotCleaner(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
