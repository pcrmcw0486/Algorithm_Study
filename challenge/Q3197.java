/*
 백조의 호수 Gold I //다시 푼다.
  1. 일반적인 bfs 두번
  2. using binary search
  3. using disjoint-set
fail 이유 : 메모리초과 >> 오답.
내 생각 : visit을 따로 구현하지 않고, 번호를 통해 visit을 체크하려고했는데
잘 안된 것 같다. 이후에 다시 풀어보도록 한다.
이게 나는 각각에 번호를 부여해서 풀려고 했는데,
그 방식을 사용하게되면 물(0)과 백조(2 || 3)이 만났을 때 
물에 대해 다시 한번 3으로 변환하는 과정에서 시간이 꽤 걸리는 것 같다.
아 ! 그냥 물이면 이동가능하다. 벽이면 다음에 탐색 가능한지 보도록 next에 넣어둔다.
이렇게 생각하면 쉽긴 하네.이게 1번. 이 생각이면 물이면 이동 가능하다고 한다면,
백조가 탐색한 부분만 visit을 처리해주어야함.

2번은 물에서부터 bfs돌려서 거리를 다 구한다음, 최소 거리를 찾기 위해
0~MAX day 까지 이분탐색을 bfs로 돌려서 찾아내는 방식.

3번은 각각의 좌표를 한 덩어리인가로 판단하기 위해 disjoint-set을 통해
map[i][j]라면 i * R + j와 같은 식으로 disjoint-set을 이용.

이거 되면 내가 생각한대로 해본다.
visit을 따로 쓰지 않고 번호로 판단하는 아.. 번호로 판단하게되면
물을 번호로 바꾸는 과정에서 시간이 많이 걸리는 구나. 아니 근데
그건 이방법도 똑같음.물에서 탐색하니까. > 이건 한쪽에서 진행.
1 > 0 으로 바뀌는 시점에서 옆이 0이면 넣고 쭉 탐색하는데,
1 > 3으로 바뀌는 시점에서 옆이 0이면 넣고 탐색하는 것, 뭐가 다른가.
하고 해본다. 생각해보자.끝까지 간다.
 */
package challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3197 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] map = new int[R][C];
        boolean[][] visit = new boolean[R][C];
        int[][] swan = new int[2][2];
        Queue<int[]> waterQ = new LinkedList<>();
        Queue<int[]> swanTerriotry = new LinkedList<>();
        int day = 0;
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                switch (line.charAt(j)) {
                    case '.':
                        map[i][j] = 0;
                        waterQ.offer(new int[] { i, j });
                        break;
                    case 'X':
                        map[i][j] = 1;
                        break;
                    case 'L':
                        map[i][j] = 0;
                        swan[day][0] = i;
                        swan[day][1] = j;
                        waterQ.offer(new int[] { i, j });
                        day++;
                        break;
                }
            }
        }

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };
        day = 0;
        swanTerriotry.offer(new int[] { swan[0][0], swan[0][1] });
        visit[swan[0][0]][swan[0][1]] = true;
        boolean success = false;
        while (!success) {
            Queue<int[]> nextQ = new LinkedList<>();
            while (!swanTerriotry.isEmpty()) {
                int[] point = swanTerriotry.poll();
                if (point[0] == swan[1][0] && point[1] == swan[1][1]) {
                    success = true;
                    break;
                }
                for (int i = 0; i < dx.length; i++) {
                    int nx = point[0] + dx[i];
                    int ny = point[1] + dy[i];
                    if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1 || visit[nx][ny])
                        continue;
                    visit[nx][ny] = true;
                    if (map[nx][ny] == 1) {
                        nextQ.offer(new int[] { nx, ny });
                        continue;
                    }
                    swanTerriotry.offer(new int[] { nx, ny });
                }
            }
            if (success)
                break;
            swanTerriotry = nextQ;

            int size = waterQ.size();
            for (int i = 0; i < size; i++) {
                int[] point = waterQ.poll();
                for (int j = 0; j < dx.length; j++) {
                    int nx = point[0] + dx[j];
                    int ny = point[1] + dy[j];
                    if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1)
                        continue;
                    if (map[nx][ny] == 1) {
                        map[nx][ny] = 0;
                        waterQ.offer(new int[] { nx, ny });
                    }
                }
            }
            day++;
        }
        System.out.println(day);

    }
}
