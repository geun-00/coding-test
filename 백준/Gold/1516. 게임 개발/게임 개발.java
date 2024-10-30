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
        int[] build = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            build[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) {
                    break;
                }
                graph[num - 1].add(i);
                inDegree[i]++;
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

                result[next] = Math.max(result[next], result[now] + build[now]);

                if (inDegree[next] == 0) {
                    qu.offer(next);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(result[i] + build[i]);
        }
    }
}
