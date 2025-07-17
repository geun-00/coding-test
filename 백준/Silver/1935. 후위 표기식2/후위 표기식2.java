import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Deque<Double> stack = new ArrayDeque<>();

        double[] arr = new double[26];
        String query = br.readLine();

        for (int i = 0; i < n; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }

        for (char c : query.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                stack.push(arr[c - 'A']);
            } else {
                double second = stack.pop();
                double first = stack.pop();
                stack.push(calculate(first, second, c));
            }
        }

        System.out.printf("%.2f", stack.pop());
    }

    private static double calculate(double value1, double value2, char c) {
        switch (c) {
            case '+': return value1 + value2;
            case '-': return value1 - value2;
            case '*': return value1 * value2;
            default: return value1 / value2;
        }
    }
}