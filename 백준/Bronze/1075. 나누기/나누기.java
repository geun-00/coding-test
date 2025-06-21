import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int f = Integer.parseInt(br.readLine());

        for (int i = n - (n % 100); i <= n - (n % 100) + 99; i++) {
            if (i % f == 0) {
                System.out.printf("%02d", i % 100);
                return;
            }
        }
    }
}
