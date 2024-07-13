import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] graph;
    static int[] colors;
    static boolean check;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph = new ArrayList[v + 1];
            colors = new int[v + 1];
            check = false;

            for (int i = 1; i <= v; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());

                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                graph[v1].add(v2);
                graph[v2].add(v1);
            }

            for (int i = 1; i <= v; i++) {
                if (colors[i] == 0) {
                    dfs(i, 1);
                }
            }

            System.out.println(check ? "NO" : "YES");
        }
    }

    private static void dfs(int now, int color) {
        colors[now] = color;

        for (int next : graph[now]) {
            if (colors[next] == color) {
                check = true;
                return;
            }

            if (colors[next] == 0) {
                dfs(next, -color);
            }
        }
    }
}
