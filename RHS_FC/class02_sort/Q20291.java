package RHS_FC.class02_sort;

/*
파일정리 Silver IV
확장자 별로 정리해서 몇개씩 있는지
확장자들 사전순 정리.

확장자 이름과 확장자 파일 개수를 한 줄 씩 정리.
확장자 이름 사전순 출력.
 */
import java.util.*;
import java.io.*;

public class Q20291 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String[] a = new String[N + 1];
        for (int i = 1; i <= N; i++) {
            a[i] = br.readLine().split("\\.")[1];
        }
        Arrays.sort(a, 1, N + 1);

        for (int i = 1; i <= N;) {
            // a[i] 와 같은 모든 것을 하나씩 찾는다.
            int cnt = 1, j = i + 1;
            for (; j <= N; j++) {
                if (a[j].compareTo(a[i]) == 0)
                    cnt++;
                else
                    break;
            }

            sb.append(a[i]).append(' ').append(cnt).append('\n');

            // a[j] 가 a[i] 랑 다른 상황! 즉, 다음 i 는 j 부터 시작하면 된다.
            i = j;
        }

        // 정답 출력하기
        System.out.print(sb.toString());
    }
}
