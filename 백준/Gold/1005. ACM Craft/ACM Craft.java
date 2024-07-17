import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] inDegree = new int[n + 1];
            int[] time = new int[n + 1];
            int[] arr = new int[n + 1];
            ArrayList<Integer>[] graph = new ArrayList[n + 1];

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                time[i] = Integer.parseInt(st.nextToken());
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph[x].add(y);
                inDegree[y]++;
            }

            int w = Integer.parseInt(br.readLine());

            Queue<Integer> qu = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) {
                    qu.offer(i);
                }
            }

            while (!qu.isEmpty()) {
                int now = qu.poll();

                for (int next : graph[now]) {
                    inDegree[next]--;

                    arr[next] = Math.max(arr[next], arr[now] + time[now]);

                    if (inDegree[next] == 0) {
                        qu.offer(next);
                    }
                }
            }

            sb.append(arr[w] + time[w]).append("\n");
        }
        System.out.print(sb);
    }
}
