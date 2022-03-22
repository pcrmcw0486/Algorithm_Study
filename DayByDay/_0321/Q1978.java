package DayByDay._0321;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @문제번호 : Q1978
 * @문제이름 : 소수 찾기
 * @난이도 : Silver V
 * @date : 2022-03-21 오전 10:19
 * @author : pcrmcw0486
 * @문제이해
 * 소수 찾기
 * @알고리즘

 * @접근방법

*/
public class Q1978 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        boolean[] check = new boolean[1001];
        check[0] = check[1] = true;
        for (int i = 2; i <= 1000; i++) {
            if(check[i]) continue;
            for (int j = 2 * i; j <= 1000; j += i)
                check[j] = true;
        }
        int cnt =0;
        for (int x : data) {
            if (!check[x]) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
