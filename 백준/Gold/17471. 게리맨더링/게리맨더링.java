import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static int[] people;
    static int[] area;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        graph = new ArrayList[n];
        people = new int[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            people[i] = Integer.parseInt(st.nextToken());
        }

        area = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken()) - 1;
                graph[i].add(num);
            }
        }

        int min = INF;
        for (int bit = 1; bit < (1 << n) - 1; bit++) {
            for (int i = 0; i < n; i++) {
                if ((bit & (1 << i)) != 0) {
                    area[i] = 1;
                } else {
                    area[i] = 0;
                }
            }

            if (isConnected(1) && isConnected(0)) {
                int diff = Math.abs(getPeople(1) - getPeople(0));
                min = Math.min(min, diff);
            }
        }

        System.out.println(min == INF ? -1 : min);
        
    }

    private static int getPeople(int a) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (area[i] == a) {
                sum += people[i];
            }
        }
        return sum;
    }

    private static boolean isConnected(int a) {

        int start = -1;
        int temp = 0;
        
        for (int i = 0; i < n; i++) {
            if (area[i] == a) {
                start = i;
                temp++;
            }
        }

        visit = new boolean[n];

        return dfs(start, a) == temp;
    }

    private static int dfs(int now, int a) {
        visit[now] = true;
        int count = 1;

        for (int next : graph[now]) {
            if (!visit[next] && area[next] == a) {
                count += dfs(next, a);
            }
        }

        return count;
    }
}