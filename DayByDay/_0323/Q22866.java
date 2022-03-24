package DayByDay._0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q22866
 * @문제이름 : 탑 보기
 * @난이도 : Gold IV
 * @date : 2022-03-23 오후 5:39
 * @author : pcrmcw0486
 * @문제이해
 * N개가 존재한다. 각 건물 옥상에서 양 옆에 존재하는 건물의 옆을 몇 개 볼 수 있을까
 * i기준 i-1 i-2 왼쪽 i+1 i+2는 오른쪽
 * 건물 높이 L이라면 높이가 L보다 큰 곳의 건물만 볼 수 있는데,,
 * L건물 뒤에 높이 L이하면 안보인다.
 * 각 건물에서 볼 수 있는 건물들이 어떤 것이 있을까?
 *
 * @알고리즘
 * 스택
 * @접근방법

*/
public class Q22866 {
    static private class Building{
        int idx, height;
        public Building(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Building> stack = new Stack<>();
        int[] size = new int[N + 1];
        int[] neighbor = new int[N + 1];
        Building[] buildings = new Building[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = new Building(i + 1, Integer.parseInt(st.nextToken()));
        }
        Arrays.fill(neighbor, 222222);
        //왼쪽을 바라볼 떄
        for (int i = 0; i < N; i++) {
            findBuildings(stack, size, neighbor, buildings, i);
        }
        stack.clear();

        for (int i = N-1; i >=0; i--) {
            findBuildings(stack, size, neighbor, buildings, i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N+1; i++) {
            sb.append(size[i]).append(" ");
            if (size[i] != 0) {
                sb.append(neighbor[i]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void findBuildings(Stack<Building> stack, int[] size, int[] neighbor, Building[] buildings, int i) {
        Building b = buildings[i];
        while (!stack.isEmpty()) {
            if (stack.peek().height <= b.height) {
                stack.pop();
            }else
                break;
        }
        size[b.idx] += stack.size();
        if(!stack.isEmpty()){
            int cur =Math.abs(stack.peek().idx -b.idx);
            int prev = Math.abs(neighbor[b.idx]-b.idx);
            if( cur < prev ){
                neighbor[b.idx] = stack.peek().idx;
            } else if (prev == cur) {
                neighbor[b.idx] = Math.min(stack.peek().idx, neighbor[b.idx]);
            }
        }
        stack.push(b);
    }
}
