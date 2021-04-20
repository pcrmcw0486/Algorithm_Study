/*
https://www.acmicpc.net/problem/4386
*** 별자리 만들기 Gold IV ***
*** 알고리즘 자료구조 스킬 ***
*** 문제 조건 ***
@time : 1sec
@memory : 128MB
n 개의 별들을 이어서 별자리를 하나 만든다. 조건은 다음과 같다.
 - 별자리를 이루는 선은 서로 다른 두 별을 일직선으로 이은 형태이다.
 - 모든 별들은 별자리 위의 선을 통해 서로 직/간접적으로 이어져 있어야한다.
    (연결된 그래프이다.)
별들이 2차원 평면위에 있다고 할 때, 선을 하나 이을 때 마다 두 별 사이의 거리만큼 비용이든다.
별자리를 만드는 최소 비용을 구하시오.
@input
  N : 별의 개수 (1<=N<=100)
  n개 줄에 걸쳐 각 별의 x,y좌표가 실수 형태로 주어짐.최대 둘째자리가지 주어진다.
  좌표는 1000을 넘지 않는 양의 실수이다. 0~1000,0~1000
@output
 최소 비용 출력(절대/상대오차는 10-2 소수 둘째자리)
*** 접근 방법 ***
 간선은 가상의 선으로 생각했을 때 매우 많은 dense한 그래프이다.
 Prim 알고리즘을 사용한다.
  1. 별 개수 n이 주어질 때  가장 짧은 거리에 있는 별을 빠르게 찾는 방법. > 최소비용 간선 찾기.
  2. 별의 구성여부 확인.
  3. 구성여부에 따른 (추가 혹은 무시) and then next star. which selected.
진짜 ㄹㅇ 만약에 n개 중에 동일한 별이 있다라고 하면 화가날것같아요.
 */
package tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Star1 {
    static int serialID = 0;
    int idx;
    double x;
    double y;

    public Star1(double x, double y) {
        idx = serialID++;
        this.x = x;
        this.y = y;
    }

    public double calcDist(Star1 o1, Star1 o2) {
        double dx = Math.abs(o1.x - o2.x);
        double dy = Math.abs(o1.y - o2.y);
        double result = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return result;
    }
}

public class Q4386 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Star1> stars = new ArrayList<>();
        PriorityQueue<Star1> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars.add(new Star1(x, y));
        }

    }
}
