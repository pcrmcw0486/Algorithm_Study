package programmers.naver;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        String answer = solution.solution(new String[] { "yxxy", "xxyyy" }, 2, 1);
        System.out.println(answer);

    }

    public String solution(String[] research, int n, int k) {
        String answer = "None";
        ArrayList<Integer>[] report = new ArrayList[26];
        ArrayList<String> issue = new ArrayList<String>();
        for (int i = 0; i < 26; i++) {
            report[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < research.length; i++) {
            String[] arr = research[i].split("");
            Arrays.sort(arr);
            String dayResearch = String.join("", arr);
            while (dayResearch.length() != 0) {
                char search = dayResearch.charAt(0);
                int count = (int) dayResearch.chars().filter(c -> c == search).count();
                if (count >= k) {
                    report[search - 'a'].add(count);
                    if (report[search - 'a'].size() == n) {
                        int sum = report[search - 'a'].stream().reduce(0, Integer::sum);
                        if (sum >= 2 * n * k) {
                            issue.add(String.valueOf(search));
                            report[search - 'a'].remove(0);
                        }
                    }
                } else {
                    report[i].removeAll(report[i]);
                }
                dayResearch = dayResearch.replaceAll(Character.toString(search), "");
                dayResearch.trim();
            }
        }
        Collections.sort(issue);
        String bestIssue = String.join("", issue);
        int max = 0;
        while (bestIssue.length() != 0) {
            char search = bestIssue.charAt(0);
            int count = (int) bestIssue.chars().filter(c -> c == search).count();
            if (count > max) {
                answer = String.valueOf(search);
                max = count;
            }
            bestIssue = bestIssue.replaceAll(String.valueOf(search), "");
            bestIssue.trim();
        }
        return answer;
    }

}
