package programmers.kakao.Blind2022;

import java.util.StringTokenizer;

public class Solution6 {
    public static void main(String[] args) {
        int[][] board = new int[][]{
                {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}
        };
        int[][] skills = new int[][]{
                {1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}
        };

        solution(board, skills);
    }

    private static void solution(int[][] board, int[][] skills) {
        StringTokenizer st;
        int N = board.length;
        int M = board[0].length;
        int[][] dmgPreSum = new int[N + 1][M + 1];
        for (int[] skill : skills) {
            int type = skill[0];
            int r1 = skill[1], c1 = skill[2], r2 = skill[3], c2 = skill[4];
            int degree = skill[5];
            if (type == 1) degree = -degree;
            dmgPreSum[r1][c1] += degree;
            dmgPreSum[r1][c2 + 1] -= degree;
            dmgPreSum[r2 + 1][c1] -= degree;
            dmgPreSum[r2 + 1][c2 + 1] += degree;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dmgPreSum[i][j] += dmgPreSum[i][j - 1];
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dmgPreSum[i][j] += dmgPreSum[i - 1][j];
            }
        }

        int ans = 0;
        for (int i = 0;i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + dmgPreSum[i][j] > 0) ans++;
            }
        }
        System.out.println(ans);
    }
}
