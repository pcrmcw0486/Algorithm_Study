package SAMSUNG.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q14499 {
    static int[][] map;
    static int[] line;
    static int u, d, x, y;
    static int[] data;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //기본 setting.
        int[] order = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        data = new int[7];
        line = new int[]{1, 3, 6, 4};
        u = 2;
        d = 5;
        if (map[x][y] != 0) {
            data[6] = map[x][y];
            map[x][y] = 0;
        }
        for (int i = 0; i < K; i++) {
            int ans = move(order[i]);
            if (ans != -1) {
                System.out.println(ans);
            }
        }
    }

    public static int move(int dir) {
        int nx = x, ny = y, tmp = 0;
      //  System.out.println(dir + " " + x + " , " + y);
        switch (dir) {
            case 1: //동쪽
                ny++;
                if (ny > M - 1) return -1;
                //move후 update
                tmp = line[3];
                for (int i = 3; i > 0; i--) line[i] = line[i - 1];
                line[0] = tmp;
                update(nx, ny);
                break;
            case 2:
                ny--;
                if (ny < 0) return -1;
                tmp = line[0];
                for (int i = 0; i < 3; i++) {
                    line[i] = line[i + 1];
                }
                line[3] = tmp;
                update(nx, ny);
                break;
            case 3:
                nx--;
                if (nx < 0) return -1;
                tmp = line[2];
                line[2] = d;
                d = line[0];
                line[0] = u;
                u = tmp;
                update(nx, ny);
                break;
            case 4:
                nx++;
                if (nx > N-1) return -1;
                tmp = line[2];
                line[2] = u;
                u = line[0];
                line[0] = d;
                d = tmp;
                update(nx, ny);
                break;
        }
        x = nx;
        y = ny;
        return data[line[0]];
    }

    private static void update(int nx, int ny) {
        if (map[nx][ny] == 0) {
            map[nx][ny] = data[line[2]];
        } else {
            data[line[2]] = map[nx][ny];
            map[nx][ny] = 0;
        }
    }
}
