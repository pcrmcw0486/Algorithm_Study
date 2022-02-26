package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Test_16946 {
    static int[][] dir = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    static int[][] map;
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Integer> groupInfo = new HashMap<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i =0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<M;j++){
               map[i][j] = line.charAt(j)-'0';
            }
        }
        int groupId = 2;
        int ans =0;
        for(int i =0;i<N;i++){
            for(int j=0;j<M;j++){
                //벽 만남.
                ans =1;
                if(map[i][j] == 1){
                    HashSet<Integer> groupSet = new HashSet<>();
                    for(int d=0;d<4;d++){
                        int nx= i + dir[d][0];
                        int ny = j + dir[d][1];
                        if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
                        int v = map[nx][ny];
                        if(v == 1) continue;
                        if(v == 0){
                            int cnt = bfs(nx, ny, groupId);
                            groupInfo.put(groupId, cnt);
                            groupSet.add(groupId);
                            groupId++;
                            ans += cnt;
                        }else{
                            if(!groupSet.contains(v)){
                                ans += groupInfo.get(v);
                                groupSet.add(v);
                            }
                        }
                    }
                    sb.append(ans%10);
                }else
                    sb.append(0);
            }
            sb.append('\n');

        }
        System.out.println(sb);
    }

    public static int bfs(int x, int y , int id){
        int cnt =0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        map[x][y] = id;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            cnt++;
            for(int i =0;i<4;i++){
                int nx= cur.x + dir[i][0];
                int ny = cur.y + dir[i][1];
                if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
                if(map[nx][ny] ==0){
                    map[nx][ny] = id;
                    queue.add(new Point(nx, ny));
                }
            }
        }

        return cnt;
    }
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
