import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] graph;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
        }

        int[] dist = bfs(x);

        ArrayList<Integer> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        boolean flag = false;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == k) {
                sb.append(i).append("\n");
                flag = true;
            }
        }

        if (!flag) {
            System.out.println(-1);
            return;
        }

        System.out.print(sb);
    }

    private static int[] bfs(int start) {

        int[] dist = new int[n + 1];

        boolean[] visit = new boolean[n + 1];
        visit[start] = true;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(start);

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int next : graph[now]) {
                if (!visit[next]) {
                    visit[next] = true;
                    dist[next] = dist[now] + 1;
                    qu.offer(next);
                }
            }
        }

        return dist;
    }
}
