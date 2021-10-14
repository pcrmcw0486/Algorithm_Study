package RHS_FC.class05_graph;

/*
숨바꼭질 Silver I
수빈이는 점 N 0~100_000
동생은 점K  0 ~ 100_000
수빈이는 걷거나 순간이동 X일때 걷는다면 1초후 X-1 또는 X+1 또는 2*X로 이동.
그냥 처음에 생각하기엔 dp인데.
dp배열이라고 생각한 것이 따지고 보면 정점이고 상태 공간이라고 생각하자.
최대 위치 100_000 두배뛰는 경우 200_000까지 고려하여 상태 공간 정의
 */
import java.io.*;
import java.util.*;

public class Q1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int dist[] = new int[100001];
        if (K <= N)
            dist[K] = N - K;
        else {
            Queue<Integer> position = new LinkedList<>();
            position.add(N);
            dist[N] = 0;
            while (!position.isEmpty()) {
                int now = position.poll();
                if (now == K)
                    break;
                if (now - 1 > 0 && dist[now - 1] == 0) {
                    position.offer(now - 1);
                    dist[now - 1] = dist[now] + 1;
                }
                if (now + 1 < dist.length && dist[now + 1] == 0) {
                    position.offer(now + 1);
                    dist[now + 1] = dist[now] + 1;
                }
                if (now * 2 < dist.length && dist[now * 2] == 0) {
                    position.offer(now * 2);
                    dist[now * 2] = dist[now] + 1;
                }
            }

        }
        System.out.println(dist[K]);

    }
}
