import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int col = relation[0].length;
        List<List<Integer>> candidateKeys = new ArrayList<>();

        iter:
        for (int i = 1; i < (1 << col); i++) {

            List<Integer> list = new ArrayList<>();

            for (int j = 0; j < col; j++) {
                if ((i & (1 << j)) != 0) {
                    list.add(j);
                }
            }

            // 유일성 검사
            Set<String> set = new HashSet<>();
            for (String[] row : relation) {
                StringBuilder sb = new StringBuilder();
                for (int index : list) {
                    sb.append(row[index]);
                }
                if (!set.add(sb.toString())) {
                    continue iter;
                }
            }

            // 최소성 검사
            for (List<Integer> key : candidateKeys) {
                if (list.containsAll(key)) {
                    continue iter;
                }
            }

            candidateKeys.add(list);
        }

        return candidateKeys.size();
    }
}