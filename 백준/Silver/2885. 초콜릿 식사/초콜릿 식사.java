import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        int n = 1;
        while (n < k) {
            n <<= 1;
        }

        if (n == k) {
            System.out.println(n + " " + 0);
            return;
        }

        int count = 0;
        int temp = n;
        int sum = 0;

        while (sum != k) {
            temp /= 2;
            if (sum + temp <= k) {
                sum += temp;
            }
            count++;
        }

        System.out.println(n + " " + count);
    }
}