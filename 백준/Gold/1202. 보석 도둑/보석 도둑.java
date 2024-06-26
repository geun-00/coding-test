import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] bags = new int[k];
        Jewel[] jewels = new Jewel[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewels[i] = new Jewel(m, v);
        }

        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags);
        Arrays.sort(jewels);

        PriorityQueue<Integer> qu = new PriorityQueue<>((o1, o2) -> o2 - o1);

        long sum = 0;
        int index = 0;

        for (int i = 0; i < k; i++) {
            while (index < n && bags[i] >= jewels[index].m) {
                qu.offer(jewels[index].v);
                index++;
            }
            if (!qu.isEmpty()) {
                sum += qu.poll();
            }
        }

        System.out.println(sum);

    }

    static class Jewel implements Comparable<Jewel> {

        int m, v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel o) {
            if (this.m == o.m) {
                return o.v - this.v;
            }
            return this.m - o.m;
        }
    }
}
