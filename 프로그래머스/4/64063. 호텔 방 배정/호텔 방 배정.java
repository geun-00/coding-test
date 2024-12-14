import java.util.*;

class Solution {
    
    static Map<Long, Long> parent = new HashMap<>();
    
    public long[] solution(long k, long[] room_number) {
        
        long[] ans = new long[room_number.length];

        for (int i = 0; i < room_number.length; i++) {

            long num = room_number[i];
            long found = find(num);

            ans[i] = found;

            union(num, found + 1);
        }

        return ans;
    }

    public long find(long num) {

        if (!parent.containsKey(num)) {
            return num;
        }

        long root = find(parent.get(num));
        parent.put(num, root);
        return root;
    }

    public void union(long a, long b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent.put(a, b);
        }
    }
}