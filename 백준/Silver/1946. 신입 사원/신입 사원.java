import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[n][2];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr, (a, b) -> {
                return a[0] - b[0];
            });

            int count = 1;
            int min = arr[0][1];

            for (int i = 1; i < n; i++) {
                if (arr[i][1] < min) {
                    min = arr[i][1];
                    count++;
                }
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }
}