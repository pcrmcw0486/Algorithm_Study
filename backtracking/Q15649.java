package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ silver5 N과M1
public class Q15649 {

    static int N, M;
    static int[] numbers;
    static boolean[] selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M];
        selected = new boolean[N + 1];
        // selected 사용
        permutation(0);

        System.out.println(sb.toString());
    }

    // selected 사용
    private static void permutation(int idx) {
        if (idx == M) {
            for (int n : numbers) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (selected[i])
                continue;
            selected[i] = true;
            numbers[idx] = i;
            permutation(idx + 1);
            selected[i] = false;
        }
    }
}