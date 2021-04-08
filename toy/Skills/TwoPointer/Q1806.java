/*
*** 부분합 Gold IV ***
https://www.acmicpc.net/problem/1806
*** 접근 방향 ***
문제를 간단히 하면
부분합을 구하면서 그 길이가 짧은 것을 저장하면됨.
*** 알고리즘, 자료구조, 스킬 ***
data 수가 커서 N^2이면 시간 초과
*** 문제 조건 및 이해 ***
@시간 : 0.5s
@메모리 : 128MB
@ 10 <= N <= 100_000
@ 0 < S <= 100_000_000 
연속된 수들의 부분합 중에 그 합이 S가 되는 것 중
가장 짧은 것의 길이.
> 부분합 구하는 문제와 같음.
조건 1) 불가능 하다면 0 출력
조건 2) 더하면서 나올 수 있는 최대 숫자가
     10_000 * 100_000 = 1_000_000_000 이므로 int형에서 가능.
조건 3) sorting X 연속된 수들의 부분합
조건 4) S 이상 > 처음 S또는 S이상이 되면 그 뒤로는 더 길어질 뿐임.
 */
package toy.Skills.TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Q1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int target = Integer.parseInt(input[1]);
        int[] data = new int[N];
        data = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int left = 0;
        int right = 0;
        int sum = data[left];
        int min = Integer.MAX_VALUE;
        while (left <= right) {
            if (sum < target) {
                right++;
                // right가 증가해서 N이라는 말은
                // 이미 그전에 right가 N-1(끝)에 있을 때
                // 계산을 다 했다는 뜻임.
                if (right == N)
                    break;
                sum += data[right];
            } else {
                min = Math.min(min, right - left + 1);
                sum -= data[left];
                left++;
            }

        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}
