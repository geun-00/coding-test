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

            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            arr[i][0] = d;
            arr[i][1] = p;
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> qu = new PriorityQueue<>();

        for (int[] lecture : arr) {

            int d = lecture[0];
            int p = lecture[1];

            qu.offer(p);

            if (qu.size() > d) {
                qu.poll();
            }
        }

        int sum = 0;

        for (int num : qu) {
            sum += num;
        }
        System.out.println(sum);
    }
}