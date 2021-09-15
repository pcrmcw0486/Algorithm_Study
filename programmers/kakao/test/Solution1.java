package programmers.kakao.test;

import java.util.*;

public class Solution1 {
    public static void main(String[] args) {

    }

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        int[] reportCount = new int[id_list.length];
        Set<Integer>[] mailList = new HashSet[id_list.length];
        Map<String, Integer> user = new HashMap<String, Integer>();
        for (int i = 0; i < id_list.length; i++) {
            mailList[i] = new HashSet<Integer>();
            user.put(id_list[i], i);
        }
        // report Count 증가 (중복은 Set으로 해결)
        // report 처리
        for (String s : report) {
            String[] tmp = s.split(" ");
            int reportUser = user.get(tmp[0]);
            int badUser = user.get(tmp[1]);
            if (!mailList[badUser].contains(reportUser)) {
                reportCount[badUser]++;
                mailList[badUser].add(reportUser);
            }
        }
        for (int i = 0; i < reportCount.length; i++) {
            if (reportCount[i] >= k) {
                for (Integer u : mailList[i]) {
                    answer[u]++;
                }
            }
        }
        return answer;
    }
}
