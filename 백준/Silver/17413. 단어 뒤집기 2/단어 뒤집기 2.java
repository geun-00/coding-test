import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length();) {
            if (s.charAt(i) == '<') {

                while (s.charAt(i) != '>') {
                    sb.append(s.charAt(i++));
                }
                sb.append(s.charAt(i++));
            } else {

                while (i < s.length() && s.charAt(i) != ' ' && s.charAt(i) != '<') {
                    stack.push(s.charAt(i++));
                }

                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                if (i<s.length() && s.charAt(i) == ' ') {
                    sb.append(" ");
                    i++;
                }
            }
        }

        System.out.println(sb);
    }
}