package practiceTest.kakaent;

import java.util.*;

public class test {
    public static void main(String[] args) {
        List<String> addresses = new ArrayList<String>();
        addresses.add("000.012.234.23");
        addresses.add("666.666.23.23");
        addresses.add(".213.123.23.32");
        addresses.add("2001:0db8:0000:0000:0000:ff00:0042:8329");
        addresses.add("::1");
        addresses.add("::1");
        String[] k = "23.45.22.32.".split("[.]");
        String[] ks = "23:45:22:32:".split("[:]");
        String t = "ff0";
        System.out.println(t.matches("[0-9a-f]{3}"));
        System.out.println(k.length);
        System.out.println(ks.length);
        List<String> a = validateAddresses(addresses);
        for (String s : a) {
            System.out.println(s);
        }
    }

    public static List<String> validateAddresses(List<String> addresses) {
        List<String> answer = new LinkedList<String>();
        // Write your code here
        for (String s : addresses) {
            answer.add(checks(s));
        }
        return answer;
    }

    public static String checks(String s) {
        if (s.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$")) {
            return "IPV4형태"; // >> 여기서 이제 값 체크로 들어가야됨 만약 8이상이라면 0이 있는가.
        }
        if (s.substring(0, 3).equals("::"))
            return "IPV6형태";
        return "아직몰라";
    }

    public static String check(String s) {
        String[] part;
        int val = -1;
        boolean check = true;
        if ((part = s.split("[.]")).length == 4) {
            if (s.length() - s.replace(".", "").length() == 3) {
                for (String p : part) {
                    val = -1;
                    if ((val = isNumeric(p, 0)) != -1) {
                        if (val >= 0 && val < 256)
                            continue;
                        else {
                            check = false;
                            break;
                        }
                    } else {
                        check = false;
                        break;
                    }
                }
                if (check)
                    return "IPv4";
            }
            // ipv6
        } else {
            part = s.split("[:]");
            for (String p : part) {
                if ((val = isNumeric(p, 1)) == 0)
                    continue;
                else
                    check = false;
            }
            if (check)
                return "IPv6";
        }
        return "Neither";

    }

    public static int isNumeric(String s, int type) {
        int ret = -1;
        switch (type) {
        case 0: // ipv4
            try {
                ret = Integer.parseInt(s);
                if (ret > 7 && s.matches("(.*)0(.*)")) {
                    return -1;
                }
            } catch (Exception e) {
                return -1;
            }
            break;
        case 1:
            int len = s.length();
            if (s.matches("[0-9a-f]{" + len + "}"))
                return 0;
            else
                return 1;
        }
        return ret;
    }
}
