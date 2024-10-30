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

        ArrayList<Integer>[] graph = new ArrayList[n];
        int[] inDegree = new int[n];
        int[] time = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());

            int m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                inDegree[i]++;
                graph[Integer.parseInt(st.nextToken()) - 1].add(i);
            }
        }

        Queue<Integer> qu = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                qu.offer(i);
            }
        }

        int[] result = new int[n];

        while (!qu.isEmpty()) {
            int now = qu.poll();

            for (int next : graph[now]) {
                inDegree[next]--;

                result[next] = Math.max(result[next], result[now] + time[now]);

                if (inDegree[next] == 0) {
                    qu.offer(next);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            result[i] += time[i];
            max = Math.max(max, result[i]);
        }
        System.out.println(max);
    }
}
