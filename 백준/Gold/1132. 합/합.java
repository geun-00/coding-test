import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[][] alphas = new long[10][2];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            alphas[s.charAt(0) - 'A'][0] = 1;

            for (int j = 0; j < s.length(); j++) {
                alphas[s.charAt(j) - 'A'][1] += (long) Math.pow(10, (s.length() - j - 1));
            }
        }

        Arrays.sort(alphas, Comparator.comparingLong(a -> a[1]));
        boolean[] used = new boolean[10];

        long ans = 0;
        for (long[] alpha : alphas) {
            for (int j = (int) alpha[0]; j < 10; j++) {
                if (!used[j]) {
                    used[j] = true;
                    ans += alpha[1] * (long) j;
                    break;
                }
            }
        }
        System.out.println(ans);
    }
}