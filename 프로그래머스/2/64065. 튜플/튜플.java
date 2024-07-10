import java.util.*;

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);

        String[] arr = s.split("},\\{");

        Arrays.sort(arr, (o1, o2) -> o1.length() - o2.length());

        HashSet<Integer> set = new HashSet<>();
        int[] result = new int[arr.length];

        int index = 0;
        for (String string : arr) {
            for (String str : string.split(",")) {
                int num = Integer.parseInt(str);
                if (set.add(num)) {
                    result[index++] = num;
                    break;
                }
            }
        }
        
        return result;
    }
}