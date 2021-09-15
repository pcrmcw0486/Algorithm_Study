package tmp;

public class bracketTrans {
    public static void main(String[] args) {
        bracketTrans solution = new bracketTrans();
        System.out.println(solution.solution("()))((()"));
    }

    public String solution(String p) {
        String answer = makeString(p);
        return answer;
    }

    public String makeString(String p) {
        String u = "";
        String v = "";
        if (!isComplete(p)) {
            int sum = p.charAt(0) == '(' ? 1 : -1;
            for (int i = 1; i < p.length(); i++) {
                sum += p.charAt(i) == '(' ? 1 : -1;
                if (sum == 0) {
                    u = p.substring(0, i + 1);
                    v = p.substring(i + 1);
                    break;
                }
            }
            if (!isComplete(u)) {
                String ret = "(";
                String center = makeString(v);
                ret = ret.concat(center) + ")";
                u = u.substring(1, u.length() - 1);
                for (int i = 0; i < u.length(); i++) {
                    ret += String.valueOf(u.charAt(i) == '(' ? ")" : "(");
                }
                return ret;
            } else {
                return u + makeString(v);
            }
        } else {
            return p;
        }
    }

    public boolean isComplete(String p) {
        if (p.length() == 0)
            return true;
        int sum = p.charAt(0) == '(' ? 1 : -1;
        for (int i = 1; i < p.length(); i++) {
            if (sum < 0)
                return false;
            sum += p.charAt(i) == '(' ? 1 : -1;
        }
        if (sum == 0)
            return true;
        return false;
    }
}
