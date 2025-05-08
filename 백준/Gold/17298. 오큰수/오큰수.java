import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            result[i] = -1;
        }

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                result[stack.pop()] = arr[i];
            }

            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(" ");
        }
        System.out.print(sb.toString().trim());
    }
}