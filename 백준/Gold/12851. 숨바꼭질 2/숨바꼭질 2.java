import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 숨바꼭질2 */
public class Main {
    private static final int MAX_NUM = 100000;
    // 각 정점에서 뻗어 나갈 수 있는 갈래는 x-1, x+1, x*2
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()); // 현재 위치
        int k = Integer.parseInt(st.nextToken()); // 도달해야 하는 위치

        bfs(n, k);
    }

    private static void bfs(int current, int target) {
        if (current == target) {
            System.out.println(0);
            System.out.println(1);
            return;
        }; // 현재 위치와 목표 위치가 같으면 0, 1 반환

        int[] dist = new int[MAX_NUM + 1]; // 방문 여부와 시간을 동시에 기록해보자.. => 0은 미방문한 위치
        int minTime = Integer.MAX_VALUE;
        int count = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(current);
        dist[current] = 1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // 현재 시간이 최단 시간보다 크면 탐색 불필요
            if (dist[curr] > minTime) continue;

            int[] nextSteps = {curr + 1, curr - 1, curr * 2};
            for (int next : nextSteps) {
                if (next >= 0 && next <= MAX_NUM) {
                    if (next == target) {
                        int arrivalTime = dist[curr];
                        if (count == 0) { // 처음으로 도착했을 때
                            minTime = arrivalTime;
                            count++;
                        } else if (arrivalTime == minTime) { // 2회 이상 도착 시
                            count++;
                        }
                    }

                    // 아직 도달 못 했거나 똑같은 시간에 도착하는 경우 통과
                    if (dist[next] == 0 || dist[next] == dist[curr] + 1) {
                        dist[next] = dist[curr] + 1;
                        queue.add(next);
                    }
                }
            }
        }
        System.out.println(minTime);
        System.out.println(count);
    }
}