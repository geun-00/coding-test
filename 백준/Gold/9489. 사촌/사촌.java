import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (n == 0 && k == 0) {
                break;
            }
            int[] arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[] parent = new int[n];
            int[] depth = new int[n];
            int[] child = new int[n];

            parent[0] = -1;

            int p = 0;
            int level = 0;
            int target = 0;

            for (int i = 1; i < n; i++) {
                if (arr[i] == k) {
                    target = i;
                }
                if (arr[i] != arr[i - 1] + 1) {
                    while (child[p] != 0) {
                        p++;
                    }
                    level = depth[p] + 1;
                }
                parent[i] = p;
                depth[i] = level;
                child[p]++;
            }

            int target_depth = depth[target] - 1;
            int target_parent = parent[target];

            int ans = 0;

            for (int i = 1; i < n; i++) {

                if (parent[i] != parent[target] && parent[parent[i]] == parent[parent[target]]) {
                    ans++;
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);

    }
}