import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 물건의 개수
            int K = Integer.parseInt(st.nextToken()); // 가방의 최대 부피(무게)

            int[] volumes = new int[N + 1];
            int[] values = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                volumes[i] = Integer.parseInt(st.nextToken()); // 물건의 부피 V
                values[i] = Integer.parseInt(st.nextToken());  // 물건의 가치 C
            }

            // dp[i][w] : 1번부터 i번까지의 물건을 고려했을 때, 가방의 부피가 w일 때의 최대 가치
            int[][] dp = new int[N + 1][K + 1];

            for (int i = 1; i <= N; i++) {
                int currentVolume = volumes[i];
                int currentValue = values[i];

                for (int w = 1; w <= K; w++) {
                    // 현재 검사 중인 물건의 부피가 가방의 허용 부피(w)보다 큰 경우 -> 넣을 수 없음
                    if (currentVolume > w) {
                        dp[i][w] = dp[i - 1][w]; // 이전까지의 최적값 그대로 가져옴
                    } 
                    // 현재 물건을 넣을 공간이 되는 경우 -> '넣지 않는 경우'와 '무조건 넣는 경우' 중 큰 값 선택
                    else {
                        dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - currentVolume] + currentValue);
                    }
                }
            }

            System.out.println("#" + tc + " " + dp[N][K]);
        }
    }
}