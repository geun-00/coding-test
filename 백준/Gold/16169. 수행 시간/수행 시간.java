import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Integer>[] graph = new List[n + 1];
        List<Computer>[] computers = new List[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            computers[i] = new ArrayList<>();
        }

        int[] grades = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int grade = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            computers[grade].add(new Computer(i, time));
            grades[i] = grade;
        }

        int[] inDegree = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            for (Computer c : computers[i]) {
                inDegree[c.num] = computers[i - 1].size();
            }
        }

        Queue<Computer> qu = new ArrayDeque<>();
        for (Computer c : computers[1]) {
            qu.offer(new Computer(c.num, 0));
        }

        int[] arr = new int[n + 1];

        int ans = 0;
        while (!qu.isEmpty()) {
            Computer cur = qu.poll();

            int nowNum = cur.num;
            int start = cur.time;
            int time = 0;
            int curGrade = grades[nowNum];

            for (Computer computer : computers[curGrade]) {
                if (computer.num == nowNum) {
                    time = computer.time;
                    break;
                }
            }

            int finish = start + time;
            ans = Math.max(ans, finish);
            
            if (curGrade == n) continue;

            for (Computer next : computers[curGrade + 1]) {
                int nextNum = next.num;
                int transferTime = (nowNum - nextNum) * (nowNum - nextNum);
                int totalTime = finish + transferTime;

                arr[nextNum] = Math.max(arr[nextNum], totalTime);

                if (--inDegree[nextNum] == 0) {
                    qu.offer(new Computer(nextNum, arr[nextNum]));
                }
            }
        }

        System.out.println(ans);
    }

    static class Computer {
        int num;
        int time;

        public Computer(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
}