import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long ans = 0;
        int len = 1;
        int start = 1;
        int end = 9;

        while (start <= n) {

            int now = Math.min(n, end);
            ans += (long) (now - start + 1) * len;

            len++;
            start *= 10;
            end = end * 10 + 9;
        }

        System.out.println(ans);
    }
}