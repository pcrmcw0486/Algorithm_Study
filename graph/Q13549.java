package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q13549 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        System.out.println(bfs(N, K));
        br.close();
    }

    public static int bfs(int N, int K) {
        if (K <= N)
            return N - K;

        Queue<Integer> q = new LinkedList<>();
        int[] time = new int[100001];
        // for(int i =0;i<time.length;i++)
        // time[i] = -1;
        q.offer(N);
        time[N] = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i = 0; i < 3; i++) {
                int next;
                if (i == 0)
                    next = now * 2;
                else if (i == 1)
                    next = now + 1;
                else
                    next = now - 1;

                if (next < 0 || next > time.length - 1)
                    continue;

                if (next == K)
                    return time[now];

                if (time[next] == 0 || time[next] < time[now] + 1) {
                    if (i == 0)
                        time[next] = time[now];
                    else
                        time[next] = time[now] + 1;
                    q.offer(next);
                }

            }
        }

        return -1;
    }
}
