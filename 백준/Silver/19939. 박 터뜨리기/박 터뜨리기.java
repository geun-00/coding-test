import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[k + 1];
        int sum = 0;

        for (int i = 1; i <= k; i++) {
            arr[i] = i;
            sum += i;
        }

        if (n < sum) {
            System.out.println(-1);
            return;
        }

        int remain = n - sum;
        while (remain > 0) {
            for (int i = k; i > 0 && remain > 0; i--) {
                arr[i]++;
                remain--;
            }
        }

        System.out.println(arr[k] - arr[1]);
    }
}