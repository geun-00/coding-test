import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        init(n);

        Tree[] trees = new Tree[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            trees[i] = new Tree(i, x1, x2, y);
        }

        Arrays.sort(trees, (a, b) -> {
            if (a.x1 == b.x1) {
                return Integer.compare((b.x2 - b.x1), (a.x2 - a.x1));
            }
            return Integer.compare(a.x1, b.x1);
        });

        Tree last = trees[0];

        for (int i = 1; i < n; i++) {
            if (trees[i].x1 <= last.x2) {
                union(trees[i].number, last.number);
            }

            if (last.x2 < trees[i].x2) {
                last = trees[i];
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            sb.append((find(a) == find(b)) ? 1 : 0).append("\n");
        }

        System.out.print(sb);
    }

    private static void init(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    static class Tree {
        int number;
        int x1, x2, y;

        public Tree(int number, int x1, int x2, int y) {
            this.number = number;
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
        }
    }
}