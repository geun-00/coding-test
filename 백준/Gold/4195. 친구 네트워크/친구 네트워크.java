import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static HashMap<String, String> friend;
    static HashMap<String, Integer> size;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            friend = new HashMap<>();
            size = new HashMap<>();

            int f = Integer.parseInt(br.readLine());

            for (int i = 0; i < f; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String f1 = st.nextToken();
                String f2 = st.nextToken();

                if (!friend.containsKey(f1)) {
                    friend.put(f1, f1);
                    size.put(f1, 1);
                }

                if (!friend.containsKey(f2)) {
                    friend.put(f2, f2);
                    size.put(f2, 1);
                }

                union(f1, f2);
                System.out.println(size.get(find(f1)));
            }
        }
    }

    private static void union(String f1, String f2) {
        f1 = find(f1);
        f2 = find(f2);

        if (!f1.equals(f2)) {
            friend.put(f1, f2);
            size.put(f2, size.get(f1) + size.get(f2));
        }
    }

    private static String find(String f) {
        if (f.equals(friend.get(f))) {
            return f;
        }

        String root = find(friend.get(f));
        friend.put(f, root);

        return root;
    }
}
