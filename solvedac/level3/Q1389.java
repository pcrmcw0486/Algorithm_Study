package solvedac.level3;
/*
2021.12.02
문제번호 : Q1389
이름 및 난이도 : 케빈 베이컨의 6 단계 법칙. Silver I
문제이해 
-----------------
최대 6 단계 안에서 서로 아는 사람으로 연결될 수 있다.
케빈 베이컨 게임 임의의 두 사람이 최소 몇 단계만에 이어질 수 있는가.
케빈 베이컨의 수가 가장 작은 사람을 구하는 프로그램.
사람이 여러명이라면 번호가 가장 작은 사람.
접근 방법 :
제한 조건 : 
유저의수는 N<= 100 1<=M<=5000
친구 관계는 중복되어 들어올 수도 있고, 친구가 한명 도 없는 사람은 없다.
모두 연결이 되어 있다. 조건의 수가 크지 않음.
그냥 플로이드 와샬,
*/

import java.io.*;
import java.util.*;

public class Q1389 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] countMap = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(countMap[i], N + 1);
            countMap[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            countMap[u][v] = countMap[v][u] = 1;
        }
        // i to j through k
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (countMap[i][j] > countMap[i][k] + countMap[k][j]) {
                        countMap[i][j] = countMap[i][k] + countMap[k][j];
                    }
                }
            }
        }
        int minValue = Integer.MAX_VALUE;
        int ans = -1;
        for (int i = 1; i < N + 1; i++) {
            int minCnt = 0;
            for (int j = 1; j < N + 1; j++) {
                minCnt += countMap[i][j];
            }
            if (minCnt < minValue) {
                minValue = minCnt;
                ans = i;
            }
        }

        System.out.println(ans);
    }
}
