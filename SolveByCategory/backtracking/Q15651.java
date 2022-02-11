package SolveByCategory.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15651 {
    public static StringBuilder sb = new StringBuilder();
    public static int N;
    public static int M;
    public static int numbers[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M];
        solution(0, 0);
        System.out.print(sb.toString());
    }

    public static void solution(int number, int count) {
        if (count == M) {
            for (int n : numbers)
                sb.append(n).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = 1; i < N + 1; i++) {
            numbers[count] = i;
            solution(i, count + 1);
        }
    }
}
