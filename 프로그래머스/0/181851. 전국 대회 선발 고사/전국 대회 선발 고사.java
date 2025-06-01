import java.util.*;

class Solution {
    public int solution(int[] rank, boolean[] attendance) {
        
        List<Integer> candidate = new ArrayList<>();
        
        for (int i = 0; i < rank.length; i++) {
            if (attendance[i]) {
                candidate.add(rank[i]);
            }
        }
        
        for (int a : candidate) {
            System.out.println(a);
        }
        
        candidate.sort(null);
        
        int a = 10000 * getIndex(candidate.get(0), rank);
        int b = 100 * getIndex(candidate.get(1), rank);
        int c = getIndex(candidate.get(2), rank);
        
        return a + b + c;
    }
    
    public int getIndex(int target, int[] rank) {
        for (int i = 0; i < rank.length; i++) {
            if (rank[i] == target) {
                return i;
            }
        }
        
        return -1;
    }
}