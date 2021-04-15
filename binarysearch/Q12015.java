/*
https://www.acmicpc.net/problem/12015
*** 가장 긴 증가하는 부분 수열 2 Gold II ****
*** 접근 방식 ***
기존의 LIS의 경우 dp방식으로 풀어내어 O(n^2)의 시간복잡도를 가진다.
이번 문제의 경우 수열 A의 크기가 1_000_000으로 O(N^2)시 시간초과.
 * LIS를 푸는 방법은 대체로 다양한데 이는 천천히 알아보도록 하자.
하여 처음에 
'아, 이분탐색을 써서 하려면 소팅이 되어 있어야 한다.'
' 그러면 i번째 구할 때 i-1번째 까지 미리 length값과 data값으로 sorting을 하자'
였는데 'data(length)일때 ' 6(5) 7(1) 10(3) 15(2) input 8 일 때 어떻게 해야하는가'
라는 질문에 해결을 못함.14 15 8 9 10 7 2 3 4 5 6 (9)
일때 자신보다 작은 값 중 가장 큰 length값을 찾자 였는데,
이게 수렴 조건이 두개라 애매해짐.
이를 해결한 방법이 있는데 그게 다음의 풀이임.
내부의 값 보다 길이에 신경을 쓰는 방법(어차피 길이는 정해져 있으므로)
if 14 15 로 저장을 해두고 다음 값이 들어 올 때
14 -> 8 로 바꾸면 세부적인 길이는 알 수 없으나 이러나 저러나
길이 2는 그대로 유지됨.
여기서 max값 보다 크면 커지고 예를 들어
14 15 8 17 9 16 19 라고 해보자.
1) 14
2) 14 15
3) 8 15 (14 -> 8 )
4) 8 15 17 (14 15 17 을 내포하고있음  순서적으로는 맞지 않으나
새로 시작될 수 있는 가능성에 초점을 둠.)
5) 8 9 17 
6) 8 16 17
7) 8 16 17 19
총 길이 4.
N에 대해 log N O(N logN)
*** 사용 알고리즘 자료구조 스킬 ***
이분탐색.
*** 문제 조건 ***
@param
  N : 수열 A의 크기  1<= N <= 1_000_000
  A_j : 각 원소 크기 1<= A_i <= 1_000_000
@time : 1s
@memory : 512MB
 */
package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12015{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] data = new int[N+1];
        
        for(int i =1;i<N+1;i++){
            data[i] = Integer.parseInt(st.nextToken());
        }
        //solution
        int end = 1;
        for(int i =2;i<N+1;i++){
            if(data[end] < data[i]){
                data[++end] = data[i];
            }else{
                //찾아야할것이 자신보다 큰 수 중 첫번째
                // 즉, upper_bound
                int left =1;
                int right = end;
                while(left < right){
                    int mid = (left + right)/2;
                    if(data[mid] == data[i]){
                        right = mid;
                        break;
                    }
                    else if(data[mid]>=data[i]){
                        right = mid;
                    }else{
                        left = mid+1;
                    }
                }
                data[right] = data[i];
            }
        //     for(int j =1;j<=end;j++){
        //         System.out.print(data[j] + " ");
        //     }
        //    System.out.print(end);
        //     System.out.println();
        }
        System.out.println(end);
    }
}
