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

            List<String> tempList = new LinkedList<>(tokens);

            for (String op : ops) {

                for (int i = 0; i < tempList.size(); i++) {

                    if (tempList.get(i).equals(op)) {

                        long left = Long.parseLong(tempList.get(i - 1));
                        long right = Long.parseLong(tempList.get(i + 1));
                        long result = calculate(left, right, tempList.get(i));

                        tempList.remove(i - 1);
                        tempList.remove(i - 1);
                        tempList.remove(i - 1);

                        tempList.add(i - 1, String.valueOf(result));
                        i -= 2;
                    }
                }
            }

            long num = Long.parseLong(tempList.get(0));
            ans = Math.max(ans, Math.abs(num));
        }

        return ans;
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