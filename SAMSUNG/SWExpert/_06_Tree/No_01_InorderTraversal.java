package SAMSUNG.SWExpert._06_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No_01_InorderTraversal {
    static int N;
    static Node[] tree;
    static StringBuilder res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = 10;
        for (int tt = 1; tt <= 10; tt++) {
            sb.append('#').append(tt).append(' ');
            res = new StringBuilder();
            N = Integer.parseInt(br.readLine());
            tree = new Node[N+1];
            for(int i =1;i<N+1;i++){
                st = new StringTokenizer(br.readLine());
                int curNode = Integer.parseInt(st.nextToken());
                char curValue = st.nextToken().charAt(0);
                tree[curNode] = new Node(curValue);
                while(st.hasMoreTokens()){
                    tree[curNode].addChild(Integer.parseInt(st.nextToken()));
                }
            }
            inorderTraversal(1);
            sb.append(res).append('\n');
        }
        System.out.println(sb.toString());
    }
    public static void inorderTraversal(int num){
        Node cur = tree[num];
        //left
        if(cur.left != -1) inorderTraversal(cur.left);
        res.append(tree[num].value);
        //right
        if(cur.right != -1) inorderTraversal(cur.right);
    }
    static class Node{
        char value;
        int left;
        int right;
        Node(char value){
            this.value = value;
            left =-1;
            right = -1;
        }
        void addChild(int child){
            if(left == -1)
                left = child;
            else
                right = child;
        }
    }

}
