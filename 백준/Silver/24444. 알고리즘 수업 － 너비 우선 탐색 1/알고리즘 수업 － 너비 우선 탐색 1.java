import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()) - 1;

        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        for (List<Integer> list : graph) {
            Collections.sort(list);
        }

        int order = 1;
        int[] ans = new int[n];
        ans[r] = order++;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(r);

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int next : graph[now]) {

                if (ans[next] == 0) {
                    ans[next] = order++;
                    qu.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(ans)
                .forEach(num -> sb.append(num).append("\n"));
        System.out.print(sb);
    }
}
