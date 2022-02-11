package SAMSUNG.SWExpert._05_Graph;

public class UserSolution {
    // (1,10) (2,10) --> (10,1) (10,2)로 변환하여야함.
    static int N;
    static int maps[][];
    static int visited[][];
    static int[][] dir = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    static Point[] queue;
    static int front;
    static int rear;
    void bfs_init(int map_size, int map[][]) {
        maps = map;
        N = map_size;
    }

    int bfs(int x1, int y1, int x2, int y2) {
        visited = new int[N+1][N+1];
        queue = new Point[N*N];
        front =-1;
        rear = 0;
        Point s = new Point(y1-1,x1-1);
        Point e = new Point(y2-1,x2-1);
        queue[rear++] = s;
        visited[y1-1][x1-1] = 1;
        while(front <rear){
            Point cur = queue[++front];
            if(front == rear) break;
            if(cur.x == e.x && cur.y == e.y) break;
            for(int i =0;i<4;i++){
                int nx= cur.x + dir[i][0];
                int ny = cur.y + dir[i][1];
                if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
                if(maps[nx][ny] == 1) continue;
                if(visited[nx][ny] != 0) continue;
                visited[nx][ny] = visited[cur.x][cur.y] +1;
                queue[rear++] = new Point(nx,ny);
            }
        }
        return visited[e.x][e.y]==0?-1:visited[e.x][e.y]-1;

    }
    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
