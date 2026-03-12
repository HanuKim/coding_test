import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] numbers;
	static int[] calcs = new int[4]; // +, -, x, % 개수
	static int[] position; // 연산자 위치

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers = new int[N];
		position = new int[N - 1];

		for (int i = 0; i < N; i++) {
			numbers[i] = sc.nextInt();
		}

		for (int i = 0; i < 4; i++) {
			calcs[i] = sc.nextInt();
		}

//		for (int i = 0; i < N-1; i++) {
//			position[i] = -1;
//		}
//		
		Arrays.fill(position, -1);

		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}

	private static void dfs(int cnt) {
		if (cnt == N - 1) {
			calculation();
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (calcs[i] == 0)
				continue;

			position[cnt] = i;
			calcs[i]--;

			dfs(cnt + 1);

			calcs[i]++;
			position[cnt] = -1;

		}
	}

	private static void calculation() {
		int result = numbers[0];
		
		for (int i = 0; i < N-1; i++) {
			int next = numbers[i+1];
			if (position[i] == 0) {
				result += next;
			}
			if (position[i] == 1) {
				result -= next;
			}
			if (position[i] == 2) {
				result *= next; 
			}
			
			if (position[i] == 3) {
				result /= next;
				
			}
		}
		if (result > max)
			max = result;
		if (result < min)
			min = result;
	}

}
