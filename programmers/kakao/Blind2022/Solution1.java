package programmers.kakao.Blind2022;

import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
* 22분
* */
public class Solution1 {

    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {
                "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"
        };
        int k = 2;
        int[] solve = solve(id_list, report, k);
        for (int x : solve) {
            System.out.println(x);
        }


    }


    private static int[] solve(String[] id_list, String[] report, int k) {
        StringTokenizer st;
        HashMap<String, Integer> reportStatus = new HashMap<String, Integer>();
        HashMap<String, HashSet<String>> reportHistory = new HashMap<String, HashSet<String>>();
        for (String user : id_list) {
            reportStatus.put(user, 0);
            reportHistory.put(user, new HashSet<String>());
        }
        for (String reportInfo : report) {
            st = new StringTokenizer(reportInfo);
            String user = st.nextToken();
            HashSet<String> userReportHistory = reportHistory.get(user);
            while (st.hasMoreTokens()) {
                String reportedUser = st.nextToken();
                if (!userReportHistory.contains(reportedUser)) {
                    userReportHistory.add(reportedUser);
                    reportStatus.put(reportedUser, reportStatus.getOrDefault(reportedUser, 0) + 1);
                }
            }
        }
        int[] mailCnt = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            String user = id_list[i];
            HashSet<String> userReportHistory = reportHistory.get(user);
            for (String reportedUser : userReportHistory) {
                if (reportStatus.get(reportedUser) >= k) {
                    mailCnt[i]++;
                }
            }
        }
        return mailCnt;
    }

    static class UserReport {
        String userName;
        HashSet<String> reportUser;
    }
}
