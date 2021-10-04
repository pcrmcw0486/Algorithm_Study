package toy;

import java.io.*;

public class Q2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int max = N / 5;
        int check = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = max; i >= 0; i--) {
            check = i;
            int rest = N - 5 * i;
            if (rest % 3 == 0) {
                check += rest / 3;
                ans = check;
                break;
            }
        }
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
