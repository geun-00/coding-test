import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            PriorityQueue<Integer> minPQ = new PriorityQueue<>();
            PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer, Integer> map = new HashMap<>();

            int k = Integer.parseInt(br.readLine());

            for (int j = 0; j < k; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String oper = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if ("D".equals(oper)) {
                    if (!minPQ.isEmpty() && !maxPQ.isEmpty()) {
                        if (n == 1) {
                            delete(maxPQ, map);
                        } else {
                            delete(minPQ, map);
                        }
                    }
                } else {
                    minPQ.offer(n);
                    maxPQ.offer(n);
                    map.put(n, map.getOrDefault(n, 0) + 1);
                }
            }

            postProcess(minPQ, map);
            postProcess(maxPQ, map);

            if (minPQ.isEmpty() || maxPQ.isEmpty()) {
                sb.append("EMPTY");
            } else {
                sb.append(maxPQ.peek()).append(" ").append(minPQ.peek());
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void postProcess(PriorityQueue<Integer> pq, Map<Integer, Integer> map) {
        while (!pq.isEmpty()) {
            int value = pq.peek();
            if (map.getOrDefault(value, 0) == 0) {
                pq.poll();
            } else {
                break;
            }
        }
    }

    private static void delete(PriorityQueue<Integer> pq, Map<Integer, Integer> map) {
        while (!pq.isEmpty()) {
            int value = pq.peek();
            if (map.getOrDefault(value, 0) == 0) {
                pq.poll();
            } else {
                map.put(value, map.get(value) - 1);
                pq.poll();
                break;
            }
        }
    }
}