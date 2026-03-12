import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* ABCDE */
public class Main {
	// 모두가 이어져 있는 관계?
	// 어떤 정점에서 N개만큼의 점을 찍을 수 있어야 함
	static int N, M;
	static List<List<Integer>> graph;
	static boolean[] visited;
	static boolean exist= false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		graph = new ArrayList<>();
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}

		int cnt = 0;
		while (cnt != M) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph.get(a).add(b);
			graph.get(b).add(a);
			cnt++;
		}

		for (int i = 0; i < N; i++) {
			dfs(i, 0);
			if (exist)
				break;
		}

		if (exist)
			System.out.println(1);
		else
			System.out.println(0);
	}

	public static void dfs(int v, int depth) {
		if (depth == 4) {
			exist = true;
			return;
		}

		visited[v] = true;

		for (int next : graph.get(v)) {
			if (!visited[next]) {
				dfs(next, depth + 1);
			}
			
			if(exist) return;

		}
		visited[v] = false;
	}
}
