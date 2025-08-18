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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[n + m + 1];
        for (int i = 0; i <= n + m; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int hyperTube = n + i + 1;

            for (int j = 0; j < k; j++) {
                int num = Integer.parseInt(st.nextToken());
                graph[hyperTube].add(num);
                graph[num].add(hyperTube);
            }
        }
        
        if (n == 1) {
            System.out.println(1);
            return;
        }

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{1, 1});

        boolean[] visit = new boolean[n + m + 1];
        visit[1] = true;

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();
            int num = cur[0];
            int depth = cur[1];

            if (num == n) {
                System.out.println(depth);
                return;
            }

            for (int next : graph[num]) {
                if (!visit[next]) {
                    visit[next] = true;
                    int nextDepth = (next > n) ? depth : depth + 1;
                    qu.offer(new int[]{next, nextDepth});
                }
            }
        }
        
        System.out.println(-1);
    }
}