package RHS_FC.class05_graph;

/*
탈출 Gold IV
고슴도치는 비버의 굴로 가능한 빨리 도망가고자 함.
. 비어있, * 물, 돌 X, 비버 D, 고슴도치 S
물은 매분 비어있는 칸으로 확장.
물과 고슴도치는 돌을 통과할 수 없음.
고슴도치는 물로 차있는 구역 통과못하고
물도 비버의 소굴로 이동할 수 없음.
최소 시간 구하기.
고슴도치는 물이 찰 예정인 칸으로 이동 못함.
 자 순서 기준으로 보았을 때
 고슴도치는 물이 찰 예정인 칸으로 이동 못하기 때문에
 물을 먼저 채우고 고슴도치를 이동시키도록 한다.
 총 번져가는게 물과 고슴도치임.
 물은 고슴도치와 상관없이 퍼져가기 때문에 기준은 물을 기준으로 한다.

*/
import java.io.*;
import java.util.*;

public class Q3055 {
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        Queue<int[]> position = new LinkedList<int[]>();
        Queue<int[]> water = new LinkedList<int[]>();
        boolean[][] waterVisit = new boolean[N][M];
        int[][] kacVisit = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    position.add(new int[] { i, j });
                    kacVisit[i][j] = 1;
                } else if (map[i][j] == '*') {
                    water.add(new int[] { i, j });
                }
            }
        }
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

        boolean success = false;
        while ((!water.isEmpty() || !position.isEmpty()) && !success) {
            // 물부터
            int waterCnt = water.size();
            for (int i = 0; i < waterCnt; i++) {
                int point[] = water.poll();
                for (int k = 0; k < 4; k++) {
                    int nx = point[0] + dir[k][0];
                    int ny = point[1] + dir[k][1];
                    if (impossible(nx, ny, waterVisit))
                        continue;
                    if (map[nx][ny] == '.') {
                        waterVisit[nx][ny] = true;
                        water.add(new int[] { nx, ny });
                    }
                }
            }
            int kaCnt = position.size();
            if (kaCnt == 0) {
                success = false;
                break;
            }
            for (int j = 0; j < kaCnt; j++) {
                int point[] = position.poll();
                if (map[point[0]][point[1]] == 'D') {
                    success = true;
                    System.out.println(kacVisit[point[0]][point[1]] - 1);
                    break;
                }
                for (int k = 0; k < 4; k++) {
                    int nx = point[0] + dir[k][0];
                    int ny = point[1] + dir[k][1];
                    if (impossible(nx, ny, waterVisit))
                        continue;
                    if (kacVisit[nx][ny] != 0)
                        continue;
                    if (map[nx][ny] == '.' || map[nx][ny] == 'D') {
                        kacVisit[nx][ny] = kacVisit[point[0]][point[1]] + 1;
                        position.add(new int[] { nx, ny });
                    }
                }
            }
        }
        if (!success)
            System.out.println("KAKTUS");

    }

    public static boolean impossible(int x, int y, boolean[][] waterVisit) {
        return x < 0 || y < 0 || x > N - 1 || y > M - 1 || waterVisit[x][y];
    }
}
