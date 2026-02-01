import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        Scanner sc = new Scanner(System.in);
		for(int test_case = 1; test_case <= 10; test_case++)
		{
            int tc = sc.nextInt(); 
            // 선입선출이니까 큐.. ArrayDeque 사용
            // Integer 타입을 담는 ArrayDeque 생성
            Deque<Integer> deque = new ArrayDeque<>();

            for (int i = 0; i < 8; i++) {
                deque.addLast(sc.nextInt());
            }

            // 종료 여부 판단 플래그 변수
            boolean end = false;

            while (!end) {
                for (int i = 1; i <= 5; i++) {
                   // 덱이 확정적으로 Null이 아니므로 pollFirst 대신 removeFirst 사용
                   int num = deque.removeFirst();
                   num -= i;

                   // num이 0보다 작거나 같으면 맨 마지막에 0 넣고 종료
                   if (num <= 0) {
                       deque.addLast(0);
                       end = true;
                       break;
                   } else {
                       deque.addLast(num);
                   }
                }
            }
            System.out.print("#" + tc + " ");
            for (int x : deque) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
	}
}