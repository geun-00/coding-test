import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] tree = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        long d = 0, g = 0;

        for (int i = 1; i <= n; i++) {
            if (tree[i].size() >= 3) {

                int m = tree[i].size();

                g += (((long) m * (m - 1) * (m - 2)) / 6);
            }
        }

        for (int i = 1; i <= n; i++) {

            if (tree[i].size() >= 2) {

                for (int adj : tree[i]) {
                    if (i < adj && tree[adj].size() >= 2) {

                        d += (long) (tree[i].size() - 1) * (tree[adj].size() - 1);
                    }
                }
            }
        }

        if (d == g * 3) {
            System.out.println("DUDUDUNGA");
        } else if (d > g * 3) {
            System.out.println("D");
        } else {
            System.out.println("G");
        }
    }
}