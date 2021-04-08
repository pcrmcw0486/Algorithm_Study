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
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Q1450 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] item = new int[N];
        item = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(item);

        int left = 0;
        int right = 0;
        int sum = 0;
        double count = 0;
        int temp = 0;

        // 똑같이 부분합을 구함.
        // 부분합과 같아지는 시점에서 count개수를 구함.
        // 다시 진행하다가 부분합과 같아지는 시점을 구함
        //
        while (true) {
            if (sum < C) {
                if (right == N)
                    break;
                sum += item[right];
                right++;
            } else {
                if (sum == C) {
                    if (temp <= left) {
                        count += Math.pow(2.0, right - left) - 1;
                    } else {
                        count += Math.pow(2.0, temp - left) * (Math.pow(2.0, right - temp) - 1);
                    }
                    temp = right;
                }
                sum -= item[left];
                left++;
            }
        }
        count += Math.pow(2.0, right - temp) - 1;
        System.out.println((int) count);
    }
}
