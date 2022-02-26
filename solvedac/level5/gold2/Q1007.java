package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q1007
 * @문제이름 : 벡터 매칭
 * @난이도 : GoldII
 * @date : 2022-02-12 오전 7:59
 * @author : pcrmcw0486
 * @문제이해
 * 평면상 N개의 점 그 점 집합을 P
 * P의 벡터 매칭은 벡터의 집합 벡터는 P의 한점에서 다른 점으로 끝나는 벡터의 집합
 * P에 속하는 모든 점은 한번씩 쓰여야한다.
 * 벡터 매칭에 있는 벡터 개수는 P점의 절반이다.
 * 벡터 매칭의 벡터 합 길이 최소값
 * @알고리즘
 * 모든 조합을 보는 것은 너무 많은 경우의 수이다.
 * 벡터 합의 길이가 최소가 되려면,
 * @접근방법

 */
public class Q1007 {
    static class Vec{
        double x;
        double y;

        public Vec(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double sum(){
            return Math.sqrt(x * x + y * y);
        }
    }
    static double ans;
    static int N;
    static Vec[] data;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while( T-- >0){
            ans =Double.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            data = new Vec[N];
            double sumX=0, sumY=0;
            for(int i =0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                data[i] = new Vec(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
                sumX += data[i].x;
                sumY += data[i].y;
            }
            solution(0, 0, new Vec(sumX, sumY));
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }

    public static void solution(int depth, int idx, Vec prev){
        if(depth == N/2){
            //N/2개를 이미 선택하여 뺏음
//            for(int i =idx;i<N;i++){
//                prev.x += data[i].x;
//                prev.y += data[i].y;
//            }
            ans = Math.min(ans, prev.sum());
            return;
        }
        for(int i= idx;i<N;i++){
            //select하는 경우
            solution(depth + 1, i+1, new Vec(prev.x -2*data[i].x, prev.y-2*data[i].y));
            //solution(depth + 1, i+1, new Vec(prev.x -data[i].x, prev.y-data[i].y));
            // prev.x += data[i].x ;
          //  prev.y += data[i].y ;
            //select하고 나서 다음거 보기전에 자신을 더해줌 및 원상복귀
        }
    }
}
