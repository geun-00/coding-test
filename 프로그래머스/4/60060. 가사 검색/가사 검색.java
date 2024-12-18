import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {                

        Node trie = new Node();
        Node reverseTrie = new Node();

        for (String word : words) {
            trie.add(word, 0);
            reverseTrie.add(new StringBuilder(word).reverse().toString(), 0);
        }
        
        int n = queries.length;
        
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            String query = queries[i];

            ans[i] = query.startsWith("?")
                    ? reverseTrie.count(new StringBuilder(query).reverse().toString(), 0)
                    : trie.count(query, 0);
        }

        return ans;
    }
    
    static class Node {

        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Character, Node> children = new HashMap<>();

        public void add(String word, int index) {

            if (index >= word.length()) return;

            int length = word.length() - index;
            countMap.put(length, countMap.getOrDefault(length, 0) + 1);

            char c = word.charAt(index);
            Node child = children.getOrDefault(c, new Node());

            child.add(word, index + 1);

            children.put(c, child);
        }

        public int count(String query, int index) {

            if (query.charAt(index) == '?') {
                return countMap.getOrDefault(query.length() - index, 0);
            }

            char c = query.charAt(index);

            if (!children.containsKey(c)) return 0;

            Node child = children.get(c);
            return child.count(query, index + 1);
        }
    }
}