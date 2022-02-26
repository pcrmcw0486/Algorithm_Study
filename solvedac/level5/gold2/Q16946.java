package solvedac.level5.gold2;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @문제번호 : Q17143
 * @문제이름 : 벽부수고 이동하기 4
 * @난이도 : Gold II
 * @date : 2022-02-19 오후 4:31
 * @author : pcrmcw0486
 * @문제이해
 * N*M 행렬에서 각각의 벽에 대해
 * 벽을 부수고 이동할 수 있는 곳으로 변경해보았을 때 그 위치에서
 * 이동할 수 있는 칸의 개수를 출력한다 %10해서
 * @알고리즘
 * 그래프탐색
 * @접근방법
 * 모든 벽들에 대해 탐색하기에는
 * 벽이 있을 수 있는 개수 NM개  각각에 대해 최악으로 다 돌아보아야한다.
 * NM 이 10^6
 * NM/2*4 = 2NM 그냥 다 봐도 될거같긴한데.
 * 근데 기본적으로 세어본것을 또 세어본다는 것 자체가 낭비임.
 * 빈 공간들에 대해 중복적으로 검사를 하게 되는데 이 부분이 매우 비효율적이라고 생각함.
 * 내 아이디어는 다음과 같음
 *  - 1. 빈 공간들에 대해 grouping 하고 ID부여
 *  - 2. 각 벽들은 현재 위치에서 중복되지 않는 ID들에 대해 값을 더해주고
 *      자신의 위치 +1을 해줌.
 * - O(NM) + 벽개수*4

 */
public class Q16946 {
    static int[][] dir = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws IOException {
        Queue<Point> walls = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for(int i =0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<M;j++){
                if(line.charAt(j) == '0')
                    map[i][j] = 1;
                else{
                    map[i][j] = 0;
                    walls.add(new Point(i, j));
                }
            }
        }
        HashMap<Integer, Integer> groupInfo = new HashMap<>();
        Queue<Point> group;
        int groupId = 2;
        for(int i =0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == 1){
                    int cnt =0;
                    group = new LinkedList<>();
                    map[i][j] = groupId;
                    group.add(new Point(i,j));
                    while (!group.isEmpty()) {
                        Point cur = group.poll();
                        cnt++;
                        for(int d =0;d<4;d++){
                            int nx = cur.x + dir[d][0];
                            int ny = cur.y + dir[d][1];
                            if(nx<0 || nx>N-1||ny<0 || ny>M-1) continue;
                            if(map[nx][ny] == 0) continue;
                            if(map[nx][ny] ==1) {
                                map[nx][ny] = groupId;
                                group.add(new Point(nx, ny));
                            }
                        }
                    }
                    groupInfo.put(groupId, cnt);
                    groupId++;
                }
            }
        }
        int[][] ans = new int[N][M];
        while(!walls.isEmpty()){
            Point cur = walls.poll();
            Set<Integer> checkGroup = new HashSet<>();
            int cnt =0;
            for(int i =0;i<4;i++){
                int nx = cur.x + dir[i][0];
                int ny = cur.y + dir[i][1];
                if(nx<0 || nx>N-1||ny<0 || ny>M-1) continue;
                if(map[nx][ny] == 0) continue;
                if(!checkGroup.contains(map[nx][ny])){
                    cnt += groupInfo.get(map[nx][ny]);
                    checkGroup.add(map[nx][ny]);
                }
            }
            ans[cur.x][cur.y] = (cnt+1)%10;
        }
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<N;i++){
            for(int j=0;j<M;j++){
                sb.append(ans[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
