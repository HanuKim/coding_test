import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 가수의 수
        int M = Integer.parseInt(st.nextToken()); // 보조 PD의 수

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[N + 1];

        // 1. 그래프 구축 및 진입 차수 계산
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            if (count == 0) continue;

            int u = Integer.parseInt(st.nextToken());
            for (int j = 1; j < count; j++) {
                int v = Integer.parseInt(st.nextToken());
                adj.get(u).add(v);
                indegree[v]++;
                u = v; // 다음 관계를 위해 업데이트
            }
        }

        // 2. 위상 정렬 시작 (진입 차수가 0인 노드 큐에 삽입)
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            sb.append(curr).append("\n");
            count++;

            for (int next : adj.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        // 3. 결과 출력 (모든 가수를 방문했는지 확인)
        if (count == N) {
            System.out.print(sb);
        } else {
            System.out.println(0);
        }
    }
}