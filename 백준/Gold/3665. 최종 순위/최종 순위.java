import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] inDegree = new int[n + 1];
            List<Integer>[] graph = new ArrayList[n + 1];

            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] rank = new int[n];
            for (int i = 0; i < n; i++) {
                rank[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    graph[rank[i]].add(rank[j]);
                    inDegree[rank[j]]++;
                }
            }

            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (graph[a].contains(b)) {
                    graph[a].remove(Integer.valueOf(b));
                    graph[b].add(a);
                    inDegree[a]++;
                    inDegree[b]--;
                } else {
                    graph[b].remove(Integer.valueOf(a));
                    graph[a].add(b);
                    inDegree[a]--;
                    inDegree[b]++;
                }
            }

            Queue<Integer> qu = new ArrayDeque<>();
            List<Integer> result = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) qu.offer(i);
            }

            while (!qu.isEmpty()) {
                if (qu.size() > 1) break;

                int now = qu.poll();
                result.add(now);

                for (int next : graph[now]) {
                    if (--inDegree[next] == 0) {
                        qu.offer(next);
                    }
                }
            }

            if (result.size() == n) {
                for (int num : result) {
                    sb.append(num).append(" ");
                }
                sb.append("\n");
            } else {
                sb.append("IMPOSSIBLE").append("\n");
            }
        }

        System.out.print(sb);
    }
}
