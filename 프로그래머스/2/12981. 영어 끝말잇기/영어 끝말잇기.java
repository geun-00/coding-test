import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        HashSet<String> used = new HashSet<>();
        used.add(words[0]);

        char prev = words[0].charAt(words[0].length() - 1);

        for (int i = 1; i < words.length; i++) {
            if (used.contains(words[i]) || prev != words[i].charAt(0)) {

                return new int[]{i % n + 1, i / n + 1};
            }

            used.add(words[i]);
            prev = words[i].charAt(words[i].length() - 1);
        }

        return new int[]{0, 0};
    }
}