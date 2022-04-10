package programmers.kakao.Blind2022;
/**
 * @문제번호 : Solution4
 * @문제이름 :
 * @난이도 :
 * @date : 2022-04-08 오후 6:33
 *   07 : 27
 *   문제 잘읽자 제발..
 *   54분.
 * @author : pcrmcw0486
 * @문제이해
 * 불리하게
 * 어피치 n발 후 라이언 n발
 * 점수 계산
 * k는 1~10사이 점수 어피치 a발, 라이언 b발
 * k점을 어피치가 a발 맞히고 라이언이 b발 맞힐때
 * 더 많은 화살을 k점에 맞힌 선수가 k점을 (a==b면 어피치가)
 * a==b==0이면 둘다 0점.
 * 모든 점수 계산
 * 어피치가 n발쏜 후 , 라이언이 화살을 쏜다.
 * 우승할 수 없는 경우 -1을 return
 * 가장 큰 점수 차이로 우승하기 위해 정수배열 10부터 0까지
 * 총 10발을 10곳에 즉
 * 한발당 갈 수 있는 곳은 11곳
 * 11^10
 * 관점을 바꾸어 해당 점수를 받거나 받지 않거나 둘중 하나로 하면 2^10가지 경우의수
 *
 * @알고리즘

 * @접근방법

*/
public class Solution4 {
    public static void main(String[] args) {
        int n =4;
        int[] info = new int[]{2,0,1,1,0,0,0,0,0,0,0};
        int[] solution = solution(n, info);
        for (int i : solution) {
            System.out.print( i + " ");
        }
        System.out.println();
    }

    static int max = -1;
    static int[] result;
    private static int[] solution(int n, int[] info) {
        result = new int[11];
        dfs(10, n, info, new boolean[11]);
        if(max == -1)
            return new int[]{-1};
//        for(int i = 0;i<11;i++){
//            System.out.print(result[i] + " ");
//        }
//        System.out.println();
        return result;
    }

    private static void dfs(int point, int arrowCnt, int[] info, boolean[] isGetPoint) {
        if(point == -1 || arrowCnt == 0){
            //점수계산
            //true -> rion (무조건 화살을 한발이상 쐇음)
            int rionPoint =0;
            int apeachPoint =0;
            for (int i = 0; i < 10; i++) {
                if (isGetPoint[i]) {
                    rionPoint+=(10-i);
                } else if (info[i] != 0) {
                    apeachPoint += (10 - i);
                }
            }
            if (rionPoint > apeachPoint) {
                int diff = rionPoint- apeachPoint;
                if (diff > max) {
                    max = diff;
                    for (int i = 0; i < 11; i++) {
                        result[i] =0 ;
                        if(isGetPoint[i])
                            result[i] = info[i] +1;
                    }
                    if (arrowCnt > 0) {
                        result[10] += arrowCnt;
                    }
                }else if (diff == max) {
                    int[] candidate = new int[11];
                    for (int i = 0; i < 11; i++) {
                        candidate[i] =0 ;
                        if(isGetPoint[i])
                            candidate[i] = info[i] +1;
                    }
                    if (arrowCnt > 0) {
                        candidate[10] += arrowCnt;
                    }
                    boolean newIsBetter = false;
                    for (int i = 10; i >= 0; i--) {
                        if(candidate[i] > result[i]){
                            newIsBetter = true;
                            break;
                        }else if(candidate[i] < result[i])
                            break;
                    }
                    if (newIsBetter) {
                        System.arraycopy(candidate,0,result,0,result.length);
                    }
                }
            }
            return;
        }
        dfs(point -1, arrowCnt, info, isGetPoint);
        if (arrowCnt >= info[10 - point] + 1) {
            isGetPoint[10-point] = true;
            dfs(point - 1, arrowCnt - info[10 - point] - 1, info, isGetPoint);
            isGetPoint[10-point] = false;
        }

    }


}
