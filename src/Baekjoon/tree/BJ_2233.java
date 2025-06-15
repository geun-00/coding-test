package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href = "https://www.acmicpc.net/problem/2233">백준 2233번 - 트리 : 사과나무</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2233%EB%B2%88-%EC%82%AC%EA%B3%BC%EB%82%98%EB%AC%B4">velog</a>
 *
 * @since 2025-05-25
 */
public class BJ_2233 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        char[] input = br.readLine().toCharArray();
        String[] arr = br.readLine().split(" ");

        int X = Integer.parseInt(arr[0]) - 1;
        int Y = Integer.parseInt(arr[1]) - 1;

        Deque<Integer> stack = new ArrayDeque<>();

        int nodeNum = 1;    //노드 번호
        int depth = 1;      //현재 노드의 레벨

        // 1
        int[] start = new int[n + 1];       //각 노드의 방문할 때 위치
        int[] end = new int[n + 1];         //각 노드의 리턴될 때 위치
        int[] level = new int[n + 1];       //각 노드의 레벨
        int[] parent = new int[n + 1];      //각 노드의 부모 노드

        int[] nodeIndex = new int[n * 2];   //노드의 방문 및 리턴 순서

        // 2
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '0') {
                start[nodeNum] = i + 1; //현재 노드의 방문 위치 저장

                if (!stack.isEmpty()) {
                    int par = stack.peek(); //부모 노드
                    parent[nodeNum] = par;
                }

                //현재 노드 방문 처리
                stack.push(nodeNum);
                level[nodeNum] = depth;
                nodeIndex[i] = nodeNum;

                //노드 번호와 레벨 증가
                nodeNum++;
                depth++;
            }
            else {
                int cur = stack.pop(); //가장 마지막에 방문했던 노드
                end[cur] = i + 1;      //마지막 방문 노드의 리턴 위치 저장
                nodeIndex[i] = cur;
                depth--; //레벨 감소
            }
        }

        // 3
        int lca = getLCA(nodeIndex[X], nodeIndex[Y], level, parent);
        System.out.println(start[lca] + " " + end[lca]);
    }

    private static int getLCA(int x, int y, int[] level, int[] parent) {
        //항상 y 노드가 더 깊은 노드로 맞추기
        if (level[x] > level[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        //레벨을 맞춘다
        while (level[x] != level[y]) {
            y = parent[y];
        }

        //최소 공통 조상 노드를 찾는다
        while (x != y) {
            x = parent[x];
            y = parent[y];
        }

        return x;
    }
}
