import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 일할 수 있는 날
		long N = sc.nextInt();
		// 준수가 일할 수 있는 날 
		long M = sc.nextInt();
		
		// 누적합 
		long[] arr = new long[Math.toIntExact(N + 1)];
		arr[0] = 0; 
		for (int i=1; i<=N; i++) {
			arr[i] = arr[i-1] + sc.nextInt();
		}
		
		// 최대 이익 
		long sum = 0;
		long result = Integer.MIN_VALUE;
		for (int i=1; i <= N - M + 1; i++) {
			long j = i + M -1;
			sum = arr[Math.toIntExact(j)] - arr[i-1];
			result = Math.max(result, sum);
		}
		System.out.println(result);
		
		sc.close();
	}
}
