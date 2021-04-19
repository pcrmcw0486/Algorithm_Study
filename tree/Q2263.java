/*
https://www.acmicpc.net/problem/2263
*** 트리의 순회 Gold III ***
*** 접근 방법 ***
LVR과 LRV가 주어졌을 때 
post에서 V를 찾아 inorder에서 V를 찾을 때 그 값을 기준으로
왼쪽과 오른쪽이 나누어짐에 착안하여 나누어 지는 길이값이
동일해야함으로(Left 길이, right길이) 이를 이용하여 해결
 > 문제 접근은 올바르게 했으나 배열에서 구간을 분할하는 과정에서
 > 실수와 혼동이 다수 있었음.
 > 길이로 가야하는데 -1, +1 로만 생각한다던가,
 > 두 배열의 포인터를 다르게 두고 풀어야 하는데 어거지로 어떻게든
 > 하나로 풀려고 했다던가 여튼 명확하게 생각하지 않은 상태에서
 > 문제를 풀려고 하니 굴레에 빠져서 잘 나오지 못했다.
 > 더 명확하게 풀어보자.
*** 알고리즘 자료구조 스킬 ***
두 배열에서 같은 연산이 반복됨(구간만 변경)
분할 정복을 통해 각 배열의 위치만 투 포인터로 변경해주어서
해결.
*** 문제 조건 ***
자연수 1~N 으로 중복없이 이루어진 트리에서
inorder와 postorder가 주어졌을 때
preorder를 구하시오
 */
package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Q2263 {
    static int[] in;
    static int[] post;
    static int[] idx;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        in = new int[N + 1];
        post = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            in[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }
        idx = new int[N + 1];
        for (int i = 1; i <= N; i++)
            idx[in[i]] = i;
        solve(1, N, 1, N);
        System.out.println(sb.toString());
    }

    public static void solve(int in_start, int in_end, int post_start, int post_end) {
        if (in_start > in_end || post_start > post_end)
            return;
        int value = post[post_end];
        // System.out.println(value);
        int midIdx = idx[value];
        sb.append(value).append(" ");
        int left = midIdx - in_start;
        solve(in_start, midIdx - 1, post_start, post_start + left - 1);
        solve(midIdx + 1, in_end, post_start + left, post_end - 1);
    }
}
