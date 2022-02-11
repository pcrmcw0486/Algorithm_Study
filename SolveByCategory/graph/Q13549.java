package SolveByCategory.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

    // 같은 bfs라도 가중치, 즉 먼저 도달하는 경우에 대해 생각해주어야함.

    public static int bfs(int N, int K) {
        if (K <= N)
            return N - K;
        Queue<Integer> q = new LinkedList<>();

        int[] time = new int[100001];
        Arrays.fill(time, -1);
        q.offer(N);
        time[N] = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            int next = now * 2;
            int dist = time[now];

            
            while (next < 100001 && time[next]==-1) {
                time[next] = Math.min(time[next],dist);
                q.offer(next);
                next *= 2;
            }
            for (int i = 0; i < 2; i++) {
                if (i == 0)
                    next = now - 1;
                else
                    next = now + 1;
                if (next < 0 || next > 100000)
                    continue;
                if (time[next] == -1) {
                    time[next] = dist + 1;
                    q.offer(next);
                }
            }
        }return-1;
}}
