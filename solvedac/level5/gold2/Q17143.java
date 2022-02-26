package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @문제번호 : Review_17143
 * @문제이름 : 낚시왕
 * @난이도 : GoldII
 * @date : 2022-02-26 오후 3:40
 * @author : pcrmcw0486
 * @문제이해
 * 빡구현 문제.
 * 상어 움직임이 Point인 문제이다. 움직임에 주의해서 작성.
 * @알고리즘
 * 구현
 * @접근방법
 * 주어진 범위 N에 대해 2*(N-1) 의 범위에서 놀게 된다.
 * 나는 좌표 0~N-1이 편하니까.
*/

public class Q17143 {
    static class Shark{
        int speed;
        int dir;
        int size;

        public Shark(int speed, int dir, int size) {
            this.speed= speed;
        switch (dir){
            case 1:
                this.dir = UP;
                break;
            case 2:
                this.dir = DOWN;
                break;
            case 3:
                this.dir = RIGHT;
                break;
            case 4:
                this.dir = LEFT;
                break;
        }

            this.size = size;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    " size=" + size +
                    ", speed=" + speed +
                    ", dir=" + dir +
                    '}';
        }
    }

    static int map[][];
    static int N,M;
    static Shark[] shark;
    static final int UP =0, RIGHT =1, DOWN =2, LEFT=3;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        shark = new Shark[K+1];
        //상어 입력
        shark[0] = new Shark(0, 0, 0);
        for (int i = 1; i < K + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());       //0위 1 아래 2 오른쪽 3왼쪽  >> 0위 1오른쪽 2 아래 3 왼쪽으로 변경
            int size = Integer.parseInt(st.nextToken());
            shark[i] = new Shark(speed, dir, size);
            map[r][c] = i;
        }
        int ans =0;
        for(int i =0;i<M;i++){
            ans += fishing(i);
            moveShark();
        }
        System.out.println(ans);
    }

    private static void moveShark() {
        int[][] newMap = new int[N][M];
        int nx, ny;
        for(int i =0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] ==0) continue;
                //움직여보자.
                Shark s = shark[map[i][j]];
                nx = i;
                ny = j;
                switch (s.dir){
                    case UP :
                        nx = ((2 * (N - 1) - i) + s.speed)%(2*(N-1));
                        s.dir = DOWN;
                        if(nx >= N-1){
                            nx = 2*(N-1) - nx;
                            s.dir = UP;
                        }
                        break;
                    case DOWN :
                        nx = (i + s.speed) % (2*(N-1));
                        if(nx >= N-1){
                            nx = 2*(N-1) -nx;
                            s.dir = UP;
                        }
                        break;
                    case RIGHT:
                        ny = (j + s.speed) % (2 * (M - 1));
                        if(ny >= M-1){
                            ny = 2*(M-1)-ny;
                            s.dir = LEFT;
                        }
                        break;
                    case LEFT:
                        ny = ((2 * (M - 1)) - j + s.speed) % (2 * (M - 1));
                        s.dir = RIGHT;
                        if (ny >= M - 1) {
                            ny = 2*(M-1) - ny;
                            s.dir  = LEFT;
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + s.dir);
                }
                Shark t = shark[newMap[nx][ny]];
                //System.out.println("shark["+(char)('A'+ map[i][j]-1) +"] (" + i + " , " + j + ") - > ( " + nx + ", " + ny + " )"  );
                if (s.size > t.size) {
                    newMap[nx][ny] = map[i][j];
                }
            }
        }
        map = newMap;
    }

    private static int fishing(int pos) {
        int ret =0;
//        System.out.println("===== "+  pos+ " ======");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(shark[map[i][j]].size + " ");
//            }
//            System.out.println();
//        }
        for(int i =0;i<N;i++){
            if(map[i][pos] != 0){
             //   System.out.println("get " + shark[map[i][pos]]);
                ret = shark[map[i][pos]].size;
                map[i][pos] =0;
                break;
            }
        }


        return ret;
    }
}
