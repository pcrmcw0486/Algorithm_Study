package RHS_FC.class03_binarySearch;

/*
랜선 자르기 Silver III
K개의 길이가 다른 랜선을 N개의 같은 길이 랜선으로
만들고 싶다.
300 -> 140 140 20
N개 보다 많이 만드는 것도 N개를 만드는 것에 포함된다.
이 때 만들 수 있는 최대 랜선 길이를 구하자
K 10_000
N은 1~ 1_000_000 K<=N
랜선 길이는 Int형으로 표현가능하다.
1로 자르면 존나 많겟지요.
True (ans)|| False가 되겟지요.
True이면 길이를 한번 늘려보아요. 길이가 짧으니까요

일단, 계산 과정 중 long 을 놓친거 하나 있고
r 범위 시발 Integer.MAX_VALUE +1 이랑 (long)Integer.MAX_VALUE +1이 다른거 같다.
이건 test한번 해봐야 할ㄷㅅ.
 */
import java.io.*;
import java.util.*;

public class Q1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] data = new int[K];
        long l = 0;
        long r = 0;
        for (int i = 0; i < K; i++) {
            data[i] = Integer.parseInt(br.readLine());
            r = Math.max(r, data[i]);
        }
        r++;
        r = (long) Integer.MAX_VALUE + 1L;
        while (l < r) {
            long mid = (l + r) / 2;
            if (determination(data, (long) N, mid)) {
                l = mid + 1;
            } else
                r = mid;
        }
        /*
         * while (l + 1 < r) { if (determination(data, K, m = l + r >> 1)) l = m; else r
         * = m; }
         */
        System.out.println(l - 1);
    }

    public static boolean determination(int[] data, long target, long length) {
        long count = 0;
        for (int i = 0; i < data.length; i++) {
            count += (data[i] / length);
        }
        return count >= target;
    }
}
