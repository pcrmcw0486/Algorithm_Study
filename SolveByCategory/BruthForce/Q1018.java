package SolveByCategory.BruthForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q1018 {
    public static void main(String[] args) throws IOException {
        boolean[][] case1 = { { false, true, false, true, false, true, false, true },
                { true, false, true, false, true, false, true, false },
                { false, true, false, true, false, true, false, true },
                { true, false, true, false, true, false, true, false },
                { false, true, false, true, false, true, false, true },
                { true, false, true, false, true, false, true, false },
                { false, true, false, true, false, true, false, true },
                { true, false, true, false, true, false, true, false } };
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = (line.charAt(j) == 'B');
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < M - 7; i++) {
            for (int j = 0; j < N - 7; j++) {
                int min_count = 0;
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        if (case1[k][l] != map[i + k][j + l])
                            min_count++;
                    }
                }
                result = Math.min(result, Math.min(min_count, 64 - min_count));
            }
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
