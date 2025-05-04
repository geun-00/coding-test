import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String init = br.readLine();

        Deque<Character> left = new ArrayDeque<>();
        Deque<Character> right = new ArrayDeque<>();

        for (char c : init.toCharArray()) {
            left.push(c);
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if ("L".equals(command)) {
                if (!left.isEmpty()) {
                    right.push(left.pop());
                }
            }
            else if ("D".equals(command)) {
                if (!right.isEmpty()) {
                    left.push(right.pop());
                }
            }
            else if ("B".equals(command)) {
                if (!left.isEmpty()) {
                    left.pop();
                }
            }
            else if ("P".equals(command)) {
                left.push(st.nextToken().charAt(0));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : left) sb.append(c);

        StringBuilder reversed = sb.reverse();
        for (Character c : right) reversed.append(c);

        System.out.print(reversed);
    }
}