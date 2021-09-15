package programmers.kakao.Intern2021;

import java.util.*;

public class Solution2 {
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };
    static char[][] map;
    static boolean[][] visited;
    static boolean success;

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] answer = solution.solution(new String[][] { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
                { "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOPOP" }, { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
                { "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } });
        for (int a : answer) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public int[] solution(String[][] places) {
        int T = places.length;
        int[] answer = new int[T];
        while (T-- > 0) {
            map = new char[5][5];
            success = true;
            visited = new boolean[5][5];
            ArrayList<Point> students = new ArrayList<Point>();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    map[i][j] = places[4 - T][i].charAt(j);
                    if (map[i][j] == 'P') {
                        students.add(new Point(i, j, 0));
                    }
                }
            }
            for (char[] line : map) {
                for (char a : line) {
                    System.out.print(a + " ");
                }
                System.out.println();
            }
            System.out.println(students.size());

            for (int i = 0; i < students.size() && success; i++) {
                Point s = students.get(i);
                visited[s.x][s.y] = true;
                dfs(students.get(i));
                visited[s.x][s.y] = false;
            }
            answer[4 - T] = success ? 1 : 0;
        } // i-th Test case end
        return answer;
    }

    private void dfs(Point a) {
        if (a.dist >= 2)
            return;

        for (int i = 0; i < 4; i++) {
            int nx = a.x + dx[i];
            int ny = a.y + dy[i];
            if (nx < 0 || ny < 0 || nx > 4 || ny > 4 || visited[nx][ny])
                continue;
            if (map[nx][ny] == 'X')
                continue;
            if (map[nx][ny] == 'P') {
                success = false;
                return;
            }
            visited[nx][ny] = true;
            dfs(new Point(nx, ny, a.dist + 1));
            visited[nx][ny] = false;
        }
    }

    private class Point {
        int x;
        int y;
        int dist;

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}