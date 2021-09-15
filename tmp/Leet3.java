package tmp;

public class Leet3 {
    public static void main(String[] args) {
        Leet3 leet = new Leet3();
        System.out.println(leet.solution("dvdf"));
    }

    public int solution(String s) {
        StringBuilder sb = new StringBuilder();
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            String pattern = sb.toString();
            // int index = s.index
            for (int j = 0; j < pattern.length(); j++) {
                if (s.charAt(i) == pattern.charAt(j)) {
                    answer = Math.max(answer, sb.length());
                    sb = new StringBuilder(sb.substring(i + 1));
                }
            }
            sb.append(s.charAt(i));
        }
        answer = Math.max(answer, sb.length());
        return answer;
    }
}
