package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13325">백준 13325번 - 트리 : 이진 트리</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13325%EB%B2%88-%EC%9D%B4%EC%A7%84-%ED%8A%B8%EB%A6%AC">velog</a>
 *
 * @since 2024-10-10
 */
public class BJ_13325 {

    static int[] weight;
    static int result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        int edges = (int) (Math.pow(2, k + 1));
        weight = new int[edges];

        StringTokenizer st = new StringTokenizer(br.readLine());

        //2번 ~ 마지막 리프노드 번호 까지 가중치
        for (int i = 2; i < edges; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, k);

        System.out.println(result);
    }


    private static int dfs(int node, int k) {
        //리프 노드
        if (k == 0) {
            result += weight[node];
            return weight[node];
        }

        int leftChild = node * 2;
        int rightChild = node * 2 + 1;

        //현재 노드의 왼쪽 노드로부터 리프 노드까지의 거리
        int leftDist = dfs(leftChild, k - 1);

        //현재 노드의 오른쪽 노드로부터 리프 노드까지의 거리
        int rightDist = dfs(rightChild, k - 1);

        //두 길이가 다르다면 짧은 쪽의 길이를 더 길게 만들어 길이를 같게 맞춰야 한다.
        //만약 두 길이가 같다면 0을 더하고 부모 노드에서 조정하게 된다.
        result += weight[node] + Math.abs(leftDist - rightDist);

        //길이가 더 긴 쪽의 길이로 맞춰야하므로 큰 값을 반환한다.
        return weight[node] + Math.max(leftDist, rightDist);
    }
}
