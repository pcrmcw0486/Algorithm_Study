package RHS_FC.class01_BruteForce;

/*
N과 M(1) Silver III
중복허용 안함.
*/

import java.io.*;
import java.util.*;

public class Q15469 {
    static StringBuilder sb = new StringBuilder();
    static int[] selected;
    static boolean[] used;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        selected = new int[M + 1];
        used = new boolean[N + 1];

        findCombination(1);

        System.out.println(sb.toString());
    }

    public static void findCombination(int k) {
        if (k == M + 1) {
            for (int i = 1; i < M + 1; i++)
                sb.append(selected[i]).append(' ');
            sb.append('\n');
        } else {
            for (int cand = 1; cand < N + 1; cand++) {
                if (used[cand])
                    continue;
                used[cand] = true;
                selected[k] = cand;
                findCombination(k + 1);
                used[cand] = false;
                selected[k] = 0;
            }
        }
    }

}
