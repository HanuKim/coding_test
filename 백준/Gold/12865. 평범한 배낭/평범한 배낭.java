import java.util.Scanner;

/* 평범한 배낭 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		int n = sc.nextInt(); 
		int k = sc.nextInt(); 
		int[] weights = new int[n+1]; 
		int[] values = new int[n+1]; 
		for (int i = 1; i <= n; i++) {
			weights[i] = sc.nextInt();
			values[i] = sc.nextInt(); 
		}
		
		int[][] dp = new int[n+1][k+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if (j - weights[i] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		
		System.out.println(dp[n][k]);
	}
}