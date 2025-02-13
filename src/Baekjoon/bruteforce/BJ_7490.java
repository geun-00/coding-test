package Baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <a href = "https://www.acmicpc.net/problem/7490">백준 7490번 - 브루트포스 : 0 만들기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-7490%EB%B2%88-0-%EB%A7%8C%EB%93%A4%EA%B8%B0">velog</a>
 * @since 2025-01-26
 */
public class BJ_7490 {

    static Pattern numPattern = Pattern.compile("\\d+");
    static Pattern operPattern = Pattern.compile("[+-]");
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            solve(1, "1", n);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void solve(int depth, String expr, int n) {
        if (depth == n) {
            if (test(expr)) {
                sb.append(expr).append("\n");
            }
            return;
        }

        solve(depth + 1, expr + " " + (depth + 1), n);
        solve(depth + 1, expr + "+" + (depth + 1), n);
        solve(depth + 1, expr + "-" + (depth + 1), n);
    }

    private static boolean test(String expr) {

        expr = expr.replace(" ", "");

        List<Integer> numbers = getNumbers(expr); //숫자 추출
        List<String> operators = getOperators(expr); //연산자 추출

        return getResult(numbers, operators) == 0;
    }

    private static List<Integer> getNumbers(String expr) {

        List<Integer> numbers = new ArrayList<>();

        Matcher numMatcher = numPattern.matcher(expr);

        while (numMatcher.find()) {
            numbers.add(Integer.valueOf(numMatcher.group()));
        }

        return numbers;
    }

    private static List<String> getOperators(String expr) {

        List<String> operators = new ArrayList<>();

        Matcher operMatcher = operPattern.matcher(expr);

        while (operMatcher.find()) {
            operators.add(operMatcher.group());
        }

        return operators;
    }

    private static int getResult(List<Integer> numbers, List<String> operators) {

        int result = numbers.get(0);

        for (int i = 0; i < operators.size(); i++) {

            String operation = operators.get(i);
            int num = numbers.get(i + 1);

            result += operation.equals("+") ? num : -num;
        }

        return result;
    }
}