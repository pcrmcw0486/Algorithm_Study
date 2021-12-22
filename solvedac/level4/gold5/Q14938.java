package solvedac.level4.gold5;
/*
2021.12.22
문제번호 : Q14938
이름 및 난이도 : 서강그라운드 Gold IV
문제이해 
-----------------
어디로 낙하해야 자신의 수색 범위 내에서 가장 많은 아이템을 얻을 수 있는가.
지역은 일정한 길이 l (1<=l<=15)의 길로 다른 지역과 연결. 양방향 통행 가능.
낙하 중심 수색범위 (1<= m<=15)이내의 모든 지역의 아이템 습득이 가능. 
얻을 수 있는 아이템 최대 개수를 알려주자.

접근 방법 :
 예은이가 떨어지는 곳이 어디인지 모른다. 어디인지 모르지만 최대의 값을 가져야한다.
 즉, 모든 위치에서 모든 거리를 구하여 해당 거리안에 있는 아이템들을 모았을 때 최대가 되는 개수를 찾아야하므로
 all source to all destination 이 맞다.
   >> 시작 노드를 for문으로 돌리면서 dijkstra나 BFS를 돌릴 수도 있을 거고
   >> Floyd로 구할 수도 있을 거고 어떻게든 shortest path 를 구해서 찾아내면 되는 문제이다.
 Floyd
제한 조건 : 
 최대거리는 지역개수 100 거리 길이 15  >> 1500
 - 간선의 중복여부는 알 수 없다 아직.
    없다고 생각하고 작성.
*/

import java.io.*;
import java.util.*;

public class Q14938 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 지역개수
        int M = Integer.parseInt(st.nextToken()); // 수색범위
        int R = Integer.parseInt(st.nextToken()); // 길의 개수
        int[] item = new int[N + 1];
        int[][] routes = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(routes[i], 2000);
            routes[i][i] = 0;
        }

        // Item data
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        // routes
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            routes[u][v] = w;
            routes[v][u] = w;
        }

        // Floyd
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (routes[i][j] > routes[i][k] + routes[k][j]) {
                        routes[i][j] = routes[i][k] + routes[k][j];
                    }
                }
            }
        }

        // Find answer
        int ans = 0;
        for (int i = 1; i < N + 1; i++) {
            int sum = item[i];
            for (int j = 1; j < N + 1; j++) {
                if (i == j)
                    continue;
                if (routes[i][j] <= M) {
                    sum += item[j];
                }
            }
            ans = Math.max(ans, sum);
        }
        System.out.println(ans);
    }
}
