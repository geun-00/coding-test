import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int[] trues = new int[t];

        for (int i = 0; i < t; i++) {
            trues[i] = Integer.parseInt(st.nextToken());
        }

        int[][] party = new int[m][50];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int people = Integer.parseInt(st.nextToken());
            for (int j = 0; j < people; j++) {
                party[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            int first = party[i][0];

            for (int j = 1; j < party[i].length; j++) {
                if (party[i][j] == 0) {
                    break;
                }

                union(first, party[i][j]);
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            int first = party[i][0];
            boolean flag = true;

            for (int tr : trues) {
                if (find(first) == find(tr)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}
