package solvedac.level5.gold4;
/*
2022.01.11
문제번호 : Q1987
이름 및 난이도 : 알파벳 Gold IV
문제이해 
-----------------
세로 R칸, 가로 C칸 모양 보드. 
각 칸에 대문자 알파벳이 하나씩. 좌측(1행 1열) 말이 있다.
말은 상하좌우 인접한 네 칸 중 한칸 이동.
(새로 디오한 칸에는 지나온 모든 칸에 적혀있는 알파벳과 달라야한다.)(같은 알파벳 두번 X)
좌측 상단에서 말이 최대한 몇 칸을 지날 수 있는가?

제한 조건 : 
    R, C <= 20
접근 방법 :
    2^26의 상태. 2^26 = 67_108_864  >> 상태의 확인 bit
    back tracking 으로 가본다. with dp
    back tracking으로 가는 건 너무 많은 경우의 수와
    중복의 경우의 수가 있음.
     →  ←  ↑ ↓
     → →  로 도착한 경우와
     ↑ → → ↓ 로 도착한 칸 의 위치는 같지만 상태는 다르다.
     dp로 해결하는데 어려움이 있다.
     
     backtracking..다시 돌아가는 건 안됨.(중복 X)'
      1. bfs
        >> bfs로 하기에 상태공간의 정의가 [R][C][2^26] 이 되어야함.
     2. dfs
      >> dfs로 할 시에 중복의 관리는 bit계산을 통한 현재 상태를 통해 진행한다.
      >> depth가 깊어봐야 26이라고 생각함.
      >> 채택
       >> dfs에서 가장 중요한 visit배열에 대해 너무 간과했다.
       >> 그냥 case가 적겠지하고 넘겨서 체크해주지 않았더니 시간이 생각보다 많이 걸렸다.
       >> visit배열은 항상 체크해주도록 한다.
       >> 중복이 문제이다. 중복은 칸에 도착했다 로 보통 해결하지만 이 문제의 경우
       >> 도착했을 때의 상태가 갔다면 더이상 보지 않아도 된다.
       >> 가 맞은 판단.

       ++ bit가 아니라 boolean[26]으로도 가능하다.
       역시 backtracking promising 에 따라 달라진다 속도가.
    3. dp (X)
      dp는 작은 문제를 풀었을 때 큰 문제를 풀어야하나.
      → →  로 도착한 경우와
     ↑ → → ↓ 로 도착한 칸 의 위치는 같지만 상태는 다르다.
     dp로 해결하는데 어려움이 있다.
     
*/

import java.io.*;
import java.util.*;

public class Q1987 {
    static int cnt = 1;
    static int R, C;
    static char[][] map;
    static int[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int status = 1 << map[0][0] - 'a';
        visit = new int[R][C];
        dfs(0, 0, status, 1);
        System.out.println(cnt);
    }

    static int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void dfs(int r, int c, int status, int depth) {
        if (visit[r][c] == status)
            return;
        visit[r][c] = status;
        boolean isEnd = true;
        for (int i = 0; i < 4; i++) {
            int nx = dir[i][0] + r;
            int ny = dir[i][1] + c;
            if (nx < 0 || ny < 0 || nx > R - 1 || ny > C - 1)
                continue;
            if ((status & 1 << map[nx][ny] - 'a') > 0)
                continue;
            isEnd = false;
            dfs(nx, ny, status | 1 << map[nx][ny] - 'a', depth + 1);
        }
        if (isEnd) {
            cnt = Math.max(cnt, depth);
        }
    }
}
