import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            int n = Integer.parseInt(input);

            int remain = 1 % n;
            int ans = 1;

            while (remain != 0) {
                ans++;
                remain = (remain * 10 + 1) % n;
            }

            System.out.println(ans);
        }
    }
}