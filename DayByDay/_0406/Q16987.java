package DayByDay._0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q16987
 * @문제이름 : 계란으로 계란치기
 * @난이도 : Silver I
 * @date : 2022-04-06 오후 2:26
 * @author : pcrmcw0486
 * @문제이해
 * 계란 (내구도, 무게)
 * 계란으로 계란 치면
 *  계란의 내구도는 상대 계란 무게만큼 깎인다.
 *  내구도가 0이하가 되는 순간 계란은 깨진다.
 *  1. 가장 왼쪽 계란
 *  2. 깨지지 않은 다른 계란 중 하나 친다..
 *    - 손에 든 계란이 깨졌거나 다 깨져있다면 치지 않고 넘어간다.
 *     - 이후 손에 든 계란을 원래 자리에 둔다.
 *     한번만 치는 문제.
 *  3. 가장 최근에 든 계란의 한칸 오른쪽 계란을 손에 들고 2번 과정을 진행한다.
 *   단, 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 계란 치는 과정을 종료한다.
 * @알고리즘

 * @접근방법

 문제를 제대로 읽자..
*/
public class Q16987 {
    static int N;
    static boolean[] isBreak;
    static Egg[] eggs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];
        isBreak = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.println(findMaxEgg(0,0));
    }

    public static int findMaxEgg(int index, int cnt) {
        if(index == N) {
            return cnt;
        }
        Egg selectedEgg = eggs[index];
        if(selectedEgg.durability <= 0) {
            return findMaxEgg(index + 1,cnt);
        }
        int max = cnt;
        for (int i = 0; i < N; i++) {
            //break과정
            int tmp =0 ;
            if(i == index) continue;
            Egg defenseEgg = eggs[i];
            if(defenseEgg.durability <= 0) continue;
            selectedEgg.durability -= defenseEgg.weight;
            defenseEgg.durability -= selectedEgg.weight;
            if(selectedEgg.durability <=0) tmp++;
            if(defenseEgg.durability<=0) tmp++;
            max = Math.max(max, findMaxEgg(index + 1, cnt + tmp));
            //복구 과정
            selectedEgg.durability += defenseEgg.weight;
            defenseEgg.durability += selectedEgg.weight;
        }
        return max;
    }

    static class Egg{
        int weight, durability;

        public Egg(int durability, int weight) {
            this.weight = weight;
            this.durability = durability;
        }
    }
}
