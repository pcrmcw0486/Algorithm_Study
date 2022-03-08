package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @문제번호 : Review_1208
 * @문제이름 : 부분 수열의 합 2
 * @난이도 : Gold I
 * @date : 2022-03-04 오전 12:41
 * @author : pcrmcw0486
 * @문제이해
 * N개의 정수로 이루어진 수열이 있을 때,
 * 크기가 양수인 부분 수열 중에서 그 수열의 원소를 다 더한 값이
 * S가 되는 경우의 수를 구하시오.
 * @알고리즘
 *
 * @접근방법
 * N의 범위가 40으로 모든 경우의 수를 한번에 구할 경우 2^40이 된다.
 * 이를 반으로 나누어 두번 구한다면 2^20 * 2로 훨씬 작은 시간 안에 구할 수 있다.
 * 각 부분 수열의 합들을 이용하여 A + B = S가 되는 값을 구한다.
 * 이러한 방식은 여러가지가 있는데 정렬 후 이분탐색을 활용하여 upeprbound와 lowerbound의 개수를
 * 더해주는 방법 또는 Map을 쓰는 방법이 있는데 Map이 더 편하니까 Map으로 한다.
 *
 *  또 다른 방법으로는 각 수의 합이 최대 4000000을 넘지 않는다는 점에서 배열을 활용하는 방법도 존재한다.

*/
public class Review_1208 {
    static int[] data;
    static int N, S;
    static long ans =0L;
    static Map<Integer, Long> rightInfo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        data = new int[N];
        rightInfo = new HashMap<>();
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        findRight(0, N / 2, 0);
        solve(N / 2, N, 0);
        if(S ==0) ans--;
        System.out.println(ans);

    }

    private static void solve(int idx, int end, int value) {
        if(idx==end) {
            ans += rightInfo.getOrDefault(S-value, 0L);
            return;
        }
        solve(idx+1, end, value);
        solve(idx+1, end, value + data[idx]);
    }

    private static void findRight(int idx, int end, int value) {
        if(idx == end) {
            rightInfo.put(value, rightInfo.getOrDefault(value, 0L) + 1L);
            return;
        }
        findRight(idx + 1, end, value);
        findRight(idx + 1, end, value + data[idx]);
    }
}
