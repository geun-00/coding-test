import sys
from collections import defaultdict

input = sys.stdin.readline
dp = []


def dfs(node, parent, tree):
    if len(tree[node]) == 1 and tree[node][0][0] == parent:
        dp[node] = tree[node][0][1]
        return dp[node]

    for next_node, dynamite in tree[node]:

        if next_node != parent:
            cost = dfs(next_node, node, tree)
            dp[node] += min(cost, dynamite)

    return dp[node]


t = int(input())

ans = []

for _ in range(t):

    n, m = map(int, input().split())

    tree = defaultdict(list)
    dp = [0] * n

    for _ in range(m):
        u, v, d = map(int, input().split())
        u -= 1
        v -= 1

        tree[u].append((v, d))
        tree[v].append((u, d))

    ans.append(dfs(0, -1, tree))

print("\n".join(map(str, ans)))
