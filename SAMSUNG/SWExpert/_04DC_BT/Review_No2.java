package SAMSUNG.SWExpert._04DC_BT;
//p개의 날짜를 적절히 선택하였을 때 연속가능한 최대길이 K
// K길이일 때 p개 이하로 날짜를 선택가능한가?

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Review_No2 {
    static int N, P;
    static int[] day;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            sb.append('#').append(tt).append(' ');
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            day = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) day[i] = Integer.parseInt(st.nextToken());

            int ans = 0;
            /*for(int i =0;i<N;i++){
                ans = Math.max(ans, findByBinarySearch(i));
            }*/
            ans = findByTwoPointer();
            sb.append(ans).append('\n');
        }
        System.out.println(sb.toString());
    }

    public static int findByTwoPointer() {
        int ans = 0;
        int right = 0;
        for (int left = 0; left < N; left++) {
            int diffDay = 0;
            while (right < N) {
                diffDay = (day[right] - day[left] + 1) - (right - left + 1);
                if (diffDay <= P) right++;
                else
                    break;
            }
            right--;
            diffDay = (day[right] - day[left] + 1) - (right - left + 1);
            ans = Math.max(ans, day[right] - day[left] + 1 + P - diffDay);
        }
        return ans;
    }

    public static int findByBinarySearch(int index) {
        int left = index;
        int right = N - 1;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int diffDay = (day[mid] - day[index] + 1) - (mid - index + 1);
            if (diffDay <= P) {
                ans = Math.max(ans, (day[mid] - day[index] + 1) + (P - diffDay));
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
