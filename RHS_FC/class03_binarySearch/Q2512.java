package RHS_FC.class03_binarySearch;

/*
예산 Silver III 
국가 예산 총액은 정해져있음.
정해진 총액 이하에서 가능한 한 최대의 총 예산을 
 > 모든 요청이 배정 가능하면 그대로 배정
 > 배정될 수 없는 경우, 특정한 정수 상한액을 계산하여,
   그 이상인 예상 요청에는 모두 상한액을 배정한다. 상한액이하는
   그대로 배정.
   이때, 배정된 예사들 중 최댓값인 정수를 출력한다.
   상한액을 X로 했을 때 계산된 값이 총액안에서 예산 분배가 가능한가? Yes/No
   Yes중 가장 큰 X를 구하자.

   변수와 계산과정 데이터타입에 유의하도록 하자.
   지방의 수 N 3 ~ 10_000
   값들은 1~100_000
   총예산 M : 1_000_000_000 (10억)
   계산 중 나올수 있는 가장 큰수는
   10_000 * 100_000 >> 1_000_000_000 
*/
import java.io.*;
import java.util.*;

public class Q2512 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = 1;
        int r = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
            r = Math.max(r, data[i]);
        }
        int limit = Integer.parseInt(br.readLine());

        while (l <= r) {
            int m = l + r >> 1;
            if (determination(m, limit, data)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        System.out.println(ans);
    }

    public static boolean determination(int budget, int limit, int[] data) {
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i] <= budget ? data[i] : budget;
        }
        return sum <= limit;
    }
}
