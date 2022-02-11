package SAMSUNG.SWExpert._04DC_BT;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
L분짜리 광고.
N개의 피크 시간대, i번째 피크시간대 (si~ei)
최대한 피크 시간대와 길게 겹치도록 광고를 올린다.
적절히 t를 정해 t분 부터 t+L까지 지속됨.

가장 많이 겹치는 'L'을 구하고 싶다.
[ )
많이 겹치려면. 하나의 광고 이상 겹친다.
1광고와 겹친다면 [234 5) [6 7) => 4개 인데.
  si < ei < si+1
  어떠한 정수 L
  어떤 광고와 겹칠 때 최대는
  먼저시작한 광고의 시작점과 겹칠때?
  시작점 보다 앞으로가서 -1하고 뒤로가서 +1 해봐야 같은 값이고
  시작점보다 앞으로 가서 -1햇는데 뒤에 광고가 없으면 어차피 -1이라
  최댓값은 먼저시작한 광고의 시작점과 겹칠때이다.
  그렇다면 어떠한 광고의 시작점으로 부터 L만큼 갔을 때 겹치는 개수를 구하면 된다.
  [t, t+L] 이고 각 광고들이 [s,e)로 있을 때
  [Math.max(t,s), Math.min(t+L-1, e-1)] 이다.
  예시에서
  2 5
  6 10 이면 우리가 처음 선택하는 광고에 맞추면 [2,7(2+5))가 되고
  [2,5)   [6,7) -> 5-2 + 7-6 = 1이되고
  이 과정은 localMin
  광고의 끝 t+L 이 어떤 광고의 시작점 s보다 작거나 같으면 끝이난다.

  아까도 그렇고 같은 문제인데 그 counting 에서 많이 헷갈린거같다.

  N datainput이 10000개.
* */
public class No_3_AddTime {
    static int N;
    static int L;
    static int[][] schedule;
    static long[] totalSum;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        schedule = new int[100001][2];
        totalSum = new long[100001];
        for(int test_case = 1; test_case<=T;test_case++){
            sb.append('#').append(test_case).append(' ');
            L = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());
            for(int i =1;i<N+1;i++){
                st = new StringTokenizer(br.readLine());
                schedule[i][0] = Integer.parseInt(st.nextToken());
                schedule[i][1] = Integer.parseInt(st.nextToken());
                totalSum[i] = totalSum[i-1] + schedule[i][1] - schedule[i][0];
            }

            long ans = -1;
            for(int i =1;i<N+1;i++){
                ans = Math.max(ans, findBest(i));
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb.toString());
    }

    // t시간으로 부터 L만큼 떨어진 위치에 존재하는 광고가 어디있는가.
    //이걸 찾는 거임
    //무엇을 찾아야하는지에 집중해보자.
    //우리는 지금 선택한 i번째 광고로 부터 L만큼 떨어진 광고를 찾아
    // 거기까지 합을 구해야한다.

    //시작시간이 0~~~~ 10^8 안에 어딘가 존재하는데
    //t+L 보다 작은 어떤 시작시간이 존재하는데 그런 광고중 가장 뒤에 있는 광고를 찾고 싶음.
    public static long findBest(int idx){
        // @ left와 right는 index임.
        int left = idx;
        int right = N;
        int target = schedule[idx][0]+ L;
        int mid;
        int pos = -1;
        while(left<=right){
            mid = (left+right)>>1;
            if(schedule[mid][0] <= target){
                pos = mid;
                left = mid +1;
            }else{
                right = mid -1;
            }
        }
        //left에 위치하고 있음. 즉 idx~left까지의 합을 구해야함.
        //근데 left를 다 못채울수도 있음.
        //[s     e]   t+L   idx == pos
        // [s  ... t+L]    idx  != pos
        //근데 만약 [s...t+L e] 가 아니라  [s ...e] t+L이면?  e의 위치에 따라 달라진다.
        long totalTime =0 ;
        if(target < schedule[pos][1]){
            totalTime = totalSum[pos-1] - totalSum[idx-1] + (target-schedule[pos][0]);
        }else{
            totalTime = totalSum[pos] - totalSum[idx-1];
        }
        return totalTime;
    }
}
