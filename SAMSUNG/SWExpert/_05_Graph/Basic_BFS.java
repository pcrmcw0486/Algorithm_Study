package SAMSUNG.SWExpert._05_Graph;

import java.util.Queue;

public class Basic_BFS {
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
        visited = new int[map_size+1][map_size+1];
        N = map_size;
        queue = new Point[N*N];
        front =0;
        rear = 0;
    }

    int bfs(int x1, int y1, int x2, int y2) {
        Point s = new Point(y1-1,x1-1);
        Point e = new Point(y2-1,x2-1);
        queue[rear++] = s;
        visited[y1][x1] = 1;
        while(front <=rear){
            Point cur = queue[front++];
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

        return visited[e.x][e.y];
    }
    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
