import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            if (L + P + V == 0) {
                System.out.print(sb);
                return;
            }

            int ans = (V / P) * L + Math.min(V % P, L);
            sb.append(String.format("Case %d: %d\n", tc++, ans));
        }
    }
}
