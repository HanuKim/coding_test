import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 사람 수
        int K = sc.nextInt(); // 제거 인덱스 
        
        // 배열에 남은 요소가 없을 때까지 K번째 요소를 빼와야 함 
        // 만약 배열의 길이보다 작을 때까지 K, 2K, 3K... 빼내고 이후에는 비어 있는 요소는 인덱스 제외하고 가져오도록 한다면? 
        // 조건이 복잡해짐 => K번째만 신경쓸 수 있도록 배열을 계속 갱신시키자 => K번째가 아닌 것은 뒤로 보내자 
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            // K-1보다 작은 인덱스까지는 리스트 뒤로 보내기 
            for (int i = 0; i < K - 1; i++) {
                queue.offer(queue.poll());
            }
            // K번째 인덱스 요소는 꺼내서 result에 넣기 
            result.add(queue.poll());
        }

        StringBuilder sb = new StringBuilder("<");
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            if (i < result.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(">");

        System.out.println(sb.toString());
    }
}
