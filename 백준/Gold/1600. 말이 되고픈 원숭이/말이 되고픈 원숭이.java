import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/* 말이 되고픈 원숭이 */
public class Main {
    static int K, N, M;
    static int[][] map;
    static boolean[][][] visited;

    // 원숭이 움직임
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    // 말 움직임
    static int[] hx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] hy = {1, -1, 2, -2, 2, -2, 1, -1};

    static class Node{
        int x, y, k, dist;
        Node(int x, int y, int k, int dist) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        M = sc.nextInt();
        N = sc.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M][K+1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        System.out.println(bfs());
    }

    private static int bfs(){
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == N-1 && cur.y == M-1) {
                return cur.dist;
            }

            // 원숭이 이동
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                // 범위 밖, 벽, 이미 방문
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == 1) continue;
                if (visited[nx][ny][cur.k]) continue;
                visited[nx][ny][cur.k] = true;
                q.add(new Node(nx, ny, cur.k, cur.dist + 1));
            }

            // 말 이동
            if (cur.k < K) {
                for (int d = 0; d < 8; d++) {
                    int nx = cur.x + hx[d];
                    int ny = cur.y + hy[d];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if (map[nx][ny] == 1) continue;
                    if (visited[nx][ny][cur.k+1]) continue;

                    visited[nx][ny][cur.k + 1] = true;
                    q.add(new Node(nx, ny, cur.k+1, cur.dist+1));
                }
            }
        }

        return -1;
    }
}