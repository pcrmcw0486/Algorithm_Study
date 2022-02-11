/* 숨바꼭질(Silver1)
1초마다 움직여서 동생의 위치를 찾는다.
움직이는 방법은 총 3가지로 매초 마다 3가지 방법씩 늘어난다.
가장 크게 움직였을 때 또한 2제곱 수로 늘어남.
2 4 8 16 32 64 
모든 경우의수를 찾는 것은 부담이 된다고 판단.
동생의 위치의 /2 보다 작은 경우에는 뒤로 가지 않는다.
또한 동생의 위치가 밑에 있을 때는 밑으로만 간다.
 */
package SolveByCategory.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);

        System.out.println(solution(N, K));
    }

    public static int solution(int N, int K) {
        if (K <= N)
            return N - K;

        Queue<Integer> position = new LinkedList<>();
        int[] visit = new int[100001];
        position.offer(N);
        visit[N] = 1;
        while (!position.isEmpty()) {
            int np = position.poll();
            int count = visit[np];
            if (np == K)
                return count - 1;
            if (np - 1 > 0 && visit[np - 1] == 0) {
                position.offer(np - 1);
                visit[np - 1] = count + 1;
            }
            if (np + 1 < visit.length && visit[np + 1] == 0) {
                position.offer(np + 1);
                visit[np + 1] = count + 1;
            }
            if (np * 2 < visit.length && visit[np * 2] == 0) {
                position.offer(np * 2);
                visit[np * 2] = count + 1;
            }
        }
        return -1;
    }
}
