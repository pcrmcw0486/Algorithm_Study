package RHS_FC.class04_twoPointer;

/*
라이언 인형은 1 어피치 인형은 2
라이언인형이 K개 이상있는 가장 작은 연속된 인형들의 집합 크기
K랑 N 둘다 10^6
쭉 보는데 O(N)
count 따라 움직이는데 O(N)
 */
import java.io.*;
import java.util.*;

public class Q15565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dolls = new int[N];
        int count = 0;
        int ans = N + 1;
        st = new StringTokenizer(br.readLine());
        for (int l = -1, r = 0; r < N; r++) {
            dolls[r] = Integer.parseInt(st.nextToken());
            if (dolls[r] == 1) {
                count++;
                if (count == K) {
                    l++;
                    while (dolls[l] != 1) {
                        l++;
                    }
                    ans = Math.min(ans, r - l + 1);
                    count--;
                }

            }
        }
        System.out.println(ans == N + 1 ? -1 : ans);
    }
}
