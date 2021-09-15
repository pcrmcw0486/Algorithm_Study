/*
N과 M(3) Silver III
- 
 */
package RHS_FC.class01_BruteForce;

import java.io.*;
import java.util.*;

public class Q15651 {
    static int[] selected;
    static int N, M;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        selected = new int[M + 1];

        findSequence(1);

        System.out.print(sb.toString());
    }

    public static void findSequence(int k) {
        if (k == M + 1) {
            // all chosen
            for (int i = 1; i <= M; i++)
                sb.append(selected[i]).append(' ');
            sb.append('\n');
        } else {
            // 1~N까지를 k 번 원소로 한번씩 정하고
            // 다음 step으로 이동.
            for (int i = 1; i <= N; i++) {
                selected[k] = i;
                findSequence(k + 1);
                selected[k] = 0;
            }
        }
    }

}
