import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 숨바꼭질 */
public class Main {
    private static final int MAX_NUM = 100000;
    // 각 정점에서 뻗어 나갈 수 있는 갈래는 x-1, x+1, x*2
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()); // 현재 위치
        int k = Integer.parseInt(st.nextToken()); // 도달해야 하는 위치

        System.out.println(bfs(n, k));
    }

    private static int bfs(int current, int target) {
        if (current == target) return 0; // 현재 위치와 목표 위치가 같으면 0 반환

        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[MAX_NUM + 1]; // 방문 여부와 시간을 동시에 기록해보자.. => 0은 미방문한 위치

        queue.add(current);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            int[] nextSteps = {curr + 1, curr - 1, curr * 2};
            for (int next : nextSteps) {
                if (next >= 0 && next <= MAX_NUM && dist[next] == 0) {
                    if (next == target) return dist[curr] + 1; // 큐에 굳이 할당하지 않고 바로 dist + 1 해서 반환 

                    dist[next] = dist[curr] + 1;
                    queue.add(next);
                }
            }
        }
        return -1;
    }
}