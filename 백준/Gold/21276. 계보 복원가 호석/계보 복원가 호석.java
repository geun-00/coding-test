import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        HashMap<String, ArrayList<String>> parentToChild = new HashMap<>();
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        HashMap<String, Integer> inDegree = new HashMap<>();
        ArrayList<String> people = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            String name = st.nextToken();

            people.add(name);
            parentToChild.put(name, new ArrayList<>());
            result.put(name, new ArrayList<>());
            inDegree.put(name, 0);
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            String child = st.nextToken(); //x
            String parent = st.nextToken(); //y

            parentToChild.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1);
        }

        ArrayList<String> root = new ArrayList<>();
        Queue<String> qu = new ArrayDeque<>();
        for (String key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                root.add(key);
                qu.offer(key);
            }
        }
        root.sort(null);

        sb.append(root.size()).append("\n"); //가문의 개수
        for (String r : root) {
            sb.append(r).append(" ");
        }
        sb.append("\n");

        while (!qu.isEmpty()) {
            String parent = qu.poll();

            for (String child : parentToChild.get(parent)) {
                inDegree.put(child, inDegree.get(child) - 1);

                if (inDegree.get(child) == 0) {
                    result.get(parent).add(child);
                    qu.offer(child);
                }
            }
        }

        people.sort(null);
        for (String parent : people) {
            ArrayList<String> list = result.get(parent);
            list.sort(null);

            sb.append(parent).append(" ").append(list.size()).append(" ");

            for (String child : list) {
                sb.append(child).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
