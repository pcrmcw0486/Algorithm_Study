package SolveByCategory.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15652 {
    static StringBuilder sb;
    static int N;
    static int M;
    static int numbers[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M];

        solution(1, 0);
        System.out.print(sb);
    }

    public static void solution(int start, int count) {
        if (count == M) {
            for (int n : numbers)
                sb.append(n).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = start; i < N + 1; i++) {
            numbers[count] = i;
            solution(i, count + 1);
        }

    }
}
