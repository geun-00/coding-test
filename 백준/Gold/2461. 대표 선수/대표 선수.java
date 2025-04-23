import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr[i]);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.value));
        int max = 0;
        for (int i = 0; i < n; i++) {
            pq.offer(new Node(arr[i][0], i, 0));
            max = Math.max(max, arr[i][0]);
        }

        int ans = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int value = node.value;
            int num = node.num;
            int index = node.index;
            ans = Math.min(ans, max - value);

            if (index + 1 == m) break;

            max = Math.max(max, arr[num][index + 1]);
            pq.offer(new Node(arr[num][index + 1], num, index + 1));
        }

        System.out.println(ans);
    }

    static class Node {
        int value, num, index;

        public Node(int value, int num, int index) {
            this.value = value;
            this.num = num;
            this.index = index;
        }
    }
}