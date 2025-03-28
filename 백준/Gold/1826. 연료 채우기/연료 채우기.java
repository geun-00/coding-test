import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());

        int idx = 0;
        int count = 0;

        while (P < L) {

            while (idx < n && arr[idx][0] <= P) {
                qu.offer(arr[idx][1]);
                idx++;
            }

            if (qu.isEmpty()) {
                System.out.println(-1);
                return;
            }

            P += qu.poll();
            count++;
        }

        System.out.println(count);

    }
}