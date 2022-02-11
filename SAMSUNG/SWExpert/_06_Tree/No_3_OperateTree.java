package SAMSUNG.SWExpert._06_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_3_OperateTree {
    static Node[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        for(int tt = 1 ;tt<=10;tt++){
            ans.append('#').append(tt).append(' ');
            int N = Integer.parseInt(br.readLine());
            tree = new Node[N+1];
            for(int i =0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                int curNum = Integer.parseInt(st.nextToken());
                String value = st.nextToken();
                tree[curNum] = new Node(value);
                while (st.hasMoreTokens()) {
                    int childNum = Integer.parseInt(st.nextToken());
                    tree[curNum].addChild(childNum);
                }
            }
            ans.append((int)Math.floor(operateTree(1))).append('\n');
        }
        System.out.println(ans.toString());
    }

    public static double operateTree(int num){
        Node cur = tree[num];
        double leftValue = -1;
        double rightValue = -1;
        double ret =  -1;
        if(cur.left == -1 && cur.right == -1) return Double.parseDouble(cur.value);
        leftValue = operateTree(cur.left);
        rightValue = operateTree(cur.right);
        switch (cur.value){
            case "+":
                ret = leftValue + rightValue;
                break;
            case "-":
                ret = leftValue - rightValue;
                break;
            case "/":
                ret = leftValue/rightValue;
                break;
            case "*" :
                ret = leftValue*rightValue;
                break;
        }
        return ret;
    }

    static class Node{
        String value;
        int left;
        int right;
        Node(String value){
            this.value = value;
            left = -1;
            right =-1;
        }
        public void addChild(int childNum){
            if(left == -1) left = childNum;
            else right = childNum;
        }
    }
}
