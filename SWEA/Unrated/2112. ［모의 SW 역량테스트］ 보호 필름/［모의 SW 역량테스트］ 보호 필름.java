import java.util.Scanner;

public class Solution {
	static int D, W, K, ans;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();

			map = new int[D][W];
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			ans = K; // 투약 횟수의 최댓값은 K

			// K가 1이면 바로 통과
			if (K == 1 || check()) {
				System.out.println("#" + tc + " 0");
			} else {
				dfs(0, 0);
				System.out.println("#" + tc + " " + ans);
			}
		}
	}

	static void dfs(int row, int cnt) {
		// 1. 가지치기: 현재 투약 횟수가 이미 찾은 최솟값보다 크면 중단
		if (cnt >= ans)
			return;

		// 2. 기저 조건: 모든 행의 결정이 끝남
		if (row == D) {
			if (check()) {
				ans = Math.min(ans, cnt);
			}
			return;
		}

		// 3. 탐색 3가지
		// 1) 투여 안 함
		dfs(row + 1, cnt);

		// 원본 백업
		int[] backup = new int[W];
		for (int i = 0; i < W; i++) {
			backup[i] = map[row][i];
		}

		// 2) a 투여
		for (int i = 0; i < W; i++) {
			map[row][i] = 0;
		}
		dfs(row + 1, cnt + 1);

		// 3) b 투여
		for (int i = 0; i < W; i++) {
			map[row][i] = 1;
		}
		dfs(row + 1, cnt + 1);

		// 원상 복구
		for (int i = 0; i < W; i++) {
			map[row][i] = backup[i];
		}
	}

	static boolean check() {
		for (int i = 0; i < W; i++) {
			boolean pass = false;
			int count = 1;
			for (int j = 0; j < D - 1; j++) {
				if (map[j][i] == map[j + 1][i]) {
					count++;
				} else {
					count = 1;
				}

				if (count >= K) {
					pass = true;
					break;
				}
			}

			if (!pass)
				return false;

		}

		return true;
	}
}
