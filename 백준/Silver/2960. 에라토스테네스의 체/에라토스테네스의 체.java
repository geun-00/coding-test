import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] prime = new boolean[n + 1];

        int count = 0;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (!prime[i]) {
                prime[i] = true;
                count++;
                for (int j = i * 2; j <= n; j += i) {
                    if (!prime[j]) {
                        prime[j] = true;
                        count++;
                    }

                    if (count == k) {
                        System.out.println(j);
                        return;
                    }
                }
            }
        }

        if (count < k) {

            for (int i = 2; i <= n; i++) {
                if (!prime[i]) {
                    count++;
                    if (count == k) {
                        System.out.println(i);
                        break;
                    }
                }
            }
        }
    }
}