package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        int[] dp_increase = new int[N];
        int[] dp_decrease = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
            dp_increase[i] = 1;
            dp_decrease[i] = 1;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (data[i] > data[j] && dp_increase[i] < dp_increase[j] + 1)
                    dp_increase[i] = dp_increase[j] + 1;
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= i; j--) {
                if (data[i] > data[j] && dp_decrease[i] < dp_decrease[j] + 1)
                    dp_decrease[i] = dp_decrease[j] + 1;
            }
        }
        for (int i = 0; i < N; i++)
            System.out.print(dp_increase[i] + " ");
        System.out.println();
        for (int i = 0; i < N; i++)
            System.out.print(dp_decrease[i] + " ");
        System.out.println();
        int max = -1;
        for (int i = 0; i < N; i++) {
            max = max > dp_decrease[i] + dp_increase[i] - 1 ? max : dp_decrease[i] + dp_increase[i] - 1;
        }
        System.out.println(max);
    }
}
