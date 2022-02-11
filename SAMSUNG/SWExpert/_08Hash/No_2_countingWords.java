package SAMSUNG.SWExpert._08Hash;

import java.util.Scanner;

public class No_2_countingWords {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for (int tt = 1; tt <= T; tt++) {
            sb.append('#').append(tt).append(' ');
            char[] text = sc.next().toCharArray();
            char[] pattern = sc.next().toCharArray();
            int[] failure = preProcessingKMP(pattern);
            int cnt =0;
            for (int j = 0, i = 0; i < text.length; i++) {
                while (j > 0 && text[i] != pattern[j]) {
                    j = failure[j-1];
                }
                if(text[i] == pattern[j]){
                    if(j==pattern.length-1){
                       cnt++;
                       j = failure[j];
                    }else{
                        //다음 검색.
                        j++;
                    }
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }
    public static int[] preProcessingKMP(char[] text){
        int[] failure = new int[text.length];
        for (int j = 0, i = 1; i < text.length; i++) {
            while(j > 0 && text[i] != text[j]) {
                j = failure[j-1];
            }
            if(text[i] == text[j]){
                failure[i] = ++j;
            }
        }
        return failure;
    }

}
