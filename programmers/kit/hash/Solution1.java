package programmers.kit.hash;

/*
완주하지 못한 선수 level 1
마라톤 경기 참여 선수 수는 1명 이상 100_000 이하.
completion의 길이는 participant길이보다 1 작음.
참가자이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있음.
참가자 중에는 동명이인이 있을 수 있음.

여러 방법이 존재할 수 있음.
Naiive하게 생각하면 100_000 * 999_999 돌리면서 확인하는 방법인데 너무 큼. 되긴 될것 시간이 오래 걸림.
삽입O(1), 탐색 O(1) 인 Map 또는 Set 알고리즘으로 해결 가능함.
근데 동명이인이 있다는게 문제임.
Set으로 해결 가능한가? 만약 2명이 있는데 1명이 못한 경우는 가능하나, 2명이 모두 완주한다면 힘드네.
그냥 Map으로 하겟습니다.
여러 방법이 있음.
*/
import java.util.*;

public class Solution1 {
    public static void main(String[] args) {
        String[] participants = { "mislav", "stanko", "mislav", "ana" };
        String[] completion = { "stanko", "ana", "mislav" };
        System.out.println(solution(participants, completion));
    }

    public static String solution(String[] participants, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String key : participants) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        for (String player : completion)
            map.put(player, map.get(player) - 1);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                answer = entry.getKey();
                break;
            }
        }
        return answer;
    }

}
