package solvedac.level4.gold5;
/*
2021.12.19
문제번호 : Q13549
이름 및 난이도 : 숨바꼭질 3
문제이해 
-----------------

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q13549 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int ans = solution(N, K);
        System.out.println(ans);
    }

    public static int solution(int N, int K) {
        if (K <= N) {
            return N - K;
        } else {

            // 방문하면 가장 빠른 곳임.
            boolean[] visited = new boolean[100001];
            int[] time = new int[100001];
            // 순서에 주의한다. 00 0000 1111112222 순서로 와야함
            // PQ를 쓰지 않기 때문에 시간을 잘 맞추어야한다.
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.offer(N);
            visited[N] = true;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur == K)
                    break;
                int tmp = 2 * cur;
                // 0초 순간이동 2배
                while (tmp <= 100000) {
                    if (visited[tmp])
                        break;
                    visited[tmp] = true;
                    time[tmp] = time[cur];
                    queue.offer(tmp);
                    tmp *= 2;
                }
                if (cur - 1 >= 0 && !visited[cur - 1]) {
                    visited[cur - 1] = true;
                    time[cur - 1] = time[cur] + 1;
                    queue.offer(cur - 1);
                }
                if (cur + 1 <= 100_000 && !visited[cur + 1]) {
                    visited[cur + 1] = true;
                    time[cur + 1] = time[cur] + 1;
                    queue.offer(cur + 1);
                }

            }

            return time[K];
        }
    }
}
