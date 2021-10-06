package RHS_FC.class04_twoPointer;

/*
https://www.acmicpc.net/problem/16472
고냥이 Gold III
문자열 중에서 최대 N개 종류의 알파벳을 가진 연속된 문자열 인식.
'연속된'
N개가 될 때 까지 쭉 간다.
N+1개가 된다면? 앞쪽에서 N개가 될때까지 증가시킨다.
abb > c가 들어온다
bbc가 된다.
bbc > a
cacc 여기 check를 어떻게 할거냐.
cacc > b
ccb set을 가지고 있어야하는가.
true / false + count로 하던가
set을 사용하던가 둘 중 하나인듯.
true/ false + count로 한번 해보겠습니다.
 */
import java.io.*;
import java.util.*;

public class Q16472 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] data = br.readLine().toCharArray();
        int[] check = new int[26];
        int start = 0;
        int end = 0;
        int count = 0;
        int ans = 0;
        for (end = 0; end < data.length; end++) {
            // N까지 증가
            if (count < N && check[data[end] - 'a'] == 0) {
                count++;
                check[data[end] - 'a']++;
                continue;
            }
            if (check[data[end] - 'a'] > 0) {
                check[data[end] - 'a']++;
                continue;
            } else {
                // 새로운 문자 들어옴. N+1
                count++;
                check[data[end] - 'a']++;
                ans = Math.max(ans, end - start);
                while (count > N) {
                    check[data[start] - 'a']--;
                    if (check[data[start] - 'a'] == 0)
                        count--;
                    start++;
                }
            }
            // N+1이 들어올 때
            // N이 되도록 맞춰주어야함.
        }
        ans = Math.max(ans, end - start);
        System.out.println(ans);

    }
}
