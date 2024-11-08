import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] counting = new int[8001];
        int min = 4001, max = -4001;
        double sum = 0;
        int maxFreq = 0;
        int first = 4001, second = 4001;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            sum += num;
            counting[num + 4000]++;

            min = Math.min(min, num);
            max = Math.max(max, num);

            if (counting[num + 4000] > maxFreq) {
                maxFreq = counting[num + 4000];
                first = num;
                second = 4001;
            } else if (counting[num + 4000] == maxFreq) {
                if (num < first) {
                    second = first;
                    first = num;
                } else if (num < second) {
                    second = num;
                }
            }
        }

        //산술평균
        sb.append(Math.round(sum / n)).append("\n");

        //중앙값
        int count = 0;
        for (int i = 0; i <= 8000; i++) {
            count += counting[i];

            if (count >= (n + 1) / 2) {
                sb.append(i - 4000).append("\n");
                break;
            }
        }

        sb.append(second == 4001 ? first : second).append("\n");

        //범위
        sb.append(max - min);

        System.out.print(sb);
    }
}