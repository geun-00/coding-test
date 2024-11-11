import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        System.out.println(bfs(n));
    }

    private static int bfs(int n) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(1);

        boolean[] visit = new boolean[n + 1];
        visit[1] = true;

        int count = 0;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int adj : graph[now]) {
                if (!visit[adj]) {
                    visit[adj] = true;
                    count++;
                    qu.offer(adj);
                }
            }
        }

        return count;
    }
}