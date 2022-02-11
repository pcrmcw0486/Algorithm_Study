/*
https://www.acmicpc.net/problem/14003
*** 가장 긴 증가하는 부분 수열 5 Platinum V ***
<비교 DP Q14002 > 
*** 접근 방향 ***
Q14002와는 다르게 데이터의 크기가 커졌기 때문에 dp로 해결 불가
Q12015와 같이 접근해서 풀게되는데, 이 때 이진탐색을 이용한
오름차순 수열 생성의 경우 size는 같으나 값의 순서가 달라짐.
 1) - 이 역시도 순서를 기억해야 하므로 memoization을 사용해야한다고
  보고 접근. 어떻게 기록할 것인가에 초점을 두고 생각
    - 어차피 마지막 값 바뀔 때 한번 바뀜. 그전에 바뀌는건 의미X
     - 값을 담는 배열과 index를 담는 배열을 두고
     - 비교는 index의 값과 비교한다.
     의 반례를 찾았다 비단 음수만의 문제는 아님.
     9
     10 11 7 9 8 5 7 -5 8 또는 10 11 7 9 8 5 7 1 8
     자리를 찾아가면서 바꾸는 과정에서 data와 prev가 달라 prev에서만 검사하는 과정인데,
     5 7 일때 prev[index]보다 크고 data1[index]보다 작은 경우 update가 되는데 이 과정에서
     prev에 제대로 update가 되지 않는 경우가 있다. prev[len]보다 작은 수 중에서 미리 update가 되는 것
     즉, update가 되면 안되는데 되는 경우가 있음. 제대로 덜 바뀐거지.
     크게 생각하면 LIS구하는 방식에서 나오는 배열 자체가 LIS가 아님을 알고 있었지만 그 배열로 다시 udpate하려고하는
     이상한 생각을 했음
2) 가장 먼저 생각한대로, Q14002 처럼 index를 기록하는 방식으로 진행한다.
  수의 가능한 위치를 기록(preIndex)하여 최장 수열 길이부터 내려오면서
  print
   
*** 알고리즘 자료구조 스킬 ***
- 이분탐색 을 이용한 오름차순 자리 찾기
   (k보다 큰 수 중 가장 최솟값 찾기)  [-- false --||-- true --] 
*** 문제 조건 ***
@param
    N : 수열 A의 크기 (1<= N <= 1_000_000)
    x : A 안에 데이터 크기 (-1_000_000_000 <= x <= 1_000_000_000)
    int 형으로 가능 하겟네요
@output
    - 가장 긴 증가하는 부분 수열의 길이
    - 임의의 해당 수열 출력.

 */
package SolveByCategory.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q14003 {
    static int len1 = 0;
    static int[] data1;
    static int[] prev1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N]; // data input
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int[] positionIdx = new int[N]; // prev index
        for (int i = 0; i < N; i++)
            positionIdx[i] = i;

        int[] list = new int[N]; // save LIS sequence
        int idx = 0; // sequence Length
        list[idx++] = arr[0];

        for (int i = 1; i < N; i++) {
            if (list[idx - 1] < arr[i]) {
                positionIdx[i] = idx;
                list[idx++] = arr[i];
            } else {
                // lowerbound
                int left = 0;
                int right = idx - 1;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (list[mid] < arr[i])
                        left = mid + 1;
                    else
                        right = mid;
                }
                // 가능한 위치
                positionIdx[i] = right;
                list[right] = arr[i];
            }
        }
        int target = idx - 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            if (positionIdx[i] == target) {
                stack.push(arr[i]);
                target--;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(idx).append("\n");
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(" ");
        System.out.println(sb.toString());

    }

    public static void fail(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        data1 = new int[N];
        prev1 = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        data1[0] = Integer.parseInt(st.nextToken());
        prev1[0] = data1[0];
        for (int i = 1; i < N; i++) {
            int item = Integer.parseInt(st.nextToken());
            if (item > data1[len1]) {
                len1++;
                data1[len1] = item;
                prev1[len1] = item;
            } else if (item > prev1[len1]) {
                len1++;
                prev1[len1] = item;
                for (int j = 0; j <= len1; j++) {
                    data1[j] = prev1[j];
                }
            } else {
                int position = lowerBound1(item);
                prev1[position] = item;
            }
            // 1 2 3 7 8 9 4 5 6 7 10
        }
        StringBuilder sb = new StringBuilder();
        sb.append(len1 + 1).append("\n");
        for (int i = 0; i <= len1; i++) {
            sb.append(data1[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    public static int lowerBound1(int item) {
        int left = 0;
        int right = len1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (item <= prev1[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
