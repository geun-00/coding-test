import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Queue<String> in = new ArrayDeque<>();
        Queue<String> out = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            in.offer(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            out.offer(br.readLine());
        }

        int ans = 0;

        while (!out.isEmpty()) {

            String outCar = out.poll();

            if (!in.isEmpty() && !in.peek().equals(outCar)) {
                ans++;
                in.remove(outCar);
            } else {
                in.poll();
            }
        }

        System.out.println(ans);
    }
}