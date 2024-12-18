import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        
        HashMap<String, ArrayList<Integer>> scoresMap = getScoresMap(info);

        int n = query.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = solve(query[i], scoresMap);
        }

        return ans;
    }
    
    public int solve(String query, HashMap<String, ArrayList<Integer>> scoresMap) {

        String[] tokens = query.split(" (and )?");
        int score = Integer.parseInt(tokens[tokens.length - 1]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.length - 1; i++) {
            sb.append(tokens[i]);
        }

        String key = sb.toString();

        if (!scoresMap.containsKey(key)) return 0;

        ArrayList<Integer> scores = scoresMap.get(key);

        return scores.size() - lowerBound(score, scores);
    }
    
    public int lowerBound(int score, ArrayList<Integer> scores) {

        int low = 0;
        int high = scores.size() - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            if (scores.get(mid) < score) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }
    
    public HashMap<String, ArrayList<Integer>> getScoresMap(String[] info) {

        HashMap<String, ArrayList<Integer>> scoresMap = new HashMap<>();

        for (String s : info) {
            String[] tokens = s.split(" ");
            int score = Integer.parseInt(tokens[tokens.length - 1]);

            forEachKey(0, "", tokens, scoresMap, score);
        }

        for (List<Integer> list : scoresMap.values()) {
            Collections.sort(list);
        }

        return scoresMap;
    }
    
    public void forEachKey(int index, String prefix, String[] tokens, HashMap<String, ArrayList<Integer>> scoresMap, int score) {

        if (index == tokens.length - 1) {
            scoresMap.putIfAbsent(prefix, new ArrayList<>());
            scoresMap.get(prefix).add(score);
            return;
        }

        forEachKey(index + 1, prefix + tokens[index], tokens, scoresMap, score);
        forEachKey(index + 1, prefix + "-", tokens, scoresMap, score);
    }
}