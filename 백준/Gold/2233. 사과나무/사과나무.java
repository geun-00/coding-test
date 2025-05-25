import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        char[] input = br.readLine().toCharArray();
        String[] arr = br.readLine().split(" ");

        int X = Integer.parseInt(arr[0]);
        int Y = Integer.parseInt(arr[1]);

        Deque<Integer> stack = new ArrayDeque<>();
        int nodeNum = 1;
        int depth = 1;
        int nodeX = 0;
        int nodeY = 0;

        int[] start = new int[n + 1];
        int[] end = new int[n + 1];
        int[] level = new int[n + 1];
        int[] parent = new int[n + 1];
        int[] nodeIndex = new int[n * 2];

        List<Integer>[] tree = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < input.length; i++) {

            if (input[i] == '0') {
                start[nodeNum] = i + 1;

                if (!stack.isEmpty()) {
                    int par = stack.peek();
                    tree[par].add(nodeNum);
                    parent[nodeNum] = par;
                }

                stack.push(nodeNum);
                level[nodeNum] = depth;
                nodeIndex[i] = nodeNum;

                nodeNum++;
                depth++;
            } else {
                int cur = stack.pop();
                end[cur] = i + 1;
                nodeIndex[i] = cur;
                depth--;
            }
        }

        int lca = getLCA(nodeIndex[X - 1], nodeIndex[Y - 1], level, parent);
        System.out.println(start[lca] + " " + end[lca]);
    }

    private static int getLCA(int x, int y, int[] level, int[] parent) {
        if (level[x] > level[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        while (level[x] != level[y]) {
            y = parent[y];
        }

        while (x != y) {
            x = parent[x];
            y = parent[y];
        }

        return x;
    }
}