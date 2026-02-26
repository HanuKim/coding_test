import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 암호 만들기 */
// 알파벳 소문자, 최소 한 개의 모음(a, e, i, o, u) / 최소 두 개의 자음 => 알파벳 순대로
// 1. 배열 입력받아 sorting
// 2. a부터 차례로 L 크기의 조합 생성
// 3. 최소 한 개의 모음, 두 개의 자음 포함 여부 판단
public class Main {
    static int L, C; // L: 암호 길이, C: 전체 문자 개수
    static char[] arr; // 입력 받는 문자 배열
    static char[] result; // 생성된 암호
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        result = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        combination(0, 0);
    }

    // 부분 조합 생성
    public static void combination(int start, int depth) {
        if (depth == L) {
            check();
            return;
        }

        for (int i = start; i < C; i++) {
            result[depth] = arr[i];
            combination(i + 1, depth + 1);
        }
    }

    // 자음, 모음 확인
    public static void check() {
        int vowel = 0;
        int consonant = 0;

        for (char c : result) {
            if ("aeiou".indexOf(c) >= 0) vowel++;
            else consonant++;
        }

        if (vowel >= 1 && consonant >= 2) {
            System.out.println(new String(result));
        }
    }
}
