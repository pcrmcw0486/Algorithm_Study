package SAMSUNG.SWExpert._07Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * --- 중간값 구하기 ----
 *
 *  두개의 힙으로 min값 기준 [less than Min maxheap 내림차순 정렬] midValue [greatehrThanMidvalue 오름차순 정렬]
 *  순서를 맞추고 들어오는 값에 맞춰 값을 변경해준다.
 *
 한 개의 자연수를 적는다.
 N번에 걸쳐 2개씩 추가한다.
 2개를 추가적으로 적을 때 마다 지금까지 적은 수 중 크기가 '중간'인 수를 알려주어야 한다.
 5를 쓰고, (1,3)  (2,6) (8,9) 를 썻다고 가정하면
 [1,3] 을 쓰면 [1,3,5] 라서 3이고
 [2,6]을 쓰면 [1,2,3,5,6]이라 3이고
 [8,9] 를 쓰면 [1,2,3,4,5,6,8,9]라서 크기가 중간인 수는 5이다.
 N개의 중간값을 매번 출력하면 출력양이 많으므로 그 수들의 합을 20171109로 나눈 나머지를 출력하라.
 N<= 200000

 중간값을 찾기 위해서는? sorting이 되어 있어야겟죠.
 최대 최소를 하나씩 뺏을 때 남는 하나가 중간값이겟죠?
 근데 다 빼면 다시 넣어야되는데?
 빼지 않고 보는 방법이 있나요? 중복된 값도 있을 수도 있죠..

 * */
public class No_3_midValue {
    public static int MOD = 20171109;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            sb.append('#').append(tt).append(' ');
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int midValue = Integer.parseInt(st.nextToken());
            long ans = 0;
            //작은건 maxheap으로 구성 큰건 minheap으로 구성. min값 기준
            PriorityQueue<Integer> lessThanMidValue = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> greaterThanMidValue = new PriorityQueue<>();
            for(int i =0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                //같아도 차피 mid value가 됨. 둘 중 하나가
                if(Math.min(a,b) <= midValue && Math.max(a,b) >= midValue){
                    lessThanMidValue.add(Math.min(a,b));
                    greaterThanMidValue.add(Math.max(a,b));
                }else if(Math.max(a,b) < midValue){
                    lessThanMidValue.add(a);
                    lessThanMidValue.add(b);
                    greaterThanMidValue.add(midValue);
                    midValue = lessThanMidValue.poll();
                }else if(Math.min(a,b) > midValue){
                    greaterThanMidValue.add(a);
                    greaterThanMidValue.add(b);
                    lessThanMidValue.add(midValue);
                    midValue = greaterThanMidValue.poll();
                }
                ans = (ans + midValue)%MOD;
                System.out.println(midValue);
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb.toString());
    }
}
