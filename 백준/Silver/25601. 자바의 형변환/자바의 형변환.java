import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<String, List<String>> tree = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String child = st.nextToken();
            String parent = st.nextToken();

            tree.putIfAbsent(parent, new ArrayList<>());

            tree.get(parent).add(child);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();

        if (solve(a, b, tree) || solve(b, a, tree)) {
            System.out.println(1);
            return;
        }

        System.out.println(0);
    }

    private static boolean solve(String a, String b, Map<String, List<String>> tree) {
        if (a.equals(b)) {
            return true;
        }

        if (tree.get(a) != null) {
            for (String child : tree.get(a)) {
                if (solve(child, b, tree)) {
                    return true;
                }
            }
        }

        return false;
    }
}