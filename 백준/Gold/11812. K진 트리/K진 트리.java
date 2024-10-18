import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            
            if (k == 1) {
                sb.append(Math.abs(x - y)).append("\n");
                continue;
            }

            long dist = 0;
            while (x != y) {
                if (x > y) {
                    x = getParent(x, k);
                } else {
                    y = getParent(y, k);
                }
                dist++;
            }
            sb.append(dist).append("\n");
        }

        System.out.print(sb);
    }

    private static long getParent(long node, int k) { 
        return (node - 2) / k + 1;
    }
}
