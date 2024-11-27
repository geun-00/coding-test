import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = br.readLine()) != null && !input.isEmpty()) {

            int x = Integer.parseInt(input) * 10_000_000;

            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr);

            int s = 0, e = n - 1;

            boolean flag = false;

            while (s < e) {

                int sum = arr[s] + arr[e];

                if (sum == x) {
                    flag = true;
                    break;
                }

                if (sum > x) {
                    e--;
                } else {
                    s++;
                }
            }

            if (!flag) {
                sb.append("danger").append("\n");
            } else {
                sb.append("yes ").append(arr[s]).append(" ").append(arr[e]).append("\n");
            }
        }

        System.out.print(sb);
    }
}