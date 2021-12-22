package solvedac.level3;
/*
2021.11.06
문제번호 : Q1780
이름 및 난이도 : 종이의 개수 Silver I
문제이해 
-----------------
D&C

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q1780 {
    static int[] cntArr = new int[3]; // -1, 0, 1 순서이고 값에서 +1 한값을 더해준다
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        solve(N, 0, 0);
        for (int a : cntArr) {
            System.out.println(a);
        }
    }

    public static void solve(int len, int x, int y) {
        if (len == 1) {
            cntArr[map[x][y] + 1]++;
            return;
        }
        int nextLen = len / 3;
        // check
        if (!isPossible(len, x, y)) {
            solve(nextLen, x, y);
            solve(nextLen, x + nextLen, y);
            solve(nextLen, x + 2 * nextLen, y);
            solve(nextLen, x, y + nextLen);
            solve(nextLen, x + nextLen, y + nextLen);
            solve(nextLen, x + 2 * nextLen, y + nextLen);
            solve(nextLen, x, y + 2 * nextLen);
            solve(nextLen, x + nextLen, y + 2 * nextLen);
            solve(nextLen, x + 2 * nextLen, y + 2 * nextLen);

        } else {
            cntArr[map[x][y] + 1]++;
            return;
        }
    }

    public static boolean isPossible(int len, int x, int y) {
        int prev = map[x][y];
        for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                if (map[i][j] != prev)
                    return false;
            }
        }
        return true;
    }
}
