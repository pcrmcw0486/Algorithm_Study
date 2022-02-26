package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q16742
 * @문제이름 : 피리부는 사나이
 * @난이도 : Gold II
 * @date : 2022-02-17 오후 7:03
 * @문제이해 map에 정해진 대로 움직였을 때 더이상 움직이지 않게하는 safe존의 최소 개수 구하기.
 * @알고리즘 그래프 dfs? bfs?
 * 구현
 * @접근방법 어떤 맵에 쓰여진 대로 움직이게 된다.
 * 어떤 움직임이 끝나지 않는다? -> 사이클을 이루고 있다.
 * 어느 구역에 있더라도 움직임이 끝나야하기 때문에 이러한 사이클을 모두 제거해주는 최소 개수를 구한다.
 * 사이클인지를 체크하는 배열 그리고 방문한지를 체크하는 배열 두가지를 둔다.
 * 어떤 새로운 곳에서 움직이다가 사이클인 곳을 방문햇다면? 이미 그 사이클은 체크가 된 사이클이기 때문에
 * 더이상 진행하지 않아도 된다.
 * 그게 아니라면 되돌아올때 까지 가서 safezone을 체크해주도록 한다.
 *
 * ..다시 그룹구하기 DFS 또는 disjointset
 */
public class Q16742 {
    static boolean[][] isCycle, visited;
    static int[][] map;
    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int N, M;
    static int ans;
    static int parent[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String datainfo[] = br.readLine().split(" ");
        N = Integer.parseInt(datainfo[0]);
        M = Integer.parseInt(datainfo[1]);
        map = new int[N][M];
        isCycle = new boolean[N][M];
        visited = new boolean[N][M];
        ans = 0;
        parent = new int[N * M];
        int idx =0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = getDir(line.charAt(j));
                parent[idx] = idx;
                idx++;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    findCycle(i, j);
                }
            }
        }
        System.out.println(ans);
    }

    public static void findCycle(int x, int y) {
        int r, c;
        r = x;
        c = y;
        while (true) {
            visited[r][c] = true;
            int uidx= r*N+c;
            int d = map[r][c];
            int nx = r + dir[d][0];
            int ny = c + dir[d][1];
            int pidx = nx*N + ny;
            if (visited[nx][ny]) {
                //정한 safeZone (root)
                if(parent[pidx] == pidx){
                    parent[uidx] = pidx;
                    ans++;
                }
                break;
            }
            parent[pidx] = uidx;
            r = nx;
            c = ny;
        }
    }

    public static int getDir(char c) {
        if (c == 'U') return 0;
        if (c == 'R') return 1;
        if (c == 'D') return 2;
        else return 3;
    }
    public static int find(int x){
        if(x==parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
