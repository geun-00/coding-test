import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] flag = new boolean[2_000_001];

        for (int i = 1; i < (1 << n); i++) {
            int sum = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += arr[j];
                }
            }

            flag[sum] = true;
        }

        for (int i = 1; i < flag.length; i++) {
            if (!flag[i]) {
                System.out.println(i);
                return;
            }
        }
    }
}