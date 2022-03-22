package DayByDay._0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @문제번호 : Q1411
 * @문제이름 : 비슷한 단어
 * @난이도 : Siver II
 * @date : 2022-03-22 오후 5:57
 * @author : pcrmcw0486
 * @문제이해
 * 단어 A를 숌스럽게 바꾸어 또 다른 단어 B로 바꾸면 그 단어는 비슷한 단어
 * 숌스럽게 바꾼다 : A에 등장하는 모든 알파벳을 다른 알파벳으로 바꾼다.
 * 단어에 등장하는 알파벳의 순서는 바뀌지 않는다.
 * ** 두 개의 다른 알파벳을 하나의 알파벳으로 바꿀 수 없지만, 자기자신으로 바꾸는 것은 가능하다 **
 * a->z b->z는 안되지만 a->a는 된다.
 * 단어가 여러개 주어질 때 몇 개의 상이 비슷한가.
 * 단어의 길이는 최대 50 N은 100보다 작거나 같은 자연수.
 * '몇개의 쌍이 비슷한가?'
 * @알고리즘
 * 해시
 * @접근방법
 * 알파벳 모양이 다르지만 '위치는 같다'라는 것에 중점을 두고 진행한다.
 * 이 위치가 같다를 어떻게 표현할까가 고민인데..StringBuilder를 이용해서.
 * 또한 '쌍'의 개수 - group의 개수임.
 * 단어 한번만 보면되고 N개를 봐야하니까 O(Nlen(str))
 * 단어 한번 볼때 grouping위해 set에 넣으니까 O(1)
 * 개수에 비례한다.
*/
public class Q1411 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String,Integer> groups = new HashMap<String,Integer>();
        for (int i = 0; i < N; i++) {
            int[] pos = new int[26];
            char[] text = br.readLine().toCharArray();
            StringBuilder sb = new StringBuilder();
            int idx =1;
            for(char c : text){
                if (pos[c - 'a'] == 0) {
                    pos[c-'a'] = idx++;

                }
                sb.append(pos[c-'a']);
            }
            groups.put(sb.toString(), groups.getOrDefault(sb.toString(), 0) + 1);
        }
        int cnt =0;
        for (Integer value : groups.values()) {
            if (value >= 2) {
                cnt += (value*(value-1))/2;
            }
        }
        System.out.println(cnt);
    }
}
