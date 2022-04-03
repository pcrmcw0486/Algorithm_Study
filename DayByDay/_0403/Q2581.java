package DayByDay._0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2581 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] data = new boolean[M + 1];
        int ans =0;
        int min = -1;
        for (int i = 2; i < M + 1; i++) {
            if (!data[i]) {
                if(i >=N) {
                    if(min== -1) min =i;
                    ans += i;
                }
                for (int j = i + i; j < M + 1; j = j + i) {
                    data[j] = true;
                }
            }
        }
        if (min == -1) {
            System.out.println(-1);
        }else{
            System.out.println(ans);
            System.out.println(min);
        }
    }
}
