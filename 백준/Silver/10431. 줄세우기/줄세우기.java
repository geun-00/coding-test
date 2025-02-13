import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int p = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < p; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());

            int[] arr = new int[20];
            sb.append(t);

            for (int j = 0; j < 20; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            int ans = 0;
            for (int j = 1; j < 20; j++) {
                int point = j;
                int value = arr[j];

                for (int k = j - 1; k >= 0; k--) {
                    if (arr[k] < value) {
                        point = k + 1;
                        break;
                    }
                    if (k == 0) {
                        point = 0;
                    }
                }

                for (int k = j; k > point; k--) {
                    arr[k] = arr[k - 1];
                    ans++;
                }
                arr[point] = value;
            }

            sb.append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }
}