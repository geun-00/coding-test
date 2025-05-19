import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] parent;
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n * m];
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();

            for (int j = 0; j < m; j++) {
                parent[i * m + j] = i * m + j;

                switch (arr[j]) {
                    case 'U' :
                        map[i][j] = 0;
                        break;
                    case 'D':
                        map[i][j] = 1;
                        break;
                    case 'L':
                        map[i][j] = 2;
                        break;
                    case 'R':
                        map[i][j] = 3;
                }
            }
        }

        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    dfs(i, j);
                }
            }
        }

        Set<Integer> set = Arrays.stream(parent).boxed().collect(Collectors.toSet());
        System.out.println(set.size());
    }

    private static int dfs(int x, int y) {
        if (visit[x][y]) {
            return x * m + y;
        }

        visit[x][y] = true;
        int root = dfs(x + dx[map[x][y]], y + dy[map[x][y]]);
        union(root, x * m + y);

        return root;
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}