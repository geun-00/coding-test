import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(br.readLine());

        HashMap<String, PriorityQueue<Integer>> infos = new HashMap<>();

        long count = 0;

        while (q-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            int k = Integer.parseInt(st.nextToken());

            if (command == 1) {
                PriorityQueue<Integer> pq = infos.getOrDefault(name, new PriorityQueue<>(Collections.reverseOrder()));

                for (int i = 0; i < k; i++) {
                    int num = Integer.parseInt(st.nextToken());
                    pq.offer(num);
                }

                infos.put(name, pq);
            }
            else {

                if (infos.containsKey(name)) {
                    PriorityQueue<Integer> info = infos.get(name);

                    if (k >= info.size()) {
                        while (!info.isEmpty()) {
                            count += info.poll();
                        }
                    } else {
                        for (int i = 0; i < k; i++) {
                            count += info.poll();
                        }
                    }
                }
            }
        }

        System.out.println(count);
    }
}
