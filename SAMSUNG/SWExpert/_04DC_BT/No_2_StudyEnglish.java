package SAMSUNG.SWExpert._04DC_BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
공부한날은 전날보다 1씩 쌓이고 접속 안하면 0이되는 '연속 공부 기간' 점수
누적 연속 점수 중 최댓값을 사용.

p개의 날짜에 대해 실제로 하지 않아도 체크했다고 하자.
p를 적절히 체크했을 때 매일한 연속기간의 최대 가능 길이.
제한조건 200000
1. 투 포인터식 접근 방법
  어떠한 구간 [left, right]에 대해서 cnt가 남을 때
   그 다음 구간 right+1과 right의 차이가 cnt보다 작다면
   right를 증가
   cnt가 남을 때 더 갈 곳이 없다면(right가 끝에 도달하였거나 다음 right로 가지 못할 때 )
    right-left + cnt
    다음 left를 어디로 증가시켜야 하는가?
     >> cnt를 이용해서 다음 도달 가능한 cnt를 채울 때 까지.

2. 이분탐색
 >> 어떠한 답 기준으로 왼쪽이 만족하거나 오른쪽이 만족하는 정렬된 답이 아니기 때문이라고 생각했다가
 >> 있을 것 같아서 생각해봄. O(NlogN)으로 찾아보자 라고 생각.
 pick 한 x에 대해 M을 만족하는 가장 긴 날짜.를 찾는다.
 >> 이건 이분탐색 가능. Math.max(첫날짜-M,0) ~ Math.min(마지막 날짜 + M , 10^6)
 으로 상태공간을 정의할 수 있고.
  0 3 5 6 10 11 13
 (day[right] - day[left]-1) - (right-left -1) = right와 left 사이에 필요한 count수
 이 때 가장 처음 또는 가장 마지막인 경우 날짜를 하나씩 더 더해주어야하한다(만들어 줄 때도 한칸 썼으니까)
 이를 따로 처리해준다.
 그렇다면 day[right]-day[left] 에다가 (M- 계산값 : 추가로 더 움직일 수 있는 count) 를 더해주면 최장 길이가 됨.

 이를 각각 가장 작은날짜 부터 큰 날짜 까지 모두 구해주면서 나온 max값.

 문제를 추상화하면 0~X 범위사이에 N개가 주어질 때 주어진 값들 사이의 빈 공간이 M 이하로 차이나는 두 값.
 이라는 문제에서 + 남은 count를 더해주면 되는 문제였음.

 이분탐색이라고 생각할 수 있는 부분은
   1. 가장 긴 날짜. -> 결정문제 변환가능.
   2. 날짜의 범위.
   3. 어떠한 조건을 만족하는 .
   4. 탐색 각각에 대해 찾아보아야함.

참조) 아 물론 twopointer도 가능.

어떠한 구간 [left, right]에 대해서 cnt가 남을 때
   그 다음 구간 right+1과 right의 차이가 cnt보다 작다면
   right를 증가
   cnt가 남을 때 더 갈 곳이 없다면(right가 끝에 도달하였거나 다음 right로 가지 못할 때 )
    right-left + cnt
    다음 left를 어디로 증가시켜야 하는가?
     >> cnt를 이용해서 다음 도달 가능한 cnt를 채울 때 까지.

----------
이분 탐색?
O(N log10 ^6)
pick 한 x에 대해 M을 만족하는 가장 긴 날짜.를 찾는다.
 >> 이건 이분탐색 가능. 5 ~ Math.min(마지막 날짜 + M , 10^6)
  0 3 5 6 10 11 13
day[right] - day[left] - (right-left -1) = 이게 M임. M보다 작거나 같은 (upp)
이 때 수렴하는 곳과 현재 위치 빼면 그게 길이.

M 이 2면
3 (5 ~ 10^6)



* */
public class No_2_StudyEnglish {
    static int N, M;
    static int[] day;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1 ;test_case<=T;test_case++){
            sb.append('#').append(test_case).append(' ');
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            day = new int[N+2];
            st = new StringTokenizer(br.readLine());
            for(int i = 1;i<N+1;i++){
                day[i] = Integer.parseInt(st.nextToken());
            }
            day[0] = Math.max(day[1]-M,0);
            //day[N+1] = day[N]+M;
            day[N+1] = Math.min(day[N] + M , 1000000);

//            int max = -1;
//            for(int i =0;i<N+1;i++){
//                max = Math.max(max, findLongestDay(i));
//            }
            int max = solutionByTwoPointer();
            sb.append(max).append('\n');
        }
        System.out.println(sb.toString());
    }

    //현재 left값에서 right값을 증가시키며 cnt값을 계산한다.
    // 이때 cnt값은 day[right]-day[left]-1 만큼 늘어난다.
    public static int solutionByTwoPointer(){
      int max =- 1;
      int right = 1;
      int cnt = -1;
      for(int left = 1; left<N+1;left++){
          //앞에 꺼 미리 계산하는 방법을 알게 되었음.
          // cnt<= M 인 최댓값을 원할 때
          //이걸 이분탐색으로 풀어내는 거고.
          while(right + 1< N+1){
              int betweenIdx = (right+1)-left-1;
              int betweenDayCnt = day[right+1]-day[left]-1;
              cnt = betweenDayCnt - betweenIdx;
              if(cnt<=M) right++;
              else break;
          }
          int a = right - left -1;
          int  b= day[right]-day[left]-1;
          int length = (day[right]-day[left]+1) + M - (b-a);
          max = Math.max(max, length);
      }
      return max;
    }

    public static int findLongestDay(int idx){
        int left = idx;
        int right = N+1;
        int ans = left;
        while(left<=right){
            int mid = (left+right)>>1;
            int diffDay = calcDay(idx,mid);
            if(diffDay <= M){
                ans = (day[mid] - day[idx]+1) + (M-diffDay);
                left = mid +1;
            }else{
                right = mid -1;
            }
        }
        return ans;
    }
    public static int calcDay(int left, int right){
        if(left == right) return 0;
        if(left==0 && right == N+1) return(day[right]-day[left])-(right-left-1)+1 ;
        if(left==0 || right == N+1) return(day[right]-day[left])-(right-left-1) ;
        return (day[right]-day[left]-1)-(right-left-1);
    }
}
