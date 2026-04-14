import java.util.*;
import java.io.*;

public class Main {
    static int N, minDiff = Integer.MAX_VALUE;
    static int[] populations;
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        populations = new int[N];
        adj = new ArrayList[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            while(count-- > 0) adj[i].add(Integer.parseInt(st.nextToken()) - 1);
        }

        // 비트마스킹을 통한 부분 집합 생성 (1 ~ 2^N - 2 까지)
        // 0과 2^N-1은 한 팀에 몰빵되는 경우이므로 제외
        for (int i = 1; i < (1 << N) - 1; i++) {
            if (isValid(i)) {
                int sumA = 0, sumB = 0;
                for (int j = 0; j < N; j++) {
                    if ((i & (1 << j)) != 0) sumA += populations[j];
                    else sumB += populations[j];
                }
                minDiff = Math.min(minDiff, Math.abs(sumA - sumB));
            }
        }
        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    static boolean isValid(int bit) {
        List<Integer> teamA = new ArrayList<>(), teamB = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if ((bit & (1 << i)) != 0) teamA.add(i);
            else teamB.add(i);
        }
        return isConnected(teamA) && isConnected(teamB);
    }

    static boolean isConnected(List<Integer> team) {
        if (team.isEmpty()) return false;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];
        q.offer(team.get(0));
        visited[team.get(0)] = true;
        int count = 1;

        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next : adj[curr]) {
                if (team.contains(next) && !visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    count++;
                }
            }
        }
        return count == team.size();
    }
}