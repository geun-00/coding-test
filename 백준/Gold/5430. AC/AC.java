import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder result = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); //테스트 케이스 개수

        while (t-- > 0) {
            String p = br.readLine(); //수행할 함수

            int n = Integer.parseInt(br.readLine()); //배열에 들어있는 수의 개수

            String s = br.readLine();

            Deque<Integer> deque = new ArrayDeque<>();

            if (n > 0) {
                String[] split = s.substring(1, s.length() - 1).split(",");

                for (String str : split) {
                    deque.offer(Integer.parseInt(str));
                }
            }

            boolean reverse = false;
            boolean error = false;

            for (char c : p.toCharArray()) {

                if (c == 'R') {
                    reverse = !reverse;
                } else if (c == 'D') {

                    if (deque.isEmpty()) {
                        error = true;
                        break;
                    }

                    if (reverse) {
                        deque.pollLast();
                    } else {
                        deque.pollFirst();
                    }
                }
            }

            if (error) {
                result.append("error").append("\n");
            } else {
                result.append("[");

                while (!deque.isEmpty()) {

                    if (reverse) {
                        result.append(deque.pollLast());
                    } else {
                        result.append(deque.pollFirst());
                    }

                    if (!deque.isEmpty()) {
                        result.append(",");
                    }
                }
                result.append("]").append("\n");
            }
        }

        System.out.print(result);
    }
}
