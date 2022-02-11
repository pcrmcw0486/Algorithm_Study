package SolveByCategory.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2580 {
    static int map[][];
    static boolean row_empty[][];
    static boolean col_empty[][];
    static boolean block_empty[][];
    static ArrayList<int[]> empty_list;

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9];
        row_empty = new boolean[9][10];
        col_empty = new boolean[9][10];
        block_empty = new boolean[9][10];
        empty_list = new ArrayList<int[]>();
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int block_num = (i / 3) * 3 + (j / 3);
                int data = Integer.parseInt(st.nextToken());
                map[i][j] = data;
                if (data == 0) {
                    empty_list.add(new int[] { i, j });
                } else {
                    row_empty[i][data] = true;
                    col_empty[j][data] = true;
                    block_empty[block_num][data] = true;
                }
            }
        }

        count = empty_list.size();
        solution(0);
    }

    public static void solution(int progress) {
        if (progress == count) {
            StringBuilder sb = new StringBuilder();
            for (int rows[] : map) {
                for (int n : rows) {
                    sb.append(n).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb.toString());
            System.exit(0);
        }

        int[] point = empty_list.get(progress);
        int x = point[0];
        int y = point[1];
        for (int i = 1; i < 10; i++) {
            if (isPossible(x, y, i)) {
                map[x][y] = i;
                block_empty[(x / 3) * 3 + y / 3][i] = true;
                row_empty[x][i] = true;
                col_empty[y][i] = true;
                solution(progress + 1);
                map[x][y] = 0;
                block_empty[(x / 3) * 3 + y / 3][i] = false;
                row_empty[x][i] = false;
                col_empty[y][i] = false;
            }
        }
    }

    public static boolean isPossible(int row, int col, int number) {
        int block_num = (row / 3) * 3 + col / 3;
        if (!block_empty[block_num][number] && !row_empty[row][number] && !col_empty[col][number])
            return true;
        return false;
    }
}
