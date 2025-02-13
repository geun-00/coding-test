import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());

        if (num == 1) {
            long k = Long.parseLong(st.nextToken()) - 1;

            List<Integer> numbers = getNumbers(n);

            StringBuilder sb = new StringBuilder();

            for (int i = n; i > 0; i--) {
                int index = (int) (k / fact[i - 1]);
                sb.append(numbers.get(index)).append(" ");
                numbers.remove(index);
                k %= fact[i - 1];
            }

            System.out.println(sb);
        }
        else {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            List<Integer> numbers = getNumbers(n);

            long ans = 1;

            for (int i = 0; i < n; i++) {
                int index = numbers.indexOf(arr[i]);
                ans += index * fact[n - 1 - i];
                numbers.remove(index);
            }

            System.out.println(ans);
        }
    }

    private static List<Integer> getNumbers(int n) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
