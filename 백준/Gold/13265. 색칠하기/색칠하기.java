import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<Integer>[] graph = new List[n];
            int[] color = new int[n];
            int[] in = new int[n];

            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
                color[i] = -1;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;

                graph[x].add(y);
                graph[y].add(x);

                in[x]++;
            }

            String ans = "possible";
            for (int i = 0; i < n; i++) {
                if (color[i] != -1) {
                    continue;
                }

                Queue<Integer> qu = new ArrayDeque<>();
                qu.offer(i);
                color[i] = 0;

                solve:
                while (!qu.isEmpty()) {
                    int node = qu.poll();

                    for (int next : graph[node]) {
                        if (color[next] == -1) {
                            color[next] = 1 - color[node];
                            qu.offer(next);
                        } else {
                            if (color[next] != 1 - color[node]) {
                                ans = "impossible";
                                break solve;
                            }
                        }
                    }
                }
            }
            
            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}