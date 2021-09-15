package RHS_FC.class01_BruteForce;

/*
N과 M(9) Silver II
N개의 자연수 중 M개를 고른 고열.
 */
import java.io.*;
import java.util.*;

public class Q15663 {
    static int N, M;
    static int[] data;
    static int[] selected;
    static boolean[] isUsed;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        data = new int[N];
        selected = new int[N];
        isUsed = new boolean[N];
        for (int i = 0; i < N; i++)
            data[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(data);
        rec_func(0);
        System.out.print(sb.toString());
    }

    static void rec_func(int k) {
        if (k == M) {
            for (int i = 0; i < M; i++)
                sb.append(selected[i]).append(' ');
            sb.append('\n');
        } else {
            for (int i = 0; i < N; i++) {
                if (!isUsed[i]) {
                    if (i != 0) {
                        if (data[i] == data[i - 1] && !isUsed[i - 1])
                            continue;
                    }
                    selected[k] = data[i];
                    isUsed[i] = true;
                    rec_func(k + 1);
                    selected[k] = 0;
                    isUsed[i] = false;
                }
            }
        }
    }
}
