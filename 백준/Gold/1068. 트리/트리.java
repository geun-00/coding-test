import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] tree;
    static boolean[] visit;
    static int delNode;
    static int leaf = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int root = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == -1) {
                root = i;
            } else {
                tree[num].add(i);
                tree[i].add(num);
            }
        }

        delNode = Integer.parseInt(br.readLine());

        if (root == delNode) {
            System.out.println(0);
        } else {
            dfs(root);
            System.out.println(leaf);
        }
    }

    private static void dfs(int parent) {
        visit[parent] = true;

        int child = 0;

        for (int next : tree[parent]) {
            if (!visit[next] && next != delNode) {
                child++;
                dfs(next);
            }
        }

        if (child == 0) {
            leaf++;
        }
    }
}
