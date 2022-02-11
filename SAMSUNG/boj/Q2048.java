package SAMSUNG.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q2048
 * @문제이름 : 2048(easy)
 * @난이도 : GoldII
 * @date : 2022-02-11 오후 7:48
 * @author : pcrmcw0486
 * @문제이해
 * 2048 게임 구현하기.
 * 5회 돌려서 최대 값 구하기
 * @알고리즘
 * simulation,backtracking(더이상 움직일 수없다면..?) '최대 5회'라는 keyword
 * 복잡하게 한다면 linkedlist로 각각 연결시킬수도 있음.
 * @접근방법
 * 보드의 크기가 20*20 (400)
 * 1회 움직이면서 하는데 naiive하게 한다면 400.
 * 4^5 = 1024
 * 1024 * 400 진짜 많아야. (40만)
 * 더이상 움직여서 값이 증가하지 않는다면,,? ㄴㄴ
 * 8 2 2
 * 8 2 2
 * 2 2 2
 * 84
 * 84
 * 42 증가하지 않았지만 가능성이 있다.
 * 더이상 움직일 곳이 없다면. 이건 합쳐진 개수를 통해 확인해야겟네요.
 *
*/
public class Q2048 {
    static int N;
    static int ans = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        StringTokenizer st;
        for(int i =0;i<N;i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        simulation(map,0);
    }
    public static void simulation(int[][] prevMap, int depth){
        if(depth ==4)  return;
        int[][] prev = new int[N][N];
        for(int i =0;i<N;i++){
            System.arraycopy(prev[i],0,prevMap[i],0,N);
        }
        for(int i =0;i<4;i++){
            int[][] tmp = prev;
            tmp[0][0] = depth;
            if(move(tmp,i)!=0){
                continue;
            }
            simulation(tmp,depth+1);
        }
    }

    public static int move(int[][] map,int d) {
        switch (d){
            case 0:
                return moveUp();
            case 1:
                return moveRight();
            case 2:
                return moveDown();
            case 3:
                return moveLeft();
        }
        return 0;
    }

    private static int moveLeft() {
        return 0;
    }

    private static int moveDown() {
        //TODO
        return 0;
    }

    private static int moveRight() {
        //TODO
        return 0;
    }

    public static int moveUp(){
        return 0;
    }
}
