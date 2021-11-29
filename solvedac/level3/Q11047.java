package solvedac.level3;
/*
2021.11.29
문제번호 : Q11047
이름 및 난이도 : 
문제이해 
-----------------

접근 방법 :
1. 처음 드는 생각이 BFS 또는 DP였다.
그런데 동전가치의 개수가 1_000_000 그러면 BFS할때마다
각각에서 그 개수만큼의 노드로 퍼져서 검사해야함.
꼭 필요하지 않다. 또한 동전은 앞으로만 감(즉, 큰 수방향으로만감)
DP가 맞다.
2. 그다음 생각 TopDONN이냐 BOTTOMUP으로 접근할거냐.
 동전개수에서 뺀다? 아니면 동전을 모아가면서 그 수를 맞춘다.
 두가지 방법이 있다. 
 
 1 2 4 8 에 14가 들어오면?
 4 4 4 2  (44 == 8)
 8 4 2

제한 조건 : 
동전의 가치가 오름차순으로 주어지는데, i>=2 일 때,
Ai = Ai-1의 배수라고 한다. 나누어 떨어진다.
즉 큰수로 뺄 수 있다면 빼는 것이 좋다?
Ai 1개 와 Ai-1 * N 개 라고 했을 때 1개와 N개의 차이.

N이 10이하이고 각 동전의 수와 만들려는 값이 Int형으로 가능.
*/

import java.io.*;
import java.util.*;
public class Q11047 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coinArr = new int[N];
        for(int i =0;i<N;i++){
            coinArr[i] = Integer.parseInt(br.readLine());
        }
        int cnt = 0;
        int ans =0;
        for(int i = N-1;i>=0;i--){
            if(K < coinArr[i]) continue; // 4200 5000
            cnt = K/coinArr[i];
            K -= (cnt*coinArr[i]);
            ans+=cnt;
        }
        System.out.println(ans);
    }
}
