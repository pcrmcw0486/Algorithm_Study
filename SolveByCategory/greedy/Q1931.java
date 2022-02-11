/*
사실 처음 보게 되면 DP가 아닌가 하고 생각하게 된다.
이전 단계에서 DP문제를 풀다 와서 그럴 수 있지만 이 문제가
Greedy가 아니라고 생각하고 봐도 충분히 DP로 오해하기 쉽다.
또는 Bruth-force하게 다 넣어보는 방법도 있을 수 있겟다.

local optima가 global optima가 된다는 정당성을 증명해주어야 하는데
이 문제의 경우 두 가지로 생각해볼 수 있다.
처음에는 직관적으로 접근할 수 밖에 없을 것 같다.
1) 처음에 생각햇던 것은 회의실 사용 시간이였는데
 1-5 4-7 6-9의 경우를 생각해보았을 때
 시간이 제일 작은 4-7을 고르게 되면 가장 최적인
 1-5와 6-9를 고르지 못하게 된다.

 2) 두번째가 끝나는 시간이였는데
 부분으로 잘라 보았을 때
 끝나는 시간에 관한 O(5)는 O(1)~O(4)를 포함할 수 있다.
 부분집합의 각 최적의 해가 전체의 최적의 해가 될 수 있다.
 ex) 4-6 5-7 7-11이 있을 경우
  4-6 또는 5-7 과 같이 시간으로 고를 경우
  4-6일 때는 만족하나 5-7 일때는 만족하지 못하는 것을 볼 수 있다.
  이는 부분 최적이 전체 최적으로 이어지지 않는 다는 것을 의미하고
  만약 끝나는 시간 4-6을 고르게 되면 이후의 O(11)의 최적에
  O(6)이 포함되게 된다.
 */
package SolveByCategory.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] data = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1])
                    return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });
        int result = 0;
        int end_time = -1;
        for (int i = 0; i < N; i++) {
            if (data[i][0] >= end_time) {
                end_time = data[i][1];
                result++;
            }
        }
        System.out.println(result);
    }
}
