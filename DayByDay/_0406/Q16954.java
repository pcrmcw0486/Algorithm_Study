package DayByDay._0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @문제번호 : Q16954
 * @문제이름 : 움직이는 미로 탈출
 * @난이도 : Gold IV
 * @date : 2022-04-06 오후 3:30
 * @author : pcrmcw0486
 * @문제이해
 * 체스판 탈출을 해보자.
 * 빈칸 또는 벽이다.
 * 캐릭터 가장 왼쪽 아래 -> 가장 오른쪽 위 이동
 * 게임의 특징은 벽이 움직인다.
 * 1초마다 모든 벽이 아래에 있는 행으로 한칸씩 내려가고,
 * 가장 아래에 있어서 아래 행이 없다면 벽이 사라진다.
 * 캐릭터는 1초에 인접한 한칸 또는 대각선 방향으로 이동, 또는 현재 위치 서있을 수 있다.
 * 이동시에는 빈칸으로만 이동가능하다.
 * 욱제의 캐릭터가 이동하고 -> 벽이 이동한다.
 * 벽이 캐릭터가 있는 칸으로 이동하면 더 이상 캐릭터는 이동할 수 없다.
 * 이동할 수 있는가 없는가.
 *
 * 벽을 공유하고 있는데..
 * @알고리즘

 * @접근방법

*/
public class Q16954 {

    static int[][] moveDir = new int[][]{
            {-1, 0}, {0, 1}, {1, 0}, {0, -1},
            {0, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };
    //같은 시간에 같은 곳에 존재하지만 않도록 하면된다.
    static final int SIZE =8;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] map = new char[SIZE][SIZE];
        boolean[][] isVisited = new boolean[SIZE][SIZE];
        boolean isSuccess = false;
        for (int i = 0; i < 8; i++) map[i] = br.readLine().toCharArray();
        System.out.println(findPossibleWay(map)?1:0);
    }

    private static boolean findPossibleWay(char[][] map) {
        Queue<Point> characterPos = new LinkedList<>();
        Point character = new Point(7, 0);
        characterPos.add(character);
        while (!characterPos.isEmpty()) {
            boolean[][] isVisited = new boolean[SIZE][SIZE];
            int caseNum = characterPos.size();
            while (caseNum-- > 0) {
                Point currentPos = characterPos.poll();
                if (currentPos.x == 0 && currentPos.y == SIZE - 1) {
                    return true;
                }
                if(map[currentPos.x][currentPos.y] == '#') continue;
                for (int[] direction : moveDir) {
                    int nx = currentPos.x + direction[0];
                    int ny = currentPos.y + direction[1];
                    if(nx<0||ny<0||nx>SIZE-1||ny>SIZE-1) continue;
                    if(isVisited[nx][ny] || map[nx][ny]=='#') continue;
                    isVisited[nx][ny] = true;
                    characterPos.add(new Point(nx, ny));
                }
            }
            //벽이동
            for (int i = SIZE-1; i >0; i--) {
                System.arraycopy(map[i-1],0, map[i],0,SIZE);
            }
            Arrays.fill(map[0],'.');
        }
        return false;
    }

    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
