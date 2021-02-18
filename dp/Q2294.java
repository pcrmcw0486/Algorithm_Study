package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//동전2 (Silver 1)
public class Q2294 {
    static int IMPOSSIBLE = 10001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] value = new int[n];
        for (int i = 0; i < n; i++)
            value[i] = Integer.parseInt(br.readLine());

        int[] dp = new int[k + 1];
        for (int i = 1; i < k + 1; i++)
            dp[i] = IMPOSSIBLE;

        for (int i = 0; i < n; i++) {
            for (int j = value[i]; j < k + 1; j++) {
                dp[j] = Math.min(dp[j - value[i]] + 1, dp[j]);
            }
        }
        int result = dp[k];
        if (result == IMPOSSIBLE)
            result = -1;
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

}