package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q1799
 * @문제이름 : 비숍
 * @난이도 : Gold I
 * @date : 2022-02-28 오후 2:58
 * @문제이해 비숍은 대각선으로 움직일 수 있다.
 * 비숍이 놓일 수 없는 곳이 있다.(놓일순 없지만, 지나갈 수는 있다)
 * 주어진 체스판에서 서로가 잡을 수 없는 위치에 놓을 수 있는 비숍의 최대 개수를 구하시오.
 * 체스판의 크기는 10이하. (놓을 수 있다면 1 없다면 0)
 * @알고리즘 10개 이하 많이 놓아봐야 2N-1개
 * @접근방법
 * 체스판에 왜 색칠이 되어있는지 알고 있나?
 */
public class Q1799 {
    static int N;
    static char[][] map;
    static boolean[] LURD;
    static boolean[] RULD;
    static int cnt, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        LURD = new boolean[2 * N];
        RULD = new boolean[2 * N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine().replaceAll(" ","");
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        ans =0;
        ans = findMaxBishop(0) + findMaxBishop(1);
        System.out.println(ans);
    }

    private static int findMaxBishop(int depth) {
        if (depth >= (2 * N)-1){
            return 0;
        }
        //x+y = depth
        int x;
        int y;
        int ret =-1;
        //depth는 최대 2N-2 (N-1) = N-1
        for (x = 0; x <= Math.min(depth, N - 1); x++) {
            y = depth - x;
            if(y > N-1) continue;
            if (map[x][y] == '1') {
                if (RULD[x + y] || LURD[y - x + N]) continue;
            //    System.out.println("depth : " + depth + " pos : ( " + x + " , " + y + " )");
                RULD[x + y] =  LURD[y - x + N] = true;
                ret = Math.max(ret, findMaxBishop(depth + 2)+1);
                RULD[x + y] = LURD[y - x + N] = false;
            }
        }
        if(ret < 0)
            ret = findMaxBishop(depth + 2);
        return ret;
    }
}
