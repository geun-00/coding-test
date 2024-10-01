import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] tree;
    static long[] sheep;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        sheep = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 2; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char t = st.nextToken().charAt(0);          //w=늑대, s=양
            int a = Integer.parseInt(st.nextToken());   //마리 수
            int p = Integer.parseInt(st.nextToken());   //연결된 노드 번호

            tree[p].add(new Node(t, i, a));

            if (t == 'S') {
                sheep[i] = a;
            } else {
                sheep[i] = -a;
            }
        }

        System.out.println(dfs(1));
    }

    private static long dfs(int node) {

        long sum = 0;
        for (Node next : tree[node]) {
            sum += dfs(next.to);
        }

        if (sheep[node] > 0) {
            sum += sheep[node];
        } else {
            sum += sheep[node];
            if (sum < 0) {
                sum = 0;
            }
        }

        return sum;
    }

    static class Node {

        char c;
        int to;
        int amount;

        public Node(char c, int to, int amount) {
            this.c = c;
            this.to = to;
            this.amount = amount;
        }
    }
}
