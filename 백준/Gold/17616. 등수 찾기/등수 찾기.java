import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<Integer>[] win = new List[n + 1];
        List<Integer>[] lose = new List[n + 1];

        for (int i = 0; i <= n; i++) {
            win[i] = new ArrayList<>();
            lose[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            win[a].add(b);
            lose[b].add(a);
        }

        int winCount = bfs(n, x, win);
        int loseCount = bfs(n, x, lose);

        System.out.println((loseCount + 1) + " " + (n - winCount));
    }

    private static int bfs(int n, int x, List<Integer>[] graph) {
        boolean[] visit = new boolean[n + 1];
        int count = 0;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(x);

        while (!qu.isEmpty()) {
            int node = qu.poll();

            for (int next : graph[node]) {
                if (!visit[next]) {
                    visit[next] = true;
                    count++;
                    qu.offer(next);
                }
            }
        }

        return count;
    }
}