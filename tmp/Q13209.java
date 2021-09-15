package tmp;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
검역소 Platinum III

 */

public class Q13209 {
    static int cnt = 0;
    static int[] population;
    static List<Integer>[] tree;
    static boolean tooMin = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken()); // 그룹은 k+1개

            st = new StringTokenizer(br.readLine());
            population = new int[N + 1];

            for (int i = 1; i < N + 1; i++) {
                population[i] = Integer.parseInt(st.nextToken());
            }
            tree = new ArrayList[N + 1];
            for (int i = 0; i < N + 1; i++)
                tree[i] = new ArrayList<Integer>();
            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                tree[u].add(v);
                tree[v].add(u);
            }
            long start = 0;
            long end = 0;
            int max = 0;
            for (int i = 0; i < N + 1; i++) {
                end += population[i];
                max = Math.max(max, population[i]);
            }
            while (start + 1 < end) {
                long mid = (start + end) / 2;
                cnt = 0;
                search(-1, 1, mid);
                if (max <= mid && cnt <= K)
                    end = mid;
                else
                    start = mid;
            }

            sb.append(end).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static long search(int parent, int cur, long limit) {
        long sum = population[cur];
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int next : tree[cur]) {
            if (next == parent)
                continue;
            long t = search(cur, next, limit);
            sum += t;
            pq.offer(t);
        }
        while (!pq.isEmpty() && sum > limit) {
            sum -= pq.poll();
            cnt++;
        }
        return sum;
    }
}
