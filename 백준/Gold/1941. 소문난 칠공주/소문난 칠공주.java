import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 소문난 칠공주 */
public class Main {
    static char[][] map = new char[5][5];
    static boolean[] selected = new boolean[25]; // 선택 여부 저장
    static int answer = 0; // 조합 개수
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 선택 여부 저장, 재귀, 방문체크, 조합, 7 이상일 때 중단, S가 4 이상인지 확인, 연결성 확인(bfs)
        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 재귀
        combination(0, 0);
        // 출력
        System.out.println(answer);
    }

    public static void combination(int start, int depth) {
        if (depth == 7) {
            // todo: S가 4개 이상인지 확인, 연결성 체크
            check();
            return;
        }

        for (int i = start; i < 25; i++) {
            selected[i] = true;
            combination(i + 1, depth + 1);
            selected[i] = false;
        }
    }

    public static void check() {
        // 현재 생성된 조합에 S가 4개 이상 포함되었는지 확인
        int sCount = 0;
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            if (selected[i]) {
                int x = i / 5;
                int y = i % 5;
                list.add(new int[]{x, y});
                if (map[x][y] == 'S') sCount++;
            }
        }

        // 4보다 작으면 리턴
        if (sCount < 4) return;

        // 연결성 체크
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];

        q.add(list.get(0));
        visited[list.get(0)[0]][list.get(0)[1]] = true;

        // **주의** 1부터 시작!
        int count = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if (visited[nx][ny]) continue;

                // 인덱스 역산
                int idx = nx * 5 + ny;

                // 다음 방문지 큐에 넣기
                if (selected[idx]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    count++;
                }
            }
        }
        if (count == 7) answer++;
    }
}
