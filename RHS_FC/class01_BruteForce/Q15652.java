package RHS_FC.class01_BruteForce;

/*
N과M(4)
M개를 고른 수열.
중복허용
비 내림차순이여야한다. 고르기!
 */
import java.io.*;
import java.util.*;

public class Q15652 {
    static int[] selected;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        selected = new int[M + 1];

        rec_func(1);
        System.out.println(sb.toString());

    }

    static void rec_func(int k) {
        if (k == M + 1) {
            for (int i = 1; i < selected.length; i++)
                sb.append(selected[i]).append(' ');
            sb.append('\n');
        } else {
            int start = selected[k - 1];
            if (start == 0)
                start = 1;
            for (int i = start; i < N + 1; i++) {
                selected[k] = i;
                rec_func(k + 1);
                selected[k] = 0;
            }
        }
    }
}
