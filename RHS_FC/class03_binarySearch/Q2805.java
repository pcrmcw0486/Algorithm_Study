package RHS_FC.class03_binarySearch;

/*
나무자르기 Silver III
N 1,000,000
M 2,000,000,000
 */
import java.util.*;
import java.io.*;

public class Q2805 {
    static int[] data;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        data = new int[N];
        long l = 0, r = 2000000000;
        int m, ans = 0;
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        while (l <= r) {
            m = (int) (l + r) / 2;
            if (determination(m)) {
                ans = m;
                l = m + 1;
            } else
                r = m - 1;
        }
        System.out.println(ans);
    }

    static boolean determination(int H) {
        // H 높이로 나무들을 잘랐을 때, M 만큼을 얻을 수 있으면 true, 없으면 false를 return하는 함수
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            if (data[i] > H) {
                sum += data[i] - H;
            }
        }
        return sum >= M;
    }

}
