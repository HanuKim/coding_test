import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static class Node {
		int x, y, dist, used; 
		
		Node(int x, int y, int dist, int used) {
			this.x = x;
			this.y = y; 
			this.dist = dist;
			this.used = used; 
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			String line = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0'; 
			}
		}
		
		System.out.println(bfs()); 
	}
	
	static int bfs () {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(0, 0, 1, 0)); 
		visited[0][0][0] = true; 
		
		while (!q.isEmpty()) {
			Node cur = q.poll(); 
			
			if (cur.x == N - 1 && cur.y == M -1) {
				return cur.dist; 
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d]; 
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				// 1. 벽이 아닌 경우
                if (map[nx][ny] == 0) {
                    // 현재까지의 상태(used) 그대로 방문한 적이 없는지 체크
                    if (!visited[nx][ny][cur.used]) {
                        visited[nx][ny][cur.used] = true;
                        q.offer(new Node(nx, ny, cur.dist + 1, cur.used));
                    }
                } 
                // 2. 벽인 경우
                else {
                    // 아직 벽을 부순 적이 없고, 벽을 부순 상태로 해당 칸을 방문한 적이 없는지 체크
                    if (cur.used == 0 && !visited[nx][ny][1]) {
                        visited[nx][ny][1] = true;
                        q.offer(new Node(nx, ny, cur.dist + 1, 1));
                    }
                }
			}
		}
		return -1; 
	}
}
