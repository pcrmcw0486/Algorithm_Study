package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Review_12015
 * @문제이름 : 가장 긴 증가하는 부분 수열 2
 * @난이도 : Gold II
 * @date : 2022-03-01 오전 7:47
 * @author : pcrmcw0486
 * @문제이해
 * 수열 A가 주어질 때 가장 긴 증가하는 부분 수열을 구하자.
 * @알고리즘
 * 이분탐색
 * @접근방법
 * 우리가 집중해야되는건 '가장 긴 증가하는 수열'이 된다.
 * 즉, 증가하는 수열의 길이가 중요하다.
 * 1 2 5 10 9 라고 했을 때
 * 1 2 5 10 이나 1 2 5 9 나 길이면에선 같다는 의미이다.
 * 길이에 집중해서 문제를 풀어보도록 하자.
*/
public class Review_12015 {
    static int length =0;
    static int[] possible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        possible = new int[N];
        length =0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (length==0 || possible[length-1] < num) {
                possible[length++] = num;
            }else
                findPosition(0, length, num);
        }
        System.out.println(length);
    }

    public static void findPosition(int left, int right, int value) {
        int l = left;
        int r = right-1;
        int mid =0;
        while (l <= r) {
            mid = (l+r)>>1;
            if (possible[mid] <value) {
                l = mid +1;
            }else{
                r = mid-1;
            }
        }
        possible[l] = value;
    }
}
