package SAMSUNG.SWExpert._01BitCal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Reveiw_No_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            sb.append('#').append(tt).append(' ');
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int MOD = (int)Math.pow(2,N);
            sb.append(M % MOD == MOD - 1 ? "ON" : "OFF").append('\n');
        }
        System.out.println(sb.toString());
    }
}
