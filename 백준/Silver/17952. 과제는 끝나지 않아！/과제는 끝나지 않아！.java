import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 총 몇 분인지
		Stack<int[]> s = new Stack<>(); 
		int score = 0; 
		
		for (int i = 0; i < N; i++) {
			int type = sc.nextInt(); 
			
			if (type == 1) {
				int A = sc.nextInt(); // 점수 
				int T = sc.nextInt(); // 걸리는 시간 
				s.push(new int[] {A, T}); 
			}
			
			if (!s.isEmpty()) {
				int[] cur = s.peek(); 
				cur[1]--;
				if (cur[1] == 0) {
					score += cur[0]; 
					s.pop(); 
				}
			}
		}
		System.out.println(score);
	}
}
