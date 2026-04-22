import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/* 벽 부수고 이동하기 */
public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static class Node {
        int x, y, k, dist;

        Node(int x, int y, int k, int dist) {
            super();
            this.x = x;
            this.y = y;
            this.k = k;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String s = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 0, 1));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == N -1 && cur.y == M-1) return cur.dist;

            // 정상 이동
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == 1) continue;
                if (visited[nx][ny][cur.k]) continue;
                visited[nx][ny][cur.k] = true;
                q.add(new Node(nx, ny, cur.k, cur.dist+1));
            }

            if (cur.k < 1) {
                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if (map[nx][ny] == 0) continue;
                    if (visited[nx][ny][cur.k + 1]) continue;
                    visited[nx][ny][cur.k + 1] = true;
                    q.add(new Node(nx, ny, cur.k + 1, cur.dist+1));
                }
            }
        }

        return -1;
    }
}