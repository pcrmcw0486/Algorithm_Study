package SolveByCategory.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Q14888 {
    static int[] operate;
    static int[] operand;
    static int count;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        operate = new int[4];
        operand = new int[N];
        count = N-1;
        operand = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        operate = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        solution(0,operand[0]);
        System.out.println(max + "\n" + min);
    }

    public static void solution(int progress, int result){
        if(progress == count){
            max = Math.max(result, max);
            min = Math.min(result, min);
            return;
        }
        int a = result;
        int b = operand[progress+1];
        for(int i =0;i<4;i++){
            if(isPossible(i)){
                operate[i]--;
                switch(i){
                    case 0 :
                        solution(progress +1, a+b);
                        break;
                    case 1:
                        solution(progress + 1, a-b);
                        break;
                    case 2:
                        solution(progress + 1, a*b);
                        break;
                    case 3:
                        int temp = a/b;
                        if(a<0){
                            temp = -a;
                            temp = (int)(temp/b);
                            temp *= -1;
                        }
                        solution(progress +1, temp);
                        break;
                }
                operate[i]++;
            }   
        }
    }

    public static boolean isPossible(int i) {
        if(operate[i] != 0)
            return true;
        return false;
    }
}
