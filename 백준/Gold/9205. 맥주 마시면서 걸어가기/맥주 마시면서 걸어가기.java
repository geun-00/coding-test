import java.awt.*;
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

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            int n = Integer.parseInt(br.readLine());

            Point[] points = new Point[n + 2];
            graph = new ArrayList[n + 2];

            for (int i = 0; i < n + 2; i++) {

                graph[i] = new ArrayList<>();

                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                points[i] = new Point(x, y);
            }

            for (int s = 0; s <= n; s++) {
                for (int e = s+1; e <= n + 1; e++) {

  

                    int dist = Math.abs(points[s].x - points[e].x) + Math.abs(points[s].y - points[e].y);
                    if (dist <= 1000) {
                        graph[s].add(e);
                        graph[e].add(s);
                    }
                }
            }

            sb.append(bfs(n)).append("\n");
        }

        System.out.print(sb);
    }

    private static String bfs(int n) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(0);

        boolean[] visit = new boolean[n + 2];
        visit[0] = true;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            if (now == n + 1) {
                return "happy";
            }

            for (int next : graph[now]) {
                if (!visit[next]) {
                    visit[next] = true;
                    qu.offer(next);
                }
            }
        }

        return "sad";
    }
}
