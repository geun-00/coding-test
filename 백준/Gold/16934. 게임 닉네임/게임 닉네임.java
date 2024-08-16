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
        Map<Character, Node> child;

        public Node() {
            child = new HashMap<>();
        }
    }

    static class Trie {

        Node node;

        public Trie() {
            this.node = new Node();
        }

        String insert(String name) {

            Node now = this.node;

            boolean newChild = false;
            int temp = 0;

            for (int i = 0; i < name.length(); i++) {

                char ch = name.charAt(i);

                if (!now.child.containsKey(ch)) {
                    now.child.put(ch, new Node());

                    if (!newChild) { //새로운 경로로 가는 경우
                        newChild = true;
                        temp = i + 1;
                    }
                }

                now = now.child.get(ch);
            }

            //처음 나온 이름의 경우
            if (!countMap.containsKey(name)) {
                countMap.put(name, 1);
                //마지막 예제처럼 어떤 이름의 전체는 아예 다른 이름의 일부가 될 수 있다.
                //그런 경우 새로운 자식이 생기지 않는다.
                return newChild ? name.substring(0, temp) : name;
            }
            //2번 이상 나온 이름의 경우
            else {
                countMap.put(name, countMap.get(name) + 1);
                return name + countMap.get(name);
            }
        }
    }
}
