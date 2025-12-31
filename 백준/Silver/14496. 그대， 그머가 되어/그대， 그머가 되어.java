import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        boolean[] visited = new boolean[n + 1];
        visited[a] = true;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{a, 0});

        while (!qu.isEmpty()) {
            int[] node = qu.poll();
            int num = node[0];
            int depth = node[1];

            if (num == b) {
                System.out.println(depth);
                return;
            }

            for (int next : graph[num]) {
                if (!visited[next]) {
                    visited[next] = true;
                    qu.offer(new int[]{next, depth + 1});
                }
            }
        }

        System.out.println(-1);
    }
}