package SolveByCategory.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q13305 {
    public static void main(String[] args) throws IOException {
        long[] oil;
        long[] dist;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        oil = new long[N];
        dist = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        // 0 이 거리
        for (int i = 0; i < N - 1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
            oil[i] = Integer.parseInt(st2.nextToken());
        }
        long tot_cost = oil[0] * dist[0];
        long min_gas_cost = oil[0];
        for (int i = 1; i < N - 1; i++) {
            if (oil[i] < min_gas_cost)
                min_gas_cost = oil[i];
            tot_cost += min_gas_cost * dist[i];
        }
        bw.write(String.valueOf(tot_cost));
        br.close();
        bw.close();
    }
}
