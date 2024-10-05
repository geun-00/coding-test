import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] tree;
    static long[] sheep;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        sheep = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 2; i <= n; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            char t = st.nextToken().charAt(0);          //w=늑대, s=양
            long a = Long.parseLong(st.nextToken());   //마리 수
            int p = Integer.parseInt(st.nextToken());   //연결된 노드 번호

            tree[p].add(i);

            if (t == 'S') {     //양인 경우, 양수로 저장
                sheep[i] = a;
            } else {
                sheep[i] = -a;  //늑대인 경우, 음수로 저장
            }
        }

        System.out.println(dfs(1));
    }

    private static long dfs(int node) {

        long sum = 0;

        for (int next : tree[node]) { //자식 노드 끝까지 갔다가 부모 노드로 올라오면서 계산
            sum += dfs(next);
        }
        //현재 양인 경우, 그대로 더함
        if (sheep[node] > 0) {
            sum += sheep[node];
        }
        //현재 늑대인 경우, 음수가 더해지므로 빼진다.
        //빼졌을 때 결과가 음수면 늑대가 모든 양을 잡아먹은 것이다.
        else {
            sum += sheep[node];
            if (sum < 0) {
                sum = 0;
            }
        }

        return sum;
    }
}
