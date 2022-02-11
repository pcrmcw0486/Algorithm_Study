package SAMSUNG.SWExpert._05_Graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No_1_Processor {
    static int maxCnt;
    static ArrayList<Point> processor;
    static boolean[][] map;
    static int N;
    static int size;
    static int minDist;
    static int[][] dir = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append('#').append(test_case).append(' ');
            N = Integer.parseInt(br.readLine());
            map = new boolean[N][N];
            processor = new ArrayList<Point>();
            maxCnt = 0;
            minDist = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    if (st.nextToken().equals("1")) {
                        map[i][j] = true;
                        processor.add(new Point(i, j));
                    }
                }
            }
            size = processor.size();
            dfs(0,0,0);
            sb.append(minDist).append('\n');
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int idx, int cnt, int dist) {
        if(cnt >= maxCnt){
            if(cnt > maxCnt) {//count가 최대로 큰게 되어야함. 그래서 초기화시켜주어야함.
                maxCnt = cnt;
                minDist = dist;
            }
            else
                minDist = Math.min(minDist, dist);
        }
        if (idx == size) {
            return;
        }
        Point cur = processor.get(idx);
        // 가장자리 확인
        if(cur.x == 0 || cur.y == 0 || cur.x == N-1 || cur.y == N-1) dfs(idx+1, cnt+1, dist);
        else{
            //4방 확인

            for(int i =0;i<4;i++){
                //정해진 방향 끝까지
                int curx = cur.x;
                int cury = cur.y;
                int distCount =0;
                boolean isPossible = true;
                while(true){
                    int nx = curx + dir[i][0];
                    int ny = cury + dir[i][1];
                    if(nx<0 || ny < 0 || nx>N-1||ny>N-1) break;
                    if(map[nx][ny]){
                        isPossible = false;
                        break;
                    }
                    distCount++;
                    map[nx][ny] = true;
                    curx = nx;
                    cury = ny;
                }
                if(isPossible){
                  //  System.out.println((idx+1)+"번째 " + "방향 : " + i + "  count : " + distCount);
                    dfs(idx+1, cnt+1, dist+distCount);
                }
                curx = cur.x;
                cury = cur.y;
                //false만들기
                while(distCount-- >0){
                    int nx = curx + dir[i][0];
                    int ny = cury + dir[i][1];
                    map[nx][ny] = false;
                    curx = nx;
                    cury = ny;
                }
                //유의하자...
            }
            //넣지 않음.
            dfs(idx+1,cnt,dist);
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
