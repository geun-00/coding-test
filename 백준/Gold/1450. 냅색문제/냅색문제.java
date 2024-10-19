import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int c;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //물건의 개수
        c = Integer.parseInt(st.nextToken()); //최대 c만큼

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        //0 ~ (2/N - 1) 부분
        dfs(0, n / 2, 0, left);

        //2/N ~ N-1 부분
        dfs(n / 2, n, 0, right);

        int count = 0;

        //이분 탐색을 위해 정렬
        Collections.sort(right);

        for (int num : left) {
            count += upperBound(c - num, right); //다른 한쪽에서 c-num 이하의 값들의 개수를 더해준다.
        }

        bw.write(String.valueOf(count));
        bw.flush();

        bw.close();
        br.close();
    }

    private static int upperBound(int target, ArrayList<Integer> list) {

        int left = 0;
        int right = list.size() - 1;
        int mid;

        while (left <= right) {

            mid = (left + right) / 2;

            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static void dfs(int s, int e, int sum, ArrayList<Integer> list) {

        if (sum > c) {
            return;
        }

        if (s == e) {
            list.add(sum);
            return;
        }

        //현재 물건을 넣지 않는 경우
        dfs(s + 1, e, sum, list);

        //현재 물건을 넣는 경우
        dfs(s + 1, e, sum + arr[s], list);
    }
}