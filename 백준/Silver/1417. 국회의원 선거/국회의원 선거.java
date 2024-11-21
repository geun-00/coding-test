import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());

        int dasom = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (i == 0) {
                dasom = num;
            } else {
                qu.offer(num);
            }
        }

        if (n == 1) {
            System.out.println(0);
            return;
        }

        int count = 0;

        while (!qu.isEmpty() && dasom <= qu.peek()) {
            count++;
            dasom++;
            qu.offer(qu.poll() - 1);
        }

        System.out.println(count);
    }
}