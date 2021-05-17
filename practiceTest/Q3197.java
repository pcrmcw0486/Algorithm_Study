/*
 백조의 호수 Gold I
 *** 접근 방향 ***
 치즈랑 비슷한 느낌이다.
 쉽게 생각하면 
 >  1. 백조 길 찾기
    2. 빙판 녹이기(일자 증가)
를 반복하면 되는데 과연 시간과 메모리가 가능한가.
라고 생각해서 내생각은 다음과 같음
 1. 백조 길찾기 
    1.1 백조가 현재 갈 수 있는 가장 먼 곳을
        저장해둔다.(길 찾으면서 빙판 만날 시)
        따로 저장을 해두고 다음에 사용.
        이후 빙판 녹고 그 시작지점에서 다시 찾을 때
        다른 백조를 찾으면 종료.
        구분이 필요함.
        0 아직 안찾음 1 탐색중 2 탐색 종료 및 빙판에 부딪힘.
2. 빙판 녹이기
    물 찾아서 빙판을 한칸씩 녹이는데, 
    백조에서 먼저 탐색하므로 백조 쪽 호수의 경계는모두
    2로 되어 있을 것. 하여 1로 탐색한다.
여기서 문제는 Queue를 두개 쓸 것인가 한개쓸것인가하는 것인데.
백조 길찾기용 하나, 빙판 없애기용 하나로 하느냐
그냥 하나로 해서 PQ로 해서 정렬해가며 바로 할거냐.
PQ하나로 한다 치면 정렬하는데 꽤나 시간이 많이 걸릴거같은데.
 1차 > 두개로 시도.
 라고 생각했지만 잠시. 백조는 두개.이니까
 물이 2와 2가 만나거나 2와 L이 만나면 끝이네.

 1. 백조에서 먼저 탐색하여 바운더리 찾기 (모두 2)
 2. 나머지 물 없애기 근데 1과 2가 만나면?


 2. 다른 방법
 bfs로 모든 물에 일자를 탐색한다.
 하지만 한번만 모두 탐색하면 된다.
 맵 한번 탐색, 백조 길이 탐색.
 이후 백조가장빠른길 찾아가기.
 MAX값.
 근데 이건 다 봐야한다는 점이 있음.

 1차 시도 2번 방법으로 해보겠음.
 *** 알고리즘 자료구조 스킬 ***
 *** 문제 조건 ***
 time : 1sec
 memory : 256MB
 R, C 1<= <=1500 (최대 크기 2_250_000)
 . 물, X 빙판, L 백조
 물과 접촉한 빙판은 1초 후 녹는다.
 백조는 물로만 연결될 수 있을 때
 며칠이 지나야 백조들이 만날 수 있는가.
 */
package practiceTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3197 {
    static int C;
    static int R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        Queue<int[]> water = new LinkedList<>(); // 물 범위 확장.
        Queue<int[]> swanTerritory = new LinkedList<>(); // 0 > num; 주변 모든 물을 백조 영역으로 확장.
        Queue<int[]> route = new LinkedList<>(); // num경계 증가, 1이면 한칸, 0이면 모두 num으로 하기 위해 territory에 삽입
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };
        // 1물, 0빙판 백조1 > 2, 백조1 영역 >3 백조2 > 4 백조2영역 >5
        // 양수는 일자 및 탐색한 것.
        int day = 2;
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                switch (line.charAt(j)) {
                    case '.':
                        map[i][j] = 1;
                        water.offer(new int[] { i, j });
                        break;
                    case 'X':
                        map[i][j] = 0;
                        break;
                    case 'L':
                        map[i][j] = day;
                        swanTerritory.offer(new int[] { i, j, day + 1 });
                        day += 2;
                        break;
                }
            }
        }
     

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(String.format("%2d", Integer.valueOf(map[i][j])));
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static boolean isRange(int nx, int ny) {
        return (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1);
    }
}
