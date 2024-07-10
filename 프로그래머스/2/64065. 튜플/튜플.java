import java.util.*;

class Solution {
    public List<Integer> solution(String s) {
        s = s.substring(2, s.length() - 2);

        String[] split = s.split("},\\{");

        Arrays.sort(split, (o1, o2) -> o1.length() - o2.length());

        ArrayList<Integer> list = new ArrayList<>();

        for (String string : split) {
            for (String str : string.split(",")) {
                int num = Integer.parseInt(str);
                if (!list.contains(num)) {
                    list.add(num);
                }
            }
        }
        
        return list;
    }
}