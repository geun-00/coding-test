import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[num - 1][0] = start;
            arr[num - 1][1] = end;
        }

        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<Integer> qu = new PriorityQueue<>();

        for (int[] c : arr) {

            if (!qu.isEmpty() && qu.peek() <= c[0]) {
                qu.poll();
            }
            qu.offer(c[1]);
        }

        System.out.println(qu.size());
    }
}
