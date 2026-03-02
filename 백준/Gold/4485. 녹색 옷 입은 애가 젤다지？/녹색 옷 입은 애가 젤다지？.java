import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 녹색 옷 입은 애가 젤다지? */
public class Main {
    static int N;
    static int[][] map, dist;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            map = new int[N][N];
            dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra();

            System.out.println("Problem " + tc++ + ": " + dist[N-1][N-1]);
        }
    }

    public static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);

        dist[0][0] = map[0][0];
        pq.offer(new int[]{0, 0, dist[0][0]});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];

            if (cost > dist[x][y]) continue;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                int nextCost = cost + map[nx][ny];

                if (dist[nx][ny] > nextCost) {
                    dist[nx][ny] = nextCost;
                    pq.offer(new int[]{nx, ny, nextCost});
                }
            }
        }
    }
}
