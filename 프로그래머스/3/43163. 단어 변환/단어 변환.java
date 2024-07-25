class Solution {
    
    int min = Integer.MAX_VALUE;
    boolean[] visit;
    
    public int solution(String begin, String target, String[] words) {
        
        min = Integer.MAX_VALUE;
        visit = new boolean[words.length];

        dfs(begin, target, words, 0);

        return min == Integer.MAX_VALUE ? 0 : min;
    }
    
    public void dfs(String now, String target, String[] words, int depth) {
        if (now.equals(target)) {
            min = Math.min(min, depth);
            return;
        }

        for (int i = 0; i < words.length; i++) {

            int diff = 0;
            for (int j = 0; j < now.length(); j++) {
                if (words[i].charAt(j) != now.charAt(j)) {
                    diff++;
                }
            }

            if (diff == 1 && !visit[i]) {
                visit[i] = true;
                dfs(words[i], target, words, depth + 1);
                visit[i] = false;
            }
        }
    }
}
