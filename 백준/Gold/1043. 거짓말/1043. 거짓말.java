import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ1043 {
        static int[] parent;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int N = sc.nextInt(); // 사람 수
            int M = sc.nextInt(); // 파티 수

            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) parent[i] = i;

            int truthCount = sc.nextInt();
            int[] truth = new int[truthCount];
            for (int i = 0; i < truthCount; i++) {
                truth[i] = sc.nextInt();
            }

            List<List<Integer>> parties = new ArrayList<>();

            // 파티 입력
            for (int i = 0; i < M; i++) {
                int cnt = sc.nextInt();
                List<Integer> party = new ArrayList<>();

                for (int j = 0; j < cnt; j++) {
                    party.add(sc.nextInt());
                }

                // 같은 파티 사람들 union
                for (int j = 1; j < party.size(); j++) {
                    union(party.get(0), party.get(j));
                }

                parties.add(party);
            }

            int answer = 0;

            // 각 파티 검사
            for (List<Integer> party : parties) {
                boolean canLie = true;

                for (int t : truth) {
                    for (int p : party) {
                        if (find(t) == find(p)) {
                            canLie = false;
                            break;
                        }
                    }
                    if (!canLie) break;
                }

                if (canLie) answer++;
            }

            System.out.println(answer);
        }

        static int find(int x) {
            if (parent[x] == x) return x;
            return parent[x] = find(parent[x]);
        }

        static void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa != pb) parent[pb] = pa;
        }
    }