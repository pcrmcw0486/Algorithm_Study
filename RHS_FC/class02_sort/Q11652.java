package RHS_FC.class02_sort;

/*
카드 Silver IV
N장 가지고 있다.
적혀있는 수는 -2^62 ~ 2^62 -> long long (data 값)
가장 많이 가지고 있는 정수를 구하는 프로그램 작성
여러가지라면 작은 것을 출력.
N범위 : 100,000

방법 2가지
1. 정렬(NlogN) -> {
    정렬 후 count O(N)
    NlogN + N
}
2. Max값 별로 찾기(
    만약 하나씩 나온다면 최악
    Max값에서 정렬하여 찾는 방법.

    merit가 있는가?
    데이터 읽기.근데 다 읽기 전까지 뭐가 max인지 모름.
    그래서 하고 또 max별로 빼야함.
    별로 없네.
3. tree HashMap
)
 */
import java.io.*;
import java.util.*;

public class Q11652 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] data = new long[N];
        for (int i = 0; i < N; i++) {
            data[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(data);
        int max = 0;
        long tmp = data[0];
        int tmpCount = 1;
        long answer = 0;

        // 처리 로직
        // 1 1 1 1 1 2 2 2
        // 앞뒤가 달라지는 지점.
        // 즉 처음 나오는 2에서 update해주어야함.
        for (int i = 1; i < N; i++) {
            if (tmp == data[i]) {
                tmpCount++;
            } else {
                // update
                if (max < tmpCount) {
                    answer = tmp;
                    max = tmpCount;
                }
                // init
                tmpCount = 1;
                tmp = data[i];
            }
        }
        if (max < tmpCount) {
            answer = tmp;

        }
        System.out.println(answer);
    }

}
