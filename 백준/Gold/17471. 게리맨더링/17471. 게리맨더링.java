import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/* 게리맨더링 */
public class Main {
	static int N, minDiff; // 구역 개수, 최소 차
	static int[] populations; // 인구 수 
	static List<List<Integer>> adj; // 인접 구역 리스트 
	static boolean[] selected; // a구역 선택 여부 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		
		N = sc.nextInt(); 
		minDiff = Integer.MAX_VALUE; 
		populations = new int[N]; 
		adj = new ArrayList<>(); 
		selected = new boolean[N]; 
		
		for (int i = 0; i < N; i++) {
			populations[i] = sc.nextInt(); 
			adj.add(new ArrayList<>()); // 인접 리스트의 각 리스트 초기화 
		}
		
		for (int i = 0; i < N; i++) {
			int count = sc.nextInt(); 
			
			while (count-- > 0) {
				adj.get(i).add(sc.nextInt() - 1); 
			}
		}
		
		selected[0] = true;
		divide(1);
		
		System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
	}
	
	// 구역 나누기 (DFS)
	static void divide(int idx) {
		if (idx == N) {
			if (check()) {
				calc(); 
			}
			return; 
		}
		
		selected[idx] = true; 
		divide(idx + 1);
		selected[idx] = false; 
		divide(idx + 1); 
	}
	
	// 연결성 확인 (BFS)
	static boolean check() {
		// 두 구역을 a, b로 나누기 
		List<Integer> a = new ArrayList<>(); 
		List<Integer> b = new ArrayList<>(); 
		
		// selected = true 이면 a, 아니면 b 
		for (int i = 0; i < N; i++) {
			if (selected[i]) a.add(i); 
			else b.add(i); 
		}
		
		// a, b 둘 중 하나라도 빈 구역이면 무효 
		if (a.isEmpty() || b.isEmpty()) return false; 
		
		return isConnected(a) && isConnected(b); 
	}
	
	// 연결 유효성 확인 
	static boolean isConnected(List<Integer> team) {
		Queue<Integer> q = new LinkedList<>(); 
		boolean[] visited = new boolean[N]; 
		boolean[] inTeam = new boolean[N]; 
		
		for (int t : team) {
			inTeam[t] = true; 
		}
		
		q.offer(team.get(0)); 
		visited[team.get(0)] = true; 
		int count = 1; 
		
		while (!q.isEmpty()) {
			int cur = q.poll(); 
			
			for (int next : adj.get(cur)) {
				if (inTeam[next] && !visited[next]) {
					visited[next] = true; 
					q.offer(next); 
					count++; 
				}
			}
		}
		return count == team.size();
	}
	
	static void calc() {
		int sumA = 0, sumB = 0; 
		for (int i = 0; i < N; i++) {
			if (selected[i]) sumA += populations[i]; 
			else sumB += populations[i]; 
		}
		minDiff = Math.min(minDiff, Math.abs(sumA - sumB));
	}
}