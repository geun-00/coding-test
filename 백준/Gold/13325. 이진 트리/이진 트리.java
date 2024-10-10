import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] weight;
    static int result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        int edges = (int) (Math.pow(2, k + 1));
        weight = new int[edges];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 2; i < edges; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, k);

        System.out.println(result);
    }


    private static int dfs(int node, int k) {
        if (k == 0) {
            result += weight[node];
            return weight[node];
        }

        int leftChild = node * 2;
        int rightChild = node * 2 + 1;

        int leftDist = dfs(leftChild, k - 1);
        int rightDist = dfs(rightChild, k - 1);

        result += weight[node] + Math.abs(leftDist - rightDist);
        return weight[node] + Math.max(leftDist, rightDist);
    }
}
