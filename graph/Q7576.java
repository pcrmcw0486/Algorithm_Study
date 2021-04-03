/*Q7576 토마토 Silver1 
익은 토마토 인접 안익은 토마토 익어짐.
익 : 0, 안익 : 1, 없음 : -1
최종적으로 모두 익는 횟수
*/
package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new LinkedList<>();
        int[][] map = new int[M][N];
        int count = 0;
        int day = -1;
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int check = Integer.parseInt(st.nextToken());
                if (check == 0)
                    count++;
                else if (check == 1) {
                    queue.add(new int[] { i, j });
                }
                map[i][j] = check;
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] position = queue.poll();
                for (int j = 0; j < dx.length; j++) {
                    int nx = position[0] + dx[j];
                    int ny = position[1] + dy[j];
                    if (nx < 0 || ny < 0 || nx >= M || ny >= N)
                        continue;
                    if (map[nx][ny] == 0) {
                        count--;
                        map[nx][ny] = 1;
                        queue.add(new int[] { nx, ny });
                    }
                }
            }
            day++;
        }
        System.out.println(count == 0 ? day : -1);

    }
}
