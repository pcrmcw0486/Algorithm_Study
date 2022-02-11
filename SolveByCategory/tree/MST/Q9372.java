/*
https://www.acmicpc.net/problem/9372
*** 상근이의 여행 Silver III ***
*** 접근 방법 ***
모든 국가들을 여행한다(모든 노드를 지난다) 근데 가장 적은 종류의 비행기를 탄다(최소 간선으로)
> 최소 간선으로 모든 노드를 지나게 해라. > Spanning Tree.
*** 알고리즘 자료구조 스킬 ***
Spanning Tree 구하기. 도 아님.
그냥 이해하고 있나 물어보는 문제.
*** 문제 조건 ***
@ time : 1s
@ memory : 256MB
@ 문제 이해 
    상근이 겨울방학 맞아 N 개국 여행한다. 
    최대한 적은 종류의 비행기를 타고 국가들을 이동하려고 한다.
    비행 스케줄이 주어졌을 때, 가장 적은 종류의 비행기를 타고 모든 국가를 여행한다.
    다른 국가로 이동 시, 다른 국가를 거쳐가도(심지어 이미 방문한 국가라도)된다.
@input
    T : 테스트 케이스 수 (T<=100)
    {
        N : 국가의 수 (2<=N<=1_000) -> 1~N개
        M : 비행기 종류(1<=M<=10_000)
        M개의 줄
        {
            a와 b쌍들이 입력된다. a와 b를 왕복하는 비행기(1<=a,b<=n, a!=b)
            주어지는 비행 스케줄은 항상 연결 그래프이다.
        }
    }
@output
    비행기 종류 최소 개수를 출력.
 */
package SolveByCategory.tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q9372 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            for (int i = 0; i < M; i++)
                br.readLine();
            sb.append(N - 1).append("\n");
        }
        System.out.print(sb.toString());
    }
}
