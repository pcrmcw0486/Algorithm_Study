package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @문제번호 : Review_1799
 * @문제이름 : 비숍
 * @난이도 : Gold I
 * @date : 2022-03-07 오후 4:17
 * @author : pcrmcw0486
 * @문제이해
* @알고리즘
 * 백트래킹 구현,
 * @접근방법
*/
public class Review_1799 {
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
        if (depth >= (2 * N) - 1) {
            return 0;
        }
        //x + y = depth
        int x, y;
        int ret = -1;
        for (x = 0; x <= Math.min(depth, N - 1); x++) {
            y = depth-x;
            if(y>N-1) continue;
            if (map[x][y] == '1') {
                if(RULD[x+y] || LURD[y-x+N]) continue;
                RULD[x+y] = LURD[y-x+N] = true;
                ret = Math.max(ret, findMaxBishop(depth + 2) + 1);
                RULD[x+y] = LURD[y-x+N] = false;
            }
        }
        if (ret < 0) {
            ret = findMaxBishop(depth + 2);
        }
        return ret;
    }
}
