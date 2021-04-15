/*
*** 가장 긴 증가하는 부분 수열 2, LIS (Gold II)
*** 접근 방법 ***
이전에 dp로 풀었을 때는 확인하려고 하는 index j 에 대해
0~j까지의 모든 배열을 검색하며 찾아서 relax해줌.
- 구하려고 하는 j에 대해 그 전의 배열을 긴 길이에 대해 소팅해둔다.
 data[j]보다 작은 수 중 가장 큰 dp값을 찾는다로 바꿈.
 - dp값으로 소팅된 배열에서 
 */
package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        int length = 0;
        for (int i = 1; i < N; i++) {
            if (data[length] < data[i])
                data[++length] = data[i];
            else {
                int left = 0;
                int right = length;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (data[mid] >= data[i]) {
                        right = mid;
                    } else
                        left = mid + 1;
                }
                data[left] = data[i];
            }

        }
        System.out.println(length + 1);
    }
}
