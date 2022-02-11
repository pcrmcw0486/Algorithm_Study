package SAMSUNG.SWExpert._05_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
R*C 칸
지뢰있는 칸이면 게임 끝남.
지뢰가 없는 칸이라면 꼭지점이 맞닿아 있는 최대 8칸에 대해 몇개의 지뢰가 있는지 클릭한 칸에 표시된다.
'만약 이숫자가 0이라면 그 8방향에 지뢰가 없으므로 그 8칸의 방향의 칸도 자동으로 숫자를 표시'
지뢰 * 없는칸을'.'

지뢰가 있는 칸을 제외한 모든 칸의 숫자가 표시되려면 최소 몇번의 클릭을 해야하는가?
어차피 각 칸마다 8방향씩 돌면서 개수를 알아야함.
미리구하든, 들어가서 구하든.
미리 구해놓고 지뢰개수가 적은 것 부터
어차피 0인 것들끼리는 다 연결이 되어 있다면 하나만 눌러도 다됨.
그렇기 때문에 0인거 먼저 눌러도 된다.
* */
public class No_2_PoppingPopping {
    static int[][] checkDir = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}}; //8방향
    static boolean[][] visited;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append('#').append(test_case).append(' ');
            //data받으면서 -1이라면 +1해주자.
            //다 받고 난다음에 queue에 넣어야한다.
            int N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            int bombCnt;
            ArrayList<Block> bombs = new ArrayList<Block>();
            for(int i =0;i<N;i++){
                String line = br.readLine();
                for(int j= 0;j<N;j++){
                   if(line.charAt(j) == '*'){
                       map[i][j] = -1;
                       visited[i][j] = true;
                       bombs.add(new Block(i,j));
                   }
                }
            }

            int totalCnt = N*N;
            bombCnt = bombs.size();
            for(Block b : bombs){
                for(int i =0;i<8;i++){
                    int nx = b.x + checkDir[i][0];
                    int ny = b.y + checkDir[i][1];
                    if(nx<0 || ny <0 || nx > N-1 || ny > N-1) continue;
                    if(map[nx][ny] == -1) continue;
                    map[nx][ny]++;
                }
            }

            //PriorityQueue<Block> pq = new PriorityQueue<Block>();
            int zeroCnt =0 ;
            int clicked = 0;
            for(int i =0;i<N;i++){
                for(int j =0;j<N;j++){
                    if(map[i][j] == 0){
                        //여기서 bfs돌리면서
                        if(visited[i][j]) continue;
                       // zeroCnt++;
                       // clicked++;
                        Queue<Block> queue = new LinkedList<Block>();
                        queue.add(new Block(i,j));
                        visited[i][j] = true;
                        while(!queue.isEmpty()){
                            Block cur = queue.poll();
                            for(int d =0;d<8;d++){
                                int nx = cur.x + checkDir[d][0];
                                int ny = cur.y + checkDir[d][1];
                                if(nx<0 || ny < 0 || nx > N-1 || ny > N-1) continue;
                                if(visited[nx][ny]) continue;
                                visited[nx][ny] = true;
                                clicked++;
                                if(map[nx][ny] == 0){
                                    queue.add(new Block(nx,ny));
                                }
                            }
                        }
                    }
                }
            }

            //totalClick count = 0클릭 count + (TotalCnt - bomb개수 - 0누르면서 없어진 count)

            //int clickCnt = zeroCnt + (totalCnt - bombCnt - clicked);
            int clickCnt = totalCnt - bombCnt - clicked;

            // zerocnt 1
            //total cnt = 9
            //bombCnt = 4
            //clicked = 4
            sb.append(clickCnt).append('\n');
        }
        System.out.print(sb.toString());
    }
    public static class Block{
        int x, y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
