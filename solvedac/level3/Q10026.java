package solvedac.level3;
/*
2021.12.06
문제번호 : Q10026
이름 및 난이도 : 적록색약 Gold V
문제이해 
-----------------
N*N R G B
그림의 구역이 나누어져있고, 구역은 같은 색으로 이루어져 있다.
색상이 상하좌우 인접 시 두 글자는 같은 구역(색상의 차이를 느끼지 못하는 경우 인정)
색약이 있는 사람과 없는 사람이 보는 구역의 개수를 구하시오.
접근 방법 :
    1. 구역을 구하는 함수가 필요하다.
    2. 색 구분이 가능하다면 input그대로 넣게 된다. 
    3. 색구분이 되지 않는다면 R->G 로 바꾸어 구하면 된다.
    결국 총 2번 구해야한다.
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q10026 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[][] originalData = new char[N][N];
        char[][] changeData = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                // green to Red
                char c = line.charAt(j);
                originalData[i][j] = c;
                changeData[i][j] = c == 'G' ? 'R' : c;
            }
        }

        int c1 = countDistrict(originalData);
        int c2 = countDistrict(changeData);
        System.out.println(c1 + " " + c2);
    }

    public static int countDistrict(char[][] data) {
        boolean[][] visited = new boolean[N][N];
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        Queue<Point> queue;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char color = data[i][j];
                if (!visited[i][j]) {
                    queue = new LinkedList<Point>();
                    queue.add(new Point(i, j));
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        Point cur = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nx = cur.x + dir[d][0];
                            int ny = cur.y + dir[d][1];
                            if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
                                continue;
                            if (visited[nx][ny])
                                continue;
                            if (data[nx][ny] == color) {
                                visited[nx][ny] = true;
                                queue.add(new Point(nx, ny));
                            }
                        }
                    }
                    cnt++;
                }
            }
        }
        return cnt;

    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
