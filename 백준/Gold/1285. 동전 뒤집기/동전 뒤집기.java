import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int ans = n * n;

        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (chars[j] == 'T') {
                    arr[i] += (1 << j);
                }
            }
        }

        for (int i = 0; i < (1 << n); i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                int temp = Integer.bitCount(arr[j] ^ i);
                sum += Math.min(temp, n - temp);
            }
            ans = Math.min(ans, sum);
        }

        System.out.println(ans);
    }
}