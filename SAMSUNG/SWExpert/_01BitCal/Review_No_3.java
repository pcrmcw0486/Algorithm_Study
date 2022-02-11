package SAMSUNG.SWExpert._01BitCal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Review_No_3 {
    public static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            sb.append('#').append(tt).append(' ');
            String responsible  = br.readLine();
            int[][] dp = new int[responsible.length()][(1<<4)];

            //첫째날 dp init
            //DCBA 순임
            //키를 가진 A와 첫째날 책임자.
            int status = 1 | (1<<(responsible.charAt(0)-'A'));
            for(int i =1;i<16;i++){
                if((i&status) == status)
                    dp[0][i]++;
            }

            for(int day =1;day<responsible.length();day++){
                int responsiblePerson = 1<<(responsible.charAt(day)-'A');
                for(int i = 1;i<16;i++){
                    //해당 날짜에 책임자를 무조건 포함하고
                    status = i;
                    if((status&responsiblePerson) > 0){
                        //책임자를 포함해서 전날에 있던 사람 한명은 있는 경우
                        //status ^= responsiblePerson;
                        for(int j = 1;j<16;j++){
                            if((status & j) > 0){
                                dp[day][i] = (dp[day][i] + dp[day-1][j])%MOD;
                            }
                        }
                    }
                }
            }
            long sum =0 ;
            for(int i =1;i<16;i++){
                sum = (sum + dp[responsible.length()-1][i])%MOD;
            }
            sb.append(sum).append('\n');
        }
        System.out.println(sb);
    }
}
