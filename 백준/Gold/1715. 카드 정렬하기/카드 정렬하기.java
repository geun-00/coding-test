import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> qu = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            qu.offer(Integer.parseInt(br.readLine()));
        }

        int result = 0;

        while (qu.size() > 1) {
            int card1 = qu.poll();
            int card2 = qu.poll();

            int sum = card1 + card2;
            result += sum;

            qu.offer(sum);
        }

        System.out.println(result);
    }
}
