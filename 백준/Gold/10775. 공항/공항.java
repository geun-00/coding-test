import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int gate = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        parent = new int[gate + 1];
        for (int i = 1; i <= gate; i++) {
            parent[i] = i;
        }

        int count = 0;

        for (int i = 0; i < p; i++) {
            int g = Integer.parseInt(br.readLine());

            int temp = find(g);

            if (temp == 0) {
                break;
            }

            count++;

            union(temp - 1, temp);
        }

        System.out.println(count);
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
