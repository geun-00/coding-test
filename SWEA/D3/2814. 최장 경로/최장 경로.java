import java.util.*;
import java.io.*;

class Solution
{
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static int max;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            max = 0;
            graph = new ArrayList[n + 1];
            visit = new boolean[n + 1];

            for (int i = 0; i < n + 1; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph[x].add(y);
                graph[y].add(x);
            }

            for (int i = 1; i <= n; i++) {
                dfs(i, 1);
            }

            System.out.println("#" + tc + " " + max);
        }
	}
    
    public static void dfs(int node, int depth) {
        max = Math.max(max, depth);

        visit[node] = true;
        for (int next : graph[node]) {
            if (!visit[next]) {
                dfs(next, depth + 1);
            }
        }
        visit[node] = false;
    }
}