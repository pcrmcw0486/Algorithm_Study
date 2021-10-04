package RHS_FC.class03_binarySearch;

/*
N일 동안, M번만 통장에서 돈 뺀다.
K원 인출하며, 뺀 돈으로 하루를 보낼 수 있으면 그대로 
사용하고 모자라게되면 남은금액은 통장에넣고
다시 K원 인출.
정확히 M번만, 최소금액 ㅏ
K원을 M번만 써서 가능한가?Yes/No
를 만족하는 최소금액K
  >K (작다) ||(ans) <K(같거나 크다)
what mean? 남은 금액이 그날 사용할 금액보다 많더라도 남은 금액은 통장에 집어넣고
다시 K원을 인출할 수 있다?
100 300
100 300
100 300
100 300 이렇게 가능하다 4번 가능하다 이말이구나.
그래서 300도 답이 될 수 있지만 최소는 아니다.라고 생각할 수 있는 거임.
근데 '최소'생각하면 굳이? 라는 생각은 듦.

범위설정의 중요성
L은 적어도 하루에 쓸 돈의 최댓값 만큼은 인출해야한다는 거.
[L...R] 범위 안에 정답이 존재한다.....
 */
import java.io.*;
import java.util.*;

public class Q6236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] plan = new int[N];
        for (int i = 0; i < N; i++) {
            plan[i] = Integer.parseInt(br.readLine());

        }

        int l = 1;
        int r = 1_000_000_001; // 최대 10000*100_000 10^9
        while (l + 1 < r) {
            int mid = l + r >> 1;
            // false ||(ans) true false? K원이 작아서 M번보다 많음.
            if (determination(plan, K, mid)) {
                l = mid;
            } else
                r = mid;
        }
        System.out.println(r);
    }

    public static boolean determination(int[] plan, int limit, int money) {
        int count = 0;
        int tmp = 0;
        for (int i = 0; i < plan.length; i++) {
            if (plan[i] > money)
                return true;
            if (tmp + plan[i] > money) {
                count++;
                tmp = 0;
            }
            tmp += plan[i];
        }
        count++;
        return count > limit;
    }

}
