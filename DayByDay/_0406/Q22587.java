package DayByDay._0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q22587
 * @문제이름 : 가장 긴 짝수 연속한 부분 수열(small)
 * @난이도 : Silver III
 * @date : 2022-04-06 오후 1:59
 * @문제이해 수열 S는 1 이상의 정수
 * 원하는 위치 수를 골라 최대 K번 삭제 가능.
 * S에서 K번 원소를 삭제한 수열에서 짝수로 이루어져 있는 연속한 부분 수열 중
 * 가장 긴 길이.
 * 인데 최대 K번 원소를 삭제한 수열
 * K는 100.
 * N은 50)000
 * 조합? 절대 안됨.
 * @알고리즘
 * @접근방법 dp or binarySearch
 */

public class Q22587 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //index로 들어감.
        Queue<Integer> possibleIndex = new LinkedList<>();
        Queue<Integer> oddIndex = new LinkedList<>();
        int ans =0;
        for (int i = 0; i < N; i++) {
            if (data[i] % 2 == 0) {
                possibleIndex.add(i);
            }else{
                assert K != 0;
                if (oddIndex.size() == K) {
                    ans = Math.max(ans, possibleIndex.size());
                    int pivotIndex = oddIndex.poll();
                    while (!possibleIndex.isEmpty() && possibleIndex.peek() <= pivotIndex) {
                        possibleIndex.poll();
                    }
                }
                oddIndex.add(i);
            }
        }
        ans = Math.max(ans, possibleIndex.size());
        System.out.println(ans);
    }
}
