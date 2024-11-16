import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Queue<Integer> qu = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            qu.offer(i);
        }

        StringBuilder sb = new StringBuilder();

        while (!qu.isEmpty()) {
            sb.append(qu.poll()).append(" ");

            if (!qu.isEmpty()) {
                qu.offer(qu.poll());
            }
        }

        System.out.print(sb);
    }
}