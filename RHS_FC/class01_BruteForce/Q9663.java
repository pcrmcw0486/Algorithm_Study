package RHS_FC.class01_BruteForce;

import java.io.*;

public class Q9663 {
    static int N;
    static int[] cols;
    static boolean[] rowCheck;
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cols = new int[N + 1];
        rowCheck = new boolean[N + 1];
        nQueen(1);
        System.out.println(count);
    }

    public static void nQueen(int n) {
        if (n == N + 1) { // 마지막 줄이라면.
            count++;
        } else {
            // 위에서 부터 내려오면서
            for (int i = 1; i < N + 1; i++) {
                if (!rowCheck[i]) {
                    boolean flag = true;
                    for (int j = 1; j < n; j++) {
                        // 현재위치 n, i
                        // 이전위치 j, cols[j]
                        int tmpx = Math.abs(n - j);
                        int tmpy = Math.abs(i - cols[j]);
                        if (tmpx == tmpy) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        cols[n] = i;
                        rowCheck[i] = true;
                        nQueen(n + 1);
                        cols[n] = 0;
                        rowCheck[i] = false;
                    }
                }
            }
        }
    }

}
