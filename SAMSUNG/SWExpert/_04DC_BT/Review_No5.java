package SAMSUNG.SWExpert._04DC_BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Inversion Counting Review<br>
 * 길이 N 수열 A가 주어진다. i < j 일 때, A[i] > A[j]라면 inversion 이라고 한다.<br>
 * Inversion 의 개수를 구하시오.<br>
 * 저게 이제 merge sort다..
 *
 * @implNote 그냥 구현하면 시간 터진다. 거의 O(N^2)이였는데 겨우 풀린거같은데 <br>
 * <hr>새로 아이디어 생각하기 <br>
 * "자신보다 오른쪽인데" "자신보다 작으면 된다"
 * 자신보다 왼쪽인 거는 고려할 필요가 없다.
 * mergeSort의 동작과정의 이해
 * L 다하고 R하고 합치면서 비교한다.
 * 즉 L과 R비교하면서 L이 더 작아야되는데 R보다 커서 올라가는 경우에 대해
 * inversion이 일어난다. (정리하면 sorting과정에서 자신의 오른쪽 을 다 세어보게 됨)
 */
public class Review_No5 {
    static int N;
    static int[] data, tmp;
    static long ans ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            sb.append('#').append(tt).append(' ');
            N = Integer.parseInt(br.readLine());
            ans = 0;
            data = new int[N];
            tmp = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                data[i] = Integer.parseInt(st.nextToken());
            }
            mergeSort(0,N-1);
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }

    public static void  mergeSort(int left, int right){
        if(left>=right) return;
        int mid = (left+right)>>1;
        mergeSort(left, mid);
        mergeSort(mid+1,right);
        merge(left, mid, right);
    }

    public static void  merge(int left, int mid, int right){
        int x = left, y = mid+1;
        int idx =left;
        while(x<=mid || y<=right){
            if (y > right || (x <= mid && data[x] < data[y])) {
                tmp[idx++] = data[x++];
            }else{
                tmp[idx++] = data[y++];
                ans += mid - x + 1;
            }
        }
        for(int i =left;i<=right;i++){
            data[i] = tmp[i];
        }
    }
}
