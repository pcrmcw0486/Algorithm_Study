package SAMSUNG.SWExpert._04DC_BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_5_InversionCounting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1 ;test_case<=T;test_case++){
            sb.append('#').append(test_case).append(' ');
            int N = Integer.parseInt(br.readLine());
            int[] data = new int[N];
            int[] sum = new int[N+1];
            long ans =0 ;
            data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int i =N-1;i>=0;i--){
                int n = data[i];
                ans += sum[n];
                for(int j = n+1;j<N;j++){
                    sum[j]++;
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb.toString());
    }
}
