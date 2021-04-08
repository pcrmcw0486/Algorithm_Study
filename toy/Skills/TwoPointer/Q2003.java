/* 11:47
*** 수들의 합 2 Silver III ***
*** 접근 방법 ***
포인터 i,j 를 통해 구간합을 구한다.
i = start, j = end pointer로 잡고
start ~ end 의 합을 기록한다.
D(start, end ) = M
D(start +1, end) = M-A[start]이므로 무조건 작아지니까
다시 시작 가능 
구간합 sum이 <M : j 증가
             =M : i 증가, 
             >M : i 증가 D(start, end)>M이라면 start로
             구할 수 있는 구간합을 다 구하다가 이미 넘어버리므로
             start로 시작해서 M이 될 수 없음.
*** 사용 알고리즘 자료구조 스킬 ***
투 포인터 알고리즘
*** 문제 조건 및 이해 ***
N개의 수로 된 수열
i번째 수 부터 j 번째 수까지의 합이 M이 되는 경우의 수
1<= N =< 10_000
1<= M =< 300_000_000
A[x] < 30_000 자연수
최대로 나올수 있는 구간합 역시 M의 최댓값과 같음
int로 선언시 최댓값이 2,147,483,647이니까 커버 가능
시간 0.5초
O(n^2) 이면 100_000_000 이니까 시간 초과날 수 있음.
 */
package toy.Skills.TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Q2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int[] A = new int[N];
        A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int i = 0;
        int j = 0;
        int sum = A[i];
        int count = 0;

        while (true) {
            if (sum == M)
                count++;
            if (sum < M) {
                j++;
                if (j == N)
                    break;
                sum += A[j];
            } else {
                sum -= A[i];
                i++;
            }
        }
        if (sum == M)
            count++;
        System.out.println(count);
    }
}
