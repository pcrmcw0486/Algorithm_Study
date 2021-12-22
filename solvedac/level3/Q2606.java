package solvedac.level3;
/*
2021.11.05
문제번호 : Q2606
이름 및 난이도 : 색종이 만들기 Silver III 
문제이해 
-----------------
Naiive하게 생각하는 걸 또 까먹음. 시간복잡도 계산왜안했지.
최대 2^7크기이므로 2^8(다보는데 걸리는 시간)
최악의 경우
각 종이 오른쪽 아래가 1?
2^
접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q2606 {
    static int[][] map;
    static int N;
    static int blueCnt = 0;
    static int whiteCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine().replace(" ", "");
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        solve(N, 0, 0);
        System.out.println(whiteCnt);
        System.out.println(blueCnt);
        // int[] ans = solve(N,N);
        // for (int[] line : map) {
        // for (int a : line) {
        // System.out.print(a + " ");
        // }
        // System.out.println();
        // }
    }

    public static void solve(int len, int x, int y) {
        int prev = map[x][y];
        for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                if (prev != map[i][j]) {
                    solve(len / 2, x, y);
                    solve(len / 2, x, y + len / 2);
                    solve(len / 2, x + len / 2, y);
                    solve(len / 2, x + len / 2, y + len / 2);
                    return;
                }
            }
        }
        if (prev == 1)
            blueCnt++;
        else
            whiteCnt++;
    }
}
