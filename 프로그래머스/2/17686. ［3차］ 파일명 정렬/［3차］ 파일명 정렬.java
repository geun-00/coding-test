import java.util.*;
import java.util.regex.*;

class Solution {
    public String[] solution(String[] files) {
        String[] ans = new String[files.length];
        File[] arr = new File[files.length];

        for (int i = 0; i < files.length; i++) {
            Pattern pattern = Pattern.compile("^(\\D+)(\\d{1,5})(.*)$");
            Matcher matcher = pattern.matcher(files[i]);

            if (matcher.matches()) {
                String head = matcher.group(1);
                String number = matcher.group(2);
                String tail = matcher.group(3);

                arr[i] = new File(head, number, tail);
            }
        }

        Arrays.sort(arr, (f1, f2) -> {
            if (!f1.head.equalsIgnoreCase(f2.head)) {
                return f1.head.compareToIgnoreCase(f2.head);
            }
            int num1 = Integer.parseInt(f1.number);
            int num2 = Integer.parseInt(f2.number);
            return Integer.compare(num1, num2);
        });

        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i].toString();
        }

        return ans;
    }

    static class File {
        String head;
        String number;
        String tail;

        public File(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        public String toString() {
            return head + number + tail;
        }
    }

}