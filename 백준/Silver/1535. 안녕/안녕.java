import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] hp = new int[n];
        int[] happy = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            hp[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            happy[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;

        for (int i = 0; i < (1 << n); i++) {

            int h = 100;
            int sum = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    if (h > hp[j]) {
                        h -= hp[j];
                        sum += happy[j];
                    } else {
                        break;
                    }
                }
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}