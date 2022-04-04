package DayByDay._0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q20165
 * @문제이름 : 인내의 도미노 장인 호석
 * @난이도 : Gold V
 * @date : 2022-04-04 오후 1:34
 * @author : pcrmcw0486
 * @문제이해
 * 공격수는 도미노를 계속 넘어뜨리고 수비수는 계속 세운다
 * 1. N행 M열의 2차원 격자 모양 게임판 도미노 세운다(1이상 5이하)
 * 2. 공격수 먼저 공격, 수비수는 공격 끝난 후 수비
 * 3. 공격수는 특정 격자에 놓인 도미노를 동,서,남,북 중 원하는 방향으로 넘어뜨린다.
 * 길이가 K인 도미노가 특정방향으로 넘어진다면, 그 방향으로 K-1개의 도미노 중
 * 아직 넘어지지 않은 것들이 같은 방향으로 연달아 넘어진다.  연쇄적으로 넘어질 수 있다
 * 이미 넘어진 도미노 칸을 공격한 경우 아무런 일이 일어나지 않는다.
 * 4. 수비수는 넘어져 있는 도미노 중 하나를 다시 세운다.
 * 5. 총 R번의 라운드 동안 3,4번 과정 반복 매 라운드마다 넘어뜨린 도미노 개수를 센다.
 * R*2개의 줄에 거쳐 공격수와 수비수 행동이 주어진다.
 * 공격 : X Y D (X행, Y열 도미노, D방향으로 민다) E, W, S, N(동서남북)
 * 수비 : X Y (X행, Y열 도미노를 다시 세운다.)
 *
 * 공격수 점수.
 * 넘어진 것은 F
 * 넘어지지 않은 것은 S를 공백으로 구분
 * @알고리즘
 * 구현, 자료구조/
 * @접근방법

*/
public class Q20165 {
    static int N, M, R;
    static int[][] map;
    static boolean[][] isFall;
    static int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[][] attackCmd;
    static int[][] defenceCmd;
    public static void main(String[] args) throws IOException {
        init();
        solve(map, attackCmd, defenceCmd);
    }

    private static void solve(int[][] map, int[][] attackCmd, int[][] defenceCmd) {
        isFall = new boolean[N][M];
        int totCnt = 0;
        for (int step = 0; step < R; step++) {
            int x = attackCmd[step][0];
            int y = attackCmd[step][1];
            int d = attackCmd[step][2];
            if(!isFall[x][y]){
                int fallCnt = map[x][y]-1;
                isFall[x][y] = true;
                totCnt++;
                while (fallCnt > 0) {
                    x = x + dir[d][0];
                    y = y + dir[d][1];
                    if(x<0||y<0||x>N-1||y>M-1) break;
                    fallCnt--;
                    if(isFall[x][y]) continue;
                    isFall[x][y] = true;
                    totCnt++;
                    if (fallCnt < map[x][y] - 1) {
                        fallCnt = map[x][y]-1;
                    }
                }
            }
            x = defenceCmd[step][0];
            y = defenceCmd[step][1];
            isFall[x][y] = false;

        }
        System.out.println(totCnt );
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(isFall[i][j] ? 'F' : 'S').append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i =0;i<N;i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        attackCmd = new int[R][3];
        defenceCmd = new int[R][3];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            attackCmd[i][0] = Integer.parseInt(st.nextToken())-1;
            attackCmd[i][1] = Integer.parseInt(st.nextToken())-1;
            int dir = -1;
            switch (st.nextToken()) {
                case "E":
                    dir =0;
                    break;
                case "W":
                    dir = 1;
                    break;
                case "S":
                    dir = 2;
                    break;
                case "N":
                    dir = 3;
                    break;
            }
            attackCmd[i][2] = dir;

            st = new StringTokenizer(br.readLine());
            defenceCmd[i][0] = Integer.parseInt(st.nextToken())-1;
            defenceCmd[i][1] = Integer.parseInt(st.nextToken())-1;
        }
    }
}
