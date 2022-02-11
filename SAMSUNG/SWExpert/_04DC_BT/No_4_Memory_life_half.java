    package SAMSUNG.SWExpert._04DC_BT;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.StringTokenizer;

    public class No_4_Memory_life_half {
        static int[] data;
        static int[] group;
        static int max;
        static int N,K;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            StringTokenizer st;
            int T = Integer.parseInt(br.readLine());
            for (int test_case = 1; test_case <= T; test_case++) {
                sb.append('#').append(test_case).append(' ');
                st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                K = Integer.parseInt(st.nextToken());
                data = new int[N];
                st = new StringTokenizer(br.readLine());
                max = -1;
                for(int i =0;i<N;i++){
                    data[i] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, data[i]);
                }
                group = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                sb.append(findByBinarySearch()).append('\n');
            }
            System.out.print(sb);
        }
        public static int findByBinarySearch(){
            int left =0;
            int right = max;
            int pos = -1;
            int mid;
            while(left<=right){
                mid = (left+right)>>1;
                int[] cnt = countBlock(mid);
                int groupPointer =0;
                for(int i =0;i<cnt.length;i++){
                    while(groupPointer <group.length){
                        if(cnt[i] < group[groupPointer]) break;
                        cnt[i] -= group[groupPointer++];
                    }
                }
                //이 크기로 되네? 그러면 mid보다 작게 해볼까.
                //만족하는 가장 작은 크기이니까.
                if(groupPointer == group.length){
                    pos = mid;
                    right = mid -1;
                }else{
                    left = mid +1;
                }
            }
            return pos;

        }
        public static int[] countBlock(int maxLevel){
            ArrayList<Integer> blocks = new ArrayList<>();
            int idx =0;

            while (idx != N) {
                if (data[idx] > maxLevel) {
                    idx++;
                    continue;
                }
                int cnt = 0;
                while (data[idx] <= maxLevel) {
                    cnt++;
                    idx++;
                    if (idx == N) break;
                }
                blocks.add(cnt);

            }
            int[] ret = new int[blocks.size()];
            for(int i =0;i<ret.length;i++){
                ret[i] = blocks.get(i);
            }
            return ret;
        }
    }
