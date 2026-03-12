import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N, M, invisibleArea = Integer.MAX_VALUE;
	static int[][] map;
	static List<int[]> cameras;

	// 상1 하2 좌3 우4
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, -1, 0, 0 };
	static int[][][] dir = { {}, { { 1 }, { 2 }, { 3 }, { 4 } }, { { 3, 4 }, { 1, 2 } },
			{ { 1, 4 }, { 2, 4 }, { 2, 3 }, { 1, 3 } }, { { 1, 3, 4 }, { 1, 2, 3 }, { 1, 2, 4 }, { 2, 3, 4 } },
			{ { 1, 2, 3, 4 } } };

	private static int[][] copyMap(int[][] map) {
		int[][] tempMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			int[] row = map[i].clone();
			tempMap[i] = row;
		}
		return tempMap;
	}

	// 사각지대 체크 함수
	private static void check() {
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					total++;
			}
		}

		invisibleArea = Math.min(total, invisibleArea);
	}

	// 감시영역 색칠 함수
	private static void running(int x, int y, int type, int d) {
		int times = dir[type][d].length;
		for (int i = 0; i < times; i++) {
			int nx;
			int ny; 
			int direction = dir[type][d][i];
			nx = x + dx[direction];
			ny = y + dy[direction];
			while (true) {
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					break;
				if (map[nx][ny] == 6)
					break;
				map[nx][ny] = 7;
				nx = nx + dx[direction]; 
				ny = ny + dy[direction];
			}
		}
	}

	private static void dfs(int cnt) {
		if (cnt == cameras.size()) {
			check();
			return;
		}

		int[] cur = cameras.get(cnt);
		int x = cur[0];
		int y = cur[1];
		int type = cur[2];
		int times = dir[type].length;


		for (int i = 0; i < times; i++) {
			int[][] tempMap = copyMap(map);
			running(x, y, type, i);
			dfs(cnt + 1);
			map = tempMap;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		cameras = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] != 0 && map[i][j] != 6)
					cameras.add(new int[] { i, j, map[i][j] });
			}
		}
		
		dfs(0);
		System.out.println(invisibleArea);
	}
}
