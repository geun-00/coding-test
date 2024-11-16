import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (s == 1) {

                for (int j = num; j <= n; j += num) {
                    arr[j] = 1 - arr[j];
                }
            }
            else {

                arr[num] = 1 - arr[num];

                for (int j = 1; j <= n; j++) {
                    if (num - j <= 0 || num + j > n) {
                        break;
                    }
                    if (arr[num - j] != arr[num + j]) {
                        break;
                    }
                    arr[num - j] = 1 - arr[num - j];
                    arr[num + j] = 1 - arr[num + j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(arr[i]).append(" ");
            if (i % 20 == 0) {
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }
}