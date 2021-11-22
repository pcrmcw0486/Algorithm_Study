package practiceTest.kakaent;

public class test2 {
    public static void main(String[] args) {
        long n = 13;
        long a = minOperation(n);
        System.out.println(a);
        // long n = 13 ^
        // System.out.println(13 ^);
    }

    public static long minOperation(long n) {
        long cnt = 0;
        String prev = Long.toBinaryString(n);
        char[] data = prev.toCharArray();
        while (Long.parseLong(prev, 2) != 0) {
            for (int i = 0; i < data.length - 1; i++) {
                if (data[i + 1] == '1') {
                    if ((i + 1 == data.length - 1) || (Long.parseLong(prev.substring(i + 2)) == 0)) {
                        data[i] = data[i] == '0' ? '1' : '0';
                        cnt++;
                        break;
                    }
                }
            }
            if (Long.parseLong(new String(data), 2) == 0)
                break;
            data[data.length - 1] = data[data.length - 1] == '0' ? '1' : '0';
            cnt++;
            prev = new String(data);
        }
        return cnt;
    }

}
