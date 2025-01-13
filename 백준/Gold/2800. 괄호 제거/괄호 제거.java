import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        Deque<Integer> stk = new ArrayDeque<>();
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                stk.push(i);
            } else if (ch == ')') {
                pairs.add(new Pair(stk.pop(), i));
            }
        }

        List<String> ans = new ArrayList<>();

        for (int i = 1; i < (1 << pairs.size()); i++) {

            StringBuilder sb = new StringBuilder(s);

            for (int j = 0; j < pairs.size(); j++) {
                if ((i & (1 << j)) != 0) {
                    sb.setCharAt(pairs.get(j).openIdx, ' ');
                    sb.setCharAt(pairs.get(j).closeIdx, ' ');
                }
            }
            ans.add(sb.toString().replace(" ", ""));
        }

        ans.stream()
           .distinct()
           .sorted()
           .forEach(System.out::println);
    }

    static class Pair {

        int openIdx;
        int closeIdx;

        public Pair(int openIdx, int closeIdx) {
            this.openIdx = openIdx;
            this.closeIdx = closeIdx;
        }
    }

}