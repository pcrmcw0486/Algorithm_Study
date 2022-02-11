package SAMSUNG.SWExpert._04DC_BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/*
이름이 짧을수록 앞에 있고, 같은 길이면 사전순으로 앞에 있다.
* */
public class No_1_NamingSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        TreeSet<String> names;
        StringBuilder sb= new StringBuilder();
        for(int test_case =1;test_case<=T;test_case++){
            sb.append('#').append(test_case).append('\n');
            names = new TreeSet<String>((o1, o2) -> {
                if(o1.length() == o2.length()){
                    return o1.compareTo(o2);
                }
                return o1.length() - o2.length();
            });
            int N = Integer.parseInt(br.readLine());
            int idx =0 ;
            for(idx =0;idx<N;idx++){
                names.add(br.readLine());
            }
            for(String s : names){
                sb.append(s).append('\n');
            }
        }
        System.out.print(sb.toString());
    }

}
