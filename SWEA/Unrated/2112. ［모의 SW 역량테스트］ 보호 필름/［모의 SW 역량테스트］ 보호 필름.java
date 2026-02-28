import java.io.*;
import java.util.*;

public class Solution {

    static int D, W, K;
    static int[][] map;
    static int min;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[D][W];

            for(int i=0;i<D;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<W;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if(K == 1){
                System.out.println("#"+tc+" 0");
                continue;
            }

            min = Integer.MAX_VALUE;
            dfs(0,0);

            System.out.println("#"+tc+" "+min);
        }
    }

    static void dfs(int row, int cnt){

        if(cnt >= min) return;

        if(row == D){
            if(check())
                min = Math.min(min, cnt);
            return;
        }

        dfs(row+1, cnt);

        int[] backup = map[row].clone();

        Arrays.fill(map[row],0);
        dfs(row+1, cnt+1);

        Arrays.fill(map[row],1);
        dfs(row+1, cnt+1);

        map[row] = backup;
    }

    static boolean check(){

        for(int col=0; col<W; col++){

            int count = 1;
            boolean pass = false;

            for(int row=1; row<D; row++){

                if(map[row][col] == map[row-1][col])
                    count++;
                else
                    count = 1;

                if(count >= K){
                    pass = true;
                    break;
                }
            }

            if(!pass) return false;
        }

        return true;
    }
}