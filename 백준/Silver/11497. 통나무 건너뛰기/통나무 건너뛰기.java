import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];
            int[] temp = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int left = 0, right = n - 1;
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    temp[left++] = arr[i];
                } else {
                    temp[right--] = arr[i];
                }
            }

            int ans = Math.abs(temp[n - 1] - temp[0]);
            for (int i = 0; i < n - 1; i++) {
                ans = Math.max(ans, Math.abs(temp[i + 1] - temp[i]));
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}
