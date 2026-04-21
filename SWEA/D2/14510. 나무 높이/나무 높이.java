import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[] trees = new int[N];
            int maxH = 0;
            for (int i = 0; i < N; i++) {
                trees[i] = sc.nextInt();
                maxH = Math.max(maxH, trees[i]);
            }

            int odd = 0, even = 0;
            for (int i = 0; i < N; i++) {
                int diff = maxH - trees[i];
                if (diff == 0) continue;
                even += diff / 2;
                odd += diff % 2;
            }

            // 짝수(2)를 홀수(1+1)로 변환하여 밸런스 조절
            if (even > odd) {
                while (Math.abs(even - odd) > 1) {
                    even--;
                    odd += 2;
                }
            }

            int ans = 0;
            if (odd > even) {
                ans = odd * 2 - 1;
            } else if (even > odd) {
                ans = even * 2;
            } else {
                ans = odd + even;
            }
            System.out.println("#" + tc + " " + ans);
        }
    }
}