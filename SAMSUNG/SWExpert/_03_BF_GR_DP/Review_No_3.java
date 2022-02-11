package SAMSUNG.SWExpert._03_BF_GR_DP;

import java.util.Scanner;

public class Review_No_3 {
    static char[] data;
    static int depth;
    static int max;
    static int ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for(int tt =1;tt<=T;tt++){
            ans =0;
            sb.append('#').append(tt).append(' ');
            data = sc.next().toCharArray();
            depth = sc.nextInt();
            findBest(0,0);
           // sb.append(Integer.parseInt(new String(data))).append('\n');
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }
    public static void findBest(int pos, int cnt){
        //종료조건
        // 1. 남은 횟수가 없는 경우
        // 2. 남은 횟수가 있고, 끝에 도달한 경우(max를 찾음)

        //1. 남은 횟수가 없는 경우
        // 그대로 return

        if(cnt ==depth) {
            ans = Math.max(ans, Integer.parseInt(new String(data)));
            return;
        }

        //남은 횟수가 있고, 끝에 도달한 경우 즉, 지금이 가장 최대값.
        //이건 불변임. 무조건 가장 큰 값임.
        if(pos == data.length-1){
            ans = Integer.parseInt(new String(data));
            //(depth-cnt)%2 ==0 이면 다시 돌아올 수 있음.
            if((depth-cnt)%2 !=0){
                //한번만 바꾸었을 때 최대가 되는 값을 return해주어야함.
                //max값인상태임. 변화하지 않는 것이 가장 중요함.
                boolean flag = false;
                for(int i =0;i<data.length-1;i++){
                    if(data[i] == data[i+1]){
                        flag = true;
                        break;
                    }
                }
                //중복된 값이 없어 어떻게든 변화해야하는 경우.
                if(!flag){
                    swap(data.length - 1, data.length - 2);
                    ans = Integer.parseInt(new String(data));
                }
            }
            return;
        }
        //반복적으로 해야하는 일 (언제까지?)
        char max = data[pos];
        int maxIdx = pos;
        int maxValue = Integer.parseInt(new String(data));
        //자신보다 오른쪽에 있는 큰 수를 찾았음.
        for(int i =pos+1;i<data.length;i++){
            if(data[i] >= max){
                max = data[i];
            }
        }
        //큰 수에 대해 확인하는 과정을 거친다.
        if(max == data[pos]) findBest(pos+1,cnt);
        else{
            for(int i = pos +1; i<data.length;i++){
                if(data[i] == max){
                    swap(i,pos);
                    findBest(pos+1, cnt+1);
                    swap(i,pos);
                }
            }
        }
    }
    static void swap(int a, int b){
        char tmp = data[a];
        data[a] = data[b];
        data[b] = tmp;
    }
}
