/*
 *** 두 용액 Gold V ***
 *** 접근 방법 ***
 1) sorting
 2) two_pointer알고리즘.
  0에 가깝다 > 절댓값으로 계산.
  - Min 값을 가지고 시작.min=0이면 그냥 종료시키자.
  - 두 값을 기록해두자.
 *** 알고리즘, 자료구조, 활용 스킬 ***
 for문 쓰면 시간초과남.

 투포인터 알고리즘
 *** 문제 조건 및 이해 ***
  @ 시간제한 : 1s
  @ 메모리 : 128MB
  @param  N : 2이상 100_000 이하 > N^2시 100억
산성용액과 알칼리성 용액 
int 범위 2,147,483,647
산성용액 특성값 1~1_000_000_000 양의 정수
알칼리   특성값 -1_000_000_000 ~ -1 음의 정수
가장 크거나 가장 작아도 int값 범위 내임.
같은양 섞으면 특성값의 합
조건1)특성값 0에 가장 가까운 용액만들기
조건2) 같은용액으로도 만드는 경우도 존재한다.
조건3) 서로 다른 용액을 혼합한다.
조건4) 출력해야 하는 특성값의 오름차순으로 출력한다.
조건5) 가장 가까운 용액을 만들어내는 경우가 두개 이상이라면
       아무거나 출력한다.
*/
package toy.Skills.TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Q2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        int[] answer = new int[2];
        int sum = 0;
        data = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(data);
        int left = 0;
        int right = N - 1;
        int min = Integer.MAX_VALUE; // min어차피 양수로 계산
        if (data[left] < 0 && data[right] < 0) {
            answer[0] = data[right - 1];
            answer[1] = data[right];
        } else if (data[left] > 0 && data[right] > 0) {
            answer[0] = data[left];
            answer[1] = data[left + 1];
        } else {
            while (true) {
                // 종료조건
                if (left >= right)
                    break;
                sum = Math.abs(data[left] + data[right]);
                if (sum < min) {
                    answer[0] = data[left];
                    answer[1] = data[right];
                    min = sum;
                }
                if (sum == 0) {
                    answer[0] = data[left];
                    answer[1] = data[right];
                    break;
                }
                if (data[left] < 0 && data[right] < 0) {
                    if (sum < min) {
                        answer[0] = data[right - 1];
                        answer[1] = data[right];
                    }
                    break;
                }
                if (data[left] > 0 && data[right] > 0) {
                    if (sum < min) {
                        answer[0] = data[left];
                        answer[1] = data[left + 1];
                    }
                    break;
                }

                if (Math.abs(data[left]) > Math.abs(data[right])) {
                    left++;
                } else
                    right--;
            }
        }
        System.out.println(answer[0] + " " + answer[1]);

    }

}
