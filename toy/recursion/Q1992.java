/*단계별로 풀어보기 - D&C
쿼드트리 (Silver 1)
D&C 이용한 recursion  */
package toy.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Q1992 {
    public static int[][] map;
    static StringBuilder sb2 = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++)
            map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        // System.out.println(solution(0, 0, N));
        solution2(0, 0, N);
        System.out.println(sb2.toString());

    }

    public static String solution(int x, int y, int length) {
        if (length == 1) {
            return String.valueOf(map[x][y]);
        }
        int part = (length) / 2;
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = x; i < x + length; i += part) {

            for (int j = y; j < y + length; j += part) {
                StringBuilder part_seq = new StringBuilder();
                part_seq.append(solution(i, j, part));
                if (part_seq.toString().compareTo("(0000)") == 0)
                    part_seq.replace(0, part_seq.length(), "0");
                else if (part_seq.toString().compareTo("(1111)") == 0)
                    part_seq.replace(0, part_seq.length(), "1");
                sb.append(part_seq);
            }
        }
        sb.append(")");
        if (sb.toString().compareTo("(0000)") == 0)
            sb.replace(0, sb.length(), "0");
        else if (sb.toString().compareTo("(1111)") == 0)
            sb.replace(0, sb.length(), "1");

        return sb.toString();
    }

    public static void solution2(int x, int y, int length) {
        if (check(x, y, length)) {
            sb2.append(map[x][y]);
            return;
        }
        sb2.append("(");
        solution2(x, y, length / 2);
        solution2(x, y + length / 2, length / 2);
        solution2(x + length / 2, y, length / 2);
        solution2(x + length / 2, y + length / 2, length / 2);
        sb2.append(")");
    }

    public static boolean check(int x, int y, int length) {
        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + length; j++) {
                if (map[x][y] != map[i][j])
                    return false;
            }
        }
        return true;
    }
}
