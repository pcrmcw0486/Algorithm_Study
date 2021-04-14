/*
*** 공유기 설치 Silver I ***
*** 접근 방법 및 방향 ***
인접한 두 공유기 사이가 최대가 된다.
 > C개의 공유기 사이의 최소값이
   최대로 되게 한다.(최댓값구하기 문제)(boundary)
거리가 point, 
가장 양 끝에는 공유기가 무조건 설치된다.
처음 문제 접근 시에는 고정되어 있는 집의 위치에 포인트를 두었다.
그렇게 되면 조합 문제가 되어버린다.
공유기 사이에 포인트를 두고 진행한다. > 거리 결정 문제로 변환
조건을 만족하는.
집 사이 거리 min값과 max값을 두고

*** 사용 알고리즘, 자료구조, 스킬 ***
이분 탐색
추가 삭제 연산 보다 탐색 연산이 많으므로 배열을 사용한다.
*** 문제 조건 ***
N개의 집이 수직 선 위에 있다.
같은 좌표를 가지는 N은 없다.
와이파이를 즐기기 위해 집에 공유기 C개를 설치하려고 한다.
한 집에 공유기는 하나이다.
가장 인접하 두 공유기 사이의 거리를 가능한 크게 하여 설치한다.
@param
  집 개수  2 <= N <= 200,000
  공유기 개수 2<= C <= N
  집 좌표 0 <= xi <= 1_000_000_000
@input
    N C , 집 좌표 xi()
*/
package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2110 {
    static int[] position;
    static int N;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        position = new int[N];
        for (int i = 0; i < N; i++) {
            position[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(position);

        // search
        if (C < 3)
            System.out.println(position[N - 1] - position[0]);
        else {
            // 2개는 양 끝점
            int low = 1;
            int high = (position[N - 1] - position[0]);
            int ans = 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (condition(mid)) {
                    // 만족했다면? 더 큰 값을 찾으러 가야함.
                    ans = Math.max(ans, mid);
                    low = mid + 1;
                } else {
                    // 만족하지 않았다면 거리가 너무 긴 것.
                    high = mid - 1;
                }
            }
            System.out.println(ans);
        }
    }

    public static boolean condition(int length) {
        int start = position[0];
        int count = 1;
        for (int i = 1; i < N; i++) {
            if (start + length <= position[i]) {
                start = position[i];
                count++;
            }
        }
        if (count >= C)
            return true;
        else
            return false;
    }
}