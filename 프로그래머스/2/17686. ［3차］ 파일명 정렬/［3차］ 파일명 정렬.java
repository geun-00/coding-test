import java.util.*;
import java.util.regex.*;

class Solution {
    public String[] solution(String[] files) {
                
        Pattern pattern = Pattern.compile("(\\D+)(\\d{1,5})(.*)");

        File[] arr = Arrays.stream(files)
                             .map(s -> {
                                 Matcher matcher = pattern.matcher(s);
                                 matcher.find();

                                 String head = matcher.group(1);
                                 String number = matcher.group(2);
                                 String tail = matcher.group(3);

                                 return new File(head, number, tail);
                             })
                             .toArray(File[]::new);

        return Arrays.stream(arr)
                     .sorted((f1, f2) -> {
                         if (!f1.head.equalsIgnoreCase(f2.head)) {
                             return f1.head.compareToIgnoreCase(f2.head);
                         }
                         return Integer.compare(
                             Integer.parseInt(f1.number),
                             Integer.parseInt(f2.number)
                         );
                     })
                     .map(File::toString)
                     .toArray(String[]::new);
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