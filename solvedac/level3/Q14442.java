package solvedac.level3;
/*
2021.11.04
문제번호 : Q14442
이름 및 난이도 : 벽 부수고 이동하기 2
문제이해 
-----------------
벽을 k개 부수고 지나갈 때 최단 경로
접근 방법 :
상태공간의 이해, bfs는 최단거리를 구할 때 사용하지만, 여기서의 최단거리에는 조건이 붙는다.
조건을 고려하기 위한 상태 공간이 필요하다. 
제한 조건 : 

*/

import java.io.*;
import java.util.*;

public class Q14442 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        int[][][] visited = new int[N][M][K + 1];

        int answer = -1;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[] { 0, 0, 0 });
        visited[0][0][0] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            System.out.println(" x : " + cur[0] + " y : " + cur[1] + " " + cur[2]);
            if (cur[0] == N - 1 && cur[1] == M - 1) {
                answer = visited[cur[0]][cur[1]][cur[2]];
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dir[i][0];
                int ny = cur[1] + dir[i][1];
                if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1)
                    continue;
                // visit 체크는 현재 지금 들어온 상태의 기준임.
                if (map[nx][ny] == 1 && cur[2] + 1 <= K && visited[nx][ny][cur[2] + 1] == 0) {
                    visited[nx][ny][cur[2] + 1] = visited[cur[0]][cur[1]][cur[2]] + 1;
                    queue.add(new int[] { nx, ny, cur[2] + 1 });
                } else if (map[nx][ny] == 0 && visited[nx][ny][cur[2]] == 0) {
                    visited[nx][ny][cur[2]] = visited[cur[0]][cur[2]][cur[2]] + 1;
                    queue.add(new int[] { nx, ny, cur[2] });
                }
            }
        }
        System.out.println(answer);
    }
}