package RHS_FC.class01_BruteForce;

/*
암호 만들기 Gold V
시간제한 2초
서로다른 L개의 소문자로 구성. 최소 한개의 모음(aeiou) 최소 두개의 자음
알파벳이 증가하는 순서로 배열되어 있음.
문자의 종류 C가지.
가능성있는 암호를 모두 구하라. 
기본적으로 최소 한개의 모음이 필요함.
ex) 4 6
a t c i s w  (a i / t c s w)
증가하는 순서
기본적으로 N개가 있을 때, N- 모음개수 > 1
a i / c s t w 
a 
ai
i

쉽게하면 모음 count 자음 count세서 모든 경우의 수를 구하여도
3<= L <= C <= 15 라서 15C8 < 10^6 이라 가능하긴 한데.
그렇게 하고 싶지 않음.
backtracking이라 가지를 치고 싶은데.

 */
import java.io.*;
import java.util.*;

public class Q1759 {
    static StringBuilder sb = new StringBuilder();
    static int L, C;
    static int[] selected;
    static char[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        String[] tokens = br.readLine().split(" ");
        data = new char[C + 1];
        selected = new int[L + 1];
        for (int i = 1; i <= C; i++) {
            data[i] = tokens[i - 1].charAt(0);
        }
        Arrays.sort(data, 1, C + 1);
        rec_func(1);
        System.out.print(sb.toString());
    }

    static void rec_func(int k) {
        if (k == L + 1) {
            int vowel = 0, consonant = 0;
            for (int i = 1; i < L + 1; i++) {
                if (isVowel(data[selected[i]]))
                    vowel++;
                else
                    consonant++;
            }
            if (vowel >= 1 && consonant >= 2) {
                for (int i = 1; i < L + 1; i++)
                    sb.append(data[selected[i]]);
                sb.append('\n');
            }
        } else {
            for (int cand = selected[k - 1] + 1; cand < C + 1; cand++) {
                selected[k] = cand;
                rec_func(k + 1);
                selected[k] = 0;
            }
        }
    }

    static boolean isVowel(char x) {
        return x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u';
    }
}

// tmp
/*
 * package public static void rec_func(int k, int idx) { if (k == N) { if
 * (mCount > 0 && jCount > 1) { for (int i = 0; i < selected.length; i++) {
 * sb.append(selected[i]).append(' '); } sb.append('\n'); }
 * 
 * } else if (M - idx >= N - k) { char a = data[idx]; if (a == 'a' || a == 'e'
 * || a == 'i' || a == 'o' || a == 'u') { mCount++; selected[k] = a; rec_func(k
 * + 1, idx + 1); selected[k] = ' '; mCount--; } else { jCount++; selected[k] =
 * a; rec_func(k + 1, idx + 1); selected[k] = ' '; jCount--; } rec_func(k, idx +
 * 1); } }
 */