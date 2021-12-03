package solvedac.level3;
/*
2021.12.02
문제번호 : Q1697
이름 및 난이도 : 숨바꼭질 Silver I
문제이해 
-----------------
0<= N <= 100_000 (수빈's 현재 점)
0<= N <= 100_000 (동생's 현 재 점)
걷거나 순간이동 X-1 or X+1 or 2*X
동생을 찾을 수 있는 가장 빠른 시간?
접근 방법 :
상태공간 ( 0~100_000 범위 안에서 초에 따라 갈 수 있는 위치가 달라짐)
가장 먼저 생각든건 dp와 bfs임. bfs를 통해 갈 수 있는 거리를 최적화 시켜줌.

+ 방향성을 고려해서 뒤에 있는 경우는 항상 n-k가 될 것이고
앞에 있는 경우만 고려하여 dp를 하는 경우 등 더 많은 case를 쪼개서 볼 수도 있겠다.
제한 조건 : 
거리 범위가 int형 안에서 가능하다.
*/

import java.io.*;
import java.util.*;

public class Q1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dist = new int[100001];
        int ans = -1;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(N);
        dist[N] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == K) {
                ans = dist[cur];
                break;
            }
            if (cur + 1 <= 100000 && dist[cur + 1] == 0) {
                dist[cur + 1] = dist[cur] + 1;
                queue.add(cur + 1);
            }
            if (cur - 1 >= 0 && dist[cur - 1] == 0) {
                dist[cur - 1] = dist[cur] + 1;
                queue.add(cur - 1);
            }
            if (cur * 2 <= 100000 && dist[cur * 2] == 0) {
                dist[cur * 2] = dist[cur] + 1;
                queue.add(cur * 2);
            }
        }
        System.out.println(ans);
    }
}
