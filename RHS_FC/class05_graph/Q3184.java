package RHS_FC.class05_graph;

/*
양 Silver II
. 빈필드 # 울타리 o 양 v 늑대
울타리를 지나지 않고 다른 칸으로 이동할 수 있다면 같은 영역안에 속해있다고 한다.
마당에서 "탈출"할 수 있는 칸은 어떤 영역에도 속하지 않는다.

영역안에서 양의 수가 늑대보다 많으면 이기고 아니면 양이 먹힘.
맨 처음 모든 양과 늑대는 마당 안 영역에 있음.
양과 늑대 수를 쓰시오.
R, C <= 250
*/
import java.io.*;
import java.util.*;

public class Q3184 {
    static char[][] map;
    static boolean[][] visit;
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        ArrayList<int[]> candidate = new ArrayList<int[]>();
        visit = new boolean[R][C];
        // 양 또는 늑대일때 queue에 넣고
        // 해당 영역에서의 양 및 늑대의 수를 구해서 계산한다.
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'o' || map[i][j] == 'v') {
                    candidate.add(new int[] { i, j });
                }
            }
        }
        int[] answer = new int[2];
        for (int[] point : candidate) {
            if (!visit[point[0]][point[1]]) {
                int[] result = bfs(point[0], point[1]);
                if (result[0] > result[1]) {
                    answer[0] += result[0];
                } else {
                    answer[1] += result[1];
                }
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }

    public static int[] bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<int[]>();
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int[] result = new int[2];
        queue.add(new int[] { x, y });
        visit[x][y] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            if (map[point[0]][point[1]] == 'o')
                result[0]++;
            else if (map[point[0]][point[1]] == 'v')
                result[1]++;
            for (int k = 0; k < 4; k++) {
                int nx = point[0] + dir[k][0];
                int ny = point[1] + dir[k][1];
                if (nx < 0 || ny < 0 || nx > R - 1 || ny > C - 1)
                    ;
                if (visit[nx][ny])
                    continue;
                if (map[nx][ny] == '#')
                    continue;
                visit[nx][ny] = true;
                queue.add(new int[] { nx, ny });
            }
        }
        return result;
    }
}
