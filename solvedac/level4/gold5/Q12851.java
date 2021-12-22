package solvedac.level4.gold5;
/*
2021.12.16
문제번호 : Q12851
이름 및 난이도 : 숨바꼭질 2 Gold V
문제이해 
-----------------
수빈이는 점 0<=N<=100_000
동생은 점 0<=K<=100_000   => 상태공간
X일때 1초후 X-1 또는 X+1로 이동하고 또는 2*X의 위치로 이동한다.
가장 빠른 시간이 몇 초후 + 방법이 몇가지인가.

뒤로가는 방법 한가지와 앞으로가는 방법 2가지.

접근 방법 :
상태공간에서 수빈이가 움직이는 것을 찾는다.
제한 조건 : 

*/

import java.io.*;
import java.util.*;

public class Q12851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int count = 0;
        int[] time = new int[100001];
        int minTime = Integer.MAX_VALUE;
        if (K <= N) {
            // 뒤로가는 방법은 하나뿐, 뒤에 있다면 계속 뒤로가야함.
            System.out.println((N - K) + "\n1");
        } else {
            Queue<Integer> queue = new LinkedList<Integer>();
            time[N] = 0;
            queue.add(N);
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (time[cur] > minTime) {
                    break;
                }
                if (cur == K) {
                    count++;
                    minTime = time[cur];
                    continue;
                }
                // 뒤로
                int nxt = cur - 1;
                if (nxt >= 0 && (time[nxt] == 0 || time[nxt] == time[cur] + 1)) {
                    time[nxt] = time[cur] + 1;
                    queue.add(nxt);
                }
                // 앞으로

                nxt = cur + 1;
                if (nxt <= 100_000 && (time[nxt] == 0 || time[nxt] == time[cur] + 1)) {
                    time[nxt] = time[cur] + 1;
                    queue.add(nxt);
                }
                // 2배
                nxt = cur * 2;
                if (nxt <= 100_000 && (time[nxt] == 0 || time[nxt] == time[cur] + 1)) {
                    time[nxt] = time[cur] + 1;
                    queue.add(nxt);
                }
            }
            System.out.println(minTime + "\n" + count);
        }
    }

}
