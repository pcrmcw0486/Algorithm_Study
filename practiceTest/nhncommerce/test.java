package practiceTest.nhncommerce;

import java.util.*;

public class test {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        map.put(1, 2);
        map.put(2, 3);
        map.put(4, 5);
        map1.put(1, 2);
        map1.put(2, 3);
        map1.put(4, 5);
        // System.out.println(map.equals(map1));
        System.out.println("ICNSFOATLICNATLSFO".compareTo("ICNATLSFOATLSFO")); // 결국 포문도는거네..
        System.out.println("ICNATLSFOATLSFO".compareTo("ICNSFOATLICNATLSFO"));// 빠른 순으로.
        // 3만자 비교?
        // int b = solution("hit", "cog", new String[] { "hot", "dot", "dog", "lot",
        // "log", "cog" });
        // System.out.println(b);
        // String cur = "hit";
        // for (int i = 0; i < cur.length(); i++) {
        // String s = cur.substring(0, i) + "." + cur.substring(i + 1);
        // System.out.println(s);
        // System.out.println("hot".matches(s));
        // }

        String[] a = solution2(new String[][] { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" },
                { "ATL", "SFO" } });
        // for (String aa : a) {
        // System.out.println(aa);
        // }

    }

    // ----------------------------------------------------------------
    static int totalEdge = 0;
    static ArrayList<String> ans;

    public static String[] solution2(String[][] tickets) {
        ans = new ArrayList<String>();
        totalEdge = tickets.length;
        Map<String, ArrayList<String>> routes = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < tickets.length; i++) {
            String start = tickets[i][0];
            String end = tickets[i][1];
            if (!routes.containsKey(start)) {
                ArrayList<String> r = new ArrayList<String>();
                routes.put(start, r);
            }
            if (!routes.containsKey(end)) {
                ArrayList<String> r = new ArrayList<String>();
                routes.put(end, r);
            }
            routes.get(start).add(end);
        }
        dfs(routes, "ICN", 0, "ICN");
        System.out.println(ans.size());
        Collections.sort(ans);
        String[] answer = ans.get(0).split(" ");
        return answer;
    }

    public static void dfs(Map<String, ArrayList<String>> routes, String curPassPort, int edgeCount, String s) {
        // 모든 경로를 다 보았음.
        if (edgeCount == totalEdge) {
            ans.add(s);
        }
        // 경로를 다 보기 전에 갈 곳이 없는 경우
        ArrayList<String> tmpRoutes = routes.get(curPassPort);
        if (tmpRoutes.size() == 0)
            return;
        for (int i = 0; i < tmpRoutes.size(); i++) {
            String nextPassPort = tmpRoutes.get(i);
            System.out.println(edgeCount + " " + curPassPort + " " + nextPassPort);
            routes.get(curPassPort).remove(nextPassPort);
            dfs(routes, nextPassPort, edgeCount + 1, s + " " + nextPassPort);
            routes.get(curPassPort).add(i, nextPassPort);
        }
    }

    // -----------
    public static int solution(String begin, String target, String[] words) {
        int answer = 0;
        Set<String> wordsSet = new HashSet<String>();
        for (int i = 0; i < words.length; i++) {
            wordsSet.add(words[i]);
        }
        if (!wordsSet.contains(target))
            return 0;

        boolean success = false;
        Queue<String> queue = new LinkedList<String>();
        queue.add(begin);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                System.out.println("in queue :" + cur);
                if (cur.equals(target)) {
                    success = true;
                    break;
                }
                for (int j = 0; j < cur.length(); j++) {
                    String possible = cur.substring(0, j) + "." + cur.substring(j + 1);
                    for (String p : wordsSet) {
                        if (p.matches(possible)) {
                            queue.add(p);
                        }
                    }
                }
            }
            if (success)
                break;
            answer++;
        }

        return success ? answer : 0;
    }
}
