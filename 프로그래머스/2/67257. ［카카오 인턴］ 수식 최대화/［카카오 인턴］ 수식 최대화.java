import java.util.*;

class Solution {
    
    static String[][] arr = {
            "+-*".split(""),
            "+*-".split(""),
            "-+*".split(""),
            "-*+".split(""),
            "*+-".split(""),
            "*-+".split(""),
    };
    
    public long solution(String expression) {
        StringTokenizer st = new StringTokenizer(expression, "+-*", true);

        List<String> tokens = new ArrayList<>();

        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }

        long ans = 0;

        for (String[] ops : arr) {

            long num = solve(ops, new LinkedList<>(tokens));
            ans = Math.max(ans, num);
        }

        return ans;
    }
    
    public long solve(String[] ops, List<String> tokens) {

        for (String op : ops) {
            
        ListIterator<String> iter = tokens.listIterator();

            while (iter.hasNext()) {

                String cur = iter.next();

                if (cur.equals(op)) {

                    iter.previous();
                    long left = Long.parseLong(iter.previous());
                    
                    iter.next();
                    iter.next();
                    long right = Long.parseLong(iter.next());

                    long result = calculate(left, right, cur);

                    for (int i = 0; i < 3; i++) {
                        iter.previous();
                        iter.remove();
                    }

                    iter.add(String.valueOf(result));
                }
            }
        }
        
        return Math.abs(Long.parseLong(tokens.get(0)));
    }
    
    public long calculate(long left, long right, String op) {
        switch (op) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
        }
        return 0;
    }
}