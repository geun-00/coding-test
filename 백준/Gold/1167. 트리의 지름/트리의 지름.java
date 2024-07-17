import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] tree;
    static boolean[] visit;
    static int max = 0; //루트 노드에서 가장 멀리 떨어진 노드와의 거리, 지름의 길이
    static int maxNode; //루트 노드에서 가장 멀리 떨어진 노드

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int v = Integer.parseInt(br.readLine());

        tree = new ArrayList[v + 1];

        for (int i = 1; i <= v; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());

            while (true) {
                int child = Integer.parseInt(st.nextToken());

                if (child == -1) {
                    break;
                }

                int w = Integer.parseInt(st.nextToken());

                tree[parent].add(new Node(child, w));
                tree[child].add(new Node(parent, w));
            }
        }

        visit = new boolean[v + 1];
        dfs(1, 0); //루트 노드에서 가장 멀리 떨어진 노드 찾기

        visit = new boolean[v + 1];
        dfs(maxNode, 0); //트리의 지름 구하기

        System.out.println(max);
    }

    private static void dfs(int now, int dist) {
        if (dist > max) {
            max = dist;
            maxNode = now;
        }

        visit[now] = true;

        for (Node next : tree[now]) {
            if (!visit[next.adj]) {
                dfs(next.adj, dist + next.w);
            }
        }
    }

    static class Node {
        int adj, w;

        public Node(int adj, int w) {
            this.adj = adj;
            this.w = w;
        }
    }
}
