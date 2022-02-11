package SAMSUNG.SWExpert._08Hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N*M 크기의 흑백 그림을 따라 그리며 실력을 쌓고 있다.
 * 꿈을 꾸었다 H*W 크기 흑백그림을 보았음.
 * 다음날, 선생님의 그림 안에서 꿈에서 본 그림이 몇번 등장하는지 궁금하였다.
 * 그림정보가 주어질 때 그림이 등장하는 횟수를 계산해라.
 * 내가 아는건 한 줄에서 어디에 등장하는지를 찾을 수 있음.
 * 2000* 2000
 * */
public class No_3_EungiHobby {
    static int H, W, N, M;
    static final int MAXN = 2000;
    static int[][] dreamPic = new int[MAXN][MAXN];
    static int[][] realPic = new int[MAXN][MAXN];
    static int[][] realHash = new int[MAXN][MAXN];
    static int[][] tmp = new int[MAXN][MAXN];
//    static int[][] dreamPic;
//    static int[][] realPic;
//    static int[][] realHash;
//    static int[][] tmp;
    static final int HASH_SIZE = (1<<30);
    static final int  DIV = HASH_SIZE-1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            sb.append('#').append(tt).append(' ');
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
//             dreamPic = new int[MAXN][MAXN];
//             realPic = new int[MAXN][MAXN];
//             realHash = new int[MAXN][MAXN];
//             tmp = new int[MAXN][MAXN];
            getInfo(br, H, W, dreamPic);
            getInfo(br, N, M, realPic);

            //GetHash From dreamArt
            for(int i =0;i<H;i++) tmp[0][i] = getHash(dreamPic[i],W,4);
            int myHash = getHash(tmp[0],H,5);

            //Get realArt Hash
            int mulC = calcMul(W,4);
            int mulR = calcMul(H,5);

            // [0] [row]별 가로 hash값 모두 구하기.
            for(int i =0;i<N;i++){
                tmp[0][i] = getHash(realPic[i],W,4);
                for(int j= 1;j<M-W+1;j++){
                 tmp[j][i] = getNext(tmp[j-1][i],realPic[i][j-1],mulC,realPic[i][j+W-1],4);
                }
            }

            for(int i =0;i<M-W+1;i++){
                realHash[0][i] = getHash(tmp[i],H,5);
                for(int j =1;j<N-H+1;j++){
                    realHash[j][i] = getNext(realHash[j - 1][i], tmp[i][j - 1], mulR, tmp[i][j + H- 1], 5);
                }
            }
            //compare
            int cnt =0;
            for(int i =0;i<N-H+1;i++){
                for(int j=0;j<M-W+1;j++){
                    if(realHash[i][j] == myHash) cnt++;
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }

    private static int getNext(int prev, int sub, int mul, int add, int shift) {
        long hash = prev-(sub*mul);
        hash = (hash<<shift)+hash+add;
        return (int)(hash&DIV);
    }

    //int 다빼고 그냥 long으로 음수되도 되도록 그냥 해도 될거같은데;
    //라빈카프에서 마지막 값을 빼줄 power와 동일함.
    private static int calcMul(int num, int shift) {
        long rev = 1;
        for(int i= 1;i<num;i++){
            rev = (rev<<shift)+rev;
        }
        return (int)(rev&DIV);
    }

    //해시함수 (가로 한번, 새로 한번)
    private static int getHash(int[] piv, int num, int shift){
        long hash =0;
        for(int i =0;i<num;i++){
            //shift = 4라면 33배
            //shift = 5라면 65배
            hash = (hash<<shift) + hash + piv[i];
        }
        //MOD연산과 같음.
        return (int)(hash&DIV);
    }
    private static void getInfo(BufferedReader br, int n2, int m2, int[][] pic) throws IOException {
        for(int n = 0; n< n2; n++){
            String line = br.readLine();
            for(int m = 0; m< m2; m++){
                if (line.charAt(m) == 'o') {
                    pic[n][m] = 1;
                }else{
                    pic[n][m] = 0;
                }
            }
        }
    }
}


