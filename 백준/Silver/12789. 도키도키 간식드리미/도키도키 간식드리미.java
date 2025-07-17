import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> stack = new ArrayDeque<>();

        int order = 1;
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() == order) {
                stack.pop();
                order++;
            }
            stack.push(Integer.parseInt(st.nextToken()));
        }

        while (!stack.isEmpty() && stack.peek() == order) {
            stack.pop();
            order++;
        }

        System.out.println(stack.isEmpty() ? "Nice" : "Sad");
    }
}