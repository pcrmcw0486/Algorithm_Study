/* 숨바꼭질(Silver1)
1초마다 움직여서 동생의 위치를 찾는다.
움직이는 방법은 총 3가지로 매초 마다 3가지 방법씩 늘어난다.
가장 크게 움직였을 때 또한 2제곱 수로 늘어남.
2 4 8 16 32 64 
모든 경우의수를 찾는 것은 부담이 된다고 판단.
동생의 위치의 /2 보다 작은 경우에는 뒤로 가지 않는다.
또한 동생의 위치가 밑에 있을 때는 밑으로만 간다.
 */
package SolveByCategory.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q12851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);

        // System.out.println(solution(N, K));
        solution(N, K);
    }

    public static void solution(int N, int K) {
        if (K <= N) {
            System.out.println(N - K + "\n" + 1);
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        int[] time = new int[100001];
        int minTime = Integer.MAX_VALUE;
        int count = 0;
        q.offer(N);
        time[N] = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            int next;
            if (time[now] > minTime) {
                System.out.println(minTime + "\n" + count);
                break;
            }
            for (int i = 0; i < 3; i++) {
                if (i == 0)
                    next = now - 1;
                else if (i == 1)
                    next = now + 1;
                else
                    next = now * 2;

                if (next < 0 || next > time.length - 1)
                    continue;
                if (next == K) {
                    minTime = time[now];
                    count++;
                }
                if (time[next] == 0 || time[next] == time[now] + 1) {
                    q.add(next);
                    time[next] = time[now] + 1;
                }
            }
        }

    }
}
/////////////////////////////// 처음 한 것도 맞았네
// 근데 가독성이 좋지 않음.
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.Queue;

// public class Main {
// public static void main(String[] args) throws IOException {
// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// String[] s = br.readLine().split(" ");
// int N = Integer.parseInt(s[0]);
// int K = Integer.parseInt(s[1]);

// // System.out.println(solution(N, K));
// solution(N, K);
// }

// public static void solution(int N, int K) {
// if (K <= N){
// System.out.println(N - K + "\n" + 1);
// return;
// }

// Queue<int[]> position = new LinkedList<>();
// int[][] visit = new int[100001][2];
// for (int i = 0; i < visit.length; i++) {
// visit[i][0] = Integer.MAX_VALUE;
// visit[i][1] = 0;
// }
// position.offer(new int[] { N, 1 });
// visit[N][0] = 1;
// visit[N][1] = 1;
// while (!position.isEmpty()) {
// int[] np = position.poll();
// int dist = np[1];
// int number = np[0];
// int count = visit[number][1];
// //System.out.println(np[0] + " " + np[1]);
// if (number == K) {
// System.out.println(visit[K][0] - 1 + "\n" + visit[K][1]);
// break;
// }
// if (number - 1 > 0 && dist + 1 <= visit[number - 1][0]) {
// if (visit[number - 1][1] == 0) {
// visit[number - 1][0] = dist + 1;
// position.offer(new int[] { number - 1, dist + 1 });
// }
// visit[number - 1][1] += count;
// }
// if (number + 1 < visit.length && dist + 1 <= visit[number + 1][0]) {
// if (visit[number + 1][1] == 0) {
// visit[number + 1][0] = dist + 1;
// position.offer(new int[] { number + 1, dist + 1 });
// }
// visit[number + 1][1] += count;
// }
// if (number * 2 < visit.length && dist + 1 <= visit[number * 2][0]) {
// if (visit[number * 2][1] == 0) {
// visit[number * 2][0] = dist + 1;
// position.offer(new int[] { number * 2, dist + 1 });
// }
// visit[number * 2][1] += count;
// }
// }
// }
// }
