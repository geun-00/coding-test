import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], 10000);
            dist[i][i] = 0;
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) {
                break;
            }

            a--;
            b--;

            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                }
            }
        }

        int[] arr = new int[n];
        int min = 10000;

        for (int i = 0; i < n; i++) {
            int max = 0;

            for (int j = 0; j < n; j++) {
                max = Math.max(max, dist[i][j]);
            }

            arr[i] = max;
            min = Math.min(min, arr[i]);
        }

        final int mmin = min;
        long count = Arrays.stream(arr).filter(num -> num == mmin).count();

        System.out.println(min + " " + count);

        IntStream.range(0, n).filter(i -> arr[i] == mmin).forEach(i -> System.out.print((i + 1) + " "));

    }
}
