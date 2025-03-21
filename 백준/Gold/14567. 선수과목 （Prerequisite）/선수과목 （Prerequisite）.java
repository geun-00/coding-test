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
        int m = Integer.parseInt(st.nextToken());

        int[] inDegree = new int[n];
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            inDegree[b]++;
            graph[a].add(b);
        }

        int[] arr = new int[n];

        Queue<Integer> qu = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                arr[i] = 1;
                qu.offer(i);
            }
        }

        while (!qu.isEmpty()) {
            int now = qu.poll();

            for (int next : graph[now]) {
                inDegree[next]--;

                if (inDegree[next] == 0) {
                    qu.offer(next);
                    arr[next] = arr[now] + 1;
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        for(int num : arr) sb.append(num).append(" ");
        System.out.print(sb);
    }
}