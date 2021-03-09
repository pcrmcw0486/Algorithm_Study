package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q2565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int dp[] = new int[N];
        int data[][] = new int[N][2];
        int max_result = 1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dp[i] = 1;
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (data[i][1] > data[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max_result = Math.max(max_result, dp[i]);
        }
        System.out.println(N - max_result);
    }
}
