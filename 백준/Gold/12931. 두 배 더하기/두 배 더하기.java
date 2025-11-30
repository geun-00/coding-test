import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = 0;
        int maxDiv = 0;

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            int div = 0;

            while (num > 1) {
                if (num % 2 == 1) {
                    count++;
                    num--;
                }
                div++;
                num /= 2;
            }

            arr[i] = div;

            if (num > 0) {
                count++;
            }

            maxDiv = Math.max(maxDiv, div);
        }

        System.out.println(count + maxDiv);
    }
}