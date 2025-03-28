import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        HashMap<Long, Integer> map = new HashMap<>();

        int max = 0;

        for (int i = 0; i < n; i++) {
            long s = Long.parseLong(br.readLine());

            map.put(s, map.getOrDefault(s, 0) + 1);

            max = Math.max(max, map.get(s));
        }

        PriorityQueue<Long> qu = new PriorityQueue<>();

        for (long s : map.keySet()) {
            if (map.get(s) == max) {
                qu.offer(s);
            }
        }

        System.out.println(qu.poll());
    }
}