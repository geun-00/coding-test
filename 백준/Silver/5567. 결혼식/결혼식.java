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

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            graph[a].add(b);
            graph[b].add(a);
        }

        int[] depth = new int[n];
        boolean[] visit = new boolean[n];

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(0);
        visit[0] = true;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int next : graph[now]) {

                if (!visit[next]) {
                    visit[next] = true;
                    depth[next] = depth[now] + 1;
                    qu.offer(next);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (visit[i] && depth[i] <= 2) {
                ans++;
            }
        }

        System.out.println(ans);

    }
}