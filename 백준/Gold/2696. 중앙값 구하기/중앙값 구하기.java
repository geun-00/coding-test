import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int m = Integer.parseInt(br.readLine());

            int[] arr = new int[m];

            int temp = m;
            int size = 10;

            for (int i = 0; i <= (m / size); i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < Math.min(temp, size); j++) {
                    arr[(i * size) + j] = Integer.parseInt(st.nextToken());
                }

                temp -= size;
            }

            int[] result = new int[(m + 1) / 2];
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            for (int i = 0; i < m; i++) {
                offer(arr[i], maxHeap, minHeap);
                if (i % 2 == 0) {
                    result[i / 2] = maxHeap.peek();
                }
            }

            int len = result.length;
            sb.append(result.length).append("\n");

            for (int i = 0; i <= (result.length / size); i++) {
                for (int j = 0; j < Math.min(len, size); j++) {
                    sb.append(result[(i * size) + j]).append(" ");
                }

                sb.append("\n");
                len -= size;
            }

        }

        System.out.print(sb);
    }

    private static void offer(int num, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
}