import java.util.*;

class Solution {
    public int[][] solution(int n, int[][] build_frame) {
        Set<String> set = new HashSet<>();

        for (int[] frame : build_frame) {
            int y = frame[0];
            int x = frame[1];
            int a = frame[2];
            int b = frame[3];

            //구조물 삭제
            if (b == 0) {
                String str = convert(x, y, a);
                set.removeIf(s -> s.equals(str));
                if (!isValid(set)) {
                    set.add(str);
                }
            }
            //구조물 설치
            else {
                String str = convert(x, y, a);
                set.add(str);
                if (!isValid(set)) {
                    set.remove(str);
                }
            }
        }

        return set.stream()
                  .map(s -> {
                      String[] temp = s.split(", ");
                      int x = Integer.parseInt(temp[0]);
                      int y = Integer.parseInt(temp[1]);
                      int a = Integer.parseInt(temp[2]);
                      return new int[]{y, x, a};
                  })
                  .sorted((a, b) -> {
                      if (a[0] != b[0]) return a[0] - b[0];
                      if (a[1] != b[1]) return a[1] - b[1];
                      return a[2] - b[2];
                  })
                  .toArray(int[][]::new);
    }

    private boolean isValid(Set<String> set) {
        for (String s : set) {
            String[] temp = s.split(", ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);
            int a = Integer.parseInt(temp[2]);

            if (a == 0) {
                if (x == 0 ||
                    set.contains(convert(x - 1, y, 0)) ||
                    set.contains(convert(x, y - 1, 1)) ||
                    set.contains(convert(x, y, 1))) {
                    continue;
                }
                return false;
            } else {
                if (set.contains(convert(x - 1, y, 0)) ||
                    set.contains(convert(x - 1, y + 1, 0)) ||
                    (set.contains(convert(x, y - 1, 1)) && set.contains(convert(x, y + 1, 1)))) {
                    continue;
                }
                return false;
            }
        }

        return true;
    }

    private String convert(int x, int y, int a) {
        return String.format("%d, %d, %d", x, y, a);
    }
}