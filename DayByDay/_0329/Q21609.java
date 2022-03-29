package DayByDay._0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @문제번호 : Q21609
 * @문제이름 : 상어중학교
 * @난이도 : Gold II
 * @date : 2022-03-29 오후 2:34
 * @author : pcrmcw0486
 * @문제이해
 * 크기가 N*N인 격자에서 진행
 * 초기에 격자의 모든 칸에는 블록이 하나씩 들어있고, 블록은 [검은색, 무지개, 일반]
 * 일반 블록은 M가지 색상, M이하의 자연수로 표현 검은색은 -1, 무지개는 0
 * 상하좌우
 * '블록 그룹'은 연결된 블록의 집합
 *   - 일반 블록 하나이상 있어야함
 *   - 일반 블록의 색상은 모두 같음
 *   - 검은색블록은 포함되면 안되고, 무지개 블록은 상관없다.
 *   그룹에 속한 블록의 개수는 2보다 크거나 같아야함
 *   임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동하여 그룹에 속한 다른 모든 칸으로 이동가능해야함.
 *   블록 그룹의 기준 블록은 '일반블록 중 행의번호가 가장 작은 블록, 열이 작은 블록'
 *  오토 플레이 기능
 *  1. 크기가 가장 큰 블록 그룹을 찾는다.
 *    - 그러한 그룹이 여러개라면, 무지개 블록의 수가 가장 많은 블록그룹
 *    - 기준 블록의 행이 가장 큰것, 열이 가장 큰것
 *  2. 1에서 찾은 블록의 모든 블록을 제거하고, 블록 포함 수를 B라고 할 때 B^2점을 획득
 *  3. 격자에 중력이 작용
 *  4. 격자에 90도 반시계 방향으로 회전
 *  5. 다시 격자에 중력이 작용.
 *  (검은 블록은 중력이 작용하지 않는다)
 * @알고리즘
 * //DFS로 그룹핑 그룹 Status List로 담아놓는다.
 * //그룹 status 정렬.
 * // 삭제... 이후 move move. 삭제는?어케 표시할거임 isDeleted?
 * not deleted된것 만 담는다. (stack. -1 또는 벗어나면 출력하기 기준 pos)따라.
 * 반시계 N2 map[i] -> map[뒤에서부터] [i]
 * -2 EMPTY?
 * @접근방법
*/
public class Q21609 {
    static int RAINBOW =0;
    static int BLACK = -1;
    static int EMPTY = -2;
    static int[][] map;
    static boolean[][] isVisited;
    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int N,M;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        ans =0;
        for (int i = 0; i < N; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        solve();
        System.out.println(ans);
    }

    private static void solve() {
        while(true) {
            // find BestGroup
//            printMap(map, "----------------------------");
            int ret = 0;
            PriorityQueue<Status> candidate = new PriorityQueue<>();
            isVisited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > 0 && !isVisited[i][j]) {
                        Status group = new Status(i, j);
                        int VALUE = map[i][j];
                        Queue<Point> queue = new LinkedList<>();
                        isVisited[i][j] = true;
                        queue.add(new Point(i, j));
                        while (!queue.isEmpty()) {
                            Point cur = queue.poll();
                            group.addBlock(cur, map[cur.x][cur.y]);
                            for (int d = 0; d < 4; d++) {
                                int nx = cur.x + dir[d][0];
                                int ny = cur.y + dir[d][1];
                                if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1) continue;
                                if (isVisited[nx][ny] || map[nx][ny] == BLACK || map[nx][ny] == EMPTY) continue;
                                if (map[nx][ny] == RAINBOW || map[nx][ny] == VALUE) {
                                    isVisited[nx][ny] = true;
                                    queue.add(new Point(nx, ny));
                                }
                            }
                        }
                        for (Point p : group.rainBows) {
                            isVisited[p.x][p.y] = false;
                        }
                        if (group.isValidGroup())
                            candidate.add(group);
                    }
                }
            }
            if (!candidate.isEmpty()) {
                Status s = candidate.poll();
                for (Point p : s.rainBows) {
                    map[p.x][p.y] = EMPTY;
                }
                for (Point p : s.blocks) {
                    map[p.x][p.y] = EMPTY;
                }
                ret = s.getSize();
            }
            else break;
            //printMap(map, "AFTER REMOVE counting : " + ret);
      /*  System.out.println(" ---- --- -- - - - - -");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j]==EMPTY?"- ":map[i][j] + " ");
            }
            System.out.println();
        }*/
            int[][] tmp = new int[N][N];
            tmp = fall(map);
            //System.out.println("FALL");
        //    printMap(tmp, "AFTER 1st FALL");
            tmp = rotate(tmp);
          //  printMap(tmp, "AFTER ROTATE");
            map = fall(tmp);
            //printMap(map, "AFTER 2nd FALL");

            ans += ret*ret;
        }
    }

    private static void printMap(int[][] tmp, String prompt) {
        System.out.println(prompt);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tmp[i][j] == EMPTY) {
                    sb.append("-");
                } else if (tmp[i][j] == BLACK) {
                    sb.append("ㅁ");
                }else
                    sb.append(tmp[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] rotate(int[][] src) {
        int[][] dest = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dest[i][j] = src[j][(N-1)-i];
            }
        }
        return dest;
    }

    private static int[][] fall(int[][] map) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(tmp[i], EMPTY);
        }
        for (int i = 0; i < N; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < N; j++) {
                if (map[j][i] != EMPTY) {
                    if (map[j][i] == BLACK) {
                        tmp[j][i] =BLACK;
                        int pos = j-1;
                        while (!stack.isEmpty()) {
                            tmp[pos--][i] = stack.pop();
                        }
                    }else{
                        stack.push(map[j][i]);
                    }
                }
            }
            int pos = N-1;
            while (!stack.isEmpty()) {
                tmp[pos--][i] = stack.pop();
            }
        }
        return tmp;
    }


    static class Status implements Comparable<Status>{
        int x,y;  //기준점.
        List<Point> blocks;
        List<Point> rainBows;

        public Status(int x, int y) {
            this.x = x;
            this.y = y;
            blocks = new ArrayList<>();
            rainBows = new ArrayList<>();
        }

        public boolean isValidGroup(){
            return blocks.size() > 0 && getSize() >= 2;
        }
        public void addBlock(Point cur, int value) {
            if (value == RAINBOW) rainBows.add(cur);
            else blocks.add(cur);
        }
        public int getSize() {
            return blocks.size() + rainBows.size();
        }

        /*- 그러한 그룹이 여러개라면, 무지개 블록의 수가 가장 많은 블록그룹
 *    - 기준 블록의 행이 가장 큰것, 열이 가장 큰것*/
        @Override
        public int compareTo(Status o) {
            int oSize = o.getSize();
            int size = this.getSize();
            int oRainbowCnt = o.rainBows.size();
            int rainbowCnt = this.rainBows.size();
            if(oSize== size){
            if (oRainbowCnt == rainbowCnt) {
                if (o.x == this.x) {
                    return o.y - this.y;
                }
                return o.x - this.x;
            }
            return oRainbowCnt-rainbowCnt;
            }
            return oSize - size;
        }
    }
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
