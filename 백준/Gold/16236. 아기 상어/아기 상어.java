import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 아기 상어 */
/* 2 ≤ N ≤ 20, 제한 시간 2초
 * 아기 상어 위치 : 9, 처음 크기 : 2
 * 본인보다 몸집 작은 물고기를 먹을 수 있고 몸집이 작거나 같은 물고기 칸만 지날 수 있음
 * 우선순위: 1. 가장 가까운 물고기, 2. 가장 위에 있는 물고기, 3. 가장 왼쪽에 있는 물고기 => bfs
 * 자신의 몸집과 같은 수의 물고기를 먹어야 1 커짐
 */
public class Main {
    private static int N;
    private static int[][] map; // 물고기 위치 배열
    private static int sharkX, sharkY; // 상어 위치
    private static int sharkSize = 2; // 상어 사이즈
    private static int eatCnt = 0; // 먹은 물고기 수 => size와 같아질 때까지 추적해야 함
    private static int time = 0;

    // 상좌우하
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    static class Fish {
        int x, y, dist;
        Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0; // 시작 위치 빈칸 처리
                }
            }
        }

        while (true) {
            Fish target = bfs();
            if (target == null) break; // 더 이상 먹을 물고기 없으면 종료

            // 이동
            time += target.dist;
            sharkX = target.x;
            sharkY = target.y;

            // 물고기 먹기
            map[sharkX][sharkY] = 0;
            eatCnt++;

            // 크기 증가 체크
            if (eatCnt == sharkSize) {
                sharkSize++;
                eatCnt = 0;
            }
        }

        System.out.println(time);
    }


    // 먹을 수 있는 가장 가까운 물고기 찾기
    static Fish bfs() {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{sharkX, sharkY, 0});
        visited[sharkX][sharkY] = true;

        List<Fish> candidates = new ArrayList<>();

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] > sharkSize) continue; // 본인 사이즈 보다 큰 숫자는 못 지나감
                visited[nx][ny] = true;

                q.add(new int[]{nx, ny, dist + 1});

                if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
                    candidates.add(new Fish(nx, ny, dist + 1));
                }
            }
        }

        // 먹을 물고기가 더 이상 없음
        if (candidates.isEmpty()) return null;

        // 우선순위 정렬
        Collections.sort(candidates, (a, b) -> {
            if (a.dist != b.dist) return a.dist - b.dist; // 거리
            if (a.x != b.x) return a.x - b.x;             // 위쪽
            return a.y - b.y;                             // 왼쪽
        });

        return candidates.get(0);
    }
}