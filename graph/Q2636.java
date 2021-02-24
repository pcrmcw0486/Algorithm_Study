/*치즈 (Gold V) 
 1. 외부 공기 구하기
    > edge -1처리 및 queue 저장

*/
package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import java.io.InputStreamReader;

public class Q2636 {

    static int[][] map;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        solution();
    }

    public static void solution() {
        Queue<int[]> air_queue = new LinkedList<>();
        int count = 0;
        int temp = 0;
        int phase = 0;
        int[] dx = new int[] { -1, 1, 0, 0 };
        int[] dy = new int[] { 0, 0, -1, 1 };
        while (true) {
            boolean[][] visit = new boolean[N][M];
            temp = 0;
            phase++;
            air_queue.offer(new int[] { 0, 0 });
            visit[0][0] = true;
            while (!air_queue.isEmpty()) {
                int[] point = air_queue.poll();
                for (int i = 0; i < dx.length; i++) {
                    int nx = point[0] + dx[i];
                    int ny = point[1] + dy[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && visit[nx][ny] == false) {
                        if (map[nx][ny] <= 0) {
                            map[nx][ny] = -1;
                            air_queue.offer(new int[] { nx, ny });
                        } else if (map[nx][ny] == 1) {
                            map[nx][ny] = -1;
                            temp++;
                        }
                        visit[nx][ny] = true;
                    }
                }
            }
            if (temp == 0)
                break;
            count = temp;
        }
        System.out.println(phase - 1);
        System.out.println(count);
    }
}
