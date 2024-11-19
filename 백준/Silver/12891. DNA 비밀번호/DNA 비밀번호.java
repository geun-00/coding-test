import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int num;
    static int[] nowArr, targetArr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        num = 0;
        nowArr = new int[4];
        targetArr = new int[4];

        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        char[] arr = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            targetArr[i] = Integer.parseInt(st.nextToken());

            if (targetArr[i] == 0) {
                num++;
            }
        }

        int ans = 0;

        for (int i = 0; i < p; i++) {
            add(arr[i]);
        }

        if (num == 4) {
            ans++;
        }

        for (int i = p; i < s; i++) {
            int j = i - p;

            add(arr[i]);
            remove(arr[j]);

            if (num == 4) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static void add(char c) {

        switch (c) {
            case 'A':
                nowArr[0]++;
                if (nowArr[0] == targetArr[0]) {
                    num++;
                }
                break;
            case 'C':
                nowArr[1]++;
                if (nowArr[1] == targetArr[1]) {
                    num++;
                }
                break;
            case 'G':
                nowArr[2]++;
                if (nowArr[2] == targetArr[2]) {
                    num++;
                }
                break;
            case 'T':
                nowArr[3]++;
                if (nowArr[3] == targetArr[3]) {
                    num++;
                }
                break;
        }
    }

    private static void remove(char c) {

        switch (c) {
            case 'A':
                if (nowArr[0] == targetArr[0]) {
                    num--;
                }
                nowArr[0]--;
                break;
            case 'C':
                if (nowArr[1] == targetArr[1]) {
                    num--;
                }
                nowArr[1]--;
                break;
            case 'G':
                if (nowArr[2] == targetArr[2]) {
                    num--;
                }
                nowArr[2]--;
                break;
            case 'T':
                if (nowArr[3] == targetArr[3]) {
                    num--;
                }
                nowArr[3]--;
                break;
        }
    }
}