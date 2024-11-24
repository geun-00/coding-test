import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n < 100) {
            System.out.println(n);
            return;
        }

        int count = 99;

        for (int i = 100; i <= n; i++) {

            int h = i / 100;
            int t = i % 100 / 10;
            int o = i % 10;

            if (o - t == t - h) {
                count++;
            }
        }

        System.out.println(count);
    }
}