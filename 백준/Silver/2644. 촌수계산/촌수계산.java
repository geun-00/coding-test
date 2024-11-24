import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int t1 = Integer.parseInt(st.nextToken()) - 1;
        int t2 = Integer.parseInt(st.nextToken()) - 1;

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            graph[x].add(y);
            graph[y].add(x);
        }

        System.out.println(bfs(t1, t2, n));
    }

    private static int bfs(int t1, int t2, int n) {

        ArrayDeque<Integer> qu = new ArrayDeque<>();
        qu.offer(t1);

        boolean[] visit = new boolean[n];
        visit[t1] = true;

        int count = 0;

        while (!qu.isEmpty()) {

            int size = qu.size();

            for (int i = 0; i < size; i++) {

                int now = qu.poll();
                if (now == t2) {
                    return count;
                }

                for (int next : graph[now]) {
                    if (!visit[next]) {
                        visit[next] = true;
                        qu.offer(next);
                    }
                }
            }

            count++;
        }

        return -1;
    }
}