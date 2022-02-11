/*
https://www.acmicpc.net/problem/2933
 미네랄 Gold III
 *** 접근 방향 및 문제 조건 ***
 구현문제인데..
 시간이 커버가 가능한가... 
 R*C 최대 값이 10_000 이라 그런가. > 10^5
 10^8 까지 라면
 100_000_000

 *** 알고리즘 자료구조 스킬 ***
  구현 문제, bfs
 */
package challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair2 {
    int y;
    int x;

    public Pair2(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Q2933 {
    static int R, C;
    static int[][] map;
    static int N;
    static int plays[];
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = c[j] == '.' ? 0 : 1;
            }
        }
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int target = R - Integer.parseInt(st.nextToken());
            int j;
            if (n % 2 == 0) {
                j = 0;
                while (j < C) {
                    if (map[target][j] != 0)
                        break;
                    j++;
                }
            } else {
                j = C - 1;
                while (j >= 0) {
                    if (map[target][j] != 0)
                        break;
                    j--;
                }
            }
            if (j >= 0 && j < C)
                map[target][j] = 0;
            process();
            down();
        }
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j] == 0 ? '.' : 'x');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    // 이걸 매번 다 돌아야함?
    // 다 돌면서 cluster 체크하는거 같은데.
    // 일단 벽들만 체크.
    // process() 함수는 throw후 깨진 미네랄에 대해
    // 번호를 부여해가며 모든 클러스터를 체크하는 과정.
    // map에 번호를 붙여서 체크 bfs 1회
    private static void process() {
        int cluste = 0;
        boolean check[][] = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (check[i][j])
                    continue;
                if (map[i][j] == 0)
                    continue;
                cluste++;
                Queue<Pair2> q = new LinkedList<>();
                check[i][j] = true;
                q.add(new Pair2(i, j));
                map[i][j] = cluste;
                while (!q.isEmpty()) {
                    Pair2 cur = q.poll();
                    for (int d = 0; d < dx.length; d++) {
                        int ny = cur.y + dy[d];
                        int nx = cur.x + dx[d];
                        if (ny < 0 || nx < 0 || ny > R - 1 || nx > C - 1)
                            continue;
                        if (check[ny][nx])
                            continue;
                        if (map[ny][nx] == 0)
                            continue;
                        map[ny][nx] = cluste;
                        check[ny][nx] = true;
                        q.add(new Pair2(ny, nx));
                    }
                }
            }
        }
    }

    private static void down() {
        boolean check[][] = new boolean[R][C];
        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (check[i][j])
                    continue;
                if (map[i][j] == 0)
                    continue;
                // 어떠한 해당 클러스터가 발견되면
                // 해당 모든 클러스터를 list에 저장해둠.
                int target = map[i][j];
                ArrayList<Pair2> list = getTargets(target);
                int cnt = 1;
                while (true) {
                    // 내려갈 수 있는가?
                    if (!check(cnt, list)) {
                        cnt--;
                        break;
                    }
                    cnt++;
                }
                // 내려준다. cnt가 0이라도 내려줌.
                // 그전 값은 왜 안바꿔주지? 가 아니라
                // 클러스터 가져오면서 getTargets에서 일단 0으로 모두 바꿔주네.
                // 그래서 0이라도 다시 target값을 넣어줘야하는구나.
                for (Pair2 p : list) {
                    map[p.y + cnt][p.x] = target;
                    check[p.y + cnt][p.x] = true;
                }
            }
        }
    }

    // 리스트에 담긴 클러스터, 모든 미네랄을 cnt만큼 내려본다..
    // 범위를 초과하거나 미네랄을 만나는 경우false를 반환.
    // 이후 down함수에서 true, false체크를 통해 내려가는 값을 증감한다.
    private static boolean check(int cnt, ArrayList<Pair2> list) {
        for (Pair2 p : list) {
            if (p.y + cnt >= R)
                return false;
            if (map[p.y + cnt][p.x] != 0)
                return false;
        }
        return true;
    }

    private static ArrayList<Pair2> getTargets(int target) {
        ArrayList<Pair2> list = new ArrayList<Pair2>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == target) {
                    map[i][j] = 0;
                    list.add(new Pair2(i, j));
                }
            }
        }
        return list;
    }

}
