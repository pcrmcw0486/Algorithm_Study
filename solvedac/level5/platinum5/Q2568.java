package solvedac.level5.platinum5;

import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q2568
 * @문제이름 : 전깃줄
 * @난이도 : Platinum V
 * @date : 2022-03-08 오후 1:16
 * @author : pcrmcw0486
 * @문제이해
 * 두 전봇대 사이에 전깃줄이 교차하고 있다. 몇개의 전깃줄을 없애 교차하지 않도록 하게 하고싶다.
 * 전깃줄 개수와 위치의 번호가 주어질 때 남아있는 모든 전깃줄이 교차하지 않도록 하기 위해 없애야하는
 * 최소 개수의 전깃줄을 구하는 프로그램
 *
 * 교차하지 않기 위해 없애야 하는 전깃줄의 최소 개수를 출력
 * 없애야하는 전깃줄의 A전봇대에 연결되는 위치의 번호를 오름차순으로 출력
 * 전깃줄 개수는 100,000 이하의 자연수
 * 번호는 500,000 이하의 자연수
 * 같은 위치에 두 개이상의 전깃줄이 연결되지는 않는다.
 * @알고리즘
LIS
 * @접근방법

*/
public class Q2568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines.add(new Line(a, b));
        }
        int[] temp = new int[N];
        int[] pos = new int[N];
        int size =1;
        lines.sort(((o1, o2) -> o1.a - o2.a));
        temp[0] = lines.get(0).b;
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).b > temp[size-1]) {
                pos[i] = size;
                temp[size++] = lines.get(i).b;
            }else{
                int idx = find(0, size-1, lines.get(i).b, temp);
                temp[idx] = lines.get(i).b;
                pos[i] = idx;
            }
        }
        // 0 0 1 0 1 2 3 4
        // 8 2 9 1 4 6 7 10
        size--;
        StringBuilder sb = new StringBuilder();

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = lines.size() - 1; i >= 0; i--) {
            if (pos[i] != size) {
                ans.add(lines.get(i).a);
            }else{
                size--;
            }
        }
        sb.append(ans.size()).append('\n');
        for (int i = ans.size() - 1; i >= 0; i--) {
            sb.append(ans.get(i)).append('\n');
        }

        System.out.print(sb);
    }

    private static int find(int left, int right, int value, int[] arr) {
        int mid;
        while (left < right) {
                mid = (left +right)>>1;
            if (arr[mid] <= value) {
                left = mid +1;
            }else{
                right = mid;
            }
        }
        return right;
    }

    static class Line {
        int a, b;
        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
