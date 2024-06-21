import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static HashMap<String, String> parent;
    static HashMap<String, Integer> size;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            parent = new HashMap<>();
            size = new HashMap<>();

            int f = Integer.parseInt(br.readLine());

            for (int i = 0; i < f; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String f1 = st.nextToken();
                String f2 = st.nextToken();

                if (!parent.containsKey(f1)) {
                    parent.put(f1, f1);
                    size.put(f1, 1);
                }

                if (!parent.containsKey(f2)) {
                    parent.put(f2, f2);
                    size.put(f2, 1);
                }

                union(f1, f2);
                sb.append(size.get(find(f2))).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void union(String f1, String f2) {
        f1 = find(f1);
        f2 = find(f2);

        if (!f1.equals(f2)) {
            parent.put(f1, f2);
            size.put(f2, size.get(f1) + size.get(f2));
        }
    }

    private static String find(String f) {
        if (f.equals(parent.get(f))) {
            return f;
        }

        String root = find(parent.get(f));
        parent.put(f, root);

        return root;
    }
}
