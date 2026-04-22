import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] trees = new int[N];
			int max_h = 0; 
			for (int i = 0; i < N; i++) {
				trees[i] = sc.nextInt(); 
				max_h = Math.max(max_h, trees[i]); 
			}
			
			int odd = 0; 
			int even = 0; 
			for (int i = 0; i < N; i++) {
				int diff = max_h - trees[i];
				if (diff == 0) continue; 
				even += diff / 2; 
				odd += diff % 2; 
			}
			
			if (even > odd) {
				while (Math.abs(even - odd) > 1) {
					even--; 
					odd += 2; 
				}
			}
			
			int ans = 0; 
			if (even > odd) {
				ans = even * 2;
			} else if (even < odd) {
				ans = odd * 2 - 1; 
			} else {
				ans = odd + even; 
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
