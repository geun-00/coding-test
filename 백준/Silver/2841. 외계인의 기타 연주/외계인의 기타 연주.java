import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        Deque<Integer>[] stacks = new Deque[7];
        for (int i = 0; i <= 6; i++) {
            stacks[i] = new ArrayDeque<>();
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int line = Integer.parseInt(st.nextToken());
            int fret = Integer.parseInt(st.nextToken());

            Deque<Integer> stack = stacks[line];

            while (!stack.isEmpty() && fret < stack.peek()) {
                stack.pop();
                ans++;
            }

            if (stack.isEmpty() || stack.peek() != fret) {
                stack.push(fret);
                ans++;
            }
        }

        System.out.println(ans);
    }
}