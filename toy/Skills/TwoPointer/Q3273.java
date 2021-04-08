/*
*** 두 수의 합 (SILVER IV) ***
*** 접근 방법 ***
*** 알고리즘 및 자료구조 ***
*** 문제 조건 ***
n개 서로다른 양수 
1<=N =< 100_000
N^2 10_000_000_000 1억 넘네
1<= a <= 1_000_000
ai+aj = x (0<= i < j <=n-1)을 만족하는 쌍의 수를 구하라
i와 j는 같지 않다.
1<=x<=2_000_000 
int형으로 가능
i와 j는 같지 않음.
시간 제한 1초
for문으로 짜면 안됨? O(N^2)이네. 서로 다른 양의정수
 x+y = A 일때 x가 정해지면 y도 정해짐.
 for문을 쓰면 O(n^2) >> 시간 초과가 나네요.
 정렬하는데 O(nlogn) + 구간합 two_pointer로 때리면 O(N)
 */
package toy.Skills.TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Q3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        data = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int target = Integer.parseInt(br.readLine());
        Arrays.sort(data);

        int left = 0;
        int right = N - 1;
        int count = 0;
        int sum = 0;
        // 정렬이 되어 있음.
        // left와 right는 만나서는 안됨
        // 또한 right가 target보다 커지는 순간 답이 존재하지 않음.
        // 하지만 left+ right보다 right가 커질일은 없고
        // 해가 존재 하지 않는 경우도 있을 수도 잇지
        // 다 짝순데 답이 홀수면.
        // 그렇게 되면 right가 범위를 넘어가게 됨.
        // 종료조건 정리) right가 범위를 넘거나 또는 left==right이면
        // right가 범위를 넘거나 target보다 커지는 순간.
        // ex) 2 4 6 8 11 14 target = 9 (right가 target보다 커지는) 또는
        // 2 4 6 8 12 14 target = 9 (right가 범위를 넘기는)
        while (true) {
            if (right <= left)
                break;
            sum = data[left] + data[right];
            if (sum == target) {
                count++;
                left++;
                right--;
            }
            if (sum > target)
                right--;
            else if (sum < target)
                left++;
        }
        System.out.println(count);
    }
}
