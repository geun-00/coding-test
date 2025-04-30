import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine()
                       .toCharArray();

        Deque<Character> stack = new ArrayDeque<>();

        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.push(arr[i]);
            }
            else {
                stack.pop();
                if (arr[i - 1] == '(') {
                    ans += stack.size();
                } else {
                    ans += 1;
                }
            }
        }

        System.out.println(ans);
    }
}