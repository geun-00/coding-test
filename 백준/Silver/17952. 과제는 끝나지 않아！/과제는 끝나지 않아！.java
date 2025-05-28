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

        Deque<Task> tasks = new ArrayDeque<>();
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());

            if (m == 1) {
                int a = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                if (t - 1 == 0) {
                    ans += a;
                } else {
                    tasks.push(new Task(a, t - 1));
                }
            } else {
                if (!tasks.isEmpty()) {
                    Task task = tasks.peek();
                    task.remainTime -= 1;
                    if (task.remainTime == 0) {
                        ans += task.score;
                        tasks.pop();
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static class Task {
        int score;
        int remainTime;

        public Task(int score, int remainTime) {
            this.score = score;
            this.remainTime = remainTime;
        }
    }
}