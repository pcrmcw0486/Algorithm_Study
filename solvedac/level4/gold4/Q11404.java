package solvedac.level4.gold4;
/*
2021.12.22
문제번호 : Q11404
이름 및 난이도 : 플로이드 Gold IV
문제이해 
-----------------
n개의 도시가 있고, 한 도시에서 출발하여 다르 도시에 도착하는 버스 m개가 있다.
모든 도시 쌍(A,B)에 대해 도시 A에서 B로 가는데 필요한 비용의 최솟값.
접근 방법 :
 all source to all destination (shortest Path)
제한 조건 : 
 n <= 100
 m <= 100_000
 주어지는 간선 중 시작도시와 도착 도시가 같은 경우는 없다.
 중복으로 주어지는 간선은 있을 수 있다.
 비용은 100_000보다 작거나 같은 자연수이다.
 i에서 j로 갈 수 없다면 0을 출력한다.
 주어지는 간선은 방향이 있다. a->b
 최대거리는 100 * 100_000 
*/

import java.io.*;
import java.util.*;

public class Q11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] routes = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(routes[i], 10000000);
            routes[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            routes[a][b] = Math.min(routes[a][b], w);
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (routes[i][j] > routes[i][k] + routes[k][j]) {
                        routes[i][j] = routes[i][k] + routes[k][j];
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (routes[i][j] == 10000000)
                    sb.append(0);
                else
                    sb.append(routes[i][j]);
                sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
