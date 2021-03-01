package toy.recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Q2447 {
    public static char[][] star;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        star = new char[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(star[i], ' ');
        }
        solution(N, 0, 0);

        for (int i = 0; i < N; i++) {
            bw.write(star[i]);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void solution(int N, int x, int y) {
        if (N == 1) {
            star[x][y] = '*';
            return;
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!(i == 1 && j == 1)) {
                        solution(N / 3, x + i * (N / 3), y + j * (N / 3));
                    }
                }
            }
        }
    }
}
