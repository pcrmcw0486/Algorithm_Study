/*
4*4 배열일 경우
 [0 0 0 0] data[행] = 열 의 위치 꼴로 나타내어
 대각선일 경우 열의 차와 행의 차가 같다는 점을 활용한다.
 아이디어와 코드는 이쁘지만 검사에 시간이 많이 걸린다. 탐색을
 오래해야하기때문에
 
 */
package SolveByCategory.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9663_2 {
    static int N;
    static boolean[] col_check;
    static int[] position;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        position = new int[N];
        solution(0);
        System.out.println(result);
    }

    public static void solution(int count) {
        if (count == N) {
            result++;
        }
        for (int i = 0; i < N; i++) {
            if (isPossible(count, i)) {
                position[count] = i;
                solution(count + 1);
            }
        }
    }

    public static boolean isPossible(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (position[i] == col)
                return false;
            if (Math.abs(row - i) == Math.abs(col - position[i]))
                return false;
        }
        return true;
    }
}