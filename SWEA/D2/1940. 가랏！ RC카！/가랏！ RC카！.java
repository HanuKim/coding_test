import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int num = sc.nextInt(); // 명령 개수
            int ans = 0; // 이동 거리
            int curVel = 0; // 현재 속도

            for (int i = 0; i < num; i++) {
                int dir = sc.nextInt(); // 0: 유지, 1: 가속, 2: 감속

                if (dir == 1) {
                    int vel = sc.nextInt();
                    curVel += vel; // 가속인 경우 현재 속도에 ++
                } else if (dir == 2) {
                    int vel = sc.nextInt();
                    curVel -= vel; // 감속인 경우 --

                    // 음수인 경우 예외 처리
                    if (curVel < 0) {
                        curVel = 0;
                    }
                } else {
                    // 0인 경우 속도 변화 없음
                }

                // 1초씩 움직이므로
                ans += curVel;
            }

            System.out.println("#" + tc + " " + ans);
        }
    }
}