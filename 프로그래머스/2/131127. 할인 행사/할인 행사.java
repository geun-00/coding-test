import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int count = 0;

        for (int i = 0; i <= discount.length - 10; i++) {

            HashMap<String, Integer> map = new HashMap<>();

            for (int j = i; j < i + 10; j++) {
                map.put(discount[j], map.getOrDefault(discount[j], 0) + 1);
            }

            boolean check = true;
            for (int j = 0; j < want.length; j++) {
                int num = number[j];

                if (map.getOrDefault(want[j], 0) != num) {
                    check = false;
                    break;
                }
            }

            if (check) {
                count++;
            }
        }

        return count;
    }
}