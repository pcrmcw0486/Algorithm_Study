package SAMSUNG.SWExpert._01BitCal;

import java.util.Scanner;

/*
부원 네명 ABCD 각각 참여여부 & 아무도 참여하지 않는 경우는 없다.
열쇠는 하나 -> 열쇠를 가진 사람은 무조건 참여한다. (열쇠 무조건)
오늘 활동 하는 사람중 내일 활동에도 참여하는 사람이 있다면 넘겨줄 수 있다.
+
N일 동안 각 날마다 책임자가 있다.(책임자 무조건)
N일 동안 동아리 활동을 할 수 있는 경우의 수?


* */
public class NO_3_ManageClub {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case<=T;test_case++) {
            sb.append('#').append(test_case).append(' ');
            String line = sc.next();
            int[][] dp = new int[line.length()][16];
            int status = 1 | 1 << (line.charAt(0) - 'A');
            for (int i = 1; i < 16; i++) {
                if ((status & i) == status) {
                    dp[0][i] = 1;
                }
            }
            for (int i = 1; i < line.length(); i++) {
                status = 1 << (line.charAt(i) - 'A');
                for (int j = 1; j < 16; j++) {
                    if ((status & j) > 0) {
                        int check = status | j;
                        for (int k = 1; k < 16; k++) {
                            if ((check & k) > 0) {
                                dp[i][j] = (dp[i][j]+ dp[i - 1][k])%1000000007;
                            }
                        }
                    }
                }
            }
            int sum = 0;
            for (int i = 1; i < 16; i++) {
                sum = (sum + dp[line.length() - 1][i])%1000000007;
            }
            sb.append(sum).append('\n');
        }

        System.out.println(sb.toString());
    }
}
