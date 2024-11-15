import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> qu = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            qu.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        while (!qu.isEmpty()) {

            for (int i = 0; i < k - 1; i++) {
                qu.offer(qu.poll());
            }

            sb.append(qu.poll()).append(", ");
        }

        String s = sb.toString();
        s = s.substring(0, s.length() - 2);
        System.out.print(s + ">");
    }
}