/*
*** 소수의 연속 합 ***
https://www.acmicpc.net/problem/1644
*** 접근 방법 ***
1. 주어진 자연수 까지 존재하는 소수를 구한다.
 소수 구하는 방법? >> 1) 에라토스테네스의 체O(n log logn)
2. 구해진 소수에서의 연속된 부분합을 구한다. O(n)

*** 사용 알고리즘 자료구조 스킬 ***
알고리즘 자구 없이 스킬이면 그냥 구현
- 투 포인터 + 소수 이해
*** 문제 조건 및 이해 ***
@time : 2s
@memory : 128MB
@input N : 1<= N <= 4_000_000
문제 : 하나 이상의 연속된 소수의 합으로 N을 나타내자.
ex) 3:3 
   41 : 2 + 3 + 5 + 7 + 11 + 13 = 11 + 13 + 17 = 41 : 3가지
   20의 경우 : 7 + 13 (중간에 11이 없음) X
조건 1) 한 소수는 한 번만 덧셈에 사용가능
조건 2) 연속된 소수여야 함.
경우의 수 구하기.
*/
package toy.Skills.TwoPointer;

import java.util.Scanner;

public class Q1644 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();
        int[] primes = new int[N + 1];
        boolean[] data = new boolean[N + 1]; // 초기화 false
        int idx = 0;

        // => 간단하게 또는 그냥 한번에 N까지 돌면서 바로 넣어줌. 어차피 N제곱근 뒤는 for문 돌지도 못함.
        // 소수 구하기
        data[0] = true;
        data[1] = true;
        for (int i = 2; i < N + 1; i++) {
            if (!data[i]) {
                primes[idx] = i;
                idx++;
                for (int j = i * 2; j < N + 1; j += i) {
                    data[j] = true;
                }
            }
        }

        int left = 0;
        int right = 0;
        int count = 0;
        int sum = 0;
        while (true) {
            if (sum < N) {
                if (right == idx)
                    break;
                sum += primes[right];
                right++;
            } else {
                if (sum == N)
                    count++;
                sum -= primes[left];
                left++;
            }
        }
        System.out.println(count);
    }
}
