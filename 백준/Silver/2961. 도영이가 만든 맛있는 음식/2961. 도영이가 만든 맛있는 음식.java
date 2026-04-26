import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, diff = Integer.MAX_VALUE; // diff : 신맛과 쓴맛의 차이 
	public static int[] x, y;
	public static void main(String[] args) throws IOException {
		// 신맛 = 사용 재료 신맛의 곱 
		// 쓴맛 = 사용 재료 쓴맛의 합
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		
		x = new int[N]; // 신맛 배열
		y = new int[N]; // 쓴맛 배열 
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 1, 0, false); // ** 곱은 1부터 시작 
		System.out.print(diff);
	}

	public static void dfs(int cnt, int a, int b, boolean isSelected) {
		if (cnt == N) {
			if (isSelected) { // 선택된 경우에는 a-b의 절댓값과 diff 중 최솟값 선택 
				diff = Math.min(diff, Math.abs(a-b));
			}
			return; 
		}
		
	
		dfs(cnt+1, a*x[cnt], b+y[cnt], true);
		
		dfs(cnt+1, a, b, isSelected);
	}
}
