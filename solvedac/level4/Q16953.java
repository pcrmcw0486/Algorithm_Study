package solvedac.level4;
/*
2021.12.09
문제번호 : Q16953
이름 및 난이도 : A -> B Silver I
문제이해 
-----------------
A에서 B로 바꾸는데 연산은 
 - 2를 곱하거나
 - 1을 수의 가장 오른쪽에 더하거나
 A->B 에 필요한 연산의 최솟값을 구하시오

접근 방법 :
    상태의 범위가 int형 1~10^9 인 상태공간에서 각 step에 맞게 추적한다.
    이 때 A->B로 가는 방향성이 커지기만 하기 때문에
    *2 또는 1을 오른쪽에 추가한 것 모두 B보다 커진다면 더이상
    볼 필요가 없어진다.
    정수 범위에 유의하여 작성하면 된다.
     
    + 방문 여부 체크
    2*A = 10*A +1 이 가능한 경우가 없다. 그냥 하면 될 것 같다.가 아니라.
    2*X = 2(10*B +1)  를 만족하는 경우가 있는가? 
    없는 것 같은데. 증명을 못하겟네..
    그냥 Set쓰겠습니다.

제한 조건 : 
각 A와 B의 범위는 10^9이하이고 
연산 중에 *10을 하는 경우 또는 *2를 해서 넘어가는  int범위를 넘어가는 것을 유의한다.
만들 수 없는 경우에는 -1을 출력한다.
*/

import java.io.*;
import java.util.*;

public class Q16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        Set<Integer> visited = new HashSet<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(A);
        visited.add(A);
        int ans = -1;
        int step = 0;
        boolean success = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int cur = queue.poll();
                if (cur == B) {
                    success = true;
                    break;
                }
                if (cur <= Integer.MAX_VALUE / 2) {
                    int nxt = 2 * cur;
                    if (nxt > B)
                        continue;
                    if (visited.contains(nxt))
                        continue;
                    queue.add(nxt);
                    visited.add(nxt);
                }
                if (cur <= (Integer.MAX_VALUE / 10) - 1) {
                    int nxt = 10 * cur + 1;
                    if (nxt > B)
                        continue;
                    if (visited.contains(nxt))
                        continue;
                    queue.add(nxt);
                    visited.add(nxt);
                }
            }
            if (success) {
                ans = step + 1;
                break;
            }
            step++;
        }
        System.out.println(ans);
    }

    public static void solution(int target, int num) {
        int cnt = -1;
        while (target > num) {
            if (target % 2 == 0) {
                target = target / 2;
                cnt++;
            } else if (target % 10 == 1) {
                target = (target - 1) / 10;
                cnt++;
            } else {
                target = 0;
            }
            if (target < num) {
                cnt = -1;
            }
        }
        System.out.println(cnt);
    }
}
