package DayByDay._0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q9079
 * @문제이름 : 동전게임
 * @난이도 : Silver IV
 * @date : 2022-03-24 오후 1:45
 * @author : pcrmcw0486
 * @문제이해
 * 동전을 모두 같은면이 보이도록 하고 싶다. (한 행의 모든 동전)(한열의 모든 동전)(하나의 대각선 모든 동전)
 * 최소 횟수의 연산으로 실행하고 싶다.
 * 모두 같은면으로 만드는 것이 불가능한 모양이 있다는 것도 알았다.
 *
 * @알고리즘
 * 완전탐색
 * @접근방법

*/
public class Q9079 {
    static class Table{
        int l1, l2, l3;

        public Table(int l1, int l2, int l3) {
            this.l1 = l1; this.l2 = l2; this.l3 = l3;
        }
    }

    static int[][] move = new int[][]{{4, 4, 4}, {2, 2, 2,}, {1, 1, 1,}, {4, 2, 1}, {1, 2, 4}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(T-- >0){
            int ans = -1;
            int[] data = new int[4];
            boolean[][][] isVisited = new boolean[8][8][8];
            for (int i = 0; i < 3; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    if(st.nextToken().equals("H"))
                        data[i] |= 1<<(2-j);
                }
            }
            Queue<int[]> queue = new LinkedList<>();
            isVisited[data[0]][data[1]][data[2]] = true;
            queue.add(data);
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int l1 = cur[0], l2= cur[1], l3 = cur[2];
                int d = cur[3];
                int nl1, nl2, nl3;
                if((l1==0&&l2==0&&l3==0) || (l1==7&&l2==7&&l3==7)){
                    ans = d;
                    break;
                }
                //행 뒤집기
                nl1 = l1^7;
                if(!isVisited[nl1][l2][l3]){
                    isVisited[nl1][l2][l3] = true;
                    queue.add(new int[]{nl1, l2, l3, d+1});
                }
                nl2 = l2^7;
                if(!isVisited[l1][nl2][l3]){
                    isVisited[l1][nl2][l3] = true;
                    queue.add(new int[]{l1, nl2, l3,d+1});
                }
                nl3 = l3^7;
                if(!isVisited[l1][l2][nl3]) {
                    isVisited[l1][l2][nl3]=true;
                    queue.add(new int[]{l1, l2, nl3,d+1});
                }
                //열 및 대각 뒤집기
                for (int i = 0; i < 5; i++) {
                    nl1 = l1^move[i][0];
                    nl2 = l2^move[i][1];
                    nl3 = l3^move[i][2];
                    if(!isVisited[nl1][nl2][nl3]){
                        isVisited[nl1][nl2][nl3] = true;
                        queue.add(new int[]{nl1, nl2, nl3, d + 1});
                    }
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }
}
