/*
 *** 냅색 문제 ***
 https://www.acmicpc.net/problem/1450
 *** 접근 방법 ***
 문제를 간단히 하면
 부분합 중 무게 C보다 갖거나 낮은 개수를 구한다.
 무게를 sorting하여 (O(nlogn))
 투포인터를 활용해 탐색(O(n))한다.
 하나의 조합 스킬이라고 생각할 수 있겟다. 순서는 다르겟지만?
 *** 알고리즘 자료구조 스킬 ***
 기본적인 Knapsack 문제와 달리 가치와 같은 다른
 조건이 있지 않고 '무게'에 따라서만 달라진다.
 본질적으로 Knapsack문제는 조합 최적화 문제로 무게에 최대 가치를 계산하기 위해
 모든 조합을 보면서 DP 또는 backtracking으로 해결해 나아가는데
 이 문제의 경우는 무게만 보면되기 때문에 용이하게 탐색을 통해 해결 가능하다. 
 조합이기 때문에 30C1 부터 30CN까지 모두 봐야하기 때문에 for문을 돌리게 되면 시간초과.
 조합에 특이점이 있음
 *** 문제 조건 ***
 @time : 1s
 @memory : 128MB
 @data
    N개 물건 0< N <= 30
    C 무게 0< C <= 10^9 1_000_000_000
    주어지는 물건 무게 C와 동일.
 N개 물건에 최대 C만큼 무게를 넣을 수 있을 때,
 N개의 물건을 가방에 넣는 방법의 수를 구하시오.
 2개를 넣었을 때 이미 C의 범위를 초과하여 넣지 않으면 되므로 int형에서 해결 가능하다.
 */
package toy.Skills.TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q1450 {
    public static int[] data;
    public static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        data = new int[N];
        for (int i = 0; i < N; i++)
            data[i] = Integer.parseInt(st.nextToken());

        ArrayList<Long> subSumA = new ArrayList<>();
        ArrayList<Long> subSumB = new ArrayList<>();

        // calc subSum
        dfs(subSumA, 0, (N / 2), 0L);
        dfs(subSumB, (N / 2), N, 0L);
        subSumA.add(0L);
        subSumB.add(0L);

        // 오름차순 정렬
        subSumB.sort(new Comparator<Long>() {
            public int compare(Long o1, Long o2) {
                return (int) (o1 - o2);
            };
        });

        int cnt = 0;
        for (long subSum : subSumA) {
            int left = 0;
            int ans = 0;
            int right = subSumB.size() - 1;
            if (subSum > C)
                continue;
            while (left <= right) {
                // System.out.println("[" + left + " , " + right + "]");
                int mid = (left + right) >> 1;
                if (subSumB.get(mid) + subSum <= C) {
                    ans = mid;
                    left = mid + 1;
                } else
                    right = mid - 1;
            }
            // System.out.println(right);
            cnt += (right + 1);

        }
        System.out.println(cnt);

    }

    public static void dfs(ArrayList<Long> a, int start, int end, long sum) {
        long subSum = sum;
        for (int i = start; i < end; i++) {
            subSum += (long) data[i];
            dfs(a, i + 1, end, subSum);
            if (subSum <= C)
                a.add(subSum);
            subSum -= (long) data[i];
        }
    }
}
