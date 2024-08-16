import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static HashMap<String, Integer> countMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        Trie trie = new Trie();

        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            sb.append(trie.insert(name)).append("\n");
        }

        System.out.println(sb);
    }

    static class Node {
        boolean isEnd;
        Map<Character, Node> child;

        public Node(boolean isEnd) {
            this.isEnd = isEnd;
            child = new HashMap<>();
        }
    }

    static class Trie {

        Node node;

        public Trie() {
            this.node = new Node(false);
        }

        String insert(String name) {
            Node now = this.node;

            boolean flag = false;
            int temp = 0;

            for (int i = 0; i < name.length(); i++) {
                char ch = name.charAt(i);

                if (!now.child.containsKey(ch)) {
                    now.child.put(ch, new Node(false));

                    if (!flag) {
                        flag = true;
                        temp = i + 1;
                    }
                }
                now = now.child.get(ch);

                if (i == name.length() - 1) {
                    now.isEnd = true;
                }
            }

            if (!countMap.containsKey(name)) {
                countMap.put(name, 1);
                return flag ? name.substring(0, temp) : name;
            } else {
                countMap.put(name, countMap.get(name) + 1);
                return name + countMap.get(name);
            }
        }
    }
}
