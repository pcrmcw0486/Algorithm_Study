/*단계별로 풀어보기 - 이분탐색(Binarysearch)
숫자카드2(Silver 4)
처음에 접근은 그냥 배열 만들어서 있으면 증가시키고 바로 참조하면되는거
아닌가 생각을 했는데 수의 범위를 보니까 -10,000,000~10,000,000이다.
int배열 하나만해도 80,000,000바이트니까 약 80MB다.
데이터 낭비도 심할 뿐더러 각 수가 들어올때마다 10000000을 더해주어야 하는
연산도 M번 들어가야한다.
데이터의 효율성을 고려한 자료구조로 linkedlist를 생각했고 탐색을 쉽게하기 위해
Tree구조를 사용하는 것이 베스트가 아닌가 생각이 든다.

그런데 시간은 그냥 배열 선언하고 하는게 빠르네;
참조에 시간이 덜 걸려서 그런가보다.
 */
package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        HashMap<Integer, Integer> map = new HashMap<>();
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int data = Integer.parseInt(st.nextToken());
            if (map.containsKey(data)) {
                map.replace(data, map.get(data) + 1);
            } else {
                map.put(data, 1);
            }
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            if (map.containsKey(target))
                sb.append(map.get(target)).append(" ");
            else
                sb.append("0").append(" ");
        }

        System.out.println(sb.toString());

    }
}
