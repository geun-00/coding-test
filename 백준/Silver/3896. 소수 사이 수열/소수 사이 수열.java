import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] isPrime = getPrimeArr();
        List<Integer> primes = IntStream.range(1, isPrime.length)
                                        .filter(i -> isPrime[i])
                                        .boxed()
                                        .collect(Collectors.toList());

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());

            if (isPrime[k]) {
                sb.append(0).append("\n");
                continue;
            }

            int lower = lowerBound(k, isPrime);
            int upper = upperBound(k, isPrime);

            sb.append(upper - lower).append("\n");
        }

        System.out.print(sb);
    }

    private static int lowerBound(int k, boolean[] isPrime) {
        for (int i = k - 1; i > 0; i--) {
            if (isPrime[i]) {
                return i;
            }
        }

        return 0;
    }

    private static int upperBound(int k, boolean[] isPrime) {
        for (int i = k + 1; i <= isPrime.length; i++) {
            if (isPrime[i]) {
                return i;
            }
        }

        return 0;
    }

    private static boolean[] getPrimeArr() {
        int n = 1299709;
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (!isPrime[i]) continue;

            for (int j = i * 2; j <= n; j += i) {
                isPrime[j] = false;
            }
        }

        return isPrime;
    }
}