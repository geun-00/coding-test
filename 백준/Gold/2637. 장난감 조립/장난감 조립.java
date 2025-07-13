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

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] in = new int[n + 1];
        List<Part>[] graph = new List[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            in[x]++;
            graph[y].add(new Part(x, k));
        }

        Queue<Integer> qu = new ArrayDeque<>();
        List<Integer> bases = new ArrayList<>();

        int[][] ans = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) {
                bases.add(i);
                qu.offer(i);
                ans[i][i] = 1;
            }
        }
        
        while (!qu.isEmpty()) {
            int cur = qu.poll();

            for (Part next : graph[cur]) {
                for (int i = 1; i <= n; i++) {
                    ans[next.index][i] += ans[cur][i] * next.count;
                }

                if (--in[next.index] == 0) {
                    qu.offer(next.index);
                }
            }
        }

        for (int base : bases) {
            System.out.println(base + " " + ans[n][base]);
        }
    }

    static class Part {
        int index, count;

        public Part(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }
}