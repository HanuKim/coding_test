import java.util.Scanner;

/* 미로1 */ 
public class Solution {
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	private static int[][] map; 
	private static int result; 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10; 
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); 
			map = new int[16][16]; 
			int[] start = new int[2]; 
			int[] end = new int[2]; 
			result = 0; 
			for (int i = 0; i < 16; i++) {
				String line = sc.next();
				for (int j = 0; j < 16; j++) {
					map[i][j] = line.charAt(j) - '0';
					if (map[i][j] == 2) {
						start[0] = i; 
						start[1] = j; 
					}
					if (map[i][j] == 3) {
						end[0] = i;
						end[1] = j; 
					}
				}
			}
			
			dfs(start[0], start[1]);

			System.out.println("#" + tc + " " + result);
		}
	}
	
	private static void dfs(int x, int y) {
		if (result == 1) return;
		if (map[x][y] == 3) {
			result = 1; 
			return; 
		} 
		if (map[x][y] == 0 || map[x][y] == 2) {
			map[x][y] = 4; 
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d]; 
				int ny = y + dy[d]; 
				if (nx < 0 || ny < 0 || nx >= 16 || ny >= 16) continue;  
				if (map[nx][ny] == 4 || map[nx][ny] == 1) continue; 
				dfs(nx, ny);
			}
		} 
		return; 
	}
}
