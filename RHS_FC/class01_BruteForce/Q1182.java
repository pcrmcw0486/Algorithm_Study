package RHS_FC.class01_BruteForce;
/* 부분 수열의 합 Silver II
N개의 정수로 이루어진. 양수인 부분수열

 */

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Q1182 {
    static int count = 0;
    static int[] data;
    static int N, S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        data = new int[N];
        data = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rec_func(0, 0);
        System.out.println(S == 0 ? count - 1 : count);
    }

    public static void rec_func(int k, int val) {
        if (k == N) {
            if (val == S) {
                count++;
            }
        } else {
            rec_func(k + 1, val + data[k]);
            rec_func(k + 1, val);
        }
    }

}
