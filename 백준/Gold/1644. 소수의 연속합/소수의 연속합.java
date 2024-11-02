import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[] isPrime = getPrimes(n);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                list.add(i);
            }
        }

        int[] prime = list.stream().mapToInt(Integer::intValue).toArray();

        int m = prime.length;
        int[] sum = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            sum[i] = sum[i - 1] + prime[i - 1];
        }

        int left = 1, right = 1;
        int ans = 0;

        while (right <= m) {
            int res = sum[right] - sum[left - 1];

            if (res < n) {
                right++;
            } else if (res > n) {
                left++;
            } else {
                ans++;
                right++;
                left++;
            }
        }

        System.out.println(ans);
    }

    private static boolean[] getPrimes(int n) {

        boolean[] isPrime = new boolean[n + 1];

        Arrays.fill(isPrime, true);

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = i * 2; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return isPrime;
    }
}
