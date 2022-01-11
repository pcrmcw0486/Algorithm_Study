package solvedac.level4.gold5;
/*
2021.12.19
문제번호 : Q15686 
이름 및 난이도 : 치킨 배달 Gold V
문제이해 
-----------------
N*N 도시. 빈칸, 치킨집, 집 
r,c 는 1부터 시작
"치킨거리" >> 집과 가장 가까운 치킨집 사이의 거리.
"도시의 치킨거리" >> 모든 집의 치킨 거리 합
|r1-r2| + |c1-c2|
0 빈칸 1 집 2 치킨집
최대 M개 (수익을 많이 낼 수 있는)
치킨 거리가 가장 작게 .

접근 방법 :
모든 집으로부터 치킨 집까지의 거리를 다 더하는 치킨거리가 총합이 된다.
한 집에서 가장 가깝다고 해서 다른 집도 가까운 상황이 아님.
할 수 있는 방법은 여러가지가 있을 것 같다.
 1. 치킨집 마다 치킨거리를 다 구해서 sorting시킨다음.
    가장 긴 치킨거리를 가지는 치킨 집 부터 제거하여 M개를 만드는 방법.(X)
    잘못된 생각임.
 2. 치킨집 개수가 최대 13개 이고 13에서 가장 큰 조합의 개수는
    13C6 = 1716
    조합마다 거리를 구하는 방법.
두가지 방법으로 접근이 가능하다. 각각의 시간 복잡도를 계산해보자.
 1. 치킨집 최대 13개 기준으로 BFS를 하는데 도시크기 최대 50(2500)
    O(MlogM) + O(M*N^2)
    >> ㄴㄴ 달라짐. 집 마다 치킨 집의 우선순위가 달라짐.
 2. 조합 마다 하는 방법?
  O(M*N^2) * 1716  
    > 조합마다 해보는 방법은 같은 치킨집에서의 거리를 또 구한다는 문제점이 있다.
    

    그래프라고 모두 BFS는 아니다... 
    다시한번 풀어보고 싶네.
제한 조건 : 
N 50 M 13 치킨집 개수 <= Min(M,13)

## 중복 허용 X 오름차순 
## 참조 https://www.acmicpc.net/source/32167915
*/

import java.io.*;
import java.util.*;

public class Q15686 {
    static int N;
    static int M;
    static ArrayList<Point> house;
    static ArrayList<Point> chicken;
    static boolean[] possible;
    static int ans;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        house = new ArrayList<Point>();
        chicken = new ArrayList<Point>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int option = Integer.parseInt(st.nextToken());
                if (option == 1) {
                    house.add(new Point(i, j));
                } else if (option == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }
        possible = new boolean[chicken.size()];
    }

    public static void solution() {
        ans = Integer.MAX_VALUE;
        // M개를 골랐을 때
        selectChickenHouse(0, 0);
        System.out.println(ans);
    }

    public static void selectChickenHouse(int depth, int idx) {
        if (depth == M) {
            int cityDist = 0;
            for (Point h : house) {
                int chickenDist = Integer.MAX_VALUE;
                for (int i = 0; i < chicken.size(); i++) {
                    if (!possible[i])
                        continue;
                    Point c = chicken.get(i);
                    chickenDist = Math.min(chickenDist, Math.abs(h.x - c.x) + Math.abs(h.y - c.y));
                }
                cityDist += chickenDist;
            }
            ans = Math.min(ans, cityDist);
            return;
        }
        if (chicken.size() - idx < M - depth)
            return;
        for (int i = idx; i < chicken.size(); i++) {
            if (!possible[i]) {
                possible[i] = true;
                selectChickenHouse(depth + 1, i + 1);
                possible[i] = false;
            }
        }

    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
