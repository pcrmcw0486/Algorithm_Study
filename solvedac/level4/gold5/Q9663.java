package solvedac.level4.gold5;
/*
2021.12.10
문제번호 : Q9663
이름 및 난이도 : N-Queen Gold V
문제이해 
-----------------
N*N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q9663 {
    static int N;
    static int[] info;
    static boolean[] visitedCol;
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        info = new int[N];
        visitedCol = new boolean[N];
        solution(0);
        System.out.println(cnt);
    }

    public static void solution(int depth) {
        if (depth == N) {
            cnt++;
            return;
        }
        boolean possible = true;
        // row단은 해결 by depth
        for (int i = 0; i < N; i++) {
            // col단 정하기
            if (!visitedCol[i]) {
                possible = true;
                // 현재까지의 대각선 위치 여부 확인
                for (int j = 0; j < depth; j++) {
                    // 이전에 놓은 점은 j,info[j]에 위치하여 있음.
                    // 놓을 점은 depth, info[i]
                    if (Math.abs(depth - j) == Math.abs(i - info[j])) {
                        possible = false;
                        break;
                    }
                }
                if (possible) {
                    info[depth] = i;
                    visitedCol[i] = true;
                    solution(depth + 1);
                    visitedCol[i] = false;
                }
            }

        }
    }

}
