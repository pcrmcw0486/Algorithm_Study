/*Backtracking의 가장 기본 문제인
N-Queen 을 풀어본다. 
N X N 배열에 queen N개 이므로
queen의 특성상 한 열에 queen은 하나밖에 들어가지 못한다.
즉, 행 또는 열 기준으로 하나는 체크를 하지 않아도 된다.
대각또한 밑으로 진행하기 때문에 밑의 대각과 기준에서 제외된 나머지 기준을 체크한다.
*/
package SolveByCategory.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9663_1 {
    static int N;
    static int[][] map;
    static boolean[] col_check;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        col_check = new boolean[N];
        solution(0);
        System.out.println(result);
    }

    public static void solution(int count) {
        if (count == N) {
            result++;
            return;
        }
        // 행에 대해 순차적으로 검색 즉 행의 체크는 불필요함.
        // 행의 증가와 count의 증가가 동일함.
        // 또한 대각선의 체크도 밑으로만 해주면된다.
        for (int i = 0; i < N; i++) {
            if (map[count][i] > 0)
                continue;
            // 대각 check
            int x1 = i;
            int x2 = i;
            for (int j = count + 1; j < N; j++) {
                x1++;
                x2--;
                if (x1 < N)
                    map[j][x1]++;
                if (x2 > -1)
                    map[j][x2]++;
            }
            for (int j = count + 1; j < N; j++) {
                map[j][i] += 1;
            }
            solution(count + 1);
            x1 = i;
            x2 = i;
            for (int j = count + 1; j < N; j++) {
                x1++;
                x2--;
                if (x1 < N)
                    map[j][x1]--;
                if (x2 > -1)
                    map[j][x2]--;
            }
            for (int j = count + 1; j < N; j++) {
                map[j][i]--;
            }
        }

    }
}