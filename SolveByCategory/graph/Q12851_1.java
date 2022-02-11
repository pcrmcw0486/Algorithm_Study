package SolveByCategory.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q12851_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);

        // System.out.println(solution(N, K));
        solution(N, K);
    }

    public static void solution(int N, int K) {
        if (K <= N) {
            System.out.println(N - K + "\n" + 1);
            return;
        }

        Queue<int[]> position = new LinkedList<>();
        int[][] visit = new int[100001][2];
        for (int i = 0; i < visit.length; i++)
            visit[i][0] = Integer.MAX_VALUE;

        position.offer(new int[] { N, 1 });
        visit[N][0] = 1;
        visit[N][1] = 1;

        while (!position.isEmpty()) {
            int next;
            int[] np = position.poll();
            int dist = np[1];
            int number = np[0];
            int count = visit[number][1];

            if (number == K) {
                System.out.println(visit[K][0] - 1 + "\n" + visit[K][1]);
                break;
            }
            for (int i = 0; i < 3; i++) {
                if (i == 0)
                    next = number - 1;
                else if (i == 1)
                    next = number + 1;
                else
                    next = number * 2;

                if (next < 0 || next > 100000)
                    continue;

                if (dist + 1 <= visit[next][0]) {
                    if (visit[next][1] == 0) {
                        visit[next][0] = dist + 1;
                        position.offer(new int[] { next, dist + 1 });
                    }
                    visit[next][1] += count;
                }
            }

        }
    }
}
