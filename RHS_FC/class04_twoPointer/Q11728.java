package RHS_FC.class04_twoPointer;

import java.util.*;
import java.io.*;

public class Q11728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        int[] B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            B[i] = Integer.parseInt(st.nextToken());
        int aPointer = 0;
        int bPointer = 0;
        while (aPointer < N && bPointer < M) {
            if (A[aPointer] <= B[bPointer]) {
                sb.append(A[aPointer]).append(' ');
                aPointer++;
            } else {
                sb.append(B[bPointer]).append(' ');
                bPointer++;
            }
        }
        if (aPointer == N) {
            for (int i = bPointer; i < M; i++)
                sb.append(B[i]).append(' ');
        } else {
            for (int i = aPointer; i < N; i++)
                sb.append(A[i]).append(' ');
        }
        System.out.println(sb);
    }
}
