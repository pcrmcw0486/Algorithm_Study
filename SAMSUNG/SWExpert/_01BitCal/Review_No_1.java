package SAMSUNG.SWExpert._01BitCal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Review_No_1 {
    static final int END_CONDITION = (int)Math.pow(2,10)-1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            sb.append('#').append(tt).append(' ');
            int N = Integer.parseInt(br.readLine());
            int status = 0;
            int curN = 0;
            int cnt =0 ;
            while(status !=END_CONDITION){
                curN += N;
                int tmp = curN;
                while(tmp > 0){
                    status |= 1<<(tmp%10);
                    tmp /= 10;
                }
                cnt++;
            }
            sb.append(curN).append('\n');
        }
        System.out.print(sb);
    }
}
