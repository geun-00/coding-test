import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] graph;
    static int start, end;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int max = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));

            max = Math.max(max, c);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int s = 1;
        int e = max;
        int result = 0;

        while (s <= e) {

            int mid = (s + e) / 2;
            if (bfs(mid, n)) {
                result = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        System.out.println(result);

    }

    private static boolean bfs(int weight, int n) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(start);

        boolean[] visit = new boolean[n + 1];
        visit[start] = true;

        while (!qu.isEmpty()) {
            int now = qu.poll();

            if (now == end) {
                return true;
            }

            for (Node next : graph[now]) {
                if (!visit[next.to] && next.weight >= weight) {
                    visit[next.to] = true;
                    qu.offer(next.to);
                }
            }
        }

        return false;
    }

    static class Node {

        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
